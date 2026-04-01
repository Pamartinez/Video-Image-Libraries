package com.samsung.android.gallery.app.activity.external;

import android.app.Activity;
import android.widget.ImageView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                LinkViewNavController.lambda$notifyAndFinish$3((Activity) this.f, (String) this.e);
                return;
            case 1:
                ((SharingsViewNavigatorController) this.f).lambda$finishActivityOnUiThread$7((String) this.e);
                return;
            case 2:
                ((ShortcutViewNavigatorController) this.f).lambda$moveToAlbumsForLocalDbUpdate$7((ImageView) this.e);
                return;
            default:
                ((ShortcutViewNavigatorController) this.f).lambda$publishEventForCreaturePictures$1((LaunchIntent) this.e);
                return;
        }
    }
}
