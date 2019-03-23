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
    private int[][] list;
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
        else if(bars[start].checkMoveOut(c) && bars[end].checkMoveIn(c))
            return true;
        else
            return false;
    }

    public void game()
    {
        int round = 0;
        while(!playerOne.getStatus() && !playerTwo.getStatus())//When both not win
        {
            if(round %2 == 0)//red turn
                rounds(Checker_Color.RED);
            else//white turn
                rounds(Checker_Color.WHITE);
            round++;
        }
    }

    public void rounds(Checker_Color c) {
        boolean doubles = false;//whether get 2 same dice numbers
        boolean moveFinish = false;//whether this turn finished
        boolean reEnter = false;//re-enter the board from kicking area
        int countOfList;
        int doublesNum = 0;
        int currentHit;

        if(c == Checker_Color.RED)
            currentHit = redHit;
        else
            currentHit = whiteHit;
        if(currentHit != 0)
            reEnter = true;

        points1 = 0;
        points2 = 0;
        //read the number of dice.

        if(points1 == points2)
        {
            doubles = true;
            doublesNum = points1;
        }

        while(!moveFinish)
        {
            list = new int[2][50];//save all the move (0:start,1:end)
            countOfList = 0;
            if(reEnter)
            {
                if(bars[25-points1].checkMoveIn(c))
                    list[1][countOfList++] = 25-points1;
                if(bars[25-points2].checkMoveIn(c))
                    list[1][countOfList++] = 25-points2;
                //list output

                if(countOfList != 0)
                {
                    //moving list is not empty
                    int choice = 0;//ask for a move(A OR B) (0 or 1)
                    if (bars[list[1][choice]].checkKick(c)) {
                        bars[list[1][choice]].kick(c);
                        if (c == Checker_Color.RED)
                            whiteHit++;
                        else
                            redHit++;
                    } else
                        bars[list[1][choice]].moveIn(c);
                    //move into the target bar

                    if (c == Checker_Color.RED)
                        redHit--;
                    else
                        whiteHit--;
                    //reduce the Hit number by 1

                    if (points1 == list[1][choice])
                        points1 = 0;
                    else
                        points2 = 0;
                    //erase the used number
                }
            }else if(checkBear(c))
            {
                if(points2 >= points1)
                {
                    for(int i = 1; i <= points2; i++)
                        if(bars[i].checkMoveOut(c))
                            list[0][countOfList++] = i;
                }
                else
                {
                    for(int i = 1; i <= points1; i++)
                        if(bars[i].checkMoveOut(c))
                            list[0][countOfList++] = i;
                }
                //list output
                if(countOfList != 0)
                {
                    //moving list is not empty
                    int choice = 0;//ask for a move(A,B,C,D,...)

                    bars[list[0][choice]].moveOut();
                    //move out from target bar

                    if (c == Checker_Color.RED)
                        redBear++;
                    else
                        whiteBear++;
                    //increse the Bear Off number by 1

                    if (points1 == list[0][choice])
                        points1 = 0;//use points 1 to do exact move
                    else if(points2 == list[0][choice])
                        points2 = 0;//use points 2 to do exact move
                    else if(list[0][choice] < points1 && list[0][choice] > points2)
                        points1 = 0;//this is a mid number, use the larger one, points1
                    else if(list[0][choice] > points1 && list[0][choice] < points2)
                        points2 = 0;//this is a mid number, use the larger one, points2
                    //choice number is smaller than both:
                    else if(points2 >= points1)//points2 is larger, then use the smaller one.
                        points1 = 0;
                    else//points1 is larger, then use the smaller one.
                        points2 = 0;
                    //erase the used number
                }
            }else//normal round
            {
                for (int i = 1; i <= 24; i++) {
                    if (checkMove(c, i, i + points1)) {
                        list[0][countOfList] = i;
                        list[1][countOfList++] = i + points1;
                    } else if (checkMove(c, i, i + points2)) {
                        list[0][countOfList] = i;
                        list[1][countOfList++] = i + points2;
                    }
                }//output the list

                int choice = 0;//ask for a move(A,B,C,D,...)

                if(countOfList != 0)
                {
                    move(c,list[0][choice],list[1][choice]);

                    if(list[1][choice] - list[0][choice] == points1)
                        points1 = 0;
                    else
                        points2 = 0;
                    //erase the used number
                }
            }
            if(points1 == 0 && points2 == 0 && doubles){
                doubles = false;
                points1 = doublesNum;
                points2 = doublesNum;
            }
            else if(points1 == 0 && points2 == 0 && !doubles)
                moveFinish = true;
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
        for (int i = 24; i > 6 ; i--)
        {
            if((bars[i].getColor() == c))
                return false;
        }
        return true;
    }

    public int toNumber(char a)
    {
        return a - 'A';
    }
}

