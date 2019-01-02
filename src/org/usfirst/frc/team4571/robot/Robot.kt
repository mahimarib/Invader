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
import org.usfirst.frc.team4571.robot.subsystems.*

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
class Robot : TimedRobot() {
    private val ds = DriverStation.getInstance()

    private var m_autonomousCommand: Command? = null
    private val autoChooser = SendableChooser<Command>()
    private val placementChooser = SendableChooser<Placement>()

    companion object {
        // JOYSTICKS
        val LEFT_DRIVE_STICK = DriveStick(RobotMap.LEFT_JOYSTICK)
        val RIGHT_DRIVE_STICK = DriveStick(RobotMap.RIGHT_JOYSTICK)
        val GAMEPAD = Gamepad(RobotMap.GAMEPAD)

        // SUBSYSTEMS
        val ELEVATOR = Elevator()
        val DRIVE_SYSTEM = DriveSystem()
        val ARM_SYSTEM = ArmSystem()
        val PULLEY = Pulley()
        val CLIMBER = Climber()

        // DRIVE
        private val TELE_OP_DRIVE = TeleOPDrive()

        // ARM
        private val ARM_COMMAND = ArmCommand()
        private val ELEVATOR_COMMAND = ElevatorCommand()
        private val PULLEY_COMMAND = PulleyCommand()

        // CLIMBER
        private val CLIMBER_COMMAND = ClimberCommand()

        const val DEFAULT_PERIOD: Double = TimedRobot.DEFAULT_PERIOD
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
        DRIVE_SYSTEM.resetNavX()
        ELEVATOR.resetEncoder()
    }

    override fun disabledPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun autonomousInit() {
        val gameData = ds.gameSpecificMessage
        m_autonomousCommand = autoChooser.selected

        if (m_autonomousCommand != null && gameData[0] == 'R' && m_autonomousCommand!!.name == "right") {
            m_autonomousCommand!!.start()
        } else if (m_autonomousCommand != null && gameData[0] == 'L' && m_autonomousCommand!!.name == "left") {
            m_autonomousCommand!!.start()
        } else {
            Scheduler.getInstance().add(RunMotors(4.5, 0.5))
        }
    }

    private fun log() {
        // Chassis Motors
        SmartDashboard.putNumber("top left speed", DRIVE_SYSTEM.topLeftMotorSpeed)
        SmartDashboard.putNumber("bottom left speed", DRIVE_SYSTEM.bottomLeftMotorSpeed)
        SmartDashboard.putNumber("top right speed", DRIVE_SYSTEM.topRightMotorSpeed)
        SmartDashboard.putNumber("bottom right speed", DRIVE_SYSTEM.bottomRightMotorSpeed)
        // Arm Motors
        SmartDashboard.putNumber("Elevator Motor Speed", ELEVATOR.elevatorSpeed)
        SmartDashboard.putNumber("left arm speed", ARM_SYSTEM.leftArmSpeed)
        SmartDashboard.putNumber("right arm speed", ARM_SYSTEM.rightArmSpeed)
        // Climber Motor
        SmartDashboard.putNumber("Climber Motor Speed", CLIMBER.climberSpeed)
    }

    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
        SmartDashboard.putNumber("angle", DRIVE_SYSTEM.angle)
    }

    override fun teleopInit() {

        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) m_autonomousCommand!!.cancel()

        Scheduler.getInstance().add(TELE_OP_DRIVE)
        Scheduler.getInstance().add(CLIMBER_COMMAND)
        Scheduler.getInstance().add(ARM_COMMAND)
        Scheduler.getInstance().add(ELEVATOR_COMMAND)
        Scheduler.getInstance().add(PULLEY_COMMAND)
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
        log()
    }

    override fun testPeriodic() {}

    override fun toString(): String {
        return "Robot [m_autonomousCommand=$m_autonomousCommand, autoChooser=$autoChooser]"
    }

}
