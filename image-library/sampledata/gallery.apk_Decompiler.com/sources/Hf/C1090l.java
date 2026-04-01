package hf;

import java.util.ArrayList;
import java.util.Iterator;
import ne.C1196n;

/* renamed from: hf.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1090l {

    /* renamed from: a  reason: collision with root package name */
    public final C1094p f4591a;
    public final ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final String f4592c;
    public final C1090l d;

    public C1090l(C1094p pVar, ArrayList arrayList, String str) {
        C1094p pVar2;
        C1094p pVar3;
        this.f4591a = pVar;
        this.b = arrayList;
        this.f4592c = str;
        C1090l lVar = null;
        if (str != null) {
            if (pVar != null) {
                pVar2 = pVar.a();
            } else {
                pVar2 = null;
            }
            ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C1094p pVar4 = (C1094p) it.next();
                if (pVar4 != null) {
                    pVar3 = pVar4.a();
                } else {
                    pVar3 = null;
                }
                arrayList2.add(pVar3);
            }
            lVar = new C1090l(pVar2, arrayList2, (String) null);
        }
        this.d = lVar;
    }
}
