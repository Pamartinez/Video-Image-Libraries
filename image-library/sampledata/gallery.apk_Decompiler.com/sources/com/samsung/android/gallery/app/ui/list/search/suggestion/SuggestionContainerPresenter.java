package com.samsung.android.gallery.app.ui.list.search.suggestion;

import android.view.View;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.suggestion.ISuggestionContainerView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionContainerPresenter<V extends ISuggestionContainerView> extends MvpBasePresenter<V> {
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public SuggestionContainerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void destroySearchView() {
        this.mSearchToolbarDelegate.onDestroy();
    }

    public void handleDensityChange() {
        this.mSearchToolbarDelegate.handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 1079) {
            ((ISuggestionContainerView) this.mView).switchFragment((String) eventMessage.obj);
            return true;
        } else if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public void onBackPressed() {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(29));
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildForSuggestionContainer(this);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        destroySearchView();
        FragmentVolatileStatus.resetVolatile();
    }

    public void initMenu() {
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
