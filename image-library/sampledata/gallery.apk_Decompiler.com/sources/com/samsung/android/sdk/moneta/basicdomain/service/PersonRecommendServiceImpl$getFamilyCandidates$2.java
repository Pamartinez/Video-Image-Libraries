package com.samsung.android.sdk.moneta.basicdomain.service;

import com.samsung.android.sdk.moneta.basicdomain.entity.PersonType;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@C1273e(c = "com.samsung.android.sdk.moneta.basicdomain.service.PersonRecommendServiceImpl", f = "PersonRecommendServiceImpl.kt", l = {78, 111}, m = "getFamilyCandidates")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonRecommendServiceImpl$getFamilyCandidates$2 extends C1271c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PersonRecommendServiceImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PersonRecommendServiceImpl$getFamilyCandidates$2(PersonRecommendServiceImpl personRecommendServiceImpl, C1227c cVar) {
        super(cVar);
        this.this$0 = personRecommendServiceImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getFamilyCandidates((RelationShip) null, (PersonType) null, this);
    }
}
