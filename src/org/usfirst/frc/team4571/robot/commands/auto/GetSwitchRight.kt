package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.CommandGroup

class GetSwitchRight : CommandGroup() {
    init {
        name = "R"
        addSequential(RunMotors(4.5, 0.5))
        addParallel(SetElevatorTimed(1.5, 0.8))
        addSequential(TurnCommand(-90.0))
        addSequential(SetPulleyTimed(2.0))
        addSequential(RunMotors(1.5, 0.5))
        addSequential(TimedOuttake(0.5))
    }
}
