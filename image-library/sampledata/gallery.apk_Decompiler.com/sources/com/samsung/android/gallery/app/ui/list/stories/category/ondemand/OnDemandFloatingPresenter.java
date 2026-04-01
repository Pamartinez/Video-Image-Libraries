package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.view.View;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.IOnDemandFloatingView;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandFloatingPresenter<V extends IOnDemandFloatingView> extends MvpBasePresenter<V> {
    private boolean mFirstQueryExecuted = false;
    private final OnDemandHandler mOnDemandHandler = new OnDemandHandler((IOnDemandFloatingView) this.mView);
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public OnDemandFloatingPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void executeFirstQuery() {
        if (!this.mFirstQueryExecuted) {
            this.mFirstQueryExecuted = true;
            String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "my_query", "");
            int argValue2 = ArgumentsUtil.getArgValue(getLocationKey(), "type", 0);
            Log.d(this.TAG, "executeFirstQuery", Logger.getEncodedString(argValue), Integer.valueOf(argValue2));
            if (argValue2 != 8503) {
                switch (argValue2) {
                    case 1143:
                        this.mOnDemandHandler.getSearchToolbarDelegate().handleInternalEvent(SearchToolbarEvent.obtain(107, argValue));
                        return;
                    case 1144:
                        this.mOnDemandHandler.getSearchToolbarDelegate().handleInternalEvent(SearchToolbarEvent.obtain(108));
                        return;
                    case 1145:
                        OnDemandSuggestionDataManager.getInstance().update();
                        this.mOnDemandHandler.getSearchToolbarDelegate().handleInternalEvent(SearchToolbarEvent.obtain(105, argValue));
                        return;
                    default:
                        return;
                }
            } else {
                this.mSearchToolbarDelegate.handleEvent(EventMessage.obtain(8503, argValue));
            }
        }
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (this.mOnDemandHandler.handleEvent(eventMessage)) {
            return true;
        }
        if (eventMessage.what != 1146) {
            return super.handleEvent(eventMessage);
        }
        if (eventMessage.arg1 == 0) {
            ((IOnDemandFloatingView) this.mView).showResultView((String) eventMessage.obj);
        } else {
            ((IOnDemandFloatingView) this.mView).hideResultView();
        }
        return true;
    }

    public boolean isMenuInitRequired() {
        return false;
    }

    public boolean isOnDemandRunning() {
        return this.mOnDemandHandler.isOnDemandRunning();
    }

    public boolean onBackPressed() {
        return this.mOnDemandHandler.onBackPressed();
    }

    public void onConfigurationChanged() {
        this.mOnDemandHandler.onConfigurationChanged();
    }

    public void onDestroyView() {
        this.mOnDemandHandler.destroyView();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.mOnDemandHandler.onHiddenChanged(z);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mOnDemandHandler.viewCreated();
        this.mSearchToolbarDelegate = this.mOnDemandHandler.getSearchToolbarDelegate();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mSearchToolbarDelegate.onDestroy();
        this.mOnDemandHandler.destroy();
    }

    public void onViewPause() {
        super.onViewPause();
        this.mOnDemandHandler.pause();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mOnDemandHandler.resume();
        this.mSearchToolbarDelegate.onResume();
        executeFirstQuery();
    }

    public void setSearchViewClearMode(boolean z) {
        float f;
        SearchToolbarDelegate searchToolbarDelegate = this.mSearchToolbarDelegate;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        searchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(109, Float.valueOf(f)));
    }

    public void updateSearchToolbarHorizontalPadding() {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(22));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
    }
}
