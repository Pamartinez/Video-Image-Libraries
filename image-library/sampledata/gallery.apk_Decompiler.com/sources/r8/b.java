package r8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.SecMpMethodMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2702a;
    public final /* synthetic */ SecMpMethodMap b;

    public /* synthetic */ b(SecMpMethodMap secMpMethodMap, int i2) {
        this.f2702a = i2;
        this.b = secMpMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2702a;
        SecMpMethodMap secMpMethodMap = this.b;
        switch (i2) {
            case 0:
                return secMpMethodMap.lambda$init$99(queryParams);
            case 1:
                return secMpMethodMap.lambda$init$9(queryParams);
            case 2:
                return secMpMethodMap.lambda$init$110(queryParams);
            case 3:
                return secMpMethodMap.lambda$init$111(queryParams);
            case 4:
                return secMpMethodMap.lambda$init$112(queryParams);
            case 5:
                return secMpMethodMap.lambda$init$113(queryParams);
            case 6:
                return secMpMethodMap.lambda$init$114(queryParams);
            case 7:
                return secMpMethodMap.lambda$init$115(queryParams);
            case 8:
                return secMpMethodMap.lambda$init$116(queryParams);
            case 9:
                return secMpMethodMap.lambda$init$117(queryParams);
            case 10:
                return secMpMethodMap.lambda$init$100(queryParams);
            case 11:
                return secMpMethodMap.lambda$init$118(queryParams);
            case 12:
                return secMpMethodMap.lambda$init$119(queryParams);
            case 13:
                return secMpMethodMap.lambda$init$11(queryParams);
            case 14:
                return secMpMethodMap.lambda$init$120(queryParams);
            case 15:
                return secMpMethodMap.lambda$init$121(queryParams);
            case 16:
                return secMpMethodMap.lambda$init$122(queryParams);
            case 17:
                return secMpMethodMap.lambda$init$123(queryParams);
            case 18:
                return secMpMethodMap.lambda$init$124(queryParams);
            case 19:
                return secMpMethodMap.lambda$init$125(queryParams);
            case 20:
                return secMpMethodMap.lambda$init$126(queryParams);
            case 21:
                return secMpMethodMap.lambda$init$101(queryParams);
            case 22:
                return secMpMethodMap.lambda$init$127(queryParams);
            case 23:
                return secMpMethodMap.lambda$init$128(queryParams);
            case 24:
                return secMpMethodMap.lambda$init$12(queryParams);
            case 25:
                return secMpMethodMap.lambda$init$13(queryParams);
            case 26:
                return secMpMethodMap.lambda$init$14(queryParams);
            case 27:
                return secMpMethodMap.lambda$init$15(queryParams);
            case 28:
                return secMpMethodMap.lambda$init$16(queryParams);
            default:
                return secMpMethodMap.lambda$init$17(queryParams);
        }
    }
}
