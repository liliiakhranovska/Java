package Board.logic;

//@AllArgsConstructor
//@Data

public class Coordinates implements CoordinatesImmutable {
    int x;
    int y;

//    public Coordinates copy() {
//        return new Coordinates(this);
//    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(final Coordinates origin) {
        this.x = origin.x;
        this.y = origin.y;
    }

    public Coordinates stepNorth() {
        final Coordinates res = new Coordinates(this);
        res.y++;
        return res;
    }

//    public static Coordinates stepNorth(final Coordinates origin) {
//        final Coordinates res = new Coordinates(origin);
//        res.y = res.y +1;
//        return res;
//    }

    public Coordinates stepSouth() {
        final Coordinates res = new Coordinates(this);
        res.y = res.y-1;
        return res;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
