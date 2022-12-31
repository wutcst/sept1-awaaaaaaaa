/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import java.util.*;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Deque<Room> path;
    private HashMap<Integer,Room> idRoomMap;
    private int roomNum=0;
    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game()
    {
        parser = new Parser();
        path = new ArrayDeque<>();
        idRoomMap = new HashMap<>();
        createRooms();
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms()
    {

        Room outside, theater, pub, lab, office, magic;

        // create the rooms
        outside = new Room("outside the main entrance of the university",0);
        theater = new Room("in a lecture theater",0);
        pub = new Room("in the campus pub",0);
        lab = new Room("in a computing lab",0);
        office = new Room("in the computing admin office",0);
        magic = new Room("in a magic room!You will be transport to a random room!", 1);

        idRoomMap.put(outside.getId(), outside);
        idRoomMap.put(theater.getId(), theater);
        idRoomMap.put(pub.getId(), pub);
        idRoomMap.put(lab.getId(), lab);
        idRoomMap.put(outside.getId(), office);
        idRoomMap.put(magic.getId(), magic);
        roomNum=6;
        // initialise room exits
        outside.setExit("east", theater.getId());
        outside.setExit("south", lab.getId());
        outside.setExit("west", pub.getId());
        outside.setExit("north",magic.getId());
        outside.addItems("apple","an apple", 50);

        theater.setExit("west", outside.getId());

        pub.setExit("east", outside.getId());

        lab.setExit("north", outside.getId());
        lab.setExit("east", office.getId());

        office.setExit("west", lab.getId());

        magic.setExit("south",outside.getId());
        currentRoom = outside;  // start game outside
        path.addLast(currentRoom);
    }

    /**
     * todo:文件存地图/读取地图
     */
    private void addRoom(String description){

    }
    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("back")){
            back();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        int nextId = currentRoom.getExit(direction);
        Room nextRoom = idRoomMap.get(nextId);

        if (nextRoom == null) {
            System.out.println("There is no way!");
        }
        else {
            if (nextRoom.getType() == 1) {
                System.out.println("You are "+nextRoom.getShortDescription());
                Random rdm=new Random();
                nextRoom = idRoomMap.get(rdm.nextInt(this.roomNum));

            }
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        path.addLast(currentRoom);
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void look(){
        System.out.println(currentRoom.getLongDescription());
        currentRoom.showItems();
    }

    private void back(){
        if(path.size()==1){
            System.out.println("You are already at the start!");
            return;
        }
        path.removeLast();
        currentRoom= path.getLast();
        System.out.println(currentRoom.getLongDescription());
    }
}