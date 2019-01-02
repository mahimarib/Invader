package org.usfirst.frc.team4571.robot.commands.teleop.climber

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command

class ClimberCommand : Command() {
    init {
        requires(Robot.CLIMBER)
    }

    override fun initialize() {}

    override fun execute() = Robot.CLIMBER.setPower(Robot.GAMEPAD.leftYAxis)

    override fun isFinished(): Boolean = false

    override fun end() = Robot.CLIMBER.stop()

    override fun interrupted() {}
}
