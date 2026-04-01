package com.samsung.android.gallery.widget.search.searchbar;

import A4.L;
import Ba.h;
import Ca.c;
import Vb.a;
import Vb.b;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.widget.EditText;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NewSearchToolbar extends SearchToolbar {
    private InAppAssistStyler mInAppAssistStyler;
    private InstantSearchHandler mInstantSearchHandler;

    public NewSearchToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void animateToBottom() {
        int i2;
        WindowInsets rootWindowInsets = getView().getRootWindowInsets();
        int iMEInsetsBottom = WindowUtils.getIMEInsetsBottom(rootWindowInsets);
        if (iMEInsetsBottom > 0) {
            i2 = iMEInsetsBottom + WindowUtils.getSystemInsets(rootWindowInsets).bottom;
        } else {
            i2 = 0;
        }
        getView().animate().translationY((float) i2).setDuration(200).start();
    }

    private int getDimensionPixelSize(int i2) {
        return ((Integer) Optional.ofNullable(getResources()).map(new b(i2, 0)).orElse(0)).intValue();
    }

    private void initInstantSearchHandler() {
        if (this.mOptions.supportInstantSearch()) {
            this.mInstantSearchHandler = new InstantSearchHandler(false, new h(18, this));
        }
    }

    private void initSyncWithInsetsAnimation() {
        ViewGroup view = getView();
        if (view != null) {
            view.setWindowInsetsAnimationCallback(new WindowInsetsAnimation.Callback(1) {
                int mSoftInputMode = 0;

                private void adjustSoftInputMode() {
                    if (NewSearchToolbar.this.getActivity() != null && NewSearchToolbar.this.getActivity().getWindow() != null) {
                        Window window = NewSearchToolbar.this.getActivity().getWindow();
                        this.mSoftInputMode = window.getAttributes().softInputMode;
                        window.setSoftInputMode(48);
                    }
                }

                public void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                    NewSearchToolbar.super.onEnd(windowInsetsAnimation);
                    if (NewSearchToolbar.this.getActivity() != null && NewSearchToolbar.this.getActivity().getWindow() != null && this.mSoftInputMode != 0) {
                        NewSearchToolbar.this.getActivity().getWindow().setSoftInputMode(this.mSoftInputMode);
                    }
                }

                public void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                    NewSearchToolbar.super.onPrepare(windowInsetsAnimation);
                    adjustSoftInputMode();
                    NewSearchToolbar newSearchToolbar = NewSearchToolbar.this;
                    newSearchToolbar.mBehavior.onInsetsPrepare(newSearchToolbar);
                }

                public WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                    adjustSoftInputMode();
                    NewSearchToolbar newSearchToolbar = NewSearchToolbar.this;
                    newSearchToolbar.mBehavior.onInsetsProgress(newSearchToolbar, windowInsets);
                    return windowInsets;
                }
            });
            view.setOnApplyWindowInsetsListener(new c(2, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initInstantSearchHandler$3(String str, String str2) {
        onSubmitQuery();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSyncWithInsetsAnimation$1(WindowInsets windowInsets) {
        this.mBehavior.onApplyInsets(this, windowInsets);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsets lambda$initSyncWithInsetsAnimation$2(View view, WindowInsets windowInsets) {
        if (getRootView() != null) {
            getRootView().post(new a(this, windowInsets, 1));
        }
        return windowInsets;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateInsets$5(WindowInsets windowInsets) {
        this.mBehavior.onApplyInsets(this, windowInsets);
    }

    private void updateInsets() {
        WindowInsets rootWindowInsets = getRootWindowInsets();
        if (getRootView() != null && rootWindowInsets != null) {
            getRootView().post(new a(this, rootWindowInsets, 0));
        }
    }

    public void destroy() {
        super.destroy();
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (inAppAssistStyler != null) {
            inAppAssistStyler.destroy();
            this.mInAppAssistStyler = null;
        }
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler != null) {
            instantSearchHandler.reset();
            this.mInstantSearchHandler = null;
        }
        ViewGroup view = getView();
        if (view != null) {
            view.setWindowInsetsAnimationCallback((WindowInsetsAnimation.Callback) null);
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
            ViewUtils.removeSelf(view);
        }
    }

    public boolean doNotHideIMEWhenSearch() {
        if (fromInstantSearch() || super.doNotHideIMEWhenSearch()) {
            return true;
        }
        return false;
    }

    public boolean fromInstantSearch() {
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler == null || !instantSearchHandler.isInstantSearch()) {
            return false;
        }
        return true;
    }

    public int getGradientAreaHeight() {
        return Math.max(getDimensionPixelSize(R$dimen.bottom_search_toolbar_container_min_height), ((Integer) Optional.ofNullable(ViewUtils.findParentViewById(this, R$id.bottom_search_toolbar_inner_container)).map(new V8.a(3)).orElse(0)).intValue());
    }

    public ViewGroup getView() {
        return ViewUtils.findParentViewByTag(this, "search_toolbar_primary_parent");
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8502) {
            clear();
            return true;
        } else if (i2 == 8503) {
            String str = (String) eventMessage.obj;
            if (isEditableFilterViewVisible()) {
                requestFilterViewVoiceRecognition(str);
                return true;
            }
            setQuery(str, false);
            handleVoiceInput(str);
            return true;
        } else if (i2 != 8530) {
            return super.handleEvent(eventMessage);
        } else {
            animateToBottom();
            return true;
        }
    }

    public boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        boolean z;
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler == null || !instantSearchHandler.isQueryOngoing()) {
            z = false;
        } else {
            z = true;
        }
        if (z && searchToolbarEvent.what == 24) {
            this.mInstantSearchHandler.searchPendingKeyword(getQuery());
        } else if (z && searchToolbarEvent.what == 3) {
            return true;
        }
        return super.handleInternalEvent(searchToolbarEvent);
    }

    public void handleQueryOnBehavior(String str) {
        super.handleQueryOnBehavior(str);
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler != null) {
            instantSearchHandler.setLastQuery(getQuery().toString());
        }
    }

    public void initEffect() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.InAppAssistLook) && this.mInAppAssistStyler == null) {
            this.mInAppAssistStyler = new InAppAssistStyler();
        }
    }

    public void initLayout() {
        super.initLayout();
        initEffect();
    }

    public void initTextView() {
        super.initTextView();
        this.mSearchTextView.setTextSize(0, getResources().getDimension(R$dimen.bottom_search_toolbar_textview_font_size));
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (inAppAssistStyler != null) {
            inAppAssistStyler.applyTextViewStyle(this.mSearchTextView);
        } else if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            this.mSearchTextView.setTextColor(getContext().getColor(R$color.new_search_bar_text_color));
            this.mSearchTextView.setHintTextColor(getContext().getColor(R$color.new_search_bar_hint_text_color));
        }
    }

    public boolean isEnableUpButton() {
        return false;
    }

    public void moveToQueryResultView(String str) {
        super.moveToQueryResultView(str);
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler != null) {
            instantSearchHandler.reset();
        }
    }

    public void onAttached(SearchToolbarOptions searchToolbarOptions) {
        super.onAttached(searchToolbarOptions);
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (inAppAssistStyler != null) {
            inAppAssistStyler.onAttached(this, searchToolbarOptions);
        }
        initSyncWithInsetsAnimation();
        initInstantSearchHandler();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        EditText editText = this.mSearchTextView;
        if (editText != null) {
            editText.setTextSize(0, getResources().getDimension(R$dimen.bottom_search_toolbar_textview_font_size));
        }
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (inAppAssistStyler != null) {
            inAppAssistStyler.onConfigurationChanged();
        }
        updateInsets();
    }

    public void onQueryTextChanged(String str) {
        EditText editText;
        super.onQueryTextChanged(str);
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (!(inAppAssistStyler == null || (editText = this.mSearchTextView) == null)) {
            inAppAssistStyler.onQueryTextChanged(editText);
        }
        InstantSearchHandler instantSearchHandler = this.mInstantSearchHandler;
        if (instantSearchHandler != null) {
            instantSearchHandler.postSearch(getQuery());
        }
    }

    public void onResume() {
        super.onResume();
        InAppAssistStyler inAppAssistStyler = this.mInAppAssistStyler;
        if (inAppAssistStyler != null) {
            inAppAssistStyler.onResume();
        }
    }

    public void setGradientVisibility(boolean z) {
        Optional.ofNullable(ViewUtils.findParentViewById(this, R$id.bottom_search_toolbar_inner_container)).ifPresent(new L(z, 21));
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        InstantSearchHandler instantSearchHandler;
        String str;
        super.setQuery(charSequence, z);
        if (!z && (instantSearchHandler = this.mInstantSearchHandler) != null) {
            if (charSequence != null) {
                str = charSequence.toString();
            } else {
                str = "";
            }
            instantSearchHandler.setLastQuery(str);
        }
    }

    public void setVisibility(int i2) {
        ViewUtils.setVisibility(getView(), i2);
    }

    public void updateBackground(boolean z) {
    }
}
