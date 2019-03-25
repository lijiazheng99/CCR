package BackGammon;

public class MoveRecord {
    public int start1;
    public  int start2;
    public  int end1;
    public  int end2;
    public  boolean hit1;
    public  boolean hit2;

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
            return true;//start and end are same, 2 numbers used in same checker
        else if(this.start1 == mr.start2 && this.end1 == mr.end2 && this.start2 == mr.start1 && this.end2 == mr.end1)
            return true;//first move and second are the same
        else
            return false;
    }
}
