package classes;

import java.util.List;

public class    Seance {

    private int id;
    private String name;
    private List<Day> days;


    public Seance(int id, String name,List<Day> days)
    {
        this.id=id;
        this.name=name;
        this.days=days;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<Day> getDays() {
        return days;
    }
}
