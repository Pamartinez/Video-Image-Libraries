package y7;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.plugins.filebrowser.BitmapLoader;
import java.util.function.Function;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2760a;

    public /* synthetic */ j(int i2) {
        this.f2760a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2760a) {
            case 0:
                return Long.valueOf(MotionPhotoUtils.getXmpTimeStamp(((MediaItem) obj).getPath()));
            case 1:
                return BitmapLoader.g((String) obj);
            case 2:
                return Integer.valueOf((int) ((View) obj).getY());
            default:
                return C0280e.d(1, 0, (String) obj);
        }
    }
}
