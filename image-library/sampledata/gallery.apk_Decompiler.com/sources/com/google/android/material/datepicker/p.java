package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p<S> extends z {
    public int e;
    public C0117b f;
    public u g;

    /* renamed from: h  reason: collision with root package name */
    public o f1458h;

    /* renamed from: i  reason: collision with root package name */
    public C0118c f1459i;

    /* renamed from: j  reason: collision with root package name */
    public RecyclerView f1460j;
    public RecyclerView k;
    public View l;
    public View m;
    public View n;

    /* renamed from: o  reason: collision with root package name */
    public View f1461o;

    public final void c(u uVar) {
        boolean z;
        y yVar = (y) this.k.getAdapter();
        int i2 = yVar.d.d.i(uVar);
        int i7 = i2 - yVar.d.d.i(this.g);
        boolean z3 = false;
        if (Math.abs(i7) > 3) {
            z = true;
        } else {
            z = false;
        }
        if (i7 > 0) {
            z3 = true;
        }
        this.g = uVar;
        if (z && z3) {
            this.k.scrollToPosition(i2 - 3);
            this.k.post(new g(this, i2, 0));
        } else if (z) {
            this.k.scrollToPosition(i2 + 3);
            this.k.post(new g(this, i2, 0));
        } else {
            this.k.post(new g(this, i2, 0));
        }
    }

    public final void d(o oVar) {
        this.f1458h = oVar;
        if (oVar == o.YEAR) {
            this.f1460j.getLayoutManager().scrollToPosition(this.g.f - ((F) this.f1460j.getAdapter()).d.f.d.f);
            this.n.setVisibility(0);
            this.f1461o.setVisibility(8);
            this.l.setVisibility(8);
            this.m.setVisibility(8);
        } else if (oVar == o.DAY) {
            this.n.setVisibility(8);
            this.f1461o.setVisibility(0);
            this.l.setVisibility(0);
            this.m.setVisibility(0);
            c(this.g);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.e = bundle.getInt("THEME_RES_ID_KEY");
        if (bundle.getParcelable("GRID_SELECTOR_KEY") == null) {
            this.f = (C0117b) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
            if (bundle.getParcelable("DAY_VIEW_DECORATOR_KEY") == null) {
                this.g = (u) bundle.getParcelable("CURRENT_MONTH_KEY");
                return;
            }
            throw new ClassCastException();
        }
        throw new ClassCastException();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i7;
        C0120e eVar;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.e);
        this.f1459i = new C0118c(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        u uVar = this.f.d;
        if (s.e(contextThemeWrapper, 16843277)) {
            i7 = R.layout.mtrl_calendar_vertical;
            i2 = 1;
        } else {
            i7 = R.layout.mtrl_calendar_horizontal;
            i2 = 0;
        }
        View inflate = cloneInContext.inflate(i7, viewGroup, false);
        Resources resources = requireContext().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
        int i8 = v.g;
        inflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding) * (i8 - 1)) + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * i8) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new h(0));
        int i10 = this.f.f1451h;
        if (i10 <= 0) {
            eVar = new C0120e();
        }
        gridView.setAdapter(eVar);
        gridView.setNumColumns(uVar.g);
        gridView.setEnabled(false);
        this.k = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.k.setLayoutManager(new i(this, getContext(), i2, i2));
        this.k.setTag("MONTHS_VIEW_GROUP_TAG");
        y yVar = new y(contextThemeWrapper, this.f, new j(this));
        this.k.setAdapter(yVar);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.f1460j = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f1460j.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.f1460j.setAdapter(new F(this));
            this.f1460j.addItemDecoration(new k(this));
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            ViewCompat.setAccessibilityDelegate(materialButton, new q(1, this));
            View findViewById = inflate.findViewById(R.id.month_navigation_previous);
            this.l = findViewById;
            findViewById.setTag("NAVIGATION_PREV_TAG");
            View findViewById2 = inflate.findViewById(R.id.month_navigation_next);
            this.m = findViewById2;
            findViewById2.setTag("NAVIGATION_NEXT_TAG");
            this.n = inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
            this.f1461o = inflate.findViewById(R.id.mtrl_calendar_day_selector_frame);
            d(o.DAY);
            materialButton.setText(this.g.e());
            this.k.addOnScrollListener(new l(this, yVar, materialButton));
            materialButton.setOnClickListener(new m(0, this));
            this.m.setOnClickListener(new n(this, yVar));
            this.l.setOnClickListener(new C0121f(this, yVar));
        }
        if (!s.e(contextThemeWrapper, 16843277)) {
            new PagerSnapHelper().attachToRecyclerView(this.k);
        }
        this.k.scrollToPosition(yVar.d.d.i(this.g));
        ViewCompat.setAccessibilityDelegate(this.k, new h(1));
        return inflate;
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.e);
        bundle.putParcelable("GRID_SELECTOR_KEY", (Parcelable) null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", (Parcelable) null);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.g);
    }
}
