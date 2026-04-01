package r8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.SecMpMethodMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2701a;
    public final /* synthetic */ SecMpMethodMap b;

    public /* synthetic */ a(SecMpMethodMap secMpMethodMap, int i2) {
        this.f2701a = i2;
        this.b = secMpMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2701a;
        SecMpMethodMap secMpMethodMap = this.b;
        switch (i2) {
            case 0:
                return secMpMethodMap.lambda$init$0(queryParams);
            case 1:
                return secMpMethodMap.lambda$init$74(queryParams);
            case 2:
                return secMpMethodMap.lambda$init$75(queryParams);
            case 3:
                return secMpMethodMap.lambda$init$76(queryParams);
            case 4:
                return secMpMethodMap.lambda$init$77(queryParams);
            case 5:
                return secMpMethodMap.lambda$init$78(queryParams);
            case 6:
                return secMpMethodMap.lambda$init$79(queryParams);
            case 7:
                return secMpMethodMap.lambda$init$7(queryParams);
            case 8:
                return secMpMethodMap.lambda$init$80(queryParams);
            case 9:
                return secMpMethodMap.lambda$init$81(queryParams);
            case 10:
                return secMpMethodMap.lambda$init$82(queryParams);
            case 11:
                return secMpMethodMap.lambda$init$109(queryParams);
            case 12:
                return secMpMethodMap.lambda$init$83(queryParams);
            case 13:
                return secMpMethodMap.lambda$init$84(queryParams);
            case 14:
                return secMpMethodMap.lambda$init$85(queryParams);
            case 15:
                return secMpMethodMap.lambda$init$86(queryParams);
            case 16:
                return secMpMethodMap.lambda$init$87(queryParams);
            case 17:
                return secMpMethodMap.lambda$init$88(queryParams);
            case 18:
                return secMpMethodMap.lambda$init$89(queryParams);
            case 19:
                return secMpMethodMap.lambda$init$8(queryParams);
            case 20:
                return secMpMethodMap.lambda$init$90(queryParams);
            case 21:
                return secMpMethodMap.lambda$init$91(queryParams);
            case 22:
                return secMpMethodMap.lambda$init$10(queryParams);
            case 23:
                return secMpMethodMap.lambda$init$92(queryParams);
            case 24:
                return secMpMethodMap.lambda$init$93(queryParams);
            case 25:
                return secMpMethodMap.lambda$init$94(queryParams);
            case 26:
                return secMpMethodMap.lambda$init$95(queryParams);
            case 27:
                return secMpMethodMap.lambda$init$96(queryParams);
            case 28:
                return secMpMethodMap.lambda$init$97(queryParams);
            default:
                return secMpMethodMap.lambda$init$98(queryParams);
        }
    }
}
