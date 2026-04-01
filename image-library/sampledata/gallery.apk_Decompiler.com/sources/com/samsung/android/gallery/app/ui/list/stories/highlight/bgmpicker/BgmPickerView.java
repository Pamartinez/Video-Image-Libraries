package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.BgmListAdapter;
import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import j6.C0473b;
import j6.C0474c;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmPickerView implements BgmListAdapter.Listener, BgmUpdateListener {
    private static final boolean USE_CONTENT_PROVIDER = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider);
    private BgmListView mBgmListView;
    private final BgmStateHandler mBgmStateHandler;
    private final EventHandler mEventHandler;
    private View mLayout;
    private ThemeListView mThemeListView;
    private View mTipCard;
    private View mTipCardDownloadAllBtn;
    private View mTipCardNotNowBtn;
    private View mTipCardProgressView;
    private final IStoryHighlightView mView;
    private WindowInsets mWindowInsets;

    public BgmPickerView(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        EventHandler eventHandler = iStoryHighlightView.getEventHandler();
        this.mEventHandler = eventHandler;
        this.mBgmStateHandler = new BgmStateHandler(eventHandler, this);
    }

    private boolean checkAllDownloaded() {
        if (!USE_CONTENT_PROVIDER || !this.mBgmStateHandler.isAllDownloaded()) {
            return false;
        }
        closeTipCard();
        this.mView.invalidateOptionsMenu();
        return true;
    }

    private void closeTipCard() {
        if (isTipCardVisible()) {
            ViewUtils.setVisibility(this.mTipCard, 8);
            PreferenceCache.StoryHighlightBgmTipCard.setBoolean(false);
            this.mBgmListView.updateHeight(false);
        }
    }

    private boolean enableTipCard() {
        if (!PreferenceCache.StoryHighlightBgmTipCard.getBoolean() || !PreferenceCache.StoryHighlightBgmDownloadAll.getBoolean() || this.mBgmStateHandler.isAllDownloaded()) {
            return false;
        }
        return true;
    }

    private boolean equalInsets(WindowInsets windowInsets, WindowInsets windowInsets2) {
        if (WindowUtils.getSystemInsetsTop(windowInsets) == WindowUtils.getSystemInsetsTop(windowInsets2) && WindowUtils.getSystemInsetsBottom(windowInsets) == WindowUtils.getSystemInsetsBottom(windowInsets2)) {
            return true;
        }
        return false;
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_BGM_PICKER.toString();
    }

    private void initTipCardView(View view) {
        if (USE_CONTENT_PROVIDER && enableTipCard()) {
            View findViewById = view.findViewById(R.id.tip_card);
            this.mTipCard = findViewById;
            this.mTipCardProgressView = findViewById.findViewById(R.id.progress_circle);
            View findViewById2 = this.mTipCard.findViewById(R.id.not_now_view);
            this.mTipCardNotNowBtn = findViewById2;
            findViewById2.setOnClickListener(new C0474c(this, 0));
            View findViewById3 = this.mTipCard.findViewById(R.id.download_all_view);
            this.mTipCardDownloadAllBtn = findViewById3;
            findViewById3.setOnClickListener(new C0474c(this, 1));
            ViewUtils.setVisibility(this.mTipCard, 0);
        }
    }

    private void initViewInternal(ViewGroup viewGroup, EffectTheme effectTheme, int i2) {
        this.mThemeListView = new ThemeListView(viewGroup, new C0473b(this, 0));
        this.mBgmListView = new BgmListView(this.mView, viewGroup, effectTheme, this);
        initTipCardView(viewGroup);
        this.mBgmListView.setExpandOffset(i2);
        this.mBgmListView.updateHeight(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdated$0(String str) {
        if (isDownloaded(str) && this.mBgmStateHandler.handleDownloaded(str)) {
            Log.d("BgmPickerView", "onUpdated requestReplaceBgm");
            this.mBgmListView.requestReplaceBgm(str);
        }
        this.mBgmListView.notifyItemChanged(str);
    }

    /* access modifiers changed from: private */
    public void onSelectTheme(EffectTheme effectTheme) {
        this.mBgmListView.onSelectTheme(effectTheme);
        this.mView.postAnalyticsLog(getScreenId(), effectTheme.getEventId());
    }

    /* access modifiers changed from: private */
    public void onTipCardDownloadAllClicked(View view) {
        if (downloadAllBgm()) {
            updateTipCardProgressVisibility(true);
        }
        this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_STORY_BGM_TIP_CARD_DOWNLOAD_ALL);
    }

    /* access modifiers changed from: private */
    public void onTipCardNotNowClicked(View view) {
        closeTipCard();
        this.mView.invalidateOptionsMenu();
        this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_STORY_BGM_TIP_CARD_NOT_NOW);
    }

    private void updateTipCardProgressVisibility(boolean z) {
        ViewUtils.setVisibleOrInvisible(this.mTipCardProgressView, z);
        ViewUtils.setVisibleOrInvisible(this.mTipCardDownloadAllBtn, !z);
        ViewUtils.setViewEnabled(this.mTipCardNotNowBtn, !z);
    }

    public void destroy() {
        this.mBgmStateHandler.destroy();
    }

    public boolean downloadAllBgm() {
        if (!this.mBgmStateHandler.requestDownloadAll()) {
            return false;
        }
        this.mView.invalidateOptionsMenu();
        return true;
    }

    public void handleDensityChange(int i2, int i7) {
        View view = this.mLayout;
        if (view != null && this.mThemeListView != null && this.mBgmListView != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            View d = C0086a.d(viewGroup, R.layout.story_bgm_picker_layout, viewGroup, false);
            ViewUtils.replaceView(viewGroup, this.mLayout, d);
            this.mLayout = d;
            int focusedPosition = this.mThemeListView.getFocusedPosition();
            String focusedBgm = this.mBgmListView.getFocusedBgm();
            initViewInternal((ViewGroup) this.mLayout, this.mEventHandler.getEffectTheme(), i7);
            this.mThemeListView.setFocus(focusedPosition);
            if (!TextUtils.isEmpty(focusedBgm)) {
                this.mBgmListView.setFocus(focusedBgm);
            }
        }
    }

    public void handleResolutionChange(int i2, int i7) {
        this.mThemeListView.handleResolutionChange();
        this.mBgmListView.setExpandOffset(i7);
        this.mBgmListView.updateHeight(true);
    }

    public void initView(ViewGroup viewGroup, EffectTheme effectTheme, int i2) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.story_bgm_picker_layout, (ViewGroup) viewGroup.findViewById(R.id.story_highlight_bgm_container), true);
        this.mLayout = inflate;
        initViewInternal((ViewGroup) inflate, effectTheme, i2);
    }

    public boolean isDownloaded(String str) {
        return this.mBgmStateHandler.isDownloaded(str);
    }

    public boolean isDownloading(String str) {
        return this.mBgmStateHandler.isDownloading(str);
    }

    public boolean isPlayable() {
        return ((Boolean) this.mEventHandler.requestData(DataRequest.IS_BGM_PLAYABLE, Boolean.FALSE)).booleanValue();
    }

    public boolean isTipCardVisible() {
        return ViewUtils.isVisible(this.mTipCard);
    }

    public void onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (!equalInsets(this.mWindowInsets, windowInsets)) {
            this.mWindowInsets = windowInsets;
            this.mBgmListView.updateHeight(false);
        }
    }

    public void onDownloaded(boolean z, String str, ArrayList<Uri> arrayList) {
        Log.d("BgmPickerView", "onDownloaded", str, Boolean.valueOf(z));
        if (!z) {
            Utils.showToast(AppResources.getAppContext(), (int) R.string.could_not_download_track);
        } else if (this.mBgmStateHandler.handleDownloaded(str)) {
            this.mBgmListView.requestReplaceBgm(str);
        }
        this.mBgmListView.notifyItemChanged(str);
        checkAllDownloaded();
    }

    public void onDownloadedAll(boolean z) {
        boolean checkAllDownloaded = checkAllDownloaded();
        if (!checkAllDownloaded && !z) {
            this.mBgmStateHandler.updateDownloadAllAvailable(true);
            if (isTipCardVisible()) {
                updateTipCardProgressVisibility(false);
            }
            this.mView.invalidateOptionsMenu();
            Utils.showToast(AppResources.getAppContext(), (int) R.string.could_not_download_tracks);
        }
        this.mView.getBlackboard().post("command://DownloadAllBgmComplete", (Object) null);
        Log.d("BgmPickerView", "handleAllDownloaded", Boolean.valueOf(checkAllDownloaded), Boolean.valueOf(z));
    }

    public void onHidden() {
        this.mBgmStateHandler.requestCancelDownload();
    }

    public void onSelect(BgmItemViewHolder bgmItemViewHolder) {
        String title = bgmItemViewHolder.getTitle();
        if (!isDownloading(title)) {
            if (!this.mBgmStateHandler.isDownloaded(title)) {
                if (this.mView.setInputBlock("BgmPickerView_onSelect", StatusCodes.INPUT_MISSING)) {
                    this.mBgmStateHandler.requestDownload(title);
                } else {
                    Log.d("BgmPickerView", "input block for previous download");
                }
            } else if (this.mBgmStateHandler.requestReplaceBgm(title)) {
                this.mBgmListView.setFocus(title);
            }
            this.mBgmListView.notifyItemChanged(title);
            this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_STORY_BGM_SELECT);
        }
    }

    public void onUpdated(ArrayList<String> arrayList) {
        Log.d("BgmPickerView", "onUpdated lastSelectedBgm" + this.mBgmStateHandler.getLastSelectedBgm());
        arrayList.forEach(new C0473b(this, 1));
        checkAllDownloaded();
    }

    public void resetFocus() {
        this.mBgmListView.setFocus("");
    }

    public void updateCurrentBgm() {
        String str = (String) this.mEventHandler.requestData(DataRequest.REQ_BGM_NAME);
        this.mThemeListView.setFocus(str);
        this.mBgmListView.setFocus(str);
    }
}
