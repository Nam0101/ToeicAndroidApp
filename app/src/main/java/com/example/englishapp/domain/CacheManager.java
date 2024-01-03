package com.example.englishapp.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CacheManager {
    private static final String MP3_CACHE_DIR = "mp3_cache";
    private static final String IMAGE_CACHE_DIR = "image_cache";
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static File getMp3CacheFile(Context context, String url) {
        File cacheDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), MP3_CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        Log.i("CacheManager", "getMp3CacheFile: " + url);
        return new File(cacheDir, url.hashCode() + ".mp3");
    }

    public static File getImageCacheFile(Context context, String url) {
        File cacheDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), IMAGE_CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        String extension = url.substring(url.lastIndexOf("."));
        return new File(cacheDir, url.hashCode() + extension);
    }

    public static Future<File> cacheMp3File(Context context, String url) {
        Log.i("CacheManager", "cacheMp3File: " + url);
        return cacheFile(context, url, getMp3CacheFile(context, url));
    }

    public static Future<File> cacheImageFile(Context context, String url) {
        Log.i("CacheManager", "cacheImageFile: " + url);
        return cacheFile(context, url, getImageCacheFile(context, url));
    }

    private static Future<File> cacheFile(Context context, String url, File cacheFile) {
        if (!cacheFile.exists()) {
            Log.i("CacheManager", "cacheFile: " + url);
            return executorService.submit(() -> {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    InputStream input = connection.getInputStream();
                    FileOutputStream output = new FileOutputStream(cacheFile);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.close();
                    input.close();
                    return cacheFile;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            });
        } else {
            return CompletableFuture.completedFuture(cacheFile);
        }
    }
}