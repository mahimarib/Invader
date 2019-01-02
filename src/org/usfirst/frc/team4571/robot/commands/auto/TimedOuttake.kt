package org.usfirst.frc.team4571.robot.commands.auto

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.TimedCommand

class TimedOuttake(timeout: Double) : TimedCommand(timeout) {

    init {
        requires(Robot.ARM_SYSTEM)
    }

    override fun execute() = Robot.ARM_SYSTEM.setArmMotors(0.5)

    override fun end() = Robot.ARM_SYSTEM.stop()
}
