package scene_server;

import scene_server.util.SceneSingleAction;

import java.util.List;

public class SceneRunner {


    public SceneRunner() {
    }

    public void runScene(int id, List<SceneSingleAction> actions) throws Exception {

        System.out.println("########### Run scene " + id + " ###########");

        for (SceneSingleAction action : actions) {
            action.run();
        }
    }



}
