package cn.edu.whut.sept.zuul;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandWordsTest {

    @Test
    public void isCommand() {
        CommandWords test=new CommandWords();
        if(test.isCommand("go")&&!test.isCommand("wow")){
            System.out.println("isCommand Accepted");
        }else{
            System.out.println("isCommand error");
        }
    }
}