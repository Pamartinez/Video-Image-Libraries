package J;

import android.view.View;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.pinch.v3.DataItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ListenerSet.Event, PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ boolean d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ d(AnalyticsListener.EventTime eventTime, int i2, int i7, boolean z) {
        this.g = eventTime;
        this.e = i2;
        this.f = i7;
        this.d = z;
    }

    public void invoke(Object obj) {
        ((AnalyticsListener) obj).onRendererReadyChanged((AnalyticsListener.EventTime) this.g, this.e, this.f, this.d);
    }

    public void onAnimationEnd(View view) {
        int i2 = this.e;
        int i7 = this.f;
        DataItem.lambda$getScaleAnimator$3(this.d, (ScaleAnimator) this.g, i2, i7, view);
    }

    public /* synthetic */ d(boolean z, ScaleAnimator scaleAnimator, int i2, int i7) {
        this.d = z;
        this.g = scaleAnimator;
        this.e = i2;
        this.f = i7;
    }
}
