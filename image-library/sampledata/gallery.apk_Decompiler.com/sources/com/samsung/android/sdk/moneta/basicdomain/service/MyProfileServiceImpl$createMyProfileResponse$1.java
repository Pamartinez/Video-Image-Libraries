package com.samsung.android.sdk.moneta.basicdomain.service;

import L2.a;
import com.samsung.android.sdk.moneta.basicdomain.entity.MyProfile;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyProfileWrapper;
import com.samsung.android.sdk.moneta.basicdomain.entity.wrapper.v1.MyProfileWrapperKt;
import com.samsung.android.sdk.moneta.basicdomain.response.IMyProfileResponse;
import com.samsung.android.sdk.moneta.common.Logger;
import i.C0212a;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$createMyProfileResponse$1", "Lcom/samsung/android/sdk/moneta/basicdomain/response/IMyProfileResponse$Stub;", "Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyProfileWrapper;", "myProfileWrapper", "Lme/x;", "onResponse", "(Lcom/samsung/android/sdk/moneta/basicdomain/entity/wrapper/v1/MyProfileWrapper;)V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileServiceImpl$createMyProfileResponse$1 extends IMyProfileResponse.Stub {
    final /* synthetic */ C1227c $continuation;

    public MyProfileServiceImpl$createMyProfileResponse$1(C1227c cVar) {
        this.$continuation = cVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.e$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", C0212a.k(i2, "[createMyProfileResponse-onError] error code ", " message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    public void onResponse(MyProfileWrapper myProfileWrapper) {
        MyProfile myProfile;
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[createMyProfileResponse-onResponse] myProfile");
        C1227c cVar = this.$continuation;
        if (myProfileWrapper != null) {
            myProfile = MyProfileWrapperKt.toMyProfile(myProfileWrapper);
        } else {
            myProfile = null;
        }
        cVar.resumeWith(myProfile);
    }
}
