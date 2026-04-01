package androidx.appcompat.app;

import android.view.ViewGroup;
import androidx.core.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlertController f983a;

    public /* synthetic */ a(AlertController alertController) {
        this.f983a = alertController;
    }

    public final void accept(Object obj) {
        this.f983a.seslExpandTouchTarget((ViewGroup) obj);
    }
}
