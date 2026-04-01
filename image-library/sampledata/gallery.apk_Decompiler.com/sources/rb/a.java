package rb;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.widget.crop.CropViewImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnTouchListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return ((CropViewImpl) obj).onTouch(view, motionEvent);
            default:
                return ((SearchPicturesFragment) obj).onListVewTouch(view, motionEvent);
        }
    }
}
