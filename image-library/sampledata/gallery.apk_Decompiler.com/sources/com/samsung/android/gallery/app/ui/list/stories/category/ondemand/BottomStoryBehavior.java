package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import J5.a;
import U5.c;
import a6.C0418a;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.BaseSearchToolbarBehavior;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomStoryBehavior extends BaseSearchToolbarBehavior {
    final String TAG = "BottomStoryBehavior";
    private final AtomicBoolean mIsVoiceInputRequested = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final OnDemandFloatingViewDelegate<?> mOnDemandFloatingViewDelegate;
    private final PdcRecommendDelegate<?> mPdcRecommendDelegate;
    private final IMvpBaseView mView;
    private boolean mVoiceRequestFromOuter;

    public BottomStoryBehavior(IMvpBaseView iMvpBaseView) {
        super(iMvpBaseView);
        this.mView = iMvpBaseView;
        this.mOnDemandFloatingViewDelegate = new OnDemandFloatingViewDelegate<>(iMvpBaseView);
        this.mPdcRecommendDelegate = new PdcRecommendDelegate<>(iMvpBaseView);
    }

    private void applyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        int systemInsetsBottom = WindowUtils.getSystemInsetsBottom(windowInsets) + WindowUtils.getIMEInsetsBottom(windowInsets);
        setDecorViewsHeight(searchToolbar, systemInsetsBottom);
        ViewMarginUtils.setBottomMargin(searchToolbar.getView(), systemInsetsBottom);
        searchToolbar.setVisibility(0);
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_ONDEMAND_STORY_VIEW.toString();
    }

    private boolean isConsumeBackPress() {
        PdcRecommendDelegate<?> pdcRecommendDelegate = this.mPdcRecommendDelegate;
        if (pdcRecommendDelegate == null || !pdcRecommendDelegate.visible()) {
            return false;
        }
        this.mPdcRecommendDelegate.setFocusChanged(false);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDecorViewsHeight$0(int i2, SearchToolbar searchToolbar) {
        View view = this.mView.getView();
        if (view != null) {
            int height = (view.getHeight() - i2) - searchToolbar.getGradientAreaHeight();
            OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
            if (onDemandFloatingViewDelegate != null) {
                onDemandFloatingViewDelegate.setHeight(height);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSearchToolbarWithAnim$1(SearchToolbar searchToolbar) {
        setSearchToolbarVisibility(searchToolbar, true);
    }

    private void onPostVoiceButtonClicked() {
        if (!this.mVoiceRequestFromOuter) {
            this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_VOICE_ICON_CLICKED);
        }
        this.mVoiceRequestFromOuter = false;
    }

    private void onPreVoiceButtonClicked(SearchToolbar searchToolbar) {
        this.mIsVoiceInputRequested.set(true);
        setImeVisible(searchToolbar, false);
    }

    private void setDecorViewsHeight(SearchToolbar searchToolbar, int i2) {
        ViewUtils.post(this.mView.getView(), new C0418a((Object) this, i2, (Object) searchToolbar, 0));
    }

    private void setImeVisible(SearchToolbar searchToolbar, boolean z) {
        InputMethodManager inputMethodManager = (InputMethodManager) searchToolbar.getContext().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        if (z) {
            inputMethodManager.showSoftInput(searchToolbar.getSearchTextView(), 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(searchToolbar.getWindowToken(), 0);
        }
    }

    private void setNotCallShowSoftInput(SearchToolbar searchToolbar, boolean z) {
        searchToolbar.seslSetNotCallShowSoftInput(z);
    }

    /* access modifiers changed from: private */
    public void setSearchTextCursorVisibility(SearchToolbar searchToolbar, boolean z) {
        searchToolbar.getSearchTextView().setCursorVisible(z);
        if (!z) {
            int length = searchToolbar.getSearchTextView().getText().length();
            Selection.removeSelection(searchToolbar.getSearchTextView().getText());
            searchToolbar.getSearchTextView().setSelection(length, length);
        }
    }

    private void setSearchToolbarVisibility(SearchToolbar searchToolbar, boolean z) {
        if (!z) {
            searchToolbar.setQuery("", false);
        }
        OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
        if (onDemandFloatingViewDelegate != null) {
            ViewUtils.setVisibleOrGone(onDemandFloatingViewDelegate.getRootView(), z);
        }
        ViewUtils.setVisibleOrGone(searchToolbar.getView(), z);
    }

    private void showSearchToolbarWithAnim(SearchToolbar searchToolbar, String str, boolean z) {
        searchToolbar.getView().setAlpha(0.0f);
        ViewPropertyAnimator withStartAction = searchToolbar.getView().animate().alpha(1.0f).setDuration(200).withStartAction(new c(23, this, searchToolbar));
        if (z) {
            withStartAction.withEndAction(new a(searchToolbar, 8));
        } else {
            searchToolbar.setQuery(str, false);
        }
        withStartAction.start();
        searchToolbar.requestFocus();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            if (keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160) {
                return true;
            }
            return false;
        } else if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 111) {
            return true;
        } else {
            return false;
        }
    }

    public Object getData(SearchToolbar searchToolbar, SearchToolbarDataKey searchToolbarDataKey) {
        if (searchToolbarDataKey == SearchToolbarDataKey.IS_BACK_PRESS_CONSUMED) {
            return Boolean.valueOf(isConsumeBackPress());
        }
        return null;
    }

    public void handleDensityChange() {
        this.mPdcRecommendDelegate.handleDensityChange();
    }

    public boolean handleInternalEvent(SearchToolbar searchToolbar, SearchToolbarEvent searchToolbarEvent) {
        float f;
        int i2 = searchToolbarEvent.what;
        if (i2 == 103) {
            searchToolbar.requestFocus();
            setImeVisible(searchToolbar, ((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        }
        String str = "";
        if (i2 == 104) {
            searchToolbar.setQuery(str, false);
            ThreadUtil.postOnUiThreadDelayed(new a(searchToolbar, 3), 500);
            return true;
        } else if (i2 == 105) {
            showSearchToolbarWithAnim(searchToolbar, (String) searchToolbarEvent.obj, false);
            setImeVisible(searchToolbar, true);
            return true;
        } else if (i2 == 106) {
            boolean booleanValue = ((Boolean) searchToolbarEvent.obj).booleanValue();
            setNotCallShowSoftInput(searchToolbar, booleanValue);
            setSearchTextCursorVisibility(searchToolbar, !booleanValue);
            return true;
        } else if (i2 == 111) {
            setNotCallShowSoftInput(searchToolbar, ((Boolean) searchToolbarEvent.obj).booleanValue());
            return true;
        } else if (i2 == 107) {
            Object obj = searchToolbarEvent.obj;
            if (obj != null) {
                str = (String) obj;
            }
            showSearchToolbarWithAnim(searchToolbar, (String) obj, false);
            if (TextUtils.isEmpty(str)) {
                setImeVisible(searchToolbar, true);
            }
            return true;
        } else if (i2 == 108) {
            this.mVoiceRequestFromOuter = true;
            showSearchToolbarWithAnim(searchToolbar, str, true);
            return true;
        } else if (i2 == 109) {
            Object obj2 = searchToolbarEvent.obj;
            if (obj2 != null) {
                f = ((Float) obj2).floatValue();
            } else {
                f = 1.0f;
            }
            searchToolbar.getView().animate().alpha(f).setDuration(100).start();
            return true;
        } else if (i2 == 110) {
            Object obj3 = searchToolbarEvent.obj;
            if (obj3 != null) {
                str = (String) obj3;
            }
            showSearchToolbarWithAnim(searchToolbar, (String) obj3, false);
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1142, str));
            return true;
        } else if (i2 == 30) {
            onPreVoiceButtonClicked(searchToolbar);
            return true;
        } else if (i2 != 31) {
            return this.mPdcRecommendDelegate.handleInternalEvent(searchToolbarEvent);
        } else {
            onPostVoiceButtonClicked();
            return true;
        }
    }

    public void handleQuery(String str, boolean z) {
        if (!this.mPdcRecommendDelegate.visible()) {
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1137, str));
        }
        this.mView.postAnalyticsLog(getScreenId(), AnalyticsEventId.EVENT_ONDEMAND_STORY_TRY_TO_CREATE);
    }

    public void initPdcRecommendViewListener(final SearchToolbar searchToolbar) {
        this.mPdcRecommendDelegate.setListener(new PdcRecommendDelegate.Listener() {
            public void onLaunchFullList() {
                searchToolbar.handleInternalEvent(SearchToolbarEvent.obtain(103, Boolean.FALSE));
            }

            public void onVisibilityChanged(boolean z) {
                boolean z3;
                if (BottomStoryBehavior.this.mOnDemandFloatingViewDelegate != null) {
                    OnDemandFloatingViewDelegate c5 = BottomStoryBehavior.this.mOnDemandFloatingViewDelegate;
                    if (!z || !TextUtils.isEmpty(searchToolbar.getQuery())) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    c5.setVisibility(z3, false);
                    BottomStoryBehavior.this.setSearchTextCursorVisibility(searchToolbar, true);
                }
            }

            public void onClosed(boolean z) {
            }
        });
    }

    public void onApplyInsets(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
        this.mPdcRecommendDelegate.onApplyInset();
        if (this.mView.getView() != null && WindowUtils.isIMEVisible(this.mView.getView().getRootWindowInsets())) {
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1146, 4, (Object) null));
        }
    }

    public void onAttached(SearchToolbar searchToolbar, SearchToolbarOptions searchToolbarOptions) {
        OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
        if (onDemandFloatingViewDelegate != null) {
            onDemandFloatingViewDelegate.onAttached(searchToolbar.getView());
        }
        this.mPdcRecommendDelegate.onAttached(searchToolbar.getView());
        initPdcRecommendViewListener(searchToolbar);
        searchToolbar.setGradientVisibility(false);
        searchToolbar.setVisibility(8);
    }

    public void onDestroy(SearchToolbar searchToolbar) {
        OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
        if (onDemandFloatingViewDelegate != null) {
            onDemandFloatingViewDelegate.onDestroy();
        }
        this.mPdcRecommendDelegate.onDestroy();
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        boolean z3;
        OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
        if (onDemandFloatingViewDelegate != null) {
            if (!z || !TextUtils.isEmpty(searchToolbar.getQuery())) {
                z3 = false;
            } else {
                z3 = true;
            }
            onDemandFloatingViewDelegate.setVisibility(z3, z);
        }
        if (!z) {
            this.mPdcRecommendDelegate.setFocusChanged(false);
        }
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
        if (z) {
            searchToolbar.clearFocus();
            searchToolbar.setVisibility(8);
        }
    }

    public void onInsetsProgress(SearchToolbar searchToolbar, WindowInsets windowInsets) {
        applyInsets(searchToolbar, windowInsets);
    }

    public void onQueryTextChanged(SearchToolbar searchToolbar, String str) {
        if (searchToolbar.hasFocus()) {
            OnDemandFloatingViewDelegate<?> onDemandFloatingViewDelegate = this.mOnDemandFloatingViewDelegate;
            if (onDemandFloatingViewDelegate != null) {
                onDemandFloatingViewDelegate.setVisibility(TextUtils.isEmpty(str), false);
            }
            PdcRecommendDelegate<?> pdcRecommendDelegate = this.mPdcRecommendDelegate;
            if (pdcRecommendDelegate != null) {
                pdcRecommendDelegate.setFocusChanged(false);
            }
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1146, 4, (Object) null));
        }
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
    }
}
