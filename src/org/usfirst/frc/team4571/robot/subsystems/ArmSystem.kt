package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.hardware.Vic

object ArmSystem : Subsystem() {
    private val leftArmMotor: WPI_VictorSPX = Vic(RobotMap.Arm.LEFT_ARM_MOTOR)
    private val rightArmMotor: WPI_VictorSPX = Vic(RobotMap.Arm.RIGHT_ARM_MOTOR)
    private val armMotors: SpeedControllerGroup =
        SpeedControllerGroup(leftArmMotor, rightArmMotor)

    val leftArmSpeed: Double
        get() = leftArmMotor.get()

    val rightArmSpeed: Double
        get() = rightArmMotor.get()

    init {
        rightArmMotor.inverted = true
    }

    public override fun initDefaultCommand() {}

    fun setArmMotors(power: Double) = armMotors.set(power)

    fun stop() = setArmMotors(0.0)
}

