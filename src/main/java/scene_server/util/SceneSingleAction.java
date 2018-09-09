package scene_server.util;
/*
interface to be implemneted for each oe of the actions admissible on a scene
 */

public interface SceneSingleAction {

    //just for comparation purposes
    boolean equals(Object o);
    int hashCode();

    void run() throws Exception;
}
