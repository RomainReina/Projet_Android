package classes;

public class Exercice {

    private int id;
    private String name;
    private String video;

    private int weight;
    private int sets;
    private String unit;


    public Exercice(int id, String name, String video){
        this.id=id;
        this.name=name;
        this.video=video;
    }

    public Exercice(int id, int weight, int sets, String unit){
        this.id=id;
        this.weight=weight;
        this.sets=sets;
        this.unit=unit;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVideo() {
        return video;
    }
}
