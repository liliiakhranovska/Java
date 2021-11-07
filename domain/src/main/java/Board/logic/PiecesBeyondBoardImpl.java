package Board.logic;

import static Board.logic.Piece.Color.WHITE;

public class PiecesBeyondBoardImpl implements PiecesBeyondBoard {

    public Piece[][] takenPieces = new Piece[4][8];
    private Environment environment;
    static int quantityTakenPieces = 0;
    static int quantityWhiteTakenPieces = 0;
    static int quantityBlackTakenPieces = 0;


    public PiecesBeyondBoardImpl(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void insertPiecesToBeyondBoardMatrix(Piece piece) {
        quantityTakenPieces ++;
        if (piece.getColorOfPiece() == WHITE) {
            quantityWhiteTakenPieces ++;
        } else {
            quantityBlackTakenPieces ++;
        }
        System.out.println("TEST: " + quantityTakenPieces);
        if (quantityTakenPieces < 8 ) {
            takenPieces[0][quantityTakenPieces] = piece;
        } if (quantityTakenPieces >= 8 && quantityTakenPieces <16) {
            takenPieces[1][quantityTakenPieces-8] = piece;
        } if (quantityTakenPieces >= 16 && quantityTakenPieces <24) {
            takenPieces[2][quantityTakenPieces-16] = piece;
        } if (quantityTakenPieces >= 24 && quantityTakenPieces <32) {
            takenPieces[3][quantityTakenPieces-24] = piece;
        }
        for (int i = 0; i < 2; i ++) {
            for (int j = 0; j < 8; j++) {
                if (takenPieces[i][j] != null) {
                    System.out.println("taken piece: " + takenPieces[i][j].getColorOfPiece() + " " + takenPieces[i][j].getNameOfPiece());
                }
            }
        }
    }

    @Override
    public Piece[][] getPieces() {
        return takenPieces;
    }

    @Override
    public int getQuantityWhiteTakenPieces() {
        return quantityWhiteTakenPieces;
    }

    @Override
    public int getQuantityBlackTakenPieces() {
        return quantityBlackTakenPieces;
    }

    @Override
    public Piece getPiece(int x, int y) {
        return takenPieces[x][y];
    }

    @Override
    public void insertPiecesToDefaultBoardMatrix() {
        //TODO
    }

    @Override
    public void putPiecesToDefaultBoard() {
        // TODO
    }

}






