package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//edited by Jiwei Zhang, 2/1/2019
public class Bar {
    private Checker_Color checkerColor;
    private int checkerNumber;

    public Bar()
    {
        this(Checker_Color.EMPTY, 0);
    }
    public Bar (Checker_Color cheCol, int cheNum)
    {
        checkerNumber = cheNum;
        checkerColor = cheCol;
    }

    public void setCheckerColor(Checker_Color color)
    {
        this.checkerColor = color;
    }

    public void setCheckerNumber(int num)
    {
        this.checkerNumber = num;
    }

    public Checker_Color getColor()
    {
        return this.checkerColor;
    }

    public int getCheckerNumber()
    {
        return this.checkerNumber;
    }

}

