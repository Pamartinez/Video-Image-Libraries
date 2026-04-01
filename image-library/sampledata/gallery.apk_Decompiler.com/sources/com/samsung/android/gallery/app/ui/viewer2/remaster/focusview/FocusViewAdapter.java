package com.samsung.android.gallery.app.ui.viewer2.remaster.focusview;

import Ba.g;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FocusViewAdapter extends RecyclerView.Adapter<FocusItemViewHolder> {
    private final AccessibilityDelegateCompat mAccessibilityDelegateCompat = new AccessibilityDelegateCompat() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setRoleDescription(AppResources.getString(R.string.speak_button));
        }
    };
    private final ArrayList<RectF> mFocusRectList = new ArrayList<>();
    private Consumer<RectF> mItemClickedListener;
    private final MediaItem mRemasterItem;
    private int mSelectedPosition = 0;
    private Bitmap mSourceBmp;

    public FocusViewAdapter(MediaItem mediaItem, Bitmap bitmap) {
        this.mRemasterItem = mediaItem;
        this.mSourceBmp = bitmap;
    }

    private String getContentDescription(int i2) {
        if (i2 == 0) {
            return AppResources.getString(R.string.remaster_focus_view_full_image);
        }
        if (getItemCount() > 2) {
            return AppResources.getString(R.string.remaster_focus_view_close_up_pd, Integer.valueOf(i2));
        }
        return AppResources.getString(R.string.remaster_focus_view_close_up);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0(FocusItemViewHolder focusItemViewHolder, View view) {
        int i2 = this.mSelectedPosition;
        int layoutPosition = focusItemViewHolder.getLayoutPosition();
        this.mSelectedPosition = layoutPosition;
        if (i2 != layoutPosition) {
            notifyItemChanged(i2);
            notifyItemChanged(this.mSelectedPosition);
        }
        Consumer<RectF> consumer = this.mItemClickedListener;
        if (consumer != null) {
            consumer.accept(this.mFocusRectList.get(this.mSelectedPosition));
        }
    }

    public int getItemCount() {
        return this.mFocusRectList.size();
    }

    public void recycle() {
        this.mFocusRectList.clear();
        this.mItemClickedListener = null;
        this.mSourceBmp = null;
    }

    public void resetSelectedItem() {
        notifyItemChanged(this.mSelectedPosition);
        this.mSelectedPosition = 0;
        notifyItemChanged(0);
    }

    public void setData(ArrayList<RectF> arrayList) {
        if (this.mRemasterItem != null) {
            this.mFocusRectList.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void setItemClickListener(Consumer<RectF> consumer) {
        this.mItemClickedListener = consumer;
    }

    public void onBindViewHolder(FocusItemViewHolder focusItemViewHolder, int i2) {
        focusItemViewHolder.setCropRectRatio(this.mFocusRectList.get(i2));
        focusItemViewHolder.itemView.setSelected(this.mSelectedPosition == focusItemViewHolder.getLayoutPosition());
        focusItemViewHolder.itemView.setContentDescription(getContentDescription(i2));
    }

    public FocusItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        FocusItemViewHolder focusItemViewHolder = new FocusItemViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_remaster_focus_view_layout, viewGroup, false));
        focusItemViewHolder.itemView.setOnClickListener(new g(11, this, focusItemViewHolder));
        ViewCompat.setAccessibilityDelegate(focusItemViewHolder.itemView, this.mAccessibilityDelegateCompat);
        focusItemViewHolder.setItem(this.mRemasterItem);
        focusItemViewHolder.setImage(this.mSourceBmp);
        return focusItemViewHolder;
    }
}
