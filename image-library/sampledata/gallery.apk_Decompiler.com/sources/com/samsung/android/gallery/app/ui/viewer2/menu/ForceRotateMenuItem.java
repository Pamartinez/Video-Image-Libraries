package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ForceRotateMenuItem extends ViewerMenuItem {
    public ForceRotateMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.sa_rotate);
    }

    private boolean isAutoRotateEnabled(Context context) {
        if (SeApiCompat.isDualSecondScreen(context)) {
            return SeApiCompat.isAutoRotateSecondEnabled(context);
        }
        return SeApiCompat.isAutoRotateEnabled(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !SystemUi.isRequestedOrientationIgnored(this.mEventContext.getActivity());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return !LocationKey.isAiEditGroupPanelViewer(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        return !isInMultiWindowMode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        return !isFromCamera();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$4(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isDexMode(this.mEventContext.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$5(MediaItem mediaItem, String str) {
        return !isAutoRotateEnabled(this.mEventContext.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$6(MediaItem mediaItem, String str) {
        return !isFlipCover();
    }

    public boolean onMenuSelectInternal(View view) {
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3002));
        return false;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_rotate).setToolbarOnly().setShowAsActionFlag(2).exclude("location://dynamicViewList", "location://AllDayTimeLapse", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new d(this, 0)).validate(new c(4)).validate(new d(this, 1)).validate(new d(this, 2)).validate(new d(this, 3)).validate(new d(this, 4)).validate(new d(this, 5)).setSupportPpp(true);
    }
}
