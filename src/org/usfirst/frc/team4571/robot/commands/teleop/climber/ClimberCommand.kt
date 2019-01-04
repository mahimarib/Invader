package org.usfirst.frc.team4571.robot.commands.teleop.climber

import edu.wpi.first.wpilibj.command.Command
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.subsystems.Climber

object ClimberCommand : Command() {
    init {
        requires(Climber)
    }

    override fun execute() = Climber.setPower(Robot.GAMEPAD.leftYAxis)

    override fun isFinished(): Boolean = false

    override fun end() = Climber.stop()
}
