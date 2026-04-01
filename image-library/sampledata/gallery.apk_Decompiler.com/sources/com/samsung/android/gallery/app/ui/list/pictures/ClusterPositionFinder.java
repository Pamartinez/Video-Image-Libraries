package com.samsung.android.gallery.app.ui.list.pictures;

import A.a;
import A4.C0369d;
import a6.C0418a;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.gallery.app.ui.list.picker.timeline.TimelinePickerFragment;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.o3dp.lib3dphotography.i;
import java.lang.ref.WeakReference;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterPositionFinder {
    private PositionHandler mHandler;
    private boolean mIsCheckingTargetPosition = true;
    private OnCompletedListener mOnCompletedListener;
    private Dialog mProgressDialog = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCompletedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PositionHandler extends Handler {
        private WeakReference<PicturesViewAdapter> mAdapter;
        private WeakReference<Context> mContext;
        private WeakReference<ClusterPositionFinder> mFinder;

        public PositionHandler(Context context, ClusterPositionFinder clusterPositionFinder) {
            super(Looper.getMainLooper());
            this.mContext = new WeakReference<>(context);
            this.mFinder = new WeakReference<>(clusterPositionFinder);
        }

        public void handleMessage(Message message) {
            a.w(new StringBuilder("handleMessage "), message.what, "ClusterPositionFinder");
            Context context = this.mContext.get();
            PicturesViewAdapter picturesViewAdapter = this.mAdapter.get();
            ClusterPositionFinder clusterPositionFinder = this.mFinder.get();
            if (context == null || clusterPositionFinder == null || picturesViewAdapter == null) {
                Log.e("ClusterPositionFinder", "fail to handle. null");
                return;
            }
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 == 2) {
                    clusterPositionFinder.moveToTargetPosition(picturesViewAdapter);
                    clusterPositionFinder.setCheckingState(false);
                } else if (i2 == 3) {
                    clusterPositionFinder.dismissProgressDialog();
                    clusterPositionFinder.onCompleted(message.arg1);
                    picturesViewAdapter.getView().getListView().setVisibility(0);
                }
            } else if (picturesViewAdapter.getView() == null || picturesViewAdapter.getView().getListView() == null) {
                clusterPositionFinder.dismissProgressDialog();
            } else if (picturesViewAdapter.isFullyLoaded()) {
                sendEmptyMessage(2);
            } else {
                sendEmptyMessageDelayed(1, 300);
            }
        }

        public void setAdapter(PicturesViewAdapter picturesViewAdapter) {
            this.mAdapter = new WeakReference<>(picturesViewAdapter);
        }
    }

    public ClusterPositionFinder(GalleryListView galleryListView, OnCompletedListener onCompletedListener) {
        this.mHandler = new PositionHandler(galleryListView.getContext(), this);
        this.mOnCompletedListener = onCompletedListener;
        setCheckingState(true);
        galleryListView.setVisibility(4);
        ThreadUtil.postOnUiThreadDelayed(new i(6, this, galleryListView), 100);
    }

    /* access modifiers changed from: private */
    public void dismissProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
        this.mProgressDialog = null;
    }

    private int findTargetGroupPosition(PicturesViewAdapter picturesViewAdapter) {
        LaunchIntent launchIntent;
        if (picturesViewAdapter.getView() == null || picturesViewAdapter.getView().getListView() == null || (launchIntent = (LaunchIntent) picturesViewAdapter.getView().getBlackboard().read("data://launch_intent", null)) == null) {
            return 0;
        }
        long dateTaken = launchIntent.getDateTaken();
        int dividerIndex = picturesViewAdapter.getDividerIndex(dateTaken);
        Log.d("ClusterPositionFinder", "findTargetGroupPosition", Long.valueOf(dateTaken), Integer.valueOf(dividerIndex));
        return Math.max(dividerIndex, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTargetPosition$2(PicturesViewAdapter picturesViewAdapter, int i2) {
        if (picturesViewAdapter.getView() != null) {
            Optional.ofNullable(picturesViewAdapter.getView().getListView()).ifPresent(new C0369d(i2, 19));
            if (this.mHandler != null) {
                Message message = new Message();
                message.what = 3;
                message.arg1 = i2;
                this.mHandler.sendMessageDelayed(message, 300);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToTargetPosition$3(PicturesViewAdapter picturesViewAdapter) {
        int findTargetGroupPosition = findTargetGroupPosition(picturesViewAdapter);
        Log.d("ClusterPositionFinder", "position : " + findTargetGroupPosition);
        ThreadUtil.postOnUiThread(new C0418a((Object) this, (Object) picturesViewAdapter, findTargetGroupPosition, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(GalleryListView galleryListView) {
        showProgressDialog(galleryListView.getContext());
    }

    /* access modifiers changed from: private */
    public void moveToTargetPosition(PicturesViewAdapter picturesViewAdapter) {
        ThreadUtil.postOnBgThread(new i(7, this, picturesViewAdapter));
    }

    /* access modifiers changed from: private */
    public void onCompleted(int i2) {
        OnCompletedListener onCompletedListener = this.mOnCompletedListener;
        if (onCompletedListener != null) {
            ((TimelinePickerFragment) ((com.samsung.android.sdk.scs.ai.language.a) onCompletedListener).e).lambda$onViewCreated$0(i2);
        }
    }

    private void showProgressDialog(Context context) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressCircleBuilder(context).removeDimBehind().create();
        }
        this.mProgressDialog.show();
    }

    public void checkFullLoadDone() {
        this.mHandler.sendEmptyMessageDelayed(1, 100);
    }

    public boolean isCheckingTargetPosition() {
        return this.mIsCheckingTargetPosition;
    }

    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler = null;
    }

    public void setAdapter(PicturesViewAdapter picturesViewAdapter) {
        this.mHandler.setAdapter(picturesViewAdapter);
    }

    public void setCheckingState(boolean z) {
        this.mIsCheckingTargetPosition = z;
    }
}
