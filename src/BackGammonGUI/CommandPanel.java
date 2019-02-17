package BackGammonGUI;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


import java.util.LinkedList;



public class CommandPanel extends Pane
{

    private static final long serialVersionUID = 1L;
    private static final int FONT_SIZE = 14;

    private final TextField commandField;
    private final LinkedList<String> commandBuffer;

    public CommandPanel()
    {
        commandField = new TextField();
        commandField.setPromptText("Please insert...");

        commandBuffer = new LinkedList<>();

        commandField.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                synchronized (commandBuffer)
                {
                    commandBuffer.add(commandField.getText());
                    commandField.setText("");
                    commandBuffer.notify();
                }
            }
        });

        commandField.setFont(Font.font("Times New Roman", FONT_SIZE));
        BorderPane commandPane = new BorderPane();
        commandPane.setBottom(commandField);
    }

    public String getCommand()
    {
        String command;
        synchronized(commandBuffer)
        {
            while (commandBuffer.isEmpty())
            {
                try
                {
                    commandBuffer.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            command = commandBuffer.pop();
        }
        return command;
    }
}
