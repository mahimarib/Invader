package org.usfirst.frc.team4571.robot.commands.auto

import edu.wpi.first.wpilibj.command.TimedCommand
import org.usfirst.frc.team4571.robot.subsystems.Pulley

class SetPulleyTimed(timeout: Double) : TimedCommand(timeout) {

    init {
        requires(Pulley)
    }

    override fun execute() = Pulley.goDown()

    override fun end() = Pulley.stop()
}
