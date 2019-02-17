package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
import java.lang.String;

public class Player {
    private String name;
    private CheckerColor checkerColor;
    private int pointsNeededToWin;

    public Player()
    {
        this(null,null);
    }

    public Player(String name, CheckerColor checkerColor)
    {
        this.setName(name);
        this.setCheckerColor(checkerColor);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setCheckerColor(CheckerColor checkerColor)
    {
        this.checkerColor = checkerColor;
    }

    public CheckerColor getCheckerColor()
    {
        return this.checkerColor;
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
