package classes;

public class Exercice {

    private int mId;
    private String mName;
    private String mUrl;

    public Exercice(int id, String name, String url){
        this.mId=id;
        this.mName=name;
        this.mUrl=url;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmUrl() {
        return mUrl;
    }
}
