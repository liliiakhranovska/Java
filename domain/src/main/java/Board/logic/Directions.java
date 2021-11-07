package Board.logic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Directions {

    static public List<Coordinates> north(int currentX, int currentY) {
        List<Coordinates> northCells = new ArrayList<>();
        for (int y = currentY + 1; y < 8; y++) {
            northCells.add(new Coordinates(currentX, y));
        }
        return northCells;
    }

    static public List<Coordinates> south(int currentX, int currentY) {
        List<Coordinates> southCells = new ArrayList<>();
        for (int y = currentY - 1; y > -1; y = y - 1) {
            southCells.add(new Coordinates(currentX, y));
        }
        return southCells;
    }

    static public List<Coordinates> east(int currentX, int currentY) {
        List<Coordinates> eastCells = new ArrayList<>();
        for (int x = currentX + 1; x < 8; x++) {
            eastCells.add(new Coordinates(x, currentY));
        }
        return eastCells;
    }

    static public List<Coordinates> west(int currentX, int currentY) {
        List<Coordinates> westCells = new ArrayList<>();
        for (int x = currentX - 1; x > -1; x = x - 1) {
            westCells.add(new Coordinates(x, currentY));
        }
        return westCells;
    }

    static public List<Coordinates> northEast(int currentX, int currentY) {
        List<Coordinates> northEastCells = new ArrayList<>();
        for (Integer x = currentX + 1; x < 8; x++) {
            for (Integer y = currentY + 1; y < 8; y++) {
                if (currentX - currentY == x - y) {
                    northEastCells.add(new Coordinates(x, y));
                }
            }
        }
        return northEastCells;
    }

    static public List<Coordinates> southEast(int currentX, int currentY) {
        List<Coordinates> southEastCells = new ArrayList<>();
        for (int x = currentX + 1; x < 8; x++) {
            for (int y = currentY - 1; y > -1; y = y - 1) {
                if (currentX + currentY == x + y) {
                    southEastCells.add(new Coordinates(x, y));
                }
            }
        }
        return southEastCells;
    }

    static public List<Coordinates> southWest(int currentX, int currentY) {
        List<Coordinates> southWestCells = new ArrayList<>();
        for (int x = currentX - 1; x > -1; x = x - 1) {
            for (int y = currentY - 1; y > -1; y = y - 1) {
                if (currentX - currentY == x - y) {
                    southWestCells.add(new Coordinates(x, y));
                }
            }
        }
        return southWestCells;
    }

    static public List<Coordinates> northWest(int currentX, int currentY) {
        List<Coordinates> northWestCells = new ArrayList<>();
        for (int x = currentX - 1; x > -1; x = x - 1) {
            for (int y = currentY + 1; y < 8; y++) {
                if (currentX + currentY == x + y) {
                    northWestCells.add(new Coordinates(x, y));
                }
            }
        }
        return northWestCells;
    }

    static public List<Coordinates> knightPosMoves(int currentX, int currentY) {
        List<Coordinates> knightPosMovesCells = new ArrayList<>();
        for (int x = 0; x < 8;  x++) {
            for (int y = 0; y < 8;  y++) {
                if ((abs(x - currentX) == 1 && abs(y - currentY) == 2) || (abs(x - currentX) == 2 && abs(y - currentY) == 1)) {
                    knightPosMovesCells.add(new Coordinates(x, y));
                }
            }
        }
        return knightPosMovesCells;
    }
}