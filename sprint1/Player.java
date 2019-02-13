package sprint1;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
import java.util.Scanner;

public class Player {
    private String name;
    private Checker_Color color;
    private int pointsNeededToWin;

    public Player()
    {
        this(null,null);
    }

    public Player(String name, Checker_Color color)
    {
        this.setName();
        this.setColor(color);
    }

    public void setName()
    {
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
    }

    public String getName()
    {
        return this.name;
    }

    public void setColor(Checker_Color color)
    {
        this.color = color;
    }

    public Checker_Color getColor()
    {
        return this.color;
    }

    public void setPointsNeeded(int num)
    {
        this.pointsNeededToWin = num;
    }

    public int getPointsNeeded()
    {
        return this.pointsNeededToWin;
    }
}
