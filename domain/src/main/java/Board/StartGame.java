package Board;

import Board.javafx.Application3DImpl;
import Board.logic.Application3D;
import Board.logic.Game;

public class StartGame {

    public static void main(String[] args) {
        Game.createInstance();
        final Application3D app = new Application3DImpl();
        app.main(args);
    }

}
