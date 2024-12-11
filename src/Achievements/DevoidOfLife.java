package Achievements;

public class DevoidOfLife extends Achievement {
    public DevoidOfLife() {
        name = "Devoid of Life";
        description = "The wasteland had nothing to offer here...";
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
