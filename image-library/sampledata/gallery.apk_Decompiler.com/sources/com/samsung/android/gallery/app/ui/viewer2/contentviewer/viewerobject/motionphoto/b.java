package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ MotionPhotoViewModeHandler.ViewModeAdapter d;

    public /* synthetic */ b(MotionPhotoViewModeHandler.ViewModeAdapter viewModeAdapter) {
        this.d = viewModeAdapter;
    }

    public final void accept(Object obj) {
        this.d.lambda$getViewInternal$3((Drawable) obj);
    }
}
