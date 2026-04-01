package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ MotionPhotoViewModeHandler.ViewModeAdapter d;
    public final /* synthetic */ int e;

    public /* synthetic */ c(MotionPhotoViewModeHandler.ViewModeAdapter viewModeAdapter, int i2) {
        this.d = viewModeAdapter;
        this.e = i2;
    }

    public final void onClick(View view) {
        this.d.lambda$getDropDownView$0(this.e, view);
    }
}
