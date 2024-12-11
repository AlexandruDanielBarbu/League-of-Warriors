package Achievements;

public class Oasis extends Achievement {
    public Oasis() {
        name = "The Oasis";
        description = "Discover a sanctuary.";
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
