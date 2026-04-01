package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import F8.c;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmStateHandler {
    private static final boolean USE_CONTENT_PROVIDER = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider);
    private final BgmUriService mBgmService;
    private final ArrayList<String> mDownloadingList = new ArrayList<>();
    private final EventHandler mEventHandler;
    private String mLastSelectedBgm;

    public BgmStateHandler(EventHandler eventHandler, BgmUpdateListener bgmUpdateListener) {
        this.mEventHandler = eventHandler;
        BgmUriService bgmUriService = (BgmUriService) eventHandler.requestData(DataRequest.REQ_BGM_URI_SERVICE);
        this.mBgmService = bgmUriService;
        if (bgmUriService != null) {
            bgmUriService.setBgmUpdateListener(bgmUpdateListener);
        }
    }

    private void showNetworkErrorToast() {
        Utils.showToast(AppResources.getAppContext(), (int) R.string.connect_network_to_see_music_suggestion);
    }

    public void destroy() {
        BgmUriService bgmUriService = this.mBgmService;
        if (bgmUriService != null) {
            bgmUriService.setBgmUpdateListener((BgmUpdateListener) null);
        }
    }

    public String getLastSelectedBgm() {
        return this.mLastSelectedBgm;
    }

    public boolean handleDownloaded(String str) {
        this.mDownloadingList.remove(str);
        if (!str.equals(this.mLastSelectedBgm) || !this.mEventHandler.isBgmPickerVisible()) {
            return false;
        }
        return true;
    }

    public boolean isAllDownloaded() {
        return this.mBgmService.isAllDownloaded();
    }

    public boolean isDownloaded(String str) {
        if (EffectTheme.isPreloadBgm(str) || this.mBgmService.isDownloaded(str)) {
            return true;
        }
        return false;
    }

    public boolean isDownloading(String str) {
        if (this.mDownloadingList.contains(str)) {
            return true;
        }
        if (!USE_CONTENT_PROVIDER || !this.mBgmService.isDownloading(str)) {
            return false;
        }
        return true;
    }

    public void requestCancelDownload() {
        this.mBgmService.cancelDownload();
    }

    public void requestDownload(String str) {
        Log.d("BgmStateHandler", "requestDownload", str, Boolean.TRUE);
        if (NetworkUtils.isNetworkAvailable()) {
            this.mDownloadingList.add(str);
            this.mBgmService.requestDownload(str);
            this.mLastSelectedBgm = str;
            return;
        }
        showNetworkErrorToast();
    }

    public boolean requestDownloadAll() {
        if (NetworkUtils.isNetworkAvailable()) {
            SimpleThreadPool instance = SimpleThreadPool.getInstance();
            BgmUriService bgmUriService = this.mBgmService;
            Objects.requireNonNull(bgmUriService);
            instance.execute(new c(bgmUriService, 1));
            updateDownloadAllAvailable(false);
            return true;
        }
        showNetworkErrorToast();
        Log.d("BgmStateHandler", "downloadAllBgm is failed due to network");
        return false;
    }

    public boolean requestReplaceBgm(String str) {
        if (str.equals(this.mEventHandler.requestData(DataRequest.REQ_BGM_NAME))) {
            return false;
        }
        BgmExtraInfo bgmExtraInfo = new BgmExtraInfo();
        bgmExtraInfo.bgmName = str;
        this.mEventHandler.lambda$postEvent$0(Event.CHANGE_BGM, bgmExtraInfo);
        Log.d("BgmStateHandler", "requestReplaceBgm=" + bgmExtraInfo);
        this.mLastSelectedBgm = str;
        return true;
    }

    public void updateDownloadAllAvailable(boolean z) {
        PreferenceCache.StoryHighlightBgmDownloadAll.setBoolean(z);
    }
}
