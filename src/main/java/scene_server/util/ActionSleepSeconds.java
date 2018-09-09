package scene_server.util;

import java.util.Objects;

public class ActionSleepSeconds implements SceneSingleAction {

    int secondsToSleep;

    public ActionSleepSeconds(int secondsToSleep) {
        this.secondsToSleep = secondsToSleep;
    }

    public int getSecondsToSleep() {
        return secondsToSleep;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionSleepSeconds that = (ActionSleepSeconds) o;
        return secondsToSleep == that.secondsToSleep;
    }

    @Override
    public int hashCode() {

        return Objects.hash(secondsToSleep);
    }

    @Override
    public void run() throws Exception {

        System.out.println("# Sleep " + secondsToSleep + " seconds");

        try {
            Thread.sleep(secondsToSleep*1000);
        } catch (InterruptedException e) {
            System.out.println("ERROR: on sleeping action (" + secondsToSleep +" seconds)");
            e.printStackTrace();
            throw new Exception();
        }
    }
}
