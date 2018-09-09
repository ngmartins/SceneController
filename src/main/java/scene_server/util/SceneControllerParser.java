package scene_server.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class SceneControllerParser {

    private static final String XML_ELEMENT_NAME = "Scene";
    public static final String ID = "Id";
    public static final String ACTION = "Action";
    public static final String TYPE = "type";

    //just only one instance is allowed here
    private static Map<Integer, List<SceneSingleAction>> sceneMap;

    public SceneControllerParser() {
        this.sceneMap =  new HashMap<>();
    }


    public Map<Integer, List<SceneSingleAction>> getSceneMap() {
        return sceneMap;
    }

    /*
    Parse the XML config File and generate a scene map
     */
    public void parseConfigFile(String file) throws ParserConfigurationException, IOException, SAXException {

        File inputFile = new File(file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(XML_ELEMENT_NAME);

        for (int i = 0; i < nList.getLength(); i++) {

            List<SceneSingleAction> actionsOfScene = new LinkedList<>();

            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            String sceneId = eElement.getAttribute(ID);
            //System.out.println("\n>>>>>Current Scene: " + nNode.getNodeName() + " " + sceneId);

            NodeList actionsList = eElement.getElementsByTagName(ACTION);

            for (int j = 0; j < actionsList.getLength(); j++) {

                Node nAction = actionsList.item(j);
                Element eAction = (Element) nAction;

                String actionType = eAction.getAttribute(TYPE);
                //System.out.println("Action: = "+ actionType);

                switch (actionType) {

                    case "Sleep":
                        String timeInSeconds = eAction.getElementsByTagName("TimeInSeconds").item(0).getTextContent();
                        ActionSleepSeconds sleep = new ActionSleepSeconds(Integer.valueOf(timeInSeconds));
                        actionsOfScene.add(sleep);
                        //System.out.println("\t\t time to sleep: " + timeInSeconds);
                        break;

                    case "CallScene":
                        String sceneToCall = eAction.getElementsByTagName("Id").item(0).getTextContent();
                        ActionCallScene callScene = new ActionCallScene(Integer.parseInt(sceneToCall));
                        actionsOfScene.add(callScene);
                        //System.out.println("\t\t scene to call: " + sceneToCall);
                        break;

                    case "DeviceInteraction":
                        String deviceType = eAction.getElementsByTagName("DeviceType").item(0).getTextContent();
                        String deviceId = eAction.getElementsByTagName("DeviceId").item(0).getTextContent();
                        String deviceTargetState = eAction.getElementsByTagName("DeviceTargetState").item(0).getTextContent();

                        ActionDeviceInteraction deviceInteraction = new ActionDeviceInteraction(Integer.valueOf(deviceId), deviceType, deviceTargetState);
                        actionsOfScene.add(deviceInteraction);
                        //System.out.println("\t\t" + deviceType +"_"+deviceId+"_"+deviceTargetState);
                        break;
                }
            }
            sceneMap.put(Integer.valueOf(sceneId),actionsOfScene);
        }


    }


}
