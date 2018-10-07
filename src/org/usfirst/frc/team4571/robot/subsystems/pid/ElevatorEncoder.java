package org.usfirst.frc.team4571.robot.subsystems.pid;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team4571.robot.RobotMap;

public class ElevatorEncoder implements PIDSource {
    private WPI_TalonSRX elevatorMotor;

    public ElevatorEncoder(WPI_TalonSRX elevatorMotor) {
        this.elevatorMotor = elevatorMotor;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public double pidGet() {
        return -elevatorMotor.getSelectedSensorPosition(0); // the encoder returns negative values by default
    }

    public void reset() {
        elevatorMotor.setSelectedSensorPosition(0, 0, RobotMap.PERIOD_IN_MS);
    }
}
