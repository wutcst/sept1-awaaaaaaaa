package cn.edu.whut.sept.zuul;

import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {
    @Test
    public void carryItem() {
        Player player=new Player("awa",0,1000);
        player.carryItem(new Item("apple","an apple",50));
        ArrayList<Item> bag=player.getBag();
        if(bag.get(0).getName().equals("apple")) {
            System.out.println("carryItem Accepted");
        }else{
            System.out.println("carryItem Error");
        }
    }

    //此测试必须在carryItem正确的情况下才有效
    @Test
    public void dropItem() {
        Player player=new Player("awa",0,1000);
        player.carryItem(new Item("apple","an apple",50));
        player.dropItem(0);
        ArrayList<Item> bag=player.getBag();
        if(bag.size()==0) {
            System.out.println("dropItem Accepted");
        }else{
            System.out.println("dropItem Error");
        }
    }

    //此测试必须在carryItem正确的情况下才有效
    @Test
    public void eatCookie() {
        Player player=new Player("awa",0,1000);
        player.carryItem(new Item("magic cookie","a magic cookie",50));
        player.eatCookie();
        ArrayList<Item> bag=player.getBag();
        if(bag.size()==0) {
            System.out.println("eatCookie Accepted");
        }else{
            System.out.println("eatCookie Error");
        }
    }
}