package com.samsung.android.gallery.widget.listview;

import A.a;
import Fb.e;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryRecyclerAdapter<VH extends ListViewHolder> extends RecyclerView.Adapter<VH> {
    protected final String TAG = getClass().getSimpleName();
    private final ListViewHolder.OnImageBindListener mOnBindImageListener = new e(this);
    private ListViewHolder.OnItemClickListener mOnItemClickListener = new e(this);
    private ListViewHolder.OnItemLongClickListener mOnItemLongClickListener = new e(this);
    private ListViewHolder.OnItemSecondaryClickListener mOnItemSecondaryClickListener = new e(this);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Log.initPLog(System.currentTimeMillis());
        Log.p(this.TAG, a.d(i2, i7, "onItemClick {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        handleItemClick(listViewHolder, i2, mediaItem, i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        Log.d(this.TAG, a.d(i2, i7, "onItemLongClick {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        return onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        Log.d(this.TAG, a.d(i2, i7, "onSecondaryClick {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        onListItemSecondaryClickInternal(listViewHolder, i2, mediaItem, i7, pointF, motionEvent);
    }

    public boolean checkIfEmpty() {
        if (getItemCount() == 0 || (supportHeader() && getItemCount() == 1)) {
            return true;
        }
        return false;
    }

    public TextView getHeaderDescriptionView() {
        return null;
    }

    public int getHeaderDescriptionWidthOffset() {
        return 0;
    }

    public View getHeaderView() {
        return null;
    }

    public View getTipCardView() {
        return null;
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        onListItemClickInternal(listViewHolder, i2, mediaItem, i7);
    }

    public boolean hasFooter() {
        return false;
    }

    public final boolean isData(int i2) {
        if (getItemViewType(i2) >= 0) {
            return true;
        }
        return false;
    }

    public boolean isDivider(int i2) {
        return ViewHolderValue.isDivider(getItemViewType(i2));
    }

    public final boolean isFooter(int i2) {
        return ViewHolderValue.isFooter(getItemViewType(i2));
    }

    public final boolean isHeader(int i2) {
        return ViewHolderValue.isHeader(getItemViewType(i2));
    }

    public final boolean isMonthForViewingData(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }

    public final boolean isYearData(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    public void onBindViewHolder(VH vh, int i2) {
        setListeners(vh);
        View view = vh.itemView;
        view.setTag(33554433, "V@" + i2);
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        throw new AssertionError(C0086a.i(i2, "undefined view type : "));
    }

    public abstract void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);

    public abstract boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7);

    public abstract void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent);

    public void setHeaderView(View view, int i2) {
    }

    public void setListeners(VH vh) {
        vh.setOnItemClickListener(this.mOnItemClickListener);
        vh.setOnItemLongClickListener(this.mOnItemLongClickListener);
        vh.setOnSecondaryClickListener(this.mOnItemSecondaryClickListener);
        vh.setOnBindImageListener(this.mOnBindImageListener);
    }

    public boolean skipEmptyView(boolean z) {
        return false;
    }

    public boolean supportEmptyDescription() {
        return true;
    }

    public boolean supportHeader() {
        return false;
    }

    public View getHeaderView(int i2) {
        return null;
    }

    public boolean setHeaderView(View view) {
        return false;
    }

    public void enableHeaderView(boolean z) {
    }

    public void onEmptySpaceSecondaryClick(PointF pointF) {
    }

    public void onBindImageInternal(int i2, ListViewHolder listViewHolder) {
    }
}
