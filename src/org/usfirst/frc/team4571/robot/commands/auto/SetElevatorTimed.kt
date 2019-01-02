package org.usfirst.frc.team4571.robot.commands.auto

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.TimedCommand

class SetElevatorTimed(timeout: Double, private val speed: Double) : TimedCommand(timeout) {

    init {
        requires(Robot.ELEVATOR)
    }

    override fun execute() = Robot.ELEVATOR.setElevatorMotor(speed)

    override fun end() = Robot.ELEVATOR.stopElevator()
}
