package com.company.LevelsExample;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;

import java.io.IOException;

public class LevelGenerator {
    World world;
    Gson gson;

    public LevelGenerator(World world) {
        this.world = world;
        gson = new Gson();
    }

    public LevelDef loadLevel(int level) {
        FileHandle levelFile = Gdx.files.local("assets/levels/Level"+level+".json");

        // read level data from a level.json file and use Gson to convert to a POJO
        String jsonData = levelFile.readString(null);
        System.out.println(jsonData);
        LevelDef def = gson.fromJson(jsonData, LevelDef.class);
        return def;

    }

    public void saveLevel(int level) {
        FileHandle levelFile = Gdx.files.local("assets/levels/Level"+level+".json");

        // writing to a json and save to a file
        if(!levelFile.exists()) {
            try {
                levelFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        levelFile.writeString(gson.toJson(new LevelDef()), false);
    }
}