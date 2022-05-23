package com.company;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.company.RedRevolver.RedRevolver;

public class Main {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 1080;
        config.width = 1920;
        new LwjglApplication((ApplicationListener) new RedRevolver(), config);

        /*


         */


        //hard mode is shouldShoot on Invader class at 1000 and waits 1300 to spawn each enemy

        //UI is 616 width and 821 height

        //8.556 by 11.403



 //       {
 //           "time":"90",
 //               "type":"1",
 //               "playerX":10,
 //               "playerY":20,
 //               "platforms":
 // [
 //           {"x":4.0,"y":2.0,"w":1,"h":0.08},
 //           {"x":2.0,"y":1.5,"w":3,"h":0.08},
 //           {"x":3.0,"y":0.5,"w":5,"h":0.08}
 // ]
 //       }




    //    1080 height
         //       1920

        //original game screen size is 860 height and 600 width

        //box2d screen size is 480 height and 800 width

        //Each square is 78 wide and 100 tall pixels
        //Total image is 1059 wide and 573 tall
        //first square starts after 254 pixels
        //print y values of plant and zombie to compare



        //command alt L to format code





        //package com.company;
        //
        //import com.badlogic.gdx.Game;
        //import com.badlogic.gdx.Gdx;
        //import com.badlogic.gdx.graphics.GL20;
        //import com.badlogic.gdx.graphics.OrthographicCamera;
        //import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        //import com.badlogic.gdx.math.Vector2;
        //import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
        //import com.badlogic.gdx.physics.box2d.World;
        //
        //public class Box2DLessonJSON extends Game {
        //
        //    private World world;
        //    private SpriteBatch batch;
        //    private OrthographicCamera camera;
        //    private Box2DDebugRenderer renderer;
        //
        //
        //    @Override
        //    public void create() {
        //        world = new World(new Vector2(0, -9.8f), true);
        //        batch = new SpriteBatch();
        //        camera = new OrthographicCamera();
        //        camera.setToOrtho(true, 8, 4.8f);

        //    }
        //
        //    @Override
        //    public void render() {
        //        Gdx.gl.glClearColor(0, 0, 0, 1);
        //        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //
        //        camera.update();
        //        renderer.render(world, camera.combined);
        //        world.step(1/60f, 6, 2);
        //    }
        //}


    }
}