package com.samsung.android.gallery.app.ui.viewer2.contentviewer;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.ViewerObjectThread;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import i.C0212a;
import n0.C0235b;
import q6.e;
import u7.C0520a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsViewerHolder<VIEW_BINDING extends ViewBinding> extends RecyclerView.ViewHolder implements IViewerObject, ViewerLifeCycle, FragmentLifeCycle {
    public static final boolean PRINT_DEBUG_VIEW = false;
    private static int SERIAL_NO;
    protected final String HASH_CODE = Integer.toHexString(hashCode());
    protected final StringCompat TAG;
    protected final ActionInvoker mActionInvoker;
    String mDebugText;
    TextView mDebugTextView;
    protected ContentModel mModel;
    /* access modifiers changed from: private */
    public ViewPager2 mParent;
    private final int mSerialNo;
    /* access modifiers changed from: private */
    public final StateListener mStateListener;
    protected final ViewerObjectThread mThread;
    protected final VIEW_BINDING mViewBinding;
    final Runnable mViewConfirmRunner;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface StateListener {
        void onViewConfirm(AbsViewerHolder absViewerHolder);
    }

    public AbsViewerHolder(VIEW_BINDING view_binding, StateListener stateListener) {
        super(view_binding.getRoot());
        StringCompat stringCompat = new StringCompat(getClass().getSimpleName());
        this.TAG = stringCompat;
        this.mActionInvoker = new ActionInvoker(stringCompat.toString());
        this.mThread = new ViewerObjectThread();
        int i2 = SERIAL_NO;
        SERIAL_NO = i2 + 1;
        this.mSerialNo = i2;
        this.mViewConfirmRunner = new Runnable() {
            private ViewPager2 findViewPager(View view) {
                View view2 = (View) view.getParent();
                if (view2 == null || (view2 instanceof ViewPager2)) {
                    return (ViewPager2) view2;
                }
                return findViewPager(view2);
            }

            public void run() {
                Trace.beginSection(AbsViewerHolder.this.TAG + " mViewConfirmRunner");
                if (AbsViewerHolder.this.mParent == null) {
                    AbsViewerHolder absViewerHolder = AbsViewerHolder.this;
                    absViewerHolder.mParent = findViewPager(absViewerHolder.itemView);
                }
                if (AbsViewerHolder.this.mParent != null) {
                    if (AbsViewerHolder.this.isNeedToConfirm()) {
                        AbsViewerHolder.this.mModel.setViewConfirmed(true);
                        AbsViewerHolder.this.mStateListener.onViewConfirm(AbsViewerHolder.this);
                    } else {
                        AbsViewerHolder.this.tryViewConfirm(10);
                    }
                }
                Trace.endSection();
            }
        };
        this.mViewBinding = view_binding;
        this.mStateListener = stateListener;
    }

    private String getDebugHeader() {
        return "\n" + TimeUtil.getIsoLocalTimeInMin(System.currentTimeMillis()) + "|";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        setDebugText(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setDebugText$2() {
        ContentModel contentModel;
        if (this.mDebugTextView != null && (contentModel = this.mModel) != null && contentModel.isViewConfirmed()) {
            this.mDebugTextView.setText(this.mDebugText);
        }
    }

    /* access modifiers changed from: private */
    public void tryViewConfirm(long j2) {
        this.mThread.runOnUiThread(this.mViewConfirmRunner, j2);
    }

    public void addActionInvokeListener() {
        if (PRINT_DEBUG_VIEW) {
            this.mActionInvoker.add(ViewerAction.LOG, new C0520a(0, this));
        }
    }

    public final void attachActionInvoker(ActionInvoker actionInvoker) {
        actionInvoker.attach(this.mActionInvoker);
    }

    public final void bindContentModel(ContentModel contentModel) {
        this.mModel = contentModel;
    }

    public long getDelayTime() {
        if (this.mModel.isFirstView()) {
            return 130;
        }
        return 100;
    }

    public View getTransitionView() {
        return null;
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        MediaItem mediaItem3;
        StringCompat stringCompat = this.TAG;
        stringCompat.setTag(this.mModel.getPosition() + ">" + i2);
        Log.d(this.TAG, "invalidate", this.HASH_CODE, Integer.valueOf(i2), MediaItemUtil.getSimpleLog(mediaItem));
        if (PRINT_DEBUG_VIEW && (mediaItem3 = this.mModel.getMediaItem()) != null) {
            boolean equals = MediaItemUtil.equals(mediaItem3, mediaItem);
            setDebugText("invalidate " + equals + " / " + i2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(mediaItem3.getDisplayName());
            sb2.append(" -> ");
            setDebugText(sb2.toString());
            setDebugText(mediaItem.getDisplayName());
            this.mThread.runOnUiThread(new e(14, this, mediaItem), 100);
        }
    }

    public void invoke(ViewerAction viewerAction, Object... objArr) {
        this.mActionInvoker.invoke(viewerAction, objArr);
    }

    public boolean isAttachedToWindow() {
        return this.itemView.isAttachedToWindow();
    }

    public boolean isNeedToConfirm() {
        if (!this.mModel.isViewConfirmed() && this.itemView.getX() == ((float) ViewMarginUtils.getLeftMargin(this.itemView)) && this.mParent.getScrollState() == 0) {
            return true;
        }
        return false;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        this.TAG.setTag(Integer.valueOf(this.mModel.getPosition()));
    }

    public void onPause() {
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onPause / ");
        }
    }

    public void onResume() {
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onResume / ");
        }
    }

    public void onStart() {
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onStart / ");
        }
    }

    public void onStop() {
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onStop / ");
        }
    }

    public void onViewAttached() {
        tryViewConfirm(getDelayTime());
    }

    public void onViewConfirm() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "onSlideIn(" + this.HASH_CODE + ") " + MediaItemUtil.getDebugLog(mediaItem));
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onViewConfirm / " + this.mModel.getPosition());
        }
    }

    public void onViewDetached() {
        StringCompat stringCompat = this.TAG;
        Log.i(stringCompat, "onSlideOut(" + this.HASH_CODE + ")");
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && mediaItem.isFavourite()) {
            this.mActionInvoker.invoke(ViewerAction.RESET_FAVOURITE_MENU_ITEM, new Object[0]);
        }
        this.itemView.setTranslationX(0.0f);
        this.mModel.setViewConfirmed(false);
        if (PRINT_DEBUG_VIEW) {
            setDebugText("onViewDetached / " + this.mModel.getPosition());
        }
        this.mThread.cancelUiThread(this.mViewConfirmRunner);
    }

    public void onViewRecycled() {
        this.mThread.cancel();
    }

    public void setDebugText(String str) {
        if (PRINT_DEBUG_VIEW) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mDebugText);
            String p6 = C0212a.p(sb2, getDebugHeader(), str);
            this.mDebugText = p6;
            if (p6.length() > 2048) {
                String str2 = this.mDebugText;
                this.mDebugText = str2.substring(str2.length() - 1024);
            }
            this.mThread.runOnUiThread(new t8.e(4, this));
        }
    }

    public void setPhotoViewBmp(PhotoViewCompat photoViewCompat, Bitmap bitmap, MediaItem mediaItem) {
        if (bitmap != null && mediaItem != null) {
            if (mediaItem.isVideo()) {
                this.mThread.runOnUiThread(new e(15, photoViewCompat, bitmap));
            } else {
                this.mThread.runOnUiThread(new C0235b(photoViewCompat, mediaItem, bitmap, 26));
            }
        }
    }

    public void toggleOsd(View view) {
        this.mActionInvoker.invoke(ViewerAction.TOGGLE_OSD, new Object[0]);
    }

    /* access modifiers changed from: private */
    /* renamed from: setViewerName */
    public void lambda$invalidate$1(MediaItem mediaItem) {
    }
}
