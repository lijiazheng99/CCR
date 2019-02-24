package BackGammon;
//created by Jiwei Zhang, 1/1/2019
//REfine by Jiazheng 2/2/2019
public enum Checker_Color
{
    RED(1), WHITE(-1), EMPTY(0);

    final int moveDir;

    Checker_Color(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
