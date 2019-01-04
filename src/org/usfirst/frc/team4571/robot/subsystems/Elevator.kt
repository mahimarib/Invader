package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.PIDController
import edu.wpi.first.wpilibj.PIDOutput
import edu.wpi.first.wpilibj.command.Subsystem
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.hardware.CanTalon
import org.usfirst.frc.team4571.robot.hardware.ElevatorEncoder

object Elevator : Subsystem() {
    private const val MAX_HEIGHT_ENCODER_TICK = 29000.0
    private const val MAX_HEIGHT_IN_INCHES = 89.0

    private val elevatorMotor: WPI_TalonSRX = CanTalon(RobotMap.Elevator.ELEVATOR_MOTOR)
    private val lowerLimit: DigitalInput = DigitalInput(RobotMap.Elevator.LIMIT_SWITCH)
    private val elevatorEncoder: ElevatorEncoder = ElevatorEncoder(elevatorMotor)
    val elevatorController: PIDController =
        PIDController(Encoder.P, Encoder.I, Encoder.D, elevatorEncoder, ElevatorOutput)

    val tick: Double
        get() = elevatorEncoder.pidGet()

    val elevatorSpeed: Double
        get() = elevatorMotor.get()

    val isOnTarget: Boolean
        get() = this.elevatorController.onTarget()

    val isLimitSwitchPressed: Boolean
        get() = lowerLimit.get()


    private object Encoder {
        const val P = 0.5
        const val I = 0.0
        const val D = 0.0
    }

    init {
        elevatorController.setInputRange(0.0, MAX_HEIGHT_ENCODER_TICK)
        elevatorController.setOutputRange(-0.5, 0.8)
    }

    private object ElevatorOutput : PIDOutput {
        override fun pidWrite(output: Double) = setElevatorMotor(output)
    }

    public override fun initDefaultCommand() {}

    fun setElevatorMotor(power: Double) = elevatorMotor.set(power)

    private fun convertSetpoint(setpointInInches: Double): Int {
        return (setpointInInches / MAX_HEIGHT_IN_INCHES *
                MAX_HEIGHT_ENCODER_TICK).toInt()
    }

    fun goToHeight(height: Double) {
        elevatorEncoder.reset()
        elevatorController.setpoint = convertSetpoint(height).toDouble()
        elevatorController.setAbsoluteTolerance(height * 0.1) // 10% tolerance
        elevatorController.enable()
    }

    fun stopElevator() = elevatorMotor.stopMotor()

    fun resetEncoder() = elevatorEncoder.reset()

    fun disablePID() = elevatorController.disable()
}