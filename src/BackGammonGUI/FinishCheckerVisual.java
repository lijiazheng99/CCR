package BackGammonGUI;

import BackGammon.Checker_Color;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static BackGammon.BackGammon.TILE_SIZE;

public class FinishCheckerVisual extends StackPane
{
    private Checker_Color type;

    private double oldX, oldY;

    public Checker_Color getType() {
        return type;
    }

    public FinishCheckerVisual(Checker_Color type, int x, int y)
    {
        this.type = type;

        move(x, y);

        Rectangle bg = new Rectangle(TILE_SIZE*0.48, TILE_SIZE*0.1);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.02);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.48 * 2) / 2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.1 * 2) / 2 + TILE_SIZE * 0.02);

        Rectangle ellipse = new Rectangle(TILE_SIZE * 0.48, TILE_SIZE * 0.1);
        ellipse.setFill(type == Checker_Color.RED
                ? Color.rgb(219,59,37) : Color.rgb(213,213,213));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.02);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.48 * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.1 * 2) / 2);

        getChildren().addAll(bg, ellipse);
        System.out.println("BackGammonGUI.FinishCheckerVisual.FinishCheckerVisual(): Checker images assign Successful.");
    }

    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }
}
