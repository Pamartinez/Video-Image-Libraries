package com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.ISlideshowView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataDelegate extends AbsVuDelegate<ISlideshowView> {
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            ((ISlideshowView) MediaDataDelegate.this.mView).onMediaDataChanged(-1, -1);
        }

        public void onDataChanged() {
            Log.d(MediaDataDelegate.this.TAG, "onDataChanged", Integer.valueOf(MediaDataDelegate.this.mMediaData.getCount()));
            if (MediaDataDelegate.this.mMediaData.getCount() == 0) {
                Utils.showToast(MediaDataDelegate.this.getContext(), (int) R.string.empty_set_description_no_pictures_and_videos, 1);
                ((ISlideshowView) MediaDataDelegate.this.mView).finish();
            }
            ThreadUtil.runOnUiThread(new a(this));
        }
    };
    /* access modifiers changed from: private */
    public MediaData mMediaData;

    public MediaDataDelegate(ISlideshowView iSlideshowView) {
        super(iSlideshowView);
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    private void initMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(DataKey.getSlideshowDataKey(((ISlideshowView) this.mView).getLocationKey()));
        this.mMediaData = open;
        open.register(this.mDataChangeListener);
        ((ContainerModel) this.mModel).setMediaData(this.mMediaData);
    }

    public void onCreate(Bundle bundle) {
        initMediaData();
    }

    public void onDestroy() {
        closeMediaData();
    }
}
