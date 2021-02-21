package classes;

import java.util.List;

public class Seance {

    private int mId;
    private String mName;
    private List<Exercice> mExos;
    private List<Integer> mSets;

    public Seance(int id, String name, List<Exercice> exos, List<Integer> sets)
    {
        this.mId=id;
        this.mName=name;
        this.mExos=exos;
        this.mSets=sets;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }


    public List<Exercice> getmExos() {
        return mExos;
    }

    public List<Integer> getmSets(){
        return mSets;
    }
}
