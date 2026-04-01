package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R$attr;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.reflect.widget.SeslAdapterViewReflector;
import java.lang.reflect.Field;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DropDownListView extends ListView {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding = 0;
    private int mSelectionLeftPadding = 0;
    private int mSelectionRightPadding = 0;
    private int mSelectionTopPadding = 0;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect = new Rect();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static void drawableHotspotChanged(View view, float f, float f5) {
            view.drawableHotspotChanged(f, f5);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api33Impl {
        public static boolean isSelectedChildViewEnabled(AbsListView absListView) {
            return absListView.isSelectedChildViewEnabled();
        }

        public static void setSelectedChildViewEnabled(AbsListView absListView, boolean z) {
            absListView.setSelectedChildViewEnabled(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GateKeeperDrawable extends DrawableWrapperCompat {
        private boolean mEnabled = true;

        public GateKeeperDrawable(Drawable drawable) {
            super(drawable);
        }

        public void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        public void setEnabled(boolean z) {
            this.mEnabled = z;
        }

        public void setHotspot(float f, float f5) {
            if (this.mEnabled) {
                super.setHotspot(f, f5);
            }
        }

        public void setHotspotBounds(int i2, int i7, int i8, int i10) {
            if (this.mEnabled) {
                super.setHotspotBounds(i2, i7, i8, i10);
            }
        }

        public boolean setState(int[] iArr) {
            if (this.mEnabled) {
                return super.setState(iArr);
            }
            return false;
        }

        public boolean setVisible(boolean z, boolean z3) {
            if (this.mEnabled) {
                return super.setVisible(z, z3);
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PreApi33Impl {
        private static final Field sIsChildViewEnabled;

        static {
            Field field = null;
            try {
                field = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            sIsChildViewEnabled = field;
        }

        public static boolean isSelectedChildViewEnabled(AbsListView absListView) {
            Field field = sIsChildViewEnabled;
            if (field == null) {
                return false;
            }
            try {
                return field.getBoolean(absListView);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }

        public static void setSelectedChildViewEnabled(AbsListView absListView, boolean z) {
            Field field = sIsChildViewEnabled;
            if (field != null) {
                try {
                    field.set(absListView, Boolean.valueOf(z));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ResolveHoverRunnable implements Runnable {
        public ResolveHoverRunnable() {
        }

        public void cancel() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.mResolveHoverRunnable = null;
            dropDownListView.removeCallbacks(this);
        }

        public void post() {
            DropDownListView.this.post(this);
        }

        public void run() {
            DropDownListView dropDownListView = DropDownListView.this;
            dropDownListView.mResolveHoverRunnable = null;
            dropDownListView.drawableStateChanged();
        }
    }

    public DropDownListView(Context context, boolean z) {
        super(context, (AttributeSet) null, R$attr.dropDownListViewStyle);
        this.mHijackFocus = z;
        setCacheColorHint(0);
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.mClickAnimation;
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.cancel();
            this.mClickAnimation = null;
        }
    }

    private void clickPressedItem(View view, int i2) {
        performItemClick(view, i2, getItemIdAtPosition(i2));
    }

    private void drawSelectorCompat(Canvas canvas) {
        Drawable selector;
        if (!this.mSelectorRect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas);
        }
    }

    private void positionSelectorCompat(int i2, View view) {
        Rect rect = this.mSelectorRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.mSelectionLeftPadding;
        rect.top -= this.mSelectionTopPadding;
        rect.right += this.mSelectionRightPadding;
        rect.bottom += this.mSelectionBottomPadding;
        boolean superIsSelectedChildViewEnabled = superIsSelectedChildViewEnabled();
        if (view.isEnabled() != superIsSelectedChildViewEnabled) {
            superSetSelectedChildViewEnabled(!superIsSelectedChildViewEnabled);
            if (i2 != -1) {
                refreshDrawableState();
            }
        }
    }

    private void positionSelectorLikeFocusCompat(int i2, View view) {
        boolean z;
        Drawable selector = getSelector();
        boolean z3 = true;
        if (selector == null || i2 == -1) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            selector.setVisible(false, false);
        }
        positionSelectorCompat(i2, view);
        if (z) {
            Rect rect = this.mSelectorRect;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            if (getVisibility() != 0) {
                z3 = false;
            }
            selector.setVisible(z3, false);
            DrawableCompat.setHotspot(selector, exactCenterX, exactCenterY);
        }
    }

    private void positionSelectorLikeTouchCompat(int i2, View view, float f, float f5) {
        positionSelectorLikeFocusCompat(i2, view);
        Drawable selector = getSelector();
        if (selector != null && i2 != -1) {
            DrawableCompat.setHotspot(selector, f, f5);
        }
    }

    private void setPressedItem(View view, int i2, float f, float f5) {
        View childAt;
        this.mDrawsInPressedState = true;
        Api21Impl.drawableHotspotChanged(this, f, f5);
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i7 = this.mMotionPosition;
        if (!(i7 == -1 || (childAt = getChildAt(i7 - getFirstVisiblePosition())) == null || childAt == view || !childAt.isPressed())) {
            childAt.setPressed(false);
        }
        this.mMotionPosition = i2;
        Api21Impl.drawableHotspotChanged(view, f - ((float) view.getLeft()), f5 - ((float) view.getTop()));
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        positionSelectorLikeTouchCompat(i2, view, f, f5);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private void setSelectorEnabled(boolean z) {
        GateKeeperDrawable gateKeeperDrawable = this.mSelector;
        if (gateKeeperDrawable != null) {
            gateKeeperDrawable.setEnabled(z);
        }
    }

    private boolean superIsSelectedChildViewEnabled() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.isSelectedChildViewEnabled(this);
        }
        return PreApi33Impl.isSelectedChildViewEnabled(this);
    }

    private void superSetSelectedChildViewEnabled(boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setSelectedChildViewEnabled(this, z);
        } else {
            PreApi33Impl.setSelectedChildViewEnabled(this, z);
        }
    }

    private boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState;
    }

    private void updateSelectorStateCompat() {
        Drawable selector = getSelector();
        if (selector != null && touchModeDrawsInPressedStateCompat() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    public void dispatchDraw(Canvas canvas) {
        drawSelectorCompat(canvas);
        super.dispatchDraw(canvas);
    }

    public void drawableStateChanged() {
        if (this.mResolveHoverRunnable == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            updateSelectorStateCompat();
        }
    }

    public boolean hasFocus() {
        if (this.mHijackFocus || super.hasFocus()) {
            return true;
        }
        return false;
    }

    public boolean hasWindowFocus() {
        if (this.mHijackFocus || super.hasWindowFocus()) {
            return true;
        }
        return false;
    }

    public boolean isFocused() {
        if (this.mHijackFocus || super.isFocused()) {
            return true;
        }
        return false;
    }

    public boolean isInTouchMode() {
        if ((!this.mHijackFocus || !this.mListSelectionHidden) && !super.isInTouchMode()) {
            return false;
        }
        return true;
    }

    public int measureHeightOfChildrenCompat(int i2, int i7, int i8, int i10, int i11) {
        int i12;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i13 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        View view = null;
        while (i14 < count) {
            int itemViewType = adapter.getItemViewType(i14);
            if (itemViewType != i15) {
                view = null;
                i15 = itemViewType;
            }
            view = adapter.getView(i14, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i17 = layoutParams.height;
            if (i17 > 0) {
                i12 = View.MeasureSpec.makeMeasureSpec(i17, 1073741824);
            } else {
                i12 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i2, i12);
            view.forceLayout();
            if (i14 > 0) {
                i13 += dividerHeight;
            }
            i13 += view.getMeasuredHeight();
            if (i13 < i10) {
                if (i11 >= 0 && i14 >= i11) {
                    i16 = i13;
                }
                i14++;
            } else if (i11 < 0 || i14 <= i11 || i16 <= 0 || i13 == i10) {
                return i10;
            } else {
                return i16;
            }
        }
        return i13;
    }

    public void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r0 != 3) goto L_0x000e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onForwardedEvent(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x0014
            r9 = 3
            if (r0 == r9) goto L_0x0011
        L_0x000e:
            r3 = r1
            r9 = r2
            goto L_0x0046
        L_0x0011:
            r9 = r2
            r3 = r9
            goto L_0x0046
        L_0x0014:
            r3 = r1
            goto L_0x0017
        L_0x0016:
            r3 = r2
        L_0x0017:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L_0x001e
            goto L_0x0011
        L_0x001e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L_0x0031
            r9 = r1
            goto L_0x0046
        L_0x0031:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.setPressedItem(r3, r5, r4, r9)
            if (r0 != r1) goto L_0x000e
            r7.clickPressedItem(r3, r5)
            goto L_0x000e
        L_0x0046:
            if (r3 == 0) goto L_0x004a
            if (r9 == 0) goto L_0x004d
        L_0x004a:
            r7.clearPressedItem()
        L_0x004d:
            if (r3 == 0) goto L_0x0065
            androidx.core.widget.ListViewAutoScrollHelper r9 = r7.mScrollHelper
            if (r9 != 0) goto L_0x005a
            androidx.core.widget.ListViewAutoScrollHelper r9 = new androidx.core.widget.ListViewAutoScrollHelper
            r9.<init>(r7)
            r7.mScrollHelper = r9
        L_0x005a:
            androidx.core.widget.ListViewAutoScrollHelper r9 = r7.mScrollHelper
            r9.setEnabled(r1)
            androidx.core.widget.ListViewAutoScrollHelper r9 = r7.mScrollHelper
            r9.onTouch(r7, r8)
            return r3
        L_0x0065:
            androidx.core.widget.ListViewAutoScrollHelper r7 = r7.mScrollHelper
            if (r7 == 0) goto L_0x006c
            r7.setEnabled(r2)
        L_0x006c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.DropDownListView.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.mResolveHoverRunnable == null) {
            ResolveHoverRunnable resolveHoverRunnable = new ResolveHoverRunnable();
            this.mResolveHoverRunnable = resolveHoverRunnable;
            resolveHoverRunnable.post();
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            int field_mSelectedPosition = SeslAdapterViewReflector.getField_mSelectedPosition(this);
            if (!(pointToPosition == -1 || pointToPosition == field_mSelectedPosition)) {
                if (getChildAt(pointToPosition - getFirstVisiblePosition()).isEnabled()) {
                    requestFocus();
                    if (!isHovered()) {
                        setHovered(true);
                    }
                }
                drawableStateChanged();
            }
            return onHoverEvent;
        }
        setSelection(-1);
        return onHoverEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mMotionPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        ResolveHoverRunnable resolveHoverRunnable = this.mResolveHoverRunnable;
        if (resolveHoverRunnable != null) {
            resolveHoverRunnable.cancel();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    public void setSelector(Drawable drawable) {
        GateKeeperDrawable gateKeeperDrawable;
        if (drawable != null) {
            gateKeeperDrawable = new GateKeeperDrawable(drawable);
        } else {
            gateKeeperDrawable = null;
        }
        this.mSelector = gateKeeperDrawable;
        super.setSelector(gateKeeperDrawable);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.mSelectionLeftPadding = rect.left;
        this.mSelectionTopPadding = rect.top;
        this.mSelectionRightPadding = rect.right;
        this.mSelectionBottomPadding = rect.bottom;
    }
}
