package com.google.android.material.timepicker;

import Q1.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ClockFaceView extends e implements d {
    public final ClockHandView g;

    /* renamed from: h  reason: collision with root package name */
    public final Rect f1548h = new Rect();

    /* renamed from: i  reason: collision with root package name */
    public final RectF f1549i = new RectF();

    /* renamed from: j  reason: collision with root package name */
    public final Rect f1550j = new Rect();
    public final SparseArray k;
    public final c l;
    public final int[] m;
    public final float[] n;

    /* renamed from: o  reason: collision with root package name */
    public final int f1551o;

    /* renamed from: p  reason: collision with root package name */
    public final int f1552p;
    public final int q;
    public final int r;
    public final String[] s;
    public float t;
    public final ColorStateList u;

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        SparseArray sparseArray = new SparseArray();
        this.k = sparseArray;
        this.n = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f629i, R.attr.materialClockStyle, 2131953249);
        Resources resources = getResources();
        ColorStateList u3 = B1.a.u(context, obtainStyledAttributes, 1);
        this.u = u3;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.g = clockHandView;
        this.f1551o = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = u3.getColorForState(new int[]{16842913}, u3.getDefaultColor());
        this.m = new int[]{colorForState, colorForState, u3.getDefaultColor()};
        clockHandView.f.add(this);
        int defaultColor = AppCompatResources.getColorStateList(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList u6 = B1.a.u(context, obtainStyledAttributes, 0);
        setBackgroundColor(u6 != null ? u6.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new b(this));
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.l = new c(this);
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.s = strArr;
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        boolean z = false;
        for (int i2 = 0; i2 < Math.max(this.s.length, size); i2++) {
            TextView textView = (TextView) sparseArray.get(i2);
            if (i2 >= this.s.length) {
                removeView(textView);
                sparseArray.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R.layout.material_clockface_textview, this, false);
                    sparseArray.put(i2, textView);
                    addView(textView);
                }
                textView.setText(this.s[i2]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i2));
                int i7 = (i2 / 12) + 1;
                textView.setTag(R.id.material_clock_level, Integer.valueOf(i7));
                z = i7 > 1 ? true : z;
                ViewCompat.setAccessibilityDelegate(textView, this.l);
                textView.setTextColor(this.u);
            }
        }
        ClockHandView clockHandView2 = this.g;
        if (clockHandView2.e && !z) {
            clockHandView2.f1557p = 1;
        }
        clockHandView2.e = z;
        clockHandView2.invalidate();
        this.f1552p = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.q = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.r = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }

    public final void a() {
        int i2;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        HashMap hashMap = new HashMap();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getId() != R.id.circle_center && !"skip".equals(childAt.getTag())) {
                int i8 = (Integer) childAt.getTag(R.id.material_clock_level);
                if (i8 == null) {
                    i8 = 1;
                }
                if (!hashMap.containsKey(i8)) {
                    hashMap.put(i8, new ArrayList());
                }
                ((List) hashMap.get(i8)).add(childAt);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            List<View> list = (List) entry.getValue();
            if (((Integer) entry.getKey()).intValue() == 2) {
                i2 = Math.round(((float) this.e) * 0.66f);
            } else {
                i2 = this.e;
            }
            float f = 0.0f;
            for (View id : list) {
                constraintSet.constrainCircle(id.getId(), R.id.circle_center, i2, f);
                f += 360.0f / ((float) list.size());
            }
        }
        constraintSet.applyTo(this);
        int i10 = 0;
        while (true) {
            SparseArray sparseArray = this.k;
            if (i10 < sparseArray.size()) {
                ((TextView) sparseArray.get(i10)).setVisibility(0);
                i10++;
            } else {
                return;
            }
        }
    }

    public final void b() {
        SparseArray sparseArray;
        Rect rect;
        RectF rectF;
        boolean z;
        RadialGradient radialGradient;
        RectF rectF2 = this.g.f1555j;
        float f = Float.MAX_VALUE;
        TextView textView = null;
        int i2 = 0;
        while (true) {
            sparseArray = this.k;
            int size = sparseArray.size();
            rect = this.f1548h;
            rectF = this.f1549i;
            if (i2 >= size) {
                break;
            }
            TextView textView2 = (TextView) sparseArray.get(i2);
            if (textView2 != null) {
                textView2.getHitRect(rect);
                rectF.set(rect);
                rectF.union(rectF2);
                float height = rectF.height() * rectF.width();
                if (height < f) {
                    textView = textView2;
                    f = height;
                }
            }
            i2++;
        }
        for (int i7 = 0; i7 < sparseArray.size(); i7++) {
            TextView textView3 = (TextView) sparseArray.get(i7);
            if (textView3 != null) {
                if (textView3 == textView) {
                    z = true;
                } else {
                    z = false;
                }
                textView3.setSelected(z);
                textView3.getHitRect(rect);
                rectF.set(rect);
                Rect rect2 = this.f1550j;
                textView3.getLineBounds(0, rect2);
                rectF.inset((float) rect2.left, (float) rect2.top);
                if (!RectF.intersects(rectF2, rectF)) {
                    radialGradient = null;
                } else {
                    radialGradient = new RadialGradient(rectF2.centerX() - rectF.left, rectF2.centerY() - rectF.top, 0.5f * rectF2.width(), this.m, this.n, Shader.TileMode.CLAMP);
                }
                textView3.getPaint().setShader(radialGradient);
                textView3.invalidate();
            }
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.s.length, false, 1));
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        b();
    }

    public final void onMeasure(int i2, int i7) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int max = (int) (((float) this.r) / Math.max(Math.max(((float) this.f1552p) / ((float) displayMetrics.heightPixels), ((float) this.q) / ((float) displayMetrics.widthPixels)), 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max, 1073741824);
        setMeasuredDimension(max, max);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }
}
