package com.badlogic.drop;

/**
 * Created by NurullahSamed on 6.5.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

public class MainMenu implements Screen,InputProcessor {

    final Main game;

    OrthographicCamera camera;
    private Texture yeniOyun;
    private Texture basYeni;
    private Texture basHakkinda;
    private Texture hakkinda;
    private Texture cikis;
    private Texture basCikis;
    private Texture sesacik;
    private Texture seskapali;

    public boolean ses = true;

    private Rectangle yeni;
    private Rectangle hak;
    private Rectangle cik;
    private Rectangle sesac;
    private Rectangle seskapa;

    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    private static final int        FRAME_COLS = 1;         // #1
    private static final int        FRAME_ROWS = 1;         // #2

    Animation animasyon;          // #3
    Texture                         sheet;              // #4
    TextureRegion[]                 frames;             // #5
    TextureRegion                   currentFrame;           // #7
    float stateTime;

    Animation animasyon2;          // #3
    Texture                         sheet2;              // #4
    TextureRegion[]                 frames2;             // #5
    TextureRegion                   currentFrame2;           // #7
    float stateTime2;


    Animation animasyon3;          // #3
    Texture                         sheet3;              // #4
    TextureRegion[]                 frames3;             // #5
    TextureRegion                   currentFrame3;           // #7
    float stateTime3;

    private Sound clickSound;



    boolean üstünde = false;
    boolean üstünde2 = false;
    boolean üstünde3 = false;
    private Music music;

    public MainMenu(final Main gam,boolean ses) {
        this.ses = ses;
        game = gam;
        Gdx.input.setInputProcessor(this);

        music = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        music.setLooping(true);



        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        yeniOyun = new Texture(Gdx.files.internal("yenioyun.png"));
        basYeni = new Texture(Gdx.files.internal("basyeni.png"));
        hakkinda = new Texture(Gdx.files.internal("hakkinda.png"));
        basHakkinda = new Texture(Gdx.files.internal("bashakkinda.png"));
        cikis = new Texture(Gdx.files.internal("cikis.png"));
        basCikis = new Texture(Gdx.files.internal("bascikis.png"));

        sesacik = new Texture(Gdx.files.internal("sesacik.png"));
        seskapali = new Texture(Gdx.files.internal("seskapali.png"));

        backgroundTexture = new Texture("menubackground.png");

        backgroundSprite =new Sprite(backgroundTexture);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));


        yeni = new Rectangle();
        yeni.x = 800/2 - 262/2;
        yeni.y = 320 ;
        yeni.width = yeniOyun.getWidth(); //Resimin genişliği alınıp kolay.width değişkenine atandı.
        yeni.height = yeniOyun.getHeight(); //Resimin yüksekliği alınıp kolay.height değişkenine atandı.

        hak = new Rectangle();
        hak.x = 800/2 - 262/2;
        hak.y = 220 ;
        hak.width = hakkinda.getWidth(); //Resimin genişliği alınıp kolay.width değişkenine atandı.
        hak.height = hakkinda.getHeight(); //Resimin yüksekliği alınıp kolay.height değişkenine atandı.


        cik = new Rectangle();
        cik.x = 800/2 - 262/2;
        cik.y = 120 ;
        cik.width = cikis.getWidth(); //Resimin genişliği alınıp kolay.width değişkenine atandı.
        cik.height = cikis.getHeight(); //Resimin yüksekliği alınıp kolay.height değişkenine atandı.

        sesac = new Rectangle();
        sesac.x = 30;
        sesac.y = 390;
        sesac.width = sesacik.getWidth();
        sesac.height = sesacik.getHeight();


        seskapa = new Rectangle();
        seskapa.x = 30;
        seskapa.y = 390;
        seskapa.width = seskapali.getWidth();
        seskapa.height = seskapali.getHeight();



        sheet = new Texture(Gdx.files.internal("basyeni.png")); // #9
        TextureRegion[][] tmp2 = TextureRegion.split(sheet, sheet.getWidth()/FRAME_COLS, sheet.getHeight()/FRAME_ROWS);              // #10
        frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index2++] = tmp2[i][j];
            }
        }

        animasyon = new Animation(0.25f, frames);      // #11
        stateTime = 0f;

        sheet2 = new Texture(Gdx.files.internal("bashakkinda.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(sheet2, sheet2.getWidth()/FRAME_COLS, sheet2.getHeight() / FRAME_ROWS);              // #10
        frames2 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        animasyon2 = new Animation(0.25f, frames2);      // #11
        stateTime2 = 0f;

        sheet3 = new Texture(Gdx.files.internal("bascikis.png")); // #9
        TextureRegion[][] tmp3 = TextureRegion.split(sheet3, sheet3.getWidth()/FRAME_COLS, sheet3.getHeight() / FRAME_ROWS);              // #10
        frames3 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index3++] = tmp3[i][j];
            }
        }


        animasyon3 = new Animation(0.25f, frames3);      // #11
        stateTime3 = 0f;

    }

    @Override
    public void render(float delta) {

        if(ses)
        {
            music.play();

        }
        else
        {
            music.stop();
        }

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        backgroundSprite.draw(game.batch);
        game.batch.draw(yeniOyun, yeni.x, yeni.y);
        game.batch.draw(hakkinda, hak.x, hak.y);
        game.batch.draw(cikis, cik.x, cik.y);

        if(ses)
        {
            game.batch.draw(sesacik, sesac.x, sesac.y);
        }
        else
        {
            game.batch.draw(seskapali, seskapa.x, seskapa.y);
        }
        game.batch.end();

        if (animasyon.isAnimationFinished(stateTime))
        {
            stateTime = 0;
            game.setScreen(new GameScreen(game, ses));
            dispose();
        }

        if(animasyon2.isAnimationFinished(stateTime2)) {
            stateTime2 = 0;
            game.setScreen(new Hakkinda(game, ses));
            dispose();
        }

        if(animasyon3.isAnimationFinished(stateTime3)) {
            stateTime3 = 0;
            Gdx.app.exit();
            dispose();
        }

        if(üstünde)
        {
            currentFrame = animasyon.getKeyFrame(stateTime, true);  // #16
            game.batch.begin();
            backgroundSprite.draw(game.batch);
            game.batch.draw(basYeni, yeni.x+7, yeni.y+7);             // #17
            game.batch.draw(hakkinda, hak.x, hak.y);
            game.batch.draw(cikis, cik.x, cik.y);
            game.batch.end();
            stateTime += Gdx.graphics.getDeltaTime();           // #15
            yeniOyun.dispose();
        }

        if(üstünde2)
        {
            currentFrame2 = animasyon2.getKeyFrame(stateTime2, true);  // #16
            game.batch.begin();
            backgroundSprite.draw(game.batch);
            game.batch.draw(yeniOyun, yeni.x, yeni.y);             // #17
            game.batch.draw(basHakkinda, hak.x+7, hak.y+7);
            game.batch.draw(cikis, cik.x, cik.y);
            game.batch.end();
            stateTime2 += Gdx.graphics.getDeltaTime();           // #15
            //hakkinda.dispose();
        }

        if(üstünde3)
        {
            currentFrame3 = animasyon3.getKeyFrame(stateTime3, true);  // #16
            game.batch.begin();
            backgroundSprite.draw(game.batch);
            game.batch.draw(yeniOyun, yeni.x, yeni.y);             // #17
            game.batch.draw(hakkinda, hak.x, hak.y);
            game.batch.draw(basCikis, cik.x+7, cik.y+7);
            game.batch.end();
            stateTime3 += Gdx.graphics.getDeltaTime();           // #15
            //hakkinda.dispose();
        }




    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        if(yeni.contains(touchPos.x, touchPos.y))
        {
            üstünde = true;
            if(ses)
            {
                clickSound.play();
            }

        }

        if (hak.contains(touchPos.x,touchPos.y))
        {
            üstünde2 = true;
            if(ses)
            {
                clickSound.play();
            }

        }

        if(cik.contains(touchPos.x,touchPos.y))
        {
            üstünde3 = true;
            if(ses)
            {
                clickSound.play();
            }
        }

        if(sesac.contains(touchPos.x,touchPos.y) && ses == true)
        {
            ses = false;
        }

        else if(seskapa.contains(touchPos.x,touchPos.y) && ses == false)
        {
            ses = true;
        }



        return false;
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

        /*yeniOyun.dispose();
        basYeni.dispose();
        basHakkinda.dispose();
        hakkinda.dispose();
        cikis.dispose();
        basCikis.dispose();
        sesacik.dispose();
        seskapali.dispose();
        clickSound.dispose();
        sheet.dispose();
        sheet2.dispose();
        sheet3.dispose();*/
        clickSound.dispose();
        music.dispose();




    }

    @Override
    public void show() {

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}