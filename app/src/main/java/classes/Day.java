package classes;

import java.util.List;

public class Day {

    private int id;
    private String name;
    private List<Exercice> exercises;


    public Day(int id, String name, List<Exercice> exercises){
        this.id=id;
        this.name=name;
        this.exercises=exercises;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<Exercice> getExos() {
        return exercises;
    }

}
