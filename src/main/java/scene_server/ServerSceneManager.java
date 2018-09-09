package scene_server;

import org.springframework.web.bind.annotation.*;
import scene_server.util.SceneControllerParser;
import scene_server.util.SceneSingleAction;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class ServerSceneManager {

    private static final String CONF_FILE = "SceneConfig.xml";
    private static SceneControllerParser sceneParser = new SceneControllerParser();
    private static Map<Integer, List<SceneSingleAction>> sceneMap;
    private static SceneRunner sceneRunner = new SceneRunner();


    @RequestMapping(value="/scene/{id}")
    @ResponseBody
    public String runScene(@PathVariable("id") int id) throws ParseException {

        //if config file not parsed yet, then parseit
        sceneMapChecking();


        if (sceneMap.containsKey(id)){

            try {
                sceneRunner.runScene(id, sceneMap.get(id));
            } catch (Exception e) {
                e.printStackTrace();
                return "ERROR: Running scene " + id;
            }
            return "Running scene " + id;
        }else{
            return "Scene " + id + " does not exist!";
        }

    }


    private void sceneMapChecking() throws ParseException {

            if (sceneMap==null){
                try {
                    sceneParser.parseConfigFile(CONF_FILE);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ParseException("ERROR: Fail to parse configuration file", 0);
                }
                sceneMap =  sceneParser.getSceneMap();

        }else {
            System.out.println("PARSED");
        }
    }

}
