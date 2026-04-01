package com.samsung.android.gallery.module.bgm.updater;

import com.samsung.android.gallery.module.bgm.bgmdata.BgmCache;
import com.samsung.android.gallery.module.bgm.provider.IBgmProvider;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsUpdater {
    private final String TAG = getClass().getSimpleName();
    protected final BgmCache mBgmCache = BgmCache.getInstance();
    private DownloadListener mDownloadListener;
    protected BgmUpdateListener mUpdateListener;

    public AbsUpdater(BgmUpdateListener bgmUpdateListener) {
        this.mUpdateListener = bgmUpdateListener;
    }

    private DownloadListener getDownloadListener() {
        if (this.mDownloadListener == null) {
            this.mDownloadListener = createDownloadListener();
        }
        return this.mDownloadListener;
    }

    public void bgmProviderConnected(IBgmProvider iBgmProvider) {
        this.mBgmCache.updateCache(iBgmProvider);
    }

    public void close() {
        BgmDownloadReceiver.getInstance().unregisterCallBack(getDownloadListener());
    }

    public abstract DownloadListener createDownloadListener();

    public boolean isAllDownloaded() {
        Log.e(this.TAG, "isAllDownloaded is unsupported");
        return false;
    }

    public abstract boolean isDownloaded(String str);

    public boolean isDownloading(String str) {
        Log.e(this.TAG, "isDownloading is unsupported");
        return false;
    }

    public void open() {
        BgmDownloadReceiver.getInstance().registerCallBack(getDownloadListener());
    }
}
