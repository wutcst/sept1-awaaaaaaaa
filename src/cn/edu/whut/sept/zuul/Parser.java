/**
 * 该类用于获取并解析用户输入
 */

package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;
    private Scanner reader;

    /**
     * 创建一个解析器
     */
    public Parser(){
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 读入用户从控制台输入的命令，并将命令分为两部分：命令和参数（其中参数可能不存在）
     * @return 一个解析后的命令
     */
    public Command getCommand()
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();   
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * 展示玩家输入的完整命令
     */
    public void showCommands(){
        commands.showAll();
    }
}
