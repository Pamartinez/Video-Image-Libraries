package t2;

import android.content.res.ColorStateList;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.CompoundButtonCompat;
import com.sec.android.gallery3d.R;
import o1.C0246a;

/* renamed from: t2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0281a extends AppCompatRadioButton {
    public static final int[][] f = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public ColorStateList d;
    public boolean e;

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.d == null) {
            int W6 = C0246a.W(R$attr.colorControlActivated, this);
            int W10 = C0246a.W(R.attr.colorOnSurface, this);
            int W11 = C0246a.W(R.attr.colorSurface, this);
            this.d = new ColorStateList(f, new int[]{C0246a.c0(1.0f, W11, W6), C0246a.c0(0.54f, W11, W10), C0246a.c0(0.38f, W11, W10), C0246a.c0(0.38f, W11, W10)});
        }
        return this.d;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e && CompoundButtonCompat.getButtonTintList(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.e = z;
        if (z) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, (ColorStateList) null);
        }
    }
}
