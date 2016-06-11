package com.badlogic.drop;

/**
 * Created by NurullahSamed on 13.5.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by NurullahSamed on 11.5.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by NurullahSamed on 6.5.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import javax.lang.model.util.AbstractAnnotationValueVisitor7;

public class Hakkinda implements Screen {

    final Main game;

    OrthographicCamera camera;

    private Texture credits;

    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    Rectangle hakkinda;

    boolean ses;


    public Hakkinda(final Main gam,boolean ses) {
        game = gam;
        this.ses = ses;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        backgroundTexture = new Texture("menubackground.png");
        credits = new Texture("credits.png");

        backgroundSprite =new Sprite(backgroundTexture);

        hakkinda = new Rectangle();
        hakkinda.x = 800/2 - credits.getWidth()/2;
        hakkinda.y = -150;
        hakkinda.height = credits.getHeight();
        hakkinda.width = credits.getWidth();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        backgroundSprite.draw(game.batch);
        game.batch.draw(credits, hakkinda.x, hakkinda.y);
        game.batch.end();

        hakkinda.y += 100 * Gdx.graphics.getDeltaTime();
        if(hakkinda.y >=480)
        {
            hakkinda.y = -150;
        }

        if (Gdx.input.isTouched()) {

            game.setScreen(new MainMenu(game,ses));
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }

    @Override
    public void show() {

    }

}
