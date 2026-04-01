package com.samsung.android.gallery.support.library.v12.media;

import A.a;
import N2.j;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec160MediaPlayerCompat extends Sec150MediaPlayerCompat {
    private boolean isHdr;

    public Sec160MediaPlayerCompat(int i2) {
        super(i2);
    }

    public void beginInstantSlowMoPlay() {
        try {
            this.mSemMediaPlayer.setVideoFrc(4, 0.25f, false);
        } catch (Exception e) {
            String str = this.TAG;
            Log.w(str, "beginInstantSlowMoPlay failed. e=" + e.getMessage());
            setSpeedWithPlaybackParam(0.25f);
        }
    }

    public void endInstantSlowMoPlay() {
        try {
            this.mSemMediaPlayer.setVideoFrc(1, 1.0f, false);
        } catch (Exception e) {
            String str = this.TAG;
            Log.w(str, "endInstantSlowMoPlay failed. e=" + e.getMessage());
            setSpeedWithPlaybackParam(1.0f);
        }
    }

    public Bitmap getCurrentFrame(int i2, int i7) {
        try {
            System.currentTimeMillis();
            if (this.isHdr) {
                return this.mSemMediaPlayer.getCurrentFrame(i2, i7, 1);
            }
            return this.mSemMediaPlayer.getCurrentFrame(i2, i7);
        } catch (NoSuchMethodError e) {
            if (this.isHdr) {
                String str = this.TAG;
                StringBuilder h5 = a.h(i2, i7, "getCurrentFrame for HDR {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} failed. e=");
                h5.append(e.getMessage());
                Log.w(str, h5.toString());
                return super.getCurrentFrame(i2, i7);
            }
            String str2 = this.TAG;
            StringBuilder h6 = a.h(i2, i7, "getCurrentFrame {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} failed. e=");
            h6.append(e.getMessage());
            Log.e(str2, h6.toString());
            return null;
        } catch (Error | Exception e7) {
            C0212a.y(e7, a.h(i2, i7, "getCurrentFrame {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} failed. e="), this.TAG);
            return null;
        }
    }

    public Bitmap getCurrentFrameSdr(int i2, int i7) {
        return super.getCurrentFrame(i2, i7);
    }

    public void setAudioEraserOff(boolean z, String str) {
        if (!z) {
            try {
                this.mSemMediaPlayer.setParameter(36002, str);
            } catch (Exception e) {
                j.C(e, new StringBuilder("setAudioEraser failed. e="), this.TAG);
                return;
            }
        }
        this.mSemMediaPlayer.setParameter(36001, z ^ true ? 1 : 0);
        String str2 = this.TAG;
        Log.i(str2, "setAudioEraserOff : " + z);
    }

    public void setColorCorrect() {
        try {
            this.mSemMediaPlayer.setParameter(987654321, 1);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setColorCorrecting failed. e="), this.TAG);
        }
    }

    public void setIsHdrVideo() {
        this.isHdr = true;
    }

    public void setVideoFilter(String str, int i2) {
        try {
            this.mSemMediaPlayer.setVideoFilter(str, i2);
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("setVideoFilter {");
            sb2.append(!TextUtils.isEmpty(str));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(i2);
            sb2.append("}");
            Log.d(str2, sb2.toString());
        } catch (Exception | NoSuchMethodError e) {
            String str3 = this.TAG;
            Log.w(str3, "setVideoFilter failed. e=" + e.getMessage());
        }
    }

    public void setVideoFrc(int i2, float f, boolean z) {
        try {
            this.mSemMediaPlayer.setVideoFrc(i2, f, z);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setVideoFrc failed. e="), this.TAG);
        }
    }
}
