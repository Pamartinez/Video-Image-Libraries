package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultThemeHelper {
    private static final boolean SUPPORT_DEFAULT_THEME = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryDefaultTheme);
    private BgmExtraInfo mBgmExtraInfo;
    EventHandler mEventHandler;
    private Filter mFilter = Filter.NONE;
    private EffectTheme mTheme;
    IStoryHighlightView mView;

    public DefaultThemeHelper(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mEventHandler = iStoryHighlightView.getEventHandler();
    }

    private void initTheme(String str) {
        if (this.mTheme == null) {
            MediaItem readFirstMediaItemCache = readFirstMediaItemCache();
            if (readFirstMediaItemCache == null) {
                Log.d("DefaultThemeHelper", "initTheme ignore, wait next init:" + str);
                return;
            }
            ThemeUpdater themeUpdater = new ThemeUpdater(readFirstMediaItemCache, this.mEventHandler);
            String storyTheme = MediaItemStory.getStoryTheme(readFirstMediaItemCache);
            Log.d("DefaultThemeHelper", "initTheme(origin)", storyTheme, MediaItemStory.getStoryFilter(readFirstMediaItemCache), Logger.getEncodedString(MediaItemStory.getStoryBgmInfo(readFirstMediaItemCache)));
            if (TextUtils.isEmpty(storyTheme)) {
                themeUpdater.setupTheme().updateDbIfNecessary();
            } else {
                themeUpdater.setTheme(storyTheme).setFilter(MediaItemStory.getStoryFilter(readFirstMediaItemCache)).setBgm(MediaItemStory.getStoryBgmInfo(readFirstMediaItemCache)).updateDbIfNecessary();
            }
            this.mTheme = themeUpdater.getTheme();
            this.mFilter = themeUpdater.getFilter();
            this.mBgmExtraInfo = themeUpdater.getBgmExtraInfo();
            this.mEventHandler.lambda$postEvent$0(Event.ON_THEME_INITIALIZED, new Object[0]);
            Log.d("DefaultThemeHelper", "initTheme:" + str, this, themeUpdater.getSyncFlags());
        }
    }

    private MediaItem readFirstMediaItemCache() {
        MediaData mediaData = this.mView.getMediaData();
        if (mediaData == null || mediaData.getRealCount() <= 0) {
            return null;
        }
        return mediaData.readCache(0);
    }

    public void changeBgm(BgmExtraInfo bgmExtraInfo) {
        if (bgmExtraInfo == null || !bgmExtraInfo.equals(this.mBgmExtraInfo)) {
            if (bgmExtraInfo == null) {
                bgmExtraInfo = BgmHelper.createBgmExtraInfo(this.mEventHandler, this.mTheme, false, (String) null);
                Log.e("DefaultThemeHelper", "set theme bgm due to no bgm");
            }
            this.mBgmExtraInfo = bgmExtraInfo;
            this.mEventHandler.lambda$postEvent$0(Event.ON_BGM_CHANGED, bgmExtraInfo);
            Log.d("DefaultThemeHelper", "changeBgm", this.mBgmExtraInfo);
            if (SUPPORT_DEFAULT_THEME) {
                ThemeUpdater.updateBgmToDb(this.mView.getHeaderItem(), this.mBgmExtraInfo, true);
                return;
            }
            return;
        }
        Log.d("DefaultThemeHelper", "ignore changeBgm", bgmExtraInfo);
    }

    public void changeFilter(Filter filter) {
        if (filter == null || !filter.equals(this.mFilter)) {
            this.mFilter = filter;
            Log.d("DefaultThemeHelper", "changeTheme", this);
            if (SUPPORT_DEFAULT_THEME) {
                ThemeUpdater.updateFilterToDb(this.mView.getHeaderItem(), filter, true);
                return;
            }
            return;
        }
        Log.d("DefaultThemeHelper", "ignore change Filter", filter);
    }

    public void changeTheme(EffectTheme effectTheme, Filter filter, BgmExtraInfo bgmExtraInfo) {
        this.mTheme = effectTheme;
        if (filter == null) {
            filter = effectTheme.getFilter();
        }
        this.mFilter = filter;
        if (bgmExtraInfo == null) {
            this.mBgmExtraInfo = BgmHelper.createBgmExtraInfo(this.mEventHandler, this.mTheme, false, (String) null);
        } else {
            this.mBgmExtraInfo = bgmExtraInfo;
        }
        this.mEventHandler.lambda$postEvent$0(Event.ON_THEME_CHANGED, new Object[0]);
        Log.d("DefaultThemeHelper", "changeTheme", this);
        if (SUPPORT_DEFAULT_THEME) {
            ThemeUpdater.updateThemeToDb(this.mView.getHeaderItem(), this.mTheme, this.mFilter, this.mBgmExtraInfo, 1);
        }
    }

    public void checkAndUpdateTheme(Object[] objArr) {
        if (StoryHelper.isValidObject(objArr, 4)) {
            int intValue = objArr[0].intValue();
            MediaItem headerItem = this.mView.getHeaderItem();
            if (headerItem != null && headerItem.getAlbumID() == intValue) {
                EffectTheme effectTheme = objArr[1];
                Filter filter = objArr[2];
                BgmExtraInfo bgmExtraInfo = objArr[3];
                if (!effectTheme.equals(this.mTheme) || !filter.equals(this.mFilter) || !bgmExtraInfo.equals(this.mBgmExtraInfo)) {
                    this.mTheme = effectTheme;
                    this.mFilter = filter;
                    this.mBgmExtraInfo = bgmExtraInfo;
                    this.mEventHandler.lambda$postEvent$0(Event.ON_THEME_CHANGED, new Object[0]);
                }
                Log.d("DefaultThemeHelper", "checkStoryThemeUpdated true");
            }
        }
    }

    public BgmExtraInfo getBgmExtraInfo() {
        BgmExtraInfo bgmExtraInfo = this.mBgmExtraInfo;
        if (bgmExtraInfo != null && !bgmExtraInfo.isMyMusic && !bgmExtraInfo.hasUris()) {
            BgmUriService bgmUriService = (BgmUriService) this.mEventHandler.requestData(DataRequest.REQ_BGM_URI_SERVICE);
            if (bgmUriService != null) {
                bgmExtraInfo.uris = bgmUriService.getUris(bgmExtraInfo.bgmName);
                return bgmExtraInfo;
            }
            Log.e("DefaultThemeHelper", "no BgmUriService");
        }
        return bgmExtraInfo;
    }

    public EffectTheme getEffectTheme() {
        return this.mTheme;
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public boolean isCustomTheme() {
        EffectTheme matchedTheme = EffectTheme.getMatchedTheme(this.mFilter);
        EffectTheme findTheme = EffectTheme.findTheme(this.mBgmExtraInfo.bgmName);
        if (this.mFilter.equals(Filter.NONE)) {
            return false;
        }
        if (!this.mTheme.equals(matchedTheme) || !this.mTheme.equals(findTheme)) {
            return true;
        }
        return false;
    }

    public void notifyGlobalThemeChanged() {
        if (SUPPORT_DEFAULT_THEME) {
            int i2 = UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "id"));
            Blackboard.getApplicationInstance().post("global://stories/defaultTheme/changed", new Object[]{Integer.valueOf(i2), this.mTheme, this.mFilter, this.mBgmExtraInfo});
            Log.d("DefaultThemeHelper", "notifyGlobalThemeChanged", Integer.valueOf(i2));
        }
    }

    public void onDataChangedOnUi() {
        initTheme("onDataChangedOnUi");
    }

    public void recoverBgm() {
        try {
            EffectTheme effectTheme = this.mTheme;
            if (effectTheme != null) {
                BgmExtraInfo bgmExtraInfo = this.mBgmExtraInfo;
                BgmExtraInfo createBgmExtraInfo = BgmHelper.createBgmExtraInfo(this.mEventHandler, effectTheme, false, (String) null);
                this.mBgmExtraInfo = createBgmExtraInfo;
                Log.e((CharSequence) "DefaultThemeHelper", "recoverBgm", bgmExtraInfo, createBgmExtraInfo);
                this.mEventHandler.postEvent(Event.ON_BGM_CHANGED, this.mBgmExtraInfo);
                notifyGlobalThemeChanged();
                if (SUPPORT_DEFAULT_THEME) {
                    ThemeUpdater.updateBgmToDb(this.mView.getHeaderItem(), this.mBgmExtraInfo, false);
                    return;
                }
                return;
            }
            Log.e((CharSequence) "DefaultThemeHelper", "recoverBgm fail, no theme", MediaItemUtil.getSimpleLog(this.mView.getHeaderItem()));
        } catch (Exception e) {
            Log.e((CharSequence) "DefaultThemeHelper", "recoverBgm failed", (Throwable) e);
        }
    }

    public void resetTheme() {
        this.mTheme = null;
        initTheme("resetTheme");
    }

    public String toString() {
        return "DefaultThemeHelper@" + this.mTheme + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mFilter + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mBgmExtraInfo;
    }

    public void updateLastUsedBgm(String str) {
        BgmHelper.updateLastUsedBgm(str);
    }
}
