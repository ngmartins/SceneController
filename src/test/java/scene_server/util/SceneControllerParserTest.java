package scene_server.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static scene_server.util.fileUtil.getFileFromIS;

public class SceneControllerParserTest extends TestCase {


    private static final String CONF_FILE = "conf/SceneConfig.xml";

    //ClassLoader classLoader = getClass().getClassLoader();
    //File confFile = new File(classLoader.getResource(CONF_FILE).getFile());

    //private InputStream is = getClass().getClassLoader().getResourceAsStream(CONF_FILE);
   // private File confFile = getFileFromIS(is);

    static File confFile = new File(CONF_FILE);
    private static Map<Integer, List<SceneSingleAction>> sceneMap;

    public SceneControllerParserTest() throws IOException {
    }


    private Map<Integer, List<SceneSingleAction>> buildSceneMap() {
        SceneControllerParser sceneParser = new SceneControllerParser();
        try {
            sceneParser.parseConfigFile(confFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sceneParser.getSceneMap();
    }

    @Test
    public void testGetScenes() {
        sceneMap = buildSceneMap();
        assertNotNull(sceneMap);
    }

    @Test
    public void testNumberOfScenes() {
        sceneMap = buildSceneMap();
        assertEquals(2,sceneMap.size());

    }

    @Test
    public void testNumberOfActionsPerScenes(){
        sceneMap = buildSceneMap();
        assertEquals(6,sceneMap.get(new Integer(55)).size());
        assertEquals(6,sceneMap.get(new Integer(33)).size());
    }

    @Test
    public void testScene55(){
        sceneMap = buildSceneMap();

        List<SceneSingleAction> sceneActions = sceneMap.get(55);

        assertEquals(new ActionDeviceInteraction(22,"lights","on"),sceneActions.get(0));
        assertEquals(new ActionDeviceInteraction(23,"lights","on"),sceneActions.get(1));
        assertEquals(new ActionSleepSeconds(5),sceneActions.get(2));
        assertEquals(new ActionDeviceInteraction(26,"lights","on"),sceneActions.get(3));
        assertEquals(new ActionDeviceInteraction(23,"lights","off"),sceneActions.get(4));
        assertEquals(new ActionCallScene(33),sceneActions.get(5));
    }

    @Test
    public void testScene33(){
        sceneMap = buildSceneMap();

        List<SceneSingleAction> sceneActions = sceneMap.get(33);
        List<SceneSingleAction> testerScene33 = new LinkedList<>();
        
        testerScene33.add(new ActionDeviceInteraction(12,"lights","on"));
        testerScene33.add(new ActionDeviceInteraction(13,"lights","on"));
        testerScene33.add(new ActionSleepSeconds(3));
        testerScene33.add(new ActionDeviceInteraction(16,"lights","on"));
        testerScene33.add(new ActionCallScene(3));
        testerScene33.add(new ActionDeviceInteraction(13,"lights","off"));

        assertEquals(testerScene33,sceneActions);
    }

}