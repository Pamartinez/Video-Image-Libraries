package com.samsung.android.gallery.support.library.v12;

import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.samsung.android.app.SemDualAppManager;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.library.v11.Sem125ApiCompatImpl;
import com.samsung.android.gallery.support.library.v12.media.Sec130BgmVideoPlayerCompat;
import com.samsung.android.gallery.support.library.v12.media.Sem130MediaCaptureCompat;
import i.C0212a;
import java.io.File;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem130ApiCompatImpl extends Sem125ApiCompatImpl {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VoldApi {
        static final Method copyApi;
        static final Method moveApi;
        static volatile StorageManager storageManager;
        static final boolean support;

        static {
            boolean z;
            Class<String> cls = String.class;
            Class<StorageManager> cls2 = StorageManager.class;
            Method method = Reflector.getMethod(cls2, "mvFileAtData", cls, cls);
            moveApi = method;
            Method method2 = Reflector.getMethod(cls2, "cpFileAtData", cls, cls);
            copyApi = method2;
            if (method == null || method2 == null) {
                z = false;
            } else {
                z = true;
            }
            support = z;
        }

        public static boolean copy(Context context, String str, String str2) {
            if (!support) {
                return false;
            }
            try {
                if (storageManager == null) {
                    storageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
                }
                return ((Boolean) copyApi.invoke(storageManager, new Object[]{str, str2})).booleanValue();
            } catch (Error | Exception e) {
                C0212a.y(e, new StringBuilder("Sem130ApiCompatImpl#copy failed. e="), "VoldApi");
                return false;
            }
        }

        public static boolean move(Context context, String str, String str2) {
            if (support) {
                try {
                    if (storageManager == null) {
                        storageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
                    }
                    Method method = moveApi;
                    if (method == null || !((Boolean) method.invoke(storageManager, new Object[]{str, str2})).booleanValue()) {
                        return false;
                    }
                    return true;
                } catch (Error | Exception e) {
                    C0212a.y(e, new StringBuilder("Sem130ApiCompatImpl#move failed. e="), "VoldApi");
                }
            }
            return false;
        }
    }

    private void playHapticSoundTogether(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.playSoundEffect(106);
        }
    }

    public boolean copyOnVold(Context context, String str, String str2) {
        return VoldApi.copy(context, str, str2);
    }

    public MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return new Sec130BgmVideoPlayerCompat(i2);
    }

    public int getDualAppProfileId() {
        try {
            return SemDualAppManager.getDualAppProfileId();
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getDualAppProfileId failed. e="), this.TAG);
            return -1;
        }
    }

    public MediaCaptureCompat getMediaCaptureCompat() {
        return new Sem130MediaCaptureCompat();
    }

    public int getPinnedEdgeWidth(Context context) {
        return 0;
    }

    public Bitmap getVideoFrameAtTime(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return mediaMetadataRetriever.getFrameAtTime(j2, i2, new MediaMetadataRetriever.BitmapParams());
    }

    public boolean isClipboardShowing(Context context) {
        return false;
    }

    public boolean isLeftPinnedEdge(Context context) {
        return false;
    }

    public boolean isPinEdgeEnabled(Context context) {
        return false;
    }

    public boolean isReducedTransparency(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), "accessibility_reduce_transparency", 0) == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            j.C(e, new StringBuilder("isReducedTransparency failed e="), this.TAG);
            return false;
        }
    }

    public boolean moveOnVold(Context context, String str, String str2) {
        return VoldApi.move(context, str, str2);
    }

    public void performHapticFeedback(Context context, int i2) {
        if (i2 == 14) {
            playHapticSoundTogether(context);
        }
        super.performHapticFeedback(context, i2);
    }

    public boolean touchOnVold(Context context, String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists() || !moveOnVold(context, str, str)) {
            return false;
        }
        return true;
    }

    public void showClipboardDialog(Context context) {
    }
}
