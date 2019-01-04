/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team4571.robot.commands.auto.GetSwitchLeft
import org.usfirst.frc.team4571.robot.commands.auto.GetSwitchRight
import org.usfirst.frc.team4571.robot.commands.auto.RunMotors
import org.usfirst.frc.team4571.robot.commands.teleop.arm.ArmCommand
import org.usfirst.frc.team4571.robot.commands.teleop.arm.ElevatorCommand
import org.usfirst.frc.team4571.robot.commands.teleop.arm.PulleyCommand
import org.usfirst.frc.team4571.robot.commands.teleop.climber.ClimberCommand
import org.usfirst.frc.team4571.robot.commands.teleop.drive.TeleOPDrive
import org.usfirst.frc.team4571.robot.subsystems.ArmSystem
import org.usfirst.frc.team4571.robot.subsystems.Climber
import org.usfirst.frc.team4571.robot.subsystems.DriveSystem
import org.usfirst.frc.team4571.robot.subsystems.Elevator

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
class Robot : TimedRobot() {
    private val ds = DriverStation.getInstance()

    private var autoCommand: Command? = null
    private val autoChooser = SendableChooser<Command>()
    private val placementChooser = SendableChooser<Placement>()

    companion object {
        const val DEFAULT_PERIOD: Double = TimedRobot.DEFAULT_PERIOD

        val LEFT_DRIVE_STICK = DriveStick(RobotMap.Controllers.LEFT_JOYSTICK)
        val RIGHT_DRIVE_STICK = DriveStick(RobotMap.Controllers.RIGHT_JOYSTICK)
        val GAMEPAD = Gamepad(RobotMap.Controllers.GAMEPAD)
    }

    enum class Placement {
        Left, Middle, Right
    }

    override fun robotInit() {
        autoChooser.addDefault("Cross Line", RunMotors(4.5, 0.5))
        autoChooser.addObject("if left", GetSwitchLeft())
        autoChooser.addObject("if right", GetSwitchRight())
        SmartDashboard.putData("Auto mode", autoChooser)

        placementChooser.addObject("left", Placement.Left)
        placementChooser.addObject("middle", Placement.Middle)
        placementChooser.addObject("right", Placement.Right)
        SmartDashboard.putData("alliance placement", placementChooser)
    }

    override fun disabledInit() {
        Scheduler.getInstance().removeAll()
        DriveSystem.resetNavX()
        Elevator.resetEncoder()
    }

    override fun disabledPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun autonomousInit() {
        val gameData = ds.gameSpecificMessage
        autoCommand = autoChooser.selected
        when {
            gameData[0].equals(autoCommand?.name) -> autoCommand?.start()
            else -> Scheduler.getInstance().add(RunMotors(4.5, 0.5))
        }
    }

    private fun log() {
        // Chassis Motors
        SmartDashboard.putNumber("top left speed", DriveSystem.topLeftMotorSpeed)
        SmartDashboard.putNumber("bottom left speed", DriveSystem.bottomLeftMotorSpeed)
        SmartDashboard.putNumber("top right speed", DriveSystem.topRightMotorSpeed)
        SmartDashboard.putNumber("bottom right speed", DriveSystem.bottomRightMotorSpeed)
        // Arm Motors
        SmartDashboard.putNumber("Elevator Motor Speed", Elevator.elevatorSpeed)
        SmartDashboard.putNumber("left arm speed", ArmSystem.leftArmSpeed)
        SmartDashboard.putNumber("right arm speed", ArmSystem.rightArmSpeed)
        // Climber Motor
        SmartDashboard.putNumber("Climber Motor Speed", Climber.climberSpeed)
    }

    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
        SmartDashboard.putNumber("angle", DriveSystem.angle)
    }

    override fun teleopInit() {
        autoCommand?.cancel()
        Scheduler.getInstance().add(TeleOPDrive)
        Scheduler.getInstance().add(ClimberCommand)
        Scheduler.getInstance().add(ArmCommand)
        Scheduler.getInstance().add(ElevatorCommand)
        Scheduler.getInstance().add(PulleyCommand)
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
        log()
    }

    override fun testPeriodic() {}

    override fun toString(): String =
        "Robot [autoCommand=$autoCommand, autoChooser=$autoChooser]"
}