package org.usfirst.frc.team4571.robot.commands.auto;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.usfirst.frc.team4571.robot.Robot;

public class SetElevatorTimed extends TimedCommand {
    private double speed;

    public SetElevatorTimed(double timeout, double speed) {
        super(timeout);
        requires(Robot.ELEVATOR);
        this.speed = speed;
    }

    protected void initialize() {}

    protected void execute() {
        Robot.ELEVATOR.setElevatorMotor(speed);
    }

    protected void end() {
        Robot.ELEVATOR.stopElevator();
    }

    protected void interrupted() {}
}
