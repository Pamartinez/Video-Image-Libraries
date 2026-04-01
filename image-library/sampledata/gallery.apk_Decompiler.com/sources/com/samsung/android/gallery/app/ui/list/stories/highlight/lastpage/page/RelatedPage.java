package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import B8.b;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.RelatedPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.timer.TimerView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;
import n0.C0235b;
import r6.f;
import r6.g;
import r6.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedPage extends PageHolder {
    private RelatedViewAdapter mAdapter;
    private RelatedDataHolder mData;
    private RelatedPageItem mItem;
    private PageSpec.Config mPageConfig = new PageSpec.Config();
    private RecyclerView mRelatedList;
    private View mRelatedParent;
    private TextView mRelatedTitle;
    protected TimerView mTimerView;

    public RelatedPage(View view, IStoryHighlightView iStoryHighlightView, View view2) {
        super(view, iStoryHighlightView, view2);
    }

    private void checkFocusPosition() {
        onFocusPositionChanged(this.mLastPageDataHolder.getFocusPosition());
    }

    private RelatedLayoutManager createLayoutManager() {
        return new RelatedLayoutManager(getContext(), 1, new h(this));
    }

    private static RelatedInfo getBaseInfo(int i2, ArrayList<RelatedInfo> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return arrayList.stream().filter(new b(i2, 26)).findFirst().orElse(arrayList.get(0));
    }

    private void initListView() {
        if (this.mAdapter == null) {
            RelatedViewAdapter relatedViewAdapter = new RelatedViewAdapter(getContext());
            this.mAdapter = relatedViewAdapter;
            relatedViewAdapter.setOnRelatedClickListener(new h(this));
        }
        if (this.mData.isValid()) {
            this.mAdapter.setData(this.mData.pickedStories.subList(0, Math.min(this.mData.pickedStories.size(), 2)));
        } else {
            this.mAdapter.setData(new ArrayList());
            Log.e(this.TAG, "invalid data");
        }
        this.mRelatedList.setAdapter(this.mAdapter);
        this.mRelatedList.setLayoutManager(createLayoutManager());
    }

    private void initTitleLayout() {
        int measureTextViewWidth = ViewUtils.getMeasureTextViewWidth(this.mRelatedTitle);
        int intDimen = getIntDimen(R.dimen.story_highlight_related_countdown_oval_size);
        if (this.mPageConfig.width < measureTextViewWidth + intDimen + ViewMarginUtils.getHorizontalPadding((View) this.mRelatedTitle.getParent()) + 20) {
            updateTitleLayoutParam(this.mRelatedTitle, 0, (Integer) null, Float.valueOf(1.0f));
        } else {
            updateTitleLayoutParam(this.mRelatedTitle, -2, (Integer) null, Float.valueOf(0.0f));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int lambda$createLayoutManager$0(int i2) {
        return getSizeWithBase(i2, this.mPageConfig.baseSize);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadBlurBitmap$3(MediaItem mediaItem, Blackboard blackboard, Runnable runnable) {
        Bitmap bitmap;
        int i2;
        Bitmap loadThumbnailSync = ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.FREE_KIND);
        if (loadThumbnailSync != null) {
            bitmap = BitmapUtils.blurAfterResize(AppResources.getAppContext(), loadThumbnailSync, 64);
        } else {
            bitmap = null;
        }
        if (bitmap != null) {
            if (mediaItem.isBroken() || mediaItem.isVideo()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            blackboard.publish(LocationKey.getHeaderCacheKey("data://user/storyBlurBitmap", String.valueOf(mediaItem.getFileId())), BitmapUtils.rotateBitmap(bitmap, i2));
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$ruleOutFromRelated$1(RelatedInfo relatedInfo) {
        this.mData.ruleOut(relatedInfo);
    }

    /* access modifiers changed from: private */
    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.mEventHandler.postEvent(Event.PREPARE_CHANGE_STORY, new Object[0]);
        preloadBlurBitmap(this.mView.getBlackboard(), mediaItem, (Runnable) null);
        ruleOutFromRelated(MediaItemStory.getStoryId(mediaItem));
        this.mEventHandler.postEvent(Event.CHANGE_STORY, mediaItem);
        this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_SHOW_RELATED_STORY, MediaItemStory.getStoryLevel2Detail(mediaItem));
    }

    /* access modifiers changed from: private */
    public void onProgressDone(Void voidR) {
        MediaItem item = this.mAdapter.getItem(0);
        if (item != null) {
            onItemClick((ListViewHolder) this.mRelatedList.findViewHolderForAdapterPosition(0), 0, item, 0);
        }
        this.mLastPageDataHolder.setCountDownProgress(0.0f);
        Log.d(this.TAG, "onProgressDone", MediaItemUtil.getSimpleLog(item));
    }

    /* access modifiers changed from: private */
    public void onProgressUpdated(float f) {
        this.mLastPageDataHolder.setCountDownProgress(f);
    }

    private void preloadBlurBitmap(Blackboard blackboard, MediaItem mediaItem, Runnable runnable) {
        if (blackboard != null && mediaItem != null) {
            SimpleThreadPool.getInstance().execute(new C0235b(mediaItem, blackboard, runnable, 16));
        } else if (runnable != null) {
            runnable.run();
        }
    }

    private void ruleOutFromRelated(int i2) {
        if (!this.mView.getOptions().isFromTransitoryStory()) {
            RelatedDataHolder relatedDataHolder = this.mData;
            relatedDataHolder.base = getBaseInfo(i2, relatedDataHolder.getRelatedAlbumsInfo());
            Optional.ofNullable(this.mData.base).ifPresent(new f(this, 2));
        }
    }

    private void updateCountDownAction(int i2) {
        boolean z;
        if (ResourceCompat.isLandscape(this.itemView) || getAbsoluteAdapterPosition() == i2) {
            z = true;
        } else {
            z = false;
        }
        Log.d(this.TAG, "onFocusPositionChanged", Boolean.valueOf(z), Integer.valueOf(i2), Float.valueOf(this.mLastPageDataHolder.getCountDownProgress()));
        if (z) {
            this.mTimerView.setProgress(this.mLastPageDataHolder.getCountDownProgress());
            this.mTimerView.resume();
            return;
        }
        onResetCountDown();
    }

    public void bind(PageItem pageItem) {
        RelatedPageItem relatedPageItem = this.mItem;
        if (relatedPageItem == null || !relatedPageItem.equalItems(pageItem)) {
            RelatedPageItem relatedPageItem2 = (RelatedPageItem) pageItem;
            this.mItem = relatedPageItem2;
            this.mData = relatedPageItem2.getRelatedData();
            super.bind(pageItem);
        }
    }

    public void bindView(View view) {
        this.mRelatedParent = view.findViewById(R.id.related_parent);
        this.mRelatedList = (RecyclerView) view.findViewById(R.id.related_list);
        this.mRelatedTitle = (TextView) view.findViewById(R.id.related_title);
        TimerView timerView = new TimerView();
        this.mTimerView = timerView;
        timerView.initView(view);
        this.mTimerView.setOnFinishListener(new f(this, 0));
        this.mTimerView.setProgressListener(new f(this, 1));
    }

    public void clear() {
        this.mTimerView.clear();
    }

    public View getContentView() {
        return this.mRelatedList;
    }

    public void initLayout() {
        ViewMarginUtils.setHorizontalPadding(this.mRelatedParent, this.mPageConfig.sideGap);
        ViewGroup.LayoutParams layoutParams = this.mRelatedList.getLayoutParams();
        layoutParams.width = this.mPageConfig.width;
        layoutParams.height = -2;
        this.mRelatedList.setLayoutParams(layoutParams);
        this.mRelatedList.layout(0, 0, layoutParams.width, layoutParams.height);
        ViewUtils.setViewSize(this.itemView.findViewById(R.id.related_header), Integer.valueOf(this.mPageConfig.width), (Integer) null);
    }

    public void onBindItem() {
        this.mPageConfig = new PageSpec(this.mView, this.mRootParent).calculate();
        initLayout();
        initListView();
        initTitleLayout();
    }

    public void onFocusPositionChanged(int i2) {
        updateCountDownAction(i2);
    }

    public void onLastPageShow(boolean z) {
        if (z) {
            checkFocusPosition();
        }
    }

    public void onResetCountDown() {
        this.mLastPageDataHolder.setCountDownProgress(0.0f);
        this.mTimerView.setProgress(this.mLastPageDataHolder.getCountDownProgress());
        this.mTimerView.reset();
        Log.d(this.TAG, "onResetCountDown");
    }

    public void pause() {
        this.mTimerView.pause();
    }

    public void resume() {
        checkFocusPosition();
    }

    public void updateTitleLayoutParam(View view, Integer num, Integer num2, Float f) {
        if (view != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            Optional.ofNullable(num).ifPresent(new g(layoutParams, 0));
            Optional.ofNullable(num2).ifPresent(new g(layoutParams, 1));
            Optional.ofNullable(f).ifPresent(new g(layoutParams, 2));
            view.setLayoutParams(layoutParams);
        }
    }
}
