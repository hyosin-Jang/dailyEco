package com.example.dailyeco;

public class GoalData {


        private String goal;
        private int icon;
        private int _is_active;


    public GoalData(String goal, int icon, int _is_active) {
        this.goal = goal;
        this.icon = icon;
        this._is_active = _is_active;
    }

    public int get_is_active()
    {
        return _is_active;
    }

    public void set_is_active(int _is_active)
    {
        this._is_active = _is_active;
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
