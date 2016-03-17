
import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class JunctionForward implements Behavior
{
	LightSensor right;
	LightSensor left;
	DifferentialPilot pilot;
	float dark;
	String[] list=new String[] {"Left","Right","Forward","Back"};
	int i=0;
	boolean suppressed=false;
	public JunctionForward(LightSensor right,LightSensor left,DifferentialPilot pilot, float darkvalue)
	{
		this.right=right;
		this.left=left;
		this.pilot=pilot;
		this.dark= darkvalue;
		
	}
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return (right.getLightValue()<= dark && left.getLightValue()<= dark);
	}
	@Override
	public void action() {
		suppressed=false;
	
		System.out.println("Junction detected");
		while(!suppressed)
		{
			if(!list[i].isEmpty())
				if(list[i].equals("Left"))
						{pilot.travel(100);pilot.rotate(90);i++;suppressed=true;}
				else if(list[i].equals("Right"))
					{pilot.travel(100);pilot.rotate(-90);i++;suppressed=true;}
				else if(list[i].equals("Forward"))
				{pilot.travel(100);i++;suppressed=true;}
				else if(list[i].equals("Back"))
				{
					pilot.rotate(180);i++;suppressed=true;
				}
				else
					System.exit(1);
			
			}	
			
	}
		
		
	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppressed=true;
	}

}