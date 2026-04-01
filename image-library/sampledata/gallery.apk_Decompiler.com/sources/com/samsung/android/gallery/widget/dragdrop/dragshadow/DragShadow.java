package com.samsung.android.gallery.widget.dragdrop.dragshadow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragShadow extends View.DragShadowBuilder {
    private int mDiffX = 0;
    private int mDiffY = 0;
    private int mHeight = 1;
    private BitmapDrawable mShadowBitmapDrawable;
    private int mWidth = 1;

    public DragShadow(View view, Context context, Bitmap bitmap) {
        super(view);
        if (bitmap != null) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
            this.mShadowBitmapDrawable = bitmapDrawable;
            this.mWidth = Math.max(bitmapDrawable.getBitmap().getWidth(), 1);
            int max = Math.max(this.mShadowBitmapDrawable.getBitmap().getHeight(), 1);
            this.mHeight = max;
            this.mShadowBitmapDrawable.setBounds(0, 0, this.mWidth, max);
        }
        int i2 = this.mWidth;
        if (i2 == 1 || this.mHeight == 1) {
            Log.w((CharSequence) "DragShadow", "constructor unexpected size", Integer.valueOf(i2), Integer.valueOf(this.mHeight), Logger.toSimpleString(bitmap));
        }
    }

    public void onDrawShadow(Canvas canvas) {
        BitmapDrawable bitmapDrawable = this.mShadowBitmapDrawable;
        if (bitmapDrawable != null) {
            bitmapDrawable.draw(canvas);
        }
    }

    public void onProvideShadowMetrics(Point point, Point point2) {
        BitmapDrawable bitmapDrawable = this.mShadowBitmapDrawable;
        if (bitmapDrawable == null) {
            Log.e("DragShadow", "mShadowBitmapDrawable null");
        } else {
            bitmapDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
        }
        point.set(this.mWidth, this.mHeight);
        point2.set((this.mWidth / 2) - this.mDiffX, (this.mHeight / 2) + this.mDiffY);
    }
}
