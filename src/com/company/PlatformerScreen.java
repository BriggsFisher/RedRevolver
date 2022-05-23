package com.company;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.company.LevelsExample.LevelDef;
import com.company.LevelsExample.LevelGenerator;
import com.company.LevelsExample.PlatformDef;

public class PlatformerScreen implements Screen, InputProcessor {

    Box2DLesson game;
    World world;
    OrthographicCamera debugCamera;
    Box2DDebugRenderer renderer;

    OrthographicCamera camera;

    BodyGenerator generator;
    LevelGenerator levGenerator;

    SpriteBatch batch;


    Box2DPlayer player;
    Box2DBullet bullet;

    Sprite box;

    Body boxBody;
    Body bulletBody;

    public PlatformerScreen(Box2DLesson game) {
        this.game = game;
        batch = game.batch; //already had a SpriteBatch in Box2DLesson that we are taking from there
        // gravity = -9.8m/s^2, sleep = true means when objects come to a rest they don't need to be checked
        // until another object comes into contact with them
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(new MyContactListener());
        debugCamera = new OrthographicCamera();
        // create a camera with a viewport width of 8 units and a viewport height of 4.8 units
        debugCamera.setToOrtho(false, 8, 4.8f);
//        camera.position.set(0,0,0);

        box = new Sprite(new Texture("assets/PlayerBox2D.jpg"));

        camera = new OrthographicCamera();
        // create a camera with a viewport width of 8 units and a viewport height of 4.8 units
        camera.setToOrtho(false, 800, 480f);
//        camera.position.set(0,0,0);

        renderer = new Box2DDebugRenderer();
        generator = new BodyGenerator(world);

        generator.createFloor(4, 0.05f, 8, 0.05f);
        generator.createFloor(4, 4.75f, 8, 0.05f);
        generator.createFloor(6, 1f, 2, 0.05f);
        generator.createFloor(0.05f, 2.4f, 0.05f, 4.8f);
        generator.createFloor(7.95f, 2.4f, 0.05f, 4.8f);

        generator.createRectangle(5f,1f ,0.32f ,0.32f , BodyDef.BodyType.DynamicBody,0);
        generator.createRectangle(1f,1f ,0.32f ,0.32f , BodyDef.BodyType.DynamicBody,0);






        // reference to the box 2d body
        boxBody = generator.createRectangle(1.1f,2f ,0.32f ,0.32f , BodyDef.BodyType.DynamicBody,0);
        bulletBody = generator.createBullet(1f,1f ,0.32f ,0.32f , 0); //THIS IS THE CODE FOR MY BULLETS OVER HERE LOOK AT ME
        bullet = new Box2DBullet(world,bulletBody);

        generator.createRectangle(2,1,0.05f,4,BodyDef.BodyType.StaticBody,(float)Math.PI/3);
        Body playerBody = generator.createPlayer(1,3);

        player = new Box2DPlayer(world, playerBody);

        Gdx.input.setInputProcessor(this); //this means use this class
        levGenerator = new LevelGenerator(world);
        LevelDef def = levGenerator.loadLevel(1);
        for (PlatformDef p : def.platforms) {
            generator.createFloor(p);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update();
      //  bullet.update();
        debugCamera.position.set(player.body.getPosition().x,2.4f,0);
        debugCamera.update();
        renderer.render(world, debugCamera.combined);
        camera.position.set(player.body.getPosition().x*100,240,0);
        camera.update();


        box.setPosition(boxBody.getPosition().x * 100 - 16,boxBody.getPosition().y * 100 - 16);
        box.setRotation((float)Math.toDegrees(boxBody.getAngle()));



        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        box.draw(batch);
        bullet.draw(batch);
        player.draw(batch);
        batch.end();

        world.step(1/60f, 6, 2);
    }














    @Override
    public void resize(int i, int i1) {

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
    public boolean keyDown(int i) {
        if(i == Input.Keys.A) {
            player.left = true;
        }
        if(i == Input.Keys.D) {
            player.right = true;
        }
        if(i == Input.Keys.W) {
            player.up = true;
        }
        if(i == Input.Keys.SPACE) {
            bullet.place = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        if(i == Input.Keys.A) {
            player.left = false;
        }
        if(i == Input.Keys.D) {
            player.right = false;
        }
        if(i == Input.Keys.W) {
            player.up = false;
        }
        if(i == Input.Keys.SPACE) {
            bullet.place = false;
            System.out.println("hello");
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}