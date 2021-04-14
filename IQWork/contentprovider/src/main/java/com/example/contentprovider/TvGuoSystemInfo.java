package com.example.contentprovider;

public class TvGuoSystemInfo {
    private String deviceName;
    private String firmwareVersion;
    private String deskTopVersion;
    private String deviceModel;
    private String deviceSN;

    public TvGuoSystemInfo(String deviceName, String firmwareVersion, String deskTopVersion, String deviceModel, String deviceSN) {
        this.deviceName = deviceName;
        this.firmwareVersion = firmwareVersion;
        this.deskTopVersion = deskTopVersion;
        this.deviceModel = deviceModel;
        this.deviceSN = deviceSN;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getDeskTopVersion() {
        return deskTopVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceSN() {
        return deviceSN;
    }

    @Override
    public String toString() {
        return "TvGuoSystemInfo{" +
                "deviceName='" + deviceName + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", deskTopVersion='" + deskTopVersion + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceSN='" + deviceSN + '\'' +
                '}';
    }
}
