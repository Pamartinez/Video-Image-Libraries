package androidx.media3.common.audio;

import A2.d;
import android.content.Context;
import android.media.AudioManager;
import android.os.Looper;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AudioManagerCompat {
    private static Context applicationContext;
    private static AudioManager audioManager;

    public static int abandonAudioFocusRequest(AudioManager audioManager2, AudioFocusRequestCompat audioFocusRequestCompat) {
        return audioManager2.abandonAudioFocusRequest(audioFocusRequestCompat.getAudioFocusRequest());
    }

    public static synchronized AudioManager getAudioManager(Context context) {
        synchronized (AudioManagerCompat.class) {
            try {
                Context applicationContext2 = context.getApplicationContext();
                if (applicationContext != applicationContext2) {
                    audioManager = null;
                }
                AudioManager audioManager2 = audioManager;
                if (audioManager2 != null) {
                    return audioManager2;
                }
                Looper myLooper = Looper.myLooper();
                if (myLooper != null) {
                    if (myLooper != Looper.getMainLooper()) {
                        ConditionVariable conditionVariable = new ConditionVariable();
                        BackgroundExecutor.get().execute(new d(25, applicationContext2, conditionVariable));
                        conditionVariable.blockUninterruptible();
                        AudioManager audioManager3 = (AudioManager) Assertions.checkNotNull(audioManager);
                        return audioManager3;
                    }
                }
                AudioManager audioManager4 = (AudioManager) applicationContext2.getSystemService("audio");
                audioManager = audioManager4;
                AudioManager audioManager5 = (AudioManager) Assertions.checkNotNull(audioManager4);
                return audioManager5;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static int getStreamMaxVolume(AudioManager audioManager2, int i2) {
        return audioManager2.getStreamMaxVolume(i2);
    }

    public static int getStreamMinVolume(AudioManager audioManager2, int i2) {
        return audioManager2.getStreamMinVolume(i2);
    }

    public static int getStreamVolume(AudioManager audioManager2, int i2) {
        try {
            return audioManager2.getStreamVolume(i2);
        } catch (RuntimeException e) {
            Log.w("AudioManagerCompat", "Could not retrieve stream volume for stream type " + i2, e);
            return audioManager2.getStreamMaxVolume(i2);
        }
    }

    public static boolean isStreamMute(AudioManager audioManager2, int i2) {
        return audioManager2.isStreamMute(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getAudioManager$0(Context context, ConditionVariable conditionVariable) {
        audioManager = (AudioManager) context.getSystemService("audio");
        conditionVariable.open();
    }

    public static int requestAudioFocus(AudioManager audioManager2, AudioFocusRequestCompat audioFocusRequestCompat) {
        return audioManager2.requestAudioFocus(audioFocusRequestCompat.getAudioFocusRequest());
    }
}
