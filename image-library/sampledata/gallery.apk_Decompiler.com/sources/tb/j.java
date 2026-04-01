package tb;

import android.content.DialogInterface;
import android.view.Window;
import com.samsung.android.gallery.widget.dialog.ProgressAvdBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements DialogInterface.OnShowListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Window f3293a;

    public /* synthetic */ j(Window window) {
        this.f3293a = window;
    }

    public final void onShow(DialogInterface dialogInterface) {
        ProgressAvdBuilder.lambda$create$0(this.f3293a, dialogInterface);
    }
}
