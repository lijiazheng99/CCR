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
        if(checkMove(c,start,end))
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

    public boolean checkMove(Checker_Color c, int start, int end)
    {
        /*basic idea:
            1: roll the dice twice
            2: depending on color, the check the moving direction
            3: check whether the target bar satisfies the moving conditions
                1: empty
                2: same color
                3: different color but number is 1
            4: check whether the second target aviable(using same function - "check")
         */
        if(start - end == 0)
            return false;
        else if(start <= 0 || end <= 0 || start >=25 || end >= 25)
            return false;
        else if(bars[start].checkMoveOut(c) && bars[end].checkMoveIn(c))
            return true;
        else
            return false;
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
        for (int i = 24; i > 6 ; i--)
        {
            if((bars[i].getColor() == c))
                return false;
        }
        return true;
    }

    public boolean repeat(MoveRecord mr) {
        for (int i = 0; i < moveList.length; i++)
        {if (mr.equalsTo(moveList[i]))
                return true;
        }
        return false;
    }
    public MoveRecord[] getMoveList(Checker_Color c, int p1, int p2)
    {
        moveList = new MoveRecord[50];
        points1 = p1;
        points2 = p2;
        int count = 0;
        int currHit;
        boolean doubles = (p1 == p2);
        boolean reEnter;

        MoveRecord curr = new MoveRecord();

        if(c == Checker_Color.RED)
            currHit = redHit;
        else
            currHit = whiteHit;

        reEnter = (currHit != 0);

        if(doubles)//double situation
        {
            int number = points1;


        }else {//not a double situation
            if (reEnter) //there is no bear check after the enter, cuz it is impossible
            /* About re-enter from the bar.
            1. check whether the first number can be used to re-enter a checker
                1.1 check whether only 1 checker need to be re-entered
                    1.1.1 use the second number to do a normal move
                1.2 more checkers need to be re-entered
                    1.2.1 check whether the second number can do re=enter
            2. checker whether the second number can be used
                2.1 check whether only 1 checker need to be re-entered
                    2.1.1 use the first number to do a normal move
                2.2 more need to be re-entered, cant do it.
            */ {
                if (bars[25 - points1].checkMoveIn(c))//if points 1 can do Re-Enter
                {
                    if (currHit - 1 == 0)//1 Re-enter & 1 normal move
                    {
                        for (int i = 24; i >= 1; i--)//check points 2 possible movement
                        {
                            if (checkMove(c, i, i - points2)) {
                                curr.setMoveOne(25, 25 - points1, bars[25 - points1].checkKick(c));
                                curr.setMoveTwo(i, i - points2, bars[i - points2].checkKick(c));
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }
                        }
                    } else if (bars[25 - points2].checkMoveIn(c))//need 2 dice to do re-enter and the second number is valid to use
                    {
                        curr.setMoveOne(25, 25 - points1, bars[25 - points1].checkKick(c));
                        curr.setMoveTwo(25, 25 - points2, bars[25 - points2].checkKick(c));
                        if (!repeat(curr))//a new possible move
                        {
                            moveList[count++] = curr;
                            curr = null;
                        }
                    } else ;//need 2 dice to re-enter but only the first can be used.
                } else if (bars[25 - points2].checkMoveIn(c))//if points 2 can do Re-Enter
                {
                    if (currHit - 1 == 0)//1 Re-enter & 1 normal move
                    {
                        for (int i = 24; i >= 1; i--)//check points 1 possible movement
                        {
                            if (checkMove(c, i, i - points1)) {
                                curr.setMoveOne(25, 25 - points2, bars[25 - points2].checkKick(c));
                                curr.setMoveTwo(i, i - points1, bars[i - points1].checkKick(c));
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }
                        }
                    } else ;//need 2 need 2 dice to re-enter but only the second can be used.
                }
            } else if (checkBear(c))
            /*
                1. at least 1 normal move(for loop)
                2. 2 bear off
            */
            {
                for(int i = 6; i >= 1; i--)//1 number do a normal move
                {
                    if(checkMove(c,i,i-points1))//first number do the normal move
                    {
                        for(int j = 6; j >= 1; j--)
                        {
                            bars[i].moveOut();//assume move out
                            if(checkMove(c,i,i-points2))//second number do normal move
                            {
                                curr.setMoveOne(i,i-points1,bars[i-points1].checkKick(c));
                                curr.setMoveTwo(j,j-points2,bars[j-points2].checkKick(c));
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }else if(bars[j].checkMoveOut(c) && j <= points2)//second number do bear off
                            {
                                curr.setMoveOne(i,i-points1,bars[i-points1].checkKick(c));
                                curr.setMoveTwo(j,0,false);
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }
                            bars[i].moveIn(c);//redo
                        }
                    }else if(checkMove(c,i,i-points2))//second number do the normal move & first do bear off
                    {
                        for(int j = 6; j >= 1; j--)
                        {
                            bars[i].moveOut();//assume move out
                            if(bars[j].checkMoveOut(c) && j <= points1)//first number to bear off
                            {
                                curr.setMoveOne(i,i-points2,bars[i-points2].checkKick(c));
                                curr.setMoveTwo(j,0,false);
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }
                        }
                    }
                }

                for(int i = points1; i >= 1; i--)
                {
                    if(bars[i].checkMoveOut(c)){
                        bars[i].moveOut();
                        for(int j = points2; j>= 1; j--)
                        {
                            if(bars[j].checkMoveOut(c))
                            {
                                curr.setMoveOne(i,0,false);
                                curr.setMoveTwo(j,0,false);
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
                                }
                            }
                        }
                        bars[i].moveIn(c);
                    }

                }
            }else//normal movement
            {
                for(int i = 24; i >= 1; i--)
                {
                    if(checkMove(c,i,i-points1))
                    {
                        bars[i].moveOut();
                        for(int j = 24; j >= 1; j--)
                        {
                            if(checkMove(c,j,j-points2))
                            {
                                curr.setMoveOne(i,i-points1,bars[i-points1].checkKick(c));
                                curr.setMoveTwo(j,j-points2,bars[j-points2].checkKick(c));
                                if (!repeat(curr))//a new possible move
                                {
                                    moveList[count++] = curr;
                                    curr = null;
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

