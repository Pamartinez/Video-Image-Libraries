package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LegacySuggestionContainerBehavior extends BaseSearchToolbarBehavior {
    private final boolean mIsPickMode;
    private Runnable mRunnable;
    private final IMvpBaseView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SwitchState {
        NONE,
        TO_AUTOCOMPLETE,
        TO_RECOMMENDATION
    }

    public LegacySuggestionContainerBehavior(IMvpBaseView iMvpBaseView, boolean z) {
        super(iMvpBaseView);
        this.mView = iMvpBaseView;
        this.mIsPickMode = z;
    }

    private SwitchState checkSwitchState(String str) {
        if (TextUtils.isEmpty(str)) {
            return SwitchState.TO_RECOMMENDATION;
        }
        if (isRecommendationFragment()) {
            return SwitchState.TO_AUTOCOMPLETE;
        }
        return SwitchState.NONE;
    }

    private String composeTargetKey(SwitchState switchState, String str) {
        if (switchState == SwitchState.TO_RECOMMENDATION) {
            return "location://search/fileList/Recommendation";
        }
        if (switchState == SwitchState.TO_AUTOCOMPLETE) {
            return ArgumentsUtil.appendArgs("location://search/AutoComplete", "keyword", str);
        }
        return null;
    }

    private boolean isRecommendationFragment() {
        String readLocationKeyCurrent = BlackboardUtils.readLocationKeyCurrent(this.mView.getBlackboard());
        if (readLocationKeyCurrent == null || !readLocationKeyCurrent.startsWith("location://search/fileList/Recommendation")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onQueryTextChanged$0(String str) {
        SwitchState checkSwitchState = checkSwitchState(str);
        if (checkSwitchState != SwitchState.NONE) {
            switchFragment(composeTargetKey(checkSwitchState, str));
        }
        sendKeywordChangedCommandIfNeeded(checkSwitchState, str);
    }

    private boolean needToCollapseClipboardArea() {
        if (!this.mIsPickMode) {
            return false;
        }
        if (this.mView.isLandscape() || this.mView.isInMultiWindowMode()) {
            return true;
        }
        return false;
    }

    private void sendKeywordChangedCommandIfNeeded(SwitchState switchState, String str) {
        if (switchState == SwitchState.NONE || !Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE)) {
            this.mView.getBlackboard().post("command://event/KeywordChanged", str);
        }
    }

    private void switchFragment(String str) {
        this.mView.getBlackboard().postEvent(EventMessage.obtain(1079, str));
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        return false;
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        searchToolbar.setQuery(ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mView.getBlackboard()), "keyword", ""), false);
        searchToolbar.requestFocus();
        searchToolbar.setImeVisibility(true);
        searchToolbar.updateBackground(false);
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        this.mView.postAnalyticsLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString(), AnalyticsEventId.EVENT_SEARCH_ACTIONBAR_DELETE_BTN);
        searchToolbar.setQuery((CharSequence) null, false);
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        searchToolbar.setImeVisibility(z);
        if (z && needToCollapseClipboardArea()) {
            this.mView.getBlackboard().post("command://OperateClipboardArea", Boolean.FALSE);
        }
    }

    public void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
        Runnable runnable = this.mRunnable;
        if (runnable != null) {
            ThreadUtil.removeCallbackOnUiThread(runnable);
        }
        e eVar = new e(this, str, 1);
        this.mRunnable = eVar;
        ThreadUtil.postOnUiThreadDelayed(eVar, 100);
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
    }
}
