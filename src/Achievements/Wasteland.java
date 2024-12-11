package Achievements;

public class Wasteland extends Achievement {

    public Wasteland() {
        name = "Wasteland enjoyer";
        description = "Discover the entire map.";
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
