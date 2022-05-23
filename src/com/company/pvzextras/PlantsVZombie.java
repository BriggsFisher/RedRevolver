package com.company.pvzextras;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.company.plants.*;
import com.badlogic.gdx.math.Intersector;
import com.company.zombies.Bucket;
import com.company.zombies.Normalzombie;
import com.company.zombies.Pylon;
import com.company.zombies.Zombie;

import java.util.ArrayList;

public class PlantsVZombie implements ApplicationListener {

    long spawnskysunTime, spawnzombieTime, spawnpylonTime, spawnbucketTime, eatTime, eatTimeP, eatTimeB;
    long startSpawning = System.currentTimeMillis();
    long theTime = System.currentTimeMillis();
    int money = 200;
    int score = 0;
    int time = 0;
    ArrayList<Sun> suns;
    ArrayList<Pea> peas;
    ArrayList<Normalzombie> normalzombies;
    ArrayList<Pylon> pylons;
    ArrayList<Bucket> buckets;
    SpriteBatch batch;
    Texture sunflowerTexture, doublepeaTexture, sunTexture, peashooterTexture, walnutTexture, peaTexture, normalzombieTexture, pylonTexture, bucketTexture;
    ArrayList<Plant> plants;
    ArrayList<Zombie> zombies;
    Sprite frontyard, plantui;
    Plant selected = null;
    Plant selectedP = null;
    Plant selectedW = null;
    Plant selectedC = null;
    Zombie selectedZ = null;
    Zombie selectedY = null;
    Zombie selectedX = null;
    BitmapFont ui;
    Boolean carrying = false;
    Boolean overlapping = false;
    Boolean overlapping2 = false;
    Boolean overlapping3 = false;
    Boolean overlapping4 = false;
    Boolean moving = false;
    Boolean moving2 = false;
    Boolean moving3 = false;
    Boolean moving4 = false;
    Boolean moving5 = false;
    Boolean death = false;
    Boolean startclick = false;
    Sprite gameOver;
    Sprite start;
    Sprite lawnmower1, lawnmower2, lawnmower3, lawnmower4, lawnmower5;
    Music sound;

    @Override
    public void create() {
        lawnmower1 = new Sprite(new Texture("Assets/Lawnmower.png"));
        batch = new SpriteBatch();
        suns = new ArrayList<>();
        peas = new ArrayList<>();
        normalzombies = new ArrayList<>();
        pylons = new ArrayList<>();
        buckets = new ArrayList<>();
        plants = new ArrayList<>();
        zombies = new ArrayList<>();
        peaTexture = new Texture("assets/pea2 copy.png");
        sunflowerTexture = new Texture("assets/Sunflower.png");
        doublepeaTexture = new Texture("assets/Double copy.png");
        gameOver = new Sprite(new Texture("Assets/Death3.jpg"));
        start = new Sprite(new Texture("Assets/Start.png"));
        lawnmower2 = new Sprite(new Texture("Assets/Lawnmower.png"));
        normalzombieTexture = new Texture("assets/Zombie copy.png");
        pylonTexture = new Texture("assets/Pylon copy.png");
        bucketTexture = new Texture("assets/Bucket copy.png");
        lawnmower3 = new Sprite(new Texture("Assets/Lawnmower.png"));
        frontyard = new Sprite(new Texture("assets/FrontYard.jpg"));
        plantui = new Sprite(new Texture("assets/UIFINAL.png"));
        sunTexture = (new Texture("assets/Money.png"));
        lawnmower4 = new Sprite(new Texture("Assets/Lawnmower.png"));
        peashooterTexture = new Texture("assets/Peashooter.png");
        walnutTexture = new Texture("assets/Wallnut.png");
        lawnmower5 = new Sprite(new Texture("Assets/Lawnmower.png"));
        ui = new BitmapFont();
        lawnmower1.setPosition(200, 0);
        lawnmower2.setPosition(200, 100);
        lawnmower3.setPosition(200, 200);
        lawnmower4.setPosition(200, 300);
        lawnmower5.setPosition(200, 400);
        //background music
        sound = Gdx.audio.newMusic(Gdx.files.internal("Music/PVZ music.mp3"));
        sound.setLooping(true);
        sound.play();


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

//when you click the start screen goes away
        if (startclick == false && Gdx.input.isButtonPressed(0)) {
            startSpawning = System.currentTimeMillis(); //makes sure the zombies still wait 20 or so seconds to start spawning
            startclick = true;
        }

        //moves each lawnmower sprite forwards if a zombie has touched it
        if (moving) {
            lawnmower1.translateX(3);
        }
        if (moving2) {
            lawnmower2.translateX(3);
        }
        if (moving3) {
            lawnmower3.translateX(3);
        }
        if (moving4) {
            lawnmower4.translateX(3);
        }
        if (moving5) {
            lawnmower5.translateX(3);
        }

//adds to time variable which controls the zombie spawning so it increases in difficulty over time
        if (System.currentTimeMillis() - theTime > 3000 && startclick == true) {
            theTime = System.currentTimeMillis();
            time = time + 1;
        }

//variables are set for the mouse's x and y
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();

        //sets the position of the plant cards and sun icon to the top left
        plantui.setPosition(0, Gdx.graphics.getHeight() - plantui.getHeight());

        //checks if any of the plants are on the mouse
        if (selected != null | selectedP != null | selectedW != null | selectedC != null) {
            carrying = true;
        }
        //checks if there are no plants on mouse
        if (selected == null && selectedP == null && selectedW == null && selectedC == null) {
            carrying = false;
        }

        //Buying Sunflower only when not holding a plant and no sunflowers are made and you have enough money and its on the grass
        if (Gdx.input.isButtonPressed(0) && mx >= 138 && mx <= 185 && my >= 500 && selected == null && money >= 50 && carrying == false) {
            selected = new Sunflower(sunflowerTexture);
            money = money - selected.getCost();
        }
        if (!Gdx.input.isButtonPressed(0) && selected != null && overlapping == false) {
            //let go of a plant need to snap to grid and add to array

            //makes sure you clicked on the area of the card for this plant then releases it on grid
            if (mx > 264 && mx < 940 && my < 500) {
                selected.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
                plants.add(selected);
                selected = null;
            }
        }

        //Buying Double Peashooter only when not holding a plant and no peashooters are made and you have enough money and its on the grass
        if (Gdx.input.isButtonPressed(0) && mx >= 190 && mx <= 240 && my >= 500 && selectedC == null && money >= 200 && carrying == false) {
            selectedC = new Doublepea(doublepeaTexture);
            money = money - selectedC.getCost();
        }
        if (!Gdx.input.isButtonPressed(0) && selectedC != null && overlapping4 == false) {
            //let go of a plant need to snap to grid and add to array

            //makes sure you clicked on the area of the card for this plant then releases it on grid
            if (mx > 264 && mx < 940 && my < 500) {
                selectedC.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
                plants.add(selectedC);
                selectedC = null;
            }
        }


        //Peashooter
        if (Gdx.input.isButtonPressed(0) && mx >= 80 && mx <= 128 && my >= 500 && selectedP == null && money >= 100 && carrying == false) {
            selectedP = new Peashooter(peashooterTexture);
            money = money - selectedP.getCost();
        }
        if (!Gdx.input.isButtonPressed(0) && selectedP != null && overlapping2 == false) {
            // let go of a plant, need to snap to grid and add to array

            if (mx > 264 && mx < 940 && my < 500) {
                selectedP.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
                plants.add(selectedP);
                selectedP = null;
            }
        }

        //Walnut
        if (Gdx.input.isButtonPressed(0) && mx >= 251 && mx <= 298 && my >= 500 && selectedW == null && money >= 50 && carrying == false) {
            selectedW = new Walnut(walnutTexture);
            money = money - selectedW.getCost();
        }
        if (!Gdx.input.isButtonPressed(0) && selectedW != null && overlapping3 == false) {
            // let go of a plant, need to snap to grid and add to array

            if (mx > 264 && mx < 940 && my < 500) {
                selectedW.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
                plants.add(selectedW);
                selectedW = null;
            }
        }


        batch.begin();
        frontyard.draw(batch);
        plantui.draw(batch);
        //shows your money in the top left
        ui.draw(batch, "" + money, 25, 512);

        //checks if it should spawn suns in the sky and sets to random position at the top only if its the moveable sun
        if (shouldspawnskySun()) {
            Sun temp2 = new Sun(sunTexture);
            temp2.setPosition((float) (Math.random() * (Gdx.graphics.getWidth() - temp2.getWidth())), Gdx.graphics.getHeight()); //the - temp2.getWidth() makes sure the sun isnt on the far right of the screen
            temp2.setMovable(true);
            suns.add(temp2);
        }
//checks to spawn zombie and sets to a random number being either 0, 100, 200, 300, or 400
        if (shouldspawnZombie()) {
            Normalzombie temp4 = new Normalzombie(normalzombieTexture);
            int k = (int) (Math.random() * 5);
            k = k * 100;
            temp4.setPosition(Gdx.graphics.getWidth(), k);
            normalzombies.add(temp4);
        }
        //checks to spawn pylon zombie
        if (shouldspawnPylon()) {
            Pylon temp5 = new Pylon(pylonTexture);
            int l = (int) (Math.random() * 5);
            l = l * 100;
            temp5.setPosition(Gdx.graphics.getWidth(), l);
            pylons.add(temp5);
        }
        //checks to spawn bucket zombie
        if (shouldspawnBucket()) {
            Bucket temp6 = new Bucket(bucketTexture);
            int o = (int) (Math.random() * 5);
            o = o * 100;
            temp6.setPosition(Gdx.graphics.getWidth(), o);
            buckets.add(temp6);
        }

//draws plants
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            p.draw(batch);

//checks if sunflowers should spawn suns
            if (p instanceof Sunflower) {
                Sunflower f = (Sunflower) p;
                if (f.shouldspawnSun()) {
                    Sun temp = new Sun(sunTexture);
                    temp.setPosition(p.getX() + ((int) (Math.random() * p.getWidth()) - (temp.getWidth() / 2)), p.getY() + ((int) (Math.random() * p.getHeight())) - (temp.getHeight() / 2));
                    suns.add(temp);
                }
            }
            //checks if peashooter should spawn peas
            if (p instanceof Peashooter && ((Peashooter) p).shouldspawnBullet()) {
                Pea ps = new Pea(peaTexture);
                //makes sure only 1 pea is shot at a time
                boolean fired = false;
                ps.setPosition(p.getX() + p.getWidth() - 20, p.getY() + p.getHeight() - 50);
                //shoots peas at normal zombies
                for (int j = 0; j < normalzombies.size(); j++) {
                    Normalzombie z = normalzombies.get(j);
                    //checks if zombie has the same Y and is a higher X
                    if (z.getY() == p.getY() && p.getX() < z.getX()) {
                        if (!fired) {
                            peas.add(ps);
                            fired = true;
                        }
                        break;
                    }
                }
                //shoots peas at pylon zombies
                for (int k = 0; k < pylons.size(); k++) {
                    Pylon r = pylons.get(k);
                    if (r.getY() == p.getY() && p.getX() < r.getX()) {
                        if (!fired) {
                            peas.add(ps);
                            fired = true;
                        }
                        break;
                    }
                }
                //shoots peas at bucket zombies
                for (int o = 0; o < buckets.size(); o++) {
                    Bucket c = buckets.get(o);
                    if (c.getY() == p.getY() && p.getX() < c.getX()) {
                        if (!fired) {
                            peas.add(ps);
                            fired = true;
                        }
                        break;
                    }
                }
            }
            //checks if double peashooter should spawn peas
            if (p instanceof Doublepea && ((Doublepea) p).shouldspawndoublepeaBullet()) {
                Pea cs = new Pea(peaTexture);
                //makes sure only 1 pea is shot at a time
                boolean fired = false;
                cs.setPosition(p.getX() + p.getWidth() + - 20, p.getY() + p.getHeight() - 50);
                //shoots peas at normal zombies
                for (int j = 0; j < normalzombies.size(); j++) {
                    Normalzombie z = normalzombies.get(j);
                    //checks if zombie has the same Y and is a higher X
                    if (z.getY() == p.getY() && p.getX() < z.getX()) {
                        if (!fired) {
                            peas.add(cs);
                            fired = true;
                        }
                        break;
                    }
                }
                //shoots peas at pylon zombies
                for (int k = 0; k < pylons.size(); k++) {
                    Pylon r = pylons.get(k);
                    if (r.getY() == p.getY() && p.getX() < r.getX()) {
                        if (!fired) {
                            peas.add(cs);
                            fired = true;
                        }
                        break;
                    }
                }
                //shoots peas at bucket zombies
                for (int o = 0; o < buckets.size(); o++) {
                    Bucket c = buckets.get(o);
                    if (c.getY() == p.getY() && p.getX() < c.getX()) {
                        if (!fired) {
                            peas.add(cs);
                            fired = true;
                        }
                        break;
                    }
                }




            }
        }
        //draws zombies
        for (Zombie z : zombies) {
            z.draw(batch);
        }
        //draws normal zombies
        for (int i = normalzombies.size() - 1; i >= 0; i--) {
            Normalzombie u = normalzombies.get(i);
            u.moveZombie();
            if (u.getX() < 0) {
                death = true;
            }
            u.draw(batch);
        }
        //draws pylon zombies
        for (int n = pylons.size() - 1; n >= 0; n--) {
            Pylon t = pylons.get(n);
            t.moveZombie();
            if (t.getX() < 0) {
                death = true;
            }
            t.draw(batch);
        }
        //draws bucket zombies
        for (int b = buckets.size() - 1; b >= 0; b--) {
            Bucket s = buckets.get(b);
            s.moveZombie();
            if (s.getX() < 0) {
                death = true;
            }
            s.draw(batch);
        }

        //draws peas and removes old peas so the game doesnt lag
        for (int u = peas.size() - 1; u >= 0; --u) {
            Pea o = peas.get(u);
            if (o.isExpired()) {
                peas.remove(u);
            } else {
                o.moveBullet();
                o.draw(batch);
            }

        }


//normalzombies stop at plants
        for (int i = 0; i < normalzombies.size(); i++) {
            Normalzombie z = normalzombies.get(i);
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(z.getBoundingRectangle(), p.getBoundingRectangle())) {
                    z.translateX((float) 0.5);
                    //eatTime is how long it takes to remove health from the plant
                    if (System.currentTimeMillis() - eatTime > 500) {
                        //method in plant that removes health
                        p.eat();
                        eatTime = System.currentTimeMillis();
                    }
                }
            }
        }
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            //method in plant that checks if plant has less than 1 health
            if (p.isDead()) {
                plants.remove(p);
            }

        }

        //pylonzombies stop at plants
        for (int i = 0; i < pylons.size(); i++) {
            Pylon z = pylons.get(i);
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(z.getBoundingRectangle(), p.getBoundingRectangle())) {
                    z.translateX((float) 0.5);
                    if (System.currentTimeMillis() - eatTimeP > 500) {
                        p.eat();
                        eatTimeP = System.currentTimeMillis();
                    }
                }
            }
        }

        //bucketzombies stop at plants
        for (int i = 0; i < buckets.size(); i++) {
            Bucket z = buckets.get(i);
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(z.getBoundingRectangle(), p.getBoundingRectangle())) {
                    z.translateX((float) 0.5);
                    if (System.currentTimeMillis() - eatTimeB > 500) {
                        p.eat();
                        eatTimeB = System.currentTimeMillis();
                    }
                }
            }
        }

        //kill normal zombies with lawnmower
        //lawnmower 1
        for (int i = 0; i < normalzombies.size(); i++) {
            if (Intersector.overlaps(lawnmower1.getBoundingRectangle(), normalzombies.get(i).getBoundingRectangle())) {
                normalzombies.remove(i);
                lawnmower1.translateX(5);
                moving = true;
            }
        }

        //lawnmower 2
        for (int i = 0; i < normalzombies.size(); i++) {
            if (Intersector.overlaps(lawnmower2.getBoundingRectangle(), normalzombies.get(i).getBoundingRectangle())) {
                normalzombies.remove(i);
                lawnmower2.translateX(5);
                moving2 = true;
            }
        }

        //lawnmower 3
        for (int i = 0; i < normalzombies.size(); i++) {
            if (Intersector.overlaps(lawnmower3.getBoundingRectangle(), normalzombies.get(i).getBoundingRectangle())) {
                normalzombies.remove(i);
                lawnmower3.translateX(5);
                moving3 = true;
            }
        }

        //lawnmower 4
        for (int i = 0; i < normalzombies.size(); i++) {
            if (Intersector.overlaps(lawnmower4.getBoundingRectangle(), normalzombies.get(i).getBoundingRectangle())) {
                normalzombies.remove(i);
                lawnmower4.translateX(5);
                moving4 = true;
            }
        }

        //lawnmower 5
        for (int i = 0; i < normalzombies.size(); i++) {
            if (Intersector.overlaps(lawnmower5.getBoundingRectangle(), normalzombies.get(i).getBoundingRectangle())) {
                normalzombies.remove(i);
                lawnmower5.translateX(5);
                moving5 = true;
            }
        }


//kill pylon zombies with lawnmower

        //1
        for (int i = 0; i < pylons.size(); i++) {
            if (Intersector.overlaps(lawnmower1.getBoundingRectangle(), pylons.get(i).getBoundingRectangle())) {
                pylons.remove(i);
                lawnmower1.translateX(5);
                moving = true;
            }
        }

        //2
        for (int i = 0; i < pylons.size(); i++) {
            if (Intersector.overlaps(lawnmower2.getBoundingRectangle(), pylons.get(i).getBoundingRectangle())) {
                pylons.remove(i);
                lawnmower2.translateX(5);
                moving2 = true;
            }
        }

        //3
        for (int i = 0; i < pylons.size(); i++) {
            if (Intersector.overlaps(lawnmower3.getBoundingRectangle(), pylons.get(i).getBoundingRectangle())) {
                pylons.remove(i);
                lawnmower3.translateX(5);
                moving3 = true;
            }
        }

        //4
        for (int i = 0; i < pylons.size(); i++) {
            if (Intersector.overlaps(lawnmower4.getBoundingRectangle(), pylons.get(i).getBoundingRectangle())) {
                pylons.remove(i);
                lawnmower4.translateX(5);
                moving4 = true;
            }
        }

        //5
        for (int i = 0; i < pylons.size(); i++) {
            if (Intersector.overlaps(lawnmower5.getBoundingRectangle(), pylons.get(i).getBoundingRectangle())) {
                pylons.remove(i);
                lawnmower5.translateX(5);
                moving5 = true;
            }
        }


//kill bucket zombies with lawnmower

        //1
        for (int i = 0; i < buckets.size(); i++) {
            if (Intersector.overlaps(lawnmower1.getBoundingRectangle(), buckets.get(i).getBoundingRectangle())) {
                buckets.remove(i);
                lawnmower1.translateX(5);
                moving = true;
            }
        }

        //2
        for (int i = 0; i < buckets.size(); i++) {
            if (Intersector.overlaps(lawnmower2.getBoundingRectangle(), buckets.get(i).getBoundingRectangle())) {
                buckets.remove(i);
                lawnmower2.translateX(5);
                moving2 = true;
            }
        }

        //3
        for (int i = 0; i < buckets.size(); i++) {
            if (Intersector.overlaps(lawnmower3.getBoundingRectangle(), buckets.get(i).getBoundingRectangle())) {
                buckets.remove(i);
                lawnmower3.translateX(5);
                moving3 = true;
            }
        }

        //4
        for (int i = 0; i < buckets.size(); i++) {
            if (Intersector.overlaps(lawnmower4.getBoundingRectangle(), buckets.get(i).getBoundingRectangle())) {
                buckets.remove(i);
                lawnmower4.translateX(5);
                moving4 = true;
            }
        }

        //5
        for (int i = 0; i < buckets.size(); i++) {
            if (Intersector.overlaps(lawnmower5.getBoundingRectangle(), buckets.get(i).getBoundingRectangle())) {
                buckets.remove(i);
                lawnmower5.translateX(5);
                moving5 = true;
            }
        }

//if pea overlaps normalzombie
        for (int u = peas.size() - 1; u >= 0; --u) {
            //checks if pea is already deleted
            boolean peaDead = false;
            for (int f = normalzombies.size() - 1; f >= 0; f--) {
                if (!peaDead && Intersector.overlaps(peas.get(u).getBoundingRectangle(), normalzombies.get(f).getBoundingRectangle())) {
                    peas.remove(u);
                    peaDead = true;
                    selectedZ = normalzombies.get(f);
                    selectedZ.setHealth(selectedZ.getHealth() - 1);
                    if (selectedZ.getHealth() < 1) {
                        normalzombies.remove(f);
                        if(death == false) {
                            score = score + 1;
                            //adds score to score in top right
                            break;
                        }
                    }
                }
            }
        }

        //if pea overlaps pylon
        for (int u = peas.size() - 1; u >= 0; u--) {
            boolean peaDead = false;
            for (int l = pylons.size() - 1; l >= 0; l--) {
                if (!peaDead && Intersector.overlaps(peas.get(u).getBoundingRectangle(), pylons.get(l).getBoundingRectangle())) {
                    peas.remove(u);
                    peaDead = true;
                    selectedY = pylons.get(l);
                    selectedY.setHealth(selectedY.getHealth() - 1);
                    if (selectedY.getHealth() < 1) {
                        pylons.remove(l);
                        if(death == false) {
                            score = score + 1;
                            break;
                        }
                    }
                }
            }
        }

        //if pea overlaps bucket
        for (int u = peas.size() - 1; u >= 0; u--) {
            boolean peaDead = false;
            for (int j = buckets.size() - 1; j >= 0; j--) {
                if (!peaDead && Intersector.overlaps(peas.get(u).getBoundingRectangle(), buckets.get(j).getBoundingRectangle())) {
                    peas.remove(u);
                    peaDead = true;
                    selectedX = buckets.get(j);
                    selectedX.setHealth(selectedX.getHealth() - 1);
                    if (selectedX.getHealth() < 1) {
                        buckets.remove(j);
                        if (death == false) {
                            score = score + 1;
                            break;
                        }
                    }
                }
            }
        }

//makes it so you cant place a peashooter on any plant
        overlapping2 = false;
        if (selectedP != null) {
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(selectedP.getBoundingRectangle(), p.getBoundingRectangle())) {
                    overlapping2 = true;
                }

            }
        }

        //makes it so you cant place a sunflower on any plant
        overlapping = false;
        if (selected != null) {
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(selected.getBoundingRectangle(), p.getBoundingRectangle())) {
                    overlapping = true;
                }

            }
        }

        //makes it so you cant place a walnut on any plant
        overlapping3 = false;
        if (selectedW != null) {
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(selectedW.getBoundingRectangle(), p.getBoundingRectangle())) {
                    overlapping3 = true;
                }

            }
        }

        //makes it so you cant place a double peashooter on any plant
        overlapping4 = false;
        if (selectedC != null) {
            for (int j = 0; j < plants.size(); j++) {
                Plant p = plants.get(j);
                if (Intersector.overlaps(selectedC.getBoundingRectangle(), p.getBoundingRectangle())) {
                    overlapping4 = true;
                }

            }
        }

        //makes it so when you are holding the plant it will lock to the nearest square
        //sunflower
        if (selected != null) {
            selected.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
            selected.draw(batch);
        }

        //peashooter
        if (selectedP != null) {
            selectedP.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
            selectedP.draw(batch);
        }
        //walnut
        if (selectedW != null) {
            selectedW.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
            selectedW.draw(batch);
        }
        //double peashooter
        if (selectedC != null) {
            selectedC.setPosition(((int) (mx / 79) * 79) + 27, (int) (my / 100) * 100);
            selectedC.draw(batch);
        }

        //draws lawnmowers
        lawnmower1.draw(batch);
        lawnmower2.draw(batch);
        lawnmower3.draw(batch);
        lawnmower4.draw(batch);
        lawnmower5.draw(batch);

        //draws suns and allows you to click on them to remove them and get money
        for (int i = suns.size() - 1; i >= 0; i--) {
            Sun s = suns.get(i);
            if (s.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.isButtonPressed(0)) {
                suns.remove(i);
                money = money + 25;
            }
            s.moveDown();
            s.draw(batch);
        }

//checks if you click and if not the start screen shows
        if (startclick == false) {
            start.draw(batch);
        }

        //if you die the death screen is drawn
        if (death == true) {
            gameOver.draw(batch);
            //stops music
            sound.pause();
        }

        //draws your score in top right
        ui.draw(batch, "Score " + score, Gdx.graphics.getWidth() - 65, Gdx.graphics.getHeight() - 10);

        batch.end();
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
//makes it take 15000 miliseconds to spawn suns in the sky
    public boolean shouldspawnskySun() {
        if (System.currentTimeMillis() - spawnskysunTime > 15000 && startclick == true) { //waits for game to start
            spawnskysunTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    //makes it take 10000 miliseconds to spawn zombies after a 20000 starting grace period
    public boolean shouldspawnZombie() {
        if (System.currentTimeMillis() - startSpawning > 20000 && startclick == true) {
            if (System.currentTimeMillis() - spawnzombieTime > 10000 - ((int) (time / 10) * 1000)) {
                spawnzombieTime = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }
//time for pylon zombies and only starts spawns after the time variable has added up to greater than 15
    public boolean shouldspawnPylon() {
        if (System.currentTimeMillis() - spawnpylonTime > 15000 - ((int) (time / 10) * 1000) && time > 15 && startclick == true) {
            spawnpylonTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
//time for bucket zombies and only after time has added up to greater than 30
    public boolean shouldspawnBucket() {
        if (System.currentTimeMillis() - spawnbucketTime > 20000 - ((int) (time / 10) * 1000) && time > 30 && startclick == true) {
            spawnbucketTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }



}