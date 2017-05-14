package com.example.maxmamuta.proguli;

/**
 * Created by Maxim on 5/14/2017.
 */

public class GlobalCache {

    static GlobalCache singleton = null;

    public GlobalCache getInstance() {
        if (singleton == null) {
            singleton = new GlobalCache();
        }
        return singleton;
    }

    private GlobalCache() {

    }

}
