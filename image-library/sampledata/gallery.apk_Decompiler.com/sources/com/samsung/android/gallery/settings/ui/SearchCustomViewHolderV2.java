package com.samsung.android.gallery.settings.ui;

import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchCustomViewHolderV2 extends SearchCustomViewHolder {
    private AppCompatCheckBox mCheckbox;

    public SearchCustomViewHolderV2(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        ViewUtils.setVisibleOrGone(getCompoundButton(), true);
        ViewUtils.setVisibleOrGone(view.findViewById(R$id.switch_category), false);
    }

    public void createCompoundButton(View view) {
        this.mCheckbox = (AppCompatCheckBox) view.findViewById(R$id.check_category);
    }

    public CompoundButton getCompoundButton() {
        return this.mCheckbox;
    }

    public void onClickListItem(View view) {
        if (getCompoundButton() != null) {
            getCompoundButton().setChecked(!getCompoundButton().isChecked());
        }
    }

    public void setReorderMode(boolean z) {
        super.setReorderMode(z);
        setSwitchVisibleOrGone(z);
        View view = this.mReorderIconView;
        if (view != null) {
            view.setContentDescription(AppResources.getString(R$string.reorder) + " " + getCategoryName(this.mLocationKey) + ArcCommonLog.TAG_COMMA + AppResources.getString(R$string.speak_button));
        }
    }
}
