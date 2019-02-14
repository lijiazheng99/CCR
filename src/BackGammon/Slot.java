package BackGammon;

public class Slot {
    private Color checkerColor;
    private int checkerNumber;

    public Slot()
    {
        this(Color.EMPTY, 0);
    }
    public Slot (Color cheCol, int cheNum)
    {
        checkerNumber = cheNum;
        checkerColor = cheCol;
    }

    public void setCheckerColor(Color color)
    {
        this.checkerColor = color;
    }

    public void setCheckerNumber(int num)
    {
        this.checkerNumber = num;
    }

    public Color getColor()
    {
        return this.checkerColor;
    }

    public int getCheckerNumber()
    {
        return this.checkerNumber;
    }

    public void moveIn(Color color)
    {
        if(color == this.checkerColor)
            this.checkerNumber++;
        else;
    }

    public void moveOut(Color color)
    {
        if(color == this.checkerColor)
            this.checkerNumber--;
        else;
        if(this.checkerNumber == 0)
            this.setCheckerColor(Color.EMPTY);
        else;
    }
}
