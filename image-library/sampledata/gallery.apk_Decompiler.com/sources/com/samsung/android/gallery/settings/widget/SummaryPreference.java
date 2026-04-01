package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SummaryPreference extends Preference {
    View mExtraView;
    int mMaxLines = 30;

    public SummaryPreference(Context context) {
        super(context);
    }

    public void appendView(View view) {
        this.mExtraView = view;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, 16908304);
        view.setLayoutParams(layoutParams);
        view.setPadding(0, 0, 0, 0);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(16908304);
        if (textView != null) {
            textView.setMaxLines(this.mMaxLines);
            View view = this.mExtraView;
            if (view != null) {
                ViewUtils.removeSelf(view);
                ((ViewGroup) textView.getParent()).addView(this.mExtraView);
            }
        }
        super.onBindViewHolder(preferenceViewHolder);
    }
}
