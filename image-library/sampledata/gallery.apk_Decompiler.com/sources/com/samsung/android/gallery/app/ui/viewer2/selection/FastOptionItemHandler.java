package com.samsung.android.gallery.app.ui.viewer2.selection;

import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.app.controller.internals.DeleteBurstShotCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteSimilarShotCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveBurstShotCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveSimilarShotCmd;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.groupshot.BurstShotFormat;
import com.samsung.android.gallery.module.groupshot.GroupShotFormat;
import com.samsung.android.gallery.module.groupshot.SimilarShotFormat;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.popover.PopoverHelper;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionItemHandler implements FastOptionItemView.ItemSelectedListener {
    private final Blackboard mBlackboard;
    private final EventContext mEventContext;
    private final boolean mSimilarShotGroup;
    private final ISelectionView mView;

    public FastOptionItemHandler(Blackboard blackboard, ISelectionView iSelectionView, EventContext eventContext) {
        String str;
        this.mBlackboard = blackboard;
        this.mView = iSelectionView;
        boolean isSimilarShotSelection = LocationKey.isSimilarShotSelection(iSelectionView.getLocationKey());
        this.mSimilarShotGroup = isSimilarShotSelection;
        this.mEventContext = eventContext;
        if (isSimilarShotSelection) {
            str = "similar";
        } else {
            str = "burst";
        }
        Log.d("FastOptionItemHandler", "construct", str);
    }

    private int getItemCount() {
        return this.mView.getItemCount();
    }

    private boolean isBestItemSelected(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!(mediaItem2 == null || mediaItem2.getBestImage() == 1)) {
            mediaItem2 = null;
        }
        if (mediaItem2 == null || mediaItem2.getFileId() != mediaItem.getFileId()) {
            return false;
        }
        return true;
    }

    private boolean isScreenLocked() {
        if (!LaunchIntent.isFromLockScreen(this.mEventContext.getBlackboard()) || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    private void publishPopoverInfo(int i2, View view, boolean z) {
        if (view != null && SystemUi.supportPopoverUi(view.getContext(), z)) {
            if (z) {
                RectF viewRect = ViewUtils.getViewRect(view);
                PopoverHelper.publishPopoverShareInfo(this.mEventContext.getBlackboard(), new Point((int) viewRect.left, (int) viewRect.top));
                return;
            }
            PopoverHelper.publishPopoverInfo(this.mEventContext.getBlackboard(), view, 2, i2);
        }
    }

    public void changeBestItemCmd(MediaItem[] mediaItemArr) {
        BaseCommand baseCommand;
        SharedTransition.setReturnPosition(this.mBlackboard, this.mView.getLastFocusedPosition());
        if (PocFeatures.isEnabled(PocFeatures.C2paFileEdit)) {
            baseCommand = new ChangeBestItemCmd2();
        } else {
            baseCommand = new ChangeBestItemCmd();
        }
        EventContext eventContext = this.mEventContext;
        baseCommand.execute(eventContext, mediaItemArr, eventContext.getBestItem(), getGroupShotFormat());
    }

    public void deleteShotCmd(Object obj) {
        if (this.mSimilarShotGroup) {
            new DeleteSimilarShotCmd().addConfig(DeleteCmd.DELETE_FROM_SELECTION_VIEW).execute(this.mEventContext, obj);
        } else {
            new DeleteBurstShotCmd().addConfig(DeleteCmd.DELETE_FROM_SELECTION_VIEW).execute(this.mEventContext, obj);
        }
    }

    public GroupShotFormat getGroupShotFormat() {
        if (this.mSimilarShotGroup) {
            return new SimilarShotFormat();
        }
        return new BurstShotFormat();
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemSelected(int r5, android.view.View r6) {
        /*
            r4 = this;
            com.samsung.android.gallery.app.controller.EventContext r0 = r4.mEventContext
            com.samsung.android.gallery.module.data.MediaItem[] r0 = r0.getSelectedItems()
            r1 = 0
            r2 = 1
            r3 = -1
            if (r5 == 0) goto L_0x0059
            if (r5 == r2) goto L_0x0030
            r2 = 2
            if (r5 == r2) goto L_0x0022
            r2 = 3
            if (r5 == r2) goto L_0x0014
            goto L_0x006e
        L_0x0014:
            int r2 = r0.length
            if (r2 != 0) goto L_0x001b
            r5 = 2131888357(0x7f1208e5, float:1.9411347E38)
            goto L_0x0073
        L_0x001b:
            r4.publishPopoverInfo(r5, r6, r1)
            r4.deleteShotCmd(r0)
            goto L_0x006e
        L_0x0022:
            int r2 = r0.length
            if (r2 != 0) goto L_0x0029
            r5 = 2131888358(0x7f1208e6, float:1.941135E38)
            goto L_0x0073
        L_0x0029:
            r4.publishPopoverInfo(r5, r6, r1)
            r4.saveShotCmd(r0)
            goto L_0x006e
        L_0x0030:
            int r1 = r0.length
            if (r1 != 0) goto L_0x0037
            r5 = 2131888359(0x7f1208e7, float:1.9411351E38)
            goto L_0x0073
        L_0x0037:
            boolean r1 = r4.isScreenLocked()
            if (r1 == 0) goto L_0x0052
            com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd r5 = new com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd
            r5.<init>()
            com.samsung.android.gallery.app.controller.EventContext r6 = r4.mEventContext
            U5.c r1 = new U5.c
            r2 = 6
            r1.<init>(r2, r4, r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1}
            r5.execute(r6, r0)
            goto L_0x006e
        L_0x0052:
            r4.publishPopoverInfo(r5, r6, r2)
            r4.lambda$onItemSelected$0(r0)
            goto L_0x006e
        L_0x0059:
            int r5 = r0.length
            if (r5 != r2) goto L_0x0070
            r5 = r0[r1]
            com.samsung.android.gallery.app.controller.EventContext r6 = r4.mEventContext
            com.samsung.android.gallery.module.data.MediaItem r6 = r6.getBestItem()
            boolean r5 = r4.isBestItemSelected(r5, r6)
            if (r5 == 0) goto L_0x006b
            goto L_0x0070
        L_0x006b:
            r4.changeBestItemCmd(r0)
        L_0x006e:
            r5 = r3
            goto L_0x0073
        L_0x0070:
            r5 = 2131888006(0x7f120786, float:1.9410635E38)
        L_0x0073:
            if (r5 == r3) goto L_0x007e
            com.samsung.android.gallery.app.controller.EventContext r4 = r4.mEventContext
            android.content.Context r4 = r4.getContext()
            com.samsung.android.gallery.support.utils.Utils.showToast((android.content.Context) r4, (int) r5)
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.selection.FastOptionItemHandler.onItemSelected(int, android.view.View):void");
    }

    public void saveShotCmd(Object[] objArr) {
        if (this.mSimilarShotGroup) {
            new SaveSimilarShotCmd().execute(this.mEventContext, objArr, Integer.valueOf(getItemCount()));
        } else {
            new SaveBurstShotCmd().execute(this.mEventContext, objArr, Integer.valueOf(getItemCount()));
        }
    }

    /* renamed from: shareCmd */
    public void lambda$onItemSelected$0(MediaItem[] mediaItemArr) {
        if (this.mView.setInputBlock("FastOptionItemHandler_shareCmd", 1000)) {
            if (PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
                MediaItem[] allItems = this.mEventContext.getAllItems();
                MediaItem[] mediaItemArr2 = new MediaItem[allItems.length];
                for (int i2 = 0; i2 < allItems.length; i2++) {
                    MediaItem clone = allItems[i2].clone();
                    mediaItemArr2[i2] = clone;
                    clone.setCount(1);
                }
                ShareProvider.prepareExtendedShareList(this.mEventContext.getContext(), this.mBlackboard, mediaItemArr2, (Runnable) null);
            }
            this.mView.handleShareInternal();
            new ShareViaCmd().addConfig(1).execute(this.mEventContext, mediaItemArr, null);
        }
    }
}
