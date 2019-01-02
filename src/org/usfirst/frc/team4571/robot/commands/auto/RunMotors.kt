package org.usfirst.frc.team4571.robot.commands.auto

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.TimedCommand

class RunMotors(runtime: Double, private val power: Double) : TimedCommand(runtime) {

    init {
        requires(Robot.DRIVE_SYSTEM)
    }

    override fun execute() = Robot.DRIVE_SYSTEM.drive(power, power)

    override fun end() = Robot.DRIVE_SYSTEM.stop()
}
