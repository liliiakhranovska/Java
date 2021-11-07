package Board.javafx.Impl3D.Actions;

import Board.logic.Environment;
import Board.logic.Game;
import Board.logic.Piece;
import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class TakePiece implements javafx.event.EventHandler<MouseEvent> {

    private final int targetX;
    private final int targetY;
    public String color;
    public Piece logicPiece;
    public Environment env;


    public TakePiece(AbstractPiece logicPiece, int targetX, int targetY, Environment env) {
        this.logicPiece = logicPiece;
        this.targetX= targetX;
        this.targetY = targetY;
        this.env = env;
    }

    @Override
    public void handle(MouseEvent event) {
        int selectedPieceX = Game.getInstance().getCoordinatesOfSelectedPiece().getX();
        int selectedPieceY = Game.getInstance().getCoordinatesOfSelectedPiece().getY();
        Piece selectedPiece = Game.getInstance().getPiecesOnBoard().getPiece(selectedPieceX, selectedPieceY);
        Game.getInstance().getPiecesOnBoard().getPiece(targetX, targetY).getImpl(env).removeIt(targetX, targetY);
        Game.getInstance().getPiecesOnBoard().setPiece(targetX, targetY, null);
        Game.getInstance().getPiecesOnBoard().setPiece(targetX, targetY, selectedPiece);
        Game.getInstance().getPiecesOnBoard().getPiece(targetX, targetY).placeIt(targetX, targetY);
        Game.getInstance().getPiecesOnBoard().setPiece(selectedPieceX, selectedPieceY, null);
        Game.getInstance().switchSide();
        Canvas selectionOfPiece = Game.getInstance().getPiecesOnBoard().getPiece(targetX, targetY).getImpl(env).getSelection();
        selectionOfPiece.setOpacity(0);
        Game.getInstance().setCoordinatesOfSelectedPiece(null);
        Game.getInstance().handleChanges();
    }
}
