package BackGammon;

public enum CheckerColor {
    RED(1), WHITE(-1), EMPTY(0);

    //Probably don't need
    final int moveDir;

    CheckerColor(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
