/**
 * 该类用于记录玩家的可用命令集
 * 通过调用该类的方法，可以进行用户输入的检查，也可以查看命令集的内容
 */
package cn.edu.whut.sept.zuul;

public class CommandWords
{
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "back", "take", "drop", "eat-cookie", "items"
    };

    public CommandWords(){
        // nothing to do at the moment...
    }

    /**
     *
     * @param aString 经过解析的用户输入的前一部分
     * @return 检查用户输入是否时一个命令集中的命令，若是则返回true，否则返回false
     */
    public boolean isCommand(String aString){
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString))
                return true;
        }
        return false;
    }

    /**
     * 依次输出命令集中的命令
     */
    public void showAll(){
        for(String command: validCommands){
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
