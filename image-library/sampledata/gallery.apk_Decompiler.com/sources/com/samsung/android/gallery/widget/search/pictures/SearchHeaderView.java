package com.samsung.android.gallery.widget.search.pictures;

import Ba.g;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchHeaderView {
    /* access modifiers changed from: protected */
    public final String TAG;
    /* access modifiers changed from: protected */
    public MediaItem mMediaItem;
    protected ViewGroup mRootView;
    protected HashMap<String, Supplier<Object>> mSupplierMap;
    protected final boolean mSupportViewByDate;
    protected final View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum HeaderType {
        COUNT_ONLY,
        CREATURE,
        LOCATION,
        CLUSTER_RESULT,
        RELATIONSHIP,
        SCREENSHOT,
        ASK_SCREENSHOT
    }

    public SearchHeaderView(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setHeaderItemClickListener$0(OnHeaderClickListener onHeaderClickListener, View view) {
        onHeaderClickListener.onHeaderClicked(this.mView, -1, (MediaItem) null, (Bitmap) null);
    }

    public void bind(MediaItem mediaItem) {
        if (setHeaderItem(mediaItem)) {
            initHeaderItem();
        }
    }

    public Context getContext() {
        return this.mView.getContext();
    }

    public abstract int getLayoutId();

    public ViewGroup getRootView() {
        return this.mRootView;
    }

    public View getView() {
        return this.mView;
    }

    public abstract void initHeaderItem();

    public boolean isTouchedOnViewRectRange(MotionEvent motionEvent) {
        return ViewUtils.isTouchedOnView(this.mView, motionEvent);
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onHeaderItemClicked() {
        return false;
    }

    public abstract void recycle();

    public abstract void setEnabled(boolean z);

    public abstract boolean setHeaderItem(MediaItem mediaItem);

    public void setHeaderItemClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.mView.setOnClickListener(new g(14, this, onHeaderClickListener));
    }

    public void setUiUpdateSupplier(HashMap<String, Supplier<Object>> hashMap) {
        this.mSupplierMap = hashMap;
    }

    public boolean supportSlideShow() {
        return false;
    }

    public SearchHeaderView(Context context, ViewGroup viewGroup, boolean z) {
        this.TAG = getClass().getSimpleName();
        this.mSupplierMap = new HashMap<>();
        this.mRootView = viewGroup;
        this.mSupportViewByDate = z;
        View inflate = LayoutInflater.from(context).inflate(getLayoutId(), viewGroup, false);
        this.mView = inflate;
        bindView(inflate);
    }

    public void handleResolutionChanged() {
    }

    public void onDestroyView() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void resetViewBy() {
    }

    public void showFuzzyTextOnly() {
    }

    public void bindData(MediaData... mediaDataArr) {
    }

    public void bindView(View view) {
    }

    public void handleOrientationChange(int i2) {
    }

    public void onRequestPermissionResult(int i2) {
    }

    public void recover(SearchHeaderView searchHeaderView) {
    }

    public void setItemCount(int i2) {
    }

    public void setItemCountLineVisibility(int i2) {
    }

    public void showCountHeaderOnly(boolean z) {
    }

    public void updateFuzzyResult(String str) {
    }
}
