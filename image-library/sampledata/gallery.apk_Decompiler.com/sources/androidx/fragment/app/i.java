package androidx.fragment.app;

import android.content.res.Configuration;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f991a;
    public final /* synthetic */ FragmentManager b;

    public /* synthetic */ i(FragmentManager fragmentManager, int i2) {
        this.f991a = i2;
        this.b = fragmentManager;
    }

    public final void accept(Object obj) {
        int i2 = this.f991a;
        FragmentManager fragmentManager = this.b;
        switch (i2) {
            case 0:
                fragmentManager.lambda$new$0((Configuration) obj);
                return;
            case 1:
                fragmentManager.lambda$new$1((Integer) obj);
                return;
            case 2:
                fragmentManager.lambda$new$2((MultiWindowModeChangedInfo) obj);
                return;
            default:
                fragmentManager.lambda$new$3((PictureInPictureModeChangedInfo) obj);
                return;
        }
    }
}
