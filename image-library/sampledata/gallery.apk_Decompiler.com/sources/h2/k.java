package h2;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public CharSequence f1769a;
    public final TextPaint b;

    /* renamed from: c  reason: collision with root package name */
    public final int f1770c;
    public int d;
    public Layout.Alignment e = Layout.Alignment.ALIGN_NORMAL;
    public int f = Integer.MAX_VALUE;
    public float g = 0.0f;

    /* renamed from: h  reason: collision with root package name */
    public float f1771h = 1.0f;

    /* renamed from: i  reason: collision with root package name */
    public int f1772i = 1;

    /* renamed from: j  reason: collision with root package name */
    public boolean f1773j = true;
    public boolean k;
    public TextUtils.TruncateAt l = null;

    public k(CharSequence charSequence, TextPaint textPaint, int i2) {
        this.f1769a = charSequence;
        this.b = textPaint;
        this.f1770c = i2;
        this.d = charSequence.length();
    }

    public final StaticLayout a() {
        TextDirectionHeuristic textDirectionHeuristic;
        if (this.f1769a == null) {
            this.f1769a = "";
        }
        int max = Math.max(0, this.f1770c);
        CharSequence charSequence = this.f1769a;
        int i2 = this.f;
        TextPaint textPaint = this.b;
        if (i2 == 1) {
            charSequence = TextUtils.ellipsize(charSequence, textPaint, (float) max, this.l);
        }
        int min = Math.min(charSequence.length(), this.d);
        this.d = min;
        if (this.k && this.f == 1) {
            this.e = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, min, textPaint, max);
        obtain.setAlignment(this.e);
        obtain.setIncludePad(this.f1773j);
        if (this.k) {
            textDirectionHeuristic = TextDirectionHeuristics.RTL;
        } else {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        }
        obtain.setTextDirection(textDirectionHeuristic);
        TextUtils.TruncateAt truncateAt = this.l;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.f);
        float f5 = this.g;
        if (!(f5 == 0.0f && this.f1771h == 1.0f)) {
            obtain.setLineSpacing(f5, this.f1771h);
        }
        if (this.f > 1) {
            obtain.setHyphenationFrequency(this.f1772i);
        }
        return obtain.build();
    }
}
