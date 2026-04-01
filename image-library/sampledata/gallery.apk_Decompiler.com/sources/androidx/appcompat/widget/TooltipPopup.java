package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TooltipPopup {
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private int mNavigationBarHeight = 0;
    private final int[] mTmpAnchorPos = new int[2];
    private final int[] mTmpAppPos = new int[2];
    private final Rect mTmpDisplayFrame = new Rect();

    public TooltipPopup(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843945, typedValue, false);
        if (typedValue.data != 0) {
            this.mContext = new ContextThemeWrapper(context, typedValue.data);
        } else {
            this.mContext = context;
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(R$layout.sesl_tooltip, (ViewGroup) null);
        this.mContentView = inflate;
        this.mMessageView = (TextView) inflate.findViewById(R$id.message);
        inflate.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    TooltipPopup.this.hide();
                    return true;
                } else if (action != 4) {
                    return false;
                } else {
                    TooltipPopup.this.hide();
                    return false;
                }
            }
        });
        layoutParams.setTitle(getClass().getSimpleName());
        layoutParams.packageName = this.mContext.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 262152;
    }

    private int adjustTooltipPosition(int i2, int i7, int i8) {
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (checkNaviBarForLandscape()) {
            if (rotation == 1) {
                int width = (((this.mTmpDisplayFrame.width() - i7) - getNavigationBarHeight()) / 2) - i8;
                if (i2 > width) {
                    return width - i8;
                }
            } else if (rotation == 3) {
                if (i2 <= 0) {
                    int width2 = ((i7 - this.mTmpDisplayFrame.width()) / 2) + i8;
                    if (i2 <= width2) {
                        return width2 + i8;
                    }
                } else {
                    int width3 = ((this.mTmpDisplayFrame.width() - i7) / 2) + i8;
                    if (i2 > width3) {
                        return width3 - i8;
                    }
                }
            }
        } else if (rotation == 1 || rotation == 3) {
            if (i2 <= 0) {
                int width4 = ((i7 - this.mTmpDisplayFrame.width()) / 2) + i8;
                if (i2 < width4) {
                    return width4 + i8;
                }
            } else {
                int width5 = ((this.mTmpDisplayFrame.width() - i7) / 2) + i8;
                if (i2 > width5) {
                    return width5 - i8;
                }
            }
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002b, code lost:
        r5 = r2.right;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkNaviBarForLandscape() {
        /*
            r7 = this;
            android.content.Context r0 = r7.mContext
            android.content.res.Resources r1 = r0.getResources()
            android.graphics.Rect r2 = r7.mTmpDisplayFrame
            android.graphics.Point r3 = new android.graphics.Point
            r3.<init>()
            java.lang.String r4 = "window"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getRealSize(r3)
            int r0 = r0.getRotation()
            int r4 = androidx.appcompat.R$dimen.sesl_navigation_bar_height
            float r1 = r1.getDimension(r4)
            int r1 = (int) r1
            r4 = 1
            if (r0 != r4) goto L_0x0038
            int r5 = r2.right
            int r6 = r5 + r1
            int r3 = r3.x
            if (r6 < r3) goto L_0x0038
            int r3 = r3 - r5
            r7.setNavigationBarHeight(r3)
            return r4
        L_0x0038:
            r3 = 3
            if (r0 != r3) goto L_0x0043
            int r0 = r2.left
            if (r0 > r1) goto L_0x0043
            r7.setNavigationBarHeight(r0)
            return r4
        L_0x0043:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.TooltipPopup.checkNaviBarForLandscape():boolean");
    }

    private void computePosition(View view, int i2, int i7, boolean z, WindowManager.LayoutParams layoutParams, boolean z3, boolean z7) {
        int i8;
        int i10;
        WindowManager.LayoutParams layoutParams2 = layoutParams;
        layoutParams2.token = view.getApplicationWindowToken();
        int width = view.getWidth() / 2;
        layoutParams2.gravity = 49;
        View appRootView = getAppRootView(view);
        if (appRootView == null) {
            Log.e("SESL_TooltipPopup", "Cannot find app view");
            return;
        }
        appRootView.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        Rect rect = this.mTmpDisplayFrame;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.mContext.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier != 0) {
                i10 = resources.getDimensionPixelSize(identifier);
            } else {
                i10 = 0;
            }
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, i10, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        int[] iArr = new int[2];
        appRootView.getLocationOnScreen(iArr);
        int i11 = iArr[0];
        Rect rect2 = new Rect(i11, iArr[1], appRootView.getWidth() + i11, appRootView.getHeight() + iArr[1]);
        Rect rect3 = this.mTmpDisplayFrame;
        rect3.left = rect2.left;
        rect3.right = rect2.right;
        appRootView.getLocationOnScreen(this.mTmpAppPos);
        View view2 = view;
        view2.getLocationOnScreen(this.mTmpAnchorPos);
        Log.i("SESL_TooltipPopup", "computePosition - displayFrame left : " + this.mTmpDisplayFrame.left);
        Log.i("SESL_TooltipPopup", "computePosition - displayFrame right : " + this.mTmpDisplayFrame.right);
        Log.i("SESL_TooltipPopup", "computePosition - displayFrame top : " + this.mTmpDisplayFrame.top);
        Log.i("SESL_TooltipPopup", "computePosition - displayFrame bottom : " + this.mTmpDisplayFrame.bottom);
        Log.i("SESL_TooltipPopup", "computePosition - anchorView locationOnScreen x: " + this.mTmpAnchorPos[0]);
        Log.i("SESL_TooltipPopup", "computePosition - anchorView locationOnScreen y : " + this.mTmpAnchorPos[1]);
        Log.i("SESL_TooltipPopup", "computePosition - appView locationOnScreen x : " + this.mTmpAppPos[0]);
        Log.i("SESL_TooltipPopup", "computePosition - appView locationOnScreen y : " + this.mTmpAppPos[1]);
        int[] iArr2 = this.mTmpAnchorPos;
        int i12 = iArr2[0];
        int[] iArr3 = this.mTmpAppPos;
        int i13 = i12 - iArr3[0];
        iArr2[0] = i13;
        iArr2[1] = iArr2[1] - iArr3[1];
        layoutParams2.x = (i13 + width) - (this.mTmpDisplayFrame.width() / 2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredHeight = this.mContentView.getMeasuredHeight();
        int measuredWidth = this.mContentView.getMeasuredWidth();
        int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R$dimen.sesl_hover_tooltip_popup_right_margin);
        int dimensionPixelOffset2 = this.mContext.getResources().getDimensionPixelOffset(R$dimen.sesl_hover_tooltip_popup_area_margin);
        int i14 = this.mTmpAnchorPos[1];
        int i15 = i14 - measuredHeight;
        int height = view2.getHeight() + i14;
        if (z) {
            if (ViewCompat.getLayoutDirection(view2) == 0) {
                int i16 = measuredWidth / 2;
                int width2 = (((view2.getWidth() + this.mTmpAnchorPos[0]) - (this.mTmpDisplayFrame.width() / 2)) - i16) - dimensionPixelOffset;
                layoutParams2.x = width2;
                if (width2 < ((-this.mTmpDisplayFrame.width()) / 2) + i16) {
                    layoutParams2.x = ((-this.mTmpDisplayFrame.width()) / 2) + i16 + dimensionPixelOffset;
                }
                layoutParams2.x = adjustTooltipPosition(layoutParams2.x, measuredWidth, dimensionPixelOffset);
            } else {
                int width3 = (measuredWidth / 2) + ((this.mTmpAnchorPos[0] + width) - (this.mTmpDisplayFrame.width() / 2)) + dimensionPixelOffset;
                layoutParams2.x = width3;
                layoutParams2.x = adjustTooltipPosition(width3, measuredWidth, dimensionPixelOffset);
            }
            if (height + measuredHeight > this.mTmpDisplayFrame.height()) {
                layoutParams2.y = i15;
            } else {
                layoutParams2.y = height;
            }
        } else {
            int width4 = (this.mTmpAnchorPos[0] + width) - (this.mTmpDisplayFrame.width() / 2);
            layoutParams2.x = width4;
            int i17 = measuredWidth / 2;
            if (width4 < ((-this.mTmpDisplayFrame.width()) / 2) + i17) {
                layoutParams2.x = ((-this.mTmpDisplayFrame.width()) / 2) + i17 + dimensionPixelOffset2;
            }
            layoutParams2.x = adjustTooltipPosition(layoutParams2.x, measuredWidth, dimensionPixelOffset);
            if (i15 >= 0) {
                i8 = i15;
            } else {
                i8 = height;
            }
            layoutParams2.y = i8;
        }
        if (z3) {
            layoutParams2.y = view2.getHeight() + this.mTmpAnchorPos[1];
        }
        if (z7) {
            if (ViewCompat.getLayoutDirection(view2) == 0) {
                int i18 = measuredWidth / 2;
                int width5 = (((view2.getWidth() + this.mTmpAnchorPos[0]) - (this.mTmpDisplayFrame.width() / 2)) - i18) - dimensionPixelOffset;
                layoutParams2.x = width5;
                if (width5 < ((-this.mTmpDisplayFrame.width()) / 2) + i18) {
                    layoutParams2.x = ((-this.mTmpDisplayFrame.width()) / 2) + i18 + dimensionPixelOffset2;
                }
                layoutParams2.x = adjustTooltipPosition(layoutParams2.x, measuredWidth, dimensionPixelOffset);
            } else {
                int width6 = ((measuredWidth / 2) + ((this.mTmpAnchorPos[0] + width) - (this.mTmpDisplayFrame.width() / 2))) - dimensionPixelOffset;
                layoutParams2.x = width6;
                layoutParams2.x = adjustTooltipPosition(width6, measuredWidth, dimensionPixelOffset);
            }
            if (measuredHeight + height <= this.mTmpDisplayFrame.height()) {
                i15 = height;
            }
            layoutParams2.y = i15;
        }
    }

    private static View getAppRootView(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if (!(layoutParams instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams).type != 2) {
            for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
                if (context instanceof Activity) {
                    return ((Activity) context).getWindow().getDecorView();
                }
            }
        }
        return rootView;
    }

    private int getNavigationBarHeight() {
        return this.mNavigationBarHeight;
    }

    private void setNavigationBarHeight(int i2) {
        this.mNavigationBarHeight = i2;
    }

    public void hide() {
        if (isShowing()) {
            ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
        }
    }

    public boolean isShowing() {
        if (this.mContentView.getParent() != null) {
            return true;
        }
        return false;
    }

    public void show(View view, int i2, int i7, boolean z, CharSequence charSequence) {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        computePosition(view, i2, i7, z, this.mLayoutParams, false, false);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }

    public void showActionItemTooltip(int i2, int i7, int i8, CharSequence charSequence) {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.x = i2;
        layoutParams.y = i7;
        if (i8 == 0) {
            layoutParams.gravity = 8388661;
        } else {
            layoutParams.gravity = 8388659;
        }
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }

    public void show(View view, int i2, int i7, boolean z, CharSequence charSequence, boolean z3, boolean z7) {
        if (isShowing()) {
            hide();
        }
        this.mMessageView.setText(charSequence);
        computePosition(view, i2, i7, z, this.mLayoutParams, z3, z7);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }
}
