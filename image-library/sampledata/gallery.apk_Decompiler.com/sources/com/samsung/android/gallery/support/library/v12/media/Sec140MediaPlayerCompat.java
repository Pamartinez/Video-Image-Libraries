package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.library.v9.media.Sec115MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec140MediaPlayerCompat extends Sec115MediaPlayerCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MethodHolder {
        static final Method methodSetVideoFilterLevel = Reflector.getMethod(SemMediaPlayer.class, "setVideoFilterLevel", Integer.TYPE);
        static final Method methodSetVideoFilterName = Reflector.getMethod(SemMediaPlayer.class, "setVideoFilterName", String.class);
    }

    public Sec140MediaPlayerCompat(int i2) {
        super(i2);
    }

    public void setDynamicViewConfiguration(ArrayList<MediaPlayback> arrayList, boolean z) {
        try {
            ArrayList arrayList2 = new ArrayList();
            Iterator<MediaPlayback> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaPlayback next = it.next();
                arrayList2.add(new SemMediaPlayer.DynamicViewingConfiguration(next.startMs, next.endMs, next.speed));
            }
            this.mSemMediaPlayer.setDynamicViewingConfigurations(arrayList2, z);
        } catch (Exception | NoSuchMethodError e) {
            C0212a.y(e, new StringBuilder("setDynamicViewingConfiguration failed = "), this.TAG);
        }
    }

    public void setVideoFilter(String str, int i2) {
        Method method;
        try {
            Method method2 = MethodHolder.methodSetVideoFilterName;
            if (method2 != null) {
                method2.invoke(this.mSemMediaPlayer, new Object[]{str});
            }
            if (i2 > 0 && (method = MethodHolder.methodSetVideoFilterLevel) != null) {
                method.invoke(this.mSemMediaPlayer, new Object[]{Integer.valueOf(i2)});
            }
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("setVideoFilter {");
            sb2.append(!TextUtils.isEmpty(str));
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(i2);
            sb2.append("}");
            Log.d(str2, sb2.toString());
        } catch (Exception e) {
            j.C(e, new StringBuilder("setVideoFilter failed. e="), this.TAG);
        }
    }
}
