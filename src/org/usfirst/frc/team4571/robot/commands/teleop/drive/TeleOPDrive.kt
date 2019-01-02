package org.usfirst.frc.team4571.robot.commands.teleop.drive

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command

class TeleOPDrive : Command() {
    init {
        requires(Robot.DRIVE_SYSTEM)
    }

    override fun initialize() = Robot.DRIVE_SYSTEM.stop()

    override fun execute() {
        val left = Robot.LEFT_DRIVE_STICK.yAxis
        val right = Robot.RIGHT_DRIVE_STICK.yAxis
        Robot.DRIVE_SYSTEM.drive(left, right)
    }

    override fun isFinished(): Boolean = false

    override fun end() = Robot.DRIVE_SYSTEM.stop()

    override fun interrupted() {}
}