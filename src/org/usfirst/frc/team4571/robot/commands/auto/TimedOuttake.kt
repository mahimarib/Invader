package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.TimedCommand
import org.usfirst.frc.team4571.robot.subsystems.ArmSystem

class TimedOuttake(timeout: Double) : TimedCommand(timeout) {

    init {
        requires(ArmSystem)
    }

    override fun execute() = ArmSystem.setArmMotors(0.5)

    override fun end() = ArmSystem.stop()
}
