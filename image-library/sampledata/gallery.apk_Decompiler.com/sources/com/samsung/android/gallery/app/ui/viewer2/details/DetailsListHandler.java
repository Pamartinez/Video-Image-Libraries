package com.samsung.android.gallery.app.ui.viewer2.details;

import H3.l;
import I4.b;
import K5.a;
import L7.c;
import L7.d;
import android.content.res.Configuration;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItem;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsListHandler extends ViewerObject implements FragmentLifeCycle {
    private final Object LOCK = new Object();
    private ArrayList<DetailsItem> mDetailsItemList;
    private DetailsView mDetailsView;

    private ArrayList<DetailsItem> createItemList() {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        DetailsView detailsView = this.mDetailsView;
        if (detailsView == null || eventContext == null) {
            Log.w(this.TAG, "failed to createItemList");
            return null;
        }
        ArrayList<DetailsItem> create = DetailsItemFactory.create(detailsView, eventContext);
        setTag(create);
        Log.d(this.TAG, "createItemList", Integer.valueOf(create.size()));
        return create;
    }

    private ArrayList<DetailsItem> getDetailsItems() {
        synchronized (this.LOCK) {
            try {
                if (this.mDetailsItemList == null) {
                    ArrayList<DetailsItem> createItemList = createItemList();
                    this.mDetailsItemList = createItemList;
                    if (createItemList == null) {
                        ArrayList<DetailsItem> arrayList = new ArrayList<>();
                        return arrayList;
                    }
                }
                ArrayList<DetailsItem> arrayList2 = (ArrayList) this.mDetailsItemList.clone();
                return arrayList2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mDetailsView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1() {
        runDetailsItems(new b(14));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            ViewUtils.post(this.mDetailsView, new l(23, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadDone$4(Object[] objArr, DetailsLoadResult detailsLoadResult) {
        updateDetailsItem(objArr[0], detailsLoadResult);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setTag$3(DetailsItem detailsItem) {
        detailsItem.setTag(this.TAG.getTag());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDetailsItem$7(TimeTickLog timeTickLog, MediaItem mediaItem, DetailsLoadResult detailsLoadResult, DetailsLoadResult detailsLoadResult2) {
        timeTickLog.tick();
        runDetailsItems(new c(mediaItem, detailsLoadResult, 1));
        timeTickLog.tick();
        StringCompat stringCompat = this.TAG;
        C0212a.z(new Object[]{Long.valueOf(mediaItem.getFileId()), detailsLoadResult2, timeTickLog}, new StringBuilder("update"), stringCompat);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (objArr[1].booleanValue()) {
            int intValue = objArr[0].intValue();
            if (intValue == 3) {
                onItemResumed();
            } else if (intValue == 4 || intValue == 5) {
                onItemPaused();
            }
        }
    }

    private void onItemPaused() {
        runDetailsItems(new b(17));
    }

    private void onItemRecycled() {
        runDetailsItems(new b(15));
    }

    private void onItemResumed() {
        runDetailsItems(new b(16));
    }

    /* access modifiers changed from: private */
    public void onLoadDone(Object... objArr) {
        DetailsLoadResult detailsLoadResult = objArr[1];
        if (!ThreadUtil.isMainThread() || !detailsLoadResult.key.needRefine()) {
            updateDetailsItem(objArr[0], detailsLoadResult);
        } else {
            this.mThread.runOnBgThread(new J6.c(this, objArr, detailsLoadResult, 6));
        }
    }

    private void runDetailsItems(Consumer<DetailsItem> consumer) {
        ArrayList<DetailsItem> detailsItems = getDetailsItems();
        if (detailsItems != null) {
            detailsItems.forEach(consumer);
        }
    }

    private void setTag(ArrayList<DetailsItem> arrayList) {
        if (arrayList != null) {
            arrayList.forEach(new a(5, this));
        }
    }

    private void updateDetailsItem(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        DetailsLoadResult detailsLoadResult2;
        if (MediaItemUtil.equalsId(mediaItem, this.mModel.getMediaItem())) {
            TimeTickLog timeTickLog = new TimeTickLog();
            if (detailsLoadResult == null) {
                detailsLoadResult2 = DetailsLoadResult.EMPTY;
            } else {
                detailsLoadResult2 = detailsLoadResult;
            }
            runDetailsItems(new c(mediaItem, detailsLoadResult2, 0));
            timeTickLog.tick();
            this.mThread.runOnUiThread(new B5.c(this, timeTickLog, mediaItem, detailsLoadResult2, detailsLoadResult, 4));
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.DETAILS_VIEW, new d(this, 0));
        this.mActionInvoker.add(ViewerAction.EVENT_DETAILS_INFO_UPDATED, new d(this, 1));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new d(this, 2));
        this.mActionInvoker.add(ViewerAction.REQUEST_DETAILS_ITEMS_LAYOUT_UPDATE, new d(this, 3));
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        setTag(this.mDetailsItemList);
    }

    public void onFinalized() {
        synchronized (this.LOCK) {
            try {
                ArrayList<DetailsItem> arrayList = this.mDetailsItemList;
                if (arrayList != null) {
                    arrayList.clear();
                    this.mDetailsItemList = null;
                    this.mDetailsView = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onPause() {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            onItemPaused();
        }
    }

    public void onResume() {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            onItemResumed();
        }
    }

    public void onViewAttached() {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            runDetailsItems(new b(14));
        }
    }

    public void onViewConfirm() {
        if (BottomSheetState.Details.isExpanded(this.mModel)) {
            onItemResumed();
        }
    }

    public void onViewDetached() {
        onItemPaused();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        onItemRecycled();
    }

    public void onConfigurationChanged(Configuration configuration) {
    }
}
