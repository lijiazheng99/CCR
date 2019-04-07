package BackGammon;
import java.awt.*;
public class Player {
        // Player holds the details for one player

        private int id;
        private String colorName;
        private Checker_Color color;
        private String name;
        private Dice dice;
        private int score;

        public Player(int id, String colorName, Checker_Color color) {
            this.id = id;
            name = "";
            this.colorName = colorName;
            this.color = color;
            dice = new Dice();
            score = 0;
        }

        public Player(Player player) {
            id = player.id;
            colorName = player.colorName;
            color = player.color;
            name = player.name;
            dice = new Dice(player.dice);
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore(){   return score; }

        public int getId() {
            return id;
        }

        public String getColorName() {
            return this.colorName;
        }

        public Checker_Color getColor() {
            return this.color;
        }

        public Dice getDice() { return dice; }

        public void wins(DoubleCube c) { score += c.getCurrentPoints(); }

        public String toString() {
            return name;
        }

}
