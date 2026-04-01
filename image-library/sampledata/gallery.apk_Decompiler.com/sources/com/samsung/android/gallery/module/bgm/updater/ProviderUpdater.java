package com.samsung.android.gallery.module.bgm.updater;

import Fa.C0571z;
import H3.l;
import J8.a;
import android.net.Uri;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmData;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmDataListener;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProviderUpdater extends AbsUpdater {
    protected final BgmDataListener mDataChangeListener = new a(this);
    protected ConcurrentHashMap<String, BgmItem> mDownloadableBgmMap = new ConcurrentHashMap<>();

    public ProviderUpdater(BgmUpdateListener bgmUpdateListener) {
        super(bgmUpdateListener);
    }

    private long getDownloadCount(ConcurrentHashMap<String, BgmItem> concurrentHashMap) {
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return 0;
        }
        return concurrentHashMap.values().stream().filter(new C0571z(20)).count();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        ConcurrentHashMap<String, BgmItem> concurrentHashMap = this.mDownloadableBgmMap;
        ConcurrentHashMap<String, BgmItem> bgmItemMap = BgmData.getInstance().getBgmItemMap();
        this.mDownloadableBgmMap = bgmItemMap;
        Log.d("ProviderDataUpdater", "onDataChanged", Integer.valueOf(concurrentHashMap.size()), Integer.valueOf(this.mDownloadableBgmMap.size()), Long.valueOf(getDownloadCount(bgmItemMap)));
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, BgmItem> key : this.mDownloadableBgmMap.entrySet()) {
            String str = (String) key.getKey();
            BgmItem bgmItem = this.mDownloadableBgmMap.get(str);
            BgmItem bgmItem2 = concurrentHashMap.get(str);
            if (bgmItem != null && !bgmItem.equals(bgmItem2)) {
                arrayList.add(str);
                if (bgmItem.isDownloaded()) {
                    this.mBgmCache.addBgmInfo(str, (ArrayList<Uri>) null, "onDataChanged");
                }
            }
        }
        this.mUpdateListener.onUpdated(arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        ThreadUtil.runOnUiThread(new l(15, this));
    }

    public void close() {
        super.close();
        BgmData.getInstance().close(this.mDataChangeListener);
    }

    public DownloadListener createDownloadListener() {
        return new DownloadListener() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onDownloadedAll$0(boolean z) {
                ProviderUpdater.this.mUpdateListener.onDownloadedAll(z);
            }

            public void onDownloadedAll(boolean z) {
                Log.d("ProviderDataUpdater", "onDownloadedAll", Boolean.valueOf(z));
                BgmData.getInstance().reloadData(300);
                ThreadUtil.postOnUiThreadDelayed(new a(this, z), 300);
            }
        };
    }

    public boolean isAllDownloaded() {
        if (this.mDownloadableBgmMap.size() <= 0 || !this.mDownloadableBgmMap.values().stream().allMatch(new C0571z(20))) {
            return false;
        }
        return true;
    }

    public boolean isDownloaded(String str) {
        if (this.mBgmCache.isPreloadBgm(str)) {
            return true;
        }
        BgmItem bgmItem = this.mDownloadableBgmMap.get(str);
        if (bgmItem == null || !bgmItem.isDownloaded()) {
            return false;
        }
        return true;
    }

    public boolean isDownloading(String str) {
        BgmItem bgmItem = this.mDownloadableBgmMap.get(str);
        if (bgmItem == null || !bgmItem.isDownloading()) {
            return false;
        }
        return true;
    }

    public void open() {
        super.open();
        BgmData.getInstance().open(this.mDataChangeListener);
    }
}
