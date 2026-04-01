package com.samsung.android.gallery.app.controller.internals;

import F9.e;
import O3.s;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.result.SearchResult;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveFromResultCmd extends BaseCommand {
    private void executeAfterCollecting(EventContext eventContext, String str) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/selection/Item").appendArg("title", str).build()).setOnDataCompleteListener(new s(this)).start();
    }

    private void forceReload() {
        getBlackboard().postBroadcastEvent(EventMessage.obtain(103, 1, 0, (Object) null));
    }

    private boolean hasSelectedItems() {
        MediaItem[] selectedItems = getHandler().getSelectedItems();
        if (selectedItems == null || selectedItems.length <= 0) {
            return false;
        }
        return true;
    }

    private boolean isFloatingPopupMenu() {
        if (!((Boolean) getBlackboard().read("data://floating_pop_menu", Boolean.FALSE)).booleanValue() || !isDexMode()) {
            return false;
        }
        return true;
    }

    private boolean isNoItems(int i2, int i7) {
        if (i2 == i7) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeFromResult$0(int i2) {
        Log.d(this.TAG, "removeFromResult indexing done", Integer.valueOf(i2));
        forceReload();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$removeFromResult$1(String str, MediaItem[] mediaItemArr, int i2) {
        String str2;
        ArrayList arrayList = new ArrayList();
        SearchResult create = SearchResult.create(getContext(), str);
        if (create == null) {
            Log.e(this.TAG, "Invalid locationKey without category : " + Logger.getEncodedString(str));
            stopSelection();
            return;
        }
        int i7 = 0;
        for (MediaItem mediaItem : mediaItemArr) {
            i7 += create.removeTo(mediaItem);
            arrayList.add(Long.valueOf(mediaItem.getFileId()));
        }
        String str3 = this.TAG;
        Features features = Features.SUPPORT_INTELLIGENT_SEARCH;
        if (Features.isEnabled(features)) {
            str2 = "is";
        } else {
            str2 = "legacy";
        }
        Log.d(str3, "removeFromResult(w)", str2, create.getIndexingTagType(), Integer.valueOf(mediaItemArr.length), Integer.valueOf(arrayList.size()), Integer.valueOf(i7));
        if (i7 > 0) {
            if (Features.isEnabled(features)) {
                if (isNoItems(i2, i7)) {
                    getBlackboard().postBroadcastEvent(EventMessage.obtain(8003));
                }
                create.indexing(arrayList, mediaItemArr[0], new s(this));
            } else {
                forceReload();
            }
            stopSelection();
        }
    }

    /* access modifiers changed from: private */
    public void onSelectionCompleted(EventContext eventContext, ArrayList<Object> arrayList) {
        removeFromResult(eventContext, eventContext.getSelectedItems());
    }

    private void removeFromResult(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length == 0 || eventContext.getMediaData() == null) {
            Log.e(this.TAG, "removeFromResult failed.");
            return;
        }
        SimpleThreadPool.getInstance().execute(new e((Object) this, (Object) eventContext.getLocationKey(), (Object) mediaItemArr, eventContext.getMediaData().getCount(), 2));
    }

    private void stopSelection() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_MENU_REMOVE_FROM_RESULT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str = objArr[0];
        if ((eventContext.isSelectionMode() || isFloatingPopupMenu()) && hasSelectedItems()) {
            removeFromResult(eventContext, eventContext.getSelectedItems());
        } else {
            executeAfterCollecting(eventContext, str);
        }
    }
}
