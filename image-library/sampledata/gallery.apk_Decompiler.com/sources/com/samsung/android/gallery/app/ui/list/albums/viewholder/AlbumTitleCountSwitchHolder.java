package com.samsung.android.gallery.app.ui.list.albums.viewholder;

import Fa.K;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.widget.SwitchCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumTitleCountSwitchHolder extends AlbumTitleCountViewHolder {
    SwitchCompat mSwitch;

    public AlbumTitleCountSwitchHolder(View view, int i2) {
        super(view, i2);
    }

    private String getSwitchDescription(boolean z) {
        int i2;
        if (z) {
            i2 = R.string.event_settings_on;
        } else {
            i2 = R.string.event_settings_off;
        }
        return getString(i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.switch_album);
        this.mSwitch = switchCompat;
        if (switchCompat != null) {
            switchCompat.setOnCheckedChangeListener(new K(1, this));
        }
    }

    public String getClassNameForAccessibility() {
        return Switch.class.getName();
    }

    public String getContentDescription() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.getContentDescription());
        if (ViewUtils.isVisible(this.mSwitch)) {
            str = ArcCommonLog.TAG_COMMA + getSwitchDescription(this.mSwitch.isChecked());
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public View getDecoView(int i2) {
        if (i2 == 25) {
            return this.mSwitch;
        }
        return super.getDecoView(i2);
    }

    public int getViewItemId(View view) {
        if (view == this.mSwitch) {
            return 4;
        }
        return super.getViewItemId(view);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SwitchCompat switchCompat = this.mSwitch;
        if (switchCompat != null && switchCompat.getTag() == null) {
            this.itemView.setContentDescription(getContentDescription());
            SeApiCompat.announceVoiceAssistant(this.mSwitch.getContext(), getSwitchDescription(z));
            onItemClickInternal(getViewItemId(compoundButton));
        }
    }
}
