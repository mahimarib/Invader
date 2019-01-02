package org.usfirst.frc.team4571.robot.commands.auto

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class TurnCommand(private val angle: Double) : Command() {

    init {
        requires(Robot.DRIVE_SYSTEM)
    }

    override fun initialize() {
        Robot.DRIVE_SYSTEM.resetNavX()
        Robot.DRIVE_SYSTEM.setTurnPIDParameter(angle)
    }

    override fun execute() = log()

    private fun log() {
        SmartDashboard.putData("angle pid", Robot.DRIVE_SYSTEM.turnController)
        SmartDashboard.putNumber("angle", Robot.DRIVE_SYSTEM.angle)
    }

    override fun isFinished(): Boolean = Robot.DRIVE_SYSTEM.isTurnAngleOnTarget

    override fun end() {
        Robot.DRIVE_SYSTEM.stop()
        Robot.DRIVE_SYSTEM.disableTurnPID()
        Robot.DRIVE_SYSTEM.resetNavX()
    }

    override fun interrupted() {
        Robot.DRIVE_SYSTEM.stop()
        Robot.DRIVE_SYSTEM.disableTurnPID()
        Robot.DRIVE_SYSTEM.resetNavX()
    }
}
