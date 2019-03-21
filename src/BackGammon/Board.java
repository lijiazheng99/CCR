package BackGammon;

import com.sun.org.apache.regexp.internal.REDebugCompiler;

//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Board {

    //public Bar[] bars;
    public Bar[] bars = new Bar[25];
    public Player playerOne;
    public Player playerTwo;
    public int points1,points2;
    public Dice diceToRoll;
    public int steps;
    public int red = 0;
    public int white = 0;
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

        playerOne = new Player();
        playerTwo = new Player();
        diceToRoll = new Dice();


    }

    public boolean move(Checker_Color c, int start, int end)
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
        //MOVE FROM A TO B WITHOUT ANY RULE:
        if(bars[start].checkMoveOut(c) && bars[end].checkMoveIn(c))
        {
            bars[start].moveOut();
            if(bars[end].checkKick(c))
            {
                bars[end].kick(c);
                if(c == Checker_Color.RED)
                    white++;
                else
                    red++;
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

    public boolean checkMove(Checker_Color c, int start, int steps)
    {
        if(steps == 0)
            return false;
        else if(bars[start].checkMoveOut(c) && bars[start+steps].checkMoveIn(c))
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
            {
                if(red == 0)
                    rounds(Checker_Color.RED);
                else
                    reEnter(Checker_Color.RED);
            }
            else//white turn
            {
                if(white == 0)
                    rounds(Checker_Color.WHITE);
                else
                    reEnter(Checker_Color.WHITE);
            }
            round++;
        }
    }

    public void reEnter(Checker_Color c)
    {

    }

    public void rounds(Checker_Color c) {
        boolean doubleSame = false;
        boolean moveFinish = false;
        int num = 0;
        int num1 = 0;
        int num2 = 0;
        //reading dice numbers
        if (num1 == num2)
        {
            num = num1;
            doubleSame = true;
        }
        //whether get 2 same number.
        int[] possibleMove = new int[24];
        int possibleNum = 0;
        //list of possible move starting list and its index
        if(doubleSame)//2 numbers are the same
        {
            int count = 0;
            while (!moveFinish) {
                possibleMove[0] = 0;
                for (int i = 1; i <= 24; i++) {
                    if (checkMove(c, i, num))
                        possibleMove[possibleNum++] = i;
                }
                if ((possibleMove[0] == 0))
                    //PASS!
                    break;
                int a, b;//(a,b)
                boolean moveValid = false;
                do {
                    //***ask for a move(a,b)
                    a = 0;
                    b = 0;
                    moveValid = ((a - b) == num);
                    //whether number valid
                    if (moveValid)
                        moveValid = move(c, a, b);
                    //whether move valid
                } while (!moveValid);//loop when not valid

                if (count == 4)
                    moveFinish = true;
            }
        }
        else {//2 numbers are different
            while (!moveFinish) {
                possibleMove[0] = 0;
                //setting the empty status
                for (int i = 1; i <= 24; i++) {
                    if (checkMove(c, i, num1) || checkMove(c, i, num2))
                        possibleMove[possibleNum++] = i;
                }
                //give a possible move starting list
                //***Doing output
                if((possibleMove[0] == 0))
                    //PASS!
                    break;
                int a,b;//(a,b)
                boolean moveValid = false;
                do {
                    //***ask for a move(a,b)
                    a = 0;
                    b = 0;
                    moveValid = ((a-b) == num1 || (a-b) == num2);
                    //whether number valid
                    if(moveValid)
                        moveValid = move(c,a,b);
                        //whether move valid
                }while(!moveValid);//loop when not valid
                //move finish, erase a number
                if(a-b == num1)
                    num1 = 0;
                else
                    num2 = 0;
                //when 2 numver all used
                if((num1 == 0) && (num2 == 0))
                    moveFinish = true;
            }
        }
    }


}

