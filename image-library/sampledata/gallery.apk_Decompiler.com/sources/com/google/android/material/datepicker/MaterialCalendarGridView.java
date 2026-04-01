package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.ViewCompat;
import com.sec.android.gallery3d.R;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MaterialCalendarGridView extends GridView {
    public final boolean d;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        D.c((Calendar) null);
        if (s.e(getContext(), 16843277)) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        this.d = s.e(getContext(), R.attr.nestedScrollable);
        ViewCompat.setAccessibilityDelegate(this, new q(0, this));
    }

    public final v a() {
        return (v) super.getAdapter();
    }

    public final Adapter getAdapter() {
        return (v) super.getAdapter();
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((v) super.getAdapter()).notifyDataSetChanged();
    }

    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        v vVar = (v) super.getAdapter();
        vVar.getClass();
        int max = Math.max(vVar.a(), getFirstVisiblePosition());
        int min = Math.min(vVar.c(), getLastVisiblePosition());
        vVar.getItem(max);
        vVar.getItem(min);
        throw null;
    }

    public final void onFocusChanged(boolean z, int i2, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i2, rect);
        } else if (i2 == 33) {
            setSelection(((v) super.getAdapter()).c());
        } else if (i2 == 130) {
            setSelection(((v) super.getAdapter()).a());
        } else {
            super.onFocusChanged(true, i2, rect);
        }
    }

    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!super.onKeyDown(i2, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= ((v) super.getAdapter()).a()) {
            return true;
        }
        if (19 != i2) {
            return false;
        }
        setSelection(((v) super.getAdapter()).a());
        return true;
    }

    public final void onMeasure(int i2, int i7) {
        if (this.d) {
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
            getLayoutParams().height = getMeasuredHeight();
            return;
        }
        super.onMeasure(i2, i7);
    }

    public final void setSelection(int i2) {
        if (i2 < ((v) super.getAdapter()).a()) {
            super.setSelection(((v) super.getAdapter()).a());
        } else {
            super.setSelection(i2);
        }
    }

    /* renamed from: getAdapter  reason: collision with other method in class */
    public final ListAdapter m35getAdapter() {
        return (v) super.getAdapter();
    }

    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof v) {
            super.setAdapter(listAdapter);
            return;
        }
        throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), v.class.getCanonicalName()}));
    }
}
