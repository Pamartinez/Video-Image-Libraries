package com.samsung.android.gallery.widget.search.searchbar;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchToolbarOptions {
    private SearchToolbarBehavior mBehavior;
    private boolean mClearFocus;
    private ViewGroup mContainer;
    private ViewGroup mCustomBlurTargetView;
    private boolean mDisableDirectWriting;
    private boolean mDoNotHideIMEWhenSearch;
    private boolean mEnableFiltersView;
    private boolean mEnableSuggestionHint;
    private boolean mFromInstantSearch;
    private boolean mHideCursor;
    private boolean mInflateWithGoneState;
    private boolean mIsAttachOnGalleryToolbar;
    private boolean mIsFocusable;
    private boolean mIsMoreOptionsVisible;
    private SearchToolbar.OptionMenuListener mOptionMenuListener;
    private int mSearchLayout;
    private boolean mSupportBlurBackground;
    private boolean mSupportInstantSearch;
    private boolean mSupportMoreButton;
    private boolean mSupportMultiKeywordSearch;
    private boolean mUseCustomQueryHandling;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public SearchToolbarBehavior mBehavior = new EmptyBehavior();
        /* access modifiers changed from: private */
        public boolean mClearFocus;
        /* access modifiers changed from: private */
        public ViewGroup mContainer;
        private String mContainerViewTag;
        /* access modifiers changed from: private */
        public ViewGroup mCustomBlurTargetView;
        /* access modifiers changed from: private */
        public boolean mDisableDirectWriting;
        /* access modifiers changed from: private */
        public boolean mDoNotHideIMEWhenSearch;
        /* access modifiers changed from: private */
        public boolean mEnableFiltersView;
        /* access modifiers changed from: private */
        public boolean mEnableSuggestionHint;
        /* access modifiers changed from: private */
        public boolean mFromInstantSearch;
        /* access modifiers changed from: private */
        public boolean mHideCursor;
        /* access modifiers changed from: private */
        public boolean mInflateWithGoneState;
        /* access modifiers changed from: private */
        public boolean mIsAttachOnGalleryToolbar = true;
        /* access modifiers changed from: private */
        public boolean mIsFocusable;
        /* access modifiers changed from: private */
        public boolean mIsMoreOptionsVisible;
        /* access modifiers changed from: private */
        public SearchToolbar.OptionMenuListener mOptionMenuListener;
        /* access modifiers changed from: private */
        public int mSearchLayout = R$layout.search_toolbar;
        /* access modifiers changed from: private */
        public boolean mSupportBlurBackground;
        /* access modifiers changed from: private */
        public boolean mSupportInstantSearch = PreferenceFeatures.OneUi8x.INSTANT_SEARCH;
        /* access modifiers changed from: private */
        public boolean mSupportMoreButton = true;
        /* access modifiers changed from: private */
        public boolean mSupportMultiKeywordSearch;
        /* access modifiers changed from: private */
        public boolean mUseCustomQueryHandling;

        private void findContainerViewByTag(View view) {
            ViewGroup viewGroup = (ViewGroup) view.findViewWithTag(this.mContainerViewTag);
            if (viewGroup != null) {
                ViewUtils.setVisibleOrGone(view.findViewWithTag("legacy_search_toolbar_containable"), !"new_search_toolbar_containable".equals(this.mContainerViewTag));
                this.mContainer = viewGroup;
                this.mIsAttachOnGalleryToolbar = false;
            }
        }

        public SearchToolbarOptions build(View view) {
            if (view == null) {
                return new SearchToolbarOptions(this, 0);
            }
            findContainerViewByTag(view);
            return new SearchToolbarOptions(this, 0);
        }

        public Builder disableInstantSearch() {
            this.mSupportInstantSearch = false;
            return this;
        }

        public Builder disableMoreButton() {
            this.mSupportMoreButton = false;
            return this;
        }

        public Builder doNotHideIMEWhenSearch() {
            this.mDoNotHideIMEWhenSearch = true;
            return this;
        }

        public Builder enableFiltersView() {
            this.mEnableFiltersView = true;
            if (Features.isEnabled(Features.SUPPORT_MULTI_KEYWORD_SEARCH)) {
                this.mSupportMultiKeywordSearch = true;
            }
            return this;
        }

        public Builder fromInstantSearch(boolean z) {
            this.mFromInstantSearch = z;
            return this;
        }

        public Builder inflateWithGoneState() {
            this.mInflateWithGoneState = true;
            return this;
        }

        public Builder setBehavior(SearchToolbarBehavior searchToolbarBehavior) {
            this.mBehavior = searchToolbarBehavior;
            return this;
        }

        public Builder setClearFocus() {
            this.mClearFocus = true;
            return this;
        }

        public Builder setContainerViewWithTag(String str) {
            this.mContainerViewTag = str;
            return this;
        }

        public Builder setCustomBlurTargetView(ViewGroup viewGroup) {
            if (viewGroup != null) {
                this.mCustomBlurTargetView = (ViewGroup) viewGroup.getParent();
            }
            return this;
        }

        public Builder setDisableDirectWriting() {
            this.mDisableDirectWriting = true;
            return this;
        }

        public Builder setEnableSuggestionHint() {
            this.mEnableSuggestionHint = true;
            return this;
        }

        public Builder setFocusable(boolean z) {
            this.mIsFocusable = z;
            return this;
        }

        public Builder setHideCursor() {
            this.mHideCursor = true;
            return this;
        }

        public Builder setMoreOptionVisible() {
            this.mIsMoreOptionsVisible = true;
            return this;
        }

        public Builder setOptionMenuListener(Object obj) {
            if (obj instanceof SearchToolbar.OptionMenuListener) {
                this.mOptionMenuListener = (SearchToolbar.OptionMenuListener) obj;
            }
            return this;
        }

        public Builder setSearchLayout(int i2) {
            this.mSearchLayout = i2;
            return this;
        }

        public Builder setShowCursor() {
            this.mHideCursor = false;
            return this;
        }

        public Builder useCustomQueryHandling() {
            this.mUseCustomQueryHandling = true;
            return this;
        }
    }

    public /* synthetic */ SearchToolbarOptions(Builder builder, int i2) {
        this(builder);
    }

    public static SearchToolbarOptions buildEmpty() {
        return new SearchToolbarOptions();
    }

    public boolean doNotHideIMEWhenSearch() {
        return this.mDoNotHideIMEWhenSearch;
    }

    public boolean fromInstantSearch() {
        return this.mFromInstantSearch;
    }

    public SearchToolbarBehavior getBehavior() {
        return this.mBehavior;
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    public ViewGroup getCustomBlurTargetView() {
        return this.mCustomBlurTargetView;
    }

    public SearchToolbar.OptionMenuListener getOptionMenuListener() {
        return this.mOptionMenuListener;
    }

    public int getSearchLayout() {
        return this.mSearchLayout;
    }

    public boolean isAttachOnGalleryToolbar() {
        return this.mIsAttachOnGalleryToolbar;
    }

    public boolean isClearFocus() {
        return this.mClearFocus;
    }

    public boolean isDisableDirectWriting() {
        return this.mDisableDirectWriting;
    }

    public boolean isEnableFiltersView() {
        return this.mEnableFiltersView;
    }

    public boolean isEnableSuggestionHint() {
        return this.mEnableSuggestionHint;
    }

    public boolean isFocusable() {
        return this.mIsFocusable;
    }

    public boolean isHideCursor() {
        return this.mHideCursor;
    }

    public boolean isInflateWithGoneState() {
        return this.mInflateWithGoneState;
    }

    public boolean isMoreOptionVisible() {
        return this.mIsMoreOptionsVisible;
    }

    public boolean supportBlurBackground() {
        return this.mSupportBlurBackground;
    }

    public boolean supportInstantSearch() {
        return this.mSupportInstantSearch;
    }

    public boolean supportMoreButton() {
        return this.mSupportMoreButton;
    }

    public boolean supportMultiKeywordSearch() {
        return this.mSupportMultiKeywordSearch;
    }

    public String toString() {
        char c5;
        char c6;
        char c8;
        char c10;
        char c11;
        char c12;
        StringBuilder sb2 = new StringBuilder("SearchToolbarOptions[");
        if (this.mIsFocusable) {
            c5 = 'F';
        } else {
            c5 = 'f';
        }
        sb2.append(c5);
        if (this.mHideCursor) {
            c6 = 'H';
        } else {
            c6 = 'h';
        }
        sb2.append(c6);
        if (this.mEnableSuggestionHint) {
            c8 = 'S';
        } else {
            c8 = 's';
        }
        sb2.append(c8);
        if (this.mIsAttachOnGalleryToolbar) {
            c10 = 'G';
        } else {
            c10 = 'g';
        }
        sb2.append(c10);
        if (this.mDisableDirectWriting) {
            c11 = 'd';
        } else {
            c11 = 'D';
        }
        sb2.append(c11);
        if (this.mIsMoreOptionsVisible) {
            c12 = 'M';
        } else {
            c12 = 'm';
        }
        sb2.append(c12);
        sb2.append("]");
        return sb2.toString();
    }

    public boolean useCustomQueryHandling() {
        return this.mUseCustomQueryHandling;
    }

    private SearchToolbarOptions(Builder builder) {
        this.mBehavior = new EmptyBehavior();
        this.mBehavior = builder.mBehavior;
        this.mOptionMenuListener = builder.mOptionMenuListener;
        this.mContainer = builder.mContainer;
        this.mSearchLayout = builder.mSearchLayout;
        this.mEnableSuggestionHint = builder.mEnableSuggestionHint;
        this.mClearFocus = builder.mClearFocus;
        this.mIsFocusable = builder.mIsFocusable;
        this.mHideCursor = builder.mHideCursor;
        this.mDisableDirectWriting = builder.mDisableDirectWriting;
        this.mEnableFiltersView = builder.mEnableFiltersView;
        this.mSupportMultiKeywordSearch = builder.mSupportMultiKeywordSearch;
        this.mIsAttachOnGalleryToolbar = builder.mIsAttachOnGalleryToolbar;
        this.mSupportMoreButton = builder.mSupportMoreButton;
        this.mSupportInstantSearch = builder.mSupportInstantSearch;
        this.mIsMoreOptionsVisible = builder.mIsMoreOptionsVisible;
        this.mUseCustomQueryHandling = builder.mUseCustomQueryHandling;
        this.mDoNotHideIMEWhenSearch = builder.mDoNotHideIMEWhenSearch;
        this.mSupportBlurBackground = builder.mSupportBlurBackground;
        this.mCustomBlurTargetView = builder.mCustomBlurTargetView;
        this.mFromInstantSearch = builder.mFromInstantSearch;
        this.mInflateWithGoneState = builder.mInflateWithGoneState;
    }

    private SearchToolbarOptions() {
        this.mBehavior = new EmptyBehavior();
    }
}
