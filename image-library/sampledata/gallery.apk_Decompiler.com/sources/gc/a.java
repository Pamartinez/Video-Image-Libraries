package gc;

import android.widget.RadioGroup;
import com.samsung.android.gallery.app.ui.dialog.HighlightExportOptionsDialog;
import com.samsung.android.gallery.app.ui.dialog.SortByDialog;
import com.samsung.android.gallery.app.ui.dialog.ViewAsDialog;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements RadioGroup.OnCheckedChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3266a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f3266a = i2;
        this.b = obj;
    }

    public final void onCheckedChanged(RadioGroup radioGroup, int i2) {
        int i7 = this.f3266a;
        Object obj = this.b;
        switch (i7) {
            case 0:
                ((DebugSmartCropRectInfo) obj).lambda$initShowType$7(radioGroup, i2);
                return;
            case 1:
                ((HighlightExportOptionsDialog) obj).onOrientationSelected(radioGroup, i2);
                return;
            case 2:
                ((SortByDialog) obj).onButtonChanged(radioGroup, i2);
                return;
            default:
                ((ViewAsDialog) obj).onClickRadioButton(radioGroup, i2);
                return;
        }
    }
}
