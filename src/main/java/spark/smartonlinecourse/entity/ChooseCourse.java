package spark.smartonlinecourse.entity;

import java.util.ArrayList;

public class ChooseCourse extends Course{
    private ArrayList<Integer> scoreList;
    private ArrayList<Homework> homeworkList;
    private ArrayList<Sign> signList;

    public ArrayList<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Integer> scoreList) {
        this.scoreList = scoreList;
    }

    public ArrayList<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(ArrayList<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }
}
