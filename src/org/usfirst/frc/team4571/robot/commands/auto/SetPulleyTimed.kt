package org.usfirst.frc.team4571.robot.commands.auto

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.TimedCommand

class SetPulleyTimed(timeout: Double) : TimedCommand(timeout) {

    init {
        requires(Robot.PULLEY)
    }

    override fun execute() = Robot.PULLEY.goDown()

    override fun end() = Robot.PULLEY.setPulley(0.0)
}
