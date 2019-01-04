package org.usfirst.frc.team4571.robot.commands.teleop.arm

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.subsystems.Elevator

object ElevatorCommand : Command() {
    init {
        requires(Elevator)
    }

    override fun initialize() = Elevator.resetEncoder()

    override fun execute() {
        SmartDashboard.putNumber("Raw Elevator Encoder Tick", Elevator.tick)
        when {
            Elevator.isLimitSwitchPressed &&
                    Robot.GAMEPAD.rightYAxis < 0 -> Elevator.stopElevator()
            else -> Elevator.setElevatorMotor(Robot.GAMEPAD.rightYAxis)
        }
    }

    override fun isFinished(): Boolean = false

    override fun end() {
        Elevator.stopElevator()
        Elevator.resetEncoder()
    }
}