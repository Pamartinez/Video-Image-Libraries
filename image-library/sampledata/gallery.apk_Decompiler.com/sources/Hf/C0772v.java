package Hf;

import Ae.b;
import a.C0068a;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import ee.Z;
import java.util.Comparator;
import kotlin.jvm.internal.j;

/* renamed from: Hf.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0772v implements Comparator {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0772v(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final int compare(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                C0774x xVar = (C0774x) obj;
                b bVar = (b) this.e;
                j.b(xVar);
                String obj3 = bVar.invoke(xVar).toString();
                C0774x xVar2 = (C0774x) obj2;
                j.b(xVar2);
                return C0068a.l(obj3, bVar.invoke(xVar2).toString());
            case 1:
                MaterialButton materialButton = (MaterialButton) obj;
                MaterialButton materialButton2 = (MaterialButton) obj2;
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) this.e;
                int compareTo = Boolean.valueOf(materialButton.f1432o).compareTo(Boolean.valueOf(materialButton2.f1432o));
                if (compareTo != 0) {
                    return compareTo;
                }
                int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                return Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton)).compareTo(Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton2)));
            default:
                Z z = (Z) this.e;
                int b = z.b(obj) - z.b(obj2);
                if (b != 0) {
                    return b;
                }
                return obj.getClass().getName().compareTo(obj2.getClass().getName());
        }
    }
}
