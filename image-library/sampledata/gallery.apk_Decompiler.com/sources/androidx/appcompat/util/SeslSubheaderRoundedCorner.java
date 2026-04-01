package androidx.appcompat.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.appcompat.util.SeslRoundedCorner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslSubheaderRoundedCorner extends SeslRoundedCorner {
    public SeslSubheaderRoundedCorner(Context context) {
        super(context);
    }

    private void drawRoundedCornerInternal(Canvas canvas) {
        Rect rect = this.mRoundedCornerBounds;
        int i2 = rect.left;
        int i7 = rect.right;
        int i8 = rect.top;
        int i10 = rect.bottom;
        if ((this.mRoundedCornerMode & 1) != 0) {
            SeslRoundedCorner.SeslRoundedChunkingDrawable seslRoundedChunkingDrawable = this.mTopLeftRound;
            int i11 = this.mRoundRadius;
            seslRoundedChunkingDrawable.setBounds(i2, i10, i2 + i11, i11 + i10);
            this.mTopLeftRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 2) != 0) {
            SeslRoundedCorner.SeslRoundedChunkingDrawable seslRoundedChunkingDrawable2 = this.mTopRightRound;
            int i12 = this.mRoundRadius;
            seslRoundedChunkingDrawable2.setBounds(i7 - i12, i10, i7, i12 + i10);
            this.mTopRightRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 4) != 0) {
            SeslRoundedCorner.SeslRoundedChunkingDrawable seslRoundedChunkingDrawable3 = this.mBottomLeftRound;
            int i13 = this.mRoundRadius;
            seslRoundedChunkingDrawable3.setBounds(i2, i8 - i13, i13 + i2, i8);
            this.mBottomLeftRound.draw(canvas);
        }
        if ((this.mRoundedCornerMode & 8) != 0) {
            SeslRoundedCorner.SeslRoundedChunkingDrawable seslRoundedChunkingDrawable4 = this.mBottomRightRound;
            int i14 = this.mRoundRadius;
            seslRoundedChunkingDrawable4.setBounds(i7 - i14, i8 - i14, i7, i8);
            this.mBottomRightRound.draw(canvas);
        }
    }

    public void drawRoundedCorner(int i2, int i7, int i8, int i10, Canvas canvas) {
        this.mRoundedCornerBounds.set(i2, i7, i8, i10);
        drawRoundedCornerInternal(canvas);
    }

    public void drawRoundedCorner(View view, Canvas canvas) {
        int i2;
        int i7;
        if (view.getTranslationY() != 0.0f) {
            i7 = Math.round(view.getX());
            i2 = Math.round(view.getY());
        } else {
            i7 = view.getLeft();
            i2 = view.getTop();
        }
        this.mRoundedCornerBounds.set(i7, i2, view.getWidth() + i7, view.getHeight() + i2);
        drawRoundedCornerInternal(canvas);
    }
}
