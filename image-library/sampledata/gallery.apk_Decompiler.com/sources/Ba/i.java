package Ba;

import android.view.ViewTreeObserver;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements ViewTreeObserver.OnScrollChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MotionPhotoViewCompat f2778a;

    public /* synthetic */ i(MotionPhotoViewCompat motionPhotoViewCompat) {
        this.f2778a = motionPhotoViewCompat;
    }

    public final void onScrollChanged() {
        this.f2778a.requestLayout();
    }
}
