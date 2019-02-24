package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019

public class Player
{
    private String name;
    private Checker_Color color;
    private int pointsNeededToWin;

    public Player()
    {
        this(null,null);
    }

    public Player(String name, Checker_Color color)
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

    public void setColor(Checker_Color color)
    {
        this.color = color;
    }

    public Checker_Color getColor()
    {
        return this.color;
    }
}
