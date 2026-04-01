package com.samsung.android.gallery.module.bgm.bgmdata;

import E9.a;
import android.net.Uri;
import com.samsung.android.gallery.module.bgm.bgmlist.story.Bgm;
import com.samsung.android.gallery.module.bgm.provider.IBgmProvider;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmCache {
    private static final BgmInfo sDummyBgmInfo = new BgmInfo() {
        public boolean isDummy() {
            return true;
        }
    };
    private final String TAG = getClass().getSimpleName();
    private final ConcurrentHashMap<String, BgmInfo> mBgmMap = new ConcurrentHashMap<>();
    private final List<String> mPreloadedBgmList = new ArrayList(Bgm.getAllPreloadedBgm());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final BgmCache INSTANCE = new BgmCache();
    }

    public static BgmCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCache$0(String str) {
        addBgmInfo(str, (ArrayList<Uri>) null, "update");
    }

    private void updateListFromBgmProvider(IBgmProvider iBgmProvider) {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider)) {
            try {
                ArrayList arrayList = new ArrayList(this.mBgmMap.keySet());
                List<String> downloadedBgm = iBgmProvider.getDownloadedBgm();
                arrayList.removeAll(this.mPreloadedBgmList);
                if (downloadedBgm != null && !downloadedBgm.isEmpty()) {
                    arrayList.removeAll(downloadedBgm);
                }
                if (!arrayList.isEmpty()) {
                    Log.d(this.TAG, "removed list", arrayList);
                }
                ConcurrentHashMap<String, BgmInfo> concurrentHashMap = this.mBgmMap;
                Objects.requireNonNull(concurrentHashMap);
                arrayList.forEach(new a(16, concurrentHashMap));
                ArrayList arrayList2 = new ArrayList(this.mBgmMap.keySet());
                if (downloadedBgm != null && !downloadedBgm.isEmpty()) {
                    for (String next : downloadedBgm) {
                        if (!arrayList2.contains(next)) {
                            addBgmInfo(next, (ArrayList<Uri>) null, "updateListFromBgmProvider");
                        }
                    }
                }
            } catch (Exception e) {
                A.a.s(e, new StringBuilder("fail to load bgmNames "), this.TAG);
            }
        }
    }

    public void addBgmInfo(String str, BgmInfo bgmInfo) {
        if (bgmInfo != null) {
            this.mBgmMap.put(str, bgmInfo);
        } else {
            this.mBgmMap.putIfAbsent(str, sDummyBgmInfo);
        }
    }

    public BgmInfo getBgmInfo(String str) {
        return this.mBgmMap.get(str);
    }

    public ArrayList<Uri> getUris(String str) {
        BgmInfo bgmInfo = this.mBgmMap.get(str);
        if (bgmInfo != null) {
            return bgmInfo.getUris();
        }
        return new ArrayList<>();
    }

    public boolean hasBgm(String str) {
        return this.mBgmMap.containsKey(str);
    }

    public boolean isPreloadBgm(String str) {
        return this.mPreloadedBgmList.contains(str);
    }

    public boolean isPrepared(String str) {
        BgmInfo bgmInfo = this.mBgmMap.get(str);
        if (bgmInfo == null || bgmInfo.isDummy()) {
            return false;
        }
        return true;
    }

    public void updateCache(IBgmProvider iBgmProvider) {
        this.mPreloadedBgmList.forEach(new a(15, this));
        updateListFromBgmProvider(iBgmProvider);
    }

    public void addBgmInfo(String str, ArrayList<Uri> arrayList, String str2) {
        BgmInfo bgmInfo = getBgmInfo(str);
        if ((bgmInfo == null || bgmInfo.isDummy()) && arrayList != null && !arrayList.isEmpty()) {
            BgmInfo bgmInfo2 = new BgmInfo();
            bgmInfo2.addFileInfo(arrayList);
            addBgmInfo(str, bgmInfo2);
        } else if (bgmInfo == null) {
            addBgmInfo(str, (BgmInfo) null);
        }
    }
}
