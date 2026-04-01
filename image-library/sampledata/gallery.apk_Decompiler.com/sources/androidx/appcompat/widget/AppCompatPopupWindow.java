package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.core.widget.PopupWindowCompat;
import androidx.reflect.os.SeslBuildReflector$SeslVersionReflector;
import androidx.reflect.view.SeslSemWindowManagerReflector;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.view.SeslViewRuneReflector;
import androidx.reflect.widget.SeslPopupWindowReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR = false;
    private static final boolean ONEUI_5_1_1;
    private static final int[] ONEUI_BLUR_POPUP_BACKGROUND_RES = {R$drawable.sesl_menu_popup_background, R$drawable.sesl_menu_popup_background_dark};
    private Context mContext;
    private boolean mHasNavigationBar;
    private boolean mIsReplacedPoupBackground;
    private int mNavigationBarHeight;
    private boolean mOverlapAnchor;
    private final Rect mTempRect = new Rect();

    static {
        boolean z = false;
        if (SeslBuildReflector$SeslVersionReflector.getField_SEM_PLATFORM_INT() >= 140500) {
            z = true;
        }
        ONEUI_5_1_1 = z;
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        init(context, attributeSet, i2, i7);
    }

    private Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private Transition getTransition(int i2) {
        Transition inflateTransition;
        if (i2 == 0 || i2 == 17760256 || (inflateTransition = TransitionInflater.from(this.mContext).inflateTransition(i2)) == null) {
            return null;
        }
        if (!(inflateTransition instanceof TransitionSet) || ((TransitionSet) inflateTransition).getTransitionCount() != 0) {
            return inflateTransition;
        }
        return null;
    }

    private void init(Context context, AttributeSet attributeSet, int i2, int i7) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.PopupWindow, i2, i7);
        int i8 = R$styleable.PopupWindow_overlapAnchor;
        if (obtainStyledAttributes.hasValue(i8)) {
            setSupportOverlapAnchor(obtainStyledAttributes.getBoolean(i8, false));
        }
        this.mContext = context;
        Transition transition = getTransition(obtainStyledAttributes.getResourceId(R$styleable.PopupWindow_popupEnterTransition, 0));
        Transition transition2 = getTransition(obtainStyledAttributes.getResourceId(R$styleable.PopupWindow_popupExitTransition, 0));
        setEnterTransition(transition);
        setExitTransition(transition2);
        int resourceId = obtainStyledAttributes.getResourceId(R$styleable.PopupWindow_android_popupBackground, -1);
        boolean z = false;
        for (int i10 : ONEUI_BLUR_POPUP_BACKGROUND_RES) {
            if (i10 == resourceId) {
                z = true;
            }
        }
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.PopupWindow_android_popupBackground));
        this.mIsReplacedPoupBackground = !z;
        obtainStyledAttributes.recycle();
        this.mHasNavigationBar = ActionBarPolicy.get(context).hasNavigationBar();
        this.mNavigationBarHeight = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_navigation_bar_height);
    }

    private void setSupportOverlapAnchor(boolean z) {
        if (COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = z;
        } else {
            PopupWindowCompat.setOverlapAnchor(this, z);
        }
    }

    public int getMaxAvailableHeight(View view, int i2, boolean z) {
        int i7;
        int height;
        Rect rect = new Rect();
        if (z) {
            SeslViewReflector.getWindowDisplayFrame(view, rect);
            if (this.mHasNavigationBar && this.mContext.getResources().getConfiguration().orientation != 2) {
                rect.bottom -= this.mNavigationBarHeight;
            }
        } else {
            view.getWindowVisibleDisplayFrame(rect);
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int seslGetCenterPointForFoldable = seslGetCenterPointForFoldable();
        if (seslGetCenterPointForFoldable == 0 || iArr[1] >= seslGetCenterPointForFoldable) {
            i7 = rect.bottom;
        } else {
            i7 = seslGetCenterPointForFoldable;
        }
        if (getSupportOverlapAnchor()) {
            height = iArr[1];
        } else {
            height = view.getHeight() + iArr[1];
        }
        int i8 = (i7 - height) - i2;
        int i10 = iArr[1];
        if (seslGetCenterPointForFoldable == 0 || i10 < seslGetCenterPointForFoldable) {
            seslGetCenterPointForFoldable = rect.top;
        }
        int max = Math.max(i8, (i10 - seslGetCenterPointForFoldable) + i2);
        if (getBackground() == null) {
            return max;
        }
        getBackground().getPadding(this.mTempRect);
        Rect rect2 = this.mTempRect;
        return max - (rect2.top + rect2.bottom);
    }

    public boolean getSupportOverlapAnchor() {
        return PopupWindowCompat.getOverlapAnchor(this);
    }

    public int seslGetCenterPointForFoldable() {
        Context context;
        DisplayManager displayManager;
        Display display;
        if (!ONEUI_5_1_1 || (context = this.mContext) == null || (displayManager = (DisplayManager) context.getSystemService("display")) == null || (display = displayManager.getDisplay(0)) == null || !SeslSemWindowManagerReflector.isTableMode()) {
            return 0;
        }
        Activity activity = getActivity(this.mContext);
        if (activity != null && activity.isInMultiWindowMode()) {
            return 0;
        }
        Point point = new Point();
        display.getRealSize(point);
        if (SeslViewRuneReflector.supportFoldableDualDisplay()) {
            if (this.mContext.getResources().getConfiguration().orientation == 2) {
                int i2 = point.y;
                int i7 = point.x;
                if (i2 > i7) {
                    return i7 / 2;
                }
                return i2 / 2;
            }
        } else if (SeslViewRuneReflector.supportFoldableNoSubDisplay() && this.mContext.getResources().getConfiguration().orientation == 1) {
            int i8 = point.y;
            int i10 = point.x;
            if (i8 > i10) {
                return i8 / 2;
            }
            return i10 / 2;
        }
        return 0;
    }

    public boolean seslIsAvailableBlurBackground() {
        return !this.mIsReplacedPoupBackground;
    }

    public void seslSetAllowScrollingAnchorParent(boolean z) {
        SeslPopupWindowReflector.setAllowScrollingAnchorParent(this, z);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mIsReplacedPoupBackground = true;
        super.setBackgroundDrawable(drawable);
    }

    public void showAsDropDown(View view, int i2, int i7) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i7 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i7);
    }

    public void update(View view, int i2, int i7, int i8, int i10) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i7 -= view.getHeight();
        }
        super.update(view, i2, i7, i8, i10);
    }

    public void showAsDropDown(View view, int i2, int i7, int i8) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i7 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i7, i8);
    }
}
