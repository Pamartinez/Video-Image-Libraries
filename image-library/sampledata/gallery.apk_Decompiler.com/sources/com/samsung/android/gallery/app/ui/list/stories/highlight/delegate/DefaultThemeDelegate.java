package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.DefaultThemeHelper;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import o6.k;
import o6.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultThemeDelegate extends Delegate {
    private final DefaultThemeHelper mDefaultThemeHelper;

    public DefaultThemeDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mDefaultThemeHelper = new DefaultThemeHelper(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public void changeBgm(Object... objArr) {
        this.mDefaultThemeHelper.changeBgm(objArr[0]);
    }

    /* access modifiers changed from: private */
    public void changeFilter(Object... objArr) {
        this.mDefaultThemeHelper.changeFilter(objArr[0]);
    }

    /* access modifiers changed from: private */
    public void changeTheme(Object... objArr) {
        Filter filter;
        EffectTheme effectTheme = objArr[0];
        BgmExtraInfo bgmExtraInfo = null;
        if (objArr.length >= 2) {
            filter = objArr[1];
        } else {
            filter = null;
        }
        if (objArr.length >= 3) {
            bgmExtraInfo = objArr[2];
        }
        this.mDefaultThemeHelper.changeTheme(effectTheme, filter, bgmExtraInfo);
    }

    /* access modifiers changed from: private */
    public void checkAndUpdateTheme(Object... objArr) {
        this.mDefaultThemeHelper.checkAndUpdateTheme(objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$0(DataRequest dataRequest, Object[] objArr) {
        return this.mDefaultThemeHelper;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$1(DataRequest dataRequest, Object[] objArr) {
        return this.mDefaultThemeHelper.getEffectTheme();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$2(DataRequest dataRequest, Object[] objArr) {
        return this.mDefaultThemeHelper.getFilter();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$3(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mDefaultThemeHelper.isCustomTheme());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$4(DataRequest dataRequest, Object[] objArr) {
        String str;
        BgmExtraInfo bgmExtraInfo = this.mDefaultThemeHelper.getBgmExtraInfo();
        if (bgmExtraInfo != null) {
            str = bgmExtraInfo.bgmName;
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return EffectTheme.getBgmName(0, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$5(DataRequest dataRequest, Object[] objArr) {
        return this.mDefaultThemeHelper.getBgmExtraInfo();
    }

    /* access modifiers changed from: private */
    public void notifyGlobalThemeChanged(Object... objArr) {
        this.mDefaultThemeHelper.notifyGlobalThemeChanged();
    }

    /* access modifiers changed from: private */
    public void recoverStoryBgm(Object... objArr) {
        this.mDefaultThemeHelper.recoverBgm();
    }

    /* access modifiers changed from: private */
    public void resetTheme(Object... objArr) {
        this.mDefaultThemeHelper.resetTheme();
    }

    /* access modifiers changed from: private */
    public void updateLastUsedBgm(Object... objArr) {
        this.mDefaultThemeHelper.updateLastUsedBgm(objArr[0]);
    }

    public void addListenEvent() {
        addEvent(Event.RESET_THEME, new l(this, 4));
        addEvent(Event.CHANGE_THEME, new l(this, 5));
        addEvent(Event.CHANGE_FILTER, new l(this, 6));
        addEvent(Event.CHANGE_BGM, new l(this, 7));
        addEvent(Event.CHECK_THEME_UPDATE, new l(this, 0));
        addEvent(Event.UPDATE_LAST_USED_BGM, new l(this, 1));
        addEvent(Event.NOTIFY_GLOBAL_THEME_CHANGED, new l(this, 2));
        addEvent(Event.RECOVER_STORY_BGM, new l(this, 3));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.REQ_DEFAULT_THEME_HELPER, new k(this, 0));
        addRequestProvider(DataRequest.REQ_EFFECT_THEME, new k(this, 1));
        addRequestProvider(DataRequest.REQ_FILTER_CURRENT, new k(this, 2));
        addRequestProvider(DataRequest.IS_CUSTOM_THEME, new k(this, 3));
        addRequestProvider(DataRequest.REQ_BGM_NAME, new k(this, 4));
        addRequestProvider(DataRequest.REQ_BGM_EXTRA_INFO, new k(this, 5));
    }

    public void onDataChangedOnUi() {
        this.mDefaultThemeHelper.onDataChangedOnUi();
    }
}
