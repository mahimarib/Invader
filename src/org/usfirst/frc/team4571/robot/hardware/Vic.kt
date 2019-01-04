package org.usfirst.frc.team4571.robot.hardware

import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import org.usfirst.frc.team4571.robot.Robot

class Vic(deviceNumber: Int) : WPI_VictorSPX(deviceNumber) {
    init {
        expiration = Robot.DEFAULT_PERIOD
        isSafetyEnabled = false
        setNeutralMode(NeutralMode.Brake)
    }
}