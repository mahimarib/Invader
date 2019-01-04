package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton

class DriveStick(port: Int) : Joystick(port) {
    val button1: JoystickButton = JoystickButton(this, 1)
    val button2: JoystickButton = JoystickButton(this, 2)
    val button3: JoystickButton = JoystickButton(this, 3)
    val button4: JoystickButton = JoystickButton(this, 4)
    val button5: JoystickButton = JoystickButton(this, 5)
    val button6: JoystickButton = JoystickButton(this, 6)
    val button7: JoystickButton = JoystickButton(this, 7)
    val button8: JoystickButton = JoystickButton(this, 8)
    val button9: JoystickButton = JoystickButton(this, 9)
    val button10: JoystickButton = JoystickButton(this, 10)
    val button11: JoystickButton = JoystickButton(this, 11)
    val button12: JoystickButton = JoystickButton(this, 12)

    val xAxis: Double
        get() = getRawAxis(0)

    val yAxis: Double
        get() = -getRawAxis(1)

    val zAxis: Double
        get() = getRawAxis(2)
}