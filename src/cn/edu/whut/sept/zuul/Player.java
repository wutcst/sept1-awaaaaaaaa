package cn.edu.whut.sept.zuul;

import java.util.ArrayList;

public class Player {
    private String userName;
    private int nowRoomId;
    private ArrayList<Item> bag;
    private int capacity;
    private int weight;
    public Player(String userName,int nowRoomId,int capacity){
        this.userName=userName;
        this.nowRoomId=nowRoomId;
        this.capacity=capacity;
        this.bag=new ArrayList<>();
        this.weight=0;
    }
    public boolean carryItem(Item item) {
        if(this.weight+item.getWeight()>this.capacity){
            System.out.println("You are unable to carry it!");
            return false;
        }
        this.bag.add(item);
        this.weight += item.getWeight();
        return true;
    }
    public Item dropItem(int itemId){
        if(itemId<0||itemId>=bag.size()){
            return null;
        }
        Item item=bag.get(itemId);
        weight-=item.getWeight();
        bag.remove(itemId);
        return item;
    }
    public void showBag(){
        if(this.bag.size()==0) {
            System.out.println("There is nothing in your bag!");
            return;
        }
        System.out.println("The weight of your bag is "+this.weight);
        System.out.println("These are what in your bag:");
        int i=0;
        for(Item item:this.bag){
            System.out.println(i+"."+item.getName()+"(weight:"+item.getWeight()+")\n"+item.getDescription());
        }
    }
    public boolean eatCookie(){
        for(Item item:this.bag){
            if(item.getName().equals("magic cookie")){
                System.out.print("Your capacity has been enhanced!"+this.capacity+"->");
                this.capacity+=1000;
                System.out.println(this.capacity);
                bag.remove(item);
                return true;
            }
        }
        return false;
    }

}
