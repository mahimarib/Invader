package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton

class Gamepad(port: Int) : Joystick(port) {
    val buttonA: JoystickButton = JoystickButton(this, 1)
    val buttonB: JoystickButton = JoystickButton(this, 2)
    val buttonX: JoystickButton = JoystickButton(this, 3)
    val buttonY: JoystickButton = JoystickButton(this, 4)
    val leftBumper: JoystickButton = JoystickButton(this, 5)
    val rightBumper: JoystickButton = JoystickButton(this, 6)
    val backButton: JoystickButton = JoystickButton(this, 7)
    val startButton: JoystickButton = JoystickButton(this, 8)
    val leftJoystickButton: JoystickButton = JoystickButton(this, 9)
    val rightJoystickButton: JoystickButton = JoystickButton(this, 10)

    val leftXAxis: Double
        get() = this.getRawAxis(0)

    // negative because controls are reversed
    val leftYAxis: Double
        get() = -this.getRawAxis(1)

    val rightXAxis: Double
        get() = this.getRawAxis(4)

    // negative because controls are reversed
    val rightYAxis: Double
        get() = -this.getRawAxis(5)

    val leftTrigger: Double
        get() = this.getRawAxis(2)

    val rightTrigger: Double
        get() = this.getRawAxis(3)

    val isDPadPressedUp: Boolean
        get() = pov == 0

    val isDPadPressedDown: Boolean
        get() = pov == 180
}
