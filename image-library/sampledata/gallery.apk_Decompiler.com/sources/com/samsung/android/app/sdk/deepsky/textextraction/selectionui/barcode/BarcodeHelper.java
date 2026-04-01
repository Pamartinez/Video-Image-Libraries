package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode;

import Sf.q;
import android.content.Context;
import android.graphics.Point;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableBarcode;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.helper.LockScreenHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.r;
import me.i;
import me.x;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import r3.b;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0004\b\u000b\u0010\u0015J\r\u0010\u0016\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\r¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u0019\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0!0\r¢\u0006\u0004\b\"\u0010\u001aJ\r\u0010#\u001a\u00020\u000f¢\u0006\u0004\b#\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010&R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010'R\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00180\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010'¨\u0006*"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeHelper;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;", "listener", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;)V", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "barcode", "", "showBarcodeDialog", "(Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;)Z", "", "barcodeList", "Lme/x;", "setBarcodeList", "(Ljava/util/List;)V", "", "x", "y", "(II)Z", "isBarcodeSelected", "()Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/data/SelectableBarcode;", "getSelectedBarcodes", "()Ljava/util/List;", "", "imageRatio", "Landroid/graphics/Point;", "centerOffset", "updateSelectableBarcodes", "(FLandroid/graphics/Point;)V", "", "makeHighlightPolyPerBarcode", "clearAllSelection", "()V", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeDialogListener;", "Ljava/util/List;", "selectableBarcodeList", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeHelper {
    public static final Companion Companion = new Companion((e) null);
    private List<Barcode> barcodeList;
    private final Context context;
    private final BarcodeDialogListener listener;
    private List<SelectableBarcode> selectableBarcodeList;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/barcode/BarcodeHelper$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public BarcodeHelper(Context context2, BarcodeDialogListener barcodeDialogListener) {
        j.e(context2, "context");
        j.e(barcodeDialogListener, "listener");
        this.context = context2;
        this.listener = barcodeDialogListener;
        C1202t tVar = C1202t.d;
        this.barcodeList = tVar;
        this.selectableBarcodeList = tVar;
    }

    /* access modifiers changed from: private */
    public static final x showBarcodeDialog$lambda$0(SelectableBarcode selectableBarcode, r rVar, BarcodeHelper barcodeHelper, Barcode barcode) {
        selectableBarcode.setSelected(true);
        rVar.d = barcodeHelper.showBarcodeDialog(barcode);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x showBarcodeDialog$lambda$1(r rVar) {
        rVar.d = false;
        return x.f4917a;
    }

    public final void clearAllSelection() {
        for (SelectableBarcode selected : this.selectableBarcodeList) {
            selected.setSelected(false);
        }
    }

    public final List<SelectableBarcode> getSelectedBarcodes() {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.selectableBarcodeList) {
            if (((SelectableBarcode) next).isSelected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final boolean isBarcodeSelected() {
        Iterable<SelectableBarcode> iterable = this.selectableBarcodeList;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (SelectableBarcode isSelected : iterable) {
                if (isSelected.isSelected()) {
                    LibLogger.i("BarcodeHelper", "barcode is selected");
                    return true;
                }
            }
        }
        LibLogger.i("BarcodeHelper", "barcode is not selected");
        return false;
    }

    public final List<Point[]> makeHighlightPolyPerBarcode() {
        Iterable<SelectableBarcode> iterable = this.selectableBarcodeList;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (SelectableBarcode poly : iterable) {
            arrayList.add(poly.getPoly());
        }
        return arrayList;
    }

    public final void setBarcodeList(List<Barcode> list) {
        j.e(list, "barcodeList");
        this.barcodeList = list;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [kotlin.jvm.internal.r, java.lang.Object, java.io.Serializable] */
    public final boolean showBarcodeDialog(int i2, int i7) {
        Iterator it = C1194l.r1(this.barcodeList, this.selectableBarcodeList).iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            Barcode barcode = (Barcode) iVar.d;
            SelectableBarcode selectableBarcode = (SelectableBarcode) iVar.e;
            if (PointUtil.INSTANCE.isPointInsidePoly(new Point(i2, i7), selectableBarcode.getPoly())) {
                ? obj = new Object();
                LockScreenHelper.INSTANCE.requestDismissKeyguard(this.context, new b(0, obj, selectableBarcode, this, barcode), new q(23, obj));
                return obj.d;
            }
        }
        return false;
    }

    public final void updateSelectableBarcodes(float f, Point point) {
        j.e(point, "centerOffset");
        Iterable<Barcode> iterable = this.barcodeList;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (Barcode poly : iterable) {
            arrayList.add(new SelectableBarcode(PointUtil.INSTANCE.getTransformedPoly(poly.getPoly(), f, point)));
        }
        this.selectableBarcodeList = arrayList;
    }

    private final boolean showBarcodeDialog(Barcode barcode) {
        if (this.context instanceof FragmentActivity) {
            new BarcodeDialogFragment(this.context, this.listener, barcode).show(((FragmentActivity) this.context).getSupportFragmentManager(), "BarcodeHelper");
            return true;
        }
        LibLogger.e("BarcodeHelper", "Context is not fragmentActivity, so the view cannot show dialogs.");
        return false;
    }
}
