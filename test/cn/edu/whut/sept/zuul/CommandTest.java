package cn.edu.whut.sept.zuul;

import org.junit.Test;

public class CommandTest {
    @Test
    public void isUnknown() {
        Command cmd=new Command(null,null);
        if(cmd.isUnknown()){
            System.out.println("isUnknown Accept");
        }else{
            System.out.println("isUnknown Error");
        }
    }

    @Test
    public void hasSecondWord() {
        Command cmd=new Command("help",null);
        if(!cmd.hasSecondWord()){
            System.out.println("hasSecondWord Accept");
        }else{
            System.out.println("hasSecondWord Error");
        }
    }
}