package com.samsung.android.sdk.scs.ai.texttospeech;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SynthesizeProgressListener {
    public abstract void onDone(String str);

    public abstract void onError(String str, int i2);

    public abstract void onProgress(String str, byte[] bArr, int i2, int i7);

    public abstract void onRangeStart(String str, int i2, int i7, int i8);

    public abstract void onStart(String str, int i2, int i7, int i8);
}
