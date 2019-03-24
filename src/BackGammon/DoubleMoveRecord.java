package BackGammon;

public class DoubleMoveRecord {
    private int start1;
    private int start2;
    private int start3;
    private int start4;
    private int end1;
    private int end2;
    private int end3;
    private int end4;
    private boolean hit1;
    private boolean hit2;
    private boolean hit3;
    private boolean hit4;

    //0 for bear
    //25 for bar
    //-1 for N/A

    public DoubleMoveRecord()
    {
        this.start1 = 0;
        this.start2 = 0;
        this.start3 = 0;
        this.start4 = 0;
        this.end1 = 0;
        this.end2 = 0;
        this.end3 = 0;
        this.end4 = 0;
        this.hit1 = false;
        this.hit2 = false;
        this.hit3 = false;
        this.hit4 = false;
    }

    public void setMoveOne(int a, int b, boolean c)
    {
        this.start1 = a;
        this.end1 = b;
        this.hit1 = c;
    }

    public void setMoveTwo(int a, int b, boolean c)
    {
        this.start2 = a;
        this.end2 = b;
        this.hit2 = c;
    }

    public void setMoveThree(int a, int b, boolean c)
    {
        this.start3 = a;
        this.end3 = b;
        this.hit3 = c;
    }

    public void setMoveFour(int a, int b, boolean c)
    {
        this.start4 = a;
        this.end4 = b;
        this.hit4 = c;
    }

}
