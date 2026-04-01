package r8;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.SecMpMethodMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2705a;
    public final /* synthetic */ SecMpMethodMap b;

    public /* synthetic */ e(SecMpMethodMap secMpMethodMap, int i2) {
        this.f2705a = i2;
        this.b = secMpMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2705a;
        SecMpMethodMap secMpMethodMap = this.b;
        switch (i2) {
            case 0:
                return secMpMethodMap.lambda$init$67(queryParams);
            case 1:
                return secMpMethodMap.lambda$init$68(queryParams);
            case 2:
                return secMpMethodMap.lambda$init$69(queryParams);
            case 3:
                return secMpMethodMap.lambda$init$6(queryParams);
            case 4:
                return secMpMethodMap.lambda$init$70(queryParams);
            case 5:
                return secMpMethodMap.lambda$init$71(queryParams);
            case 6:
                return secMpMethodMap.lambda$init$72(queryParams);
            case 7:
                return secMpMethodMap.lambda$init$73(queryParams);
            default:
                return secMpMethodMap.lambda$init$108(queryParams);
        }
    }
}
