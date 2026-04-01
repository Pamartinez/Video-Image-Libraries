package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import bc.d;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animations.CustomShrinkView;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.sum.core.Def;
import i.C0212a;
import ic.l;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import k7.j;
import k7.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JumpToTimelineDelegate extends AbsVuDelegate<IVuContainerView> {
    private final MediaData.SimpleDataChangeListener mDataChanged = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            JumpToTimelineDelegate.this.handleJump();
        }

        public void onDataChanged() {
            if (!JumpToTimelineDelegate.this.mIsPreparedData.getAndSet(true)) {
                SimpleThreadPool.getInstance().execute(new b(this));
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
    final SubscriberListener mSubscriberListener = new j(0, this);
    final Runnable mTimeoutCallback = new k(this, 0);

    public JumpToTimelineDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChanged);
            this.mMediaData.close();
            this.mMediaData = null;
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
            str = C0212a.o(N2.j.j(j8, "(G=", ",B="), j3, ")");
        } else {
            str = "n/a";
        }
        a.A(new Object[]{valueOf, valueOf2, str, Long.valueOf(currentTimeMillis)}, sb2, "JumpToTimeline");
        return findPositionByFileId;
    }

    /* access modifiers changed from: private */
    public void handleJump() {
        this.mDataPosition = findDataPosition(this.mMediaItem.getFileId());
        a.A(new Object[]{Integer.valueOf(this.mDataPosition), Long.valueOf(this.mStartTime)}, new StringBuilder("handleJump"), "JumpToTimeline");
        int i2 = this.mDataPosition;
        if (i2 >= 0) {
            MediaItem mediaItem = this.mMediaItem;
            MediaItem read = this.mMediaData.read(i2);
            if (read != null) {
                MediaItemStory.setViewOriginTargetCropRectRatio(mediaItem, read.getCropRectRatio());
            }
            ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
            if (currentViewer == null) {
                Log.e("JumpToTimeline", "handleJump failed. view holder null");
            } else {
                ThreadUtil.runOnUiThread(new d((Object) this, (Object) currentViewer, mediaItem, 26));
            }
        } else {
            ThreadUtil.runOnUiThread(new k(this, 2));
        }
    }

    private void jumpAndShrink() {
        a.A(new Object[]{Integer.valueOf(this.mDataPosition), this.mShrinkView, Long.valueOf(this.mStartTime)}, new StringBuilder("jumpAndShrink"), "JumpToTimeline");
        ThreadUtil.removeCallbackOnUiThread(this.mTimeoutCallback);
        this.mBlackboard.unsubscribe("data://resumed_fragment", this.mSubscriberListener);
        closeMediaData();
        ThreadUtil.postOnUiThreadDelayed(new k(this, 1), 90);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleJump$1(ViewerObjectComposite viewerObjectComposite, MediaItem mediaItem) {
        RectF rectF;
        Bitmap bitmap = viewerObjectComposite.getModel().getBitmap();
        ImageView imageView = (ImageView) viewerObjectComposite.getViewHolder().getTransitionView();
        if (imageView == null) {
            rectF = null;
        } else {
            rectF = ((PhotoViewCompat) imageView).getDisplayRect();
        }
        Log.d("JumpToTimeline", "jumpPrepared" + Logger.vt(Integer.valueOf(this.mDataPosition), Long.valueOf(this.mStartTime)));
        TransitionInfo displayRect = new TransitionInfo(mediaItem, bitmap).setDisplayRect(rectF);
        this.mActionInvoker.invoke(ViewerAction.PREPARE_EXIT_TRANSITION, displayRect);
        this.mShrinkView = new CustomShrinkView(this.mBlackboard).setTransitionInfo(displayRect).show(false);
        this.mBlackboard.publish("command://ClearBackStackFragments", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleJump$2() {
        Log.d("JumpToTimeline", "jumpPrepared failed. position not found");
        onTimeout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$jumpAndShrink$3() {
        Blackboard blackboard = this.mBlackboard;
        blackboard.publish("command://startScrollAndShrink?dataPosition=" + this.mDataPosition, this.mShrinkView);
        Optional.ofNullable(this.mProgressCircle).ifPresent(new l(15));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        jump();
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
        Log.e("JumpToTimeline", "onTimeout");
        this.mBlackboard.erase("data://shrink_active");
        this.mBlackboard.unsubscribe("data://resumed_fragment", this.mSubscriberListener);
        closeMediaData();
        Optional.ofNullable(this.mProgressCircle).ifPresent(new l(15));
        SimpleShrinkView simpleShrinkView = this.mShrinkView;
        if (simpleShrinkView != null) {
            simpleShrinkView.recycle();
            this.mShrinkView = null;
        }
    }

    private void openMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open("location://timeline");
        this.mMediaData = open;
        open.register(this.mDataChanged);
    }

    public void jump() {
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        this.mMediaItem = currentMediaItem;
        if (currentMediaItem == null) {
            Log.e("JumpToTimeline", "jump failed. wrong data");
            return;
        }
        this.mStartTime = System.currentTimeMillis();
        Log.d("JumpToTimeline", "jump", Long.valueOf(this.mMediaItem.getFileId()));
        this.mBlackboard.subscribeOnUi("data://resumed_fragment", this.mSubscriberListener);
        AlertDialog create = new ProgressCircleBuilder(((IVuContainerView) this.mView).getActivity()).create();
        this.mProgressCircle = create;
        create.show();
        openMediaData();
        ThreadUtil.postOnUiThreadDelayed(this.mTimeoutCallback, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.JUMP_TO_TIMELINE, new B7.d(19, this));
    }
}
