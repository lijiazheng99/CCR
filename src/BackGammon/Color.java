package BackGammon;
//created by Jiwei Zhang, 1/1/2019
public enum Color {
    Red(2), White(1), Empty(0);

    final int moveDir;

    Color(int moveDir)
    {
        this.moveDir = moveDir;
    }
}
