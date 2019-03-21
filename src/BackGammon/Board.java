package BackGammon;

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
            bars[end].moveIn(c);
            return true;
        }
        else
        {
            //Unvaild movement - append
            return false;
        }
    }

    public void game()
    {
        int round = 0;
        while(!playerOne.getStatus() && !playerTwo.getStatus())//When both not win
        {
            if(round %2 == 0)
                rounds(Checker_Color.RED);
            else
                rounds(Checker_Color.WHITE);
            round++;
        }
    }

    public void rounds(Checker_Color c)
    {
        int num1 = 0;
        int num2 = 0;
    }
}

