package Ba;

import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoViewCompat e;

    public /* synthetic */ j(MotionPhotoViewCompat motionPhotoViewCompat, int i2) {
        this.d = i2;
        this.e = motionPhotoViewCompat;
    }

    public final void run() {
        int i2 = this.d;
        MotionPhotoViewCompat motionPhotoViewCompat = this.e;
        switch (i2) {
            case 0:
                motionPhotoViewCompat.lambda$onLayout$0();
                return;
            case 1:
                ViewUtils.setVisibility(motionPhotoViewCompat, 8);
                return;
            case 2:
                MotionPhotoViewHolder.lambda$bindView$1(motionPhotoViewCompat);
                return;
            default:
                motionPhotoViewCompat.requestLayout();
                return;
        }
    }
}
