package Achievements;

abstract public class Achievement {
    protected String name;
    protected String description;

    abstract public boolean isCompleted();

    abstract public void setCompleted(boolean completed);

    protected boolean completed = false;

}
