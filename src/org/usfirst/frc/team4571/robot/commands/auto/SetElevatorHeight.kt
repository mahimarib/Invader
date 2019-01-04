package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.subsystems.Elevator

class SetElevatorHeight(height: Double) : Command() {
    private val height: Double = 0.toDouble()

    init {
        requires(Elevator)
    }

    override fun initialize() {
        Elevator.resetEncoder()
        Elevator.goToHeight(height)
    }

    override fun execute() {
        SmartDashboard.putNumber("encoder tick", Elevator.tick)
        SmartDashboard.putData("elevator", Elevator.elevatorController)
    }

    override fun isFinished(): Boolean = Elevator.isOnTarget

    override fun end() {
        Elevator.stopElevator()
        Elevator.disablePID()
    }
}
