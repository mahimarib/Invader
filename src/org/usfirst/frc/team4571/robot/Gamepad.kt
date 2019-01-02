package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command

/**
 * This class is for the logitech gamepad F310
 *
 * @author Mahim
 */
class Gamepad(port: Int) : Joystick(port) {
    val buttonA: JoystickButton
    val buttonB: JoystickButton
    val buttonX: JoystickButton
    val buttonY: JoystickButton
    val leftBumper: JoystickButton
    val rightBumper: JoystickButton
    val backButton: JoystickButton
    val startButton: JoystickButton
    val leftJoystickButton: JoystickButton
    val rightJoystickButton: JoystickButton

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
        get() = this.pov == 0

    val isDPadPressedDown: Boolean
        get() = this.pov == 180

    init {
        buttonA = JoystickButton(this, 1)
        buttonB = JoystickButton(this, 2)
        buttonX = JoystickButton(this, 3)
        buttonY = JoystickButton(this, 4)

        leftBumper = JoystickButton(this, 5)
        rightBumper = JoystickButton(this, 6)

        backButton = JoystickButton(this, 7)
        startButton = JoystickButton(this, 8)

        leftJoystickButton = JoystickButton(this, 9)
        rightJoystickButton = JoystickButton(this, 10)
    }
}
