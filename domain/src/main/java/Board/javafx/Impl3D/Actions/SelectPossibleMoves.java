package Board.javafx.Impl3D.Actions;

import Board.javafx.Impl3D.Environment3DImpl;
import Board.logic.*;
import Board.logic.logicpieces.AbstractPiece;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;

import java.util.ArrayList;
import java.util.List;

public class SelectPossibleMoves implements javafx.event.EventHandler<MouseEvent> {

    private final MeshView meshPiece;
    private final Environment env;
    AbstractPiece piece;


    public SelectPossibleMoves(AbstractPiece piece, MeshView meshPiece, Environment env) {
        this.piece = piece;
        this.meshPiece = meshPiece;
        this.env = env;
    }


    @Override
    public void handle(MouseEvent event) {
        int x = piece.get_x();
        int y = piece.get_y();
        if (env instanceof Environment3DImpl) {
            final Environment3DImpl envImpl = (Environment3DImpl) env;
            List<Canvas> select = selectPieces(x, y, getPossibleMoves(x, y, Game.getInstance().getPiecesOnBoard()));
            for (Canvas singleSelection : select) {
                envImpl.getChildren().add(singleSelection);
            }
        }
    }

    public List<Canvas> selectPieces(int initialX, int initialY, final List<CoordinatesImmutable> posMoves) {
        if (piece.getImpl(env).isMeshViewBlocked(meshPiece)) {
            return List.of();
        }
        final List<Canvas> selection = new ArrayList<>();
        for (final CoordinatesImmutable coords : posMoves) {
            Canvas singleSelection = new Canvas(200, 200);
            GraphicsContext gc = singleSelection.getGraphicsContext2D();
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(20);
            gc.strokeRoundRect(0, 0, 200, 200, 50, 50);
            int x = coords.getX();
            int y = coords.getY();
            singleSelection.setTranslateX(-900 + 200 * x);
            singleSelection.setTranslateY(500 - 200 * y);
            singleSelection.setTranslateZ(-101);
            selection.add(singleSelection);
        }
        Canvas singleSelection = new Canvas(200, 200);
        GraphicsContext gc = singleSelection.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(20);
        gc.strokeRoundRect(0, 0, 200, 200, 50, 50);
        singleSelection.setTranslateX(-900 + 200 * initialX);
        singleSelection.setTranslateY(500 - 200 * initialY);
        singleSelection.setTranslateZ(-101);
        selection.add(singleSelection);
        return selection;
    }

    public List<CoordinatesImmutable> getPossibleMoves(int curX, int curY, final PiecesOnBoard piecesOnBoard) {
        return piece.getPossibleMoves(piecesOnBoard);
    }
}





