/**
 * 该类用于记录一个物品
 * 一个物品具有三个属性：物品名称，物品描述，物品重量
 */
package cn.edu.whut.sept.zuul;

public class Item {
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

    /**
     * 创建一个物品
     */
    public Item(String name, String description, int weight){
        this.name=name;
        this.description=description;
        this.weight=weight;
    }
}
