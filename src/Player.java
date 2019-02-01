import java.util.Scanner;

public class Player {
    private String name;
    private Color color;

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
        Scanner scan = new Scanner();
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
}
