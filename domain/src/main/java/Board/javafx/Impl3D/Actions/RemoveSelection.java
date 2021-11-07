package Board.javafx.Impl3D.Actions;

import Board.javafx.Impl3D.Environment3DImpl;
import Board.logic.Environment;
import Board.logic.Piece;
import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class RemoveSelection implements javafx.event.EventHandler<MouseEvent> {

    public final Piece piece;
    public final Environment env;


    public RemoveSelection(AbstractPiece piece, Environment env) {
        this.piece = piece;
        this.env = env;
    }

    @Override
    public void handle(MouseEvent event) {
        if (this.env instanceof Environment3DImpl) {
            Environment3DImpl envImpl = (Environment3DImpl) this.env;
            List<Node> children = envImpl.getChildren();
            children.removeIf(node -> node instanceof Canvas);
        }
    }
}
