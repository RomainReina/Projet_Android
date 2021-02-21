package classes;

import java.util.List;

public class Seance {

    private int mId;
    private String mName;
    private List<Day> mDays;


    public Seance(int id, String name,List<Day> days)
    {
        this.mId=id;
        this.mName=name;
        this.mDays=days;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }


    public List<Day> getmDays() {
        return mDays;
    }
}
