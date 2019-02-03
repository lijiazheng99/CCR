package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Bar {
    private Checker_Color CheckerColor;
    private int CheckerNumber;

    public Bar()
    {
        this(Checker_Color.EMPTY, 0);
    }
    public Bar (Checker_Color cheCol, int cheNum)
    {
        this.setCheckerNumber(cheNum);
        this.setCheckerColor(cheCol);
    }

    public void setCheckerColor(Checker_Color color)
    {
        this.CheckerColor = color;
    }

    public void setCheckerNumber(int num)
    {
        this.CheckerNumber = num;
    }

    public Checker_Color getColor()
    {
        return this.CheckerColor;
    }

    public int getCheckerNumber()
    {
        return this.CheckerNumber;
    }

    public void moveIn()
    {
        this.CheckerNumber++;
    }

    public void moveOut()
    {
        this.CheckerNumber--;
        if(this.CheckerNumber == 0)
            this.setCheckerColor(Checker_Color.EMPTY);
    }
}

