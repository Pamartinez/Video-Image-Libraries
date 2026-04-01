package com.google.android.material.datepicker;

import Q1.a;
import a6.C0419b;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.internal.CheckableImageButton;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import com.sec.android.gallery3d.R;
import e2.C0184a;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import og.k;
import x2.C0340g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s<S> extends DialogFragment {

    /* renamed from: A  reason: collision with root package name */
    public CharSequence f1463A;
    public final LinkedHashSet d = new LinkedHashSet();
    public final LinkedHashSet e = new LinkedHashSet();
    public int f;
    public z g;

    /* renamed from: h  reason: collision with root package name */
    public C0117b f1464h;

    /* renamed from: i  reason: collision with root package name */
    public p f1465i;

    /* renamed from: j  reason: collision with root package name */
    public int f1466j;
    public CharSequence k;
    public boolean l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public CharSequence f1467o;

    /* renamed from: p  reason: collision with root package name */
    public int f1468p;
    public CharSequence q;
    public int r;
    public CharSequence s;
    public int t;
    public CharSequence u;
    public TextView v;
    public CheckableImageButton w;

    /* renamed from: x  reason: collision with root package name */
    public C0340g f1469x;
    public boolean y;
    public CharSequence z;

    public s() {
        new LinkedHashSet();
        new LinkedHashSet();
    }

    public static int d(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        Calendar b = D.b();
        b.set(5, 1);
        Calendar a7 = D.a(b);
        a7.get(2);
        a7.get(1);
        int maximum = a7.getMaximum(7);
        a7.getActualMaximum(5);
        a7.getTimeInMillis();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width) * maximum;
        return ((maximum - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding)) + dimensionPixelSize + (dimensionPixelOffset * 2);
    }

    public static boolean e(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(k.N(context, R.attr.materialCalendarStyle, p.class.getCanonicalName()).data, new int[]{i2});
        boolean z3 = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z3;
    }

    public final void c() {
        if (getArguments().getParcelable("DATE_SELECTOR_KEY") != null) {
            throw new ClassCastException();
        }
    }

    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f = bundle.getInt("OVERRIDE_THEME_RES_ID");
        if (bundle.getParcelable("DATE_SELECTOR_KEY") == null) {
            this.f1464h = (C0117b) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
            if (bundle.getParcelable("DAY_VIEW_DECORATOR_KEY") == null) {
                this.f1466j = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
                this.k = bundle.getCharSequence("TITLE_TEXT_KEY");
                this.m = bundle.getInt("INPUT_MODE_KEY");
                this.n = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
                this.f1467o = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
                this.f1468p = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
                this.q = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
                this.r = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
                this.s = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
                this.t = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
                this.u = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
                CharSequence charSequence = this.k;
                if (charSequence == null) {
                    charSequence = requireContext().getResources().getText(this.f1466j);
                }
                this.z = charSequence;
                if (charSequence != null) {
                    CharSequence[] split = TextUtils.split(String.valueOf(charSequence), "\n");
                    if (split.length > 1) {
                        charSequence = split[0];
                    }
                } else {
                    charSequence = null;
                }
                this.f1463A = charSequence;
                return;
            }
            throw new ClassCastException();
        }
        throw new ClassCastException();
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        Context requireContext = requireContext();
        requireContext();
        int i2 = this.f;
        if (i2 != 0) {
            Dialog dialog = new Dialog(requireContext, i2);
            Context context = dialog.getContext();
            this.l = e(context, 16843277);
            this.f1469x = new C0340g(context, (AttributeSet) null, R.attr.materialCalendarStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_MaterialCalendar);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, a.f633x, R.attr.materialCalendarStyle, com.samsung.android.photoremaster.engine.R.style.Widget_MaterialComponents_MaterialCalendar);
            int color = obtainStyledAttributes.getColor(1, 0);
            obtainStyledAttributes.recycle();
            this.f1469x.i(context);
            this.f1469x.k(ColorStateList.valueOf(color));
            this.f1469x.j(ViewCompat.getElevation(dialog.getWindow().getDecorView()));
            return dialog;
        }
        c();
        throw null;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        String str;
        if (this.l) {
            i2 = R.layout.mtrl_picker_fullscreen;
        } else {
            i2 = R.layout.mtrl_picker_dialog;
        }
        View inflate = layoutInflater.inflate(i2, viewGroup);
        Context context = inflate.getContext();
        if (this.l) {
            inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(d(context), -2));
        } else {
            inflate.findViewById(R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(d(context), -1));
        }
        ViewCompat.setAccessibilityLiveRegion((TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text), 1);
        this.w = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
        this.v = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
        this.w.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.w;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, AppCompatResources.getDrawable(context, R.drawable.material_ic_calendar_black_24dp));
        boolean z3 = false;
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(context, R.drawable.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        CheckableImageButton checkableImageButton2 = this.w;
        if (this.m != 0) {
            z3 = true;
        }
        checkableImageButton2.setChecked(z3);
        ViewCompat.setAccessibilityDelegate(this.w, (AccessibilityDelegateCompat) null);
        CheckableImageButton checkableImageButton3 = this.w;
        if (this.m == 1) {
            str = checkableImageButton3.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode);
        } else {
            str = checkableImageButton3.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode);
        }
        this.w.setContentDescription(str);
        this.w.setOnClickListener(new C0419b(6, this));
        Button button = (Button) inflate.findViewById(R.id.confirm_button);
        c();
        throw null;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.material.datepicker.a] */
    public final void onSaveInstanceState(Bundle bundle) {
        u uVar;
        u uVar2;
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.f);
        bundle.putParcelable("DATE_SELECTOR_KEY", (Parcelable) null);
        C0117b bVar = this.f1464h;
        ? obj = new Object();
        int i2 = C0116a.b;
        int i7 = C0116a.b;
        long j2 = bVar.d.f1471i;
        long j3 = bVar.e.f1471i;
        obj.f1450a = Long.valueOf(bVar.g.f1471i);
        int i8 = bVar.f1451h;
        C0119d dVar = bVar.f;
        p pVar = this.f1465i;
        if (pVar == null) {
            uVar = null;
        } else {
            uVar = pVar.g;
        }
        if (uVar != null) {
            obj.f1450a = Long.valueOf(uVar.f1471i);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", dVar);
        u d2 = u.d(j2);
        u d3 = u.d(j3);
        C0119d dVar2 = (C0119d) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l8 = obj.f1450a;
        if (l8 == null) {
            uVar2 = null;
        } else {
            uVar2 = u.d(l8.longValue());
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new C0117b(d2, d3, dVar2, uVar2, i8));
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", (Parcelable) null);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.f1466j);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.k);
        bundle.putInt("INPUT_MODE_KEY", this.m);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.n);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.f1467o);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.f1468p);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.q);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.r);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.s);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.t);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.u);
    }

    public final void onStart() {
        CharSequence charSequence;
        Integer num;
        boolean z3;
        Integer num2;
        int i2;
        boolean z7;
        int i7;
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.l) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.f1469x);
            if (!this.y) {
                View findViewById = requireView().findViewById(R.id.fullscreen_header);
                ColorStateList B = c.B(findViewById.getBackground());
                if (B != null) {
                    num = Integer.valueOf(B.getDefaultColor());
                } else {
                    num = null;
                }
                boolean z9 = false;
                if (num == null || num.intValue() == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Context context = window.getContext();
                TypedValue J4 = k.J(context, 16842801);
                if (J4 != null) {
                    int i8 = J4.resourceId;
                    if (i8 != 0) {
                        i7 = ContextCompat.getColor(context, i8);
                    } else {
                        i7 = J4.data;
                    }
                    num2 = Integer.valueOf(i7);
                } else {
                    num2 = null;
                }
                if (num2 != null) {
                    i2 = num2.intValue();
                } else {
                    i2 = -16777216;
                }
                if (z3) {
                    num = Integer.valueOf(i2);
                }
                WindowCompat.setDecorFitsSystemWindows(window, false);
                window.getContext();
                window.getContext();
                window.setStatusBarColor(0);
                window.setNavigationBarColor(0);
                int intValue = num.intValue();
                if (intValue == 0 || ColorUtils.calculateLuminance(intValue) <= 0.5d) {
                    z7 = false;
                } else {
                    z7 = true;
                }
                WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightStatusBars(z7);
                if (i2 != 0 && ColorUtils.calculateLuminance(i2) > 0.5d) {
                    z9 = true;
                }
                WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightNavigationBars(z9);
                ViewCompat.setOnApplyWindowInsetsListener(findViewById, new r(findViewById.getLayoutParams().height, findViewById.getPaddingTop(), findViewById));
                this.y = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable(this.f1469x, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new C0184a(requireDialog(), rect));
        }
        requireContext();
        int i10 = this.f;
        if (i10 != 0) {
            c();
            C0117b bVar = this.f1464h;
            p pVar = new p();
            Bundle bundle = new Bundle();
            bundle.putInt("THEME_RES_ID_KEY", i10);
            bundle.putParcelable("GRID_SELECTOR_KEY", (Parcelable) null);
            bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar);
            bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", (Parcelable) null);
            bundle.putParcelable("CURRENT_MONTH_KEY", bVar.g);
            pVar.setArguments(bundle);
            this.f1465i = pVar;
            z zVar = pVar;
            if (this.m == 1) {
                c();
                C0117b bVar2 = this.f1464h;
                z tVar = new t();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("THEME_RES_ID_KEY", i10);
                bundle2.putParcelable("DATE_SELECTOR_KEY", (Parcelable) null);
                bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", bVar2);
                tVar.setArguments(bundle2);
                zVar = tVar;
            }
            this.g = zVar;
            TextView textView = this.v;
            if (this.m == 1 && getResources().getConfiguration().orientation == 2) {
                charSequence = this.f1463A;
            } else {
                charSequence = this.z;
            }
            textView.setText(charSequence);
            c();
            getContext();
            throw null;
        }
        c();
        throw null;
    }

    public final void onStop() {
        this.g.d.clear();
        super.onStop();
    }
}
