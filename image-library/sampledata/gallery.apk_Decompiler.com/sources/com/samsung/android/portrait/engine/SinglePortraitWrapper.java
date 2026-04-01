package com.samsung.android.portrait.engine;

import android.util.Log;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SinglePortraitWrapper {
    static {
        Log.i("SinglePortraitWrapper", "Load libPortraitSolution.camera.samsung E");
        System.loadLibrary("PortraitSolution.camera.samsung");
        Log.i("SinglePortraitWrapper", "Load libPortraitSolution.camera.samsung X");
    }

    private native int deinit();

    private native int getMap(int i2, ByteBuffer byteBuffer);

    private native int getMapSize(int i2);

    private native int getMeta(int i2, ByteBuffer byteBuffer);

    private native int getMetaSize(int i2);

    private native int getprop(int i2);

    private native int init(int i2);

    private native int run();

    private native int setimage(ByteBuffer byteBuffer, int i2, int i7, int i8, int i10, int i11, ByteBuffer byteBuffer2, int i12, int i13, int i14, int i15, int i16);

    private native int setprop(int i2, int i7);

    public final int a(int i2) {
        return init(i2);
    }

    public final int b() {
        return run();
    }

    public final int c() {
        return deinit();
    }

    public final void d(ByteBuffer byteBuffer, int i2, int i7, int i8, int i10) {
        setimage(byteBuffer, i2, i7, i8, i10, 2, (ByteBuffer) null, 0, 0, 0, 0, 0);
    }

    public final void e(int i2) {
        setprop(1004, i2);
    }
}
