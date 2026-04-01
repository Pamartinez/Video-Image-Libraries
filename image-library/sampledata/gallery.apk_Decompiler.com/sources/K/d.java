package K;

import android.media.MediaDrm;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.drm.FrameworkMediaDrm;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements MediaDrm.OnEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FrameworkMediaDrm f368a;
    public final /* synthetic */ ExoMediaDrm.OnEventListener b;

    public /* synthetic */ d(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnEventListener onEventListener) {
        this.f368a = frameworkMediaDrm;
        this.b = onEventListener;
    }

    public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i2, int i7, byte[] bArr2) {
        this.f368a.lambda$setOnEventListener$1(this.b, mediaDrm, bArr, i2, i7, bArr2);
    }
}
