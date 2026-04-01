package com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui;

import B1.a;
import Ge.e;
import android.text.Layout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.y;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"getExplicitAlignment", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ui/ExplicitLayoutAlignment;", "Landroid/text/Layout;", "getLineExplicitAlignment", "line", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AccurateWidthTextViewKt {
    /* access modifiers changed from: private */
    public static final ExplicitLayoutAlignment getExplicitAlignment(Layout layout) {
        if (layout.getLineCount() == 0) {
            return ExplicitLayoutAlignment.LEFT;
        }
        e Z = a.Z(0, layout.getLineCount());
        ArrayList arrayList = new ArrayList();
        Iterator it = Z.iterator();
        while (it.hasNext()) {
            ExplicitLayoutAlignment lineExplicitAlignment = getLineExplicitAlignment(layout, ((y) it).nextInt());
            if (lineExplicitAlignment != null) {
                arrayList.add(lineExplicitAlignment);
            }
        }
        List H02 = C1194l.H0(arrayList);
        if (H02.size() > 1) {
            return ExplicitLayoutAlignment.MIXED;
        }
        ExplicitLayoutAlignment explicitLayoutAlignment = (ExplicitLayoutAlignment) C1194l.N0(H02);
        if (explicitLayoutAlignment == null) {
            return ExplicitLayoutAlignment.LEFT;
        }
        return explicitLayoutAlignment;
    }

    private static final ExplicitLayoutAlignment getLineExplicitAlignment(Layout layout, int i2) {
        if (i2 < 0 || i2 >= layout.getLineCount()) {
            return null;
        }
        boolean z = true;
        if (layout.getParagraphDirection(i2) != 1) {
            z = false;
        }
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i2);
        if (j.a(paragraphAlignment.name(), "ALIGN_RIGHT")) {
            return ExplicitLayoutAlignment.RIGHT;
        }
        if (j.a(paragraphAlignment.name(), "ALIGN_LEFT")) {
            return ExplicitLayoutAlignment.LEFT;
        }
        if (paragraphAlignment == Layout.Alignment.ALIGN_CENTER) {
            return ExplicitLayoutAlignment.CENTER;
        }
        if (z && paragraphAlignment == Layout.Alignment.ALIGN_NORMAL) {
            return ExplicitLayoutAlignment.LEFT;
        }
        if (z && paragraphAlignment == Layout.Alignment.ALIGN_OPPOSITE) {
            return ExplicitLayoutAlignment.RIGHT;
        }
        if (paragraphAlignment == Layout.Alignment.ALIGN_NORMAL) {
            return ExplicitLayoutAlignment.RIGHT;
        }
        return ExplicitLayoutAlignment.LEFT;
    }
}
