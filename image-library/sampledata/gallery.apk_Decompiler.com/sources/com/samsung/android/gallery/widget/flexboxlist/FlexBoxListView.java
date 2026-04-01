package com.samsung.android.gallery.widget.flexboxlist;

import A4.C0372g;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$styleable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlexBoxListView extends RecyclerView {
    private String mEmptyString;

    public FlexBoxListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* access modifiers changed from: private */
    public void retryLayout() {
        View childAt;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && (childAt = layoutManager.getChildAt(0)) != null && layoutManager.getPosition(childAt) >= 0) {
            requestLayout();
        }
    }

    public View focusSearch(View view, int i2) {
        return getParent().focusSearch(view, i2);
    }

    public String getEmptyString() {
        return this.mEmptyString;
    }

    public void onMeasure(int i2, int i7) {
        try {
            super.onMeasure(i2, i7);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.w((CharSequence) "FlexBoxListView", "onMeasure failed", (Throwable) e);
            post(new C0372g(21, this));
        } catch (NullPointerException e7) {
            Log.e((CharSequence) "FlexBoxListView", "onMeasure failed", (Throwable) e7);
        }
    }

    public FlexBoxListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.FlexBoxListView);
            this.mEmptyString = obtainStyledAttributes.getString(R$styleable.FlexBoxListView_emptyString);
            obtainStyledAttributes.recycle();
        }
        setItemAnimator((RecyclerView.ItemAnimator) null);
    }
}
