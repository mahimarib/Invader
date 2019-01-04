package org.usfirst.frc.team4571.robot.commands.teleop.arm

import edu.wpi.first.wpilibj.command.Command
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.subsystems.ArmSystem

object ArmCommand : Command() {
    private object CustomSpeed {
        const val OUTTAKE_SPEED = 0.50
        const val LOWER_SPEED = 0.3
    }

    init {
        requires(ArmSystem)
    }

    override fun execute() {
        val rightTrigger = Robot.GAMEPAD.rightTrigger
        val leftTrigger = Robot.GAMEPAD.leftTrigger
        when {
            rightTrigger > 0 && rightTrigger > leftTrigger ->
                ArmSystem.setArmMotors(-rightTrigger)
            leftTrigger > 0 && leftTrigger > rightTrigger ->
                ArmSystem.setArmMotors(leftTrigger)
            Robot.GAMEPAD.buttonB.get() ->
                ArmSystem.setArmMotors(CustomSpeed.OUTTAKE_SPEED)
            Robot.GAMEPAD.buttonA.get() ->
                ArmSystem.setArmMotors(CustomSpeed.LOWER_SPEED)
            else -> ArmSystem.stop()
        }
    }

    override fun isFinished(): Boolean = false

    override fun end() = ArmSystem.stop()
}