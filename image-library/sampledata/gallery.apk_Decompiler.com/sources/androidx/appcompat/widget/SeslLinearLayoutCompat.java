package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;
import androidx.appcompat.animation.SeslRecoilAnimator;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.util.SeslRoundedCorner;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslLinearLayoutCompat extends LinearLayoutCompat {
    private final ItemBackgroundHolder mItemBackgroundHolder;
    private final SeslRecoilAnimator.Holder mRecoilAnimatorHolder;
    private final SeslRoundedCorner mRoundedCorner;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ItemBackgroundHolder {
        Drawable activeBg = null;

        public ItemBackgroundHolder() {
        }

        public void setCancel() {
            Drawable drawable = this.activeBg;
            if (drawable != null) {
                if (drawable instanceof SeslRecoilDrawable) {
                    ((SeslRecoilDrawable) drawable).setCancel();
                } else {
                    drawable.setState(new int[0]);
                }
                this.activeBg = null;
            }
        }

        public void setPress(View view) {
            setRelease();
            Drawable background = view.getBackground();
            this.activeBg = background;
            if (background != null) {
                background.setState(new int[]{16843623});
            }
        }

        public void setRelease() {
            Drawable drawable = this.activeBg;
            if (drawable != null) {
                drawable.setState(new int[0]);
                this.activeBg = null;
            }
        }
    }

    public SeslLinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private View findChildViewUnder(View view, int i2, int i7) {
        View view2 = null;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int[] transformCoordinate = transformCoordinate(viewGroup, i2, i7);
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (isPointInsideView(transformCoordinate[0], transformCoordinate[1], childAt) && (view2 = findChildViewUnder(childAt, transformCoordinate[0], transformCoordinate[1])) != null) {
                    break;
                }
            }
        }
        if (view2 != null || !view.isClickable() || view.getVisibility() != 0 || !view.isEnabled()) {
            return view2;
        }
        return view;
    }

    private View findClickableChildUnder(MotionEvent motionEvent) {
        View view;
        int i2 = 0;
        while (true) {
            if (i2 >= getChildCount()) {
                view = null;
                break;
            }
            view = getChildAt(i2);
            if (isPointInsideView((int) motionEvent.getX(), (int) motionEvent.getY(), view)) {
                break;
            }
            i2++;
        }
        if (view == null) {
            return null;
        }
        View findChildViewUnder = findChildViewUnder(view, (int) motionEvent.getX(), (int) motionEvent.getY());
        if (!(findChildViewUnder == null || findChildViewUnder == view)) {
            if (((double) (findChildViewUnder.getHeight() * findChildViewUnder.getWidth())) < ((double) (view.getHeight() * view.getWidth())) * 0.5d) {
                return null;
            }
        }
        return findChildViewUnder;
    }

    private boolean isPointInsideView(int i2, int i7, View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()).contains(i2, i7);
    }

    private int[] transformCoordinate(View view, int i2, int i7) {
        return new int[]{i2 - view.getLeft(), i7 - view.getTop()};
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mRoundedCorner.drawRoundedCorner(canvas);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 66) {
            if (keyEvent.getAction() == 0) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    this.mRecoilAnimatorHolder.setPress(focusedChild);
                }
            } else {
                this.mRecoilAnimatorHolder.setRelease();
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0 != 212) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r3) {
        /*
            r2 = this;
            int r0 = r3.getAction()
            if (r0 == 0) goto L_0x002b
            r1 = 1
            if (r0 == r1) goto L_0x0020
            r1 = 3
            if (r0 == r1) goto L_0x0015
            r1 = 211(0xd3, float:2.96E-43)
            if (r0 == r1) goto L_0x002b
            r1 = 212(0xd4, float:2.97E-43)
            if (r0 == r1) goto L_0x0020
            goto L_0x003b
        L_0x0015:
            androidx.appcompat.widget.SeslLinearLayoutCompat$ItemBackgroundHolder r0 = r2.mItemBackgroundHolder
            r0.setCancel()
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r0 = r2.mRecoilAnimatorHolder
            r0.setRelease()
            goto L_0x003b
        L_0x0020:
            androidx.appcompat.widget.SeslLinearLayoutCompat$ItemBackgroundHolder r0 = r2.mItemBackgroundHolder
            r0.setRelease()
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r0 = r2.mRecoilAnimatorHolder
            r0.setRelease()
            goto L_0x003b
        L_0x002b:
            android.view.View r0 = r2.findClickableChildUnder(r3)
            if (r0 == 0) goto L_0x003b
            androidx.appcompat.widget.SeslLinearLayoutCompat$ItemBackgroundHolder r1 = r2.mItemBackgroundHolder
            r1.setPress(r0)
            androidx.appcompat.animation.SeslRecoilAnimator$Holder r1 = r2.mRecoilAnimatorHolder
            r1.setPress(r0)
        L_0x003b:
            boolean r2 = super.dispatchTouchEvent(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslLinearLayoutCompat.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public SeslRoundedCorner getRoundedCorner() {
        return this.mRoundedCorner;
    }

    public SeslLinearLayoutCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        int[] iArr = R$styleable.SeslLayout;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        Context context2 = context;
        ViewCompat.saveAttributeDataForStyleable(this, context2, iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        int i7 = obtainStyledAttributes.getInt(R$styleable.SeslLayout_seslLayoutRoundedCorner, 0);
        obtainStyledAttributes.recycle();
        SeslRoundedCorner seslRoundedCorner = new SeslRoundedCorner(context2);
        this.mRoundedCorner = seslRoundedCorner;
        seslRoundedCorner.setRoundedCorners(i7);
        this.mItemBackgroundHolder = new ItemBackgroundHolder();
        this.mRecoilAnimatorHolder = new SeslRecoilAnimator.Holder(context2);
    }
}
