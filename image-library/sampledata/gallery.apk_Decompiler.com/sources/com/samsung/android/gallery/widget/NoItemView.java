package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoItemView extends RelativeLayout {
    TextView mDescriptionView;
    TextView mLabelView;

    public NoItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
        getAttrs(attributeSet);
    }

    public void bindView(View view) {
        this.mLabelView = (TextView) view.findViewById(R$id.label);
        this.mDescriptionView = (TextView) view.findViewById(R$id.description);
    }

    public void getAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.NoItemView);
        setTypeArray(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public int getLayoutId() {
        return R$layout.noitem_layout;
    }

    public void initView() {
        bindView(LayoutInflater.from(getContext()).inflate(getLayoutId(), this, true));
    }

    public void setDescription(int i2) {
        setDescription(i2 == 0 ? null : getContext().getString(i2));
    }

    public void setLabel(int i2) {
        ViewUtils.setText(this.mLabelView, i2);
    }

    public void setTypeArray(TypedArray typedArray) {
        String string = typedArray.getString(R$styleable.NoItemView_label);
        if (string != null) {
            this.mLabelView.setText(string);
            this.mLabelView.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mLabelView.getLayoutParams();
            layoutParams.addRule(14, R$id.container);
            this.mLabelView.setLayoutParams(layoutParams);
        }
        if (!Features.isEnabled(Features.IS_KNOX_MODE)) {
            String string2 = typedArray.getString(R$styleable.NoItemView_description);
            if (string2 != null) {
                setDescription(string2);
                return;
            }
            return;
        }
        this.mDescriptionView.setVisibility(8);
    }

    public void setDescription(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mDescriptionView.setVisibility(8);
            return;
        }
        this.mDescriptionView.setText(str);
        this.mDescriptionView.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mDescriptionView.getLayoutParams();
        layoutParams.addRule(3, R$id.label);
        layoutParams.addRule(14, R$id.container);
        layoutParams.setMargins(layoutParams.getMarginStart(), getResources().getDimensionPixelOffset(R$dimen.noitem_description_margin_top), layoutParams.getMarginEnd(), layoutParams.bottomMargin);
        this.mDescriptionView.setLayoutParams(layoutParams);
    }

    public void setLabel(String str) {
        ViewUtils.setText(this.mLabelView, str);
    }
}
