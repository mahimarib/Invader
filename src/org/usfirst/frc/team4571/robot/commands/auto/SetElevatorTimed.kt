package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.TimedCommand
import org.usfirst.frc.team4571.robot.subsystems.Elevator

class SetElevatorTimed(timeout: Double, private val speed: Double) : TimedCommand(timeout) {

    init {
        requires(Elevator)
    }

    override fun execute() = Elevator.setElevatorMotor(speed)

    override fun end() = Elevator.stopElevator()
}
