package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$color;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$styleable;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TipCardPreference extends BasePreference {
    private int mButtonTextColor;
    private View.OnClickListener mCancelButtonClickListener;
    private String mCancelButtonText;
    private View.OnClickListener mDoneButtonClickListener;
    private String mDoneButtonText;

    public TipCardPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAttr(context, attributeSet);
    }

    private void initView(PreferenceViewHolder preferenceViewHolder) {
        TextView textView = (TextView) preferenceViewHolder.itemView.findViewById(R$id.cancel_button);
        textView.setText(this.mCancelButtonText);
        textView.setTextColor(this.mButtonTextColor);
        textView.setOnClickListener(this.mCancelButtonClickListener);
        TextView textView2 = (TextView) preferenceViewHolder.itemView.findViewById(R$id.done_button);
        textView2.setText(this.mDoneButtonText);
        textView2.setTextColor(this.mButtonTextColor);
        textView2.setOnClickListener(this.mDoneButtonClickListener);
        if (Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            ((RelativeLayout) preferenceViewHolder.itemView.findViewById(R$id.tip_card_layout_background)).setBackgroundColor(getContext().getColor(R$color.update_app_card_view_bg_color));
        }
    }

    private void setAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TipCardPreference);
        this.mCancelButtonText = obtainStyledAttributes.getString(R$styleable.TipCardPreference_cancelButtonText);
        this.mDoneButtonText = obtainStyledAttributes.getString(R$styleable.TipCardPreference_doneButtonText);
        this.mButtonTextColor = obtainStyledAttributes.getColor(R$styleable.TipCardPreference_buttonTextColor, -1);
        obtainStyledAttributes.recycle();
    }

    public int getLayoutId() {
        return R$layout.tip_card_layout_settings;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        initView(preferenceViewHolder);
    }

    public TipCardPreference setButtonTextColor(int i2) {
        this.mButtonTextColor = getContext().getColor(i2);
        return this;
    }

    public TipCardPreference setNegativeButton(int i2, View.OnClickListener onClickListener) {
        this.mCancelButtonText = getContext().getString(i2);
        this.mCancelButtonClickListener = onClickListener;
        return this;
    }

    public TipCardPreference setPositiveButton(int i2, View.OnClickListener onClickListener) {
        this.mDoneButtonText = getContext().getString(i2);
        this.mDoneButtonClickListener = onClickListener;
        return this;
    }

    public boolean supportCustomLayout() {
        return true;
    }

    public TipCardPreference(Context context) {
        super(context);
    }
}
