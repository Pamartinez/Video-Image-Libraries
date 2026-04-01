package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleHelpPreference extends HelpPreference {
    public SimpleHelpPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    private void setRoundedCorner(View view) {
        if (view != null) {
            SeApiCompat.setViewRoundedCorner(view, 15);
            SeApiCompat.setViewRoundedCornerColor(view, 15, view.getResources().getColor(R$color.gallery_status_bar_fw_background_color, (Resources.Theme) null));
        }
    }

    private void updateHelpDescription(PreferenceViewHolder preferenceViewHolder) {
        ViewUtils.setText((TextView) preferenceViewHolder.itemView.findViewById(R$id.help_description), this.mHelpDescription);
    }

    private void updateHelpImage(PreferenceViewHolder preferenceViewHolder) {
        setRoundedCorner(preferenceViewHolder.itemView.findViewById(R$id.help_image_container));
        ViewUtils.setImageDrawable((ImageView) preferenceViewHolder.itemView.findViewById(R$id.help_image), this.mHelpImage);
    }

    public int getLayoutId() {
        return R$layout.simple_help_preference_layout;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.itemView.setClickable(false);
        updateHelpImage(preferenceViewHolder);
        updateHelpDescription(preferenceViewHolder);
    }

    public boolean supportCustomLayout() {
        return true;
    }

    public SimpleHelpPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
