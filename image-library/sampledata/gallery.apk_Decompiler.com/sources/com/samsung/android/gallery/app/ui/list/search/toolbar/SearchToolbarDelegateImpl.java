package com.samsung.android.gallery.app.ui.list.search.toolbar;

import J5.a;
import android.content.Context;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchToolbarDelegateImpl implements SearchToolbarDelegate {
    private final SearchToolbarInflater mInflater;
    private final SearchToolbarOptions mOptions;
    private SearchToolbar mSearchToolbar;
    private final IMvpBaseView mView;

    public SearchToolbarDelegateImpl(IMvpBaseView iMvpBaseView, SearchToolbarOptions searchToolbarOptions, SearchToolbarInflater searchToolbarInflater) {
        this.mView = iMvpBaseView;
        this.mOptions = searchToolbarOptions;
        this.mInflater = searchToolbarInflater;
        this.mSearchToolbar = searchToolbarInflater.inflate(iMvpBaseView, searchToolbarOptions);
        if (PocFeatures.ASK_SCREENSHOT && LocationKey.isAskScreenshot(iMvpBaseView.getLocationKey())) {
            Optional.ofNullable(iMvpBaseView.getContext()).ifPresent(new c(1, this));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Context context) {
        this.mSearchToolbar.setQueryHint(context.getString(R.string.ask_hint_what_are_you_looking_for));
    }

    private void swapSearchToolbar() {
        if (this.mSearchToolbar != null) {
            SearchToolbar inflate = this.mInflater.inflate(this.mView, this.mOptions);
            inflate.replace(this.mSearchToolbar);
            this.mSearchToolbar = inflate;
        }
    }

    public void clearFocus() {
        this.mSearchToolbar.clearFocus();
    }

    public Object getData(SearchToolbarDataKey searchToolbarDataKey) {
        return this.mSearchToolbar.getData(searchToolbarDataKey);
    }

    public int getDimAreaHeight() {
        return this.mSearchToolbar.getGradientAreaHeight();
    }

    public void handleDensityChange() {
        swapSearchToolbar();
        this.mOptions.getBehavior().handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        SearchToolbar searchToolbar = this.mSearchToolbar;
        if (searchToolbar == null || !searchToolbar.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public boolean handleInternalEvent(SearchToolbarEvent searchToolbarEvent) {
        SearchToolbar searchToolbar = this.mSearchToolbar;
        if (searchToolbar == null || !searchToolbar.handleInternalEvent(searchToolbarEvent)) {
            return false;
        }
        return true;
    }

    public boolean hasFocus() {
        return this.mSearchToolbar.hasFocus();
    }

    public void initMoreOptions(SearchToolbar.OptionMenuListener optionMenuListener) {
        this.mSearchToolbar.setOptionMenuListener(optionMenuListener);
        this.mSearchToolbar.createPopupMenu();
        this.mSearchToolbar.initMoreButton();
        this.mSearchToolbar.setMoreButtonVisibility(true);
    }

    public boolean isVisible() {
        return ViewUtils.isShown(this.mSearchToolbar);
    }

    public void onDestroy() {
        this.mSearchToolbar.destroy();
    }

    public void onResume() {
        this.mSearchToolbar.onResume();
    }

    public void query(String str, boolean z) {
        this.mSearchToolbar.setQuery(str, z);
        if (z) {
            SearchToolbar searchToolbar = this.mSearchToolbar;
            Objects.requireNonNull(searchToolbar);
            searchToolbar.post(new a(searchToolbar, 3));
        }
    }

    public String toString() {
        return "[" + this.mView.getLocationKey() + ArcCommonLog.TAG_COMMA + this.mOptions.isAttachOnGalleryToolbar() + "]";
    }
}
