package com.badlogic.drop;

/**
 * Created by NurullahSamed on 6.5.2016.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Main extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public boolean ses = true;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        this.setScreen(new MainMenu(this,ses));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
