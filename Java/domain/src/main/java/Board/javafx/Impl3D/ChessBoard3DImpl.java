package Board.javafx.Impl3D;

import Board.javafx.ChessBoard;
import Board.logic.Game;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;


class ChessBoard3DImpl extends Group implements ChessBoard {

    final private Box3DImpl[][] cells = new Box3DImpl[8][8];

    public ChessBoard3DImpl() {
    }


    public void insertCellsToMatrix() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x + y) % 2 != 0) {
                    cells[x][y] = new Box3DImpl("white.jpg");
                } else {
                    cells[x][y] = new Box3DImpl("brown.jpg");
                }
            }
        }
    }

    @Override
    public Box3DImpl[][] getCells() {
        return this.cells;
    }

    public void addToGroup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.getChildren().add(cells[x][y]);
            }
        }

    }

    public void setCellsToBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                cells[x][y].setBox(x * cells[x][y].size, y * cells[x][y].size);
            }
        }
    }

    public void addBoardFrame() {
        final Group boardFrame = new Group();
        final Box leftBox;
        final Box rightBox;
        final Box topBox;
        final Box bottomBox;
        final String[] lettersLeftRight;
        final String[] lettersRightLeft;
        final String[] numbersDownUp;
        final String[] numbersUpDown;

        boardFrame.getChildren().addAll(
                leftBox = new Box(100, 1800, 200),
                rightBox = new Box(100, 1800, 200),
                topBox = new Box(1800, 100, 200),
                bottomBox = new Box(1800, 100, 200));


        boardFrame.setTranslateZ(-10);
        PhongMaterial blackMaterial = new PhongMaterial();
        blackMaterial.setDiffuseColor(Color.web("rgba(61,43,31,1.0)"));
        leftBox.setMaterial(blackMaterial);
        leftBox.translateXProperty().set(750);
        leftBox.translateYProperty().set(-100);
        leftBox.translateZProperty().set(10);

        rightBox.setMaterial(blackMaterial);
        rightBox.translateXProperty().set(-950);
        rightBox.translateYProperty().set(-100);
        rightBox.translateZProperty().set(10);

        topBox.setMaterial(blackMaterial);
        topBox.translateXProperty().set(-100);
        topBox.translateYProperty().set(750);
        topBox.translateZProperty().set(10);

        bottomBox.setMaterial(blackMaterial);
        bottomBox.translateXProperty().set(-100);
        bottomBox.translateYProperty().set(-950);
        bottomBox.translateZProperty().set(10);

        lettersLeftRight = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        lettersRightLeft = new String[]{"h", "g", "f", "e", "d", "c", "b", "a"};
        numbersDownUp = new String[]{"8", "7", "6", "5", "4", "3", "2", "1"};
        numbersUpDown = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};


        this.getChildren().add(prepareLettersOnBoard(lettersLeftRight, -830, 700, -101, 0));
        this.getChildren().add(prepareLettersOnBoard(lettersRightLeft, -870, -1200, -101, 180));
        this.getChildren().add(prepareNumbersOnBoard(numbersDownUp, -970, -870, -101, 0));
        this.getChildren().add(prepareNumbersOnBoard(numbersUpDown, 670, -830, -101, 180));
        this.getChildren().addAll(rightBox, topBox, leftBox, bottomBox);


    }

    private static Canvas prepareLettersOnBoard(String[] letters, int X, int Y, int Z, int rotate) {
        Canvas cLetters = new Canvas(1500, 300);
        GraphicsContext gc = cLetters.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        Font tr = new Font("TimesRoman", 80);
        gc.setFont(tr);
        gc.fillText(prepareString(letters, 10), 0, 77);
        cLetters.setTranslateX(X);
        cLetters.setTranslateY(Y);
        cLetters.setTranslateZ(Z);
        cLetters.rotateProperty().set(rotate);
        return cLetters;
    }

    private Canvas prepareNumbersOnBoard(String[] numbers, int X, int Y, int Z, int rotate) {
        Canvas cNumbers = new Canvas(100, 1500);
        GraphicsContext gc = cNumbers.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        Font tr = new Font("TimesRoman", 80);
        gc.setFont(tr);
        int i = 0;
        for (String number : numbers) {
            gc.fillText(number, 0, 100 + 200 * i);
            i++;
        }
        cNumbers.setTranslateZ(Z);
        cNumbers.setTranslateX(X);
        cNumbers.setTranslateY(Y);
        cNumbers.rotateProperty().set(rotate);
//        this.numbers = Numbers;
        return cNumbers;
    }


    private static String prepareString(String[] letters, int step) {
        String string = new String();
        String space;
        space = new String(new char[step]).replace("\0", " ");
        for (String letter : letters) {
            string = string.concat(letter);
            string = string.concat(space);
        }
        return string;
    }

}
