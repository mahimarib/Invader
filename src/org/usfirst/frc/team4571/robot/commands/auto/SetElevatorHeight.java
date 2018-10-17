package org.usfirst.frc.team4571.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4571.robot.Robot;

/**
 *
 */
public class SetElevatorHeight extends Command {
    private double height;

    public SetElevatorHeight(double height) {
        requires(Robot.ELEVATOR);
    }

    protected void initialize() {
        Robot.ELEVATOR.resetEncoder();
        Robot.ELEVATOR.setHeight(height);
    }

    protected void execute() {
        SmartDashboard.putNumber("encoder tick", Robot.ELEVATOR.getTick());
        SmartDashboard.putData(
                "elevator", Robot.ELEVATOR.getElevatorController());
    }

    protected boolean isFinished() {
        return Robot.ELEVATOR.isOnTarget();
    }

    protected void end() {
        Robot.ELEVATOR.stopElevator();
        Robot.ELEVATOR.disablePID();
    }

    protected void interrupted() {}
}
