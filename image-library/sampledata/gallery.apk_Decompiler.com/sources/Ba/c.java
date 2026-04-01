package Ba;

import android.view.View;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewPlayer;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements MotionPhotoViewPlayer.VideoCallback, FastOptionItemView.ItemSelectedListener {
    public final /* synthetic */ MotionPhotoActivity d;

    public /* synthetic */ c(MotionPhotoActivity motionPhotoActivity) {
        this.d = motionPhotoActivity;
    }

    public void onItemSelected(int i2, View view) {
        this.d.lambda$bindFastOptions$2(i2, view);
    }
}
