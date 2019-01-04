package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.TimedCommand
import org.usfirst.frc.team4571.robot.subsystems.DriveSystem

class RunMotors(runtime: Double, private val power: Double) : TimedCommand(runtime) {

    init {
        requires(DriveSystem)
    }

    override fun execute() = DriveSystem.drive(power, power)

    override fun end() = DriveSystem.stop()
}
