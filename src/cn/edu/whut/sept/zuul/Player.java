/**
 * 该类用于存储玩家信息
 * 在游戏开始前，需要在Game类中创建Player类的一个实例用于存储当前玩家的状态
 * Player类的实例的方法将在被解析的命令执行时在Game类中被调用，维护当前的游戏状态
 */

package cn.edu.whut.sept.zuul;

import java.util.ArrayList;

public class Player {
    private String userName;
    private int nowRoomId;
    private ArrayList<Item> bag;
    private int capacity;
    private int weight;

    /**
     * 创建玩家并初始化玩家信息。
     */
    public Player(String userName,int nowRoomId,int capacity){
        this.userName=userName;
        this.nowRoomId=nowRoomId;
        this.capacity=capacity;
        this.bag=new ArrayList<>();
        this.weight=0;
    }

    /**
     * 传入一个物品，检查玩家的负重能力是否足够，若足够则将物品加入背包，否则返回拾取失败的结果
     * @param item 要拾取的物品
     * @return 该物品是否可以被拾取，若可以则返回true，否则返回false
     */
    public boolean carryItem(Item item) {
        if(this.weight+item.getWeight()>this.capacity){
            return false;
        }
        this.bag.add(item);
        this.weight += item.getWeight();
        return true;
    }

    /**
     * 传入玩家要丢弃的物品在背包内的编号（可由items命令查看），若该编号有效，则将该物品移出背包
     * @param itemId 要丢弃的物品在背包内的编号
     * @return 丢弃的物品，若该编号的物品不存在则返回null
     */
    public Item dropItem(int itemId){
        if(itemId<0||itemId>=bag.size()){
            return null;
        }
        Item item=bag.get(itemId);
        weight-=item.getWeight();
        bag.remove(itemId);
        return item;
    }

    /**
     * 展示玩家背包内的物品
     */
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
            i++;
        }
    }

    /**
     * 检查玩家背包内的物品，若有魔法曲奇(magic cookie)，则将其从背包删除，同时增加玩家的负重能力（固定值1000点）
     * @return 若玩家背包内有魔法曲奇，则返回true，否则返回false
     */
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

    public String getUserName(){
        return this.userName;
    }

    public int getNowRoomId(){
        return this.nowRoomId;
    }

    public int getCapacity(){
        return this.capacity;
    }
}
