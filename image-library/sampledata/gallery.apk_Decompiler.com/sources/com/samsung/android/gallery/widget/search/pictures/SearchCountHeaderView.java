package com.samsung.android.gallery.widget.search.pictures;

import C3.n;
import H7.A;
import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCountHeaderView extends SearchHeaderView {
    protected static final InputBlock sInputBlock = new InputBlock();
    protected TextView mItemCount;
    View mItemCountLine;
    ImageView mMapAll;
    TextView mViewBy;
    boolean mViewByDate = false;

    public SearchCountHeaderView(ViewGroup viewGroup) {
        super(viewGroup.getContext(), viewGroup, false);
    }

    private String getItemCountText(int i2) {
        Context context = getRootView().getContext();
        if (context == null) {
            Log.se(this.TAG, "Couldn't get item count -> Context is null");
            return "";
        }
        Resources resources = context.getResources();
        if (i2 == 1) {
            return resources.getString(R$string.speak_folder_name_1_item);
        }
        return String.format(resources.getString(R$string.speak_folder_name_n_items), new Object[]{Integer.valueOf(i2)});
    }

    private int getViewByResource() {
        if (this.mViewByDate) {
            return R$string.ungroup;
        }
        return R$string.group_by_date;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViewAll$0(View view) {
        if (sInputBlock.set("ViewBy", 300)) {
            this.mViewByDate = !this.mViewByDate;
            setViewByText();
            Blackboard.postEventGlobal(EventMessage.obtain(8007, Boolean.valueOf(this.mViewByDate)));
        }
    }

    private void setViewByText() {
        ViewUtils.setText(this.mViewBy, getViewByResource());
    }

    private void updateViewByVisibility(boolean z) {
        boolean z3;
        TextView textView = this.mViewBy;
        if (!this.mSupportViewByDate || !z) {
            z3 = false;
        } else {
            z3 = true;
        }
        ViewUtils.setVisibleOrGone(textView, z3);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mItemCountLine = view.findViewById(R$id.item_count_line);
        this.mItemCount = (TextView) view.findViewById(R$id.item_count);
        this.mViewBy = (TextView) view.findViewById(R$id.view_by);
        this.mMapAll = (ImageView) view.findViewById(R$id.map_all);
        SeApiCompat.setButtonShapeEnabled(this.mViewBy);
        bindViewAll();
    }

    public void bindViewAll() {
        ImageView imageView;
        if (this.mSupportViewByDate) {
            TextView textView = this.mViewBy;
            if (textView != null) {
                textView.setOnClickListener(new A(23, this));
            }
            if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchResultOnMapView) && (imageView = this.mMapAll) != null) {
                imageView.setOnClickListener(new n(1));
            }
        }
    }

    public int getLayoutId() {
        if (this.mSupportViewByDate) {
            return R$layout.search_cluster_v2_count_header_text;
        }
        return R$layout.search_count_header_text;
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (ViewUtils.isTouchedOnView(this.mItemCount, motionEvent) || ViewUtils.isTouchedOnView(this.mViewBy, motionEvent)) {
            return false;
        }
        return true;
    }

    public void resetViewBy() {
        this.mViewByDate = false;
        setViewByText();
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return false;
    }

    public void setItemCount(int i2) {
        boolean z;
        this.mItemCount.setText(getItemCountText(i2));
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        updateViewByVisibility(z);
        updateFromSupplier();
    }

    public void setItemCountLineVisibility(int i2) {
        this.mItemCountLine.setVisibility(i2);
    }

    public void updateFromSupplier() {
        Supplier supplier;
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchResultOnMapView) && this.mMapAll != null && (supplier = this.mSupplierMap.get("enable_map_all")) != null) {
            ViewUtils.setVisibleOrGone(this.mMapAll, ((Boolean) supplier.get()).booleanValue());
        }
    }

    public SearchCountHeaderView(ViewGroup viewGroup, boolean z) {
        super(viewGroup.getContext(), viewGroup, z);
    }

    public void initHeaderItem() {
    }

    public void recycle() {
    }

    public void setEnabled(boolean z) {
    }
}
