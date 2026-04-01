package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer mContainer;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static void getOutline(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }
    }

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.mContainer = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mSplitBackground;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.mBackground;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.mContainer;
        Drawable drawable3 = actionBarContainer2.mStackedBackground;
        if (drawable3 != null && actionBarContainer2.mIsStacked) {
            drawable3.draw(canvas);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (!actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mBackground;
            if (drawable != null) {
                Api21Impl.getOutline(drawable, outline);
            }
        } else if (actionBarContainer.mSplitBackground != null) {
            Api21Impl.getOutline(actionBarContainer.mBackground, outline);
        }
    }

    public void setAlpha(int i2) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
