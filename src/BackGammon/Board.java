package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Board {

    //public Bar[] bars;
    public Bar[] bars = new Bar[25];
    private Player playerOne;
    private Player playerTwo;
    private int points1,points2;
    private Dice diceToRoll;
    private int steps;

    public Board()
    {
        System.out.println("OK3");
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

//        diceToRoll = new Dice();
//        steps = 0;
//
//        this.sideDecide();
//        if(points1 > points2) {
//            playerOne.setCheckerColor(Checker_Color.RED);
//            playerTwo.setCheckerColor(Checker_Color.WHITE);
//        }
//        else if(points1 < points2)
//        {
//            playerOne.setCheckerColor(Checker_Color.WHITE);
//            playerTwo.setCheckerColor(Checker_Color.RED);
//        }
//        else this.sideDecide();
//
//        this.pointsNeedToWinForBoth();
//
//        System.out.println(playerOne.getPointsNeeded() + playerTwo.getPointsNeeded());
    }

    public void sideDecide()
    {
        System.out.println("Rolling The sprint1.Dice To Decide the BackGammon.CheckerColor");
        points1 = diceToRoll.roll();
        points2 = diceToRoll.roll();
    }

    public void move()
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
    }

    public void pointsNeedToWinForBoth()
    {
        int whitePoints = 0;
        int redPoints = 0;

        for(int i = 1; i <= 24; i++) {
            if (bars[i].getColor() == Checker_Color.RED)
                redPoints += bars[i].getCheckerNumber() * (25-i);
            else if(bars[i].getColor() == Checker_Color.WHITE)
                whitePoints += bars[i].getCheckerNumber() * i;
            else
            System.out.println("Errors in points counting");
        }
        if(playerOne.getColor() == Checker_Color.RED) {
            playerOne.setPointsNeeded(redPoints);
            playerTwo.setPointsNeeded(whitePoints);
        }
        else
        {
            playerOne.setPointsNeeded(whitePoints);
            playerTwo.setPointsNeeded(redPoints);
        }

    }

}

