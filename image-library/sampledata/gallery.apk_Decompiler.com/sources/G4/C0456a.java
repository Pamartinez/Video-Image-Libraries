package g4;

import android.view.View;
import com.google.android.material.appbar.model.AppBarModel;
import com.samsung.android.gallery.app.ui.appbar.SuggestAppbarDelegate;

/* renamed from: g4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0456a implements AppBarModel.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2637a;
    public final /* synthetic */ SuggestAppbarDelegate b;

    public /* synthetic */ C0456a(SuggestAppbarDelegate suggestAppbarDelegate, int i2) {
        this.f2637a = i2;
        this.b = suggestAppbarDelegate;
    }

    public final void a(View view, AppBarModel appBarModel) {
        int i2 = this.f2637a;
        SuggestAppbarDelegate suggestAppbarDelegate = this.b;
        switch (i2) {
            case 0:
                suggestAppbarDelegate.lambda$showUsbStorageCard$0(view, appBarModel);
                return;
            default:
                suggestAppbarDelegate.lambda$showUsbStorageCard$1(view, appBarModel);
                return;
        }
    }
}
