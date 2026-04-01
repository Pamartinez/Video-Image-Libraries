package androidx.appcompat.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.core.util.ObjectsCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCompatSpinner extends Spinner {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};
    private boolean isNeedToSkipRefreshDrawable;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownGravity;
    int mDropDownHorizontalOffset;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    ListPopupWindow mListPopupWindow;
    ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerFromRestore;
    ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerWhenShowing;
    private SpinnerPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api23Impl {
        public static void setDropDownViewTheme(ThemedSpinnerAdapter themedSpinnerAdapter, Resources.Theme theme) {
            if (!ObjectsCompat.equals(themedSpinnerAdapter.getDropDownViewTheme(), theme)) {
                themedSpinnerAdapter.setDropDownViewTheme(theme);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DialogPopup implements SpinnerPopup, DialogInterface.OnClickListener {
        private ListAdapter mListAdapter;
        AlertDialog mPopup;
        private CharSequence mPrompt;

        public DialogPopup() {
        }

        public void dismiss() {
            AlertDialog alertDialog = this.mPopup;
            if (alertDialog != null) {
                alertDialog.dismiss();
                this.mPopup = null;
            }
        }

        public Drawable getBackground() {
            return null;
        }

        public CharSequence getHintText() {
            return this.mPrompt;
        }

        public int getHorizontalOffset() {
            return 0;
        }

        public int getVerticalOffset() {
            return 0;
        }

        public boolean isShowing() {
            AlertDialog alertDialog = this.mPopup;
            if (alertDialog != null) {
                return alertDialog.isShowing();
            }
            return false;
        }

        public void onClick(DialogInterface dialogInterface, int i2) {
            AppCompatSpinner.this.setSelection(i2);
            if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.this.performItemClick((View) null, i2, this.mListAdapter.getItemId(i2));
            }
            dismiss();
        }

        public void setAdapter(ListAdapter listAdapter) {
            this.mListAdapter = listAdapter;
        }

        public void setBackgroundDrawable(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        public void setHorizontalOffset(int i2) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        public void setHorizontalOriginalOffset(int i2) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        public void setPromptText(CharSequence charSequence) {
            this.mPrompt = charSequence;
        }

        public void setVerticalOffset(int i2) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        public void show(int i2, int i7) {
            if (this.mListAdapter != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AppCompatSpinner.this.getPopupContext());
                CharSequence charSequence = this.mPrompt;
                if (charSequence != null) {
                    builder.setTitle(charSequence);
                }
                AlertDialog create = builder.setSingleChoiceItems(this.mListAdapter, AppCompatSpinner.this.getSelectedItemPosition(), (DialogInterface.OnClickListener) this).create();
                this.mPopup = create;
                ListView listView = create.getListView();
                listView.setTextDirection(i2);
                listView.setTextAlignment(i7);
                this.mPopup.show();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                Api23Impl.setDropDownViewTheme((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i2, view, viewGroup);
        }

        public Object getItem(int i2) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i2);
        }

        public long getItemId(int i2) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return -1;
            }
            return spinnerAdapter.getItemId(i2);
        }

        public int getItemViewType(int i2) {
            return 0;
        }

        public View getView(int i2, View view, ViewGroup viewGroup) {
            return getDropDownView(i2, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null || !spinnerAdapter.hasStableIds()) {
                return false;
            }
            return true;
        }

        public boolean isEmpty() {
            if (getCount() == 0) {
                return true;
            }
            return false;
        }

        public boolean isEnabled(int i2) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i2);
            }
            return true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DropdownPopup extends ListPopupWindow implements SpinnerPopup {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private int mOriginalHorizontalOffset;
        private final Rect mVisibleRect = new Rect();

        public DropdownPopup(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            setDropDownGravity(AppCompatSpinner.this.mDropDownGravity);
            setAnchorView(AppCompatSpinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new AdapterView.OnItemClickListener(AppCompatSpinner.this) {
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                    AppCompatSpinner.this.setSelection(i2);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        DropdownPopup dropdownPopup = DropdownPopup.this;
                        AppCompatSpinner.this.performItemClick(view, i2, dropdownPopup.mAdapter.getItemId(i2));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        public void computeContentWidth() {
            int i2;
            int i7;
            Drawable background = getBackground();
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.mTempRect);
                if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
                    i2 = AppCompatSpinner.this.mTempRect.right;
                } else {
                    i2 = -AppCompatSpinner.this.mTempRect.left;
                }
            } else {
                Rect rect = AppCompatSpinner.this.mTempRect;
                rect.right = 0;
                rect.left = 0;
                i2 = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i8 = appCompatSpinner.mDropDownWidth;
            if (i8 == -2) {
                int compatMeasureContentWidth = appCompatSpinner.compatMeasureContentWidth((SpinnerAdapter) this.mAdapter, getBackground());
                int i10 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.mTempRect;
                int i11 = (i10 - rect2.left) - rect2.right;
                if (compatMeasureContentWidth > i11) {
                    compatMeasureContentWidth = i11;
                }
                setContentWidth(Math.max(compatMeasureContentWidth + 4, (width - paddingLeft) - paddingRight));
            } else if (i8 == -1) {
                setContentWidth((width - paddingLeft) - paddingRight);
            } else {
                setContentWidth(i8);
            }
            int i12 = AppCompatSpinner.this.mDropDownHorizontalOffset;
            if (i12 == 0) {
                i12 = getHorizontalOriginalOffset();
            }
            if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
                i7 = (((i2 + width) - paddingRight) - getWidth()) - i12;
            } else {
                i7 = i2 + paddingLeft + i12;
            }
            setHorizontalOffset(i7);
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        public int getHorizontalOriginalOffset() {
            return this.mOriginalHorizontalOffset;
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        public void setHorizontalOriginalOffset(int i2) {
            this.mOriginalHorizontalOffset = i2;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        public void show(int i2, int i7) {
            boolean isShowing = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            ListView listView = getListView();
            listView.setTextDirection(i2);
            listView.setTextAlignment(i7);
            if (!isShowing) {
                listView.setChoiceMode(1);
                setSelection(AppCompatSpinner.this.getSelectedItemPosition());
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
                    if (appCompatSpinner.mOnGlobalLayoutListenerWhenShowing == null) {
                        appCompatSpinner.mOnGlobalLayoutListenerWhenShowing = new ViewTreeObserver.OnGlobalLayoutListener() {
                            public void onGlobalLayout() {
                                DropdownPopup.this.computeContentWidth();
                                DropdownPopup.super.show();
                            }
                        };
                        viewTreeObserver.addOnGlobalLayoutListener(AppCompatSpinner.this.mOnGlobalLayoutListenerWhenShowing);
                        setOnDismissListener(new PopupWindow.OnDismissListener() {
                            public void onDismiss() {
                                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                                    viewTreeObserver.removeOnGlobalLayoutListener(AppCompatSpinner.this.mOnGlobalLayoutListenerWhenShowing);
                                    AppCompatSpinner.this.mOnGlobalLayoutListenerWhenShowing = null;
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        boolean mShowDropdown;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeByte(this.mShowDropdown ? (byte) 1 : 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mShowDropdown = parcel.readByte() != 0;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SpinnerPopup {
        void dismiss();

        Drawable getBackground();

        CharSequence getHintText();

        int getHorizontalOffset();

        int getVerticalOffset();

        boolean isShowing();

        void setAdapter(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);

        void setHorizontalOffset(int i2);

        void setHorizontalOriginalOffset(int i2);

        void setPromptText(CharSequence charSequence);

        void setVerticalOffset(int i2);

        void show(int i2, int i7);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.spinnerStyle);
    }

    private int getCurrentContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        View view = spinnerAdapter.getView(getSelectedItemPosition(), (View) null, this);
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        }
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        int measuredWidth = view.getMeasuredWidth();
        if (drawable == null) {
            return measuredWidth;
        }
        drawable.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        return rect.left + rect.right + measuredWidth;
    }

    public int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i7 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i7 = Math.max(i7, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i7;
        }
        drawable.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        return rect.left + rect.right + i7;
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
    }

    public int getDropDownHorizontalOffset() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            return spinnerPopup.getHorizontalOffset();
        }
        return super.getDropDownHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            return spinnerPopup.getVerticalOffset();
        }
        return super.getDropDownVerticalOffset();
    }

    public int getDropDownWidth() {
        if (this.mPopup != null) {
            return this.mDropDownWidth;
        }
        return super.getDropDownWidth();
    }

    public final SpinnerPopup getInternalPopup() {
        return this.mPopup;
    }

    public Drawable getPopupBackground() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            return spinnerPopup.getBackground();
        }
        return super.getPopupBackground();
    }

    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public CharSequence getPrompt() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            return spinnerPopup.getHintText();
        }
        return super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.isNeedToSkipRefreshDrawable = true;
        }
    }

    public void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver;
        ViewTreeObserver viewTreeObserver2;
        super.onDetachedFromWindow();
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null && spinnerPopup.isShowing()) {
            this.mPopup.dismiss();
        }
        if (!(this.mOnGlobalLayoutListenerFromRestore == null || (viewTreeObserver2 = getViewTreeObserver()) == null || !viewTreeObserver2.isAlive())) {
            viewTreeObserver2.removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerFromRestore);
            this.mOnGlobalLayoutListenerFromRestore = null;
        }
        if (this.mOnGlobalLayoutListenerWhenShowing != null && (viewTreeObserver = getViewTreeObserver()) != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerWhenShowing);
            this.mOnGlobalLayoutListenerWhenShowing = null;
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        View selectedView = getSelectedView();
        StringBuilder sb2 = new StringBuilder();
        if (selectedView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) selectedView;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    if (sb2.length() == 0) {
                        sb2 = new StringBuilder(textView.getText());
                    } else {
                        sb2.append(" ");
                        sb2.append(textView.getText());
                    }
                }
            }
        } else if (selectedView instanceof TextView) {
            sb2 = new StringBuilder(((TextView) selectedView).getText());
        }
        accessibilityNodeInfo.setContentDescription(sb2.toString());
        accessibilityNodeInfo.setClassName(Spinner.class.getName());
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        super.onMeasure(i2, i7);
        if (this.mPopup != null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            getMeasuredWidth();
            if (getSelectedItemPosition() <= -1 || getSelectedItemPosition() >= getAdapter().getCount()) {
                i8 = compatMeasureContentWidth(getAdapter(), getBackground());
            } else {
                i8 = getCurrentContentWidth(getAdapter(), getBackground());
            }
            setMeasuredDimension(Math.min(i8, View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.mShowDropdown && (viewTreeObserver = getViewTreeObserver()) != null && this.mOnGlobalLayoutListenerFromRestore == null) {
            AnonymousClass2 r0 = new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (!AppCompatSpinner.this.getInternalPopup().isShowing()) {
                        AppCompatSpinner.this.showPopup();
                    }
                    ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                    if (viewTreeObserver != null) {
                        viewTreeObserver.removeOnGlobalLayoutListener(AppCompatSpinner.this.mOnGlobalLayoutListenerFromRestore);
                        AppCompatSpinner.this.mOnGlobalLayoutListenerFromRestore = null;
                    }
                }
            };
            this.mOnGlobalLayoutListenerFromRestore = r0;
            viewTreeObserver.addOnGlobalLayoutListener(r0);
        }
    }

    public Parcelable onSaveInstanceState() {
        boolean z;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup == null || !spinnerPopup.isShowing()) {
            z = false;
        } else {
            z = true;
        }
        savedState.mShowDropdown = z;
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener = this.mForwardingListener;
        if (forwardingListener == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        if (this.mPopup == null) {
            return super.performClick();
        }
        playSoundEffect(0);
        if (this.mPopup.isShowing()) {
            return true;
        }
        showPopup();
        return true;
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.isNeedToSkipRefreshDrawable && getStateListAnimator() != null) {
            getStateListAnimator().jumpToCurrentState();
            this.isNeedToSkipRefreshDrawable = false;
        }
    }

    public void seslDismissPopup() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null && spinnerPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public void seslSetDropDownGravity(int i2) {
        this.mDropDownGravity = i2;
        ListPopupWindow listPopupWindow = this.mListPopupWindow;
        if (listPopupWindow != null) {
            listPopupWindow.setDropDownGravity(i2);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i2);
        }
    }

    public void setDropDownHorizontalOffset(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setHorizontalOriginalOffset(i2);
            this.mPopup.setHorizontalOffset(i2);
            return;
        }
        super.setDropDownHorizontalOffset(i2);
    }

    public void setDropDownVerticalOffset(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setVerticalOffset(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    public void setDropDownWidth(int i2) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i2));
    }

    public void setPrompt(CharSequence charSequence) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintMode(mode);
        }
    }

    public void showPopup() {
        this.mPopup.show(getTextDirection(), getTextAlignment());
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.mPopup != null) {
            Context context = this.mPopupContext;
            if (context == null) {
                context = getContext();
            }
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter, context.getTheme()));
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i2, int i7) {
        this(context, attributeSet, i2, i7, (Resources.Theme) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0066, code lost:
        if (r11 != null) goto L_0x0057;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r7, android.util.AttributeSet r8, int r9, int r10, android.content.res.Resources.Theme r11) {
        /*
            r6 = this;
            r6.<init>(r7, r8, r9)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r6.mTempRect = r0
            r0 = 0
            r6.mDropDownGravity = r0
            android.content.Context r1 = r6.getContext()
            androidx.appcompat.widget.ThemeUtils.checkAppCompatTheme(r6, r1)
            int[] r1 = androidx.appcompat.R$styleable.Spinner
            androidx.appcompat.widget.TintTypedArray r1 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r7, r8, r1, r9, r0)
            androidx.appcompat.widget.AppCompatBackgroundHelper r2 = new androidx.appcompat.widget.AppCompatBackgroundHelper
            r2.<init>(r6)
            r6.mBackgroundTintHelper = r2
            if (r11 == 0) goto L_0x002b
            androidx.appcompat.view.ContextThemeWrapper r2 = new androidx.appcompat.view.ContextThemeWrapper
            r2.<init>((android.content.Context) r7, (android.content.res.Resources.Theme) r11)
            r6.mPopupContext = r2
            goto L_0x003d
        L_0x002b:
            int r11 = androidx.appcompat.R$styleable.Spinner_popupTheme
            int r11 = r1.getResourceId(r11, r0)
            if (r11 == 0) goto L_0x003b
            androidx.appcompat.view.ContextThemeWrapper r2 = new androidx.appcompat.view.ContextThemeWrapper
            r2.<init>((android.content.Context) r7, (int) r11)
            r6.mPopupContext = r2
            goto L_0x003d
        L_0x003b:
            r6.mPopupContext = r7
        L_0x003d:
            r11 = -1
            r2 = 0
            if (r10 != r11) goto L_0x006f
            int[] r11 = ATTRS_ANDROID_SPINNERMODE     // Catch:{ Exception -> 0x005d, all -> 0x005b }
            android.content.res.TypedArray r11 = r7.obtainStyledAttributes(r8, r11, r9, r0)     // Catch:{ Exception -> 0x005d, all -> 0x005b }
            boolean r3 = r11.hasValue(r0)     // Catch:{ Exception -> 0x0055 }
            if (r3 == 0) goto L_0x0057
            int r10 = r11.getInt(r0, r0)     // Catch:{ Exception -> 0x0055 }
            goto L_0x0057
        L_0x0052:
            r6 = move-exception
            r2 = r11
            goto L_0x0069
        L_0x0055:
            r3 = move-exception
            goto L_0x005f
        L_0x0057:
            r11.recycle()
            goto L_0x006f
        L_0x005b:
            r6 = move-exception
            goto L_0x0069
        L_0x005d:
            r3 = move-exception
            r11 = r2
        L_0x005f:
            java.lang.String r4 = "AppCompatSpinner"
            java.lang.String r5 = "Could not read android:spinnerMode"
            android.util.Log.i(r4, r5, r3)     // Catch:{ all -> 0x0052 }
            if (r11 == 0) goto L_0x006f
            goto L_0x0057
        L_0x0069:
            if (r2 == 0) goto L_0x006e
            r2.recycle()
        L_0x006e:
            throw r6
        L_0x006f:
            r11 = 1
            if (r10 == 0) goto L_0x00ab
            if (r10 == r11) goto L_0x0075
            goto L_0x00bb
        L_0x0075:
            androidx.appcompat.widget.AppCompatSpinner$DropdownPopup r10 = new androidx.appcompat.widget.AppCompatSpinner$DropdownPopup
            android.content.Context r3 = r6.mPopupContext
            r10.<init>(r3, r8, r9)
            android.content.Context r3 = r6.mPopupContext
            int[] r4 = androidx.appcompat.R$styleable.Spinner
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r3, r8, r4, r9, r0)
            int r3 = androidx.appcompat.R$styleable.Spinner_android_dropDownWidth
            r4 = -2
            int r3 = r0.getLayoutDimension(r3, r4)
            r6.mDropDownWidth = r3
            int r3 = r10.getHorizontalOffset()
            r6.mDropDownHorizontalOffset = r3
            int r3 = androidx.appcompat.R$styleable.Spinner_android_prompt
            java.lang.String r3 = r1.getString(r3)
            r10.setPromptText(r3)
            r0.recycle()
            r6.mPopup = r10
            r6.mListPopupWindow = r10
            androidx.appcompat.widget.AppCompatSpinner$1 r0 = new androidx.appcompat.widget.AppCompatSpinner$1
            r0.<init>(r6, r10)
            r6.mForwardingListener = r0
            goto L_0x00bb
        L_0x00ab:
            androidx.appcompat.widget.AppCompatSpinner$DialogPopup r10 = new androidx.appcompat.widget.AppCompatSpinner$DialogPopup
            r10.<init>()
            r6.mPopup = r10
            int r0 = androidx.appcompat.R$styleable.Spinner_android_prompt
            java.lang.String r0 = r1.getString(r0)
            r10.setPromptText(r0)
        L_0x00bb:
            int r10 = androidx.appcompat.R$styleable.Spinner_android_entries
            java.lang.CharSequence[] r10 = r1.getTextArray(r10)
            if (r10 == 0) goto L_0x00d3
            android.widget.ArrayAdapter r0 = new android.widget.ArrayAdapter
            r3 = 17367048(0x1090008, float:2.5162948E-38)
            r0.<init>(r7, r3, r10)
            int r7 = androidx.appcompat.R$layout.support_simple_spinner_dropdown_item
            r0.setDropDownViewResource(r7)
            r6.setAdapter((android.widget.SpinnerAdapter) r0)
        L_0x00d3:
            r1.recycle()
            r6.mPopupSet = r11
            android.widget.SpinnerAdapter r7 = r6.mTempAdapter
            if (r7 == 0) goto L_0x00e1
            r6.setAdapter((android.widget.SpinnerAdapter) r7)
            r6.mTempAdapter = r2
        L_0x00e1:
            androidx.appcompat.widget.AppCompatBackgroundHelper r6 = r6.mBackgroundTintHelper
            r6.loadFromAttributes(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }
}
