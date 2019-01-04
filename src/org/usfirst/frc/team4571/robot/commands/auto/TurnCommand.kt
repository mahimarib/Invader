package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.subsystems.DriveSystem

class TurnCommand(private val angle: Double) : Command() {

    init {
        requires(DriveSystem)
    }

    override fun initialize() {
        DriveSystem.resetNavX()
        DriveSystem.turnToAngle(angle)
    }

    override fun execute() = log()

    private fun log() {
        SmartDashboard.putData("angle pid", DriveSystem.turnController)
        SmartDashboard.putNumber("angle", DriveSystem.angle)
    }

    override fun isFinished(): Boolean = DriveSystem.isTurnAngleOnTarget

    override fun end() {
        DriveSystem.stop()
        DriveSystem.turnController.disable()
        DriveSystem.resetNavX()
    }

    override fun interrupted() {
        DriveSystem.stop()
        DriveSystem.turnController.disable()
        DriveSystem.resetNavX()
    }
}