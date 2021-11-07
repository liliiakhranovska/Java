package Board.logic;

import Board.logic.logicpieces.*;

import static Board.logic.Piece.Color.BLACK;
import static Board.logic.Piece.Color.WHITE;

public class PiecesOnBoard2DImpl implements PiecesOnBoard {

    public Piece[][] pieces = new Piece[8][8];


    private Environment environment;

    public PiecesOnBoard2DImpl(Environment environment) {
        this.environment = environment;
    }


    @Override
    public Piece createPiece (Piece.Color color, Piece.PieceName name) {
        switch (color) {
            case BLACK:
                switch (name) {
                    case QUEEN:
                        return new QueenImpl(environment, BLACK);
                    case KING:
                        return new KingImpl(environment, BLACK);
                    case ROOK:
                        return new RookImpl(environment, BLACK);
                    case BISHOP:
                        return new BishopImpl(environment, BLACK);
                    case KNIGHT:
                        return new KnightImpl(environment, BLACK);
                    case PAWN:
                        return new BlackPawnImpl(environment);
                }
                break;
            case WHITE:
                switch (name) {
                    case QUEEN:
                        return new QueenImpl(environment, WHITE);
                    case KING:
                        return new KingImpl(environment, WHITE);
                    case ROOK:
                        return new RookImpl(environment, WHITE);
                    case BISHOP:
                        return new BishopImpl(environment, WHITE);
                    case KNIGHT:
                        return new KnightImpl(environment, WHITE);
                    case PAWN:
                        return new WhitePawnImpl(environment);
                }
                break;
        }
        return null;
    }

    @Override
    public void insertPiecesToDefaultBoardMatrix() {
        final GameState gamestate =  Game.getInstance().getGameState();
        this.pieces = new Piece[8][8];
        if (gamestate == null) {
            for (int x = 0; x < 8; x++) {
                pieces[x][1] = createPiece(WHITE, Piece.PieceName.PAWN);
                pieces[x][6] = createPiece(BLACK, Piece.PieceName.PAWN);
            }

            pieces[0][0] = createPiece(WHITE, Piece.PieceName.ROOK);
            pieces[7][0] = createPiece(WHITE, Piece.PieceName.ROOK);
            pieces[0][7] = createPiece(BLACK, Piece.PieceName.ROOK);
            pieces[7][7] = createPiece(BLACK, Piece.PieceName.ROOK);

            pieces[1][0] = createPiece(WHITE, Piece.PieceName.KNIGHT);
            pieces[6][0] = createPiece(WHITE, Piece.PieceName.KNIGHT);
            pieces[1][7] = createPiece(BLACK, Piece.PieceName.KNIGHT);
            pieces[6][7] = createPiece(BLACK, Piece.PieceName.KNIGHT);

            pieces[2][0] = createPiece(WHITE, Piece.PieceName.BISHOP);
            pieces[5][0] = createPiece(WHITE, Piece.PieceName.BISHOP);
            pieces[2][7] = createPiece(BLACK, Piece.PieceName.BISHOP);
            pieces[5][7] = createPiece(BLACK, Piece.PieceName.BISHOP);

            pieces[3][0] = createPiece(WHITE, Piece.PieceName.QUEEN);
            pieces[3][7] = createPiece(BLACK, Piece.PieceName.QUEEN);

            pieces[4][0] = createPiece(WHITE, Piece.PieceName.KING);
            pieces[4][7] = createPiece(BLACK, Piece.PieceName.KING);
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    final GameState.PieceSer piece = gamestate.piecesOnBoardSer.piecesSer[i][j];
                    if (piece.color != null) {
                        pieces[i][j] = createPiece(piece.color, piece.name);
                    }
                }
            }
        }
    }

    public Piece.Color getColorOfPiece(int x, int y) {
        final Piece piece = pieces[x][y];
        return piece == null ? null : piece.getColorOfPiece();
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    @Override
    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }

    @Override
    public void setPiece(int x, int y, Piece piece) {
        pieces[x][y] = piece;
    }

    @Override
    public void putPiecesToDefaultBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces[x][y] != null) {
                    pieces[x][y].placeIt(x, y);
                }
            }
        }
    }
}






