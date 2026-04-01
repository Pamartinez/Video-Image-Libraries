package com.samsung.android.gallery.app.ui.list.picker.timeline;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelinePickerPresenter extends PicturesPickerPresenter<IPicturesView> {
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public TimelinePickerPresenter(Blackboard blackboard, IPicturesView iPicturesView) {
        super(blackboard, iPicturesView);
    }

    public void handleDensityChange() {
        this.mSearchToolbarDelegate.handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
            return true;
        }
        return false;
    }

    public void moveToSearch() {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(14, Boolean.TRUE));
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.build(this);
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        this.mSearchToolbarDelegate.onDestroy();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
        toolbar.setSubtitle((CharSequence) null);
        toolbar.setNavigationIcon((Drawable) null);
    }
}
