package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import q8.a;
import v7.w;
import x5.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TopResultsClusterPresenter extends ClusterResultPresenter {
    private HashMap<Long, String> mFrameIdMap = new HashMap<>();
    private HashSet<Long> mOcrIdSet = new HashSet<>();

    public TopResultsClusterPresenter(EventContext eventContext, IClusterResult iClusterResult) {
        super(eventContext, iClusterResult);
    }

    private void selectItemSync(ArrayList<MediaItem> arrayList, boolean z) {
        ClusterResultViewAdapter adapter = this.mView.getAdapter();
        if (adapter != null && arrayList != null) {
            int itemCount = adapter.getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                MediaItem mediaItemSync = adapter.getMediaItemSync(i2);
                if (mediaItemSync != null) {
                    Iterator<MediaItem> it = arrayList.iterator();
                    while (it.hasNext()) {
                        MediaItem next = it.next();
                        if (next != null && next.getFileId() == mediaItemSync.getFileId()) {
                            adapter.selectItemWithSync(i2, z, false);
                        }
                    }
                }
            }
            adapter.notifySelectedItemChanged();
        }
    }

    private void setScsMediaItem() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            int count = mediaData.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                MediaItem read = this.mMediaData.read(i2);
                if (read != null) {
                    DetailsData.of(read).hasOcr = this.mOcrIdSet.contains(Long.valueOf(read.getFileId()));
                    VideoPropData.of(read).videoFrameIds = this.mFrameIdMap.get(Long.valueOf(read.getFileId()));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void startSelectionMode(Object obj, Bundle bundle) {
        if (this.mView.isViewActive()) {
            this.mView.getListView().enterSelectionMode(0);
            if (!this.mBlackboard.isEmpty("data://user/pick/ItemChecker")) {
                this.mView.getAdapter().setSelectableChecker((SelectableChecker) this.mBlackboard.read("data://user/pick/ItemChecker"));
            }
        }
    }

    /* access modifiers changed from: private */
    public void stopSelectionMode(Object obj, Bundle bundle) {
        if (this.mView.isViewActive()) {
            this.mView.getListView().exitSelectionMode(false);
        }
    }

    private void updateFrameIdMap() {
        HashMap<Long, String> hashMap;
        if (PreferenceFeatures.OneUi8x.VIDEO_SEARCH) {
            Object eventContextData = this.mHandler.getEventContextData("frame_id_map");
            if (eventContextData != null) {
                hashMap = (HashMap) eventContextData;
            } else {
                hashMap = new HashMap<>();
            }
            this.mFrameIdMap = hashMap;
        }
    }

    private void updateOcrInfo() {
        String str = (String) this.mHandler.getEventContextData("ocr_ids");
        if (!TextUtils.isEmpty(str)) {
            this.mOcrIdSet = (HashSet) Stream.of(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).map(new a(24)).collect(Collectors.toSet());
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("lifecycle://ON_EnterSelectionMode", new c(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("lifecycle://ON_ExitSelectionMode", new c(this, 1)).setWorkingOnUI());
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 1003) {
            Optional.ofNullable(this.mView.getListView()).ifPresent(new w(8));
        } else if (i2 == 1020) {
            selectItemSync((ArrayList) eventMessage.obj, true);
            return true;
        } else if (i2 == 1021) {
            selectItemSync((ArrayList) eventMessage.obj, false);
            return true;
        } else if (i2 == 1041) {
            Optional.ofNullable(this.mView.getListView()).ifPresent(new w(6));
            return true;
        } else if (i2 == 1042) {
            Optional.ofNullable(this.mView.getListView()).ifPresent(new w(7));
            return true;
        }
        return super.handleEvent(eventMessage);
    }

    public void initializeData(boolean z) {
        super.initializeData(z);
        updateOcrInfo();
        updateFrameIdMap();
    }

    public void onDataChangedOnUi() {
        setScsMediaItem();
        super.onDataChangedOnUi();
    }
}
