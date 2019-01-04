package org.usfirst.frc.team4571.robot.commands.teleop.arm

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.subsystems.Pulley

object PulleyCommand : Command() {

    init {
        requires(Pulley)
    }

    override fun execute() {
        when {
            Robot.GAMEPAD.isDPadPressedUp -> Pulley.goUp()
            Robot.GAMEPAD.isDPadPressedDown -> Pulley.goDown()
            else -> Pulley.stop()
        }
    }

    fun log() {
        SmartDashboard.putBoolean("is d pad pressed up",
                                  Robot.GAMEPAD.isDPadPressedUp)
        SmartDashboard.putBoolean("is d pad pressed down",
                                  Robot.GAMEPAD.isDPadPressedDown)
    }

    override fun isFinished(): Boolean = false

    override fun end() = Pulley.stop()
}
