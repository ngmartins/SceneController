package scene_server.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class ActionCallScene implements SceneSingleAction {

    int sceneToCall;

    public ActionCallScene(int sceneToCall) {
        this.sceneToCall = sceneToCall;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionCallScene that = (ActionCallScene) o;
        return sceneToCall == that.sceneToCall;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sceneToCall);
    }



    @Override
    public void run() throws Exception {
        System.out.println("# Call the scene " + sceneToCall);


        try {
            URL url = new URL("http://localhost:8080/scene/" + sceneToCall);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.getResponseCode();
            con.disconnect();

        } catch (Exception e) {
            System.out.println("#" + '\t' +"ERROR: on Calling scene "+ sceneToCall );
            e.printStackTrace();
            throw new Exception();

        }
    }
}
