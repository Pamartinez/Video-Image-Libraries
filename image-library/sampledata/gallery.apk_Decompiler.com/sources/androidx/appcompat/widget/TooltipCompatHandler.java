package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.reflect.provider.SeslSettingsReflector$SeslSystemReflector;
import androidx.reflect.view.SeslPointerIconReflector;
import androidx.reflect.view.SeslViewReflector;
import com.samsung.android.sum.core.Def;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static TooltipCompatHandler sActiveHandler = null;
    private static boolean sIsCustomTooltipPosition = false;
    private static boolean sIsForceActionBarX = false;
    private static boolean sIsForceBelow = false;
    private static boolean sIsTooltipNull = false;
    private static int sLayoutDirection;
    private static TooltipCompatHandler sPendingHandler;
    private static int sPosX;
    private static int sPosY;
    /* access modifiers changed from: private */
    public final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private final Runnable mCheckHoverRunnable = new Runnable() {
        public void run() {
            if (TooltipCompatHandler.this.mAnchor != null && !TooltipCompatHandler.this.mAnchor.isHovered()) {
                Log.i("TooltipCompatHandler", "isHovered is false. Hide!!");
                TooltipCompatHandler.this.hide();
            }
        }
    };
    private boolean mForceNextChangeSignificant;
    private boolean mFromTouch;
    private final Runnable mHideRunnable = new b(this, 1);
    private final int mHoverSlop;
    private boolean mInitialWindowFocus = false;
    private boolean mIsForceExitDelay = false;
    private boolean mIsSPenPointChanged = false;
    private boolean mIsShowRunnablePostDelayed = false;
    private int mLastHoverEvent = -1;
    private int mLastOrientation;
    View.OnLayoutChangeListener mLayoutChangeListener;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable = new b(this, 0);
    private final CharSequence mTooltipText;

    private TooltipCompatHandler(View view, CharSequence charSequence) {
        this.mAnchor = view;
        this.mTooltipText = charSequence;
        this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(view.getContext()));
        forceNextChangeSignificant();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void cancelPendingShow() {
        this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private void forceNextChangeSignificant() {
        this.mForceNextChangeSignificant = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        show(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$1(Resources resources, View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        TooltipPopup tooltipPopup;
        if (!(resources.getConfiguration().orientation == this.mLastOrientation || (tooltipPopup = this.mPopup) == null || !tooltipPopup.isShowing())) {
            hide();
        }
        this.mLastOrientation = resources.getConfiguration().orientation;
    }

    private void scheduleShow() {
        this.mAnchor.postDelayed(this.mShowRunnable, (long) ViewConfiguration.getLongPressTimeout());
    }

    public static void seslSetTooltipForceActionBarPosX(boolean z) {
        sIsForceActionBarX = z;
    }

    public static void seslSetTooltipForceBelow(boolean z) {
        sIsForceBelow = z;
    }

    public static void seslSetTooltipNull(boolean z) {
        sIsTooltipNull = z;
    }

    private static void setPendingHandler(TooltipCompatHandler tooltipCompatHandler) {
        TooltipCompatHandler tooltipCompatHandler2 = sPendingHandler;
        if (tooltipCompatHandler2 != null) {
            tooltipCompatHandler2.cancelPendingShow();
        }
        sPendingHandler = tooltipCompatHandler;
        if (tooltipCompatHandler != null) {
            tooltipCompatHandler.scheduleShow();
        }
    }

    public static void setTooltipText(View view, CharSequence charSequence) {
        if (view == null) {
            Log.i("TooltipCompatHandler", "view is null");
            return;
        }
        sIsForceActionBarX = false;
        TooltipCompatHandler tooltipCompatHandler = sPendingHandler;
        if (tooltipCompatHandler != null && tooltipCompatHandler.mAnchor == view) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            TooltipCompatHandler tooltipCompatHandler2 = sActiveHandler;
            if (tooltipCompatHandler2 != null && tooltipCompatHandler2.mAnchor == view) {
                tooltipCompatHandler2.hide();
            }
            view.setOnLongClickListener((View.OnLongClickListener) null);
            view.setLongClickable(false);
            view.setOnHoverListener((View.OnHoverListener) null);
            Context context = view.getContext();
            if (!view.isEnabled() && context != null) {
                SeslViewReflector.semSetPointerIcon(view, 2, PointerIcon.getSystemIcon(context, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()));
                return;
            }
            return;
        }
        new TooltipCompatHandler(view, charSequence);
    }

    private boolean updateAnchorPos(MotionEvent motionEvent) {
        int x9 = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (!this.mForceNextChangeSignificant && Math.abs(x9 - this.mAnchorX) <= this.mHoverSlop && Math.abs(y - this.mAnchorY) <= this.mHoverSlop) {
            return false;
        }
        this.mAnchorX = x9;
        this.mAnchorY = y;
        this.mForceNextChangeSignificant = false;
        return true;
    }

    public void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            TooltipPopup tooltipPopup = this.mPopup;
            if (tooltipPopup != null) {
                tooltipPopup.hide();
                this.mPopup = null;
                forceNextChangeSignificant();
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        this.mIsShowRunnablePostDelayed = false;
        if (sPendingHandler == this) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        this.mAnchor.removeCallbacks(this.mHideRunnable);
        if (!this.mFromTouch) {
            this.mAnchor.removeCallbacks(this.mCheckHoverRunnable);
            this.mAnchor.removeOnLayoutChangeListener(this.mLayoutChangeListener);
        }
        sPosX = 0;
        sPosY = 0;
        sIsTooltipNull = false;
        sIsCustomTooltipPosition = false;
    }

    public boolean isSPenHoveringSettingsEnabled() {
        if (Settings.System.getInt(this.mAnchor.getContext().getContentResolver(), SeslSettingsReflector$SeslSystemReflector.getField_SEM_PEN_HOVERING(), 0) == 1) {
            return true;
        }
        return false;
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.mPopup != null && this.mFromTouch) {
            return false;
        }
        if (this.mAnchor == null) {
            Log.i("TooltipCompatHandler", "TooltipCompat Anchor view is null");
            return false;
        }
        Context context = view.getContext();
        if (!motionEvent.isFromSource(16386) || isSPenHoveringSettingsEnabled()) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
            if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            int action = motionEvent.getAction();
            this.mLastHoverEvent = action;
            if (action != 7) {
                if (action == 9) {
                    this.mInitialWindowFocus = this.mAnchor.hasWindowFocus();
                    if (this.mAnchor.isEnabled() && this.mPopup == null && context != null) {
                        SeslViewReflector.semSetPointerIcon(view, 2, PointerIcon.getSystemIcon(context, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_MORE()));
                    }
                } else if (action == 10) {
                    Log.i("TooltipCompatHandler", "MotionEvent.ACTION_HOVER_EXIT : hide SeslTooltipPopup");
                    if (!(!this.mAnchor.isEnabled() || this.mPopup == null || context == null)) {
                        SeslViewReflector.semSetPointerIcon(view, 2, PointerIcon.getSystemIcon(context, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()));
                    }
                    TooltipPopup tooltipPopup = this.mPopup;
                    if (tooltipPopup == null || !tooltipPopup.isShowing() || Math.abs(motionEvent.getX() - ((float) this.mAnchorX)) >= 4.0f || Math.abs(motionEvent.getY() - ((float) this.mAnchorY)) >= 4.0f) {
                        hide();
                    } else {
                        this.mIsForceExitDelay = true;
                        this.mAnchor.removeCallbacks(this.mHideRunnable);
                        this.mAnchor.postDelayed(this.mHideRunnable, 2500);
                    }
                }
            } else if (this.mAnchor.isEnabled() && this.mPopup == null && updateAnchorPos(motionEvent)) {
                this.mAnchorX = (int) motionEvent.getX();
                this.mAnchorY = (int) motionEvent.getY();
                if (!this.mIsShowRunnablePostDelayed || this.mIsForceExitDelay) {
                    setPendingHandler(this);
                    this.mIsForceExitDelay = false;
                    this.mIsShowRunnablePostDelayed = true;
                }
            }
            return false;
        }
        if (!(!this.mAnchor.isEnabled() || this.mPopup == null || context == null)) {
            SeslViewReflector.semSetPointerIcon(view, 2, PointerIcon.getSystemIcon(context, SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT()));
        }
        return false;
    }

    public boolean onLongClick(View view) {
        this.mAnchorX = view.getWidth() / 2;
        this.mAnchorY = view.getHeight() / 2;
        show(true);
        return true;
    }

    public void onViewDetachedFromWindow(View view) {
        hide();
    }

    public void show(boolean z) {
        long j2;
        long longPressTimeout;
        long j3;
        if (this.mAnchor.isAttachedToWindow()) {
            setPendingHandler((TooltipCompatHandler) null);
            TooltipCompatHandler tooltipCompatHandler = sActiveHandler;
            if (tooltipCompatHandler != null) {
                tooltipCompatHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = z;
            TooltipPopup tooltipPopup = new TooltipPopup(this.mAnchor.getContext());
            this.mPopup = tooltipPopup;
            if (sIsCustomTooltipPosition) {
                sIsForceBelow = false;
                sIsForceActionBarX = false;
                if (!sIsTooltipNull || z) {
                    tooltipPopup.showActionItemTooltip(sPosX, sPosY, sLayoutDirection, this.mTooltipText);
                    sIsCustomTooltipPosition = false;
                } else {
                    return;
                }
            } else if (!sIsTooltipNull) {
                boolean z3 = sIsForceBelow;
                if (z3 || sIsForceActionBarX) {
                    tooltipPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText, z3, sIsForceActionBarX);
                    sIsForceBelow = false;
                    sIsForceActionBarX = false;
                } else {
                    tooltipPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
                }
            } else {
                return;
            }
            Resources resources = this.mAnchor.getContext().getResources();
            this.mLastOrientation = resources.getConfiguration().orientation;
            a aVar = new a(this, resources);
            this.mLayoutChangeListener = aVar;
            this.mAnchor.addOnLayoutChangeListener(aVar);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                j2 = 2500;
            } else {
                if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
                    longPressTimeout = (long) ViewConfiguration.getLongPressTimeout();
                    j3 = Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS;
                } else {
                    longPressTimeout = (long) ViewConfiguration.getLongPressTimeout();
                    j3 = 15000;
                }
                j2 = j3 - longPressTimeout;
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
            this.mAnchor.postDelayed(this.mHideRunnable, j2);
            if (!this.mFromTouch) {
                this.mAnchor.removeCallbacks(this.mCheckHoverRunnable);
                this.mAnchor.postDelayed(this.mCheckHoverRunnable, 300);
            }
            if (this.mLastHoverEvent == 7 && !this.mAnchor.hasWindowFocus() && this.mInitialWindowFocus != this.mAnchor.hasWindowFocus()) {
                hide();
            }
        }
    }

    public void onViewAttachedToWindow(View view) {
    }
}
