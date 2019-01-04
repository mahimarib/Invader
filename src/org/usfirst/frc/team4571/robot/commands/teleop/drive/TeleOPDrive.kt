package org.usfirst.frc.team4571.robot.commands.teleop.drive

import edu.wpi.first.wpilibj.command.Command
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.subsystems.DriveSystem

object TeleOPDrive : Command() {
    init {
        requires(DriveSystem)
    }

    override fun execute() {
        val left = Robot.LEFT_DRIVE_STICK.yAxis
        val right = Robot.RIGHT_DRIVE_STICK.yAxis
        DriveSystem.drive(left, right)
    }

    override fun isFinished(): Boolean = false

    override fun end() = DriveSystem.stop()
}