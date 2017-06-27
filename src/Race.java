import java.util.Arrays;
import java.util.Vector;

public class Race {
        // replaced to get synchronized
//    private ArrayList<Stage> stages;
    private Vector<Stage> stages;

    public Race(Stage... stages) {
        // replaced to get synchronized
//        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.stages = new Vector<>(Arrays.asList(stages));
    }

        // replaced to get synchronized
/*
    public ArrayList<Stage> getStages() {
        return stages;
    }
*/
    public Vector<Stage> getStages() {
        return stages;
    }
}
