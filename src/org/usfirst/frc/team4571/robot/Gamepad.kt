package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton

class Gamepad(port: Int) : Joystick(port) {
    val buttonA = JoystickButton(this, 1)
    val buttonB = JoystickButton(this, 2)
    val buttonX = JoystickButton(this, 3)
    val buttonY = JoystickButton(this, 4)
    val leftBumper = JoystickButton(this, 5)
    val rightBumper = JoystickButton(this, 6)
    val backButton = JoystickButton(this, 7)
    val startButton = JoystickButton(this, 8)
    val leftJoystickButton = JoystickButton(this, 9)
    val rightJoystickButton = JoystickButton(this, 10)

    val leftXAxis: Double
        get() = getRawAxis(0)

    // negative because controls are reversed
    val leftYAxis: Double
        get() = -getRawAxis(1)

    val rightXAxis: Double
        get() = getRawAxis(4)

    // negative because controls are reversed
    val rightYAxis: Double
        get() = -getRawAxis(5)

    val leftTrigger: Double
        get() = getRawAxis(2)

    val rightTrigger: Double
        get() = getRawAxis(3)

    val isDPadPressedUp: Boolean
        get() = pov == 0

    val isDPadPressedDown: Boolean
        get() = pov == 180
}
