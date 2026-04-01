package r3;

import Ae.a;
import android.content.Context;
import androidx.work.ForegroundInfo;
import androidx.work.impl.utils.WorkForegroundUpdater;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableBarcode;
import java.io.Serializable;
import java.util.UUID;
import kotlin.jvm.internal.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Serializable f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f1911h;

    public /* synthetic */ b(int i2, Serializable serializable, Object obj, Object obj2, Object obj3) {
        this.d = i2;
        this.e = obj;
        this.f = serializable;
        this.g = obj2;
        this.f1911h = obj3;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                return BarcodeHelper.showBarcodeDialog$lambda$0((SelectableBarcode) this.e, (r) this.f, (BarcodeHelper) this.g, (Barcode) this.f1911h);
            default:
                return ((WorkForegroundUpdater) this.e).lambda$setForegroundAsync$0((UUID) this.f, (ForegroundInfo) this.g, (Context) this.f1911h);
        }
    }
}
