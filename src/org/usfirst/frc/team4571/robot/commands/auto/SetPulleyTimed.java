package org.usfirst.frc.team4571.robot.commands.auto;

import edu.wpi.first.wpilibj.command.TimedCommand;
import org.usfirst.frc.team4571.robot.Robot;

public class SetPulleyTimed extends TimedCommand {

    public SetPulleyTimed(double timeout) {
        super(timeout);
        requires(Robot.PULLEY);
    }

    protected void initialize() {}

    protected void execute() {
        Robot.PULLEY.goDown();
    }

    protected void end() {
        Robot.PULLEY.setPulley(0);
    }

    protected void interrupted() {}
}
