package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import A4.Q;
import a8.d;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.story.SaveOnDemandStoryCmd;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.OnDemandPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o4.a;
import o6.t;
import r6.C0513c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandPage extends SummaryPage {
    private ViewGroup mContent;
    private ContentHolder mContentHolder;
    private OnDemandPageItem mItem;
    private View mRewritePromptBtn;
    private View mSaveBtn;
    private TitleEditDelegate mTitleEditDelegate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContentHolder extends PreviewViewHolder {
        public ContentHolder(View view, int i2) {
            super(view, i2);
        }

        public ThumbKind getThumbKind() {
            return ThumbKind.LARGE_KIND;
        }
    }

    public OnDemandPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private void bindItemView(ViewGroup viewGroup) {
        viewGroup.addView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.story_highlight_lastpage_on_demand_item_layout, viewGroup, false));
        this.mContentHolder = new ContentHolder(this.mContent, 0);
    }

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBindThumbnail$2(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(listViewHolder, mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRewritePrompt$1() {
        IStoryHighlightView iStoryHighlightView = this.mView;
        if (iStoryHighlightView != null) {
            iStoryHighlightView.getBlackboard().postEvent(EventMessage.obtain(1143));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveClick$0(Object obj) {
        this.mView.finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$3(Bitmap bitmap, MediaItem mediaItem, ListViewHolder listViewHolder) {
        if (bitmap != null) {
            if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                mediaItem.setBroken(true);
            }
            listViewHolder.bindImage(bitmap);
            return;
        }
        mediaItem.setBroken(true);
        listViewHolder.bindImage(getBrokenImage(mediaItem));
    }

    private void loadBindThumbnail(MediaItem mediaItem, ListViewHolder listViewHolder) {
        ThumbKind thumbKind = listViewHolder.getThumbKind();
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(listViewHolder, mediaItem, memCache);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new Q((Object) this, (Object) listViewHolder, (Object) mediaItem, 25));
        }
    }

    /* access modifiers changed from: private */
    public void onRewritePrompt(View view) {
        ThreadUtil.postOnUiThreadDelayed(new t(17, this), 100);
        this.mView.finish();
    }

    /* access modifiers changed from: private */
    public void onSaveClick(View view) {
        new SaveOnDemandStoryCmd().addExecuteListener(new a(10, this)).execute(this.mView.getPresenter(), Boolean.FALSE, getEditedTitle());
    }

    private void updateBtnLayout(PageSpec.Config config) {
        ViewUtils.setVisibleOrGone(this.mSaveBtn, !isLandscape());
        ViewUtils.setVisibleOrGone(this.mRewritePromptBtn, !isLandscape());
    }

    private void updateContentRoundCorner(View view, PageSpec.Config config) {
        ViewUtils.setViewShape(view, 1, (float) getSizeWithBase(R.dimen.story_ondemand_content_round_ratio, config.baseSize));
    }

    private void updateEditTitleTextArea(TextView textView, PageSpec.Config config, int i2) {
        textScaleDown(textView, (float) getSizeWithBase(R.dimen.story_collage_page_title_height_ratio, config.baseSize), 10);
    }

    public void bind(PageItem pageItem) {
        OnDemandPageItem onDemandPageItem = (OnDemandPageItem) pageItem;
        this.mItem = onDemandPageItem;
        this.mTitleEditDelegate.bindItem(onDemandPageItem.getMediaItem());
        super.bind(pageItem);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContent = (ViewGroup) view.findViewById(R.id.content);
        this.mSaveBtn = view.findViewById(R.id.save_story_layout);
        this.mRewritePromptBtn = view.findViewById(R.id.rewrite_prompt_layout);
        this.mTitleEditDelegate = new TitleEditDelegate(this.mView);
        bindItemView(this.mContent);
        this.mTitleEditDelegate.bindView(view);
        ViewUtils.setOnClickListener(this.mSaveBtn, new C0513c(this, 0));
        ViewUtils.setOnClickListener(this.mRewritePromptBtn, new C0513c(this, 1));
    }

    public View getContent() {
        return this.mContent;
    }

    public String getEditedTitle() {
        return this.mTitleEditDelegate.getEditedTitle();
    }

    public void initLayout(PageSpec.Config config) {
        super.initLayout(config);
        ViewMarginUtils.setHorizontalPadding(this.mContainer, 10);
        updateLayout(config, getSidePadding(config));
    }

    public void onBindItem() {
        initLayout(new PageSpec(this.mView, this.mRootParent).calculate());
        MediaItem mediaItem = this.mItem.getMediaItem();
        if (mediaItem != null) {
            this.mContentHolder.bind(mediaItem);
            loadBindThumbnail(mediaItem, this.mContentHolder);
        }
    }

    public void onLastPageShow(boolean z) {
        this.mTitleEditDelegate.onLastPageShow(z);
    }

    public void onThumbnailLoadCompleted(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) bitmap2, mediaItem, (Object) listViewHolder, 19));
    }

    public void prepareResolutionChange() {
        super.prepareResolutionChange();
        this.mTitleEditDelegate.prepareResolution();
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        updateViewSize(this.mContentHolder.itemView, config, i2);
        updateContentRoundCorner(this.mContentHolder.itemView, config);
        updateBtnLayout(config);
        if (this.mTitleEditDelegate.getEditTextView() != null) {
            updateEditTitleTextArea(this.mTitleEditDelegate.getEditTextView(), config, i2);
        }
    }

    public void updateViewSize(View view, PageSpec.Config config, int i2) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int sizeWithBase = getSizeWithBase(R.dimen.story_ondemand_page_horizontal_margin_ratio, config.baseSize);
            int sizeWithBase2 = getSizeWithBase(R.dimen.story_ondemand_page_horizontal_margin_ratio, config.baseSize);
            int i7 = config.width - (sizeWithBase * 2);
            layoutParams.width = i7;
            layoutParams.height = (int) (((float) i7) * 1.1395f);
            view.setLayoutParams(layoutParams);
            view.layout(0, 0, layoutParams.width, layoutParams.height);
            ViewMarginUtils.setMarginRelative(view, Integer.valueOf(sizeWithBase), Integer.valueOf(sizeWithBase2), Integer.valueOf(sizeWithBase), Integer.valueOf(sizeWithBase2));
        }
    }
}
