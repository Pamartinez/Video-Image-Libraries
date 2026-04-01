package z2;

import D0.f;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.oneui.common.internal.util.MaxFontScaleRatio;
import androidx.appcompat.oneui.common.internal.util.TextViewHelperKt;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.SemBlurCompat;
import androidx.reflect.feature.SeslFloatingFeatureReflector;
import androidx.reflect.widget.SeslTextViewReflector;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import u4.C0518a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends q {

    /* renamed from: I  reason: collision with root package name */
    public static final int[] f2226I = {R.attr.snackbarButtonStyle, R.attr.snackbarTextViewStyle};

    /* renamed from: J  reason: collision with root package name */
    public static boolean f2227J = false;

    /* renamed from: F  reason: collision with root package name */
    public final AccessibilityManager f2228F;

    /* renamed from: G  reason: collision with root package name */
    public boolean f2229G;

    /* renamed from: H  reason: collision with root package name */
    public int f2230H = -1;

    public r(Context context, ViewGroup viewGroup, SnackbarContentLayout snackbarContentLayout, SnackbarContentLayout snackbarContentLayout2) {
        super(context, viewGroup, snackbarContentLayout, snackbarContentLayout2);
        this.f2228F = (AccessibilityManager) viewGroup.getContext().getSystemService("accessibility");
    }

    public static r j(View view, int i2, int i7, boolean z, CharSequence charSequence) {
        ViewGroup viewGroup;
        int i8;
        int i10;
        int i11 = i7;
        f2227J = false;
        View view2 = view;
        ViewGroup viewGroup2 = null;
        while (true) {
            if (view2 instanceof CoordinatorLayout) {
                f2227J = true;
                viewGroup = (ViewGroup) view2;
                break;
            }
            if (view2 instanceof FrameLayout) {
                if (view2.getId() == 16908290) {
                    viewGroup = (ViewGroup) view2;
                    break;
                }
                viewGroup2 = (ViewGroup) view2;
            }
            if (view2 != null) {
                ViewParent parent = view2.getParent();
                if (parent instanceof View) {
                    view2 = (View) parent;
                } else {
                    view2 = null;
                }
            }
            if (view2 == null) {
                viewGroup = viewGroup2;
                break;
            }
            int i12 = i2;
            CharSequence charSequence2 = charSequence;
        }
        if (viewGroup != null) {
            Context context = viewGroup.getContext();
            LayoutInflater from = LayoutInflater.from(context);
            if (i11 == 0) {
                i8 = R.layout.sesl_layout_snackbar_suggest_include;
            } else {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f2226I);
                int resourceId = obtainStyledAttributes.getResourceId(0, -1);
                int resourceId2 = obtainStyledAttributes.getResourceId(1, -1);
                obtainStyledAttributes.recycle();
                if (resourceId == -1 || resourceId2 == -1) {
                    i8 = R.layout.design_layout_snackbar_include;
                } else {
                    i8 = R.layout.mtrl_layout_snackbar_include;
                }
            }
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) from.inflate(i8, viewGroup, false);
            snackbarContentLayout.setIsCoordinatorLayoutParent(f2227J);
            SnackbarContentLayout snackbarContentLayout2 = snackbarContentLayout.f1496i;
            r rVar = new r(context, viewGroup, snackbarContentLayout, snackbarContentLayout);
            rVar.f2230H = i11;
            p pVar = rVar.f2221i;
            ((SnackbarContentLayout) pVar.getChildAt(0)).getMessageView().setText(charSequence);
            if (rVar.f2230H == 0) {
                i10 = R.dimen.sesl_design_snackbar_suggest_text_size;
            } else {
                i10 = R.dimen.design_snackbar_text_size;
            }
            TextViewHelperKt.checkMaxFontScale(((SnackbarContentLayout) pVar.getChildAt(0)).getMessageView(), i10, MaxFontScaleRatio.LARGE);
            rVar.k = i2;
            snackbarContentLayout.f1498o = !z;
            snackbarContentLayout.n = 2;
            if (!z && Build.VERSION.SDK_INT >= 36 && !"FALSE".equalsIgnoreCase(SeslFloatingFeatureReflector.getString("SEC_FLOATING_FEATURE_GRAPHICS_SUPPORT_3D_SURFACE_TRANSITION_FLAG", "FALSE")) && snackbarContentLayout2 != null) {
                if (SemBlurCompat.setBlurEffectPreset(snackbarContentLayout.f1496i, 2, new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.5f, -15.0f, 0.0f, 255.0f, 33.8f, 153.7f), (Integer) null, (Float) null, 1)) {
                    snackbarContentLayout2.setOutlineProvider(new t(snackbarContentLayout, (float) snackbarContentLayout.getContext().getResources().getDimensionPixelSize(R.dimen.sesl_design_snackbar_suggest_background_radius)));
                    snackbarContentLayout2.setClipToOutline(true);
                    snackbarContentLayout2.setBackgroundTintList(ColorStateList.valueOf(0));
                    snackbarContentLayout2.invalidate();
                }
            }
            if (i11 == 0) {
                pVar.setAnimationMode(2);
            }
            return rVar;
        }
        throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
    }

    public final void k(CharSequence charSequence, View.OnClickListener onClickListener) {
        int i2;
        int i7;
        p pVar = this.f2221i;
        boolean z = false;
        Button actionView = ((SnackbarContentLayout) pVar.getChildAt(0)).getActionView();
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) pVar.getChildAt(0);
        Resources resources = pVar.getResources();
        if (this.f2230H == 0) {
            i2 = R.drawable.sesl_snackbar_suggest_action_frame_mtrl;
        } else {
            i2 = R.drawable.sem_snackbar_action_frame_mtrl;
        }
        snackbarContentLayout.setBackground(resources.getDrawable(i2));
        if (!TextUtils.isEmpty(charSequence)) {
            this.f2229G = true;
            if (this.f2230H != 0) {
                actionView.setVisibility(0);
            }
            actionView.setText(charSequence);
            actionView.setOnClickListener(new C0518a(3, this, onClickListener));
            if (this.f2230H == 0) {
                i7 = R.dimen.sesl_design_snackbar_suggest_action_text_size;
            } else {
                i7 = R.dimen.sesl_design_snackbar_action_text_size;
            }
            TextViewHelperKt.checkMaxFontScale(((SnackbarContentLayout) pVar.getChildAt(0)).getMessageView(), i7, MaxFontScaleRatio.LARGE);
            ContentResolver contentResolver = this.f2220h.getContentResolver();
            if (contentResolver != null && Settings.System.getInt(contentResolver, "show_button_background", 0) == 1) {
                z = true;
            }
            SeslTextViewReflector.semSetButtonShapeEnabled(actionView, z);
            return;
        }
        actionView.setVisibility(8);
        actionView.setOnClickListener((View.OnClickListener) null);
        this.f2229G = false;
    }

    public final void l() {
        int i2;
        f F4 = f.F();
        int i7 = this.k;
        boolean z = false;
        int i8 = -2;
        if (i7 != -2) {
            if (this.f2229G) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            i8 = this.f2228F.getRecommendedTimeoutMillis(i7, i2 | 3);
        }
        k kVar = this.f2225x;
        synchronized (F4.e) {
            try {
                if (F4.H(kVar)) {
                    v vVar = (v) F4.g;
                    vVar.b = i8;
                    ((Handler) F4.f).removeCallbacksAndMessages(vVar);
                    F4.T((v) F4.g);
                    return;
                }
                v vVar2 = (v) F4.f106h;
                if (!(vVar2 == null || kVar == null || vVar2.f2233a.get() != kVar)) {
                    z = true;
                }
                if (z) {
                    ((v) F4.f106h).b = i8;
                } else {
                    F4.f106h = new v(i8, kVar);
                }
                v vVar3 = (v) F4.g;
                if (vVar3 == null || !F4.z(vVar3, 4)) {
                    F4.g = null;
                    F4.V();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
