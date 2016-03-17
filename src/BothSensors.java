import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class BothSensors implements Behavior {

	LightSensor sensor1;
	LightSensor sensor2;
	DifferentialPilot pilot;
	int leftLightLevel;
	int rightLightLevel;
	boolean suppressed=false;
	float dark;
	
	public BothSensors(LightSensor sensor1,LightSensor sensor2,DifferentialPilot pilot, float  darkvalue)
	{
		this.sensor1=sensor1;
		this.sensor2=sensor2;
		this.pilot=pilot;
		this.dark = darkvalue;
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
		
	}
	
	
	public boolean takeControl() {
		leftLightLevel = sensor2.getLightValue();
		rightLightLevel = sensor1.getLightValue();
		
		System.out.println((leftLightLevel + rightLightLevel)/2 );
			return (leftLightLevel + rightLightLevel)/2 > dark ;
	}
	
	@Override
	public void action() {
		suppressed=false;
		
		float error;
		float gain = 15.0f;
		float maxSpeed = Motor.A.getMaxSpeed();
		while(!suppressed )
			{
			leftLightLevel = sensor2.getLightValue();
			rightLightLevel = sensor1 .getLightValue();
			error = rightLightLevel - leftLightLevel;

			Motor.C.forward(); 
			Motor.B.forward();
			
			Motor.C.setSpeed( (maxSpeed/2) - (error * gain)); //proportional line following.
			Motor.B.setSpeed( (maxSpeed/2) + (error * gain));
		
			}
		
	}

	@Override
	public void suppress() {
		
			suppressed=true;
			
		
	}

}