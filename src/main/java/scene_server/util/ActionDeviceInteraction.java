package scene_server.util;


import java.net.HttpURLConnection;
import java.net.URL;
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
    public void run() throws Exception {


        try {
            System.out.println("# Switch state of " + deviceType + " " + deviceID + " to " + deviceAction);
            URL url = new URL("http://localhost:8080/service1.local/" + deviceType + '/' + deviceID + '/' + deviceAction);
            System.out.println("#" + '\t' +"POST: " + url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.getResponseCode();
            con.disconnect();

        } catch (Exception e) {
            System.out.println("#" + '\t' +"ERROR: in switching the state of " + deviceType + " " + deviceID + " to " + deviceAction );
            e.printStackTrace();
            throw new Exception();

        }
    }
}
