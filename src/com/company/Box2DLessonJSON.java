package com.company;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.company.BodyGenerator;
import com.company.LevelsExample.LevelDef;
import com.company.LevelsExample.LevelGenerator;
import com.company.LevelsExample.PlatformDef;
import com.company.PlatformerScreen;

public class Box2DLessonJSON extends Game implements InputProcessor {


    private World world;
    private OrthographicCamera debugCamera;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Box2DPlayer player;

    private LevelGenerator generator;
    private BodyGenerator bodyGenerator;

    @Override
    public void create() {
        world = new World(new Vector2(0, -9.8f), true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480f);
        debugCamera = new OrthographicCamera();
        debugCamera.setToOrtho(false, 8, 4.8f);

        renderer = new Box2DDebugRenderer();
        bodyGenerator = new BodyGenerator(world);

        Body playerBody = bodyGenerator.createPlayer(1, 3);
        player = new Box2DPlayer(world, playerBody);

        generator = new LevelGenerator(world);
        LevelDef def = generator.loadLevel(1);
        for (PlatformDef p : def.platforms) {
            bodyGenerator.createFloor(p);
        }
        //for(EnemiesDef e: def.enemies) {
        //    bodyGenerator.createEnemy(e);
        //}

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update();
        debugCamera.position.set(player.body.getPosition().x, 2.4f, 0);
        debugCamera.update();
        renderer.render(world, debugCamera.combined);
        camera.position.set(player.body.getPosition().x * 100, 240, 0);
        camera.update();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        batch.end();

        world.step(1 / 60f, 6, 2);

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