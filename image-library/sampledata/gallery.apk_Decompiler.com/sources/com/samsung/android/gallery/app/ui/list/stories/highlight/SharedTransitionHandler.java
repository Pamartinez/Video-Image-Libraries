package com.samsung.android.gallery.app.ui.list.stories.highlight;

import B8.g;
import O3.b;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import bc.d;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.filter.StoryFilterApplier;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.BlurUtil;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.EntryBlurHolder;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.o3dp.lib3dphotography.i;
import com.sec.android.gallery3d.R;
import g6.e;
import g6.f;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedTransitionHandler {
    private static final boolean SMART_CROP = PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSmartCrop);
    private Blackboard mBlackboard;
    private final AtomicBoolean mDataInit = new AtomicBoolean();
    private EntryBlurHolder mEntryBlurHolder;
    private ViewGroup mEntryBlurLayout;
    private View mGradientView;
    private TransitionInfo mInfo;
    private boolean mTransitionInfoChanged;
    private ImageView mTransitionView;
    private final IStoryHighlightView mView;
    private int retryEndCount = 0;

    public SharedTransitionHandler(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
    }

    private void applyFilter(Bitmap bitmap, Filter filter) {
        if (bitmap != null) {
            StoryFilterApplier storyFilterApplier = new StoryFilterApplier(this.mView);
            storyFilterApplier.setImageFilter(filter);
            storyFilterApplier.apply(bitmap, true, new f(0, this));
        }
    }

    private boolean fromTripInYear() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "story_trip_in_year", false);
    }

    private Bitmap getBitmapFromView(ImageView imageView) {
        if (imageView == null || !(imageView.getDrawable() instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) this.mTransitionView.getDrawable()).getBitmap();
    }

    private Filter getFilter(MediaItem mediaItem) {
        if (mediaItem != null) {
            String storyFilter = MediaItemStory.getStoryFilter(mediaItem);
            if (!TextUtils.isEmpty(storyFilter)) {
                return FilterHolder.findCurrentSepFilter(storyFilter);
            }
        }
        return this.mView.getEventHandler().getFilter();
    }

    private MediaItem getHeaderFromCache(int i2) {
        if (UnsafeCast.isInvalid(i2)) {
            return null;
        }
        return (MediaItem) this.mBlackboard.pop(LocationKey.getHeaderCacheKey("stories", i2));
    }

    private String getImageTransitionName(MediaItem mediaItem, boolean z) {
        if (this.mView.getOptions().withOnDemandVi()) {
            return StorySharedTransitionHelper.getOnDemandViImageTransitionName(mediaItem);
        }
        return StorySharedTransitionHelper.getImageTransitionName(mediaItem);
    }

    private int getMaskedReturnPos(MediaItem mediaItem, int i2) {
        if (isTransitory(mediaItem)) {
            return i2 + 100000;
        }
        if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || !fromTripInYear()) {
            return i2;
        }
        return Math.max(PositionInTrips.get(this.mView.getHeaderItem(), this.mView.getStoriesData()), 0);
    }

    private Rect getScreenRect(View view, int[] iArr) {
        return new Rect(0, 0, view.getRootView().getWidth() + iArr[0], view.getRootView().getHeight() + iArr[1]);
    }

    private int getStoryId() {
        return ArgumentsUtil.getArgs(this.mView.getLocationKey()).getInt("id");
    }

    private Object getTransitionTagKey(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "";
        }
        return mediaItem.getAlbumID() + "/" + mediaItem.getFileId() + "/" + isTransitory(mediaItem);
    }

    private boolean hasValidInfo() {
        TransitionInfo transitionInfo = this.mInfo;
        if (transitionInfo == null || !transitionInfo.hasValidData()) {
            return false;
        }
        return true;
    }

    private boolean isTransitory(MediaItem mediaItem) {
        return StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFilter$6(Bitmap bitmap) {
        this.mTransitionView.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFilter$7(Bitmap bitmap, Filter filter) {
        ThreadUtil.runOnUiThread(new i(22, this, bitmap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadTransitionInfo$4(MediaItem mediaItem, Bitmap bitmap) {
        setTransitionInfo(mediaItem, bitmap, new int[]{0, 0}, Filter.NONE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadTransitionInfo$5(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.runOnUiThread(new d((Object) this, (Object) mediaItem, (Object) bitmap, 15));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onEnterTransitionEndV2$1() {
        this.retryEndCount++;
        onEnterTransitionEndV2();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startTransition$0(SharedTransition.TransitionListener transitionListener) {
        if (!this.mView.isDestroyed()) {
            transitionListener.onEnterTransitionStartV2();
            transitionListener.onEnterTransitionEndV2();
            Log.d("SharedTransitionHandler", "tr failed, force end");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$transitionPostEnd$2() {
        ViewUtils.setVisibility(this.mTransitionView, 4);
        EntryBlurHolder entryBlurHolder = this.mEntryBlurHolder;
        if (entryBlurHolder != null) {
            entryBlurHolder.disable(50);
        }
        if (!this.mView.isDestroyed() && supportReturnTransition()) {
            loadTransitionInfo(this.mView.getHeaderItem(), true);
        }
    }

    private void layoutTransitionView(int i2, int i7) {
        this.mTransitionView.layout(0, 0, i2, i7);
        ViewUtils.setViewSize(this.mTransitionView, Integer.valueOf(i2), Integer.valueOf(i7));
    }

    private void loadTransitionInfo(MediaItem mediaItem, boolean z) {
        ThumbKind thumbKind;
        if (this.mTransitionView != null && mediaItem != null) {
            if (z || !getTransitionTagKey(mediaItem).equals(this.mTransitionView.getTag())) {
                Log.d("SharedTransitionHandler", "loadTransitionInfo", MediaItemUtil.getSimpleLog(mediaItem), Boolean.valueOf(z));
                if (mediaItem.isHeif()) {
                    thumbKind = ThumbKind.MEDIUM_KIND;
                } else {
                    thumbKind = ThumbKind.LARGE_KIND;
                }
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new b(25, this, mediaItem));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: prepareEnterInternal */
    public void lambda$prepareEnter$3(boolean z, int[] iArr) {
        MediaItem mediaItem;
        Bitmap bitmap;
        boolean z3;
        TransitionInfo transitionInfo;
        if (!z || (transitionInfo = this.mInfo) == null) {
            if (this.mView.getHeaderItem() != null) {
                mediaItem = this.mView.getHeaderItem();
            } else {
                mediaItem = getHeaderFromCache(getStoryId());
            }
            if (mediaItem != null) {
                bitmap = ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.FREE_KIND);
            } else {
                bitmap = null;
            }
            if (mediaItem != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            Log.d("SharedTransitionHandler", "prepareEnterInternal", Boolean.valueOf(z3), Logger.toString(bitmap));
            if (!(mediaItem == null || bitmap == null)) {
                updateLayout(mediaItem, bitmap, iArr);
            }
            setEntryBlur(this.mEntryBlurLayout, this.mView.getHeaderItem());
            return;
        }
        setTransitionInfo(transitionInfo.item, transitionInfo.bitmap, iArr, getFilter(this.mView.getHeaderItem()));
        setEntryBlur(this.mEntryBlurLayout, this.mInfo.item);
        startTransition(this.mBlackboard, (SharedTransition.TransitionListener) this.mView);
    }

    private void publishReturnTransitionInfo(MediaItem mediaItem, Bitmap bitmap, int i2) {
        SharedTransition.setTransitionName(this.mTransitionView, (String) null);
        SharedTransition.setTransitionName(this.mGradientView, (String) null);
        new TransitionInfo(mediaItem, bitmap).setDisplayRect(new RectF((float) this.mTransitionView.getLeft(), (float) this.mTransitionView.getTop(), (float) this.mTransitionView.getRight(), (float) this.mTransitionView.getBottom())).setPresetMatrix(this.mTransitionView.getImageMatrix()).publish(this.mBlackboard);
        this.mBlackboard.publish("data://shrink_active", DataKey.ShrinkType.HIGHLIGHT);
        this.mBlackboard.publish("data://story_transition_return_position", Integer.valueOf(i2));
        SharedTransition.setReturnPosition(this.mBlackboard, i2);
    }

    private void setEntryBlur(ViewGroup viewGroup, MediaItem mediaItem) {
        if (this.mEntryBlurHolder == null && viewGroup != null && mediaItem != null) {
            EntryBlurHolder entryBlurHolder = new EntryBlurHolder(this.mView, viewGroup, mediaItem);
            this.mEntryBlurHolder = entryBlurHolder;
            BlurUtil.applyBlur(this.mBlackboard, entryBlurHolder, mediaItem, false);
        }
    }

    private void setFitCenterLayout(MediaItem mediaItem, int[] iArr) {
        int i2 = 0;
        ImageMatrix.MatrixParam create = ImageMatrix.MatrixParam.create(this.mTransitionView, false);
        if (!mediaItem.isVideo() && !mediaItem.isBroken()) {
            i2 = mediaItem.getOrientation();
        }
        this.mTransitionView.setImageMatrix(ImageMatrix.createFitCenter(create.withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag()).withExtraArea(iArr)));
    }

    private void setSmartCropLayout(MediaItem mediaItem, Bitmap bitmap, int[] iArr) {
        int i2;
        StoryHighlightRect.RectInfo build = new StoryHighlightRect.RectBuilder(mediaItem).setScreenRect(getScreenRect(this.mTransitionView, iArr)).setImageSize(bitmap.getWidth(), bitmap.getHeight()).build();
        layoutTransitionView(build.displayRect.width(), build.displayRect.height());
        if (mediaItem.isVideo() || mediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        this.mTransitionView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mTransitionView, false).withCropRect(build.imageCropRect).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag())));
    }

    private void setTransitionInfo(MediaItem mediaItem, Bitmap bitmap, int[] iArr, Filter filter) {
        setTransitionName(mediaItem, this.mView.getOptions().withOnDemandVi());
        this.mTransitionView.setTag(getTransitionTagKey(mediaItem));
        this.mTransitionView.setImageBitmap(bitmap);
        applyFilter(bitmap, filter);
        updateLayout(mediaItem, bitmap, iArr);
    }

    private void setTransitionName(MediaItem mediaItem, boolean z) {
        SharedTransition.setTransitionName(this.mTransitionView, getImageTransitionName(mediaItem, z));
        SharedTransition.setTransitionName(this.mGradientView, StorySharedTransitionHelper.getGradientTransitionName(mediaItem));
    }

    private void startTransition(Blackboard blackboard, SharedTransition.TransitionListener transitionListener) {
        if (!SharedTransition.startPostponedEnterTransition(transitionListener, blackboard)) {
            ThreadUtil.postOnUiThread(new i(21, this, transitionListener));
        }
    }

    private boolean supportReturnTransition() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "returnTransition", false);
    }

    private void transitionPostEnd() {
        this.mView.getEventHandler().postEvent(Event.ENTER_TRANSITION_POST_END, new Object[0]);
        ThreadUtil.postOnUiThread(new e(this, 1));
    }

    private void updateGradientLayout() {
        Bitmap bitmapFromView = getBitmapFromView(this.mTransitionView);
        MediaItem headerItem = this.mView.getHeaderItem();
        if (bitmapFromView != null && headerItem != null) {
            if (SMART_CROP) {
                ViewUtils.setViewSize(this.mGradientView, Integer.valueOf(this.mTransitionView.getWidth()), Integer.valueOf(this.mTransitionView.getHeight()));
                return;
            }
            StoryHighlightRect.RectInfo build = new StoryHighlightRect.RectBuilder(headerItem).setScreenRect(new Rect(0, 0, this.mTransitionView.getWidth(), this.mTransitionView.getHeight())).setImageSize(bitmapFromView.getWidth(), bitmapFromView.getHeight()).withSmartCrop(false).build();
            ViewUtils.setViewSize(this.mGradientView, Integer.valueOf(build.imageCropRect.width()), Integer.valueOf(build.imageCropRect.height()));
        }
    }

    private void updateLayout(MediaItem mediaItem, Bitmap bitmap, int[] iArr) {
        if (!SMART_CROP || bitmap == null) {
            setFitCenterLayout(mediaItem, iArr);
        } else {
            setSmartCropLayout(mediaItem, bitmap, iArr);
        }
        updateGradientLayout();
    }

    private void updateTransitionInfoChange() {
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            setTransitionInfoChanged();
        }
    }

    private boolean useSimpleShrink() {
        if (this.mTransitionInfoChanged || supportReturnTransition()) {
            return true;
        }
        if (this.mView.getOptions().isOnDemandStory() || !this.mView.getOptions().withOnDemandVi()) {
            return false;
        }
        return true;
    }

    public void handleDensityChange() {
        updateTransitionInfoChange();
    }

    public void handleOrientationChange() {
        updateTransitionInfoChange();
    }

    public void handleResolutionChange() {
        updateTransitionInfoChange();
    }

    public void onDataChangedOneUi() {
        if (this.mDataInit.getAndSet(true)) {
            this.mTransitionInfoChanged = true;
        }
    }

    public void onEnterTransitionEndV2() {
        boolean z;
        PreviewViewHolder currentViewHolder = this.mView.getEventHandler().getCurrentViewHolder();
        boolean z3 = false;
        if (currentViewHolder == null || !currentViewHolder.hasBitmap()) {
            z = false;
        } else {
            z = true;
        }
        String str = "enterTransition, tryDone=" + z;
        if (currentViewHolder != null) {
            z3 = true;
        }
        Log.d("SharedTransitionHandler", str, Boolean.valueOf(z3), Integer.valueOf(this.retryEndCount), Boolean.valueOf(ViewUtils.isVisible(this.mTransitionView)), ViewUtils.getViewRect(this.mTransitionView));
        if (z) {
            transitionPostEnd();
        } else if (this.retryEndCount < 5) {
            ThreadUtil.postOnUiThreadDelayed(new e(this, 0), 30);
        } else {
            transitionPostEnd();
        }
    }

    public void onHeaderUpdated(MediaItem mediaItem) {
        loadTransitionInfo(mediaItem, false);
    }

    public void onPrepareSharedTransitionV2(SharedTransition.TransitionListener transitionListener) {
        this.mInfo = SharedTransition.popTransitionInfo(this.mBlackboard);
        SharedTransition.onPrepareStories(this.mBlackboard, transitionListener, this.mView.getOptions().withOnDemandVi());
    }

    public boolean prepareEnter(View view, int[] iArr) {
        Log.d("SharedTransitionHandler", "prepareEnter");
        this.mTransitionView = (ImageView) view.findViewById(R.id.transition_view);
        this.mGradientView = view.findViewById(R.id.dummy_gradient_view);
        this.mEntryBlurLayout = (ViewGroup) view.findViewById(R.id.entry_blur_layout);
        boolean hasValidInfo = hasValidInfo();
        if (this.mTransitionView.getWidth() > 0) {
            lambda$prepareEnter$3(hasValidInfo, iArr);
            return hasValidInfo;
        }
        this.mTransitionView.post(new g((Object) this, hasValidInfo, (Object) iArr, 12));
        return hasValidInfo;
    }

    public void prepareReturn(MediaItem mediaItem, int i2) {
        if (!this.mView.getOptions().useDefaultExitTransition()) {
            ViewUtils.setVisibleOrGone(this.mTransitionView, true);
            updateGradientLayout();
            if (i2 != -1 && mediaItem != null) {
                setTransitionName(mediaItem, false);
                if (useSimpleShrink()) {
                    int maskedReturnPos = getMaskedReturnPos(mediaItem, i2);
                    Bitmap bitmapFromView = getBitmapFromView(this.mTransitionView);
                    if (bitmapFromView == null) {
                        Log.w((CharSequence) "SharedTransitionHandler", "fail back transition", MediaItemUtil.getSimpleLog(mediaItem), Logger.toSimpleString(bitmapFromView));
                        return;
                    }
                    publishReturnTransitionInfo(mediaItem, bitmapFromView, maskedReturnPos);
                } else {
                    this.mBlackboard.publish("data://story_transition_return_position", Integer.valueOf(i2));
                }
                Log.d("SharedTransitionHandler", "prepareReturn shrink", Boolean.valueOf(useSimpleShrink()), Boolean.valueOf(isTransitory(mediaItem)));
            }
        }
    }

    public void resetTransitionInfo() {
        View view = this.mGradientView;
        if (view != null) {
            view.setTransitionName((String) null);
        }
    }

    public void setBlackboard(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    public void setTransitionInfoChanged() {
        this.mTransitionInfoChanged = true;
    }

    public void onEnterTransitionStartV2() {
    }
}
