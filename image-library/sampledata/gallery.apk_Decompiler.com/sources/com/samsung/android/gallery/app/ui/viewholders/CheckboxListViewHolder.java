package com.samsung.android.gallery.app.ui.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.samsung.android.gallery.widget.abstraction.ContextThemeWrapperCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CheckboxListViewHolder extends ListViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    /* access modifiers changed from: protected */
    public CheckBox mCheckboxView;
    protected ViewStub mCheckboxViewStub;
    protected View mDividerView;
    protected boolean mEnabled = false;
    /* access modifiers changed from: protected */
    public ListViewHolder.SelectListener mSelectListener;
    protected boolean mUseThumbnailCheckBox = true;

    public CheckboxListViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void inflateCheckbox() {
        CheckBox checkBox = (CheckBox) inflateStub(this.mCheckboxViewStub);
        this.mCheckboxView = checkBox;
        onCheckboxViewStubInflated(checkBox);
    }

    private boolean isShowCheckBox() {
        int i2 = this.mSupportDecoItemType;
        if (i2 == -1 || (i2 & 1) == 0) {
            return false;
        }
        return true;
    }

    public void bindView(View view) {
        this.mCheckboxViewStub = (ViewStub) view.findViewById(R.id.checkboxViewStub);
        this.mDividerView = view.findViewById(R.id.divider_layout);
    }

    public void clearReplacedView(View view) {
        if (this.mCheckboxView == view) {
            this.mCheckboxView = null;
        }
    }

    public CheckBox getCheckbox() {
        if (this.mCheckboxView == null && this.mCheckboxViewStub != null) {
            inflateCheckbox();
        }
        return this.mCheckboxView;
    }

    public Context getContext() {
        return this.itemView.getContext();
    }

    public View getDividerView() {
        return this.mDividerView;
    }

    public final LayoutInflater getStubLayoutInflater() {
        LayoutInflater layoutInflater;
        if (this.itemView.getContext() instanceof ContextThemeWrapperCompat) {
            layoutInflater = ((ContextThemeWrapperCompat) this.itemView.getContext()).getStubLayoutInflater();
        } else {
            layoutInflater = null;
        }
        if (layoutInflater != null) {
            return layoutInflater;
        }
        return LayoutInflater.from(this.itemView.getContext());
    }

    public int getViewItemId(View view) {
        if (view == this.mCheckboxView) {
            return 1;
        }
        return 0;
    }

    public boolean hasCheckbox() {
        if (this.mCheckboxView == null && this.mCheckboxViewStub == null) {
            return false;
        }
        return true;
    }

    public View inflateStub(ViewStub viewStub) {
        View inflate = getStubLayoutInflater().inflate(viewStub.getLayoutResource(), (ViewGroup) null, false);
        ViewUtils.replaceView(viewStub, inflate);
        return inflate;
    }

    public boolean isCheckBoxEnabled() {
        if (!hasCheckbox() || !this.mEnabled) {
            return false;
        }
        return true;
    }

    public void onCheckboxViewStubInflated(CheckBox checkBox) {
        checkBox.setOnClickListener(this);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i2;
        if (this.mUseThumbnailCheckBox) {
            if (z) {
                i2 = R.drawable.gallery_btn_check_bg_mtrl;
            } else {
                i2 = R.drawable.gallery_btn_uncheck_bg_mtrl;
            }
            compoundButton.setBackgroundResource(i2);
        }
    }

    public void onClick(View view) {
        onItemClickInternal(getViewItemId(view));
    }

    public void recycle() {
        this.mEnabled = false;
        CheckBox checkBox = this.mCheckboxView;
        if (checkBox != null) {
            checkBox.setVisibility(8);
            this.mCheckboxView.setChecked(false);
            this.mCheckboxView.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        this.mSelectListener = null;
        super.recycle();
    }

    public void recycleToBackup() {
        super.recycleToBackup();
        replaceWithViewStubToClear(this.mCheckboxView, this.mCheckboxViewStub);
        this.mCheckboxView = null;
    }

    public final void replaceWithViewStubToClear(View view, ViewStub viewStub) {
        if (view != null && viewStub != null) {
            ViewUtils.replaceView(view, viewStub);
            clearReplacedView(view);
        }
    }

    public void setCheckboxEnabled(boolean z) {
        if (hasCheckbox()) {
            this.mEnabled = z;
            if (z && this.mCheckboxView == null && this.mCheckboxViewStub != null) {
                inflateCheckbox();
            }
            if (this.mCheckboxView == null) {
                return;
            }
            if (!this.mEnabled || (!isShowCheckBox() && !this.mCheckboxView.isChecked())) {
                this.mCheckboxView.setVisibility(8);
                this.mCheckboxView.setChecked(false);
                this.mCheckboxView.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                return;
            }
            this.mCheckboxView.setVisibility(0);
            this.mCheckboxView.setOnCheckedChangeListener(this);
        }
    }

    public void setChecked(boolean z) {
        CheckBox checkBox;
        int i2;
        if (hasCheckbox() && (checkBox = this.mCheckboxView) != null) {
            checkBox.setChecked(z);
            CheckBox checkBox2 = this.mCheckboxView;
            if (isShowCheckBox() || z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            checkBox2.setVisibility(i2);
        }
    }

    public void setSelectListener(ListViewHolder.SelectListener selectListener) {
        this.mSelectListener = selectListener;
    }

    public void setUseThumbnailCheckbox(boolean z) {
        this.mUseThumbnailCheckBox = z;
    }

    public void skipCheckboxAnimation() {
        CheckBox checkBox;
        if (hasCheckbox() && (checkBox = this.mCheckboxView) != null) {
            checkBox.jumpDrawablesToCurrentState();
        }
    }
}
