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
    private final ArrayList<Item> items;
    public Room(String description,int type)
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
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

    public void addItem(Item item){
        this.items.add(item);
    }
    public void addItem(String name,String description,int weight){
        this.items.add(new Item(name,description,weight));
    }
    public Item delItem(int id){
        if(this.items.size()==0||id<0||id>=this.items.size()){
            return null;
        }
        Item item=items.get(id);
        items.remove(id);
        return item;
    }

    public void showItems(){
        if(items.size()==0){
            System.out.println("There is nothing in current room!");
            return;
        }
        System.out.println("Here are the items in current room:");
        int i=0;
        for(Item item: items){
            System.out.println(i+"."+item.getName()+","+item.getDescription()+","+item.getWeight());
            i++;
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
        int exit;
        try{
            exit=exits.get(direction);
        }catch (Exception e){
            exit = -1;
        }
        return exit;
    }

    public int getId(){
        return this.id;
    }
    public int getType(){
        return this.type;
    }
}


