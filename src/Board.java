public class Board {

    private Bar[] bars;
    private Player playerOne;
    private Player playerTwo;
    private int points1,points2;
    private Dice diceToRoll;
    private int steps;

    public Board()
    {
        bars = new Bar[25];
        //"0" IS NOT FOR USE
        playerOne = new Player();
        playerTwo = new Player();

    }

    public void setUp()
    {
        for(int i = 1; i <= 24; i++)
        {
            if(i == 1 || i == 12 || i == 17 || i == 19 )
                bars[i].setCheckerColor(Color.Red);
            else if(i == 24 || i == 13 || i == 8 || i == 6 )
                bars[i].setCheckerColor(Color.White);
            else
                bars[i].setCheckerColor(Color.Empty);

            if(i == 6 || i == 12 || i == 13 || i == 19 )
                bars[i].setCheckerNumber(5);
            else if(i == 1 || i == 24)
                bars[i].setCheckerNumber(2);
            else if(i == 8 || i == 17)
                bars[i].setCheckerNumber(3);
            else
                bars[i].setCheckerNumber(0);
        }

        diceToRoll = new Dice();
        steps = 0;

        this.sideDecide();
        if(points1 > points2) {
            playerOne.setColor(Color.Red);
            playerTwo.setColor(Color.White);
        }
        else if(points1 < points2)
        {
            playerOne.setColor(Color.White);
            playerTwo.setColor(Color.Red);
        }
        else this.sideDecide();

        this.pointsNeedToWinForBoth();

        System.out.println(playerOne.getPointsNeeded() + playerTwo.getPointsNeeded());
    }

    public void sideDecide()
    {
        System.out.println("Rolling The Dice To Decide the Color");
        points1 = diceToRoll.roll();
        points2 = diceToRoll.roll();
    }

    public void move()
    {

    }

    public void pointsNeedToWinForBoth()
    {
        int whitePoints = 0;
        int redPoints = 0;

        for(int i = 1; i <= 24; i++) {
            if (bars[i].getColor() == Color.Red)
                redPoints += bars[i].getCheckerNumber() * (25-i);
            else if(bars[i].getColor() == Color.White)
                whitePoints += bars[i].getCheckerNumber() * i;
            else
                System.out.println("Errors in points counting");
        }
        if(playerOne.getColor() == Color.Red) {
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

