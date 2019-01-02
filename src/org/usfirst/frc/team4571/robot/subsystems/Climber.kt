package org.usfirst.frc.team4571.robot.subsystems

import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX

import edu.wpi.first.wpilibj.command.Subsystem

class Climber : Subsystem() {
    private val climberMotor: WPI_TalonSRX = WPI_TalonSRX(RobotMap.CLIMBER_MOTOR)

    val climberSpeed: Double
        get() = climberMotor.get()

    init {
        climberMotor.expiration = Robot.DEFAULT_PERIOD
        climberMotor.isSafetyEnabled = false
        climberMotor.setNeutralMode(NeutralMode.Brake)
        climberMotor.configNeutralDeadband(0.25, 200)
    }

    public override fun initDefaultCommand() {}

    fun setPower(power: Double) = climberMotor.set(power)

    fun stop() = climberMotor.stopMotor()
}