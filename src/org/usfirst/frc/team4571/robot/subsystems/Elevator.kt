package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.PIDController
import edu.wpi.first.wpilibj.command.Subsystem
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.subsystems.pid.ElevatorEncoder
import org.usfirst.frc.team4571.robot.subsystems.pid.ElevatorOutput

class Elevator : Subsystem() {
    private val elevatorMotor: WPI_TalonSRX
    val elevatorController: PIDController
    private val elevatorEncoder: ElevatorEncoder
    private val elevatorOutput: ElevatorOutput

    private val lowerLimit: DigitalInput

    val tick: Double
        get() = elevatorEncoder.pidGet()

    val elevatorSpeed: Double
        get() = elevatorMotor.get()

    val isOnTarget: Boolean
        get() = this.elevatorController.onTarget()

    val isLimitSwitchPressed: Boolean
        get() = lowerLimit.get()

    companion object {
        private const val MAX_HEIGHT_ENCODER_TICK = 29000.0
        private const val MAX_HEIGHT_IN_INCHES = 89.0
        private val p = 0.5
        private val i = 0.0
        private val d = 0.0
    }

    init {
        elevatorMotor = WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR)
        elevatorMotor.expiration = Robot.DEFAULT_PERIOD
        elevatorMotor.isSafetyEnabled = false
        elevatorMotor.setNeutralMode(NeutralMode.Brake)

        elevatorEncoder = ElevatorEncoder(elevatorMotor)
        elevatorOutput = ElevatorOutput(elevatorMotor)
        elevatorController = PIDController(p, i, d, elevatorEncoder, elevatorOutput)

        lowerLimit = DigitalInput(RobotMap.LIMIT_SWITCH)
    }

    public override fun initDefaultCommand() {}

    /**
     * converts the given height in inches to encoder ticks
     *
     * @param setpointInInches setpoint in inches
     */
    private fun convertSetpoint(setpointInInches: Double): Int {
        return (setpointInInches / MAX_HEIGHT_IN_INCHES * MAX_HEIGHT_ENCODER_TICK).toInt()
    }

    fun setParameters(height: Double) {
        elevatorEncoder.reset()
        elevatorController.setInputRange(0.0, MAX_HEIGHT_ENCODER_TICK)
        elevatorController.setOutputRange(-0.5, 0.8)
        elevatorController.setpoint = convertSetpoint(height).toDouble()
        elevatorController.setAbsoluteTolerance(height * 0.1) // 10% tolerance
        elevatorController.enable()
    }

    fun stopElevator() = elevatorMotor.set(0.0)

    fun resetEncoder() = elevatorEncoder.reset()

    fun disablePID() = elevatorController.disable()

    fun setElevatorMotor(power: Double) = elevatorMotor.set(power)
}