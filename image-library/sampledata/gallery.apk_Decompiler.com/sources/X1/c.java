package X1;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import k2.d;
import k2.h;
import kotlin.jvm.internal.j;
import l2.C0231d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends h {

    /* renamed from: e0  reason: collision with root package name */
    public float f894e0;

    /* renamed from: f0  reason: collision with root package name */
    public boolean f895f0;

    /* renamed from: g0  reason: collision with root package name */
    public b f896g0;
    public final int h0;

    /* renamed from: i0  reason: collision with root package name */
    public final int f897i0;

    /* renamed from: j0  reason: collision with root package name */
    public int f898j0;
    public final int k0;
    public boolean l0;

    /* renamed from: m0  reason: collision with root package name */
    public final ArrayList f899m0 = new ArrayList();

    public c(Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.sesl_bottom_navigation_width_proportion, typedValue, true);
        this.f894e0 = typedValue.getFloat();
        this.h0 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_item_max_width);
        this.f897i0 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_item_min_width);
        this.f898j0 = (int) (((float) getResources().getDisplayMetrics().widthPixels) * this.f894e0);
        this.k0 = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_active_item_min_width);
        this.T = false;
    }

    private int getLargestItemWidth() {
        int i2 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                i2 = Math.max(i2, childAt.getMeasuredWidth());
            }
        }
        return i2;
    }

    public final void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        boolean z3;
        int childCount = getChildCount();
        int i12 = i8 - i2;
        int i13 = i10 - i7;
        C0231d dVar = this.f1824W;
        int i14 = 0;
        if (dVar != null) {
            Resources resources = getResources();
            if (getViewVisibleItemCount() == 5) {
                z3 = true;
            } else {
                z3 = false;
            }
            i11 = dVar.e(resources, z3);
        } else {
            i11 = 0;
        }
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt = getChildAt(i16);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    int i17 = i12 - i15;
                    childAt.layout((i17 - childAt.getMeasuredWidth()) + i11, 0, i17 - i11, i13);
                } else {
                    childAt.layout(i15 + i11, 0, (childAt.getMeasuredWidth() + i15) - i11, i13);
                }
                i15 += childAt.getMeasuredWidth();
            }
        }
        d[] dVarArr = this.f1825h;
        if (dVarArr != null) {
            int length = dVarArr.length;
            while (i14 < length) {
                d dVar2 = dVarArr[i14];
                if (dVar2 != null) {
                    i(dVar2);
                    i14++;
                } else {
                    return;
                }
            }
        }
    }

    public final void onMeasure(int i2, int i7) {
        int i8;
        int largestItemWidth;
        boolean z;
        int i10;
        int i11;
        int i12;
        C0231d dVar;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        if (((float) View.MeasureSpec.getSize(i2)) / getResources().getDisplayMetrics().density < 590.0f) {
            this.f894e0 = 1.0f;
        } else {
            this.f894e0 = 0.75f;
        }
        this.f898j0 = (int) (((float) getResources().getDisplayMetrics().widthPixels) * this.f894e0);
        int size = (int) (((float) View.MeasureSpec.getSize(i2)) * this.f894e0);
        getMenu();
        getVisibleItemCount();
        int i18 = 0;
        for (int i19 = 0; i19 < getChildCount(); i19++) {
            if (getChildAt(i19).getVisibility() == 0) {
                i18++;
            }
        }
        int childCount = getChildCount();
        ArrayList arrayList = this.f899m0;
        arrayList.clear();
        int size2 = View.MeasureSpec.getSize(i7);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
        if (getLabelVisibilityMode() != 0 || !this.l0) {
            if (i18 == 0) {
                i13 = 1;
            } else {
                i13 = i18;
            }
            int i20 = size / i13;
            if (i18 != 2) {
                i20 = Math.min(i20, this.f898j0);
            }
            int i21 = size - (i20 * i18);
            for (int i22 = 0; i22 < childCount; i22++) {
                if (getChildAt(i22).getVisibility() == 8) {
                    i14 = 0;
                } else if (i21 > 0) {
                    i14 = i20 + 1;
                    i21--;
                } else {
                    i14 = i20;
                }
                arrayList.add(Integer.valueOf(i14));
            }
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            int visibility = childAt.getVisibility();
            int i23 = this.k0;
            if (visibility != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.f898j0, Integer.MIN_VALUE), makeMeasureSpec);
                i23 = Math.max(i23, childAt.getMeasuredWidth());
            }
            if (childAt.getVisibility() != 8) {
                i15 = 1;
            } else {
                i15 = 0;
            }
            int i24 = childCount - i15;
            int min = Math.min(size - (this.f897i0 * i24), Math.min(i23, this.f898j0));
            int i25 = size - min;
            if (i24 == 0) {
                i16 = 1;
            } else {
                i16 = i24;
            }
            int min2 = Math.min(i25 / i16, this.h0);
            int i26 = i25 - (i24 * min2);
            for (int i27 = 0; i27 < childCount; i27++) {
                if (getChildAt(i27).getVisibility() != 8) {
                    if (i27 == getSelectedItemPosition()) {
                        i17 = min;
                    } else {
                        i17 = min2;
                    }
                    if (i26 > 0) {
                        i17++;
                        i26--;
                    }
                } else {
                    i17 = 0;
                }
                arrayList.add(Integer.valueOf(i17));
            }
        }
        if (!this.f895f0 || (dVar = this.f1824W) == null) {
            i8 = 0;
        } else {
            i8 = dVar.d(i18, getResources());
        }
        int i28 = 0;
        for (int i29 = 0; i29 < childCount; i29++) {
            View childAt2 = getChildAt(i29);
            if (!(childAt2 == null || childAt2.getVisibility() == 8)) {
                if (this.f895f0) {
                    childAt2.setMinimumWidth(i8);
                }
                int intValue = ((Integer) arrayList.get(i29)).intValue();
                if (this.f895f0) {
                    i12 = Integer.MIN_VALUE;
                } else {
                    i12 = 1073741824;
                }
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(intValue, i12), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i28 += childAt2.getMeasuredWidth();
            }
        }
        if (this.f895f0 && (largestItemWidth = getLargestItemWidth()) != 0) {
            int i30 = largestItemWidth * i18;
            int min3 = (Math.min(getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_floating_max_width), size) - getPaddingLeft()) - getPaddingRight();
            if (i30 <= min3) {
                z = true;
            } else {
                z = false;
            }
            boolean z3 = false;
            for (int i31 = 0; i31 < childCount; i31++) {
                View childAt3 = getChildAt(i31);
                if (!(childAt3 == null || childAt3.getVisibility() == 8)) {
                    ViewGroup.LayoutParams layoutParams = childAt3.getLayoutParams();
                    if (z) {
                        i10 = i18;
                        i11 = largestItemWidth;
                    } else {
                        i10 = i18 - 1;
                        i11 = min3 / Math.max(i18, 0);
                    }
                    if (layoutParams.width != i11) {
                        childAt3.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), makeMeasureSpec);
                        z3 = true;
                    }
                    min3 -= i11;
                    i18 = i10;
                }
            }
            if (z3) {
                int i32 = 0;
                for (int i33 = 0; i33 < childCount; i33++) {
                    View childAt4 = getChildAt(i33);
                    if (!(childAt4 == null || childAt4.getVisibility() == 8)) {
                        i32 = childAt4.getMeasuredWidth() + i32;
                    }
                }
                i28 = i32;
            }
        }
        setMeasuredDimension(i28, size2);
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.l0 = z;
    }

    public void setStrategy(C0231d dVar) {
        this.f1824W = dVar;
        this.V = dVar.i();
        C0231d dVar2 = this.f1824W;
        Resources resources = getResources();
        dVar2.getClass();
        j.e(resources, "resources");
        int b = C0231d.b(dVar2.h(), resources);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof d) {
                ((d) childAt).setSelectedSidePadding(b);
            }
        }
        if (this.V) {
            setClipToPadding(false);
            setClipChildren(false);
        }
    }

    public void setViewType(int i2) {
        super.setViewType(i2);
        b bVar = this.f896g0;
        if (bVar != null) {
            ((BottomNavigationView) ((G0.c) bVar).e).a(i2);
        }
    }

    public void setViewTypeChangeListener(b bVar) {
        this.f896g0 = bVar;
    }
}
