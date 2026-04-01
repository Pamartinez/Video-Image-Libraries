package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.RecapCoverItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import l6.a;
import n0.C0235b;
import r6.d;
import r6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapCoverPage extends SummaryPage {
    private View mContentContainer;
    private ImageView mImage;
    private RecapCoverItem mItem;
    private View mReplayBtn;
    private View mSaveAsStoryBtn;

    public RecapCoverPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBindThumbnail$0(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$3(Bitmap bitmap, MediaItem mediaItem) {
        if (bitmap != null) {
            if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                Optional.ofNullable(mediaItem).ifPresent(new e(0));
            }
            this.mImage.setImageBitmap(bitmap);
        } else {
            Optional.ofNullable(mediaItem).ifPresent(new e(1));
            this.mImage.setImageBitmap(getBrokenImage(mediaItem));
        }
        this.mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void loadBindThumbnail(MediaItem mediaItem) {
        ThumbKind thumbKind;
        if (mediaItem.isVideo()) {
            thumbKind = ThumbKind.FULL_NC_KIND;
        } else {
            thumbKind = ThumbKind.LARGE_KIND;
        }
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(mediaItem, memCache);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new a(3, this, mediaItem));
        }
    }

    /* access modifiers changed from: private */
    public void onReplayClicked(View view) {
        this.mEventHandler.postEvent(Event.LAST_PAGE_REPLAY, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onSaveAsClicked(View view) {
        this.mEventHandler.postEvent(Event.SAVE_STORY_FROM_ON_DEMAND, new Object[0]);
    }

    public void bind(PageItem pageItem) {
        this.mItem = (RecapCoverItem) pageItem;
        super.bind(pageItem);
    }

    public void bindTitle(TextView textView, TextView textView2) {
        textView.setText(this.mItem.getTitle());
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContentContainer = view.findViewById(R.id.content_container);
        this.mImage = (ImageView) view.findViewById(R.id.image);
        this.mTitle.setOnClickListener((View.OnClickListener) null);
        this.mTitle.setClickable(false);
        this.mReplayBtn = view.findViewById(R.id.replay_button_layout);
        this.mSaveAsStoryBtn = view.findViewById(R.id.save_story_layout);
        ViewUtils.setOnClickListener(this.mReplayBtn, new d(this, 0));
        ViewUtils.setOnClickListener(this.mSaveAsStoryBtn, new d(this, 1));
        ViewUtils.setAccessibilityRoleDescription(this.mReplayBtn, R.string.speak_button);
        ViewUtils.setAccessibilityRoleDescription(this.mSaveAsStoryBtn, R.string.speak_button);
    }

    public View getContent() {
        return this.mContentContainer;
    }

    public int getSidePadding(PageSpec.Config config) {
        return getSizeWithBase(R.dimen.story_recap_content_padding_ratio, config.baseSize);
    }

    public void onBindItem() {
        initLayout(new PageSpec(this.mView, this.mRootParent).calculate());
        loadBindThumbnail(this.mItem.getMediaItem());
    }

    public void onThumbnailLoadCompleted(MediaItem mediaItem, Bitmap bitmap) {
        ThreadUtil.runOnUiThread(new C0235b(this, bitmap, mediaItem, 15));
    }

    public void recycle() {
        super.recycle();
        this.mImage.setImageBitmap((Bitmap) null);
        this.mTitle.setText((CharSequence) null);
        this.mTitle.setClickable(true);
        this.mSubTitle.setText((CharSequence) null);
        this.mItem = null;
    }

    public void updateLayout(PageSpec.Config config, int i2) {
        super.updateLayout(config, i2);
        ViewUtils.setViewShape(this.mContentContainer, 1, (float) getSizeWithBase(R.dimen.story_collage_content_round_ratio, config.baseSize));
        updateViewSize(this.mContentContainer, config, i2);
        ViewUtils.setVisibleOrGone(this.mReplayBtn, !isLandscape());
        ViewUtils.setVisibleOrGone(this.mSaveAsStoryBtn, !isLandscape());
    }

    public void updateViewSize(View view, PageSpec.Config config, int i2) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int i7 = config.width - (i2 * 2);
            layoutParams.width = i7;
            layoutParams.height = (int) (((float) i7) * 1.25f);
            view.setLayoutParams(layoutParams);
            view.layout(0, 0, layoutParams.width, layoutParams.height);
            ViewMarginUtils.setMarginRelative(view, Integer.valueOf(i2), Integer.valueOf(getSizeWithBase(R.dimen.story_collage_list_top_margin_ratio, config.baseSize)), Integer.valueOf(i2), Integer.valueOf(i2));
        }
    }
}
