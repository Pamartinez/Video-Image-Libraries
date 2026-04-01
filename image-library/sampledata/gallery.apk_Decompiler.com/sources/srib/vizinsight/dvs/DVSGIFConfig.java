package srib.vizinsight.dvs;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DVSGIFConfig {
    private int currentIndex;
    private int fps;
    private int max_resolution;
    private String savePath;
    private int screenHeight;
    private int screenWidth;
    private int touchX;
    private int touchY;
    private Uri url;

    public DVSGIFConfig(int i2, int i7, int i8, int i10, int i11, Uri uri, String str, int i12, int i13) {
        this.max_resolution = i2;
        this.fps = i7;
        this.touchX = i8;
        this.touchY = i10;
        this.currentIndex = i11;
        this.url = uri;
        this.savePath = str;
        this.screenWidth = i12;
        this.screenHeight = i13;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public int getFps() {
        return this.fps;
    }

    public int getMax_resolution() {
        return this.max_resolution;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getTouchX() {
        return this.touchX;
    }

    public int getTouchY() {
        return this.touchY;
    }

    public Uri getUrl() {
        return this.url;
    }

    public DVSGIFConfig(int i2, int i7, int i8, int i10, int i11, Uri uri) {
        this.max_resolution = i2;
        this.fps = i7;
        this.touchX = i8;
        this.touchY = i10;
        this.currentIndex = i11;
        this.url = uri;
        this.savePath = "";
    }
}
