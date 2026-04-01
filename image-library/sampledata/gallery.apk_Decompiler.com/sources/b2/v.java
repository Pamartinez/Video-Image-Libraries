package B2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends ArrayAdapter {
    public ColorStateList d;
    public ColorStateList e;
    public final /* synthetic */ w f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v(w wVar, Context context, int i2, String[] strArr) {
        super(context, i2, strArr);
        this.f = wVar;
        a();
    }

    public final void a() {
        ColorStateList colorStateList;
        w wVar = this.f;
        ColorStateList colorStateList2 = wVar.k;
        ColorStateList colorStateList3 = null;
        if (colorStateList2 != null) {
            int[] iArr = {16842919};
            colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList2.getColorForState(iArr, 0), 0});
        } else {
            colorStateList = null;
        }
        this.e = colorStateList;
        if (!(wVar.f77j == 0 || wVar.k == null)) {
            int[] iArr2 = {16843623, -16842919};
            int[] iArr3 = {16842913, -16842919};
            colorStateList3 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{ColorUtils.compositeColors(wVar.k.getColorForState(iArr3, 0), wVar.f77j), ColorUtils.compositeColors(wVar.k.getColorForState(iArr2, 0), wVar.f77j), wVar.f77j});
        }
        this.d = colorStateList3;
    }

    public final View getView(int i2, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i2, view, viewGroup);
        if (view2 instanceof TextView) {
            TextView textView = (TextView) view2;
            w wVar = this.f;
            RippleDrawable rippleDrawable = null;
            if (wVar.getText().toString().contentEquals(textView.getText()) && wVar.f77j != 0) {
                ColorDrawable colorDrawable = new ColorDrawable(wVar.f77j);
                if (this.e != null) {
                    DrawableCompat.setTintList(colorDrawable, this.d);
                    rippleDrawable = new RippleDrawable(this.e, colorDrawable, (Drawable) null);
                } else {
                    rippleDrawable = colorDrawable;
                }
            }
            ViewCompat.setBackground(textView, rippleDrawable);
        }
        return view2;
    }
}
