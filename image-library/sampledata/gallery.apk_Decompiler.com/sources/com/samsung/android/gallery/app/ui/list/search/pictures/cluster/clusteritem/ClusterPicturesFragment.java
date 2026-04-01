package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultFragment;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterPicturesFragment extends SearchClusterResultFragment {
    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (!isDestroyed() && getMediaData(getLocationKey()).getCount() == 0) {
            ThreadUtil.postOnUiThread(new e(24, this));
            Log.majorEvent("onDataChangedOnUi : count=0. move back" + Logger.getEncodedString(ThreadUtil.getCallStack()));
        }
    }

    public ClusterPicturesPresenter createPresenter(ISearchClusterResultView iSearchClusterResultView) {
        return new ClusterPicturesPresenter(this.mBlackboard, iSearchClusterResultView);
    }
}
