package scene_server.util;

public interface SceneSingleAction {

    boolean equals(Object o);
    int hashCode();

    void run() throws Exception;
}
