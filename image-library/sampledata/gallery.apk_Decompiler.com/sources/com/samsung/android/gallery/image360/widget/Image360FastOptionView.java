package com.samsung.android.gallery.image360.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.image360.R$color;
import com.samsung.android.gallery.image360.R$drawable;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.image360.R$string;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360FastOptionView extends RelativeLayout implements View.OnClickListener, IImage360FastOptionView {
    private Image360FastOptionItem m360ViewBtn;
    private LinearLayout mBubblePickerContainer;
    private Image360FastOptionItem mDualViewBtn;
    private Image360FastOptionViewListener mListener;
    private Image360FastOptionItem mModeSelectorBtn;
    private Image360FastOptionItem mMoreBtn;
    private Image360FastOptionMoreMenu mMoreMenu;
    private Image360FastOptionItem mMotionViewBtn;
    private Image360FastOptionItem mRoundViewBtn;
    private LinearLayout mViewIconContainer;
    private final int[] mViewModeIcons;
    private final int[] mViewModeNames;

    public Image360FastOptionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void enableView(View view, boolean z) {
        int i2;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.gallery360viewer_fast_option_view, this, true);
        Image360FastOptionItem image360FastOptionItem = (Image360FastOptionItem) inflate.findViewById(R$id.btn_motion_view);
        this.mMotionViewBtn = image360FastOptionItem;
        image360FastOptionItem.setOnClickListener(this);
        ((Image360FastOptionItem) inflate.findViewById(R$id.btn_reset)).setOnClickListener(this);
        Image360FastOptionItem image360FastOptionItem2 = (Image360FastOptionItem) inflate.findViewById(R$id.btn_mode_selector);
        this.mModeSelectorBtn = image360FastOptionItem2;
        image360FastOptionItem2.setOnClickListener(this);
        this.mBubblePickerContainer = (LinearLayout) inflate.findViewById(R$id.bubble_picker_container);
        this.mViewIconContainer = (LinearLayout) inflate.findViewById(R$id.view_icon_container);
        Image360FastOptionItem image360FastOptionItem3 = (Image360FastOptionItem) inflate.findViewById(R$id.btn_dual_view);
        this.mDualViewBtn = image360FastOptionItem3;
        image360FastOptionItem3.setOnClickListener(this);
        ((Image360FastOptionItem) inflate.findViewById(R$id.btn_panoramic_view)).setOnClickListener(this);
        Image360FastOptionItem image360FastOptionItem4 = (Image360FastOptionItem) inflate.findViewById(R$id.btn_360_view);
        this.m360ViewBtn = image360FastOptionItem4;
        image360FastOptionItem4.setOnClickListener(this);
        Image360FastOptionItem image360FastOptionItem5 = (Image360FastOptionItem) inflate.findViewById(R$id.btn_round_view);
        this.mRoundViewBtn = image360FastOptionItem5;
        image360FastOptionItem5.setOnClickListener(this);
        ((Image360FastOptionItem) inflate.findViewById(R$id.btn_stretched_view)).setOnClickListener(this);
        Image360FastOptionItem image360FastOptionItem6 = (Image360FastOptionItem) inflate.findViewById(R$id.btn_more);
        this.mMoreBtn = image360FastOptionItem6;
        image360FastOptionItem6.setOnClickListener(this);
        this.mMoreMenu = new Image360FastOptionMoreMenu(getContext(), this.mMoreBtn);
    }

    private boolean isViewIconId(int i2) {
        if (i2 == R$id.btn_dual_view || i2 == R$id.btn_panoramic_view || i2 == R$id.btn_360_view || i2 == R$id.btn_round_view || i2 == R$id.btn_stretched_view) {
            return true;
        }
        return false;
    }

    private void setVisibleView(View view, boolean z) {
        int i2;
        if (view != null) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    private void updateFastOptionState(Image360FastOptionItem image360FastOptionItem, boolean z) {
        int i2;
        if (z) {
            i2 = R$color.gallery360viewer_view_mode_icon_color_selected;
        } else {
            i2 = R$color.fast_menu_item_text_color;
        }
        image360FastOptionItem.setColorFilter(i2);
    }

    public void configurationChanged() {
        this.mMoreMenu.dismiss();
    }

    public boolean isViewIconContainerVisible() {
        if (this.mViewIconContainer.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        boolean z;
        int id = view.getId();
        if (id == R$id.btn_motion_view) {
            Image360FastOptionViewListener image360FastOptionViewListener = this.mListener;
            if (image360FastOptionViewListener != null) {
                image360FastOptionViewListener.onMotionViewClicked();
            }
        } else if (id == R$id.btn_reset) {
            Image360FastOptionViewListener image360FastOptionViewListener2 = this.mListener;
            if (image360FastOptionViewListener2 != null) {
                image360FastOptionViewListener2.onResetClicked();
            }
        } else if (id == R$id.btn_mode_selector) {
            int i2 = 0;
            if (this.mViewIconContainer.getVisibility() == 8) {
                z = true;
            } else {
                z = false;
            }
            updateFastOptionState(this.mModeSelectorBtn, z);
            LinearLayout linearLayout = this.mViewIconContainer;
            if (!z) {
                i2 = 8;
            }
            linearLayout.setVisibility(i2);
            Image360FastOptionViewListener image360FastOptionViewListener3 = this.mListener;
            if (image360FastOptionViewListener3 != null) {
                image360FastOptionViewListener3.onModeSelectorClicked(z);
            }
        } else if (isViewIconId(id)) {
            Image360FastOptionViewListener image360FastOptionViewListener4 = this.mListener;
            if (image360FastOptionViewListener4 != null) {
                image360FastOptionViewListener4.onViewIconClicked(Integer.parseInt(view.getTag().toString()));
            }
        } else if (id == R$id.btn_more) {
            this.mMoreMenu.toggle();
        }
    }

    public void setEnableMotionView(boolean z) {
        updateFastOptionState(this.mMotionViewBtn, z);
    }

    public void setListener(Image360FastOptionViewListener image360FastOptionViewListener) {
        this.mListener = image360FastOptionViewListener;
        this.mMoreMenu.setListener(image360FastOptionViewListener);
    }

    public void setVisibleMotionView(boolean z) {
        float f;
        setVisibleView(this.mMotionViewBtn, z);
        LinearLayout linearLayout = this.mBubblePickerContainer;
        if (z) {
            f = 3.0f;
        } else {
            f = 2.0f;
        }
        linearLayout.setWeightSum(f);
    }

    public void setVisibleSelfieViews(boolean z) {
        if (z) {
            setVisibleView(this.m360ViewBtn, false);
            setVisibleView(this.mRoundViewBtn, false);
            setVisibleView(this.mDualViewBtn, false);
        }
    }

    public void setVisibleViewIconContainer(boolean z) {
        setVisibleView(this.mViewIconContainer, z);
        updateFastOptionState(this.mModeSelectorBtn, z);
    }

    public void updateModeSelector(int i2) {
        this.mModeSelectorBtn.setImage(this.mViewModeIcons[i2]);
        this.mModeSelectorBtn.setText(this.mViewModeNames[i2]);
        String string = this.mModeSelectorBtn.getContext().getString(this.mViewModeNames[i2]);
        this.mModeSelectorBtn.setContentDescription(string);
        this.mModeSelectorBtn.setTooltipText(string);
    }

    public void updateMoreMenu(ArrayList<MenuItem> arrayList, boolean z) {
        if (z) {
            enableView(this.mMoreBtn, this.mMoreMenu.isEnabled());
            return;
        }
        Context context = getContext();
        if (arrayList != null && !arrayList.isEmpty() && context != null) {
            this.mMoreMenu.update(context, arrayList);
            enableView(this.mMoreBtn, true);
        }
    }

    public Image360FastOptionView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2, 0);
        this.mViewModeIcons = new int[]{R$drawable.gallery_ic_detail_360_view, R$drawable.gallery_ic_detail_round, R$drawable.gallery_ic_detail_stretch, R$drawable.gallery_ic_detail_dual, R$drawable.gallery_ic_detail_panoramic};
        this.mViewModeNames = new int[]{R$string.gallery360viewer_mode_360_view, R$string.gallery360viewer_mode_round_view, R$string.gallery360viewer_mode_stretched_view, R$string.gallery360viewer_mode_dual_view, R$string.gallery360viewer_mode_panorama_view};
        initView();
    }
}
