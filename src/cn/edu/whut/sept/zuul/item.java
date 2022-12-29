package cn.edu.whut.sept.zuul;

public class item {
    private String name;

    private String description;

    private int weight;

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getWeight(){
        return weight;
    }

    public item(String name, String description, int weight){
        this.name=name;
        this.description=description;
        this.weight=weight;
    }
}
