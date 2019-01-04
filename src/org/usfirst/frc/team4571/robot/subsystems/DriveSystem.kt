package org.usfirst.frc.team4571.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.PIDController
import edu.wpi.first.wpilibj.PIDOutput
import edu.wpi.first.wpilibj.SPI.Port
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import org.usfirst.frc.team4571.robot.Robot
import org.usfirst.frc.team4571.robot.RobotMap
import org.usfirst.frc.team4571.robot.hardware.CanTalon

object DriveSystem : Subsystem() {

    private object Turn {
        const val P = 1.3
        const val I = 0.0
        const val D = 3.1
    }

    private object Straight {
        const val K = 0.0
        const val I = 0.0
        const val D = 0.0
    }

    private val topLeftMotor: WPI_TalonSRX =
        CanTalon(RobotMap.Drive.TOP_LEFT_MOTOR)
    private val bottomLeftMotor: WPI_TalonSRX =
        CanTalon(RobotMap.Drive.BOTTOM_LEFT_MOTOR)
    private val topRightMotor: WPI_TalonSRX =
        CanTalon(RobotMap.Drive.TOP_RIGHT_MOTOR)
    private val bottomRightMotor: WPI_TalonSRX =
        CanTalon(RobotMap.Drive.BOTTOM_RIGHT_MOTOR)

    private val differentialDrive: DifferentialDrive

    private val navX: AHRS = AHRS(Port.kMXP)

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
        topLeftMotor.inverted = true
        bottomLeftMotor.inverted = true
        topRightMotor.inverted = true
        bottomRightMotor.inverted = true

        bottomLeftMotor.follow(topLeftMotor)
        bottomRightMotor.follow(topRightMotor)

        differentialDrive = DifferentialDrive(topLeftMotor, topRightMotor)
        differentialDrive.expiration = Robot.DEFAULT_PERIOD
        differentialDrive.isSafetyEnabled = false

        turnController = PIDController(Turn.P, Turn.I, Turn.D,
                                       navX, TurnOutput)
        turnController.setInputRange(-180.0, 180.0)
        turnController.setOutputRange(-0.8, 0.8)
        turnController.setAbsoluteTolerance(5.0)
    }

    public override fun initDefaultCommand() {}

    private object TurnOutput : PIDOutput {
        override fun pidWrite(output: Double) = drive(-output, output)
    }

    fun drive(left: Double, right: Double) =
        differentialDrive.tankDrive(left, right)

    fun stop() = drive(0.0, 0.0)

    fun resetNavX() = navX.reset()

    fun turnToAngle(angleSetPoint: Double) {
        turnController.reset()
        turnController.setpoint = angleSetPoint
        turnController.enable()
    }
}