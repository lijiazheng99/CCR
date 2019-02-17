package BackGammon;

public class Slot {
    private CheckerColor checkerColor;
    private int checkerNumber;

    public Slot()
    {
        this(CheckerColor.EMPTY, 0);
    }
    public Slot (CheckerColor cheCol, int cheNum)
    {
        checkerNumber = cheNum;
        checkerColor = cheCol;
    }

    public void setCheckerColor(CheckerColor checkerColor)
    {
        this.checkerColor = checkerColor;
    }

    public void setCheckerNumber(int num)
    {
        this.checkerNumber = num;
    }

    public CheckerColor getColor()
    {
        return this.checkerColor;
    }

    public int getCheckerNumber()
    {
        return this.checkerNumber;
    }

    public void moveIn(CheckerColor checkerColor)
    {
        if(checkerColor == this.checkerColor)
            this.checkerNumber++;
        else;
    }

    public void moveOut(CheckerColor checkerColor)
    {
        if(checkerColor == this.checkerColor)
            this.checkerNumber--;
        else;
        if(this.checkerNumber == 0)
            this.setCheckerColor(CheckerColor.EMPTY);
        else;
    }
}
