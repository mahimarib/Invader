package org.usfirst.frc.team4571.robot.hardware

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.PIDSource
import edu.wpi.first.wpilibj.PIDSourceType
import org.usfirst.frc.team4571.robot.RobotMap

class ElevatorEncoder(private val elevatorMotor: WPI_TalonSRX) : PIDSource {

    override fun setPIDSourceType(pidSource: PIDSourceType) {}

    override fun getPIDSourceType(): PIDSourceType = PIDSourceType.kDisplacement

    override fun pidGet(): Double =
        (-elevatorMotor.getSelectedSensorPosition(0)).toDouble()

    fun reset() {
        elevatorMotor.setSelectedSensorPosition(0, 0, RobotMap.PERIOD_IN_MS)
    }
}
