package org.usfirst.frc.team4571.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.Button
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command

class DriveStick(port: Int) : Joystick(port) {
    val button1: JoystickButton
    val button2: JoystickButton
    val button3: JoystickButton
    val button4: JoystickButton
    val button5: JoystickButton
    val button6: JoystickButton
    val button7: JoystickButton
    val button8: JoystickButton
    val button9: JoystickButton
    val button10: JoystickButton
    val button11: JoystickButton
    val button12: JoystickButton

    val yAxis: Double
        get() = -this.getRawAxis(1)

    val xAxis: Double
        get() = this.getRawAxis(0)

    init {
        this.button1 = JoystickButton(this, 1)
        this.button2 = JoystickButton(this, 2)
        this.button3 = JoystickButton(this, 3)
        this.button4 = JoystickButton(this, 4)
        this.button5 = JoystickButton(this, 5)
        this.button6 = JoystickButton(this, 6)
        this.button7 = JoystickButton(this, 7)
        this.button8 = JoystickButton(this, 8)
        this.button9 = JoystickButton(this, 9)
        this.button10 = JoystickButton(this, 10)
        this.button11 = JoystickButton(this, 11)
        this.button12 = JoystickButton(this, 12)
    }

    fun button1WhenPressed(command: Command): DriveStick {
        this.button1.whenPressed(command)
        return this
    }

    fun button2WhenPressed(command: Command): DriveStick {
        this.button2.whenPressed(command)
        return this
    }

    fun button3WhenPressed(command: Command): DriveStick {
        this.button3.whenPressed(command)
        return this
    }

    fun button4WhenPressed(command: Command): DriveStick {
        this.button4.whenPressed(command)
        return this
    }

    fun button5WhenPressed(command: Command): DriveStick {
        this.button5.whenPressed(command)
        return this
    }

    fun button6WhenPressed(command: Command): DriveStick {
        this.button6.whenPressed(command)
        return this
    }

    fun button7WhenPressed(command: Command): DriveStick {
        this.button7.whenPressed(command)
        return this
    }

    fun button8WhenPressed(command: Command): DriveStick {
        this.button8.whenPressed(command)
        return this
    }

    fun button9WhenPressed(command: Command): DriveStick {
        this.button9.whenPressed(command)
        return this
    }

    fun button10WhenPressed(command: Command): DriveStick {
        this.button10.whenPressed(command)
        return this
    }

    fun button11WhenPressed(command: Command): DriveStick {
        this.button11.whenPressed(command)
        return this
    }

    fun button12WhenPressed(command: Command): DriveStick {
        this.button12.whenPressed(command)
        return this
    }
}