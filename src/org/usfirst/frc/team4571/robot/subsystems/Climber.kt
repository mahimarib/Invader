package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.hardware.CanTalon

object Climber : Subsystem() {
    private val climberMotor: WPI_TalonSRX = CanTalon(RobotMap.CLIMBER_MOTOR)

    val climberSpeed: Double
        get() = climberMotor.get()

    init {
        climberMotor.configNeutralDeadband(0.25, RobotMap.PERIOD_IN_MS)
    }

    public override fun initDefaultCommand() {}

    fun setPower(power: Double) = climberMotor.set(power)

    fun stop() = climberMotor.stopMotor()
}