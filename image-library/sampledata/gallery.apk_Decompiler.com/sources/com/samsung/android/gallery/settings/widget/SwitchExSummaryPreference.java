package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SeslSwitchPreferenceScreen;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwitchExSummaryPreference extends SeslSwitchPreferenceScreen {
    private CharSequence mExtraSummary;
    private int mExtraSummaryColor;
    private boolean mExtraSummaryVisible;
    private int mProgressLayoutId;
    private int mSwitchLayoutId;

    public SwitchExSummaryPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        init();
    }

    private void init() {
        this.mSwitchLayoutId = getWidgetLayoutResource();
        this.mProgressLayoutId = R$layout.preference_progress_bar_layout;
        this.mExtraSummaryColor = Utils.getTextColorFromTextColorAttr(getContext(), 16842808);
    }

    private void updateExtraSummary(PreferenceViewHolder preferenceViewHolder) {
        if (!this.mExtraSummaryVisible || TextUtils.isEmpty(this.mExtraSummary)) {
            ViewUtils.setVisibleOrGone(preferenceViewHolder.itemView.findViewById(R$id.extra_summary), false);
            return;
        }
        View findViewById = preferenceViewHolder.itemView.findViewById(16908304);
        if (findViewById != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            if (viewGroup instanceof RelativeLayout) {
                View view = preferenceViewHolder.itemView;
                int i2 = R$id.extra_summary;
                viewGroup.removeView(view.findViewById(i2));
                View inflate = View.inflate(getContext(), R$layout.preference_extra_summary_text_view_layout, (ViewGroup) null);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(3, findViewById.getId());
                inflate.setLayoutParams(layoutParams);
                viewGroup.addView(inflate, viewGroup.indexOfChild(findViewById) + 1);
                TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(i2);
                if (textView != null) {
                    textView.setText(this.mExtraSummary);
                    textView.setTextColor(this.mExtraSummaryColor);
                    textView.setVisibility(0);
                }
            }
        }
    }

    public boolean isSwitchShowing() {
        if (getWidgetLayoutResource() == this.mSwitchLayoutId) {
            return true;
        }
        return false;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        updateExtraSummary(preferenceViewHolder);
    }

    public void setExtraSummary(String str) {
        this.mExtraSummary = str;
        notifyChanged();
    }

    public void setExtraSummaryColor(int i2) {
        this.mExtraSummaryColor = i2;
        notifyChanged();
    }

    public boolean setExtraSummaryVisible(boolean z) {
        this.mExtraSummaryVisible = z;
        notifyChanged();
        return this.mExtraSummaryVisible;
    }

    public void showProgress(boolean z) {
        int i2;
        if (z) {
            i2 = this.mProgressLayoutId;
        } else {
            i2 = this.mSwitchLayoutId;
        }
        setWidgetLayoutResource(i2);
        setEnabled(!z);
    }

    public void showSwitch(boolean z) {
        int i2;
        if (z) {
            i2 = this.mSwitchLayoutId;
        } else {
            i2 = 0;
        }
        setWidgetLayoutResource(i2);
    }

    public SwitchExSummaryPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    public SwitchExSummaryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SwitchExSummaryPreference(Context context) {
        super(context);
        init();
    }
}
