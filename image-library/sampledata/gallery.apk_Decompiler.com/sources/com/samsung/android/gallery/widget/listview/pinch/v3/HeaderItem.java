package com.samsung.android.gallery.widget.listview.pinch.v3;

import A4.Y;
import Fa.C0571z;
import Fb.h;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HeaderItem extends PinchItem {
    private Integer mFromHeight;
    private int mMinLines;
    private int mToHeight;

    private void calculateHeightDelta(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo) {
        TextView headerDescriptionView = pinchLayoutManager.getHeaderDescriptionView();
        if (headerDescriptionView != null) {
            int maxLines = headerDescriptionView.getMaxLines();
            TextUtils.TruncateAt ellipsize = headerDescriptionView.getEllipsize();
            int headerDescriptionWidthOffset = pinchLayoutManager.getHeaderDescriptionWidthOffset();
            StaticLayout staticLayout = getStaticLayout(headerDescriptionView, pinchLayoutManager.getHeaderWidth(gridInfo.from()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            StaticLayout staticLayout2 = getStaticLayout(headerDescriptionView, pinchLayoutManager.getHeaderWidth(gridInfo.to()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            if (staticLayout == null || staticLayout2 == null) {
                this.mFromHeight = -1;
                return;
            }
            this.mMinLines = Math.min(staticLayout.getLineCount(), staticLayout2.getLineCount());
            this.mFromHeight = Integer.valueOf(staticLayout.getHeight());
            this.mToHeight = staticLayout2.getHeight();
            return;
        }
        this.mFromHeight = -1;
    }

    private PropertyAnimator getAlphaAnimator() {
        return new AlphaAnimator(this.mListViewHolder.getRootView(), 1.0f, 0.0f).setDurationRelative(0.05f).setAnimationListener(new h(27, this));
    }

    private PropertyAnimator getHeightAnimator(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo) {
        if (this.mFromHeight == null) {
            calculateHeightDelta(pinchLayoutManager, gridInfo);
        }
        TextView headerDescriptionView = pinchLayoutManager.getHeaderDescriptionView();
        if (headerDescriptionView == null || this.mFromHeight.intValue() < 0) {
            return null;
        }
        int maxLines = headerDescriptionView.getMaxLines();
        TextUtils.TruncateAt ellipsize = headerDescriptionView.getEllipsize();
        headerDescriptionView.setMaxLines(this.mMinLines);
        headerDescriptionView.setEllipsize(TextUtils.TruncateAt.END);
        return new HeightAnimator(headerDescriptionView, this.mFromHeight.intValue(), this.mToHeight).setAnimationListener(new Y(maxLines, (Serializable) ellipsize, 3));
    }

    private float getLeft(int i2) {
        RectF rect = getRect(i2);
        if (rect != null) {
            return rect.left;
        }
        return 0.0f;
    }

    private StaticLayout getStaticLayout(TextView textView, int i2, int i7, TextUtils.TruncateAt truncateAt) {
        int i8;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        int i10 = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        CharSequence text = textView.getText();
        if (i2 <= 0 || (i8 = i2 - i10) <= 0 || text == null || text.length() == 0) {
            return null;
        }
        StaticLayout.Builder includePad = StaticLayout.Builder.obtain(text, 0, text.length(), textView.getPaint(), i8).setIncludePad(textView.getIncludeFontPadding());
        if (i7 > 0) {
            includePad.setMaxLines(i7);
        }
        if (truncateAt != null) {
            includePad.setEllipsize(truncateAt);
        }
        return includePad.build();
    }

    private float getTop(int i2) {
        RectF rect = getRect(i2);
        if (rect != null) {
            return rect.top;
        }
        return (float) (-this.mListViewHolder.itemView.getHeight());
    }

    private PropertyAnimator getWidthAnimator(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo, boolean z) {
        int i2;
        int i7;
        if (z) {
            i2 = gridInfo.to();
        } else {
            i2 = gridInfo.from();
        }
        int headerWidth = pinchLayoutManager.getHeaderWidth(i2);
        if (z) {
            i7 = gridInfo.from();
        } else {
            i7 = gridInfo.to();
        }
        int headerWidth2 = pinchLayoutManager.getHeaderWidth(i7);
        if (headerWidth != headerWidth2) {
            return new WidthAnimator(this.mListViewHolder.getRootView(), headerWidth, headerWidth2);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getHeightAnimator$0(int i2, TextUtils.TruncateAt truncateAt, View view) {
        TextView textView = (TextView) view;
        textView.getLayoutParams().height = -2;
        textView.setMaxLines(i2);
        textView.setEllipsize(truncateAt);
    }

    private boolean supportHeaderAnimation(GridInfo gridInfo) {
        if (gridInfo.from() == gridInfo.to() || gridInfo.withYear()) {
            return false;
        }
        return true;
    }

    public ArrayList<PropertyAnimator> getAnimators(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager, int i2, boolean z, Runnable runnable) {
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        if (supportHeaderAnimation(gridInfo)) {
            arrayList.add(getTranslateAnimator(gridInfo, i2));
            arrayList.add(getWidthAnimator(pinchLayoutManager, gridInfo, false));
            arrayList.add(getHeightAnimator(pinchLayoutManager, gridInfo));
        } else {
            arrayList.add(getAlphaAnimator());
        }
        arrayList.removeIf(new C0571z(21));
        return arrayList;
    }

    public int getHeightDelta(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo) {
        if (this.mFromHeight == null) {
            calculateHeightDelta(pinchLayoutManager, gridInfo);
        }
        if (this.mFromHeight.intValue() < 0) {
            return 0;
        }
        return this.mToHeight - this.mFromHeight.intValue();
    }

    public TranslationAnimator getTranslateAnimator(GridInfo gridInfo, int i2) {
        return new TranslationAnimator(this.mListViewHolder.getRootView(), new RectF(getLeft(gridInfo.from()), getTop(gridInfo.from()), 0.0f, 0.0f), new RectF(getLeft(gridInfo.to()), getTop(gridInfo.to()), 0.0f, 0.0f));
    }

    public boolean isData() {
        return false;
    }

    public boolean isHeader() {
        return true;
    }

    public boolean isSameGroup(int i2, int i7, SparseArray<Integer> sparseArray) {
        for (int i8 = 0; i8 < this.mPinchInfoOnGrid.size(); i8++) {
            if (getViewPosition(this.mPinchInfoOnGrid.keyAt(i8)) == i2) {
                return true;
            }
        }
        return false;
    }

    public void onAnimationCompleted() {
        super.onAnimationCompleted();
        ViewUtils.resize(this.mListViewHolder.getRootView(), -1, -2);
        resetAlpha(this.mListViewHolder.getRootView());
    }

    public ArrayList<PropertyAnimator> getAnimators(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo, float f, int i2, float f5, boolean z) {
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        arrayList.add(getTranslateAnimator(gridInfo, f, 0, z));
        arrayList.add(getWidthAnimator(pinchLayoutManager, gridInfo, z));
        arrayList.add(getHeightAnimator(pinchLayoutManager, gridInfo));
        arrayList.removeIf(new C0571z(21));
        return arrayList;
    }
}
