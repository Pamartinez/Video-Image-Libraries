package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.SemBlurCompat;
import androidx.core.widget.PopupWindowCompat;
import androidx.reflect.os.SeslBuildReflector$SeslVersionReflector;
import com.samsung.android.sdk.scs.base.StatusCodes;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListPopupWindow implements ShowableListMenu {
    private static final boolean ONEUI_5_1_1;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    private boolean mForceShowUpper;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsOverflowPopup;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    AppCompatPopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static int getMaxAvailableHeight(PopupWindow popupWindow, View view, int i2, boolean z) {
            return popupWindow.getMaxAvailableHeight(view, i2, z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static void setEpicenterBounds(PopupWindow popupWindow, Rect rect) {
            popupWindow.setEpicenterBounds(rect);
        }

        public static void setIsClippedToScreen(PopupWindow popupWindow, boolean z) {
            popupWindow.setIsClippedToScreen(z);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ListSelectorHider implements Runnable {
        public ListSelectorHider() {
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PopupDataSetObserver extends DataSetObserver {
        public PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PopupTouchInterceptor implements View.OnTouchListener {
        public PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            AppCompatPopupWindow appCompatPopupWindow;
            int action = motionEvent.getAction();
            int x9 = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (appCompatPopupWindow = ListPopupWindow.this.mPopup) != null && appCompatPopupWindow.isShowing() && x9 >= 0 && x9 < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.postDelayed(listPopupWindow.mResizePopupRunnable, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
                listPopupWindow2.mHandler.removeCallbacks(listPopupWindow2.mResizePopupRunnable);
                return false;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ResizePopupRunnable implements Runnable {
        public ResizePopupRunnable() {
        }

        public void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.mDropDownList;
            if (dropDownListView != null && dropDownListView.isAttachedToWindow() && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
                int childCount = ListPopupWindow.this.mDropDownList.getChildCount();
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                if (childCount <= listPopupWindow.mListItemExpandMaximum) {
                    listPopupWindow.mPopup.setInputMethodMode(2);
                    ListPopupWindow.this.show();
                }
            }
        }
    }

    static {
        boolean z;
        if (SeslBuildReflector$SeslVersionReflector.getField_SEM_PLATFORM_INT() >= 140500) {
            z = true;
        } else {
            z = false;
        }
        ONEUI_5_1_1 = z;
    }

    public ListPopupWindow(Context context) {
        this(context, (AttributeSet) null, R$attr.listPopupWindowStyle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: androidx.appcompat.widget.DropDownListView} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildDropDown() {
        /*
            r13 = this;
            androidx.appcompat.widget.DropDownListView r0 = r13.mDropDownList
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x00bb
            android.content.Context r0 = r13.mContext
            androidx.appcompat.widget.ListPopupWindow$2 r5 = new androidx.appcompat.widget.ListPopupWindow$2
            r5.<init>()
            r13.mShowDropDownRunnable = r5
            boolean r5 = r13.mModal
            r5 = r5 ^ r3
            androidx.appcompat.widget.DropDownListView r5 = r13.createDropDownListView(r0, r5)
            r13.mDropDownList = r5
            android.graphics.drawable.Drawable r6 = r13.mDropDownListHighlight
            if (r6 == 0) goto L_0x0022
            r5.setSelector(r6)
        L_0x0022:
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            android.widget.ListAdapter r6 = r13.mAdapter
            r5.setAdapter(r6)
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            android.widget.AdapterView$OnItemClickListener r6 = r13.mItemClickListener
            r5.setOnItemClickListener(r6)
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            r5.setFocusable(r3)
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            r5.setFocusableInTouchMode(r3)
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            androidx.appcompat.widget.ListPopupWindow$3 r6 = new androidx.appcompat.widget.ListPopupWindow$3
            r6.<init>()
            r5.setOnItemSelectedListener(r6)
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            androidx.appcompat.widget.ListPopupWindow$PopupScrollListener r6 = r13.mScrollListener
            r5.setOnScrollListener(r6)
            android.widget.AdapterView$OnItemSelectedListener r5 = r13.mItemSelectedListener
            if (r5 == 0) goto L_0x0054
            androidx.appcompat.widget.DropDownListView r6 = r13.mDropDownList
            r6.setOnItemSelectedListener(r5)
        L_0x0054:
            androidx.appcompat.widget.DropDownListView r5 = r13.mDropDownList
            android.view.View r6 = r13.mPromptView
            if (r6 == 0) goto L_0x00b4
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            r7.<init>(r0)
            r7.setOrientation(r3)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r8 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r2, r4, r8)
            int r8 = r13.mPromptPosition
            if (r8 == 0) goto L_0x008c
            if (r8 == r3) goto L_0x0085
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r5 = "Invalid hint position "
            r0.<init>(r5)
            int r5 = r13.mPromptPosition
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = "ListPopupWindow"
            android.util.Log.e(r5, r0)
            goto L_0x0092
        L_0x0085:
            r7.addView(r5, r0)
            r7.addView(r6)
            goto L_0x0092
        L_0x008c:
            r7.addView(r6)
            r7.addView(r5, r0)
        L_0x0092:
            int r0 = r13.mDropDownWidth
            if (r0 < 0) goto L_0x0098
            r5 = r1
            goto L_0x009a
        L_0x0098:
            r0 = r4
            r5 = r0
        L_0x009a:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r5)
            r6.measure(r0, r4)
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r5 = r6.getMeasuredHeight()
            int r6 = r0.topMargin
            int r5 = r5 + r6
            int r0 = r0.bottomMargin
            int r5 = r5 + r0
            r0 = r5
            r5 = r7
            goto L_0x00b5
        L_0x00b4:
            r0 = r4
        L_0x00b5:
            androidx.appcompat.widget.AppCompatPopupWindow r6 = r13.mPopup
            r6.setContentView(r5)
            goto L_0x00d9
        L_0x00bb:
            androidx.appcompat.widget.AppCompatPopupWindow r0 = r13.mPopup
            android.view.View r0 = r0.getContentView()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r0 = r13.mPromptView
            if (r0 == 0) goto L_0x00d8
            android.view.ViewGroup$LayoutParams r5 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r5 = (android.widget.LinearLayout.LayoutParams) r5
            int r0 = r0.getMeasuredHeight()
            int r6 = r5.topMargin
            int r0 = r0 + r6
            int r5 = r5.bottomMargin
            int r0 = r0 + r5
            goto L_0x00d9
        L_0x00d8:
            r0 = r4
        L_0x00d9:
            androidx.appcompat.widget.AppCompatPopupWindow r5 = r13.mPopup
            android.graphics.drawable.Drawable r5 = r5.getBackground()
            if (r5 == 0) goto L_0x00ee
            android.graphics.Rect r6 = r13.mTempRect
            r5.getPadding(r6)
            android.graphics.Rect r5 = r13.mTempRect
            int r6 = r5.top
            int r5 = r5.bottom
            int r6 = r6 + r5
            goto L_0x00f4
        L_0x00ee:
            android.graphics.Rect r5 = r13.mTempRect
            r5.setEmpty()
            r6 = r4
        L_0x00f4:
            androidx.appcompat.widget.AppCompatPopupWindow r5 = r13.mPopup
            int r5 = r5.getInputMethodMode()
            r7 = 2
            if (r5 != r7) goto L_0x00fe
            goto L_0x00ff
        L_0x00fe:
            r3 = r4
        L_0x00ff:
            android.view.View r4 = r13.getAnchorView()
            int r5 = r13.mDropDownVerticalOffset
            int r3 = r13.getMaxAvailableHeight(r4, r5, r3)
            boolean r4 = r13.mDropDownAlwaysVisible
            if (r4 != 0) goto L_0x0171
            int r4 = r13.mDropDownHeight
            if (r4 != r2) goto L_0x0112
            goto L_0x0171
        L_0x0112:
            int r4 = r13.mDropDownWidth
            r5 = -2
            if (r4 == r5) goto L_0x013a
            r1 = 1073741824(0x40000000, float:2.0)
            if (r4 == r2) goto L_0x0121
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r1)
        L_0x011f:
            r8 = r1
            goto L_0x0153
        L_0x0121:
            android.content.Context r2 = r13.mContext
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.graphics.Rect r4 = r13.mTempRect
            int r5 = r4.left
            int r4 = r4.right
            int r5 = r5 + r4
            int r2 = r2 - r5
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            goto L_0x011f
        L_0x013a:
            android.content.Context r2 = r13.mContext
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.graphics.Rect r4 = r13.mTempRect
            int r5 = r4.left
            int r4 = r4.right
            int r5 = r5 + r4
            int r2 = r2 - r5
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            goto L_0x011f
        L_0x0153:
            androidx.appcompat.widget.DropDownListView r7 = r13.mDropDownList
            int r11 = r3 - r0
            r12 = -1
            r9 = 0
            r10 = -1
            int r1 = r7.measureHeightOfChildrenCompat(r8, r9, r10, r11, r12)
            if (r1 <= 0) goto L_0x016f
            androidx.appcompat.widget.DropDownListView r2 = r13.mDropDownList
            int r2 = r2.getPaddingTop()
            androidx.appcompat.widget.DropDownListView r13 = r13.mDropDownList
            int r13 = r13.getPaddingBottom()
            int r13 = r13 + r2
            int r13 = r13 + r6
            int r0 = r0 + r13
        L_0x016f:
            int r1 = r1 + r0
            return r1
        L_0x0171:
            int r3 = r3 + r6
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ListPopupWindow.buildDropDown():int");
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

    private int getMaxAvailableHeight(View view, int i2, boolean z) {
        int updatePopupHeightForFoldableModel;
        int maxAvailableHeight = Api24Impl.getMaxAvailableHeight(this.mPopup, view, i2, z);
        if (ONEUI_5_1_1 || !this.mIsOverflowPopup || (updatePopupHeightForFoldableModel = updatePopupHeightForFoldableModel(view)) <= 0 || updatePopupHeightForFoldableModel >= maxAvailableHeight) {
            return maxAvailableHeight;
        }
        return updatePopupHeightForFoldableModel;
    }

    private void removePromptView() {
        View view = this.mPromptView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mPromptView);
            }
        }
    }

    private boolean setBlurEffect() {
        int i2;
        SemBlurCompat.CurveParameter curveParameter;
        View contentView = this.mPopup.getContentView();
        if (contentView == null || this.mContext == null || !this.mPopup.seslIsAvailableBlurBackground()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 35) {
            boolean isLightTheme = SeslMisc.isLightTheme(this.mContext);
            this.mContext.getResources().getColor(R$color.sesl_popup_menu_blur_background_dark, this.mContext.getTheme());
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_menu_popup_corner_radius);
            if (isLightTheme) {
                curveParameter = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.75f, 25.0f, 15.0f, 235.0f, 214.6f, 252.8f);
            } else {
                curveParameter = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.7f, -15.0f, 0.0f, 235.0f, 36.7f, 87.7f);
            }
            return SemBlurCompat.setBlurEffectPreset(contentView, 0, curveParameter, (Integer) null, Float.valueOf((float) dimensionPixelSize), 0);
        }
        if (SeslMisc.isLightTheme(this.mContext)) {
            i2 = R$color.sesl_popup_menu_blur_background;
        } else {
            i2 = R$color.sesl_popup_menu_blur_background_dark;
        }
        return SemBlurCompat.setBlurEffect(contentView, 0, this.mContext.getResources().getColor(i2, this.mContext.getTheme()), 120, (float) this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_menu_popup_corner_radius));
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        Api29Impl.setIsClippedToScreen(this.mPopup, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0123 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int updatePopupHeightForFoldableModel(android.view.View r13) {
        /*
            r12 = this;
            android.graphics.Point r0 = new android.graphics.Point
            r0.<init>()
            android.content.Context r1 = r12.mContext
            java.lang.String r2 = "display"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.hardware.display.DisplayManager r1 = (android.hardware.display.DisplayManager) r1
            r2 = -2
            java.lang.String r3 = "ListPopupWindow"
            if (r1 != 0) goto L_0x001a
            java.lang.String r12 = "displayManager is null, can not update height"
            android.util.Log.w(r3, r12)
            return r2
        L_0x001a:
            r4 = 0
            android.view.Display r1 = r1.getDisplay(r4)
            if (r1 != 0) goto L_0x0027
            java.lang.String r12 = "display is null, can not update height"
            android.util.Log.w(r3, r12)
            return r2
        L_0x0027:
            boolean r5 = androidx.reflect.view.SeslSemWindowManagerReflector.isTableMode()
            if (r5 != 0) goto L_0x002e
            return r2
        L_0x002e:
            android.content.Context r5 = r12.mContext
            android.app.Activity r5 = r12.getActivity(r5)
            if (r5 == 0) goto L_0x003d
            boolean r5 = r5.isInMultiWindowMode()
            if (r5 == 0) goto L_0x003d
            return r2
        L_0x003d:
            r5 = 2
            int[] r6 = new int[r5]
            r13.getLocationOnScreen(r6)
            r1.getRealSize(r0)
            boolean r13 = androidx.reflect.view.SeslViewRuneReflector.supportFoldableDualDisplay()
            r1 = 1
            if (r13 == 0) goto L_0x0066
            android.content.Context r13 = r12.mContext
            android.content.res.Resources r13 = r13.getResources()
            android.content.res.Configuration r13 = r13.getConfiguration()
            int r13 = r13.orientation
            if (r13 != r5) goto L_0x0085
            int r13 = r0.y
            int r7 = r0.x
            if (r13 <= r7) goto L_0x0063
            int r7 = r7 / r5
            goto L_0x0086
        L_0x0063:
            int r7 = r13 / 2
            goto L_0x0086
        L_0x0066:
            boolean r13 = androidx.reflect.view.SeslViewRuneReflector.supportFoldableNoSubDisplay()
            if (r13 == 0) goto L_0x0085
            android.content.Context r13 = r12.mContext
            android.content.res.Resources r13 = r13.getResources()
            android.content.res.Configuration r13 = r13.getConfiguration()
            int r13 = r13.orientation
            if (r13 != r1) goto L_0x0085
            int r13 = r0.y
            int r7 = r0.x
            if (r13 <= r7) goto L_0x0083
            int r13 = r13 / r5
            r7 = r13
            goto L_0x0086
        L_0x0083:
            int r7 = r7 / r5
            goto L_0x0086
        L_0x0085:
            r7 = r4
        L_0x0086:
            java.lang.String r13 = "center = "
            java.lang.String r8 = " , anchor top = "
            java.lang.StringBuilder r13 = c0.C0086a.o(r7, r13, r8)
            r8 = r6[r1]
            r13.append(r8)
            java.lang.String r13 = r13.toString()
            android.util.Log.e(r3, r13)
            if (r7 == 0) goto L_0x0123
            android.content.Context r13 = r12.mContext
            android.content.res.Resources r13 = r13.getResources()
            int r2 = androidx.appcompat.R$dimen.sesl_menu_popup_top_margin
            int r13 = r13.getDimensionPixelSize(r2)
            android.content.Context r2 = r12.mContext
            android.content.res.Resources r2 = r2.getResources()
            int r8 = androidx.appcompat.R$dimen.sesl_menu_popup_bottom_margin
            int r2 = r2.getDimensionPixelSize(r8)
            r8 = r6[r1]
            if (r7 <= r8) goto L_0x00bc
            int r7 = r7 - r8
            int r7 = r7 - r13
            int r7 = r7 - r2
            return r7
        L_0x00bc:
            android.content.Context r8 = r12.mContext
            java.lang.String r9 = "window"
            java.lang.Object r8 = r8.getSystemService(r9)
            android.view.WindowManager r8 = (android.view.WindowManager) r8
            if (r8 == 0) goto L_0x00ee
            android.view.WindowMetrics r12 = r8.getCurrentWindowMetrics()
            android.view.WindowInsets r12 = r12.getWindowInsets()
            int r4 = android.view.WindowInsets.Type.systemBars()
            android.graphics.Insets r12 = r12.getInsets(r4)
            int r4 = r12.bottom
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "systemBar insets = "
            r8.<init>(r9)
            r8.append(r12)
            java.lang.String r12 = r8.toString()
            android.util.Log.d(r3, r12)
            goto L_0x010a
        L_0x00ee:
            android.content.Context r8 = r12.mContext
            android.content.res.Resources r8 = r8.getResources()
            java.lang.String r9 = "dimen"
            java.lang.String r10 = "android"
            java.lang.String r11 = "navigation_bar_height"
            int r8 = r8.getIdentifier(r11, r9, r10)
            if (r8 <= 0) goto L_0x010a
            android.content.Context r12 = r12.mContext
            android.content.res.Resources r12 = r12.getResources()
            int r4 = r12.getDimensionPixelSize(r8)
        L_0x010a:
            java.lang.String r12 = "navigationBarHeight = "
            c0.C0086a.C(r4, r12, r3)
            r12 = r6[r1]
            int r1 = r12 - r7
            int r3 = r7 - r4
            int r3 = r3 / r5
            if (r1 <= r3) goto L_0x011c
            int r12 = r12 - r7
            int r12 = r12 - r13
            int r12 = r12 - r2
            return r12
        L_0x011c:
            int r0 = r0.y
            int r0 = r0 - r12
            int r0 = r0 - r13
            int r0 = r0 - r2
            int r0 = r0 - r4
            return r0
        L_0x0123:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ListPopupWindow.updatePopupHeightForFoldableModel(android.view.View):int");
    }

    public void clearListSelection() {
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
    }

    public DropDownListView createDropDownListView(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView((View) null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public ListView getListView() {
        return this.mDropDownList;
    }

    public Object getSelectedItem() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId() {
        if (!isShowing()) {
            return Long.MIN_VALUE;
        }
        return this.mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        if (!isShowing()) {
            return -1;
        }
        return this.mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView() {
        if (!isShowing()) {
            return null;
        }
        return this.mDropDownList.getSelectedView();
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isInputMethodNotNeeded() {
        if (this.mPopup.getInputMethodMode() == 2) {
            return true;
        }
        return false;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void seslForceShowUpper(boolean z) {
        this.mForceShowUpper = z;
    }

    public PopupWindow seslGetPopupWindow() {
        return this.mPopup;
    }

    public void seslSetAllowScrollingAnchorParent(boolean z) {
        AppCompatPopupWindow appCompatPopupWindow = this.mPopup;
        if (appCompatPopupWindow != null) {
            appCompatPopupWindow.seslSetAllowScrollingAnchorParent(z);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.mObserver;
        if (dataSetObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.mAdapter;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView = this.mDropDownList;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view) {
        this.mDropDownAnchorView = view;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i2) {
        Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            Rect rect = this.mTempRect;
            this.mDropDownWidth = rect.left + rect.right + i2;
            return;
        }
        setWidth(i2);
    }

    public void setDropDownGravity(int i2) {
        this.mDropDownGravity = i2;
    }

    public void setEpicenterBounds(Rect rect) {
        Rect rect2;
        if (rect != null) {
            rect2 = new Rect(rect);
        } else {
            rect2 = null;
        }
        this.mEpicenterBounds = rect2;
    }

    public void setHeight(int i2) {
        if (i2 >= 0 || -2 == i2 || -1 == i2) {
            this.mDropDownHeight = i2;
            return;
        }
        throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
    }

    public void setHorizontalOffset(int i2) {
        this.mDropDownHorizontalOffset = i2;
    }

    public void setInputMethodMode(int i2) {
        this.mPopup.setInputMethodMode(i2);
    }

    public void setIsOverflowPopup(boolean z) {
        this.mIsOverflowPopup = z;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.mItemSelectedListener = onItemSelectedListener;
    }

    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    public void setPromptPosition(int i2) {
        this.mPromptPosition = i2;
    }

    public void setSelection(int i2) {
        DropDownListView dropDownListView = this.mDropDownList;
        if (isShowing() && dropDownListView != null) {
            dropDownListView.setListSelectionHidden(false);
            dropDownListView.setSelection(i2);
            if (dropDownListView.getChoiceMode() != 0) {
                dropDownListView.setItemChecked(i2, true);
            }
        }
    }

    public void setVerticalOffset(int i2) {
        this.mDropDownVerticalOffset = i2;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i2) {
        this.mDropDownWidth = i2;
    }

    public void show() {
        DropDownListView dropDownListView;
        int i2;
        int i7;
        int i8;
        int i10;
        int buildDropDown = buildDropDown();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        this.mPopup.seslSetAllowScrollingAnchorParent(!isInputMethodNotNeeded);
        boolean z = false;
        if (!this.mPopup.isShowing()) {
            int i11 = this.mDropDownWidth;
            if (i11 == -1) {
                i11 = -1;
            } else if (i11 == -2) {
                i11 = getAnchorView().getWidth();
            }
            int i12 = this.mDropDownHeight;
            if (i12 == -1) {
                buildDropDown = -1;
            } else if (i12 != -2) {
                buildDropDown = i12;
            }
            if (setBlurEffect() && (dropDownListView = this.mDropDownList) != null) {
                dropDownListView.setOverScrollMode(2);
            }
            if (Settings.System.getString(this.mContext.getContentResolver(), "current_sec_active_themepackage") != null && this.mPopup.seslIsAvailableBlurBackground()) {
                Drawable background = this.mPopup.getBackground();
                if (background instanceof LayerDrawable) {
                    Drawable drawable = ((LayerDrawable) background).getDrawable(0);
                    if (drawable instanceof GradientDrawable) {
                        ((GradientDrawable) drawable).setStroke(this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_menu_popup_stroke_width), this.mContext.getResources().getColor(R$color.sesl_menu_popup_background_stroke_color, this.mContext.getTheme()));
                    }
                }
            }
            this.mPopup.setWidth(i11);
            this.mPopup.setHeight(buildDropDown);
            setPopupClipToScreenEnabled(true);
            AppCompatPopupWindow appCompatPopupWindow = this.mPopup;
            if (!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible) {
                z = true;
            }
            appCompatPopupWindow.setOutsideTouchable(z);
            this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
            if (this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
            }
            Api29Impl.setEpicenterBounds(this.mPopup, this.mEpicenterBounds);
            PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.mModal) {
                this.mHandler.post(this.mHideSelector);
            }
        } else if (getAnchorView().isAttachedToWindow()) {
            int i13 = this.mDropDownWidth;
            if (i13 == -1) {
                i13 = -1;
            } else if (i13 == -2) {
                i13 = getAnchorView().getWidth();
            }
            int i14 = this.mDropDownHeight;
            if (i14 == -1) {
                if (isInputMethodNotNeeded) {
                    i14 = buildDropDown;
                } else {
                    i14 = -1;
                }
                if (isInputMethodNotNeeded) {
                    AppCompatPopupWindow appCompatPopupWindow2 = this.mPopup;
                    if (this.mDropDownWidth == -1) {
                        i10 = -1;
                    } else {
                        i10 = 0;
                    }
                    appCompatPopupWindow2.setWidth(i10);
                    this.mPopup.setHeight(0);
                } else {
                    AppCompatPopupWindow appCompatPopupWindow3 = this.mPopup;
                    if (this.mDropDownWidth == -1) {
                        i8 = -1;
                    } else {
                        i8 = 0;
                    }
                    appCompatPopupWindow3.setWidth(i8);
                    this.mPopup.setHeight(-1);
                }
            } else if (i14 == -2) {
                i14 = buildDropDown;
            }
            AppCompatPopupWindow appCompatPopupWindow4 = this.mPopup;
            if (!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible) {
                z = true;
            }
            appCompatPopupWindow4.setOutsideTouchable(z);
            int i15 = this.mDropDownVerticalOffset;
            if (this.mForceShowUpper) {
                i15 -= buildDropDown;
                if (!this.mOverlapAnchor) {
                    i15 -= getAnchorView().getHeight();
                }
            }
            int i16 = i15;
            AppCompatPopupWindow appCompatPopupWindow5 = this.mPopup;
            View anchorView = getAnchorView();
            int i17 = this.mDropDownHorizontalOffset;
            if (i13 < 0) {
                i2 = -1;
            } else {
                i2 = i13;
            }
            if (i14 < 0) {
                i7 = -1;
            } else {
                i7 = i14;
            }
            appCompatPopupWindow5.update(anchorView, i17, i16, i2, i7);
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2, int i7) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mForceShowUpper = false;
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i2, i7);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.mDropDownVerticalOffset = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i2, i7);
        this.mPopup = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PopupScrollListener implements AbsListView.OnScrollListener {
        public PopupScrollListener() {
        }

        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.mHandler.removeCallbacks(listPopupWindow.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }

        public void onScroll(AbsListView absListView, int i2, int i7, int i8) {
        }
    }
}
