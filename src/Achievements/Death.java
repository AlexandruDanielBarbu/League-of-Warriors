package Achievements;

public class Death extends Achievement {

    public Death() {
        name = "Like a puppet on a string";
        description = "Die and immediately play another game.";
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
