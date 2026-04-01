package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.graphics.Rect;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0004J\u000f\u0010\t\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\t\u0010\u0004J\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u0004¨\u0006\u000b"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionManager$getToolbarActionListener$1", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "Lme/x;", "saveAsSticker", "()V", "copy", "share", "edit", "saveAsImage", "selectAll", "saveAsGif", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarActionManager$getToolbarActionListener$1 implements ToolbarActionListener {
    final /* synthetic */ Rect $rect;
    final /* synthetic */ ToolbarActionManager this$0;

    public ToolbarActionManager$getToolbarActionListener$1(ToolbarActionManager toolbarActionManager, Rect rect) {
        this.this$0 = toolbarActionManager;
        this.$rect = rect;
    }

    public void copy() {
        Log.d(ToolbarActionManager.TAG, "copy");
    }

    public void edit() {
        Log.d(ToolbarActionManager.TAG, "edit");
        ObjectResult access$getObjectResult$p = this.this$0.objectResult;
        if (access$getObjectResult$p != null) {
            this.this$0.toolbarMenuManager.editClipperFilePath(access$getObjectResult$p.getOutput().getObjBitmap());
        }
    }

    public void saveAsGif() {
        Log.d(ToolbarActionManager.TAG, "saveAsGif");
    }

    public void saveAsImage() {
        Log.d(ToolbarActionManager.TAG, "saveAsImage");
    }

    public void saveAsSticker() {
        Log.d(ToolbarActionManager.TAG, "saveAsSticker");
        ObjectResult access$getObjectResult$p = this.this$0.objectResult;
        if (access$getObjectResult$p != null) {
            ToolbarActionManager toolbarActionManager = this.this$0;
            toolbarActionManager.toolbarMenuManager.insertClippedImage(access$getObjectResult$p.getOutput().getObjBitmap(), this.$rect);
        }
    }

    public void selectAll() {
        Log.d(ToolbarActionManager.TAG, "selectAll");
        this.this$0.toolbarMenuManager.selectAll(this.this$0.selectAll);
    }

    public void share() {
        Log.d(ToolbarActionManager.TAG, "share");
    }
}
