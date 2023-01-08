/**
 * 该类用于存储地图上一个房间的信息。
 */
package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
public class Room
{
    private final int id;
    private static int cnt=0;
    private final int type;
    private final String description;
    private final HashMap<String, Integer> exits;
    private final ArrayList<Item> items;

    /**
     * 创建一个房间
     */
    public Room(String description,int type)
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.type=type;
        this.id=cnt++;
    }

    /**
     * 为该房间添加一个出口
     * @param direction 方向
     * @param id 去往房间的编号
     */
    public void setExit(String direction, int id){
        exits.put(direction, id);
    }

    /**
     * 在当前房间内添加一个物品，在玩家成功执行丢弃物品操作时被调用。
     * @param item 要添加的物品
     */
    public void addItem(Item item){
        this.items.add(item);
    }

    /**
     * 在当前房间内添加一个物品，在载入地图时需要直接给当前房间添加一个新物品时调用。
     * @param name 物品名称
     * @param description 物品介绍
     * @param weight 物品重量
     */
    public void addItem(String name,String description,int weight){
        this.items.add(new Item(name,description,weight));
    }

    /**
     * 从当前房间中移除指定编号的物品。
     * @param id 物品编号
     * @return 若没有该编号的物品，则返回null，否则返回该物品。
     */
    public Item delItem(int id){
        if(this.items.size()==0||id<0||id>=this.items.size()){
            return null;
        }
        Item item=items.get(id);
        items.remove(id);
        return item;
    }

    /**
     * 展示当前房间内的物品信息
     */
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

    /**
     * 获取该房间的介绍
     * @return 返回介绍(description)
     */
    public String getShortDescription(){
        return this.description;
    }

    /**
     * 获取该房间的完整介绍
     * @return 房间的完整介绍，包括介绍(description)和房间的出口
     */
    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * 获取房间的出口信息
     * @return 房间的出口信息
     */
    private String getExitString(){
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    /**
     * 查询获取在当前房间的某个出口方向的房间的编号
     * @param direction 出口方向
     * @return 出口房间的编号，若该方向上没有出口则返回-1
     */
    public int getExit(String direction){
        int exit;
        try{
            exit=exits.get(direction);
        }catch (Exception e){
            exit = -1;
        }
        return exit;
    }

    /**
     * 获取房间编号
     * @return 房间编号
     */
    public int getId(){
        return this.id;
    }

    /**
     * 获取房间的类型
     * @return 房间的类型，若房间是一个传送房间，则返回值为1，否则为0
     */
    public int getType(){
        return this.type;
    }
}


