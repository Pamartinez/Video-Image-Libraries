package com.samsung.android.gallery.widget.toolbar;

import B2.C0000a;
import a6.C0419b;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$plurals;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.o3dp.lib3dphotography.i;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectInfoView extends LinearLayout {
    private final View.AccessibilityDelegate mAccessibilityDelegate;
    private boolean mCheckBoxEnabled;
    private boolean mCheckBoxVisible;
    private String mDefaultTitle;
    private boolean mIsFloatingMode;
    private boolean mIsRtl;
    private int mMaxCount;
    private OnSelectAllListener mOnSelectAllListener;
    private int mResId;
    private CheckBox mSelectAll;
    /* access modifiers changed from: private */
    public ViewGroup mSelectAllLayout;
    private LayoutTransition mSelectAllLayoutTransition;
    private TextView mSelectAllText;
    /* access modifiers changed from: private */
    public TextView mSelectCountTextView;
    /* access modifiers changed from: private */
    public ViewGroup mSelectInfoLayout;
    /* access modifiers changed from: private */
    public TextView mSelectSideTextView;
    private TextView mSelectSizeTextView;
    private View mSelectTitleLayout;
    private int mSelectedCount;
    private int mTitleTint;
    private View mView;

    public SelectInfoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    private void bindView(View view) {
        this.mSelectInfoLayout = (ViewGroup) view.findViewById(R$id.select_info_layout);
        this.mSelectAllLayout = (ViewGroup) view.findViewById(R$id.select_all_layout);
        this.mSelectAll = (CheckBox) view.findViewById(R$id.select_all);
        this.mSelectAllText = (TextView) view.findViewById(R$id.select_all_text);
        this.mSelectTitleLayout = view.findViewById(R$id.select_title_layout);
        this.mSelectCountTextView = (TextView) view.findViewById(R$id.select_count);
        this.mSelectSideTextView = (TextView) view.findViewById(R$id.select_side);
        this.mSelectSizeTextView = (TextView) view.findViewById(R$id.select_size);
        this.mIsRtl = getResources().getBoolean(R$bool.is_right_to_left);
        this.mSelectAllLayoutTransition = this.mSelectAllLayout.getLayoutTransition();
    }

    private String getDefaultTitle() {
        if (!TextUtils.isEmpty(this.mDefaultTitle)) {
            return this.mDefaultTitle;
        }
        Context context = getContext();
        int i2 = this.mResId;
        if (i2 == -1) {
            i2 = R$string.select_items;
        }
        return context.getString(i2);
    }

    private String getSelectTitleForCount(int i2, int i7) {
        if (i7 > 0) {
            if (i2 != 0 || this.mIsFloatingMode) {
                return getSelectTitleWithMax(i2, i7);
            }
            return getDefaultTitle();
        } else if (i2 == 0) {
            if (this.mIsFloatingMode) {
                return "0";
            }
            return getDefaultTitle();
        } else if (this.mIsFloatingMode) {
            return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)});
        } else {
            return getResources().getQuantityString(R$plurals.n_selected, i2, new Object[]{Integer.valueOf(i2)});
        }
    }

    private String getSelectTitleForSide(int i2, int i7) {
        if (i7 > 0) {
            return getSelectTitleWithMax(i2, i7);
        }
        if (i2 == 0) {
            return "0";
        }
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)});
    }

    private String getSelectTitleWithMax(int i2, int i7) {
        if (this.mIsRtl) {
            return String.format(Locale.getDefault(), "%d/%d", new Object[]{Integer.valueOf(i7), Integer.valueOf(i2)});
        }
        return String.format(Locale.getDefault(), "%d/%d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i7)});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view, boolean z) {
        if (ViewUtils.isVisible(this.mSelectAllLayout)) {
            this.mSelectAll.requestFocus();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCheckClick$1(View view) {
        updateContentDescription();
        view.announceForAccessibility(this.mSelectInfoLayout.getContentDescription());
    }

    private StringBuilder makeContentDescription(int i2) {
        StringBuilder sb2 = new StringBuilder();
        if (this.mSelectAll.isChecked()) {
            sb2.append(String.format(getContext().getString(R$string.speak_x_selected), new Object[]{Integer.valueOf(i2)}));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContext().getString(R$string.speak_double_tap_to_deselect_all));
            sb2.append(' ');
            sb2.append(getContext().getString(R$string.speak_checkbox));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContext().getString(R$string.speak_item_selected));
            return sb2;
        } else if (i2 <= 0) {
            sb2.append(getContext().getString(R$string.speak_nothing_selected));
            sb2.append(' ');
            sb2.append(getContext().getString(R$string.speak_double_tap_to_select_all));
            sb2.append(' ');
            sb2.append(getContext().getString(R$string.speak_checkbox));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContext().getString(R$string.speak_item_unselected));
            return sb2;
        } else {
            sb2.append(String.format(getContext().getString(R$string.speak_x_selected), new Object[]{Integer.valueOf(i2)}));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContext().getString(R$string.speak_double_tap_to_select_all));
            sb2.append(' ');
            sb2.append(getContext().getString(R$string.speak_checkbox));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(getContext().getString(R$string.speak_item_unselected));
            return sb2;
        }
    }

    /* access modifiers changed from: private */
    public void onCheckClick(View view) {
        if (this.mOnSelectAllListener == null) {
            return;
        }
        if (this.mSelectAll.isChecked()) {
            this.mOnSelectAllListener.onSelectAll();
            post(new i(13, this, view));
            return;
        }
        this.mOnSelectAllListener.onUnSelectAll();
        updateContentDescription(0);
        view.announceForAccessibility(this.mSelectInfoLayout.getContentDescription());
    }

    /* access modifiers changed from: private */
    public void onPopulateAccessibilityEventForInfoLayout(View view, AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 1) {
            ViewGroup viewGroup = this.mSelectInfoLayout;
            if (view == viewGroup && viewGroup.isAccessibilityFocused() && ViewUtils.isVisible(this.mSelectAllLayout)) {
                CheckBox checkBox = this.mSelectAll;
                checkBox.setChecked(!checkBox.isChecked());
                this.mSelectAll.callOnClick();
            }
        } else if (eventType == 32768) {
            Log.v("SelectInfoView", "Populating accessibility event");
            if (view == this.mSelectInfoLayout) {
                updateContentDescription();
            } else if (view == this.mSelectCountTextView) {
                setContentDescriptionForSelectCountView();
            } else if (view == this.mSelectSideTextView) {
                setContentDescriptionForSelectCountSideView();
            }
        }
    }

    private int parseCountFromSelectCountView() {
        String replaceAll = this.mSelectCountTextView.getText().toString().replaceAll("[^0-9]", "");
        if (!replaceAll.isEmpty()) {
            return Integer.parseInt(replaceAll);
        }
        return 0;
    }

    private void setCheckBoxColor(int i2, int i7) {
        this.mSelectAll.setButtonTintList(new ColorStateList(new int[][]{new int[]{-16842912}, new int[]{16842912}}, new int[]{i2, i7}));
    }

    private void setContentDescriptionForSelectCountSideView() {
        String[] split = this.mSelectSideTextView.getText().toString().split("/");
        if (split.length > 1) {
            this.mSelectSideTextView.setContentDescription(String.format(getContext().getString(R$string.speak_n_of_n_items_seleced), new Object[]{Integer.valueOf(split[0]), Integer.valueOf(split[1])}));
            return;
        }
        TextView textView = this.mSelectSideTextView;
        textView.setContentDescription(textView.getText().toString());
    }

    private void setContentDescriptionForSelectCountView() {
        String[] split = this.mSelectCountTextView.getText().toString().split("/");
        if (split.length > 1) {
            this.mSelectCountTextView.setContentDescription(String.format(getContext().getString(R$string.speak_n_of_n_items_seleced), new Object[]{Integer.valueOf(split[0]), Integer.valueOf(split[1])}));
            return;
        }
        TextView textView = this.mSelectCountTextView;
        textView.setContentDescription(textView.getText().toString());
    }

    private void setSelectAllLayout(int i2) {
        if (i2 == -1) {
            showSelectAll();
        } else {
            hideSelectAll();
        }
        updateSelectAllLayoutChild();
        updateSelectCountTextView();
    }

    private void setSelectTitle(int i2, int i7) {
        this.mSelectCountTextView.setText(getSelectTitleForCount(i2, i7));
        this.mSelectSideTextView.setText(getSelectTitleForSide(i2, i7));
    }

    private void updateBackgroundColor() {
        this.mView.setBackgroundResource(R$drawable.actionbar_transparent_background);
    }

    private void updateCheckBoxColor() {
        if (this.mCheckBoxVisible) {
            int i2 = this.mTitleTint;
            setCheckBoxColor(i2, i2);
            this.mSelectAllText.setTextColor(this.mTitleTint);
        }
    }

    private void updateContentDescription() {
        updateContentDescription(-1);
    }

    private void updateSelectAllLayoutChild() {
        if (ViewUtils.isVisible(this.mSelectAllLayout)) {
            ViewUtils.setVisibleOrGone(this.mSelectAllText, !this.mIsFloatingMode);
            ViewUtils.setVisibleOrGone(this.mSelectSideTextView, this.mIsFloatingMode);
            ViewUtils.setVisibleOrGone(this.mSelectTitleLayout, !this.mIsFloatingMode);
            return;
        }
        ViewUtils.setVisibleOrGone(this.mSelectTitleLayout, true);
    }

    private void updateSelectCountTextView() {
        if (!ViewUtils.isVisible(this.mSelectAllLayout) && this.mSelectTitleLayout != null) {
            ViewParent parent = getParent();
            if ((parent instanceof Toolbar) && ((Toolbar) parent).getNavigationIcon() != null) {
                ViewMarginUtils.setStartPadding(this.mSelectTitleLayout, 0);
            }
        }
    }

    public void disableLayoutTransition() {
        ViewGroup viewGroup = this.mSelectAllLayout;
        if (viewGroup != null) {
            viewGroup.setLayoutTransition((LayoutTransition) null);
        }
    }

    public void exitSelectionMode() {
        this.mSelectAll.setChecked(false);
        this.mSelectInfoLayout.setClickable(false);
        ViewGroup viewGroup = this.mSelectAllLayout;
        if (viewGroup != null) {
            viewGroup.setLayoutTransition(this.mSelectAllLayoutTransition);
        }
        hideSelectAll();
    }

    public CharSequence getSelectCountString(int i2, int i7) {
        TextView textView = this.mSelectCountTextView;
        if (textView == null || TextUtils.isEmpty(textView.getText())) {
            return "";
        }
        if (i2 == 0) {
            return getDefaultTitle();
        }
        if (i7 > 0) {
            return getSelectTitleWithMax(i2, i7);
        }
        return getResources().getQuantityString(R$plurals.n_selected, i2, new Object[]{Integer.valueOf(i2)});
    }

    public void hideSelectAll() {
        ViewGroup viewGroup;
        if (this.mCheckBoxVisible && (viewGroup = this.mSelectAllLayout) != null) {
            viewGroup.setVisibility(8);
            this.mSelectAll.setVisibility(8);
        }
    }

    public void initView() {
        if (this.mView == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R$layout.select_info_layout, this, true);
            this.mView = inflate;
            bindView(inflate);
            if (!this.mCheckBoxEnabled) {
                ViewGroup viewGroup = this.mSelectAllLayout;
                if (viewGroup != null) {
                    viewGroup.setAlpha(0.4f);
                    this.mSelectAll.setClickable(false);
                }
            } else if (this.mCheckBoxVisible) {
                this.mSelectAll.setOnClickListener(new C0419b(9, this));
            } else {
                ViewUtils.setVisibility(this.mSelectAllLayout, 8);
            }
            updateBackgroundColor();
            updateTextColor();
            this.mSelectCountTextView.setAccessibilityDelegate(this.mAccessibilityDelegate);
            this.mSelectSideTextView.setAccessibilityDelegate(this.mAccessibilityDelegate);
            this.mSelectInfoLayout.setAccessibilityDelegate(this.mAccessibilityDelegate);
            this.mSelectInfoLayout.setOnFocusChangeListener(new C0000a(6, this));
        }
    }

    public void onAppbarVisibleRatio(float f) {
        TextView textView = this.mSelectCountTextView;
        if (textView != null && textView.getVisibility() != 8) {
            float f5 = (0.66999996f - f) / 0.65999997f;
            if (Float.compare(f5, this.mSelectCountTextView.getAlpha()) != 0) {
                this.mSelectCountTextView.setAlpha(f5);
            }
        }
    }

    public void resetView() {
        ViewUtils.removeAllViews(this);
        this.mView = null;
        initView();
    }

    public void setCheckBoxEnabled(boolean z) {
        this.mCheckBoxEnabled = z;
    }

    public void setCheckBoxVisible(boolean z) {
        this.mCheckBoxVisible = z;
    }

    public void setCheckSelectAll(boolean z) {
        if (this.mCheckBoxVisible) {
            this.mSelectAll.setChecked(z);
        }
    }

    public void setFloatingMode(boolean z) {
        this.mIsFloatingMode = z;
        ViewUtils.setText(this.mSelectCountTextView, getSelectTitleForCount(this.mSelectedCount, this.mMaxCount));
        ViewUtils.setText(this.mSelectSideTextView, getSelectTitleForSide(this.mSelectedCount, this.mMaxCount));
        updateSelectAllLayoutChild();
    }

    public void setOnSelectAllListener(OnSelectAllListener onSelectAllListener) {
        this.mOnSelectAllListener = onSelectAllListener;
    }

    public void setPickerMode(String str) {
        ViewGroup viewGroup = this.mSelectAllLayout;
        if (viewGroup != null) {
            viewGroup.setVisibility(8);
        }
        setSelectedInfoTitle(str);
        this.mSelectCountTextView.setText(getDefaultTitle());
        ViewMarginUtils.setStartPadding(this.mSelectTitleLayout, 0);
    }

    public void setSelectedCountInfo(int i2, int i7, int i8) {
        setSelectedCountInfo(i2, i2, i7, i8);
    }

    public void setSelectedInfoTitle(int i2) {
        this.mResId = i2;
    }

    public void setSelectedSizeInfo(long j2) {
        this.mSelectSizeTextView.setText(StringCompat.toReadableSize((double) j2));
    }

    public void showSelectAll() {
        ViewGroup viewGroup;
        if (this.mCheckBoxVisible && (viewGroup = this.mSelectAllLayout) != null) {
            viewGroup.setVisibility(0);
            this.mSelectAll.setVisibility(0);
        }
    }

    public void updateTextColor() {
        int i2 = this.mTitleTint;
        if (i2 != 16777215) {
            this.mSelectCountTextView.setTextColor(i2);
            this.mSelectSideTextView.setTextColor(this.mTitleTint);
            this.mSelectSizeTextView.setTextColor(this.mTitleTint);
            updateCheckBoxColor();
        }
    }

    public SelectInfoView(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        boolean isTerminated;
        boolean isTerminated2;
        this.mCheckBoxEnabled = true;
        this.mCheckBoxVisible = true;
        boolean z = false;
        this.mIsFloatingMode = false;
        this.mMaxCount = 0;
        this.mResId = -1;
        this.mSelectedCount = 0;
        this.mDefaultTitle = null;
        this.mAccessibilityDelegate = new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                int i2;
                int i7;
                boolean isVisible = ViewUtils.isVisible(SelectInfoView.this.mSelectAllLayout);
                if (view == SelectInfoView.this.mSelectInfoLayout) {
                    SelectInfoView.this.mSelectInfoLayout.setClickable(isVisible);
                }
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                ViewGroup f = SelectInfoView.this.mSelectInfoLayout;
                int i8 = 2;
                if (isVisible) {
                    i2 = 1;
                } else {
                    i2 = 2;
                }
                f.setImportantForAccessibility(i2);
                TextView e = SelectInfoView.this.mSelectCountTextView;
                if (isVisible) {
                    i7 = 2;
                } else {
                    i7 = 1;
                }
                e.setImportantForAccessibility(i7);
                TextView g = SelectInfoView.this.mSelectSideTextView;
                if (!isVisible) {
                    i8 = 1;
                }
                g.setImportantForAccessibility(i8);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                SelectInfoView.this.onPopulateAccessibilityEventForInfoLayout(view, accessibilityEvent);
            }
        };
        setId(View.generateViewId());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GalleryToolbar);
            try {
                this.mTitleTint = obtainStyledAttributes.getColor(R$styleable.GalleryToolbar_titleTextColor, 16777215);
                if (obtainStyledAttributes instanceof AutoCloseable) {
                    ((AutoCloseable) obtainStyledAttributes).close();
                    return;
                } else if (obtainStyledAttributes instanceof ExecutorService) {
                    ExecutorService executorService = (ExecutorService) obtainStyledAttributes;
                    if (executorService != ForkJoinPool.commonPool() && !(isTerminated2 = executorService.isTerminated())) {
                        executorService.shutdown();
                        while (!isTerminated2) {
                            try {
                                isTerminated2 = executorService.awaitTermination(1, TimeUnit.DAYS);
                            } catch (InterruptedException unused) {
                                if (!z) {
                                    executorService.shutdownNow();
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        return;
                    }
                    return;
                } else {
                    obtainStyledAttributes.recycle();
                    return;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    private void updateContentDescription(int i2) {
        if (ViewUtils.isVisible(this.mSelectAllLayout)) {
            if (i2 < 0) {
                i2 = parseCountFromSelectCountView();
            }
            this.mSelectInfoLayout.setContentDescription(makeContentDescription(i2).toString());
        }
    }

    public void setSelectedCountInfo(int i2, int i7, int i8, int i10) {
        if (this.mSelectAllLayout != null) {
            this.mSelectedCount = i7;
            this.mMaxCount = i10;
            setCheckSelectAll(i2 != 0 && i2 == i8);
            setSelectAllLayout(i10);
            setSelectTitle(i7, i10);
        }
    }

    public void setSelectedInfoTitle(String str) {
        this.mDefaultTitle = str;
    }

    public void setSelectedCountInfo(int i2) {
        this.mMaxCount = 0;
        this.mSelectedCount = i2;
        this.mSelectAllLayout.setVisibility(8);
        setSelectTitle(i2, 0);
    }
}
