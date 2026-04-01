package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import Ae.c;
import L2.a;
import Vf.A;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.PhotoEditorServiceManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.StickerCenterReceiver;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.StickerCenterServiceManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.VideoClipper;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {2, 1, 0})
@C1273e(c = "com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuManager$insertClippedImage$1", f = "ToolbarMenuManager.kt", l = {}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenuManager$insertClippedImage$1 extends i implements c {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ Rect $rect;
    int label;
    final /* synthetic */ ToolbarMenuManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ToolbarMenuManager$insertClippedImage$1(ToolbarMenuManager toolbarMenuManager, Bitmap bitmap, Rect rect, C1227c cVar) {
        super(2, cVar);
        this.this$0 = toolbarMenuManager;
        this.$bitmap = bitmap;
        this.$rect = rect;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new ToolbarMenuManager$insertClippedImage$1(this.this$0, this.$bitmap, this.$rect, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((ToolbarMenuManager$insertClippedImage$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (this.label == 0) {
            a.A(obj);
            StickerCenterServiceManager access$getStickerCenterServiceManager$p = this.this$0.stickerCenterServiceManager;
            if (access$getStickerCenterServiceManager$p == null || !access$getStickerCenterServiceManager$p.isClippedStickerEnabled()) {
                PhotoEditorServiceManager access$getPhotoEditorServiceManager$p = this.this$0.photoEditorServiceManager;
                if (access$getPhotoEditorServiceManager$p != null) {
                    access$getPhotoEditorServiceManager$p.insertClippedImageToDB(this.$bitmap);
                }
            } else {
                StickerCenterServiceManager access$getStickerCenterServiceManager$p2 = this.this$0.stickerCenterServiceManager;
                if (access$getStickerCenterServiceManager$p2 != null) {
                    Bitmap bitmap = this.$bitmap;
                    DeviceType access$getDeviceType$p = this.this$0.deviceType;
                    Rect rect = this.$rect;
                    String access$getStickerID$p = this.this$0.stickerID;
                    VideoClipper access$getVideoClipper$p = this.this$0.videoClipper;
                    if (access$getVideoClipper$p != null) {
                        z = access$getVideoClipper$p.isSupportedPoint();
                    } else {
                        z = false;
                    }
                    access$getStickerCenterServiceManager$p2.sendImageToStickerCenter(bitmap, access$getDeviceType$p, rect, access$getStickerID$p, z);
                }
                VideoClipper access$getVideoClipper$p2 = this.this$0.videoClipper;
                if (access$getVideoClipper$p2 != null) {
                    Context access$getContext$p = this.this$0.context;
                    StickerCenterReceiver stickerCenterReceiver = new StickerCenterReceiver(access$getVideoClipper$p2);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(StickerCenterReceiver.ACTION_STICKER_CENTER_RECEIVER);
                    access$getContext$p.registerReceiver(stickerCenterReceiver, intentFilter, 2);
                }
            }
            return x.f4917a;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
