package com.samsung.android.gallery.app.ui.viewholders;

import a6.C0419b;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesAdapter;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPicturesDateLocationViewHolder extends DateLocationViewHolder {
    private ImageView mExpandButton;
    private OnExpandClickListener mExpandClickedListener;
    private boolean mIsExpanded;
    private boolean mIsRtl = Features.isEnabled(Features.IS_RTL);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnExpandClickListener {
    }

    public SearchPicturesDateLocationViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void noticeIfFirstClicked(View view) {
        if (this.mIsExpanded && PreferenceCache.NoticeExpandGroupByDate.compareAndSet(true, false)) {
            Toast.makeText(view.getContext(), view.getContext().getString(R.string.notice_group_by_date_expand, new Object[]{this.mDateText.getText()}), 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void onExpandButtonClicked(View view) {
        this.mIsExpanded = !this.mIsExpanded;
        updateArrowDirection();
        OnExpandClickListener onExpandClickListener = this.mExpandClickedListener;
        if (onExpandClickListener != null) {
            ((SearchPicturesAdapter) ((p) onExpandClickListener).e).onExpandClicked(getMediaItem(), getViewPosition(), this.mIsExpanded);
            noticeIfFirstClicked(view);
        }
    }

    private void updateArrowDirection() {
        ImageView imageView = this.mExpandButton;
        boolean z = this.mIsExpanded;
        int i2 = 270;
        boolean z3 = this.mIsRtl;
        if (!z ? !z3 : z3) {
            i2 = 90;
        }
        imageView.setRotation((float) i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_arrow);
        this.mExpandButton = imageView;
        imageView.setOnClickListener(new C0419b(1, this));
    }

    public void enableExpandButton(boolean z, OnExpandClickListener onExpandClickListener) {
        ViewUtils.setVisibleOrGone(this.mExpandButton, z);
        if (!z) {
            onExpandClickListener = null;
        }
        this.mExpandClickedListener = onExpandClickListener;
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibleOrGone(this.mExpandButton, false);
        this.mIsExpanded = false;
        this.mExpandClickedListener = null;
    }

    public void setExpanded(boolean z) {
        this.mIsExpanded = z;
        updateArrowDirection();
    }
}
