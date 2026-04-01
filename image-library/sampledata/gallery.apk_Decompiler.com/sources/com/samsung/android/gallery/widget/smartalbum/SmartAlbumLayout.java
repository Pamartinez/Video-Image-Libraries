package com.samsung.android.gallery.widget.smartalbum;

import H7.A;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.utils.AppbarInfo;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartAlbumLayout extends FrameLayout {
    protected final String TAG;
    Blackboard mBlackboard;
    boolean mClickEnabled;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    boolean mIsPickMode;
    boolean mIsTitleFontInit;
    SmartAlbumItemFactory mItemFactory;
    LinearLayout mItemsLayout;
    View mLayout;
    String mLocationKey;
    private String mScreenId;
    TextView mToolbarTitle;

    public SmartAlbumLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void bindView(View view) {
        this.mLayout = view.findViewById(R$id.main_layout);
        this.mToolbarTitle = (TextView) view.findViewById(R$id.toolbar_title);
        this.mItemsLayout = (LinearLayout) view.findViewById(R$id.items_layout);
        this.mItemFactory = createItemFactory();
        this.mCollapsingToolbarLayout = (CollapsingToolbarLayout) getRootView().findViewById(R$id.toolbar_layout);
    }

    private int getStartEndMinMargin() {
        return getResources().getDimensionPixelOffset(R$dimen.smart_album_layout_start_end_min_margin);
    }

    private int getVisibleItemCount() {
        int i2 = 0;
        for (int i7 = 0; i7 < this.mItemsLayout.getChildCount(); i7++) {
            if ((this.mItemsLayout.getChildAt(i7) instanceof SmartAlbumItem) && this.mItemsLayout.getChildAt(i7).getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    private boolean isAlbumMoveMode() {
        if (this.mBlackboard.read("data://album_move") != null) {
            return true;
        }
        return false;
    }

    private boolean isSmartAlbumVisible() {
        if (this.mItemsLayout.getVisibility() != 8) {
            return true;
        }
        return false;
    }

    private boolean isTablet() {
        return Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    }

    private void setClickEnabledInPickMode() {
        float f;
        this.mItemsLayout.setAlpha(1.0f);
        this.mClickEnabled = true;
        for (int i2 = 0; i2 < this.mItemsLayout.getChildCount(); i2++) {
            if (this.mItemsLayout.getChildAt(i2) instanceof SmartAlbumItem) {
                if (((SmartAlbumItem) this.mItemsLayout.getChildAt(i2)).isVisibleInPickMode()) {
                    boolean isEnableInPickMode = ((SmartAlbumItem) this.mItemsLayout.getChildAt(i2)).isEnableInPickMode();
                    this.mItemsLayout.getChildAt(i2).setFocusable(isEnableInPickMode);
                    View childAt = this.mItemsLayout.getChildAt(i2);
                    if (isEnableInPickMode) {
                        f = 1.0f;
                    } else {
                        f = 0.37f;
                    }
                    childAt.setAlpha(f);
                    this.mItemsLayout.getChildAt(i2).setEnabled(isEnableInPickMode);
                } else {
                    this.mItemsLayout.getChildAt(i2).setVisibility(8);
                }
            }
        }
    }

    private void updateSmartAlbumItemLayout(int i2, boolean z, boolean z3) {
        for (int i7 = 0; i7 < this.mItemsLayout.getChildCount(); i7++) {
            View childAt = this.mItemsLayout.getChildAt(i7);
            if (childAt instanceof SmartAlbumItem) {
                ((SmartAlbumItem) childAt).updateLayout(i2, z, z3);
            }
        }
    }

    public SmartAlbumItemFactory createItemFactory() {
        return new SmartAlbumItemFactory();
    }

    public int getLayoutId() {
        return R$layout.smart_album_layout;
    }

    public int getOneItemMinWidth() {
        int i2;
        Resources resources = getResources();
        if (isTablet()) {
            i2 = R$dimen.smart_album_item_min_width_tablet;
        } else {
            i2 = R$dimen.smart_album_item_min_width;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    public String getScreenId() {
        return this.mScreenId;
    }

    public int getStartEndMargin() {
        return getResources().getDimensionPixelOffset(R$dimen.smart_album_layout_start_end_margin);
    }

    public CharSequence getTitleString() {
        TextView textView = this.mToolbarTitle;
        if (textView != null && textView.getVisibility() != 8) {
            return this.mToolbarTitle.getText();
        }
        try {
            return this.mCollapsingToolbarLayout.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean hasEnoughSpace() {
        if (ResourceCompat.getWindowWidth(getContext()) >= (getOneItemMinWidth() * this.mItemsLayout.getChildCount()) + (getStartEndMargin() * 2)) {
            return true;
        }
        return false;
    }

    public boolean isSmartAlbumClickEnabled() {
        return this.mClickEnabled;
    }

    public boolean isSmartAlbumTitleAvailable() {
        if (!AppbarInfo.isAppbarSpaceEnough(getContext()) || this.mIsPickMode) {
            return false;
        }
        return true;
    }

    public boolean isSmartAlbumTitleEnabled() {
        TextView textView = this.mToolbarTitle;
        if (textView == null || textView.getVisibility() == 8) {
            return false;
        }
        return true;
    }

    public boolean needCenterAlign() {
        if (isTablet() || !hasEnoughSpace()) {
            return true;
        }
        return false;
    }

    public void onItemClick(View view) {
        if ((view instanceof SmartAlbumItem) && view.isEnabled() && getAlpha() > 0.0f) {
            SmartAlbumItem smartAlbumItem = (SmartAlbumItem) view;
            smartAlbumItem.handleOnClick(this.mIsPickMode);
            AnalyticsLogger.getInstance().postLog(getScreenId(), smartAlbumItem.getAnalyticsId());
        }
    }

    public void restoreState(SmartAlbumLayout smartAlbumLayout) {
        if (isSmartAlbumTitleAvailable()) {
            if (!smartAlbumLayout.isSmartAlbumVisible()) {
                TextView textView = this.mToolbarTitle;
                if (textView != null) {
                    textView.setText(this.mCollapsingToolbarLayout.getTitle());
                    this.mCollapsingToolbarLayout.setTitle("");
                    FontUtils.resizeUpToExtraLarge(getContext(), this.mToolbarTitle);
                }
                setClickEnabled(false);
            } else if (smartAlbumLayout.isSmartAlbumTitleEnabled()) {
                this.mToolbarTitle.setText(smartAlbumLayout.getTitleString());
                FontUtils.resizeUpToExtraLarge(getContext(), this.mToolbarTitle);
                setClickEnabled(smartAlbumLayout.isSmartAlbumClickEnabled());
            } else {
                updateTitle();
            }
        } else if (!smartAlbumLayout.isSmartAlbumVisible()) {
            updateSmartAlbumVisible(false);
        } else if (smartAlbumLayout.isSmartAlbumClickEnabled()) {
            ViewUtils.setVisibility(this.mToolbarTitle, 8);
        } else if (isAlbumMoveMode()) {
            setClickEnabled(smartAlbumLayout.isSmartAlbumClickEnabled());
        } else {
            updateSmartAlbumVisible(false);
            this.mCollapsingToolbarLayout.setTitle(smartAlbumLayout.getTitleString());
        }
    }

    public void setBlackboard(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        for (int i2 = 0; i2 < this.mItemsLayout.getChildCount(); i2++) {
            View childAt = this.mItemsLayout.getChildAt(i2);
            if (childAt instanceof SmartAlbumItem) {
                ((SmartAlbumItem) childAt).setBlackboard(blackboard);
            }
        }
    }

    public void setClickEnabled(boolean z) {
        float f;
        LinearLayout linearLayout = this.mItemsLayout;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.37f;
        }
        linearLayout.setAlpha(f);
        this.mClickEnabled = z;
        for (int i2 = 0; i2 < this.mItemsLayout.getChildCount(); i2++) {
            if (this.mItemsLayout.getChildAt(i2) instanceof SmartAlbumItem) {
                this.mItemsLayout.getChildAt(i2).setFocusable(z);
                this.mItemsLayout.getChildAt(i2).setEnabled(z);
            }
        }
    }

    public void setImportantForAccessibility(int i2) {
        super.setImportantForAccessibility(i2);
        for (int i7 = 0; i7 < this.mItemsLayout.getChildCount(); i7++) {
            if (this.mItemsLayout.getChildAt(i7) instanceof SmartAlbumItem) {
                this.mItemsLayout.getChildAt(i7).setImportantForAccessibility(i2);
            }
        }
    }

    public void setTitleVisibility(int i2) {
        TextView textView = this.mToolbarTitle;
        if (textView != null && textView.getVisibility() != i2) {
            this.mToolbarTitle.setVisibility(i2);
        }
    }

    public void updateItemsDetails() {
        for (int i2 = 0; i2 < this.mItemsLayout.getChildCount(); i2++) {
            View childAt = this.mItemsLayout.getChildAt(i2);
            if (childAt instanceof SmartAlbumItem) {
                ((SmartAlbumItem) childAt).updateDetails();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateItemsLayout() {
        /*
            r11 = this;
            android.content.Context r0 = r11.getContext()
            int r0 = com.samsung.android.gallery.support.utils.ResourceCompat.getWindowWidth(r0)
            android.widget.LinearLayout r1 = r11.mItemsLayout
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r1 = (android.widget.LinearLayout.LayoutParams) r1
            int r2 = r11.getStartEndMargin()
            int r3 = r11.getStartEndMinMargin()
            int r4 = r11.getOneItemMinWidth()
            boolean r5 = r11.needCenterAlign()
            r6 = 0
            if (r5 == 0) goto L_0x0039
            int r7 = r11.getVisibleItemCount()
            if (r7 <= 0) goto L_0x0039
            int r8 = r4 * r7
            int r9 = r3 * 2
            int r8 = r8 + r9
            if (r8 <= r0) goto L_0x0039
            int r0 = r0 - r9
            int r0 = r0 / r7
            if (r4 <= r0) goto L_0x0039
            r4 = 1
            r10 = r4
            r4 = r0
            r0 = r10
            goto L_0x003a
        L_0x0039:
            r0 = r6
        L_0x003a:
            r11.updateSmartAlbumItemLayout(r4, r0, r5)
            if (r5 == 0) goto L_0x0041
            r0 = r3
            goto L_0x0042
        L_0x0041:
            r0 = r2
        L_0x0042:
            r1.setMarginStart(r0)
            if (r5 == 0) goto L_0x0048
            r2 = r3
        L_0x0048:
            r1.setMarginEnd(r2)
            boolean r0 = r11.mIsPickMode
            if (r0 == 0) goto L_0x0051
            r1.topMargin = r6
        L_0x0051:
            android.widget.LinearLayout r11 = r11.mItemsLayout
            r11.setLayoutParams(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.smartalbum.SmartAlbumLayout.updateItemsLayout():void");
    }

    public void updateSelectionCountTitle(CharSequence charSequence) {
        TextView textView = this.mToolbarTitle;
        if (textView != null) {
            textView.setVisibility(0);
            this.mToolbarTitle.setText(charSequence);
        }
    }

    public void updateSmartAlbumVisible(boolean z) {
        int i2;
        LinearLayout linearLayout = this.mItemsLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    public void updateTitle() {
        TextView textView = this.mToolbarTitle;
        if (textView != null) {
            textView.setVisibility(0);
            if ("location://albums".equals(this.mLocationKey)) {
                this.mToolbarTitle.setText(R$string.tab_tag_albums);
            } else {
                this.mToolbarTitle.setText(R$string.pictures);
            }
            if (!this.mIsTitleFontInit) {
                FontUtils.resizeUpToExtraLarge(getContext(), this.mToolbarTitle);
                this.mIsTitleFontInit = true;
            }
        }
    }

    public void updateView(Blackboard blackboard, String str, String str2) {
        this.mItemFactory.createItems(this.mItemsLayout, new A(29, this));
        setBlackboard(blackboard);
        this.mLocationKey = str;
        this.mScreenId = str2;
        if (isSmartAlbumTitleAvailable()) {
            updateTitle();
        }
        updateItemsLayout();
        updateItemsDetails();
    }

    public void updateViewInPickMode(Blackboard blackboard, String str, String str2) {
        this.mIsPickMode = true;
        this.mItemFactory.createItems(this.mItemsLayout, new A(29, this));
        setBlackboard(blackboard);
        this.mLocationKey = str;
        this.mScreenId = str2;
        ViewUtils.setVisibility(this.mToolbarTitle, 8);
        setClickEnabledInPickMode();
        View view = this.mLayout;
        view.setPadding(view.getPaddingStart(), this.mLayout.getPaddingTop(), this.mLayout.getPaddingRight(), 0);
        updateItemsLayout();
    }

    public SmartAlbumLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = getClass().getSimpleName();
        this.mClickEnabled = true;
        this.mIsPickMode = false;
        this.mIsTitleFontInit = false;
        bindView(LayoutInflater.from(getContext()).inflate(getLayoutId(), this, true));
    }
}
