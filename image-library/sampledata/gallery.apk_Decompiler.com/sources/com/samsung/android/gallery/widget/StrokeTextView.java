package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StrokeTextView extends AppCompatTextView {
    private boolean mIsDrawing;
    private boolean mStroke = false;
    private int mStrokeColor;
    private int mStrokeWidth;

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.StrokeTextView);
        this.mStroke = obtainStyledAttributes.getBoolean(R$styleable.StrokeTextView_textStroke, false);
        this.mStrokeWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.StrokeTextView_textStrokeWidth, 0);
        this.mStrokeColor = obtainStyledAttributes.getColor(R$styleable.StrokeTextView_textStrokeColor, -1);
        obtainStyledAttributes.recycle();
    }

    public void invalidate() {
        if (!this.mIsDrawing) {
            super.invalidate();
        }
    }

    public void onDraw(Canvas canvas) {
        if (this.mStroke) {
            this.mIsDrawing = true;
            ColorStateList textColors = getTextColors();
            getPaint().setStyle(Paint.Style.STROKE);
            getPaint().setStrokeWidth((float) this.mStrokeWidth);
            setTextColor(this.mStrokeColor);
            super.onDraw(canvas);
            getPaint().setStyle(Paint.Style.FILL);
            setTextColor(textColors);
            this.mIsDrawing = false;
        }
        super.onDraw(canvas);
    }
}
