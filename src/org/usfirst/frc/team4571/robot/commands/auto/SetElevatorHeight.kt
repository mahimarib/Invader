package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.Robot

class SetElevatorHeight(height: Double) : Command() {
    private val height: Double = 0.toDouble()

    init {
        requires(Robot.ELEVATOR)
    }

    override fun initialize() {
        Robot.ELEVATOR.resetEncoder()
        Robot.ELEVATOR.setParameters(height)
    }

    override fun execute() {
        SmartDashboard.putNumber("encoder tick", Robot.ELEVATOR.tick)
        SmartDashboard.putData("elevator", Robot.ELEVATOR.elevatorController)
    }

    override fun isFinished(): Boolean = Robot.ELEVATOR.isOnTarget

    override fun end() {
        Robot.ELEVATOR.stopElevator()
        Robot.ELEVATOR.disablePID()
    }
}
