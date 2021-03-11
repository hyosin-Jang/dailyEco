package com.example.edit_goal2;

public class GoalData {


        private String goal;
        private int icon;


    public GoalData(String goal, int icon) {
        this.goal = goal;
        this.icon = icon;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getIcon()
    {
        return icon;
    }
    public void setIcon(int icon)
    {
        this.icon = icon;
    }
}
