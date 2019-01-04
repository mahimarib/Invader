/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4571.robot

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
object RobotMap {

    const val PERIOD_IN_MS = (Robot.DEFAULT_PERIOD * 1000).toInt()

    object Controllers {
        const val LEFT_JOYSTICK = 0
        const val RIGHT_JOYSTICK = 1
        const val GAMEPAD = 2
    }

    object Drive {
        const val TOP_LEFT_MOTOR = 2
        const val BOTTOM_LEFT_MOTOR = 1
        const val TOP_RIGHT_MOTOR = 3
        const val BOTTOM_RIGHT_MOTOR = 4
    }

    object Elevator {
        const val ELEVATOR_MOTOR = 5
        const val LIMIT_SWITCH = 0 // Make sure it is on the DIO Port
    }

    object Arm {
        const val LEFT_ARM_MOTOR = 9
        const val RIGHT_ARM_MOTOR = 8
        const val PULLEY_MOTOR = 6
    }

    const val CLIMBER_MOTOR = 7

    /** REV Blinkin PWM channel  */
    const val REV_BLINKIN_CHANNEL = 0
}