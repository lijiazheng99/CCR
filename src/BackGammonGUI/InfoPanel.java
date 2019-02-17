
package BackGammonGUI;

import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;



public class InfoPanel extends Pane
{

    private static final long serialVersionUID = 1L;
    private static final int TEXT_AREA_HEIGHT = 40;
    private static final int CHARACTER_WIDTH = 47;
    private static final int FONT_SIZE = 12;

    private final TextArea textArea;

    public InfoPanel()
    {
        textArea = new TextArea();
        textArea.setPrefHeight(TEXT_AREA_HEIGHT);
        textArea.setPrefWidth(CHARACTER_WIDTH);
        textArea.setEditable(false);
        textArea.setFont(Font.font("monospaced", FONT_SIZE));
        textArea.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
    }

    public void addText(String text)
    {
        textArea.appendText("\n:"+text);
    }
}
