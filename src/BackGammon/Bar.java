package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Bar {
    private Color CheckerColor;
    private int CheckerNumber;

    public Bar()
    {
        this(Color.Empty, 0);
    }
    public Bar (Color cheCol, int cheNum)
    {
        this.setCheckerNumber(cheNum);
        this.setCheckerColor(cheCol);
    }

    public void setCheckerColor(Color color)
    {
        this.CheckerColor = color;
    }

    public void setCheckerNumber(int num)
    {
        this.CheckerNumber = num;
    }

    public Color getColor()
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
            this.setCheckerColor(Color.Empty);
    }
}

