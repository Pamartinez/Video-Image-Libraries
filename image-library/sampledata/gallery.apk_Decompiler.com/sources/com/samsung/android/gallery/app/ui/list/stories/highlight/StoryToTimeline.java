package com.samsung.android.gallery.app.ui.list.stories.highlight;

import A.a;
import N2.j;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animations.LoadAfterShrinkView;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.livemotion.theme.Transform;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.Def;
import e5.d;
import i.C0212a;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryToTimeline {
    final Blackboard mBlackboard;
    private final MediaData.SimpleDataChangeListener mDataChanged = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            StoryToTimeline.this.handleJump();
        }

        public void onDataChanged() {
            if (!StoryToTimeline.this.mIsPreparedData.getAndSet(true)) {
                SimpleThreadPool.getInstance().execute(new b(3, this));
            }
        }
    };
    int mDataPosition = -1;
    final AtomicBoolean mIsPreparedData = new AtomicBoolean(false);
    MediaData mMediaData;
    MediaItem mMediaItem;
    AlertDialog mProgressCircle;
    SimpleShrinkView mShrinkView;
    long mStartTime;
    final SubscriberListener mSubscriberListener = new a(this);
    final Runnable mTimeoutCallback = new b(0, this);
    IStoryHighlightView mView;

    public StoryToTimeline(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChanged);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    private void dismissDialog() {
        AlertDialog alertDialog = this.mProgressCircle;
        if (alertDialog != null && alertDialog.isShowing() && ViewUtils.isWindowAttachedToWindow(this.mProgressCircle.getWindow())) {
            this.mProgressCircle.dismiss();
        }
    }

    private int findDataPosition(long j2) {
        long j3;
        long j8;
        String str;
        if (this.mMediaData == null) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int findPositionByFileId = this.mMediaData.findPositionByFileId(j2);
        if (findPositionByFileId < 0) {
            j8 = FilesApi.findGroupId(j2, SimilarPhotoHelper.isSimilarPhotoMode());
            j3 = FilesApi.findBestId(j8);
            if (j3 > 0 && j3 != j2) {
                findPositionByFileId = this.mMediaData.findPositionByFileId(j3);
            }
        } else {
            j8 = 0;
            j3 = 0;
        }
        StringBuilder sb2 = new StringBuilder("findPosition");
        Long valueOf = Long.valueOf(j2);
        Integer valueOf2 = Integer.valueOf(findPositionByFileId);
        if (j8 != 0) {
            str = C0212a.o(j.j(j8, "(G=", ",B="), j3, ")");
        } else {
            str = "n/a";
        }
        a.A(new Object[]{valueOf, valueOf2, str, Long.valueOf(currentTimeMillis)}, sb2, "StoryToTimeline");
        return findPositionByFileId;
    }

    private RectF getDisplayRect(ViewPagerHolder viewPagerHolder, MediaItem mediaItem, Bitmap bitmap) {
        StoryHighlightRect.RectBuilder imageSize = new StoryHighlightRect.RectBuilder(mediaItem).setScreenRect(new Rect(0, 0, viewPagerHolder.itemView.getWidth(), viewPagerHolder.itemView.getHeight())).setImageSize(bitmap.getWidth(), bitmap.getHeight());
        boolean z = PocFeatures.STORY_ORIGIN_SCALE_WHEN_PAUSE;
        Rect rect = imageSize.withSmartCrop(!z).build().displayRect;
        RectF rectF = new RectF(rect);
        if ((rect.left != 0 || rect.top != 0) && !z) {
            return rectF;
        }
        RectF viewRect = ViewUtils.getViewRect(viewPagerHolder.itemView);
        return new RectF((viewRect.width() * 0.5f) - (((float) rect.width()) * 0.5f), (viewRect.height() * 0.5f) - (((float) rect.height()) * 0.5f), (((float) rect.width()) * 0.5f) + (viewRect.width() * 0.5f), (((float) rect.height()) * 0.5f) + (viewRect.height() * 0.5f));
    }

    private String getTransformInfo(View view, MediaItem mediaItem) {
        float[] fArr = new float[9];
        view.getMatrix().getValues(fArr);
        PageTransform pageTransform = (PageTransform) this.mView.getEventHandler().requestData(DataRequest.VIEW_PAGER_CURRENT_TRANSFORM);
        int intValue = ((Integer) this.mView.getEventHandler().requestData(DataRequest.VIEW_PAGER_CURRENT)).intValue();
        float[] transformPivot = getTransformPivot(view, pageTransform, mediaItem);
        Transform.TYPE transformType = getTransformType(view, pageTransform, mediaItem, intValue);
        return fArr[0] + "#" + fArr[4] + "#" + fArr[2] + "#" + fArr[5] + "#" + transformPivot[0] + "#" + transformPivot[1] + "#" + transformType.ordinal();
    }

    private float[] getTransformPivot(View view, PageTransform pageTransform, MediaItem mediaItem) {
        float[] fArr = new float[2];
        if (pageTransform != null) {
            if (pageTransform.getPivotX() == -1.0f && pageTransform.getPivotY() == -1.0f) {
                return Transform.getPivotByROI(mediaItem);
            }
            fArr[0] = pageTransform.getPivotX();
            fArr[1] = pageTransform.getPivotY();
            return fArr;
        } else if (view.getScaleX() != 1.0f) {
            return Transform.getPivotByROI(mediaItem);
        } else {
            fArr[1] = 0.5f;
            fArr[0] = 0.5f;
            return fArr;
        }
    }

    private Transform.TYPE getTransformType(View view, PageTransform pageTransform, MediaItem mediaItem, int i2) {
        MediaItem mediaItem2;
        if (i2 == 0) {
            mediaItem2 = null;
        } else {
            mediaItem2 = mediaItem;
        }
        Transform.TYPE type = Transform.getType(mediaItem2);
        if (i2 == 0) {
            Transform.TYPE type2 = Transform.getType(mediaItem);
            if (type == type2 || (pageTransform == null ? view.getScaleX() == 1.0f : pageTransform.getPivotX() == 0.5f || pageTransform.getPivotY() == 0.5f)) {
                return type;
            }
            return type2;
        } else if (!Transform.isTranslate(type.ordinal()) || view.getScaleX() == 1.025f) {
            return type;
        } else {
            return Transform.TYPE.SCALE_IN;
        }
    }

    /* access modifiers changed from: private */
    public void handleJump() {
        this.mDataPosition = findDataPosition(this.mMediaItem.getFileId());
        a.A(new Object[]{Integer.valueOf(this.mDataPosition), Long.valueOf(this.mStartTime)}, new StringBuilder("handleJump"), "StoryToTimeline");
        int i2 = this.mDataPosition;
        if (i2 >= 0) {
            MediaItem mediaItem = this.mMediaItem;
            MediaItem read = this.mMediaData.read(i2);
            if (read != null) {
                MediaItemStory.setViewOriginTargetCropRectRatio(mediaItem, read.getCropRectRatio());
            }
            ViewPagerHolder viewPagerHolder = (ViewPagerHolder) this.mView.getEventHandler().getCurrentViewHolder();
            if (viewPagerHolder == null) {
                Log.e("StoryToTimeline", "handleJump failed. view holder null");
                return;
            }
            ThreadUtil.runOnUiThread(new c(this, viewPagerHolder.getBitmap(), ViewUtils.getBitmapFromView(viewPagerHolder.getBlurContainer()), viewPagerHolder, mediaItem));
            return;
        }
        ThreadUtil.runOnUiThread(new b(2, this));
    }

    private void jumpAndShrink() {
        a.A(new Object[]{Integer.valueOf(this.mDataPosition), Long.valueOf(this.mStartTime)}, new StringBuilder("jumpAndShrink"), "StoryToTimeline");
        ThreadUtil.removeCallbackOnUiThread(this.mTimeoutCallback);
        this.mBlackboard.unsubscribe("data://resumed_fragment", this.mSubscriberListener);
        closeMediaData();
        ThreadUtil.postOnUiThreadDelayed(new b(1, this), 1000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleJump$0(Bitmap bitmap, Bitmap bitmap2, ViewPagerHolder viewPagerHolder, MediaItem mediaItem) {
        if (bitmap == null || bitmap2 == null) {
            Log.d("StoryToTimeline", "Bitmap is null", bitmap, bitmap2);
            onTimeout();
            return;
        }
        a.A(new Object[]{Integer.valueOf(this.mDataPosition), Long.valueOf(this.mStartTime)}, new StringBuilder("jumpPrepared"), "StoryToTimeline");
        TransitionInfo background = new TransitionInfo(mediaItem, bitmap).setDisplayRect(getDisplayRect(viewPagerHolder, mediaItem, bitmap)).setBackground(bitmap2);
        background.publish(this.mBlackboard);
        this.mShrinkView = new LoadAfterShrinkView(this.mBlackboard, new d(4), 0.012499988f).setTransformInfo(getTransformInfo(viewPagerHolder.getDecoView(35), mediaItem)).setTransitionInfo(background).show(false);
        this.mBlackboard.publish("command://ClearBackStackFragments", (Object) null);
        this.mView = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleJump$1() {
        Log.d("StoryToTimeline", "jumpPrepared failed. position not found");
        onTimeout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$jumpAndShrink$2() {
        Blackboard blackboard = this.mBlackboard;
        blackboard.publish("command://startScrollAndShrink?dataPosition=" + this.mDataPosition, this.mShrinkView);
        dismissDialog();
    }

    /* access modifiers changed from: private */
    public void onFragmentLifecycleResumed(Object obj, Bundle bundle) {
        String str = (String) obj;
        if (str.startsWith("BottomTab") || str.startsWith("ListContainer")) {
            this.mBlackboard.publish("command://bottomtab/select", "location://timeline");
        } else if (str.startsWith("Timeline")) {
            jumpAndShrink();
        }
    }

    /* access modifiers changed from: private */
    public void onTimeout() {
        Log.e("StoryToTimeline", "onTimeout");
        this.mBlackboard.erase("data://shrink_active");
        this.mBlackboard.unsubscribe("data://resumed_fragment", this.mSubscriberListener);
        closeMediaData();
        dismissDialog();
        SimpleShrinkView simpleShrinkView = this.mShrinkView;
        if (simpleShrinkView != null) {
            simpleShrinkView.recycle();
            this.mShrinkView = null;
        }
        this.mView = null;
    }

    private void openMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://timeline");
        this.mMediaData = open;
        open.register(this.mDataChanged);
    }

    public void jump(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        ViewPagerHolder viewPagerHolder = (ViewPagerHolder) iStoryHighlightView.getEventHandler().getCurrentViewHolder();
        if (viewPagerHolder == null || viewPagerHolder.getMediaItem() == null) {
            Log.e("StoryToTimeline", "jump failed. wrong data");
            return;
        }
        this.mStartTime = System.currentTimeMillis();
        MediaItem mediaItem = viewPagerHolder.getMediaItem();
        this.mMediaItem = mediaItem;
        Log.d("StoryToTimeline", "jump", Long.valueOf(mediaItem.getFileId()));
        this.mBlackboard.subscribeOnUi("data://resumed_fragment", this.mSubscriberListener);
        AlertDialog create = new ProgressCircleBuilder(iStoryHighlightView.getActivity()).create();
        this.mProgressCircle = create;
        create.show();
        openMediaData();
        ThreadUtil.postOnUiThreadDelayed(this.mTimeoutCallback, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
    }
}
