package utc.hiep.pacmanjavafx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utc.hiep.pacmanjavafx.controller.GameController;
import utc.hiep.pacmanjavafx.lib.FontLib;
import utc.hiep.pacmanjavafx.lib.Global;

public class HUD {
    private final GameController game;

    private long currentScore;
    private int lives;
    private int currentLevel;

    private long highScore;
    private int highLevel;

    public HUD(GameController game) {
        this.game = game;
        this.highScore = game.highScore();
        this.highLevel = game.highLevel();
    }

    public void update() {
        currentScore = game.score();
        lives = game.lives();
        currentLevel = game.gameLevel().levelNum();

        if(currentScore > highScore) {
            highScore = currentScore;
            highLevel = currentLevel;
        }
    }


    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(FontLib.EMULOGIC(Global.TILE_SIZE - 1));

        gc.fillText("SCORE", Global.TILE_SIZE,  Global.TILE_SIZE);
        gc.fillText(String.valueOf(currentScore), Global.TILE_SIZE, 2 * Global.TILE_SIZE);


        gc.fillText("L" + currentLevel, Global.TILE_SIZE * 9, 2 * Global.TILE_SIZE);


        gc.fillText("HIGH SCORE", Global.TILE_SIZE * 17, Global.TILE_SIZE);
        gc.fillText(String.valueOf(highScore), Global.TILE_SIZE * 17, 2 * Global.TILE_SIZE);
        gc.fillText("L" + highLevel, Global.TILE_SIZE * 24 + Global.HALF_TILE_SIZE, 2 * Global.TILE_SIZE);


        int i = 0;
        while(i < lives) {
            gc.drawImage(GameController.rm().getImage("sprite_sheet"), 130, 17, 13, 13, Global.TILE_SIZE * (i * 2 + 2) + Global.HALF_TILE_SIZE, (Global.TILES_Y - 2) * Global.TILE_SIZE + (double) Global.HALF_TILE_SIZE / 2, Global.GAME_SCALE * 13, Global.GAME_SCALE * 13);
            i++;
        }

    }

}
