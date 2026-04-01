package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayButtonTextView extends LinearLayout {
    private final ImageView mIconView;
    private final TextView mTextView;
    private final LinearLayout mViewLayout;

    public PlayButtonTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public void reset() {
        this.mIconView.setImageDrawable((Drawable) null);
        this.mIconView.setVisibility(8);
        this.mTextView.setText((CharSequence) null);
        setVisibility(8);
    }

    public void resizeFont(float f, float f5) {
        if (f <= 0.0f) {
            f = this.mTextView.getTextSize();
        }
        float f8 = getResources().getConfiguration().fontScale;
        if (f8 > f5) {
            f = (f / f8) * f5;
        }
        this.mTextView.setTextSize(0, f);
    }

    public void resizeFontLarge(float f) {
        resizeFont(f, 1.3f);
    }

    public void setButtonContentDescription(int i2) {
        this.mTextView.setContentDescription(getContext().getString(i2) + ArcCommonLog.TAG_COMMA + getContext().getString(R$string.speak_button));
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mTextView.setContentDescription(charSequence);
    }

    public void setIcon(Drawable drawable) {
        this.mIconView.setImageDrawable(drawable);
        setIconVisibility(0);
    }

    public void setIconVisibility(int i2) {
        this.mIconView.setVisibility(i2);
        if (i2 == 0) {
            ViewMarginUtils.setStartPadding(this.mViewLayout, getResources().getDimensionPixelOffset(R$dimen.play_button_icon_start_padding));
        }
    }

    public void setText(CharSequence charSequence) {
        this.mTextView.setText(charSequence);
    }

    public PlayButtonTextView(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        View inflate = LayoutInflater.from(context).inflate(R$layout.button_icon_text_view, this, true);
        this.mViewLayout = (LinearLayout) inflate.findViewById(R$id.button_icon_text_view_layout);
        ImageView imageView = (ImageView) inflate.findViewById(R$id.button_icon);
        this.mIconView = imageView;
        TextView textView = (TextView) inflate.findViewById(R$id.button_text);
        this.mTextView = textView;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ButtonTextView);
            String string = obtainStyledAttributes.getString(R$styleable.ButtonTextView_text);
            if (string != null) {
                textView.setText(string);
            }
            float f = obtainStyledAttributes.getFloat(R$styleable.ButtonTextView_textSize, 0.0f);
            if (f > 0.0f) {
                textView.setTextSize(f);
            }
            int resourceId = obtainStyledAttributes.getResourceId(R$styleable.ButtonTextView_icon, 0);
            if (resourceId > 0) {
                imageView.setImageDrawable(getContext().getDrawable(resourceId));
                setIconVisibility(0);
            }
            int integer = obtainStyledAttributes.getInteger(R$styleable.ButtonTextView_iconVisibility, 8);
            if (integer != 8) {
                setIconVisibility(integer);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setContentDescription(int i2) {
        this.mTextView.setContentDescription(getContext().getString(i2));
    }

    public void setText(int i2) {
        this.mTextView.setText(i2);
    }

    public void setIcon(Bitmap bitmap) {
        this.mIconView.setImageBitmap(bitmap);
        setIconVisibility(0);
    }

    public void setIcon(Uri uri) {
        this.mIconView.setImageURI(uri);
        setIconVisibility(0);
    }
}
