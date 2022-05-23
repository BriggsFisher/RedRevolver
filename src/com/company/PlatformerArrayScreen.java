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

import java.util.ArrayList;

public class PlatformerArrayScreen implements Screen, InputProcessor {

    Box2DLesson game;
    World world;
    OrthographicCamera debugCamera;
    Box2DDebugRenderer renderer;

    OrthographicCamera camera;
    SpriteBatch batch;

    Box2DPlayer player;

    BodyGenerator generator;

    ArrayList<Box> boxes;
    ArrayList<Circle> circles;

    float spawnTime = 0;
    Texture boxTexture, circleTexture;

    public PlatformerArrayScreen(Box2DLesson game) {
        this.game = game;
        batch = game.batch;
        // gravity = -9.8m/s^2, sleep = true means when objects come to a rest they don't need to be checked
        // until another object comes into contact with them
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(new MyContactListener());

        debugCamera = new OrthographicCamera();
        // create a debugCamera with a viewport width of 8 units and a viewport height of 4.8 units
        debugCamera.setToOrtho(false, 8, 4.8f);

        camera = new OrthographicCamera();
        // create a debugCamera with a viewport width of 8 units and a viewport height of 4.8 units
        camera.setToOrtho(false, 800, 480);


        renderer = new Box2DDebugRenderer();
        generator = new BodyGenerator(world);

        generator.createFloor(4, 0.05f, 8, 0.05f);
        generator.createFloor(0.05f, 2.4f, 0.05f, 4.8f);
        generator.createFloor(7.95f, 2.4f, 0.05f, 4.8f);

        generator.createRectangle(2,2.9f,0.05f,11f,BodyDef.BodyType.StaticBody,(float)Math.PI/2.1f);
        generator.createRectangle(6f,4.1f,0.05f,11f,BodyDef.BodyType.StaticBody,(float)Math.PI/-2.1f);
        generator.createRectangle(2,0.6f,0.05f,11f,BodyDef.BodyType.StaticBody,(float)Math.PI/2.1f);
        generator.createRectangle(6f,1.8f,0.05f,11f,BodyDef.BodyType.StaticBody,(float)Math.PI/-2.1f);

        boxes = new ArrayList<>();
        circles = new ArrayList<>();
        boxTexture = new Texture("assets/Circle2.png");
        circleTexture = new Texture("assets/Circle2.png");

        Body playerBody = generator.createPlayer(7,1);
        player = new Box2DPlayer(world, playerBody);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update();
        spawnTime += v;
        if(spawnTime > 1f) {
            spawnTime -= 3;
            Box temp = new Box(boxTexture);
            temp.body = generator.createCircle(7.4f,4.6f);
            boxes.add(temp);
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugCamera.update();
        camera.update();
        renderer.render(world, debugCamera.combined);


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(Box b:boxes){
        b.update();
        b.draw(batch);
        }
        for(Circle c:circles){
            c.update();
            c.draw(batch);
        }

        //texture on arrayscreen on edsby


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