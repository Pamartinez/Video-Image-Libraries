package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "com.samsung.android.sdk.moneta.basicdomain.service.MyProfileServiceImpl", f = "MyProfileServiceImpl.kt", l = {120, 221}, m = "getMyFamily")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileServiceImpl$getMyFamily$5 extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MyProfileServiceImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MyProfileServiceImpl$getMyFamily$5(MyProfileServiceImpl myProfileServiceImpl, C1227c cVar) {
        super(cVar);
        this.this$0 = myProfileServiceImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getMyFamily((RelationShip) null, (PersonType) null, this);
    }
}
