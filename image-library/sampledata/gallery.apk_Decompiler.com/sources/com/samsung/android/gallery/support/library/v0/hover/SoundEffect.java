package com.samsung.android.gallery.support.library.v0.hover;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SoundEffect {
    public static void play(Context context, int i2) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.playSoundEffect(i2);
            return;
        }
        Log.e("SoundEffect", "play " + i2 + " failed. null audio manager");
    }
}
