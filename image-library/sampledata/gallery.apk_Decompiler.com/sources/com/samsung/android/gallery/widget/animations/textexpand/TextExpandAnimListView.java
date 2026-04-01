package com.samsung.android.gallery.widget.animations.textexpand;

import A4.L;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import e5.C0451a;
import e6.C0453a;
import g6.g;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExpandAnimListView extends RelativeLayout {
    private int mItemMarginStart;
    private final Runnable mPlayAnimRunnable = new C0451a(24, this);
    private final ArrayList<TextExpandAnimView> mVisibleList = new ArrayList<>();

    public TextExpandAnimListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setImportantForAccessibility$4(boolean z, TextExpandAnimView textExpandAnimView) {
        int i2;
        if (z) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        textExpandAnimView.setImportantForAccessibility(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateViewList$0(TextExpandAnimView textExpandAnimView) {
        textExpandAnimView.getItemAnimation().cancel();
        textExpandAnimView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateViewList$1(TextExpandAnimView textExpandAnimView) {
        textExpandAnimView.setListener(new a(this));
    }

    /* access modifiers changed from: private */
    public void playItemAnimation() {
        updateMargin(false);
        prepareItemAnimation();
        ViewUtils.setVisibility(this, 0);
        this.mVisibleList.forEach(new C0453a(19));
    }

    private void prepareItemAnimation() {
        int i2;
        TextExpandAnimView textExpandAnimView = null;
        int i7 = 0;
        while (i7 < this.mVisibleList.size()) {
            TextExpandAnimView textExpandAnimView2 = this.mVisibleList.get(i7);
            if (textExpandAnimView == null) {
                i2 = 0;
            } else {
                i2 = textExpandAnimView.getXPosition() + textExpandAnimView.getExpandWidth() + this.mItemMarginStart;
            }
            textExpandAnimView2.updateXPosition(i2);
            textExpandAnimView2.getItemAnimation().prepare(i7, this.mItemMarginStart, i2);
            i7++;
            textExpandAnimView = textExpandAnimView2;
        }
    }

    public void hide() {
        removeCallbacks(this.mPlayAnimRunnable);
        ViewUtils.setVisibility(this, 8);
        this.mVisibleList.forEach(new C0453a(20));
    }

    public void onViewTextUpdate(TextExpandAnimView textExpandAnimView) {
        int i2 = -1;
        for (int i7 = 0; i7 < this.mVisibleList.size(); i7++) {
            if (this.mVisibleList.get(i7) == textExpandAnimView) {
                i2 = i7;
            }
            if (i7 > i2 && i2 != -1) {
                int i8 = i7 - 1;
                int xPosition = this.mVisibleList.get(i8).getXPosition() + this.mVisibleList.get(i8).getExpandWidth() + this.mItemMarginStart;
                this.mVisibleList.get(i7).updateXPosition(xPosition);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVisibleList.get(i7).getLayoutParams();
                layoutParams.setMarginStart(xPosition);
                this.mVisibleList.get(i7).setLayoutParams(layoutParams);
            }
        }
    }

    public void setImportantForAccessibility(boolean z) {
        this.mVisibleList.forEach(new L(z, 26));
    }

    public void showWithAnim() {
        removeCallbacks(this.mPlayAnimRunnable);
        ViewUtils.post(this, this.mPlayAnimRunnable);
    }

    public void updateMargin(boolean z) {
        int i2 = this.mItemMarginStart;
        this.mItemMarginStart = getResources().getDimensionPixelOffset(R$dimen.ai_edit_item_margin_start);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.ai_edit_list_margin_start);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.setMarginStart(dimensionPixelOffset);
        setLayoutParams(marginLayoutParams);
        setPaddingRelative(0, 0, dimensionPixelOffset, 0);
        if (getVisibility() == 0 && !this.mVisibleList.isEmpty() && i2 != this.mItemMarginStart && z) {
            showWithAnim();
        }
    }

    public void updateViewList(ArrayList<TextExpandAnimView> arrayList) {
        this.mVisibleList.forEach(new C0453a(18));
        this.mVisibleList.clear();
        this.mVisibleList.addAll(arrayList);
        this.mVisibleList.forEach(new g(3, this));
    }
}
