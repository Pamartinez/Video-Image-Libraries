package Ia;

import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ActionInvokeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2825a;

    public /* synthetic */ a(int i2) {
        this.f2825a = i2;
    }

    public final void onHandle(Object[] objArr) {
        switch (this.f2825a) {
            case 0:
                ActionInvokeListener.lambda$static$0(objArr);
                return;
            default:
                RemasterDetector.getInstance().releaseIfExist();
                return;
        }
    }
}
