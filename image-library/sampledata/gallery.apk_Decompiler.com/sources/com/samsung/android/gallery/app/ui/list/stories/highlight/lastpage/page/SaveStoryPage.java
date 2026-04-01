package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveStoryPage extends PageHolder {
    private PageSpec.Config mPageConfig = new PageSpec.Config();
    private View mSaveAsParent;

    public SaveStoryPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    /* access modifiers changed from: private */
    public void onSaveAsClicked(View view) {
        this.mEventHandler.postEvent(Event.SAVE_STORY_FROM_ON_DEMAND, new Object[0]);
    }

    public void bindView(View view) {
        super.bindView(view);
        View findViewById = view.findViewById(R.id.save_story_parent_layout_in_page);
        this.mSaveAsParent = findViewById;
        ViewUtils.setOnClickListener(findViewById.findViewById(R.id.save_story_layout_in_page), new C0496a(15, this));
    }

    public void initLayout() {
        ViewMarginUtils.setHorizontalPadding(this.itemView, this.mPageConfig.sideGap);
        ViewMarginUtils.setBottomMargin(this.itemView, this.mPageConfig.pageBottomSpace);
        ViewMarginUtils.setMargin(this.mSaveAsParent, (Integer) null, 0, (Integer) null, Integer.valueOf((getSizeWithBase(R.dimen.story_collage_page_title_height_ratio, this.mPageConfig.baseSize) * 2) / 3));
    }

    public void onBindItem() {
        this.mPageConfig = new PageSpec(this.mView, this.mRootParent).calculate();
        initLayout();
    }
}
