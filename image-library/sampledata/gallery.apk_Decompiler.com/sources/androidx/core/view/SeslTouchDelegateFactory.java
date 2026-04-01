package androidx.core.view;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.SeslTouchTargetDelegate;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslTouchDelegateFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Strategy {
        SeslTouchTargetDelegate.ExtraInsets getExtraInsets(Rect rect, Rect rect2, Rect rect3);
    }

    public static SeslTouchTargetDelegate.Builder make(LinearLayout linearLayout, List<View> list) {
        b bVar;
        if (list.size() == 0) {
            return null;
        }
        int height = linearLayout.getHeight();
        int width = linearLayout.getWidth();
        int i2 = 0;
        Rect rect = new Rect(0, 0, width, height);
        ArrayList arrayList = new ArrayList();
        for (View calculateViewBounds : list) {
            arrayList.add(SeslTouchTargetDelegate.calculateViewBounds(linearLayout, calculateViewBounds));
        }
        if (linearLayout.getOrientation() == 0) {
            bVar = new b(rect, 0);
        } else {
            bVar = new b(rect, 1);
        }
        Rect rect2 = (Rect) C0212a.i(arrayList, 1);
        arrayList.add(new Rect(Math.max(0, width - rect2.right) + width, Math.max(0, height - rect2.bottom) + height, width, height));
        Rect rect3 = new Rect(0, 0, 0, 0);
        SeslTouchTargetDelegate.Builder builder = new SeslTouchTargetDelegate.Builder(linearLayout);
        while (i2 < list.size()) {
            Rect rect4 = (Rect) arrayList.get(i2);
            int i7 = i2 + 1;
            builder.addDelegateView(list.get(i2), bVar.getExtraInsets(rect3, rect4, (Rect) arrayList.get(i7)));
            rect3 = rect4;
            i2 = i7;
        }
        return builder;
    }
}
