package org.usfirst.frc.team4571.robot.subsystems

import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.commands.teleop.arm.ArmCommand

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX

import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem

class ArmSystem : Subsystem() {
    private val leftArmMotor: WPI_VictorSPX
    private val rightArmMotor: WPI_VictorSPX
    private val armMotors: SpeedControllerGroup

    val leftArmSpeed: Double
        get() = leftArmMotor.get()

    val rightArmSpeed: Double
        get() = rightArmMotor.get()

    init {
        this.leftArmMotor = WPI_VictorSPX(
                RobotMap.LEFT_ARM_MOTOR)
        this.rightArmMotor = WPI_VictorSPX(
                RobotMap.RIGHT_ARM_MOTOR)

        this.rightArmMotor.inverted = true

        this.leftArmMotor.expiration = Robot.DEFAULT_PERIOD
        this.rightArmMotor.expiration = Robot.DEFAULT_PERIOD

        this.leftArmMotor.isSafetyEnabled = false
        this.rightArmMotor.isSafetyEnabled = false

        this.armMotors = SpeedControllerGroup(leftArmMotor, rightArmMotor)

    }

    public override fun initDefaultCommand() {
        defaultCommand = ArmCommand()
    }

    fun setArmMotors(power: Double) {
        armMotors.set(power)
    }

    fun stop() {
        setArmMotors(0.0)
    }
}

