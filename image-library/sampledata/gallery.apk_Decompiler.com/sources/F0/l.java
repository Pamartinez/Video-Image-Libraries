package F0;

import J0.f;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.LocaleList;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends Paint {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f211a;

    public /* synthetic */ l() {
        this.f211a = 2;
    }

    public void setAlpha(int i2) {
        switch (this.f211a) {
            case 2:
                PointF pointF = f.f362a;
                super.setAlpha(Math.max(0, Math.min(ScoverState.TYPE_NFC_SMART_COVER, i2)));
                return;
            default:
                super.setAlpha(i2);
                return;
        }
    }

    public void setTextLocales(LocaleList localeList) {
        switch (this.f211a) {
            case 2:
                return;
            default:
                super.setTextLocales(localeList);
                return;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i2, int i7) {
        super(i2);
        this.f211a = i7;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(PorterDuff.Mode mode) {
        super(1);
        this.f211a = 2;
        setXfermode(new PorterDuffXfermode(mode));
    }

    private final void a(LocaleList localeList) {
    }
}
