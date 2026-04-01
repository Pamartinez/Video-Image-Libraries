package B2;

import B1.a;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.sec.android.gallery3d.R;
import h2.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends LinearLayout {
    public final TextInputLayout d;
    public final AppCompatTextView e;
    public CharSequence f;
    public final CheckableImageButton g;

    /* renamed from: h  reason: collision with root package name */
    public ColorStateList f78h;

    /* renamed from: i  reason: collision with root package name */
    public PorterDuff.Mode f79i;

    /* renamed from: j  reason: collision with root package name */
    public int f80j;
    public ImageView.ScaleType k;
    public View.OnLongClickListener l;
    public boolean m;

    public y(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        CharSequence text;
        this.d = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, this, false);
        this.g = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.e = appCompatTextView;
        if (a.E(getContext())) {
            MarginLayoutParamsCompat.setMarginEnd((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        View.OnLongClickListener onLongClickListener = this.l;
        CharSequence charSequence = null;
        checkableImageButton.setOnClickListener((View.OnClickListener) null);
        L2.a.x(checkableImageButton, onLongClickListener);
        this.l = null;
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        L2.a.x(checkableImageButton, (View.OnLongClickListener) null);
        if (tintTypedArray.hasValue(69)) {
            this.f78h = a.v(getContext(), tintTypedArray, 69);
        }
        if (tintTypedArray.hasValue(70)) {
            this.f79i = u.d(tintTypedArray.getInt(70, -1), (PorterDuff.Mode) null);
        }
        if (tintTypedArray.hasValue(66)) {
            b(tintTypedArray.getDrawable(66));
            if (tintTypedArray.hasValue(65) && checkableImageButton.getContentDescription() != (text = tintTypedArray.getText(65))) {
                checkableImageButton.setContentDescription(text);
            }
            checkableImageButton.setCheckable(tintTypedArray.getBoolean(64, true));
        }
        int dimensionPixelSize = tintTypedArray.getDimensionPixelSize(67, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize >= 0) {
            if (dimensionPixelSize != this.f80j) {
                this.f80j = dimensionPixelSize;
                checkableImageButton.setMinimumWidth(dimensionPixelSize);
                checkableImageButton.setMinimumHeight(dimensionPixelSize);
            }
            if (tintTypedArray.hasValue(68)) {
                ImageView.ScaleType j2 = L2.a.j(tintTypedArray.getInt(68, -1));
                this.k = j2;
                checkableImageButton.setScaleType(j2);
            }
            appCompatTextView.setVisibility(8);
            appCompatTextView.setId(R.id.textinput_prefix_text);
            appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            ViewCompat.setAccessibilityLiveRegion(appCompatTextView, 1);
            TextViewCompat.setTextAppearance(appCompatTextView, tintTypedArray.getResourceId(60, 0));
            if (tintTypedArray.hasValue(61)) {
                appCompatTextView.setTextColor(tintTypedArray.getColorStateList(61));
            }
            CharSequence text2 = tintTypedArray.getText(59);
            this.f = !TextUtils.isEmpty(text2) ? text2 : charSequence;
            appCompatTextView.setText(text2);
            e();
            addView(checkableImageButton);
            addView(appCompatTextView);
            return;
        }
        throw new IllegalArgumentException("startIconSize cannot be less than 0");
    }

    public final int a() {
        int i2;
        CheckableImageButton checkableImageButton = this.g;
        if (checkableImageButton.getVisibility() == 0) {
            i2 = MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()) + checkableImageButton.getMeasuredWidth();
        } else {
            i2 = 0;
        }
        return ViewCompat.getPaddingStart(this.e) + ViewCompat.getPaddingStart(this) + i2;
    }

    public final void b(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.g;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = this.f78h;
            PorterDuff.Mode mode = this.f79i;
            TextInputLayout textInputLayout = this.d;
            L2.a.c(textInputLayout, checkableImageButton, colorStateList, mode);
            c(true);
            L2.a.u(textInputLayout, checkableImageButton, this.f78h);
            return;
        }
        c(false);
        View.OnLongClickListener onLongClickListener = this.l;
        checkableImageButton.setOnClickListener((View.OnClickListener) null);
        L2.a.x(checkableImageButton, onLongClickListener);
        this.l = null;
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        L2.a.x(checkableImageButton, (View.OnLongClickListener) null);
        if (checkableImageButton.getContentDescription() != null) {
            checkableImageButton.setContentDescription((CharSequence) null);
        }
    }

    public final void c(boolean z) {
        boolean z3;
        CheckableImageButton checkableImageButton = this.g;
        int i2 = 0;
        if (checkableImageButton.getVisibility() == 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != z) {
            if (!z) {
                i2 = 8;
            }
            checkableImageButton.setVisibility(i2);
            d();
            e();
        }
    }

    public final void d() {
        int i2;
        EditText editText = this.d.g;
        if (editText != null) {
            if (this.g.getVisibility() == 0) {
                i2 = 0;
            } else {
                i2 = ViewCompat.getPaddingStart(editText);
            }
            ViewCompat.setPaddingRelative(this.e, i2, editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), editText.getCompoundPaddingBottom());
        }
    }

    public final void e() {
        int i2;
        int i7 = 8;
        if (this.f == null || this.m) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        if (this.g.getVisibility() == 0 || i2 == 0) {
            i7 = 0;
        }
        setVisibility(i7);
        this.e.setVisibility(i2);
        this.d.q();
    }

    public final void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        d();
    }
}
