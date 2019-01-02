package org.usfirst.frc.team4571.robot.subsystems.pid

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX

import edu.wpi.first.wpilibj.PIDOutput

class ElevatorOutput(private var elevatorMotor: WPI_TalonSRX) : PIDOutput {

    override fun pidWrite(output: Double) = elevatorMotor.set(output)
}
