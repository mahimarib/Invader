package org.usfirst.frc.team4571.robot.commands.teleop.arm

import org.usfirst.frc.team4571.robot.Robot

import edu.wpi.first.wpilibj.command.Command

/**
 * This command controls the wheels of the arms, it will spin when a button
 * is held, one button will spin it forward for out take and spin the other way
 * for intake
 *
 * @author Mahim
 */
class ArmCommand : Command() {
    companion object {
        private const val OPTIMAL_OUTTAKE_SPEED = 0.50
        private const val LOWER_SPEED = 0.3
    }

    init {
        requires(Robot.ARM_SYSTEM)
    }

    override fun initialize() {}

    override fun execute() {
        val rightTrigger = Robot.GAMEPAD.rightTrigger
        val leftTrigger = Robot.GAMEPAD.leftTrigger

        if (rightTrigger > 0 && rightTrigger > leftTrigger) {
            Robot.ARM_SYSTEM.setArmMotors(-rightTrigger)
        } else if (leftTrigger > 0 && leftTrigger > rightTrigger) {
            Robot.ARM_SYSTEM.setArmMotors(leftTrigger)
        } else if (Robot.GAMEPAD.buttonB.get()) {
            Robot.ARM_SYSTEM.setArmMotors(OPTIMAL_OUTTAKE_SPEED)
        } else if (Robot.GAMEPAD.buttonA.get()) {
            Robot.ARM_SYSTEM.setArmMotors(LOWER_SPEED)
        } else {
            Robot.ARM_SYSTEM.stop()
        }
    }

    override fun isFinished(): Boolean = false

    override fun end() = Robot.ARM_SYSTEM.stop()

    override fun interrupted() {}

}
