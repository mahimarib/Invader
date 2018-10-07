package org.usfirst.frc.team4571.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4571.robot.Robot;
import org.usfirst.frc.team4571.robot.RobotMap;
import org.usfirst.frc.team4571.robot.commands.teleop.arm.ArmCommand;

public class ArmSystem extends Subsystem {
    private WPI_VictorSPX leftArmMotor, rightArmMotor;
    private SpeedControllerGroup armMotors;

    public ArmSystem() {
        this.leftArmMotor = new WPI_VictorSPX(RobotMap.LEFT_ARM_MOTOR);
        this.rightArmMotor = new WPI_VictorSPX(RobotMap.RIGHT_ARM_MOTOR);

        this.rightArmMotor.setInverted(true);

        this.leftArmMotor.setExpiration(Robot.DEFAULT_PERIOD);
        this.rightArmMotor.setExpiration(Robot.DEFAULT_PERIOD);

        this.leftArmMotor.setSafetyEnabled(false);
        this.rightArmMotor.setSafetyEnabled(false);

        this.armMotors = new SpeedControllerGroup(leftArmMotor, rightArmMotor);

    }

    public void initDefaultCommand() {
        setDefaultCommand(new ArmCommand());
    }

    public void setArmMotors(double power) {
        armMotors.set(power);
    }

    public void stop() {
        setArmMotors(0.0);
    }

    public double getLeftArmSpeed() {
        return leftArmMotor.get();
    }

    public double getRightArmSpeed() {
        return rightArmMotor.get();
    }
}

