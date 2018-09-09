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

    //This method exposes an endpoint to all scenes and execute their actions
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
            //needs to be printed for verification of callScene action
            String msg = "# ERROR: Scene " + id + " does not exist!";
            System.out.println(msg);
            return msg;
        }
    }

    //THIS METHOD IS JUST FOR TESTING PURPOSES
    //It will exposes an endpoint to all device interactions
    //POST eg: http://localhost:8080/service1.local/ligths/22/on
    //#################################################################################################################
    @RequestMapping(value="//service1.local/{type}/{id}/{state}")
    @ResponseBody
    public String deviceReceptor(@PathVariable("type") String type, @PathVariable("id") int id, @PathVariable("state") String state) throws ParseException {
        String ret = "# The state of device " + type+ " " + id + " was changed to " + state;
        System.out.println(ret);
        return ret;
    }
    //#################################################################################################################


    private static void sceneMapChecking() throws ParseException {

            if (sceneMap==null){
                try {
                    sceneParser.parseConfigFile(CONF_FILE);
                    sceneMap =  sceneParser.getSceneMap();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ParseException("ERROR: Fail to parse configuration file", 0);
                }

        }
    }

}