package com.samsung.android.sdk.moneta.basicdomain.service;

import L2.a;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapper;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.PersonWrapperKt;
import com.samsung.android.sdk.moneta.basicdomain.response.IPersonListResponse;
import com.samsung.android.sdk.moneta.common.Logger;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;
import qe.C1227c;

@Metadata(d1 = {"\u0000=\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\f\u0010\rR:\u0010\u0013\u001a(\u0012$\u0012\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010 \u0012*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010\u000f0\u000f0\u000e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createPersonListResponse$1", "Lcom/samsung/android/sdk/moneta/basicdomain/response/IPersonListResponse$Stub;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/PersonWrapper;", "response", "Lme/x;", "onResponse", "(Ljava/util/List;)V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "Ljava/lang/ref/WeakReference;", "Lqe/c;", "", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/Person;", "kotlin.jvm.PlatformType", "continuationRef", "Ljava/lang/ref/WeakReference;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileServiceImpl$createPersonListResponse$1 extends IPersonListResponse.Stub {
    private final WeakReference<C1227c> continuationRef;

    public MyProfileServiceImpl$createPersonListResponse$1(C1227c cVar) {
        this.continuationRef = new WeakReference<>(cVar);
    }

    public void onError(int i2, String str) {
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", C0212a.k(i2, "[createPersonListResponse-onError] error code ", " message: ", str));
        C1227c cVar = this.continuationRef.get();
        if (cVar != null) {
            cVar.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
        } else {
            logger.e$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[createPersonListResponse-onError] No ref to Continuation");
        }
    }

    public void onResponse(List<PersonWrapper> list) {
        j.e(list, Contract.RESPONSE);
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[createPersonListResponse-onResponse] response size " + list.size());
        C1227c cVar = this.continuationRef.get();
        if (cVar != null) {
            Iterable<PersonWrapper> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (PersonWrapper person : iterable) {
                arrayList.add(PersonWrapperKt.toPerson(person));
            }
            cVar.resumeWith(arrayList);
            return;
        }
        logger.e$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[createPersonListResponse-onResponse] No ref to Continuation");
    }
}
