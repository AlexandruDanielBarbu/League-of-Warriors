package Achievements;

abstract public class Achievement {
    protected String name;
    protected String description;

    abstract public boolean isCompleted();

    abstract public void setCompleted(boolean completed);

    protected boolean completed = false;

    @Override
    public String toString() {
        if (completed)
            return String.format("%s - completed\n-------------------\n%s", name,description);
        return String.format("%s - not completed\n-------------------\n", name);
    }
}
