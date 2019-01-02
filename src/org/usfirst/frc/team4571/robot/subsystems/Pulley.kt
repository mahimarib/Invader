package org.usfirst.frc.team4571.robot.subsystems

import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX

import edu.wpi.first.wpilibj.command.Subsystem

class Pulley : Subsystem() {
    private val pulleyMotor: WPI_TalonSRX = WPI_TalonSRX(RobotMap.PULLEY_MOTOR)

    public override fun initDefaultCommand() {}

    init {
        pulleyMotor.expiration = Robot.DEFAULT_PERIOD
        pulleyMotor.isSafetyEnabled = false
        pulleyMotor.setNeutralMode(NeutralMode.Brake)
    }

    fun setPulley(speed: Double) = pulleyMotor.set(speed)

    fun goUp() = setPulley(UP_SPEED)

    fun goDown() = setPulley(DOWN_SPEED)

    companion object {
        private const val UP_SPEED = 0.5
        private const val DOWN_SPEED = -0.5
    }
}