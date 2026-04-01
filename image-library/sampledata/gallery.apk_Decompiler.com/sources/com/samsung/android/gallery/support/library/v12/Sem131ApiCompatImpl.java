package com.samsung.android.gallery.support.library.v12;

import N2.j;
import android.content.Context;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.v12.media.Sec131BgmAudioPlayerCompat;
import com.samsung.android.gallery.support.library.v12.media.Sec131BgmVideoPlayerCompat;
import com.samsung.android.gallery.support.library.v12.remster.SemRemasterManagerReflection;
import com.samsung.android.sdk.cover.ScoverManager;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem131ApiCompatImpl extends Sem130ApiCompatImpl {
    public MediaPlayerCompat createSecBgmAudioPlayer(int i2) {
        return new Sec131BgmAudioPlayerCompat(i2);
    }

    public MediaPlayerCompat createSecBgmVideoPlayer(int i2) {
        return new Sec131BgmVideoPlayerCompat(i2);
    }

    public VslMesDetectorCompat getVslMesDetectorCompat(String str) {
        SemRemasterManagerReflection semRemasterManagerReflection = new SemRemasterManagerReflection(str);
        if (semRemasterManagerReflection.support()) {
            return semRemasterManagerReflection;
        }
        return super.getVslMesDetectorCompat(str);
    }

    public boolean isClearCoverAttached(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ScoverState coverState = new ScoverManager(context.getApplicationContext()).getCoverState();
            String str = this.TAG;
            Log.d(str, "isClearCoverAttached " + coverState + " +" + (System.currentTimeMillis() - currentTimeMillis));
            if (coverState == null || !coverState.getAttachState() || coverState.getType() != 17) {
                return false;
            }
            return true;
        } catch (Exception e) {
            j.D(e, new StringBuilder("isClearCoverAttached failed. e="), this.TAG);
            return false;
        }
    }
}
