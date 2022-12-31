package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
public class Room
{
    private int id;
    private static int cnt=0;
    private int type;
    private String description;
    private HashMap<String, Integer> exits;
    private final ArrayList<Item> Items;
    public Room(String description,int type)
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.Items = new ArrayList<>();
        this.type=type;
        this.id=cnt++;
    }

    public void setExit(String direction, int id)
    {
        exits.put(direction, id);
    }

    public String getShortDescription()
    {
        return description;
    }

    public void addItems(String name,String description,int weight){
        this.Items.add(new Item(name,description,weight));
    }

    public void showItems(){
        System.out.println("Here are the items in current room:");
        for(Item i: Items){
            System.out.println(i.getName()+","+i.getDescription()+","+i.getWeight());
        }
    }

    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public int getExit(String direction)
    {
        return exits.get(direction);
    }

    public int getId(){
        return this.id;
    }
    public int getType(){
        return this.type;
    }
}


