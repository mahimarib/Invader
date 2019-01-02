package org.usfirst.frc.team4571.robot.commands.teleop.arm

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class ElevatorCommand : Command() {
    init {
        requires(Robot.ELEVATOR)
    }

    override fun initialize() = Robot.ELEVATOR.resetEncoder()

    override fun execute() {
        SmartDashboard.putNumber("Raw Elevator Encoder Tick", Robot.ELEVATOR.tick)
        if (Robot.ELEVATOR.isLimitSwitchPressed && Robot.GAMEPAD.rightYAxis < 0) {
            Robot.ELEVATOR.stopElevator()
        } else {
            Robot.ELEVATOR.setElevatorMotor(Robot.GAMEPAD.rightYAxis)
        }
    }

    override fun isFinished(): Boolean = false

    override fun end() {
        Robot.ELEVATOR.stopElevator()
        Robot.ELEVATOR.resetEncoder()
    }

    override fun interrupted() {}
}