package Board.logic.logicpieces;

import Board.logic.*;

import java.util.List;
import java.util.Objects;

interface RequiredInner {
    List<CoordinatesImmutable> getPossibleMovesInner(final PiecesOnBoard piecesOnBoard);
}

abstract public class AbstractPiece implements Piece, RequiredInner {
    protected int _x;
    protected int _y;

    protected Color color;
    protected PieceName name;

    protected Environment environment;

    protected PhysicalPiece physicalPiece;

    protected AbstractPiece(final Color color, final PieceName name, final Environment environment) {
        this.color = color;
        this.name = name;
        this.physicalPiece = environment.createPiece(name, color, this);
        this.environment = environment;
//        System.out.println("name: " + name + ", color: " + color + ", logicpiece: " +this);
    }

    @Override
    public PhysicalPiece getImpl(Environment environment) {
        return physicalPiece;
    }

    @Override
    public void placeIt(int x, int y) {
        this._x = x;
        this._y = y;
        this.physicalPiece.placeIt(x,y);
    }


    @Override
    public int get_x() {
        return _x;
    }

    @Override
    public int get_y() {
        return _y;
    }

    @Override
    public PieceName getNameOfPiece() {
        return name;
    }

    @Override
    public Color getColorOfPiece() {
        return color;
    }

    @Override
    public List<CoordinatesImmutable> getPossibleMoves(final PiecesOnBoard piecesOnBoard) {
        if (!Objects.equals(this.color, Game.getInstance().getSide())) {
            System.out.println("Other side turn");
            return List.of();
        }
        return this.getPossibleMovesInner(piecesOnBoard);
    }

    @Override
    public List<CoordinatesImmutable> getPossibleAttacks(final PiecesOnBoard piecesOnBoard) {
        if (!Objects.equals(this.color, Game.getInstance().getSide())) {
            return List.of();
        }
        return this.getPossibleMovesInner(piecesOnBoard);
    }

}
