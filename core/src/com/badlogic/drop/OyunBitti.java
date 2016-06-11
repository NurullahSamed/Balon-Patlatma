package com.badlogic.drop;

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
import com.badlogic.gdx.math.MathUtils;
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

public class OyunBitti implements Screen {

    final Main game;
    private Texture oyunbitti;

    OrthographicCamera camera;


    private Texture backgroundTexture;
    private Sprite backgroundSprite;


    Rectangle ok;
    Rectangle carpi;


    long puan ;
    boolean ses;


    public OyunBitti(final Main gam,long veri,boolean ses) {
        game = gam;
        this.ses = ses;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        backgroundTexture = new Texture("menubackground.png");
        oyunbitti = new Texture(Gdx.files.internal("oyunbitti.png"));

        backgroundSprite =new Sprite(backgroundTexture);

        puan = veri;

        ok = new Rectangle();
        ok.x = 360;
        ok.y = 220;
        ok.width = 82;
        ok.height = 48;

        carpi = new Rectangle();
        carpi.x = 470;
        carpi.y = 410;
        carpi.width = 40;
        carpi.height = 42;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        backgroundSprite.draw(game.batch);
        game.batch.draw(oyunbitti,800/2-250/2, 200);
        String str = puan+"";
        game.font.getData().setScale(3, 3);
        game.font.draw(game.batch, str, 375, 320);  //395 320

        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(ok.contains(touchPos.x,touchPos.y) || carpi.contains(touchPos.x,touchPos.y))
            {
                game.setScreen(new MainMenu(game,ses));
            }

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