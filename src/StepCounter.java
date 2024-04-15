public class StepCounter {
    public static int STEPS = 0;

    private final int stepsBefore;

    public StepCounter() {
        this.stepsBefore = STEPS;
        STEPS = 0;
    }

    public int endCount() {
        int steps = STEPS;
        STEPS = stepsBefore;
        return steps;
    }

    public static void step() {
        STEPS++;
    }
}
