package BackGammon;

public class MoveRecord {
    private int start1;
    private int start2;
    private int end1;
    private int end2;
    private boolean hit1;
    private boolean hit2;

    //0 for bear
    //25 for bar
    //-1 for N/A

    public MoveRecord()
    {
        this.start1 = -1;
        this.start2 = -1;
        this.end1 = -1;
        this.end2 = -1;
        this.hit1 = false;
        this.hit2 = false;
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

    public boolean equalsTo(MoveRecord mr)
    {
        if(this.start1 == mr.start1 && this.end2 == mr.end2 && !this.hit1 && !mr.hit1)
            return true;
        else if(this.start1 == mr.start2 && this.end1 == mr.end2 && this.start2 == mr.start1 && this.end2 == mr.end1)
            return true;
        else
            return false;
    }
}
