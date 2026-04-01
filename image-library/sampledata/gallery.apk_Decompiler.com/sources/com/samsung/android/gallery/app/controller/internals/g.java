package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.MotionPhotoExportCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ MotionPhotoExportCmd d;
    public final /* synthetic */ MotionPhotoExportCmd.SubMenuType e;
    public final /* synthetic */ EventContext f;

    public /* synthetic */ g(MotionPhotoExportCmd motionPhotoExportCmd, MotionPhotoExportCmd.SubMenuType subMenuType, EventContext eventContext) {
        this.d = motionPhotoExportCmd;
        this.e = subMenuType;
        this.f = eventContext;
    }

    public final void run() {
        this.d.lambda$onItemSelected$1(this.e, this.f);
    }
}
