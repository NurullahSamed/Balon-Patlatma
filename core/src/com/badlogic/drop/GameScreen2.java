package com.badlogic.drop;

/**
 * Created by NurullahSamed on 11.5.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Created by NurullahSamed on 2.5.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;


/**
 * Created by NurullahSamed on 2.5.2016.
 */


import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen2  implements Screen {
    Preferences prefs;

    final Main game;
    private Texture yesil;
    private Texture kirmizi;
    private Texture siyah;
    private Texture sari;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Sound dropSound;
    private Sound popSound;
    private Sound siyahpopSound;
    private Music rainMusic;
    //private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Array<Rectangle> kirmizibalonlar;
    private Array<Rectangle> saribalonlar;
    private Array<Rectangle> yesilbalonlar;
    private Array<Rectangle> siyahbalonlar;
    private long kirmiziUretildi;
    private double sariUretildi;
    private double yesilUretildi;
    private double siyahUretildi;
    public long puan2 = 0;
    private boolean yesilpatladi;
    private boolean kirmizipatladi;
    private boolean saripatladi;
    private boolean siyahpatladi;
    //BitmapFont font;
    private float yesilx;
    private float yesily;

    private float kirmizix;
    private float kirmiziy;

    private float sarix;
    private float sariy;

    private float siyahx;
    private float siyahy;

    private float sure = 20;


    private static final int        FRAME_COLS = 4;         // #1
    private static final int        FRAME_ROWS = 1;         // #2

    Animation kirmizianimasyon;          // #3
    Texture                         sheet;              // #4
    TextureRegion[]                 frames;             // #5
    TextureRegion                   currentFrame;           // #7

    private static final int        FRAME_COLS2 = 4;         // #1
    private static final int        FRAME_ROWS2 = 4;         // #2

    Animation                      siyahanimasyon;
    Texture                         sheet2;
    TextureRegion[]                 frames2;
    TextureRegion                   currentFrame4;

    Animation                       yesilanimasyon;          // #3
    Texture                         sheet3;              // #4
    TextureRegion[]                 frames3;             // #5
    TextureRegion                   currentFrame2;           // #7

    Animation                       sarianimasyon;          // #3
    Texture                         sheet4;              // #4
    TextureRegion[]                 frames4;             // #5
    TextureRegion                   currentFrame3;           // #7


    float stateTime;                                        // #8
    float stateTime2;
    float stateTime3;
    float stateTime4;

    long oyunbasladi;
    long move;
    int secim;

    boolean birKirmizi = false;
    boolean birSari = false;
    boolean birYesil = false;
    boolean birSiyah = false;
    long puan;

    boolean ses;

    public GameScreen2(final Main gam,long veri,boolean ses) {
        this.game = gam;
        this.ses = ses;
        puan = veri;

        oyunbasladi = TimeUtils.nanoTime();
        move = TimeUtils.nanoTime();

        //BALONLAR

        yesil = new Texture(Gdx.files.internal("yesil.png"));
        sari = new Texture(Gdx.files.internal("sari.png"));
        kirmizi = new Texture(Gdx.files.internal("kirmizi.png"));
        siyah = new Texture(Gdx.files.internal("siyah.png"));
        backgroundTexture = new Texture("background2.png");

        backgroundSprite =new Sprite(backgroundTexture);

        //font = new BitmapFont();


        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        popSound = Gdx.audio.newSound(Gdx.files.internal("pop.flac"));
        siyahpopSound = Gdx.audio.newSound(Gdx.files.internal("siyahpop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        //rainMusic.setLooping(true);
        //rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        //batch = new SpriteBatch();

        // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        bucket.width = 64;
        bucket.height = 64;

        // create the raindrops array and spawn the first raindrop
        kirmizibalonlar = new Array<Rectangle>();
        saribalonlar = new Array<Rectangle>();
        yesilbalonlar = new Array<Rectangle>();
        siyahbalonlar = new Array<Rectangle>();
        kirmiziBalonÜretme();
        sariBalonÜretme();
        yesilBalonÜretme();
        siyahBalonÜretme();


        sheet = new Texture(Gdx.files.internal("pop.png")); // #9
        TextureRegion[][] tmp2 = TextureRegion.split(sheet, sheet.getWidth()/FRAME_COLS, sheet.getHeight()/FRAME_ROWS);              // #10
        frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index2++] = tmp2[i][j];
            }
        }

        sheet2 = new Texture(Gdx.files.internal("popsiyah.png")); // #9
        TextureRegion[][] tmp3 = TextureRegion.split(sheet2, sheet2.getWidth()/FRAME_COLS2, sheet2.getHeight()/FRAME_ROWS2);              // #10
        frames2 = new TextureRegion[FRAME_COLS2 * FRAME_ROWS2];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS2; i++) {
            for (int j = 0; j < FRAME_COLS2; j++) {
                frames2[index3++] = tmp3[i][j];
            }
        }

        sheet3 = new Texture(Gdx.files.internal("pop2.png")); // #9
        TextureRegion[][] tmp4 = TextureRegion.split(sheet3, sheet3.getWidth()/FRAME_COLS, sheet3.getHeight()/FRAME_ROWS);              // #10
        frames3 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index4 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames3[index4++] = tmp4[i][j];
            }
        }

        sheet4 = new Texture(Gdx.files.internal("pop3.png")); // #9
        TextureRegion[][] tmp5 = TextureRegion.split(sheet4, sheet4.getWidth()/FRAME_COLS, sheet4.getHeight()/FRAME_ROWS);              // #10
        frames4 = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index5 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames4[index5++] = tmp5[i][j];
            }
        }


        kirmizianimasyon = new Animation(0.050f, frames);      // #11
        stateTime = 0f;

        yesilanimasyon = new Animation(0.050f, frames3);
        stateTime2 = 0f;

        sarianimasyon = new Animation(0.050f, frames4);
        stateTime3 = 0f;

        siyahanimasyon = new Animation(0.050f, frames2);
        stateTime4 = 0f;

    }



    private void kirmiziBalonÜretme() {
        Rectangle balon = new Rectangle();
        balon.x = MathUtils.random(0, 800 - 64);
        balon.y = 0;
        balon.width = 64;
        balon.height = 64;
        kirmizibalonlar.add(balon);
        kirmiziUretildi = TimeUtils.nanoTime();
    }

    private void sariBalonÜretme() {
        Rectangle balon = new Rectangle();
        balon.x = MathUtils.random(0, 800 - 64);
        balon.y = MathUtils.random(0, 480 - 64);;
        balon.width = 64;
        balon.height = 64;
        saribalonlar.add(balon);
        sariUretildi = TimeUtils.nanoTime();
    }


    private void yesilBalonÜretme() {
        Rectangle balon = new Rectangle();
        balon.x = MathUtils.random(0, 800 - 64);
        balon.y = 0;
        balon.width = 64;
        balon.height = 64;
        yesilbalonlar.add(balon);
        yesilUretildi = TimeUtils.nanoTime();
    }

    private void siyahBalonÜretme() {
        Rectangle balon = new Rectangle();
        balon.x = MathUtils.random(0, 800 - 64);
        balon.y = 0;
        balon.width = 64;
        balon.height = 64;
        siyahbalonlar.add(balon);
        siyahUretildi = TimeUtils.nanoTime();
    }


    @Override
    public void dispose() {
        // dispose of all the native resources
        kirmizi.dispose();
        yesil.dispose();
        siyah.dispose();
        sari.dispose();
        dropSound.dispose();
        //rainMusic.dispose();
        game.batch.dispose();
    }

    @Override
    public void render(float delta) {
        //System.out.print("\nFPS: " + Gdx.graphics.getFramesPerSecond() + "\n");
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        //Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops

        game.batch.begin();
        backgroundSprite.draw(game.batch);
        String str = puan2+"";
        game.font.getData().setScale(3, 3);
        game.font.draw(game.batch, str, 720, 460);

        String str2 = sure+"";
        game.font.draw(game.batch, str2, 720, 420);

        if(TimeUtils.nanoTime()-oyunbasladi > 1000000000 )
        {
            sure--;
            oyunbasladi = TimeUtils.nanoTime();
        }


        for (Rectangle balon : kirmizibalonlar) {
            game.batch.draw(kirmizi, balon.x, balon.y);
        }

        for (Rectangle balon : saribalonlar) {
            game.batch.draw(sari, balon.x, balon.y);
        }

        for (Rectangle balon : yesilbalonlar) {
            game.batch.draw(yesil, balon.x, balon.y);
        }

        for (Rectangle balon : siyahbalonlar) {
            game.batch.draw(siyah, balon.x, balon.y);
        }

        game.batch.end();

        // process user input
        /*if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;*/

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - kirmiziUretildi > 1000000000) kirmiziBalonÜretme();
        if (TimeUtils.nanoTime() - yesilUretildi > 1000000000) yesilBalonÜretme();
        if (TimeUtils.nanoTime() - sariUretildi > 4000000000.0) sariBalonÜretme();
        if (TimeUtils.nanoTime() - siyahUretildi > 3000000000.0) siyahBalonÜretme();




        //Kırmızı balonların random hareketi
        Iterator<Rectangle> iter = kirmizibalonlar.iterator();
        while (iter.hasNext() ) {
            Rectangle kirmizi = iter.next();

            kirmizi.y += 200f * Gdx.graphics.getDeltaTime();

            int random = MathUtils.random(300000000, 800000000);
            if(TimeUtils.nanoTime()-move > random )
            {
                secim = MathUtils.random(0, 1);
                move = TimeUtils.nanoTime();
            }

            if(secim ==0 )
            {
                kirmizi.x -= MathUtils.random(150, 200) * Gdx.graphics.getDeltaTime();
            }
            if(secim == 1 )
            {
                kirmizi.x += MathUtils.random(150, 200) * Gdx.graphics.getDeltaTime();
            }





            //Kırmızı balonların çerceveden çıktığında silinmesi
            if (kirmizi.y + 64 < 0 || kirmizi.y > 480 || kirmizi.x+64 < 0 || kirmizi.x > 800)
            {
                iter.remove();
                //dropSound.play();
            }


            //Kırmızı balona tıklandığında patlaması
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if(kirmizi.contains(touchPos.x,touchPos.y))
                {
                    iter.remove();
                    if(ses)
                    {
                        popSound.play();
                    }
                    puan2 += 10;

                    kirmizipatladi = true;
                    birKirmizi = true;
                    kirmizix = kirmizi.x;
                    kirmiziy = kirmizi.y;

                }
                //dispose();
            }
        }

        if(kirmizianimasyon.isAnimationFinished(stateTime))
        {
            kirmizipatladi = false;
            stateTime = 0;
        }

        if(kirmizipatladi)
        {
            currentFrame = kirmizianimasyon.getKeyFrame(stateTime, true);  // #16
            game.batch.begin();
            game.batch.draw(currentFrame, kirmizix, kirmiziy);             // #17
            game.batch.end();
            stateTime += Gdx.graphics.getDeltaTime();           // #15
        }



        Iterator<Rectangle> iter2 = saribalonlar.iterator();
        while (iter2.hasNext()) {
            Rectangle sari = iter2.next();
            boolean sariKayboldu = false;
            //Sari balonun çıktıktan bir saniye sonra yok olması
            if (TimeUtils.nanoTime() - sariUretildi > 1000000000.0)
            {
                iter2.remove();
                if(ses)
                {
                    dropSound.play();
                }
                sariKayboldu = true;
            }

            //Sari balona tıklandığında patlaması
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if(sari.contains(touchPos.x,touchPos.y) && sariKayboldu == false)
                {
                    iter2.remove();
                    if(ses)
                    {
                        popSound.play();
                    }
                    puan2 += 20;

                    saripatladi = true;
                    birSari = true;
                    sarix = sari.x;
                    sariy = sari.y;
                }
                //dispose();
            }
        }

        if(sarianimasyon.isAnimationFinished(stateTime3))
        {
            saripatladi = false;
            stateTime3 = 0;
        }

        if(saripatladi)
        {
            currentFrame3 = sarianimasyon.getKeyFrame(stateTime3, true);  // #16
            game.batch.begin();
            game.batch.draw(currentFrame3, sarix, sariy);             // #17
            game.batch.end();
            stateTime3 += Gdx.graphics.getDeltaTime();           // #15
        }

        Iterator<Rectangle> iter3 = yesilbalonlar.iterator();
        while (iter3.hasNext()) {
            Rectangle yesil = iter3.next();
            yesil.y += 400 * Gdx.graphics.getDeltaTime();




            int secim = MathUtils.random(0, 100);

            if(secim == 0)
            {
                iter3.remove();
                Rectangle balon = new Rectangle();
                balon.x = yesil.x;
                balon.y = yesil.y;
                balon.width = 64;
                balon.height = 64;
                siyahbalonlar.add(balon);
                //siyahUretildi = TimeUtils.nanoTime();

            }

            //Yesil balona tıklandığında patlamasi
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if(yesil.contains(touchPos.x,touchPos.y))
                {
                    iter3.remove();
                    if(ses)
                    {
                        popSound.play();
                    }
                    puan2 += 5;
                    yesilpatladi = true;
                    birYesil = true;
                    yesilx = yesil.x;
                    yesily = yesil.y;

                }
                //dispose();
            }

        }


        if(yesilanimasyon.isAnimationFinished(stateTime2))
        {
            yesilpatladi = false;
            stateTime2 = 0;
        }

        if(yesilpatladi)
        {
            currentFrame2 = yesilanimasyon.getKeyFrame(stateTime2, true);  // #16
            game.batch.begin();
            game.batch.draw(currentFrame2, yesilx, yesily);             // #17
            game.batch.end();
            stateTime2 += Gdx.graphics.getDeltaTime();           // #15
        }







        Iterator<Rectangle> iter4 = siyahbalonlar.iterator();
        while (iter4.hasNext()) {
            Rectangle siyah = iter4.next();
            siyah.y += 400 * Gdx.graphics.getDeltaTime();



            //Siyah balonların çerceveden çıktığında silinmesi
            if (siyah.y + 64 < 0 || siyah.y > 480 || siyah.x+64 < 0 || siyah.x > 800)
            {
                //iter4.remove();
                //dropSound.play();g
            }

            int secim = MathUtils.random(0, 100);

            if(secim == 0)
            {
                iter4.remove();
                Rectangle balon = new Rectangle();
                balon.x = siyah.x;
                balon.y = siyah.y;
                balon.width = 64;
                balon.height = 64;
                yesilbalonlar.add(balon);

            }

            //Siyah balona tıklandığında patlaması
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if(siyah.contains(touchPos.x,touchPos.y)) {
                    iter4.remove();
                    if(ses)
                    {
                        siyahpopSound.play();

                    }
                    puan2 -= 10;
                    Gdx.input.vibrate(250);

                    siyahpatladi = true;
                    birSiyah = true;
                    siyahx = siyah.x;
                    siyahy = siyah.y;
                }
                //dispose();
            }


        }

        if(siyahanimasyon.isAnimationFinished(stateTime4))
        {
            siyahpatladi = false;
            stateTime4 = 0;
        }

        if(siyahpatladi)
        {
            currentFrame4 = siyahanimasyon.getKeyFrame(stateTime4, true);  // #16
            game.batch.begin();
            game.batch.draw(currentFrame4, siyahx, siyahy);             // #17
            game.batch.end();
            stateTime4 += Gdx.graphics.getDeltaTime();           // #15
        }

        if(sure == 0)
        {

            if(birYesil && birSari && birSiyah && birKirmizi && puan2 >= 100 )  //Oyun başarılı
            {
                game.setScreen(new OyunBasarili2(game,puan,puan2,ses));
            }

            else //Oyun Başarısız
            {
                game.setScreen(new OyunBitti2(game,puan,puan2,ses));
                //game.setScreen(new GameScreen(game));
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
    public void show() {

    }



}

