package BackGammon;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static BackGammon.BackGammon.TILE_SIZE;

public class Checker_vis extends StackPane
{

    private Checker_Color type;

    private double mouseX, mouseY;
    private double oldX, oldY;



    public Checker_Color getType()
    {
        return type;
    }

    public Checker_vis (Checker_Color type, int x, int y)
    {
        this.type = type;

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        Ellipse bg = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        bg.setFill(type == Checker_Color.RED ? Color.rgb(27,27,27) : Color.rgb(103,104,108));

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03 );

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2 );
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 + TILE_SIZE * 0.07);

        Ellipse ellipse = new Ellipse(TILE_SIZE * 0.3125, TILE_SIZE * 0.26);
        ellipse.setFill(type == Checker_Color.WHITE ? Color.rgb(27,27,27) : Color.rgb(103,104,108));

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(TILE_SIZE * 0.03 );

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2 );
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * 0.26 * 2) / 2 );

        getChildren().addAll(ellipse);

        setOnMousePressed( e -> {mouseX = e.getSceneX(); mouseY = e.getSceneY();});

        setOnMouseDragged(e ->{ relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);});

    }
}
