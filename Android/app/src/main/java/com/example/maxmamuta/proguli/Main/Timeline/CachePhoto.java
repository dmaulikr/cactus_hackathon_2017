package com.example.maxmamuta.proguli.Main.Timeline;

import android.content.Context;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Maxim on 5/13/2017.
 */

public class CachePhoto {

    private static CachePhoto singleton = null;
    private byte[] cachePhoto;

    public static CachePhoto getInstance() {
        if (singleton == null)
            singleton = new CachePhoto();
        return singleton;
    }

    private CachePhoto() {
        cachePhoto = new byte[0];
    }

    public void setPhotoCache(byte [] cache) {
        cachePhoto = cache;
    }

    public byte[] getPhotoCache() {
        return cachePhoto;
    }
}
