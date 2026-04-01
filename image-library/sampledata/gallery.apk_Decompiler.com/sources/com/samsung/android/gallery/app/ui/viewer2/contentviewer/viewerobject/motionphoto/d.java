package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoViewModeHandler.ViewModeAdapter e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ d(MotionPhotoViewModeHandler.ViewModeAdapter viewModeAdapter, boolean z, int i2) {
        this.d = i2;
        this.e = viewModeAdapter;
        this.f = z;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateDropDownViewBasedOnSelect$1(this.f, (Drawable) obj);
                return;
            default:
                this.e.lambda$updateDropDownViewBasedOnSelect$2(this.f, (Drawable) obj);
                return;
        }
    }
}
