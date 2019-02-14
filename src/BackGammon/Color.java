package BackGammon;

public enum Color {
    RED(1), WHITE(-1), EMPTY(0);

    final int moveDir;

    Color(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
