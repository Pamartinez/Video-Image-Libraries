package com.samsung.android.gallery.support.library.v11;

import N2.j;
import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.abstraction.VideoTranscoderCompat;
import com.samsung.android.gallery.support.library.v0.display.SemDisplayManagerCompat;
import com.samsung.android.gallery.support.library.v11.display.SemDisplayManagerCompat120;
import com.samsung.android.gallery.support.library.v11.media.Sec120BgmVideoPlayerCompat;
import com.samsung.android.gallery.support.library.v11.media.Sem120MediaCaptureCompat;
import com.samsung.android.gallery.support.library.v11.media.SemVideoTranscoderCompat120;
import com.samsung.android.gallery.support.library.v11.system.SemBoosterCompat120;
import com.samsung.android.gallery.support.library.v9.Sem115ApiCompatImpl;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem120ApiCompatImpl extends Sem115ApiCompatImpl {
    public SemDisplayManagerCompat createDisplayManagerCompat(Context context) {
        return new SemDisplayManagerCompat120(context);
    }

    public MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return new Sec120BgmVideoPlayerCompat(i2);
    }

    public VideoTranscoderCompat createVideoTranscoderCompat() {
        return new SemVideoTranscoderCompat120();
    }

    public BoosterCompat getBoosterCompat(Context context) {
        return SemBoosterCompat120.getInstance(context);
    }

    public MediaCaptureCompat getMediaCaptureCompat() {
        return new Sem120MediaCaptureCompat();
    }

    public boolean isFolded(Activity activity) {
        if (activity != null) {
            try {
                if (activity.getDisplay().getDisplayId() == 1) {
                    return true;
                }
            } catch (NullPointerException unused) {
            }
        }
        return false;
    }

    public boolean isUpsm(Context context) {
        return false;
    }

    public boolean isVoiceServiceEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.semIsScreenReaderEnabled()) {
            return false;
        }
        return true;
    }

    public boolean setAutoBrightnessLimit(Context context, int i2, int i7) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            PowerManager powerManagerService = getPowerManagerService(context);
            if (powerManagerService == null) {
                return false;
            }
            powerManagerService.semSetAutoBrightnessLimit(((float) i2) / 255.0f, ((float) i7) / 255.0f);
            String str = this.TAG;
            Log.i(str, "setAutoBrightnessLimit {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            return true;
        } catch (Exception e) {
            j.D(e, new StringBuilder("setAutoBrightnessLimit failed. e="), this.TAG);
            return false;
        }
    }
}
