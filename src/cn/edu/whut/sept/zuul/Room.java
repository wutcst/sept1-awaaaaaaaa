package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
public class Room
{
    private String description;
    private HashMap<String, Room> exits;

    private final ArrayList<item> items;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<item>();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public void addItems(String name,String description,int weight){
        this.items.add(new item(name,description,weight));
    }

    public void showItems(){
        System.out.println("Here are the items in current room:");
        for(item i:items){
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

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
}


