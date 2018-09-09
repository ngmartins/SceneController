package scene_server.util;


import java.util.Objects;

public class ActionDeviceInteraction implements SceneSingleAction {

    int deviceID;
    String deviceType;
    String deviceAction;

    public ActionDeviceInteraction(int deviceID, String deviceType, String deviceAction) {
        this.deviceID = deviceID;
        this.deviceType = deviceType;
        this.deviceAction = deviceAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionDeviceInteraction that = (ActionDeviceInteraction) o;
        return deviceID == that.deviceID &&
                Objects.equals(deviceType, that.deviceType) &&
                Objects.equals(deviceAction, that.deviceAction);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceID, deviceType, deviceAction);
    }


    @Override
    public void run() {
        System.out.println("# Switch state of " + deviceType + " " + deviceID + " to " + deviceAction);
    }
}
