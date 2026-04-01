package com.google.android.material.timepicker;

import Q1.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import bc.C0584a;
import com.sec.android.gallery3d.R;
import x2.C0340g;
import x2.C0341h;
import x2.C0343j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class e extends ConstraintLayout {
    public final C0584a d = new C0584a(16, this);
    public int e;
    public final C0340g f;

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        C0340g gVar = new C0340g();
        this.f = gVar;
        C0341h hVar = new C0341h(0.5f);
        C0343j e7 = gVar.d.f2103a.e();
        e7.e = hVar;
        e7.f = hVar;
        e7.g = hVar;
        e7.f2119h = hVar;
        gVar.setShapeAppearanceModel(e7.a());
        this.f.k(ColorStateList.valueOf(-1));
        ViewCompat.setBackground(this, this.f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f618H, R.attr.materialClockStyle, 0);
        this.e = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }

    public abstract void a();

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (view.getId() == -1) {
            view.setId(ViewCompat.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            C0584a aVar = this.d;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            C0584a aVar = this.d;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    public final void setBackgroundColor(int i2) {
        this.f.k(ColorStateList.valueOf(i2));
    }
}
