
package BackGammonGUI;

import BackGammon.*;

//import java.awt.BorderLayout;
//import javax.swing.*;

import javafx.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class UI {

    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;

    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel commandPanel;

    public UI(Board board)
    {
//        infoPanel = new InfoPanel();
//        commandPanel = new CommandPanel();
//        JFrame frame = new JFrame();
//        boardPanel = new BoardPanel(board);
//        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        frame.setTitle("Backgammon");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        frame.add(boardPanel, BorderLayout.LINE_START);
//        frame.add(infoPanel, BorderLayout.LINE_END);
//        frame.add(commandPanel,BorderLayout.PAGE_END);
//        frame.setResizable(false);
//        frame.setVisible(true);

        infoPanel = new InfoPanel();
        commandPanel = new CommandPanel();
        boardPanel = new BoardPanel(board);
        BorderPane mainPane = new BorderPane();
        mainPane.setPrefSize(FRAME_WIDTH,FRAME_HEIGHT);
        mainPane.setLeft(boardPanel);
        mainPane.setRight(infoPanel);
        mainPane.setBottom(commandPanel);
        mainPane.setVisible(true);

    }

    public String getCommand()
    {
        return commandPanel.getCommand();
    }

    public void display()
    {
        boardPanel.refresh();
    }

    public void displayString(String string)
    {
        infoPanel.addText(string);
    }
}
