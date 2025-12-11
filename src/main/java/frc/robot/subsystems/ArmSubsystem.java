import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class ArmSubsystem {
    private final VictorSP armMotor;
    private final Encoder armEncoder;
    //private final ProfiledPIDController armController;

    // PID Gains and Motion Profile Constraints
    private static final double kP = 0.1; // Proportional gain
    private static final double kI = 0.0; // Integral gain
    private static final double kD = 0.0; // Derivative gain
    private static final double kMaxVelocity = 1.0; // Max velocity in units/sec
    private static final double kMaxAcceleration = 0.5; // Max acceleration in units/sec^2

    private SparkMax angleMotor = new SparkMax(Constants.ARM_MOTOR_ID, MotorType.kBrushless);
    public SparkAbsoluteEncoder absAngleEncoder = angleMotor.getAbsoluteEncoder();
    private PIDController armPIDController =
       new PIDController(Constants.ARM_KP_ANGLE, Constants.ARM_KI_ANGLE, Constants.ARM_KD_ANGLE);
       
    private double angleSetpoint = 5;


    public ArmSubsystem() 
    {
        Shuffleboard.getTab("Arm Sysid Testing").addDouble("Absolute Angle", absAngleEncoder::getPosition);
        Shuffleboard.getTab("Arm Sysid Testing").addDouble("Angle ProfileGoal", () -> angleSetpoint);
        //Shuffleboard.getTab("Arm Sysid Testing").addDouble("Angle Setpoint", () -> previousTrapezoidState_Angle.position);

        Shuffleboard.getTab("Arm Sysid Testing").addDouble("Angle Motor Current", angleMotor::getOutputCurrent);        
        Shuffleboard.getTab("Arm Sysid Testing").addDouble("Angle Motor Output", angleMotor::getAppliedOutput);
        SmartDashboard.putData(armPIDController);
        angleSetpoint = absAngleEncoder.getPosition(); //Gets position in rotations
        angleMotor.getEncoder().setPosition(angleSetpoint); //Sets angleMotors internal encder to match
        armPIDController.reset();
    }

    public void SetAngleSetpoint(double targetAngle)
    {
        angleSetpoint = Units.degreesToRotations(targetAngle);
    }
    
    public void SetAngleMotor(double speed)
    {
        if (speed > 1) speed = 1;
        else if (speed < -1) speed = -1;
        angleMotor.setVoltage(1 * speed);
    }
    

    public void periodic() 
    {    
        double currentAngle = Units.rotationsToDegrees(absAngleEncoder.getPosition());
        double output = armPIDController.calculate(currentAngle);
        //Restrict to 1/4 speed
        output = MathUtil.clamp(output, -0.25, 0.25);
        SetAngleMotor(output);
    }

    public boolean atSetpoint() {
        return armPIDController.atSetpoint();
    }
}