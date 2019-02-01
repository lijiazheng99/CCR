public class Board {

    private Bar[] bars;
    private Player PlayerOne;
    private Player PlayerTwo;

    public Board()
    {
        bars = new Bar[25];
        //"0" IS NOT FOR USE
        PlayerOne = new Player();
        PlayerTwo = new Player();

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

        System.out.println(whitePoints + " " + redPoints);
    }

}
