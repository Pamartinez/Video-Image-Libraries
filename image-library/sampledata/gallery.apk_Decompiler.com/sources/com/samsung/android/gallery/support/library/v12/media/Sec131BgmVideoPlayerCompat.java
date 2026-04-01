package com.samsung.android.gallery.support.library.v12.media;

import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.media.SemMediaPlayer;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec131BgmVideoPlayerCompat extends Sec130BgmVideoPlayerCompat {
    public Sec131BgmVideoPlayerCompat(int i2) {
        super(i2);
    }

    public void setDynamicViewConfiguration(ArrayList<MediaPlayback> arrayList) {
        try {
            ArrayList arrayList2 = new ArrayList();
            Iterator<MediaPlayback> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaPlayback next = it.next();
                arrayList2.add(new SemMediaPlayer.DynamicViewingConfiguration(next.startMs, next.endMs, next.speed));
            }
            this.mSemMediaPlayer.setDynamicViewingConfigurations(arrayList2);
        } catch (Exception | NoSuchMethodError e) {
            C0212a.y(e, new StringBuilder("setDynamicViewingConfiguration failed = "), this.TAG);
        }
    }
}
