package com.samsung.android.gallery.module.album.hide;

import A2.d;
import A4.H;
import A9.b;
import com.samsung.android.gallery.app.ui.list.albums.hide.AlbumsHideFragment;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsHideManager {
    private final AtomicBoolean mRunning = new AtomicBoolean(false);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAlbumHideListener {
    }

    /* access modifiers changed from: private */
    /* renamed from: hideAlbum */
    public void lambda$setHideAlbum$0(OnAlbumHideListener onAlbumHideListener, MediaItem mediaItem) {
        try {
            mediaItem.setAlbumHide(!mediaItem.isAlbumHide());
            int updateAlbumsHideState = AlbumHelper.getInstance().updateAlbumsHideState(mediaItem);
            Log.d("AlbumsHideManager", "updated item" + Logger.v(Integer.valueOf(mediaItem.getAlbumID()), Integer.valueOf(updateAlbumsHideState)));
            Blackboard.getApplicationInstance().post("global://stories/data_pendingUpdate", (Object) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ThreadUtil.postOnUiThreadDelayed(new d(24, this, onAlbumHideListener), 300);
    }

    /* access modifiers changed from: private */
    /* renamed from: notifyListener */
    public void lambda$hideAlbum$1(OnAlbumHideListener onAlbumHideListener) {
        if (onAlbumHideListener != null) {
            H h5 = (H) onAlbumHideListener;
            ((AlbumsHideFragment) h5.e).lambda$onSwitchClick$0((Consumer) h5.f);
        }
        this.mRunning.set(false);
    }

    public void setHideAlbum(OnAlbumHideListener onAlbumHideListener, MediaItem mediaItem) {
        if (!this.mRunning.get()) {
            this.mRunning.set(true);
            SimpleThreadPool.getInstance().execute(new b(this, onAlbumHideListener, mediaItem, 7));
        }
    }
}
