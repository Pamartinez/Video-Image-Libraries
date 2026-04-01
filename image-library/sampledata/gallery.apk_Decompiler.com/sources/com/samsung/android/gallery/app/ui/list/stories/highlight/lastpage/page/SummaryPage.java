package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o5.C0496a;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SummaryPage extends PageHolder implements ISummaryPage {
    protected LinearLayout mContainer;
    protected View mContentParent;
    protected TextView mSubTitle;
    protected TextView mTitle;

    public SummaryPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    /* access modifiers changed from: private */
    public void onTitleClicked(View view) {
        new RenameStoryCmd().execute(this.mView.getPresenter(), this.mView.getHeaderItem());
        this.mEventHandler.lambda$postEvent$0(Event.LAST_PAGE_STOP_AUTO_SCROLL, new Object[0]);
    }

    public void bind(PageItem pageItem) {
        bindTitle(this.mTitle, this.mSubTitle);
        super.bind(pageItem);
    }

    public void bindTitle(TextView textView, TextView textView2) {
        MediaItem headerItem = this.mView.getHeaderItem();
        if (headerItem != null) {
            String title = headerItem.getTitle();
            textView.setText(title);
            ((View) textView.getParent()).setContentDescription(title);
            textView2.setText(MediaItemStory.getStoryTimeDuration(headerItem));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContainer = (LinearLayout) view.findViewById(R.id.container);
        this.mContentParent = view.findViewById(R.id.content_parent);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mSubTitle = (TextView) view.findViewById(R.id.subtitle);
        this.mTitle.setOnClickListener(new C0496a(16, this));
    }

    public void fixXPositionInRtl() {
        if (Features.isEnabled(Features.IS_RTL)) {
            Optional.ofNullable(getContent()).ifPresent(new e(2));
        }
    }

    public Activity getActivity() {
        return this.mView.getActivity();
    }

    public abstract View getContent();

    public View getContentView() {
        return this.mContentParent;
    }

    public int getSidePadding(PageSpec.Config config) {
        return getSizeWithBase(R.dimen.story_collage_content_side_padding, config.baseSize);
    }

    public void initLayout(PageSpec.Config config) {
        ViewMarginUtils.setHorizontalPadding(this.mContainer, config.sideGap);
        updateLayout(config, getSidePadding(config));
    }

    public void setContentParentTopPadding(View view, int i2) {
        ViewMarginUtils.setTopPadding(view, i2);
    }

    public void textScaleDown(TextView textView, float f) {
        textScaleDown(textView, f, 0);
    }

    public void updateContentParentLayout(View view, PageSpec.Config config) {
        setContentParentTopPadding(view, getSizeWithBase(R.dimen.story_collage_content_top_padding_ratio, config.baseSize));
        ViewUtils.setViewShape(view, 1, (float) getSizeWithBase(R.dimen.story_collage_content_round_ratio, config.baseSize));
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        updateContentParentLayout(this.mContentParent, config);
        updateTitleArea((View) this.mSubTitle.getParent(), config, i2);
        updateTitleLayout(this.mTitle, config);
        updateSubTitleLayout(this.mSubTitle, config);
        fixXPositionInRtl();
    }

    public void updateSubTitleLayout(TextView textView, PageSpec.Config config) {
        int sizeWithBase = getSizeWithBase(R.dimen.story_collage_page_sub_title_height_ratio, config.baseSize);
        ViewUtils.setViewSize(textView, (Integer) null, Integer.valueOf(sizeWithBase));
        textScaleDown(textView, (float) sizeWithBase);
    }

    public void updateTitle() {
        if (this.mView.getHeaderItem() != null) {
            this.mTitle.setText(this.mView.getHeaderItem().getTitle());
        }
    }

    public void updateTitleArea(View view, PageSpec.Config config, int i2) {
        ViewMarginUtils.setHorizontalPadding(view, getSizeWithBase(R.dimen.story_collage_page_title_side_padding_ratio, config.baseSize));
        ViewUtils.setViewSize(view, Integer.valueOf(config.width), (Integer) null);
    }

    public void updateTitleLayout(TextView textView, PageSpec.Config config) {
        int sizeWithBase = getSizeWithBase(R.dimen.story_collage_page_title_height_ratio, config.baseSize);
        ViewUtils.setViewSize((View) textView.getParent(), (Integer) null, Integer.valueOf(sizeWithBase));
        textScaleDown(textView, (float) sizeWithBase);
    }

    public void updateViewSize(View view, PageSpec.Config config, int i2) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int i7 = config.width - (i2 * 2);
            layoutParams.width = i7;
            layoutParams.height = (int) (((float) i7) * 1.25f);
            view.setLayoutParams(layoutParams);
            view.layout(0, 0, layoutParams.width, layoutParams.height);
            ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i2), Integer.valueOf(getSizeWithBase(R.dimen.story_collage_list_top_margin_ratio, config.baseSize)), Integer.valueOf(i2), Integer.valueOf(getSizeWithBase(R.dimen.story_collage_list_bottom_margin_ratio, config.baseSize)));
        }
    }

    public void textScaleDown(TextView textView, float f, int i2) {
        textView.measure(0, 0);
        int measuredHeight = textView.getMeasuredHeight() + i2;
        float textSize = textView.getTextSize();
        float f5 = (float) measuredHeight;
        float f8 = f5 - f;
        if (f8 > 0.0f) {
            textView.setTextSize(0, ((f5 - f8) / f5) * textSize);
        }
    }
}
