package BackGammon;

//import com.sun.org.apache.regexp.internal.REDebugCompiler;

//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Board {

    //public Bar[] bars;
    public Bar[] bars = new Bar[25];
    public Player playerOne;
    public Player playerTwo;
    public int points1,points2;
    private int redHit = 0;
    private int whiteHit = 0;
    private int redBear = 0;
    private int whiteBear = 0;
    private MoveRecord[] moveList;
    //kicking numbers

    public Board()
    {
        int i = 0;
        while(i != 25)
        {
            bars[i] = new Bar();
            i++;
        }
        //"0" IS NOT FOR USE
    }

    public void setUp() //initializing the board bars
    {
        for(int i = 1; i <= 24; i++)
        {
            if(i == 1 || i == 12 || i == 17 || i == 19 )
                bars[i].setCheckerColor(Checker_Color.RED);
            else if(i == 24 || i == 13 || i == 8 || i == 6 )
                bars[i].setCheckerColor(Checker_Color.WHITE);
            else
                bars[i].setCheckerColor(Checker_Color.EMPTY);

            if(i == 6 || i == 12 || i == 13 || i == 19 )
                bars[i].setCheckerNumber(5);
            else if(i == 1 || i == 24)
                bars[i].setCheckerNumber(2);
            else if(i == 8 || i == 17)
                bars[i].setCheckerNumber(3);
            else
                bars[i].setCheckerNumber(0);
        }
    }

    public boolean move(Checker_Color c, int start, int end)
    {
        //MOVE FROM A TO B WITHOUT ANY RULE:
        if(checkMove(c,start,end-start))
        {
            bars[start].moveOut();

            if(bars[end].checkKick(c))
            {
                bars[end].kick(c);
                if(c == Checker_Color.RED)
                    whiteHit++;
                else
                    redHit++;
            }
            else
                bars[end].moveIn(c);
            return true;
        }
        else
        {
            //Unvaild movement - append
            return false;
        }
    }

    public int getRedHit()
    {
        return redHit;
    }

    public int getWhiteHit()
    {
        return whiteHit;
    }

    public int getRedBear()
    {
        return redBear;
    }

    public int getWhiteBear()
    {
        return whiteBear;
    }

    public boolean checkBear(Checker_Color c) {
        if(c == Checker_Color.RED)
        {
            for(int i = 1; i <= 18; i++)
                if(bars[i].getColor() == c) return false;
        }else
        {
            for(int i = 24; i >= 7; i--)
                if(bars[i].getColor() == c) return false;
        }

        return true;
    }

    public boolean checkReEnter(Checker_Color c, int points)
    {
        if(c == Checker_Color.RED)
            return redHit != 0;
        else
            return whiteHit != 0;
    }

    public void reEnter(Checker_Color c, int points)
    {
        if(c == Checker_Color.RED)
            bars[0+points].moveIn(c);
        else if(c == Checker_Color.WHITE)
            bars[25-points].moveIn(c);
        else;
    }

    public void reEnterUndo(Checker_Color c, int points)
    {
        if(c == Checker_Color.RED)
            bars[0+points].moveOut();
        else if(c == Checker_Color.WHITE)
            bars[25-points].moveOut();
        else;
    }

    public boolean checkBearOff(Checker_Color c, int points)
    {
        if(c == Checker_Color.RED)
            return bars[25-points].checkMoveOut(c);
        else
            return bars[points].checkMoveOut(c);
    }

    public boolean checkMove(Checker_Color c, int start, int points)
    {
        if(points == 0)
            return false;
        else if(start <= 0 || start >=25)
            return false;

        if(c == Checker_Color.RED){
            if(bars[start].checkMoveOut(c) && bars[start+points].checkMoveIn(c))
                return true;
            else return false;
        }
        else if(c == Checker_Color.WHITE)
        {
            if(bars[start].checkMoveOut(c) && bars[start-points].checkMoveIn(c))
                return true;
            else return false;
        }
        else return false;
    }

    public boolean checkHit(Checker_Color c, int index)
    {
        if(c == Checker_Color.RED)
            return bars[25-index].checkKick(c);
        else if(c == Checker_Color.WHITE)
            return bars[index].checkKick(c);
        return false;
    }

    public boolean checkRepeat(MoveRecord mr) {
        for (int i = 0; i < moveList.length; i++)
        {if (mr.equalsTo(moveList[i]))
                return true;
        }
        return false;
    }
    public MoveRecord[] getMoveList(Checker_Color c, int p1, int p2)
    {
        moveList = new MoveRecord[50];
        boolean reEnter;
        boolean doubles;
        boolean exist;
        points1 = p1;
        points2 = p2;

        int count = 0;

        int currHit;
        if(c == Checker_Color.RED)
            currHit = redHit;
        else
            currHit = whiteHit;

        reEnter = (currHit != 0);
        doubles = (p1 == p2);


        MoveRecord curr = new MoveRecord();



        if(doubles)//double situation
        {
            int number = points1;

        }else {//not a double situation
            if(reEnter)
            {
                if(currHit == 1)
                {
                    if(checkReEnter(c,points1))//points1 can be used to re-enter
                    {
                        //calculate points2 normal move
                        //assume the Re-enter
                        reEnter(c,points1);
                        exist = false;
                        for(int i = 24; i >= 1; i--)
                        {
                            if(checkMove(c,i,points2))
                            {
                                exist = true;
                                curr.setMoveOne(25,25-points1, checkHit(c,25-points1));
                                curr.setMoveTwo(i, i-points2,checkHit(c,i-points2));
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }
                        }
                        if(!exist)//second move doesn't exist
                        {
                            curr.setMoveOne(25,25-points1, checkHit(c,25-points1));
                            curr.setMoveTwo(-1,-1,false);
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                        reEnterUndo(c,points1);
                        //Undo the assume
                    }

                    if(checkReEnter(c,points2))
                    {
                        //calculate points2 normal move
                        //assume the Re-enter
                        reEnter(c,points2);
                        exist = false;
                        for(int i = 24; i >= 1; i--)
                        {
                            if(checkMove(c,i,points1))
                            {
                                exist = true;
                                curr.setMoveOne(25,25-points2, checkHit(c,25-points2));
                                curr.setMoveTwo(i, i-points1,checkHit(c,i-points1));
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }
                        }
                        if(!exist)//second move doesn't exist
                        {
                            curr.setMoveOne(25,25-points2, checkHit(c,25-points2));
                            curr.setMoveTwo(-1,-1,false);
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                        reEnterUndo(c,points2);
                        //Undo the assume
                    }
                }else if(currHit > 1)
                {
                    if(checkReEnter(c,points1))
                    {
                        if(checkReEnter(c,points2))
                        {
                            curr.setMoveOne(25,25-points1, checkHit(c,25-points1));
                            curr.setMoveTwo(25,25-points2, checkHit(c,25-points2));
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }else
                        {
                            curr.setMoveOne(25,25-points1, checkHit(c,25-points1));
                            curr.setMoveTwo(-1,-1, false);
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                    }else if(checkReEnter(c,points2))
                    {
                        curr.setMoveOne(25,25-points2, checkHit(c,25-points2));
                        curr.setMoveTwo(-1,-1, false);
                        if(!checkRepeat(curr)){
                            moveList[count++] = curr;
                            curr = new MoveRecord();
                        }
                    }
                }
            }else if(checkBear(c))
            {
                //number 1 and number 2 bear off choices
                //all the checkers <= points can just bear off
                if(checkBearOff(c,points1) && checkBearOff(c,points2))
                {
                    curr.setMoveOne(points1,0, false);
                    curr.setMoveTwo(points2,0, false);
                    if(!checkRepeat(curr)){
                        moveList[count++] = curr;
                        curr = new MoveRecord();
                    }
                }else if(checkBearOff(c,points1))
                {
                    for(int i = 1; i <= 24; i++)
                    {
                        if(checkMove(c,i,points2))
                        {
                            curr.setMoveOne(points1,0, false);
                            curr.setMoveTwo(i,i-points2,checkHit(c,i-points2));
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                    }
                }else if(checkBearOff(c,points2))
                {
                    for(int i = 1; i <= 24; i++)
                    {
                        if(checkMove(c,i,points1))
                        {
                            curr.setMoveOne(points2,0, false);
                            curr.setMoveTwo(i,i-points1,checkHit(c,i-points1));
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                    }
                }else
                {
                    //a normal move from
                    //normal move
                    for(int i = 1; i <= 24; i++)
                    {
                        //number 1 normal move
                        exist = false;
                        if(checkMove(c,i,points1))
                        {
                            bars[i].moveOut();
                            if(checkBear(c) && checkBearOff(c,points2))
                            {
                                //number 2 bear off
                                curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                                curr.setMoveTwo(points2,0,false);
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }
                            else
                            {
                                for(int j = 1; j <= 24; j++)
                                {
                                    //number 2 normal move
                                    if(checkMove(c,i,points2))
                                    {
                                        exist = true;
                                        curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                                        curr.setMoveTwo(i,i-points2,checkHit(c,i-points2));
                                        if(!checkRepeat(curr)){
                                            moveList[count++] = curr;
                                            curr = new MoveRecord();
                                        }
                                    }
                                }
                                if(!exist)
                                {
                                    curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                                    curr.setMoveTwo(-1,-1,false);
                                    if(!checkRepeat(curr)){
                                        moveList[count++] = curr;
                                        curr = new MoveRecord();
                                    }
                                }
                            }
                            bars[i].moveIn(c);
                        }
                        else if(checkMove(c,i,points2))//number 1 can't move right now
                        {
                            bars[i].moveOut();
                            if(checkBear(c) && checkBearOff(c,points1))
                            {
                                //number 1 bear off
                                curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                                curr.setMoveTwo(points1,0,false);
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }else
                            {
                                for(int j = 1; j <= 24; j++)
                                {
                                    //number 1 normal move
                                    if(checkMove(c,i,points1))
                                    {
                                        exist = true;
                                        curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                                        curr.setMoveTwo(i,i-points1,checkHit(c,i-points1));
                                        if(!checkRepeat(curr)){
                                            moveList[count++] = curr;
                                            curr = new MoveRecord();
                                        }
                                    }
                                }
                                if(!exist)
                                {
                                    curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                                    curr.setMoveTwo(-1,-1,false);
                                    if(!checkRepeat(curr)){
                                        moveList[count++] = curr;
                                        curr = new MoveRecord();
                                    }
                                }
                            }
                            bars[i].moveIn(c);
                        }
                    }
                }



            }else
            {
                //normal move
                for(int i = 1; i <= 24; i++)
                {
                    //number 1 normal move
                    exist = false;
                    if(checkMove(c,i,points1))
                    {
                        bars[i].moveOut();
                        if(checkBear(c) && checkBearOff(c,points2))
                        {
                            //number 2 bear off
                            curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                            curr.setMoveTwo(points2,0,false);
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }
                        else
                        {
                            for(int j = 1; j <= 24; j++)
                            {
                                //number 2 normal move
                                if(checkMove(c,i,points2))
                                {
                                    exist = true;
                                    curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                                    curr.setMoveTwo(i,i-points2,checkHit(c,i-points2));
                                    if(!checkRepeat(curr)){
                                        moveList[count++] = curr;
                                        curr = new MoveRecord();
                                    }
                                }
                            }
                            if(!exist)
                            {
                                curr.setMoveOne(i,i-points1,checkHit(c,i-points1));
                                curr.setMoveTwo(-1,-1,false);
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }
                        }
                        bars[i].moveIn(c);
                    }
                    else if(checkMove(c,i,points2))//number 1 can't move right now
                    {
                        bars[i].moveOut();
                        if(checkBear(c) && checkBearOff(c,points1))
                        {
                            //number 1 bear off
                            curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                            curr.setMoveTwo(points1,0,false);
                            if(!checkRepeat(curr)){
                                moveList[count++] = curr;
                                curr = new MoveRecord();
                            }
                        }else
                        {
                            for(int j = 1; j <= 24; j++)
                            {
                                //number 1 normal move
                                if(checkMove(c,i,points1))
                                {
                                    exist = true;
                                    curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                                    curr.setMoveTwo(i,i-points1,checkHit(c,i-points1));
                                    if(!checkRepeat(curr)){
                                        moveList[count++] = curr;
                                        curr = new MoveRecord();
                                    }
                                }
                            }
                            if(!exist)
                            {
                                curr.setMoveOne(i,i-points2,checkHit(c,i-points2));
                                curr.setMoveTwo(-1,-1,false);
                                if(!checkRepeat(curr)){
                                    moveList[count++] = curr;
                                    curr = new MoveRecord();
                                }
                            }
                        }
                        bars[i].moveIn(c);
                    }
                }
            }
        }
        return moveList;
    }
}

