package w8;

import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Image360Fragment e;

    public /* synthetic */ a(Image360Fragment image360Fragment, int i2) {
        this.d = i2;
        this.e = image360Fragment;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        int i2 = this.d;
        Image360Fragment image360Fragment = this.e;
        switch (i2) {
            case 0:
                return image360Fragment.onApplyWindowInsetsMainLayout(view, windowInsets);
            default:
                return image360Fragment.onApplyWindowInsetsToolParent(view, windowInsets);
        }
    }
}
