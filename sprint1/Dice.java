package sprint1;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
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
