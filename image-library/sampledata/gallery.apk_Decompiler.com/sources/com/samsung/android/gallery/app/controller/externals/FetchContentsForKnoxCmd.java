package com.samsung.android.gallery.app.controller.externals;

import A.a;
import A4.C0371f;
import A4.H;
import H3.j;
import N3.b;
import android.app.Dialog;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.knox.AbsKnoxOperator;
import com.samsung.android.gallery.module.knox.KnoxAlbumOperator;
import com.samsung.android.gallery.module.knox.KnoxItemOperator;
import com.samsung.android.gallery.module.knox.KnoxOperationCallback;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FetchContentsForKnoxCmd extends BaseCommand {
    private int mContainerId;
    private ArrayList<MediaItem> mItemList;
    private KnoxUtil.MoveType mMoveType;
    private Dialog mProgressDialog = null;

    /* renamed from: com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType[] r0 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType = r0
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.MOVE_TO_KNOX     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.utils.KnoxUtil$MoveType r1 = com.samsung.android.gallery.module.utils.KnoxUtil.MoveType.MOVE_TO_SECURE_FOLDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd.AnonymousClass2.<clinit>():void");
        }
    }

    private AbsKnoxOperator createKnoxOperator(String str) {
        if (isAlbumType(str)) {
            return new KnoxAlbumOperator(getBlackboard(), this.mMoveType, this.mContainerId, getKnoxOperationCallback());
        }
        return new KnoxItemOperator(getBlackboard(), this.mMoveType, this.mContainerId, getKnoxOperationCallback());
    }

    /* access modifiers changed from: private */
    public void dismissProgressDialog() {
        ThreadUtil.runOnUiThread(new b(this, 1));
    }

    private KnoxOperationCallback getKnoxOperationCallback() {
        return new KnoxOperationCallback() {
            public void dismissProgressDialog() {
                FetchContentsForKnoxCmd.this.dismissProgressDialog();
            }

            public void showCloudSyncConfirmDialog(int i2, int i7, Consumer<Context> consumer) {
                FetchContentsForKnoxCmd.this.showCloudSyncConfirmDialog(i2, i7, consumer);
            }

            public void showProgressDialog() {
                FetchContentsForKnoxCmd.this.showProgressDialog();
            }
        };
    }

    private boolean isAlbumType(String str) {
        if (str.equals("location://albums") || str.startsWith("location://folder/root") || str.equals("location://albums/all")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissProgressDialog$3() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Object[] objArr, Integer num) {
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)}, new StringBuilder("onPreExecute#PppChecker"), str);
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, obj, obj2, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, Object[] objArr, MediaItem[] mediaItemArr) {
        if (!GppmHelper.SUPPORT_DONATION_MULTIPLE || LocationKey.isAlbumsMatch(eventContext.getLocationKey()) || LocationKey.isAllAlbumsMatch(eventContext.getLocationKey())) {
            Object[] objArr2 = objArr;
            super.onPreExecute(eventContext, objArr2[0], objArr2[1], mediaItemArr);
            return;
        }
        EventContext eventContext2 = eventContext;
        MediaItem[] mediaItemArr2 = mediaItemArr;
        executePppChecker(eventContext2, mediaItemArr2, new j(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, objArr, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudSyncConfirmDialog$4(Consumer consumer, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null && ((Boolean) arrayList.get(0)).booleanValue()) {
            consumer.accept(getContext());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showProgressDialog$2() {
        AlertDialog create = new ProgressCircleBuilder(getContext()).setProgressMessage(AppResources.getString(R.string.processing)).create();
        this.mProgressDialog = create;
        create.show();
    }

    private void operate(String str, MediaItem[] mediaItemArr) {
        createKnoxOperator(str).operate(getContext(), mediaItemArr);
    }

    /* access modifiers changed from: private */
    public void showCloudSyncConfirmDialog(int i2, int i7, Consumer<Context> consumer) {
        if (getHandler() != null) {
            DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/SyncDataTransfer").appendArg("imageCount", i2).appendArg("videoCount", i7).appendArg("containerName", KnoxUtil.getInstance().getKnoxContainerName(this.mMoveType)).build()).setOnDataCompleteListener(new H(29, (Object) this, (Object) consumer)).start();
        }
    }

    /* access modifiers changed from: private */
    public void showProgressDialog() {
        ThreadUtil.runOnUiThread(new b(this, 0));
    }

    public Long getAnalyticsValue() {
        ArrayList<MediaItem> arrayList = this.mItemList;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return Long.valueOf((long) this.mItemList.size());
    }

    public String getEventId() {
        int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$gallery$module$utils$KnoxUtil$MoveType[this.mMoveType.ordinal()];
        if (i2 == 1) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_MOVE_TO_KNOX.toString();
        }
        if (i2 != 2) {
            return null;
        }
        return AnalyticsEventId.EVENT_MENU_MOVE_REMOVE_TO_SECURE_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMoveType = objArr[1];
        this.mContainerId = KnoxUtil.getInstance().getKnoxContainerId(this.mMoveType);
        this.mItemList = new ArrayList<>();
        getBlackboard().postEvent(EventMessage.obtain(1003));
        operate(objArr[0], objArr[2]);
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (objArr.length < 3 || objArr[2] == null) {
            Log.d(this.TAG, "onPreExecute", Integer.valueOf(eventContext.getSelectedItemCount()));
            if (eventContext.isSelectionMode()) {
                loadAndExecute(eventContext, new C0371f((Object) this, (Object) eventContext, (Object) objArr, 10));
            } else {
                super.onPreExecute(eventContext, objArr[0], objArr[1], eventContext.getSelectedItems());
            }
        } else {
            super.onPreExecute(eventContext, objArr);
        }
    }
}
