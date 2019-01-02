package org.usfirst.frc.team4571.robot.subsystems

import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.subsystems.pid.TurnOutput

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.kauailabs.navx.frc.AHRS

import edu.wpi.first.wpilibj.PIDController
import edu.wpi.first.wpilibj.SPI.Port
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.drive.DifferentialDrive

class DriveSystem : Subsystem() {
    private val topLeftMotor: WPI_TalonSRX
    private val bottomLeftMotor: WPI_TalonSRX
    private val topRightMotor: WPI_TalonSRX
    private val bottomRightMotor: WPI_TalonSRX

    private val differentialDrive: DifferentialDrive

    private val navX: AHRS

    val turnController: PIDController

    val topLeftMotorSpeed: Double
        get() = this.topLeftMotor.get()

    val bottomLeftMotorSpeed: Double
        get() = this.bottomLeftMotor.get()

    val topRightMotorSpeed: Double
        get() = this.topRightMotor.get()

    val bottomRightMotorSpeed: Double
        get() = this.bottomRightMotor.get()

    val angle: Double
        get() = this.navX.angle

    val isTurnAngleOnTarget: Boolean
        get() = this.turnController.onTarget()

    init {
        topLeftMotor = WPI_TalonSRX(
                RobotMap.TOP_LEFT_MOTOR)
        bottomLeftMotor = WPI_TalonSRX(
                RobotMap.BOTTOM_LEFT_MOTOR)
        topRightMotor = WPI_TalonSRX(
                RobotMap.TOP_RIGHT_MOTOR)
        bottomRightMotor = WPI_TalonSRX(
                RobotMap.BOTTOM_RIGHT_MOTOR)

        topLeftMotor.expiration = Robot.DEFAULT_PERIOD
        bottomLeftMotor.expiration = Robot.DEFAULT_PERIOD
        topRightMotor.expiration = Robot.DEFAULT_PERIOD
        bottomRightMotor.expiration = Robot.DEFAULT_PERIOD

        topLeftMotor.isSafetyEnabled = false
        bottomLeftMotor.isSafetyEnabled = false
        topRightMotor.isSafetyEnabled = false
        bottomRightMotor.isSafetyEnabled = false

        topLeftMotor.setNeutralMode(NeutralMode.Brake)
        bottomLeftMotor.setNeutralMode(NeutralMode.Brake)
        topRightMotor.setNeutralMode(NeutralMode.Brake)
        bottomRightMotor.setNeutralMode(NeutralMode.Brake)

        topLeftMotor.inverted = true
        bottomLeftMotor.inverted = true
        topRightMotor.inverted = true
        bottomRightMotor.inverted = true

        val leftMotors = SpeedControllerGroup(topLeftMotor, bottomLeftMotor)
        val rightMotors = SpeedControllerGroup(topRightMotor, bottomRightMotor)

        differentialDrive = DifferentialDrive(leftMotors, rightMotors)
        differentialDrive.expiration = Robot.DEFAULT_PERIOD
        differentialDrive.isSafetyEnabled = false

        navX = AHRS(Port.kMXP)

        val turnOutput = TurnOutput(differentialDrive)
        turnController = PIDController(rotate_K, rotate_I, rotate_D, navX, turnOutput)
    }

    public override fun initDefaultCommand() {}

    fun drive(left: Double, right: Double) =
            this.differentialDrive.tankDrive(left, right)

    fun stop() = drive(0.0, 0.0)

    fun resetNavX() = navX.reset()

    /**
     * used for making big turns
     *
     * @param angleSetPoint angle in degrees
     */
    fun setTurnPIDParameter(angleSetPoint: Double) {
        turnController.reset()
        turnController.setInputRange(-180.0, 180.0)
        turnController.setOutputRange(-0.8, 0.8)
        turnController.setpoint = angleSetPoint
        turnController.setAbsoluteTolerance(5.0)
        turnController.enable()
    }

    fun disableTurnPID() = turnController.disable()

    companion object {
        /**
         * For rotating
         */
        private val rotate_K = 1.3
        private val rotate_I = 0.0
        private val rotate_D = 3.1
        /**
         * For maintaining steady angle
         */
        private val tuning_K = 0.0
        private val tuning_I = 0.0
        private val tuning_D = 0.0
    }
}
