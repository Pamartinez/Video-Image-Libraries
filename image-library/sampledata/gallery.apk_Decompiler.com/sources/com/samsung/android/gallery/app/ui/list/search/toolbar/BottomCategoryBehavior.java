package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BottomCategoryBehavior extends BottomSearchBehavior {
    public BottomCategoryBehavior(IBaseListView iBaseListView, boolean z) {
        super(iBaseListView, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbar$0(View view) {
        BlackboardUtils.publishBackKeyEvent(this.mView.getBlackboard());
    }

    public void updateToolbar(SearchToolbar searchToolbar, String str) {
        GalleryToolbar toolbar = this.mView.getToolbar();
        if (toolbar != null) {
            if (this.mIsPickMode) {
                toolbar.setTitle(PickerUtil.getUsageTitle(this.mView.getBlackboard()));
                toolbar.setSubtitle((CharSequence) null);
                this.mView.getPresenter().setNavigationUpButton(toolbar);
                toolbar.setNavigationOnClickListener(new a(this, 0));
                return;
            }
            this.mView.getPresenter().setNavigationUpButton(toolbar);
            toolbar.setTitle(str);
        }
    }

    public void onHiddenChange(SearchToolbar searchToolbar, boolean z) {
    }
}
