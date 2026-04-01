package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import a8.d;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterPicturesPresenter extends SearchClusterResultPresenter {
    public ClusterPicturesPresenter(Blackboard blackboard, ISearchClusterResultView iSearchClusterResultView) {
        super(blackboard, iSearchClusterResultView);
    }

    private int[] getPdcCount() {
        int i2;
        int i7 = 0;
        if (getAllItems() != null) {
            MediaItem[] allItems = getAllItems();
            int length = allItems.length;
            i2 = 0;
            while (i7 < length) {
                if (allItems[i7].isVideo()) {
                    i2++;
                }
                i7++;
            }
            i7 = getItemCount() - i2;
        } else {
            i2 = 0;
        }
        return new int[]{i7, i2};
    }

    private boolean isPdcClusterPictures(String str) {
        if (str == null || !str.startsWith("location://search/fileList/PdcPictures")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarCount$0(int[] iArr) {
        getToolbar().setSubtitle((CharSequence) StringResources.getCountString(iArr[0], iArr[1]));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarCount$1(String str, Context context, Bundle bundle) {
        int[] iArr;
        if (isPdcClusterPictures(str)) {
            iArr = getPdcCount();
        } else {
            iArr = SearchEngineFactory.create(context).getMediaTypeCount(bundle);
        }
        if (iArr != null) {
            ThreadUtil.postOnUiThread(new e(27, this, iArr));
        }
    }

    private void updateToolbarCount() {
        String locationKey = getLocationKey();
        if (locationKey != null) {
            Bundle args = ArgumentsUtil.getArgs(locationKey);
            SimpleThreadPool.getInstance().execute(new d((Object) this, locationKey, (Object) getApplicationContext(), (Object) args, 23));
        }
    }

    public void getMediaTypeCountFromBlackboard() {
        Object read;
        if (TextUtils.isEmpty(ArgumentsUtil.getArgValue(getLocationKey(), "search_cluster_result_type", "")) && (read = this.mBlackboard.read("data://user/SearchClusterMediaTypeCount")) != null) {
            int[] iArr = (int[]) read;
            this.mMediaTypeImageCnt = iArr[0];
            this.mMediaTypeVideoCnt = iArr[1];
        }
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 8002) {
            return super.handleEvent(eventMessage);
        }
        return false;
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        updateToolbarCount();
    }
}
