package r8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.SecMpMethodMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2704a;
    public final /* synthetic */ SecMpMethodMap b;

    public /* synthetic */ d(SecMpMethodMap secMpMethodMap, int i2) {
        this.f2704a = i2;
        this.b = secMpMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2704a;
        SecMpMethodMap secMpMethodMap = this.b;
        switch (i2) {
            case 0:
                return secMpMethodMap.lambda$init$42(queryParams);
            case 1:
                return secMpMethodMap.lambda$init$43(queryParams);
            case 2:
                return secMpMethodMap.lambda$init$44(queryParams);
            case 3:
                return secMpMethodMap.lambda$init$45(queryParams);
            case 4:
                return secMpMethodMap.lambda$init$46(queryParams);
            case 5:
                return secMpMethodMap.lambda$init$105(queryParams);
            case 6:
                return secMpMethodMap.lambda$init$47(queryParams);
            case 7:
                return secMpMethodMap.lambda$init$48(queryParams);
            case 8:
                return secMpMethodMap.lambda$init$49(queryParams);
            case 9:
                return secMpMethodMap.lambda$init$4(queryParams);
            case 10:
                return secMpMethodMap.lambda$init$50(queryParams);
            case 11:
                return secMpMethodMap.lambda$init$51(queryParams);
            case 12:
                return secMpMethodMap.lambda$init$52(queryParams);
            case 13:
                return secMpMethodMap.lambda$init$53(queryParams);
            case 14:
                return secMpMethodMap.lambda$init$54(queryParams);
            case 15:
                return secMpMethodMap.lambda$init$55(queryParams);
            case 16:
                return secMpMethodMap.lambda$init$106(queryParams);
            case 17:
                return secMpMethodMap.lambda$init$56(queryParams);
            case 18:
                return secMpMethodMap.lambda$init$57(queryParams);
            case 19:
                return secMpMethodMap.lambda$init$58(queryParams);
            case 20:
                return secMpMethodMap.lambda$init$59(queryParams);
            case 21:
                return secMpMethodMap.lambda$init$5(queryParams);
            case 22:
                return secMpMethodMap.lambda$init$60(queryParams);
            case 23:
                return secMpMethodMap.lambda$init$61(queryParams);
            case 24:
                return secMpMethodMap.lambda$init$62(queryParams);
            case 25:
                return secMpMethodMap.lambda$init$63(queryParams);
            case 26:
                return secMpMethodMap.lambda$init$64(queryParams);
            case 27:
                return secMpMethodMap.lambda$init$107(queryParams);
            case 28:
                return secMpMethodMap.lambda$init$65(queryParams);
            default:
                return secMpMethodMap.lambda$init$66(queryParams);
        }
    }
}
