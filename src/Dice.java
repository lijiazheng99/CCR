import java.util.Random;

public class Dice {

    private int points;

    Random rand = new Random();

    public int roll ()
    {
        points = 1 + rand.nextInt()%6;
        return points;
    }
}
