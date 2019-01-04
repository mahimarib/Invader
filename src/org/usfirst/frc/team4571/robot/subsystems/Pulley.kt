package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.hardware.CanTalon

object Pulley : Subsystem() {
    private const val UP_SPEED = 0.5
    private const val DOWN_SPEED = -0.5

    private val pulleyMotor: WPI_TalonSRX =
        CanTalon(RobotMap.Arm.PULLEY_MOTOR)

    public override fun initDefaultCommand() {}

    fun stop() = pulleyMotor.stopMotor()

    fun goUp() = pulleyMotor.set(UP_SPEED)

    fun goDown() = pulleyMotor.set(DOWN_SPEED)
}