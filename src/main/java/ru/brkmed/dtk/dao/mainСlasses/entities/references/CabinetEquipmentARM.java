package ru.brkmed.dtk.dao.mainСlasses.entities.references;

import ru.brkmed.dtk.dao.mainСlasses.entities.references.OperatingSystemEnum;

public class CabinetEquipmentARM {
    OperatingSystemEnum operatingSystemEnum;
    private boolean operationPeriodARM;
    private boolean connectedSecureNetwork;

    public CabinetEquipmentARM(OperatingSystemEnum operatingSystemEnum, boolean operationPeriodARM, boolean connectedSecureNetwork) {
        this.operatingSystemEnum = operatingSystemEnum;
        this.operationPeriodARM = operationPeriodARM;
        this.connectedSecureNetwork = connectedSecureNetwork;
    }

    public OperatingSystemEnum getOperatingSystemEnum() {
        return operatingSystemEnum;
    }

    public void setOperatingSystemEnum(OperatingSystemEnum operatingSystemEnum) {
        this.operatingSystemEnum = operatingSystemEnum;
    }

    public boolean isOperationPeriodARM() {
        return operationPeriodARM;
    }

    public void setOperationPeriodARM(boolean operationPeriodARM) {
        this.operationPeriodARM = operationPeriodARM;
    }

    public boolean isConnectedSecureNetwork() {
        return connectedSecureNetwork;
    }

    public void setConnectedSecureNetwork(boolean connectedSecureNetwork) {
        this.connectedSecureNetwork = connectedSecureNetwork;
    }
}
