package ru.lionzxy.damagetweaker.handlers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lionzxy on 6/9/16.
 */
public class TicksHandler {
    private static List<Runnable> tasks = new ArrayList<Runnable>();
    private static boolean serverStarted = false;

    public static void onServerLoaded() {
        serverStarted = true;
        if(!tasks.isEmpty()){
            for(Runnable runnable : tasks)
                runnable.run();
            tasks.clear();
        }
    }

    public static void addTasksAfterSererLoaded(Runnable runnable){
        if(serverStarted)
            runnable.run();
        else tasks.add(runnable);
    }
}
