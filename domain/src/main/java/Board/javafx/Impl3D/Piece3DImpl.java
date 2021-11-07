package Board.javafx.Impl3D;

import Board.fileManagement.ReadPointsByName;
import Board.javafx.*;
import Board.javafx.Impl3D.Actions.*;
import Board.logic.CoordinatesImmutable;
import Board.logic.Environment;
import Board.logic.Game;
import Board.logic.Piece;
import Board.logic.logicpieces.AbstractPiece;
import Board.logic.logicpieces.PhysicalPiece;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.List;

import static Board.logic.Piece.Color.WHITE;

public class Piece3DImpl extends Group implements PhysicalPiece {

    private Piece.Color color;
    private Piece.PieceName name;
    private int[] position;
    private Environment env;
    public Canvas selection;
    public boolean isTaken = false;


    TriangleMesh pieceMesh = new TriangleMesh();
    static ReadPointsByName readPointsByNameInst = new ReadPointsByName("coordinates.txt");
    static ClassLoader cl = Piece3DImpl.class.getClassLoader();
    static Image whiteImg = new Image(cl.getResourceAsStream("white.jpg"));
    static Image blackImg = new Image(cl.getResourceAsStream("brown.jpg"));

    int x;
    int y;

    public final AbstractPiece logicPiece;
    final ChessBoard chessBoard;
    MeshView piece;


    public Piece3DImpl(Piece.PieceName pieceName,
                       Piece.Color pieceColor,
                       final ChessBoard chessBoard,
                       Environment environment,
                       AbstractPiece logicPiece) {
        this.logicPiece = logicPiece;
        this.chessBoard = chessBoard;
        boolean isWhite = pieceColor.equals(WHITE);
        this.color = pieceColor;
        this.name = pieceName;
        this.env = environment;
        switch (pieceName) {
            case QUEEN: {
                addTexCoords("QUEENTEXCOORDS");
                addPoints("QUEENPOINTS");
                addFaces("QUEENFACES");
                break;
            }
            case KING: {
                addTexCoords("KINGTEXCOORDS");
                addPoints("KINGPOINTS");
                addFaces("KINGFACES");
                break;
            }
            case ROOK: {
                addTexCoords("ROOKTEXCOORDS");
                addPoints("ROOKPOINTS");
                addFaces("ROOKFACES");
                break;
            }
            case BISHOP: {
                addTexCoords("BISHOPTEXCOORDS");
                addPoints("BISHOPPOINTS");
                addFaces("BISHOPFACES");
                break;
            }
            case KNIGHT: {
                addTexCoords("KNIGHTTEXCOORDS");
                addPoints("KNIGHTPOINTS");
                addFaces("KNIGHTFACES");
                if (isWhite) {
                    final Rotate rotate = new Rotate(180, 0, 0, 0, Rotate.Z_AXIS);
                    this.getTransforms().add(rotate);
                }
                break;
            }
            case PAWN: {
                addTexCoords("PAWNTEXCOORDS");
                addPoints("PAWNPOINTS");
                addFaces("PAWNFACES");
                break;
            }
        }


        piece = new MeshView(pieceMesh);
        piece.setDrawMode(DrawMode.FILL);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(isWhite ? whiteImg : blackImg);
        piece.setMaterial(material);
        piece.setTranslateX(0);
        piece.setTranslateY(0);
        piece.setTranslateZ(250);
        Rotate rotate = new Rotate(-90, 0, 0, 0, Rotate.X_AXIS);
        piece.getTransforms().add(rotate);

        this.selection = selectPiece();
        this.selection.setOpacity(0);
        getChildren().add(selection);


        FadeTransition pieceSelection = new FadeTransition(Duration.millis(1), selection);
        pieceSelection.setFromValue(0.0);
        pieceSelection.setToValue(1.0);


        piece.setOnMousePressed(event -> {
            // if no piece is selected, select piece if possible - there should be possible moves for selected piece
            if (this.isTaken) {
                return;
            }
            if (Game.getInstance().getCoordinatesOfSelectedPiece() == null &&
                    Game.getInstance().getSide() == this.getColorOfPiece()
            ) {
                final List <CoordinatesImmutable> posMoves = this.logicPiece.getPossibleMoves(Game.getInstance().getPiecesOnBoard());
                if (posMoves.isEmpty()) {
                    return;
                }

                MyHandler<MouseEvent> catchHandler = new CatchPiece(this, selection);
                catchHandler.handle(event);

                // if piece is selected, move if possible
                if (catchHandler.handle(event)) {
                    Group[][] cells = chessBoard.getCells();
                    for (CoordinatesImmutable coord : posMoves) {
                        int x = coord.getX();
                        int y = coord.getY();
                        cells[x][y].addEventHandler(MouseEvent.MOUSE_CLICKED, new MovePiece(logicPiece, x, y, selection));
                    }
                }
            }

            // if piece is selected, take if possible
            if (Game.getInstance().getCoordinatesOfSelectedPiece() != null && Game.getInstance().getSide() != this.getColorOfPiece()) {
                int targetX = this.get_x();
                int targetY = this.get_y();
                final var coordinatesOfSelectedPiece = Game.getInstance().getCoordinatesOfSelectedPiece();
                final Piece selectedPiece = Game.getInstance().getPiecesOnBoard().getPiece(coordinatesOfSelectedPiece.getX(), coordinatesOfSelectedPiece.getY());
                final List<CoordinatesImmutable> posAttacks = selectedPiece.getPossibleAttacks(Game.getInstance().getPiecesOnBoard());

                for (CoordinatesImmutable coord : posAttacks) {
                    int pos_x = coord.getX();
                    int pos_y = coord.getY();

                    if (pos_x == targetX && pos_y == targetY) {
                        final var t = new TakePiece(logicPiece, targetX, targetY, env);
                        t.handle(event);
                    }
                }
            }
        });

        piece.addEventHandler(MouseEvent.MOUSE_ENTERED, new SelectPossibleMoves(logicPiece, piece, env));
        piece.addEventHandler(MouseEvent.MOUSE_EXITED, new RemoveSelection(logicPiece, env));

        getChildren().add(piece);
    }

    public Canvas selectPiece() {
        Canvas selection = new Canvas(200, 200);
        GraphicsContext gc = selection.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(20);
        gc.strokeRoundRect(0, 0, 200, 200, 50, 50);
        selection.setTranslateX(-100);
        selection.setTranslateY(-100);
        selection.setTranslateZ(-101);
        return selection;
    }

    public void setToBoard(char letter, int number) {
        int stepX = letter - 96;
        int stepY = number;
        this.translateXProperty().set(stepX * 200 - 1000);
        this.translateYProperty().set((-stepY + 4) * 200);
    }

    public void addTexCoords(String nameOfPiece) {
        float[] TEXCOORDS = this.readPointsByNameInst.ExtractPointsToFloatArray(nameOfPiece);
        multiplyArrayAndInteger(TEXCOORDS, 10);
        pieceMesh.getTexCoords().addAll(TEXCOORDS);
    }

    public void addFaces(String nameOfPoints) {
        int[] FACES = this.readPointsByNameInst.ExtractPointsToIntArray(nameOfPoints);
        pieceMesh.getFaces().addAll(FACES);
    }

    public void addPoints(String nameOfPiece) {
        float[] POINTS = this.readPointsByNameInst.ExtractPointsToFloatArray(nameOfPiece);
        multiplyArrayAndInteger(POINTS, 10);
        pieceMesh.getPoints().addAll(POINTS);
    }

    @Override
    public Canvas getSelection() {
        return selection;
    }


    public float[] multiplyArrayAndInteger(float[] numbers, int scale) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * scale;
        }
        return numbers;
    }

    public Piece.Color getColorOfPiece() {
        return this.color;
    }

    @Override
    public boolean isMeshViewBlocked(MeshView piece) {
        return this.isTaken;
    }


    public void setPositionOfPiece(int x, int y) {
        int[] pos = {x, y};
        this.position = pos;
    }

    public Piece3DImpl getPiece() {
        return this;
    }


    @Override
    public void placeIt(int x, int y) {
        this.x = x;
        this.y = y;
        char letter = Character.forDigit(x + 10, 18);
        this.setToBoard(letter, y + 1);
        this.setPositionOfPiece(x, y);
    }

    @Override
    public void removeIt(int x, int y) {
        this.isTaken = true;
        Game.getInstance().getPieceBeyondBoard().insertPiecesToBeyondBoardMatrix(this.logicPiece);
        int quantityWhiteTakenPieces = Game.getInstance().getPieceBeyondBoard().getQuantityWhiteTakenPieces();
        int quantityBlackTakenPieces = Game.getInstance().getPieceBeyondBoard().getQuantityBlackTakenPieces();
        int step;
        int number;
        if (this.getColorOfPiece() == WHITE) {
            step = quantityWhiteTakenPieces % 8 == 0 ? 10 : 18 - (quantityWhiteTakenPieces % 8);
            number = quantityWhiteTakenPieces < 9 ? 10 : 11;
        } else {
            step = quantityBlackTakenPieces % 8 == 0 ? 17 : (quantityBlackTakenPieces % 8) + 9;
            number = quantityBlackTakenPieces < 9 ? -1 : -2;
        }
        char letter = Character.forDigit(step, 18);
        this.setToBoard(letter, number);
    }

    //    @Override
    public int get_x() {
        return this.x;
    }

    //    @Override
    public int get_y() {
        return this.y;
    }



}