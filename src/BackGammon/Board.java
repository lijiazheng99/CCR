package BackGammon;

//import com.sun.org.apache.regexp.internal.REDebugCompiler;

//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Board {

    //public Bar[] bars;
    public Bar[] bars = new Bar[25];
    public Player playerOne;
    public Player playerTwo;
    public int points1, points2;
    private int redHit = 0;
    private int whiteHit = 0;
    int count = 0;
    private int redBear = 0;
    private int whiteBear = 0;
    private MoveRecord[] moveList;
    private DoubleMoveRecord[] doubleMoveList;
    //kicking numbers

    public Board() {
        int i = 0;
        while (i != 25) {
            bars[i] = new Bar();
            i++;
        }
        //"0" IS NOT FOR USE
    }

    public void setUp() //initializing the board bars
    {
        for (int i = 1; i <= 24; i++) {
            if (i == 1 || i == 12 || i == 17 || i == 19)
                bars[i].setCheckerColor(Checker_Color.RED);
            else if (i == 24 || i == 13 || i == 8 || i == 6)
                bars[i].setCheckerColor(Checker_Color.WHITE);
            else
                bars[i].setCheckerColor(Checker_Color.EMPTY);

            if (i == 6 || i == 12 || i == 13 || i == 19)
                bars[i].setCheckerNumber(5);
            else if (i == 1 || i == 24)
                bars[i].setCheckerNumber(2);
            else if (i == 8 || i == 17)
                bars[i].setCheckerNumber(3);
            else
                bars[i].setCheckerNumber(0);
        }
    }

    public boolean move(Checker_Color c, int start, int end) {
        //MOVE FROM A TO B WITHOUT ANY RULE:
        if (checkMove(c, start, end - start)) {
            bars[start].moveOut();

            if (bars[end].checkKick(c)) {
                bars[end].kick(c);
                if (c == Checker_Color.RED)
                    whiteHit++;
                else
                    redHit++;
            } else
                bars[end].moveIn(c);
            return true;
        } else {
            //Unvaild movement - append
            return false;
        }
    }

    public int getRedHit() {
        return redHit;
    }

    public int getWhiteHit() {
        return whiteHit;
    }

    public int getRedBear() {
        return redBear;
    }

    public int getWhiteBear() {
        return whiteBear;
    }

    public void moveIn(Checker_Color c, int num) {
        if (c == Checker_Color.RED)
            bars[25 - num].moveIn(c);
        else
            bars[num].moveIn(c);
    }

    public void moveOut(Checker_Color c, int num) {
        if (c == Checker_Color.RED)
            bars[25 - num].moveOut();
        else
            bars[num].moveOut();
    }

    public boolean checkBear(Checker_Color c) {
        if (c == Checker_Color.RED) {
            for (int i = 1; i <= 18; i++)
                if (bars[i].getColor() == c) return false;
        } else if (c == Checker_Color.WHITE) {
            for (int i = 24; i >= 7; i--)
                if (bars[i].getColor() == c) return false;
        } else ;

        return true;
    }

    public boolean checkReEnter(Checker_Color c, int points) {
        if (c == Checker_Color.RED)
            return redHit != 0;
        else
            return whiteHit != 0;
    }

    public void reEnter(Checker_Color c, int points) {
        if (c == Checker_Color.RED)
            bars[0 + points].moveIn(c);
        else if (c == Checker_Color.WHITE)
            bars[25 - points].moveIn(c);
        else ;
    }

    public void reEnterUndo(Checker_Color c, int points) {
        if (c == Checker_Color.RED)
            bars[0 + points].moveOut();
        else if (c == Checker_Color.WHITE)
            bars[25 - points].moveOut();
        else ;
    }

    public boolean checkBearOff(Checker_Color c, int points) {
        if (c == Checker_Color.RED)
            return bars[25 - points].checkMoveOut(c);
        else
            return bars[points].checkMoveOut(c);
    }

    public boolean checkMove(Checker_Color c, int start, int points) {
        if (points == 0)
            return false;
        else if (start <= 0 || start >= 25)
            return false;

        if (c == Checker_Color.RED) {
            if (25 - start + points >= 25)
                return false;
            if (bars[25 - start].checkMoveOut(c) && bars[25 - start + points].checkMoveIn(c))
                return true;
            else return false;
        } else if (c == Checker_Color.WHITE) {
            if (start - points <= 0)
                return false;
            if (bars[start].checkMoveOut(c) && bars[start - points].checkMoveIn(c))
                return true;
            else return false;
        } else return false;
    }

    public boolean checkHit(Checker_Color c, int index) {
        if (index < 1 || index > 24)
            return false;
        if (c == Checker_Color.RED)
            return bars[25 - index].checkKick(c);
        else if (c == Checker_Color.WHITE)
            return bars[index].checkKick(c);
        return false;
    }

    public boolean checkRepeat(MoveRecord mr) {
        for (int i = 0; i < count; i++) {
            if (moveList[i].equalsTo(mr))
                return true;
        }
        return false;
    }
}




