package com.samsung.android.gallery.widget.animations;

import Bb.g;
import F.b;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.i;
import com.samsung.scsp.media.api.d;
import eb.C0688b;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleAutoScroller {
    private float mAlphaWeight;
    private GalleryAppBarLayout mAppbarLayout;
    protected final Blackboard mBlackboard;
    private Runnable mFinishListener;
    private Handler mForceShrinkHandler;
    /* access modifiers changed from: private */
    public SimpleShrinkHandler mHandler;
    private boolean mIsShrinkBlackBg;
    private final View.OnLayoutChangeListener mLayoutChangeListener = new g(8, this);
    protected final RecyclerView mListView;
    protected int mPosition;
    private boolean mPostAnim;
    private int mRetryCount;
    private final String mScreenMode;
    protected final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (SimpleAutoScroller.this.mHandler != null && i2 == 1) {
                SimpleAutoScroller.this.mHandler.cancelAnimation();
            }
        }
    };
    private DataKey.ShrinkType mShrinkType;
    protected SimpleShrinkView mShrinkView;
    private int mStartDelay;
    private Runnable mStartListener;
    private IThemeColor mThemeColor;
    private final long mTimestamp;
    private Runnable mUpdateListener;
    private boolean mWithRadius;

    public SimpleAutoScroller(Blackboard blackboard, RecyclerView recyclerView, int i2) {
        this.mBlackboard = blackboard;
        this.mListView = recyclerView;
        this.mPosition = i2;
        String str = (String) blackboard.read("data://resumed_fragment");
        this.mScreenMode = str;
        this.mTimestamp = System.currentTimeMillis();
        this.mShrinkType = BlackboardUtils.getViewerShrink(blackboard);
        Log.st("SimpleAutoScroller", "constructor {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mShrinkType + "} " + ViewUtils.getViewRect(recyclerView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$findAndShrink$9() {
        RecyclerView recyclerView = this.mListView;
        if (recyclerView instanceof IAutoScroller) {
            ((IAutoScroller) recyclerView).refreshScrollPosition();
        }
        findAndShrink();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLayoutChange$4() {
        this.mListView.scrollToPosition(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLayoutChange$5() {
        this.mListView.scrollToPosition(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$shrinkIfFound$6(ListViewHolder listViewHolder) {
        if (!this.mListView.isAttachedToWindow() || !this.mListView.isShown()) {
            Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + "} start force");
            lambda$findAndShrink$10(listViewHolder);
            return;
        }
        this.mListView.scrollToPosition(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$shrinkIfFound$7(Boolean bool) {
        onFinish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0() {
        this.mShrinkView = null;
        onError("error action");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$1() {
        this.mListView.scrollToPosition(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$2() {
        this.mListView.addOnLayoutChangeListener(this.mLayoutChangeListener);
        this.mListView.addOnScrollListener(this.mScrollListener);
        notifyStartListener();
        this.mListView.post(new C0688b(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$3() {
        this.mListView.scrollToPosition(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$tryStartShrink$8() {
        this.mListView.scrollToPosition(this.mPosition);
    }

    private void notifyFinishListener() {
        Runnable runnable = this.mFinishListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void onFinish() {
        Log.st("SimpleAutoScroller", "onFinish +" + (System.currentTimeMillis() - this.mTimestamp));
        notifyFinishListener();
        this.mListView.removeOnScrollListener(this.mScrollListener);
        this.mBlackboard.erase("data://video_viewer_return_info");
    }

    /* access modifiers changed from: private */
    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        Log.st("SimpleAutoScroller", "onLayoutChange {retry=" + this.mRetryCount + "} " + ViewUtils.getViewRect(this.mListView) + ArcCommonLog.TAG_COMMA + SystemUi.getScreenMode(this.mBlackboard));
        int i15 = this.mRetryCount + 1;
        this.mRetryCount = i15;
        if (i15 > 20 || !isListViewAvailable()) {
            onError("retry fail");
            return;
        }
        String str = this.mScreenMode;
        if (str != null && str.equals(this.mBlackboard.read("data://resumed_fragment"))) {
            Log.st("SimpleAutoScroller", "waiting fragment resumed");
            this.mListView.post(new C0688b(this, 7));
        } else if (!shrinkIfFound(this.mPosition)) {
            this.mListView.post(new C0688b(this, 8));
        }
    }

    private void recycle() {
        this.mListView.removeOnLayoutChangeListener(this.mLayoutChangeListener);
    }

    private boolean shrinkIfFound(int i2) {
        try {
            ListViewHolder listViewHolder = (ListViewHolder) this.mListView.findViewHolderForAdapterPosition(i2);
            if (listViewHolder != null) {
                if (listViewHolder.getMediaItem() != null) {
                    if (this.mAppbarLayout != null && this.mListView.getHeight() - this.mAppbarLayout.getVisibleHeight() < listViewHolder.itemView.getBottom()) {
                        Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + "} failed. appbar collapsed by " + this.mAppbarLayout.getVisibleHeight());
                        this.mAppbarLayout.setExpanded(false, false);
                        this.mAppbarLayout = null;
                        return false;
                    } else if (!ViewUtils.contains(this.mListView, listViewHolder.itemView)) {
                        Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + "} failed. partially visible");
                        ThreadUtil.postOnUiThreadDelayed(new i(11, this, listViewHolder), 200);
                        return true;
                    } else if (lambda$findAndShrink$10(listViewHolder)) {
                        return true;
                    } else {
                        Bitmap bitmap = this.mShrinkView.mBitmap;
                        Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.getEncodedString((String) Optional.ofNullable(listViewHolder.getImage()).map(new d(12)).orElse("null")) + "} start shrink " + Logger.toSimpleString(bitmap) + " +" + (System.currentTimeMillis() - this.mTimestamp));
                        if (bitmap != null) {
                            listViewHolder.bindImage(bitmap);
                        }
                        recycle();
                        notifyStartListener();
                        return true;
                    }
                }
            }
            Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + "} failed. not found");
            return false;
        } catch (Exception e) {
            Log.ste("SimpleAutoScroller", "shrinkIfFound failed e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public boolean tryStartShrink(Message message) {
        Log.st("SimpleAutoScroller", "try shrink " + message.what);
        if (!shrinkIfFound(this.mPosition)) {
            if (message.what < 3) {
                this.mListView.post(new C0688b(this, 6));
                this.mForceShrinkHandler.sendEmptyMessageDelayed(message.what + 1, 50);
                return true;
            }
            lambda$findAndShrink$10((ListViewHolder) null);
        }
        return true;
    }

    public void cancel() {
        SimpleShrinkHandler simpleShrinkHandler = this.mHandler;
        if ((simpleShrinkHandler instanceof SimpleBackShrinkHandler) && !simpleShrinkHandler.isBackToCamera()) {
            this.mHandler.cancel();
        }
    }

    public void findAndShrink() {
        if (!shrinkIfFound(this.mPosition)) {
            int i2 = this.mRetryCount + 1;
            this.mRetryCount = i2;
            if (i2 < 10) {
                this.mListView.scrollToPosition(this.mPosition);
                this.mListView.postDelayed(new C0688b(this, 1), 10);
                return;
            }
            lambda$findAndShrink$10((ListViewHolder) null);
            return;
        }
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mListView.findViewHolderForAdapterPosition(this.mPosition);
        if (findViewHolderForAdapterPosition != null && !ViewUtils.contains(this.mListView, findViewHolderForAdapterPosition.itemView)) {
            this.mListView.scrollToPosition(this.mPosition);
            ThreadUtil.postOnUiThread(new i(12, this, findViewHolderForAdapterPosition));
        }
    }

    public boolean isListViewAvailable() {
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            adapter = recyclerView.getAdapter();
        } else {
            adapter = null;
        }
        if (adapter == null || adapter.getItemCount() <= 0) {
            return false;
        }
        return true;
    }

    public final void notifyStartListener() {
        Runnable runnable = this.mStartListener;
        if (runnable != null) {
            runnable.run();
            this.mStartListener = null;
        }
    }

    public final void onError(String str) {
        Log.ste("SimpleAutoScroller", "onError : " + str);
        SimpleShrinkView simpleShrinkView = this.mShrinkView;
        if (simpleShrinkView == null || simpleShrinkView.mShrinkView == null || !lambda$findAndShrink$10((ListViewHolder) null)) {
            recycle();
            notifyStartListener();
            onFinish();
        }
    }

    public SimpleAutoScroller setAlphaWeight(float f) {
        this.mAlphaWeight = f;
        return this;
    }

    public SimpleAutoScroller setAppbar(GalleryAppBarLayout galleryAppBarLayout) {
        this.mAppbarLayout = galleryAppBarLayout;
        return this;
    }

    public SimpleAutoScroller setPostAnim() {
        this.mPostAnim = true;
        return this;
    }

    public SimpleAutoScroller setShrinkBlackBg(boolean z) {
        this.mIsShrinkBlackBg = z;
        return this;
    }

    public SimpleAutoScroller setShrinkType(DataKey.ShrinkType shrinkType) {
        this.mShrinkType = shrinkType;
        return this;
    }

    public SimpleAutoScroller setShrinkView(Object obj) {
        this.mShrinkView = (SimpleShrinkView) obj;
        return this;
    }

    public SimpleAutoScroller setStartDelay(int i2) {
        this.mStartDelay = i2;
        return this;
    }

    public SimpleAutoScroller setThemeColor(IThemeColor iThemeColor) {
        this.mThemeColor = iThemeColor;
        return this;
    }

    public void start() {
        if (!isListViewAvailable() || this.mPosition < 0) {
            onError("start fail");
        } else if (this.mShrinkType != DataKey.ShrinkType.NONE) {
            this.mShrinkView = new SimpleShrinkView(this.mBlackboard).withErrorAction(new C0688b(this, 2)).withReadyAction(new C0688b(this, 3)).show(this.mIsShrinkBlackBg);
            Handler handler = new Handler(new b(4, this));
            this.mForceShrinkHandler = handler;
            handler.sendEmptyMessageDelayed(1, 150);
        } else {
            this.mListView.addOnLayoutChangeListener(this.mLayoutChangeListener);
            this.mListView.post(new C0688b(this, 4));
            this.mListView.post(new C0688b(this, 5));
        }
    }

    public SimpleAutoScroller withFinishAction(Runnable runnable) {
        this.mFinishListener = runnable;
        return this;
    }

    public SimpleAutoScroller withStartAction(Runnable runnable) {
        this.mStartListener = runnable;
        return this;
    }

    public SimpleAutoScroller withUpdateAction(Runnable runnable) {
        this.mUpdateListener = runnable;
        return this;
    }

    /* access modifiers changed from: private */
    /* renamed from: shrinkIfFound */
    public boolean lambda$findAndShrink$10(ListViewHolder listViewHolder) {
        SimpleShrinkView simpleShrinkView = this.mShrinkView;
        boolean z = false;
        if (simpleShrinkView == null || simpleShrinkView.mActivity == null) {
            Log.st("SimpleAutoScroller", "shrinkIfFound error { " + this.mShrinkView + "}");
            return false;
        }
        DataKey.ShrinkType shrinkType = this.mShrinkType;
        if (shrinkType == DataKey.ShrinkType.DRAG) {
            this.mHandler = new SimpleDragShrinkHandler(simpleShrinkView, listViewHolder);
        } else if (shrinkType == DataKey.ShrinkType.PINCH) {
            this.mHandler = new NewPinchShrinkHandler(simpleShrinkView, listViewHolder);
        } else if (shrinkType == DataKey.ShrinkType.BACK_PRESSED) {
            this.mHandler = new SimpleBackShrinkHandler(simpleShrinkView, listViewHolder);
        } else if (shrinkType == DataKey.ShrinkType.HIGHLIGHT) {
            this.mHandler = new SimpleHighlightShrinkHandler(simpleShrinkView, listViewHolder).setRadius((int[]) this.mBlackboard.pop("data://story_transition_view_radius"));
        } else if (shrinkType == DataKey.ShrinkType.HIGHLIGHT_JUMP) {
            this.mHandler = new SimpleHighlightJumpHandler(simpleShrinkView, listViewHolder);
        }
        if (this.mHandler == null) {
            return false;
        }
        Log.st("SimpleAutoScroller", "shrinkIfFound {" + this.mPosition + "} start " + this.mShrinkType + " +" + (System.currentTimeMillis() - this.mTimestamp));
        recycle();
        Handler handler = this.mForceShrinkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        SimpleShrinkHandler withRadius = this.mHandler.setAlphaWeight(this.mAlphaWeight).setThemeColor(this.mThemeColor).setPosition(this.mPosition).setPostAnim(this.mPostAnim).setWithRadius(this.mWithRadius);
        GalleryAppBarLayout galleryAppBarLayout = this.mAppbarLayout;
        if (galleryAppBarLayout != null && galleryAppBarLayout.getVisibleHeight() > 0) {
            z = true;
        }
        withRadius.setAppbarVisible(z).setStartDelay(this.mStartDelay).withUpdateAction(this.mUpdateListener).withFinishAction(new com.samsung.android.sum.core.descriptor.b(21, this)).show();
        return true;
    }
}
