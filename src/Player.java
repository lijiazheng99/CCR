import java.util.Scanner;

public class Player {
    private String name;
    private Color color;
    private int pointsNeededToWin;

    public Player()
    {
        this(null,null);
    }

    public Player(String name, Color color)
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

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
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
