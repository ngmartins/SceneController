package scene_server.util;

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
    public void run() {
        System.out.println("# Call the scene " + sceneToCall);
    }
}
