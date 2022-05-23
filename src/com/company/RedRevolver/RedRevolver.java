package com.company.RedRevolver;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.company.LevelsExample.LevelDef;
import com.company.LevelsExample.LevelGeneratorRegular;
import com.company.LevelsExample.PlatformDef;
import com.company.RedRevolver.invaders.Invader;
import com.company.RedRevolver.invaders.Laser;

import java.util.ArrayList;



public class RedRevolver extends Game implements Screen, InputProcessor {

    com.badlogic.gdx.math.Rectangle p;
    SpriteBatch batch;
    int score = 0; //score of player
    int bombsUsed = 0; //limits the amount of bombs the player can use
    Player player;
    Boolean sound = true; //makes the death sound only play once
    Boolean bombboolean = false; //used to make it so that when I press V it can delete bullets multiple times
    Boolean death = false; //when death true, game no longer renders and game over screen is shown
    Boolean gameStart = false; //waits for space to be pressed to start
    Boolean usedBomb = false; //makes sure the blueUI only moves once
    long spawntime, spawntime2, bullettime, lasertime, bombtime, startlaser = System.currentTimeMillis();
    long playerhit = System.currentTimeMillis() - 250; //invincibility frames
    ArrayList<Invader> invaders;
    ArrayList<Laser> lasers;
    ArrayList<SpaceBullet> spacebullets;
    ArrayList<InvaderBullet> invaderbullets;
    Texture invaderTexture, playerTexture, bulletTexture, invaderbulletTexture, laserTexture;
    Sprite gameOver, startGame, leftTexture, rightTexture, blackUI, gameUI, greenUI, blueUI, oneLife, zeroLife, starBackground, starBackground2;
    BitmapFont ui; //ui for player's score is initialized
    Music backgroundsound, hitsound, deadsound, lasersound, enemysound, bombsound;


    @Override
    public void create() {
        batch = new SpriteBatch();
        invaders = new ArrayList();
        lasers = new ArrayList();
        invaderTexture = new Texture("assets/Thin Enemy.png");
        laserTexture = new Texture("Assets/LaserEnemy.png");
        playerTexture = new Texture("Assets/stop1.png");
        gameOver = new Sprite(new Texture("Assets/gameOver.jpg"));
        startGame = new Sprite(new Texture("Assets/Main Menu 2.png"));
        blackUI = new Sprite(new Texture("Assets/BlackUI.png"));
        gameUI = new Sprite(new Texture("Assets/RedUI2.png"));
        greenUI = new Sprite(new Texture("Assets/greenUI.png"));
        blueUI = new Sprite(new Texture("Assets/blueUI.png"));
        oneLife = new Sprite(new Texture("Assets/oneLife.png"));
        zeroLife = new Sprite(new Texture("Assets/zeroLife.png"));
        bulletTexture = new Texture("assets/EnemyBullet.png");
        leftTexture = new Sprite(new Texture("Assets/Left.jpg"));
        starBackground = new Sprite(new Texture("Assets/Spaceing copy.png"));
        starBackground2 = new Sprite(new Texture("Assets/Spaceing copy.png"));
        starBackground.setPosition(400,0);
        starBackground2.setPosition(400,starBackground.getHeight());
        rightTexture = new Sprite(new Texture("Assets/Right.jpg"));
        rightTexture.setPosition(Gdx.graphics.getWidth() - rightTexture.getWidth(), 0);
        invaderbulletTexture = new Texture("assets/invaderBullet.png");
        player = new Player(playerTexture, 3);
        player.sprite.setPosition(Gdx.graphics.getWidth() / 2 - player.sprite.getWidth() / 2, 50); //855ish tall
        spacebullets = new ArrayList();
        invaderbullets = new ArrayList();
        ui = new BitmapFont();
        gameOver.setPosition((Gdx.graphics.getWidth() / 2) - (gameOver.getWidth() / 2), 0);
        startGame.setPosition((Gdx.graphics.getWidth() / 2) - (gameOver.getWidth() / 2), 0);
        gameUI.setPosition(leftTexture.getWidth(), 0);
        blackUI.setPosition(leftTexture.getWidth(), 0);
        greenUI.setPosition(480, 10);
        blueUI.setPosition(789, 10);
        oneLife.setPosition(719, 0);
        zeroLife.setPosition(719, 0);
        p = new Rectangle(0, 0, (int) (player.sprite.getWidth() / 8), (int) (player.sprite.getHeight() / 8)); //made a smaller rectangle to make the hitbox only the red dot in the center of the ship
        //background music and sound effects
        backgroundsound = Gdx.audio.newMusic(Gdx.files.internal("Music/Stage2.wav"));
        backgroundsound.play();
        backgroundsound.setLooping(true);
        hitsound = Gdx.audio.newMusic(Gdx.files.internal("Music/Hit.wav"));
        deadsound = Gdx.audio.newMusic(Gdx.files.internal("Music/Explosion14.wav"));
        lasersound = Gdx.audio.newMusic(Gdx.files.internal("Music/LaserCharging.wav"));
        enemysound = Gdx.audio.newMusic(Gdx.files.internal("Music/Explosion8.wav"));
        bombsound = Gdx.audio.newMusic(Gdx.files.internal("Music/Explosion11.wav"));

        //Used for making extra levels but I didn't have time
        LevelGeneratorRegular levGenerator;
        //Gdx.input.setInputProcessor(this); //this means use this class
        levGenerator = new LevelGeneratorRegular();
        LevelDef def = levGenerator.loadLevel(1);
        for (PlatformDef p : def.platforms) {
            System.out.println(p.x + " " + p.y + " " + p.w + " " + p.h);  //create the enemy array list in here
        }

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //lets the background loop infintely
        starBackground.translateY((float)-1);
        starBackground2.translateY((float)-1);
        if(starBackground2.getY() < 0) {
            starBackground.setY(starBackground2.getY() + starBackground2.getHeight());
        }
        if(starBackground.getY() < 0) {
            starBackground2.setY(starBackground.getY() + starBackground.getHeight());
        }

        //sets the rectangle hitbox to the player's center
        p.setPosition(player.sprite.getX() + player.sprite.getWidth() / 2 - p.getWidth() / 2, player.sprite.getY() + player.sprite.getHeight() / 2 - p.getHeight() / 2);


        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) { //Work in progress pause button
            gameStart = false;
            backgroundsound.play();
            backgroundsound.setLooping(true);
        }
        //lets you restart the game and restarts some variables that aren't in the create method
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            backgroundsound.stop();
            death = false;
            score = 0;
            bombsUsed = 0;
            startlaser = System.currentTimeMillis();
            sound = true;
            playerhit = System.currentTimeMillis() - 250;
            create();
            }
        //makes sure the blueUI only moves once
        if (!Gdx.input.isKeyPressed(Input.Keys.V)) {
            usedBomb = false;
        }


        //if space is pressed the starting screen disappears
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameStart = true;
        }

        //waits 500 milliseconds and spawns a bullet at player's location
        if (System.currentTimeMillis() - bullettime > 120 && Gdx.input.isKeyPressed(Input.Keys.Z) && !Gdx.input.isKeyPressed(Input.Keys.X) && gameStart == true || System.currentTimeMillis() - bullettime > 140 && Gdx.input.isKeyPressed(Input.Keys.C) && !Gdx.input.isKeyPressed(Input.Keys.X) && gameStart == true) {
            SpaceBullet b = new SpaceBullet(bulletTexture);
            b.setPosition((player.sprite.getX() + player.sprite.getWidth() / 2) - (b.getWidth() / 2), player.sprite.getY() + player.sprite.getHeight() / 2);
            spacebullets.add(b);
            bullettime = System.currentTimeMillis();
        }
        //When player isnt pressing X it resets the laser's charge time
        if (!Gdx.input.isKeyPressed(Input.Keys.X)) {
            lasertime = System.currentTimeMillis();
        }
        //after 700 miliseconds of charging it shoots the laser
        if (Gdx.input.isKeyPressed(Input.Keys.X) && gameStart == true) {
            if (greenUI.getX() > 330) {
                greenUI.translateX((float) -0.5);
            }
            if (System.currentTimeMillis() - lasertime > 700 & Gdx.input.isKeyPressed(Input.Keys.X)) {
                if (Gdx.input.isKeyPressed(Input.Keys.X) && greenUI.getX() > 330) {
                    SpaceBullet b = new SpaceBullet(bulletTexture);
                    b.setPosition((player.sprite.getX() + player.sprite.getWidth() / 2) - (b.getWidth() / 2), player.sprite.getY() + player.sprite.getHeight() / 2);
                    spacebullets.add(b);
                }
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X) && !death && gameStart){
            lasersound.play();
        }
        else{
            lasersound.stop();
        }

        //waits 1500 milliseconds to spawn each enemy
        if (System.currentTimeMillis() - spawntime > 1500 && gameStart == true && !death) {
            Invader o = new Invader(invaderTexture, 6);
            //sets the enemy position to a random location in the playing field
            o.setPosition((float) ((Math.random() * (Gdx.graphics.getWidth() - (rightTexture.getWidth() + leftTexture.getWidth()) - o.getWidth())) + leftTexture.getWidth()), Gdx.graphics.getHeight() + o.getHeight());
            invaders.add(o);
            spawntime = System.currentTimeMillis(); //resets spawn time
        }
        //waits 4000 milliseconds to spawn each laser enemy
        if (System.currentTimeMillis() - spawntime2 + (score * 8) > 4000 && gameStart == true && !death && System.currentTimeMillis() - startlaser > 46660) {
            Laser k = new Laser(laserTexture);
            //sets the enemy position to a random location in the playing field
            k.setPosition((float) ((Math.random() * (Gdx.graphics.getWidth() - (rightTexture.getWidth() + leftTexture.getWidth()) - k.getWidth())) + leftTexture.getWidth()), Gdx.graphics.getHeight() + k.getHeight());
            lasers.add(k);
            spawntime2 = System.currentTimeMillis(); //resets spawn time for the laser enemies
        }
        //moves the player left if the Left key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.sprite.getX() >= leftTexture.getWidth() && gameStart) {
            player.moveLeft();
        }
        //moves the player right if the Right key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.sprite.getX() <= Gdx.graphics.getWidth() - player.sprite.getWidth() - rightTexture.getWidth() && gameStart) {
            player.moveRight();
        }
        //moves the player up if the Up key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.sprite.getY() <= Gdx.graphics.getHeight() - player.sprite.getHeight() && gameStart) {
            player.moveUp();
        }
        //moves the player down if the Down key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.sprite.getY() >= 50 && gameStart) {
            player.moveDown();
        }

        //Lets the player use bombs to destroy all enemy bullets
        if (Gdx.input.isKeyJustPressed(Input.Keys.V) && bombsUsed < 5 && gameStart && !death) {
            bombtime = System.currentTimeMillis();
            bombboolean = true;
            if (usedBomb == false) {
                blueUI.translateX(29);
                usedBomb = true;
                bombsUsed++;
                bombsound.play();
            }
        }
        if (System.currentTimeMillis() - bombtime < 1100 && bombboolean) {
            for (int i = invaderbullets.size() - 1; i >= 0; i--) {
                invaderbullets.remove(i);
            }

        }

        batch.begin();
        starBackground.draw(batch);
        starBackground2.draw(batch);

        if (death == false) {
            player.draw(batch); //draws player if not hit by bullet
        }

        player.update();
        player.draw(batch);

        //draws the enemies
        if (death == false && gameStart == true) {
            for (int k = invaders.size() - 1; k >= 0; k--) {
                Invader o = invaders.get(k);
                o.draw(batch);
                o.InvaderMoveDown();
                if (o.shouldShoot()) { //waits 1 second to spawn enemy bullet
                    InvaderBullet t = new InvaderBullet(invaderbulletTexture);
                    t.setPosition(o.getX() + (o.getWidth() / 2) - (t.getWidth() / 2), o.getY() + (o.getWidth() / 2) - (t.getWidth() / 2));
                    float dx = player.sprite.getX() + (player.sprite.getWidth() / 2) - t.getX(); //finds the player's position and shoots the bullet at the player using pythagorean
                    float dy = player.sprite.getY() + (player.sprite.getHeight() / 2) - t.getY();
                    float dist = (float) Math.sqrt(dx * dx + dy * dy);
                    t.px = dx / dist;
                    t.py = dy / dist;
                    float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
                    t.setRotation(angle);
                    invaderbullets.add(t);
                }
            }
        }
        //draws the laser enemies
        if (death == false && gameStart == true) {
            for (int k = lasers.size() - 1; k >= 0; k--) {
                Laser j = lasers.get(k);
                j.draw(batch);
                j.InvaderMoveDown();
                if (j.shouldShoot()) { //shoots a bullet straight
                    InvaderBullet t = new InvaderBullet(invaderbulletTexture);
                    t.setPosition(j.getX() + (j.getWidth() / 2) - (t.getWidth() / 2), j.getY() + (j.getWidth() / 2) - (t.getWidth() / 2));
                    t.px = (float)0;
                    t.py = -1;
                    float angle = (float) Math.toDegrees(Math.atan2(0, 0));
                    t.setRotation(angle);
                    invaderbullets.add(t);
                }
                if (j.shouldshootLeft()) { //shoots a bullet angled to the left
                    InvaderBullet u = new InvaderBullet(invaderbulletTexture);
                    u.setPosition(j.getX() + (j.getWidth() / 2) - (u.getWidth() / 2), j.getY() + (j.getWidth() / 2) - (u.getWidth() / 2));
                    u.px = (float) -0.5;
                    u.py = -1;
                    float angle = (float) Math.toDegrees(Math.atan2(0, -30));
                    u.setRotation(angle);
                    invaderbullets.add(u);
                }
                if (j.shouldshootRight()) { //shoots a bullet angled to the right
                    InvaderBullet u = new InvaderBullet(invaderbulletTexture);
                    u.setPosition(j.getX() + (j.getWidth() / 2) - (u.getWidth() / 2), j.getY() + (j.getWidth() / 2) - (u.getWidth() / 2));
                    u.px = (float) 0.5;
                    u.py = -1;
                    float angle = (float) Math.toDegrees(Math.atan2(0, 30));
                    u.setRotation(angle);
                    invaderbullets.add(u);
                }
            }
        }
        //draws the player's bullets
        if (death == false && gameStart == true) {
            for (int i = spacebullets.size() - 1; i >= 0; i--) {
                SpaceBullet b = spacebullets.get(i);
                if (b.isExpired()) { //removes player bullets after 2 seconds
                    spacebullets.remove(i);
                } else {
                    b.move(); //moves bullet up
                    b.draw(batch);
                }
            }
        }
        //draws the enemies' bullets
        if (death == false && gameStart == true) {
            for (int i = invaderbullets.size() - 1; i >= 0; i--) {
                InvaderBullet t = invaderbullets.get(i);
                if (t.isExpiredb()) { //removes enemy bullets after 9 seconds
                    invaderbullets.remove(i);
                } else {
                    t.moveb(); //moves bullets down
                    if(System.currentTimeMillis() - bombtime > 1100) { //While the bomb is running the enemy bullets wont spawn
                        t.draw(batch);
                    }
                }
            }
        }
        //if player's bullets overlap the enemy it deletes that invader
        for (int q = invaders.size() - 1; q >= 0; q--) {
            Invader z = invaders.get(q);
            for (int j = spacebullets.size() - 1; j >= 0; j--) {
                if (Intersector.overlaps(spacebullets.get(j).getBoundingRectangle(), invaders.get(q).getBoundingRectangle())) {
                    z.damage();
                    spacebullets.remove(j);
                    break;
                }
            }
        }

        //if player's bullets overlap the laser enemy it deletes that laser invader
        for (int q = lasers.size() - 1; q >= 0; q--) {
            Laser z = lasers.get(q);
            for (int j = spacebullets.size() - 1; j >= 0; j--) {
                if (Intersector.overlaps(spacebullets.get(j).getBoundingRectangle(), lasers.get(q).getBoundingRectangle())) {
                    z.damage();
                    spacebullets.remove(j);
                    break;
                }
            }
        }

        //allows you to kill invaders
        for (int i = 0; i < invaders.size(); i++) {
            Invader z = invaders.get(i);
            //method in invader that checks if invader has less than 1 health
            if (z.isDead()) {
                invaders.remove(z);
                enemysound.play();
                score++; //adds to player's score
                //checks if the special weapon has been used since the normal X is 480, I chose 476 to make sure the max is around 480 X
                if (greenUI.getX() < 476) {
                    greenUI.translateX((float) 5);
                }
            }
        }

        //allows you to kill laser invaders
        for (int i = 0; i < lasers.size(); i++) {
            Laser z = lasers.get(i);
            //method in invader that checks if invader has less than 1 health
            if (z.isDead()) {
                lasers.remove(z);
                enemysound.play();
                score++; //adds to player's score
                //checks if the special weapon has been used since the normal X is 480, I chose 477 to make sure the max is around 480 X
                if (greenUI.getX() < 476) {
                    greenUI.translateX((float) 5);
                }
            }
        }

        //if the invader goes offscreen it deletes the invader and doesn't give any points
        for (int i = 0; i < invaders.size(); i++) {
            Invader z = invaders.get(i);
            //method in invader that checks if the invader is off screen
            if (z.isPassed()) {
                invaders.remove(z);
            }

        }

        //if the laser enemy goes offscreen it deletes the laser enemy and doesn't give any points
        for (int i = 0; i < lasers.size(); i++) {
            Laser n = lasers.get(i);
            //method in invader that checks if the invader is off screen
            if (n.isPassed()) {
                lasers.remove(n);
            }

        }

        //if the enemy's bullets overlap the player, it deletes both
        for (int v = invaders.size() - 1; v >= 0; v--) {
            for (int j = invaderbullets.size() - 1; j >= 0; j--) {
                if (Intersector.overlaps(invaderbullets.get(j).getBoundingRectangle(), (p))) {
                    if(System.currentTimeMillis() - playerhit > 250) { //slight invincibility frames so you only take 1 damage if 3 bullets hit you at the same time
                        player.playerDamage();
                        playerhit = System.currentTimeMillis();
                        if(player.health > 0) { //only plays the hit sound effect for the first two hits
                            hitsound.play();
                        }
                    }
                    invaderbullets.remove(j);
                    break;
                }
            }
        }
        //checks if the player's health reaches 0
        if (player.playerisDead()) {
            death = true; //makes the player death screen display
            if(sound) {
                deadsound.play();
                sound = false; //makes the death sound only play once
            }
            backgroundsound.stop();
        }
        //draws background picture
        blackUI.draw(batch); //black bar behind the special gauge and bomb gauge
        blueUI.draw(batch); //blue bar for bombs
        greenUI.draw(batch); //green bar for special attack
        gameUI.draw(batch); //the background for the entire bottom of the screen that shows the player's bombs, special, and lives

        //shows the player's health is 2 when the player is hit
        if (player.health == 2) {
            oneLife.draw(batch);
        }
        //shows the player's health is 1 when the player is hit twice
        if (player.health == 1) {
            zeroLife.draw(batch);
        }

        //If player health is less than 1 then
        //death = true;

        //when space is not pressed it shows the starting screen and resets the timer that starts spawning laser enemies
        if (gameStart == false) {
            startGame.draw(batch);
            backgroundsound.stop();
            startlaser = System.currentTimeMillis();
        }
        //plays background music when the game starts
        if(gameStart) {
            backgroundsound.play();
        }


        leftTexture.draw(batch); //left part of the background
        rightTexture.draw(batch); //right part of the background

        //when the enemy bullet hits the player the death screen is shown
        if (death == true) {
            gameOver.draw(batch);
        }

        ui.draw(batch, "Score: " + score, 500, 820); //Shows the player's score that increases with each kill

        batch.end();
    }


    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.LEFT) {
            player.left = true;
        }
        if (i == Input.Keys.RIGHT) {
            player.right = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        if (i == Input.Keys.LEFT) {
            player.left = false;
        }
        if (i == Input.Keys.RIGHT) {
            player.right = false;
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

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void hide() {

    }
}
