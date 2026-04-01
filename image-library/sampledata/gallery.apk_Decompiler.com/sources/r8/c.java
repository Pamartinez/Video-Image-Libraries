package r8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.SecMpMethodMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2703a;
    public final /* synthetic */ SecMpMethodMap b;

    public /* synthetic */ c(SecMpMethodMap secMpMethodMap, int i2) {
        this.f2703a = i2;
        this.b = secMpMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2703a;
        SecMpMethodMap secMpMethodMap = this.b;
        switch (i2) {
            case 0:
                return secMpMethodMap.lambda$init$18(queryParams);
            case 1:
                return secMpMethodMap.lambda$init$19(queryParams);
            case 2:
                return secMpMethodMap.lambda$init$102(queryParams);
            case 3:
                return secMpMethodMap.lambda$init$1(queryParams);
            case 4:
                return secMpMethodMap.lambda$init$20(queryParams);
            case 5:
                return secMpMethodMap.lambda$init$21(queryParams);
            case 6:
                return secMpMethodMap.lambda$init$22(queryParams);
            case 7:
                return secMpMethodMap.lambda$init$23(queryParams);
            case 8:
                return secMpMethodMap.lambda$init$24(queryParams);
            case 9:
                return secMpMethodMap.lambda$init$25(queryParams);
            case 10:
                return secMpMethodMap.lambda$init$26(queryParams);
            case 11:
                return secMpMethodMap.lambda$init$27(queryParams);
            case 12:
                return secMpMethodMap.lambda$init$28(queryParams);
            case 13:
                return secMpMethodMap.lambda$init$103(queryParams);
            case 14:
                return secMpMethodMap.lambda$init$29(queryParams);
            case 15:
                return secMpMethodMap.lambda$init$2(queryParams);
            case 16:
                return secMpMethodMap.lambda$init$30(queryParams);
            case 17:
                return secMpMethodMap.lambda$init$31(queryParams);
            case 18:
                return secMpMethodMap.lambda$init$32(queryParams);
            case 19:
                return secMpMethodMap.lambda$init$33(queryParams);
            case 20:
                return secMpMethodMap.lambda$init$34(queryParams);
            case 21:
                return secMpMethodMap.lambda$init$35(queryParams);
            case 22:
                return secMpMethodMap.lambda$init$36(queryParams);
            case 23:
                return secMpMethodMap.lambda$init$37(queryParams);
            case 24:
                return secMpMethodMap.lambda$init$104(queryParams);
            case 25:
                return secMpMethodMap.lambda$init$38(queryParams);
            case 26:
                return secMpMethodMap.lambda$init$39(queryParams);
            case 27:
                return secMpMethodMap.lambda$init$3(queryParams);
            case 28:
                return secMpMethodMap.lambda$init$40(queryParams);
            default:
                return secMpMethodMap.lambda$init$41(queryParams);
        }
    }
}
