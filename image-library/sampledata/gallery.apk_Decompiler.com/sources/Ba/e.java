package Ba;

import android.view.View;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoActivity e;

    public /* synthetic */ e(MotionPhotoActivity motionPhotoActivity, int i2) {
        this.d = i2;
        this.e = motionPhotoActivity;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        MotionPhotoActivity motionPhotoActivity = this.e;
        switch (i2) {
            case 0:
                motionPhotoActivity.lambda$onCreate$0(view);
                return;
            default:
                motionPhotoActivity.lambda$bindVideoCtrl$4(view);
                return;
        }
    }
}
