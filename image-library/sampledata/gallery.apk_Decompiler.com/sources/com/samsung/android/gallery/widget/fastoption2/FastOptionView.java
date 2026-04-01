package com.samsung.android.gallery.widget.fastoption2;

import A4.C0367b;
import Ab.f;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastOptionView extends LinearLayout {
    private final ArrayList<FastOptionItemView> mFastOptionItemView = new ArrayList<>();
    private final SparseArray<FastOptionItemView> mFastOptionItemViewMap = new SparseArray<>();
    private final FastOptionItemView.ItemSelectedListener mItemSelectedInternal = new f(this, 0);
    private FastOptionItemView.ItemSelectedListener mItemSelectedListener;
    private int mMenuItemsHash;
    private FastOptionItemView mMoreBtn;
    private FastOptionMoreMenu mMoreMenu;
    private View mProgress;

    public FastOptionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void addMoreMenuButton(boolean z, boolean z3, boolean z7) {
        int i2;
        float f;
        Context context = getContext();
        if (this.mMoreMenu.getCount() > 0 && context != null) {
            Drawable drawable = context.getDrawable(R$drawable.gallery_ic_ab_more);
            FastOptionItemParams.Builder builder = FastOptionItemParams.builder();
            if (z3) {
                i2 = R$string.more;
            } else {
                i2 = R$string.more_options;
            }
            FastOptionItemView addToFastOptionView = addToFastOptionView(builder.setTitleRes(i2).setDrawable(drawable).setMenuId(-1).setShowText(z).setDisabledDim(z7).build(), new f(this, 2));
            this.mMoreBtn = addToFastOptionView;
            addToFastOptionView.updateNewBadge(this.mMoreMenu.hasNewBadgeMenu());
            this.mMoreMenu.updateAnchor(this.mMoreBtn);
            FastOptionItemView fastOptionItemView = this.mMoreBtn;
            if (z7) {
                f = 0.4f;
            } else {
                f = 1.0f;
            }
            fastOptionItemView.setAlpha(f);
            this.mMoreBtn.setClickable(!z7);
        }
    }

    private void clearProgress() {
        View view = this.mProgress;
        if (view != null) {
            ViewUtils.removeView(this, view);
            this.mProgress = null;
        }
    }

    private void init(Context context) {
        this.mMoreMenu = new FastOptionMoreMenu(context, new f(this, 1));
        setBackgroundResource(R$drawable.viewer_floating_background);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addMoreMenuButton$3(int i2, View view) {
        this.mMoreMenu.setAnchorView(this.mMoreBtn);
        this.mMoreMenu.showMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(int i2) {
        this.mItemSelectedInternal.onItemSelected(i2, this.mMoreBtn);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(int i2, View view) {
        FastOptionItemView.ItemSelectedListener itemSelectedListener = this.mItemSelectedListener;
        if (itemSelectedListener != null) {
            itemSelectedListener.onItemSelected(i2, view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateItemLayoutParams$2(FastOptionItemView fastOptionItemView) {
        fastOptionItemView.setLayoutParams(this.mFastOptionItemView.size());
    }

    private void updateVisibility() {
        if (getItemCount() <= 0) {
            setVisibility(8);
        } else if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void addItem(FastOptionItemParams.Builder builder, int i2) {
        Context context = getContext();
        if (context != null) {
            builder.setDrawable(context.getDrawable(i2));
            addItem(builder.build());
        }
    }

    public View addPostProcessing() {
        if (this.mProgress == null) {
            ViewUtils.removeAllViews(this);
            LayoutInflater.from(getContext()).inflate(R$layout.postprocessing, this, true);
            this.mProgress = findViewById(R$id.post_processing);
            if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
                this.mProgress.setLayoutParams(new LinearLayout.LayoutParams(0, getResources().getDimensionPixelSize(R$dimen.fast_menu_imageview_height), 1.0f));
                setVisibility(0);
            }
        }
        return this.mProgress;
    }

    public FastOptionItemView addToFastOptionView(FastOptionItemParams fastOptionItemParams, FastOptionItemView.ItemSelectedListener itemSelectedListener) {
        boolean z;
        FastOptionItemView fastOptionItemView = getFastOptionItemView(fastOptionItemParams, itemSelectedListener);
        this.mFastOptionItemView.add(fastOptionItemView);
        addView(fastOptionItemView);
        if (fastOptionItemParams.isDisabledDim() || fastOptionItemParams.isEnabledDim()) {
            z = true;
        } else {
            z = false;
        }
        fastOptionItemView.updateDimWithEnabled(z, fastOptionItemParams.isEnabledDim());
        fastOptionItemView.setLayoutParams(this.mFastOptionItemView.size());
        fastOptionItemView.requestLayout();
        clearProgress();
        return fastOptionItemView;
    }

    public void applyMenu(boolean z, boolean z3, boolean z7) {
        updateVisibility();
        addMoreMenuButton(z, z3, z7);
        updatePadding();
    }

    public void clear() {
        Iterator<FastOptionItemView> it = this.mFastOptionItemView.iterator();
        while (it.hasNext()) {
            ViewUtils.removeView(this, it.next());
        }
        clearProgress();
        this.mFastOptionItemView.clear();
        this.mMoreMenu.clear();
        this.mFastOptionItemViewMap.clear();
    }

    public void closeMoreMenu() {
        this.mMoreMenu.dismiss();
    }

    public void enableView(int i2, boolean z) {
        int i7;
        View view = this.mFastOptionItemViewMap.get(i2);
        if (z) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        ViewUtils.setVisibility(view, i7);
    }

    public View findFastOptionItemView(int i2) {
        return this.mFastOptionItemViewMap.get(i2);
    }

    public FastOptionItemView getFastOptionItemView(FastOptionItemParams fastOptionItemParams, FastOptionItemView.ItemSelectedListener itemSelectedListener) {
        FastOptionItemView fastOptionItemView = this.mFastOptionItemViewMap.get(fastOptionItemParams.getMenuId());
        if (fastOptionItemView != null) {
            return fastOptionItemView;
        }
        FastOptionItemView fastOptionItemView2 = new FastOptionItemView(getContext());
        fastOptionItemView2.inflateView(fastOptionItemParams.isShowText());
        fastOptionItemView2.setWidthRes(fastOptionItemParams.getWidthRes());
        fastOptionItemView2.setImage(fastOptionItemParams.getDrawable());
        fastOptionItemView2.setText(fastOptionItemParams.getTitleRes());
        fastOptionItemView2.setMenuId(fastOptionItemParams.getMenuId());
        fastOptionItemView2.setTag(Integer.valueOf(fastOptionItemParams.getMenuId()));
        fastOptionItemView2.setItemSelectedListener(itemSelectedListener);
        this.mFastOptionItemViewMap.put(fastOptionItemParams.getMenuId(), fastOptionItemView2);
        return fastOptionItemView2;
    }

    public FastOptionItemView getFastOptionMoreItem() {
        return this.mMoreBtn;
    }

    public int getItemCount() {
        int i2;
        int size = this.mFastOptionItemView.size();
        if (this.mProgress != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        return size + i2;
    }

    public boolean isSameMenus(int i2) {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            if (this.mMenuItemsHash == i2 && this.mProgress == null) {
                return true;
            }
            return false;
        } else if (this.mMenuItemsHash == i2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void setItemSelectedListener(FastOptionItemView.ItemSelectedListener itemSelectedListener) {
        this.mItemSelectedListener = itemSelectedListener;
    }

    public void setMenuHash(int i2) {
        this.mMenuItemsHash = i2;
    }

    public void showMoreMenuInClickedPosition(MotionEvent motionEvent) {
        this.mMoreMenu.showMoreMenuInClickedPosition((ViewGroup) getRootView(), motionEvent);
    }

    public void updateFastOptionItemDisabledDim(int i2, boolean z) {
        FastOptionItemView fastOptionItemView = this.mFastOptionItemViewMap.get(i2);
        if (fastOptionItemView != null) {
            fastOptionItemView.updateDimWithEnabled(z, false);
        }
    }

    public void updateItemLayoutParams() {
        if (getRootView().getWidth() > 0) {
            this.mFastOptionItemView.forEach(new C0367b(7, this));
        }
    }

    public void updateMoreMenuAdapter(ArrayList<FastOptionMenuItem> arrayList) {
        this.mMoreMenu.clear();
        this.mMoreMenu.updateAdapter(arrayList);
    }

    public void updateNewBadge() {
        FastOptionItemView fastOptionItemView;
        if (this.mMoreMenu.getCount() > 0 && (fastOptionItemView = this.mMoreBtn) != null) {
            fastOptionItemView.updateNewBadge(this.mMoreMenu.hasNewBadgeMenu());
        }
    }

    public void updatePadding() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.viewer_floating_fast_option_horizontal_padding);
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    public void addItem(FastOptionItemParams fastOptionItemParams) {
        if (fastOptionItemParams.isFastOptionMenu()) {
            addToFastOptionView(fastOptionItemParams, this.mItemSelectedInternal);
        } else {
            this.mMoreMenu.addItem(new FastOptionMenuItem(fastOptionItemParams));
        }
    }
}
