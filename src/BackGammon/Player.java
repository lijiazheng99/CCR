package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
import java.util.Scanner;
import java.lang.String;

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
        this.setName(name);
        this.setColor(color);
    }

    public void setName(String name)
    {
        this.name = name;
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
