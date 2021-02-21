package classes;

import java.util.List;

public class Day {

    private int mId;
    private List<Exercice> mExos;
    private List<Integer> mSets;

    public Day(int id, List<Exercice> exos, List<Integer> sets){
        this.mId=id;
        this.mExos=exos;
        this.mSets=sets;
    }

    public int getmId() {
        return mId;
    }

    public List<Exercice> getmExos() {
        return mExos;
    }

    public List<Integer> getmSets() {
        return mSets;
    }
}
