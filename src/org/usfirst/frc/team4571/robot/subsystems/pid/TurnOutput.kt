package org.usfirst.frc.team4571.robot.subsystems.pid

import edu.wpi.first.wpilibj.PIDOutput
import edu.wpi.first.wpilibj.drive.DifferentialDrive

class TurnOutput(private val differentialDrive: DifferentialDrive) : PIDOutput {

    override fun pidWrite(output: Double) = differentialDrive.tankDrive(output, -output)
}
