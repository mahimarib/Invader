package org.usfirst.frc.team4571.robot.commands.teleop.arm

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class PulleyCommand : Command() {
    init {
        requires(Robot.PULLEY)
    }

    override fun initialize() {}

    override fun execute() {
        if (Robot.GAMEPAD.isDPadPressedUp) {
            Robot.PULLEY.goUp()
        } else if (Robot.GAMEPAD.isDPadPressedDown) {
            Robot.PULLEY.goDown()
        } else {
            Robot.PULLEY.setPulley(0.0)
        }
    }

    fun log() {
        SmartDashboard.putBoolean("is d pad pressed up", Robot.GAMEPAD.isDPadPressedUp)
        SmartDashboard.putBoolean("is d pad pressed down", Robot.GAMEPAD.isDPadPressedDown)
    }

    override fun isFinished(): Boolean = false

    override fun end() {}

    override fun interrupted() {}
}
