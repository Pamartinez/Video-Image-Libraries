package com.samsung.android.gallery.database.dal.cmh;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements CursorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2620a;
    public final /* synthetic */ CmhMethodMap b;

    public /* synthetic */ a(CmhMethodMap cmhMethodMap, int i2) {
        this.f2620a = i2;
        this.b = cmhMethodMap;
    }

    public final Cursor query(QueryParams queryParams) {
        int i2 = this.f2620a;
        CmhMethodMap cmhMethodMap = this.b;
        switch (i2) {
            case 0:
                return cmhMethodMap.lambda$init$0(queryParams);
            case 1:
                return cmhMethodMap.lambda$init$2(queryParams);
            case 2:
                return cmhMethodMap.lambda$init$3(queryParams);
            case 3:
                return cmhMethodMap.lambda$init$4(queryParams);
            case 4:
                return cmhMethodMap.lambda$init$5(queryParams);
            case 5:
                return cmhMethodMap.lambda$init$6(queryParams);
            case 6:
                return cmhMethodMap.lambda$init$7(queryParams);
            case 7:
                return cmhMethodMap.lambda$init$8(queryParams);
            case 8:
                return cmhMethodMap.lambda$init$9(queryParams);
            case 9:
                return cmhMethodMap.lambda$init$10(queryParams);
            case 10:
                return cmhMethodMap.lambda$init$11(queryParams);
            case 11:
                return cmhMethodMap.lambda$init$12(queryParams);
            case 12:
                return cmhMethodMap.lambda$init$13(queryParams);
            case 13:
                return cmhMethodMap.lambda$init$14(queryParams);
            case 14:
                return cmhMethodMap.lambda$init$15(queryParams);
            case 15:
                return cmhMethodMap.lambda$init$16(queryParams);
            case 16:
                return cmhMethodMap.lambda$init$17(queryParams);
            default:
                return cmhMethodMap.lambda$init$1(queryParams);
        }
    }
}
