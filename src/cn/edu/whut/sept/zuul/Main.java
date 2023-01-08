/**
 * 该类是项目的主类，调用该类以运行游戏
 */
package cn.edu.whut.sept.zuul;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.login();
    }
}

