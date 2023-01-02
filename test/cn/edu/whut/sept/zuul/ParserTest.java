package cn.edu.whut.sept.zuul;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void getCommand() {
        String s="go east";
        ByteArrayInputStream strIn=new ByteArrayInputStream(s.getBytes());
        System.setIn(strIn);
        try{
            Command cmd=new Parser().getCommand();
            if(cmd.getCommandWord().equals("go")&&cmd.getSecondWord().equals("east")){
                System.out.println("getCommand Accepted");
            }else{
                System.out.println("getCommand Error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}