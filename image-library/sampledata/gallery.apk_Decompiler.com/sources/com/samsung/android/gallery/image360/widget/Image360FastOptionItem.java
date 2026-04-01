package com.samsung.android.gallery.image360.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.image360.R$color;
import com.samsung.android.gallery.image360.R$dimen;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.image360.R$styleable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360FastOptionItem extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public Image360FastOptionItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void initImageView(TypedArray typedArray, int i2) {
        int i7;
        if (i2 == 1) {
            i7 = R$color.fast_menu_item_text_color;
        } else {
            i7 = R$color.gallery360viewer_view_mode_icon_color;
        }
        this.mImageView.setColorFilter(getContext().getColor(i7));
        Drawable drawable = typedArray.getDrawable(R$styleable.Image360FastOptionItem_image);
        if (drawable != null) {
            this.mImageView.setImageDrawable(drawable);
        }
    }

    private void initTextView(TypedArray typedArray, int i2) {
        int i7;
        int i8;
        if (i2 == 1) {
            i7 = R$dimen.gallery360viewer_fast_option_text_size;
        } else {
            i7 = R$dimen.gallery360viewer_view_mode_icon_text_size;
        }
        if (i2 == 1) {
            i8 = R$color.fast_menu_item_text_color;
        } else {
            i8 = R$color.gallery360viewer_view_mode_text_color;
        }
        int i10 = 0;
        this.mTextView.setTextSize(0, getResources().getDimension(i7));
        this.mTextView.setTextColor(getContext().getColor(i8));
        String string = typedArray.getString(R$styleable.Image360FastOptionItem_text);
        TextView textView = this.mTextView;
        if (string == null) {
            i10 = 8;
        }
        textView.setVisibility(i10);
        if (string != null) {
            this.mTextView.setText(string);
        }
    }

    private void initView(AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.gallery360viewer_fast_option_item, this, true);
        this.mImageView = (ImageView) inflate.findViewById(R$id.img);
        this.mTextView = (TextView) inflate.findViewById(R$id.text);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.Image360FastOptionItem);
            int i2 = obtainStyledAttributes.getInt(R$styleable.Image360FastOptionItem_depth, 1);
            initImageView(obtainStyledAttributes, i2);
            initTextView(obtainStyledAttributes, i2);
            obtainStyledAttributes.recycle();
        }
    }

    public void setColorFilter(int i2) {
        this.mImageView.setColorFilter(getContext().getColor(i2));
    }

    public void setImage(int i2) {
        Drawable drawable = getContext().getDrawable(i2);
        if (drawable != null) {
            this.mImageView.setImageDrawable(drawable);
        }
    }

    public void setText(int i2) {
        setText(getContext().getString(i2));
    }

    public Image360FastOptionItem(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2, 0);
        initView(attributeSet);
    }

    private void setText(String str) {
        this.mTextView.setVisibility(str != null ? 0 : 8);
        if (str != null) {
            this.mTextView.setText(str);
        }
    }
}
