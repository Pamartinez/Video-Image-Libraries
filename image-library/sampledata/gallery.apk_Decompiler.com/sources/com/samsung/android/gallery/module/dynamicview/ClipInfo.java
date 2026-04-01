package com.samsung.android.gallery.module.dynamicview;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipInfo {
    public int clipEndMs;
    public int clipPosition;
    public int clipStartMs;
    public final ArrayList<PlaybackOption> playbackOptions;
    public int priority;
    public float score;
    public int typeId;

    public ClipInfo(int i2, int i7, int i8, float f, int i10, int i11, ArrayList<PlaybackOption> arrayList) {
        ArrayList<PlaybackOption> arrayList2 = new ArrayList<>();
        this.playbackOptions = arrayList2;
        this.clipPosition = i2;
        this.typeId = i7;
        this.priority = i8;
        this.score = f;
        this.clipStartMs = i10;
        this.clipEndMs = i11;
        arrayList2.addAll(arrayList);
    }

    public float getScore() {
        return this.score;
    }
}
