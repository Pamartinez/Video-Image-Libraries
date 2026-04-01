package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AutoCompleteListHolder extends ListViewHolder {
    private View mDivider;
    private ImageView mIconView;
    private TextView mTitleView;

    public AutoCompleteListHolder(View view) {
        super(view, 0);
    }

    public final void bindView(View view) {
        this.mIconView = (ImageView) view.findViewById(R.id.auto_complete_icon);
        this.mTitleView = (TextView) view.findViewById(R.id.auto_complete_title);
        this.mDivider = this.itemView.findViewById(R.id.auto_complete_item_divider);
        if (PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
            ViewUtils.setVisibleOrGone(this.mIconView, false);
        }
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public void setDividerVisible(boolean z) {
        int i2;
        View view = this.mDivider;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
    }

    public void setIcon(Drawable drawable) {
        if (PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
            ViewUtils.setVisibleOrGone(this.mIconView, false);
        } else {
            this.mIconView.setImageDrawable(drawable);
        }
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
    }
}
