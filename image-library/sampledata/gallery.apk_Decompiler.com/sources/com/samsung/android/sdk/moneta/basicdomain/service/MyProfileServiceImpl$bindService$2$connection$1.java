package com.samsung.android.sdk.moneta.basicdomain.service;

import Vf.C0873j;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.samsung.android.sdk.moneta.basicdomain.IBasicDomainService;
import com.samsung.android.sdk.moneta.common.Logger;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/samsung/android/sdk/moneta/basicdomain/service/MyProfileServiceImpl$bindService$2$connection$1", "Landroid/content/ServiceConnection;", "Landroid/content/ComponentName;", "componentName", "Landroid/os/IBinder;", "binder", "Lme/x;", "onServiceConnected", "(Landroid/content/ComponentName;Landroid/os/IBinder;)V", "onServiceDisconnected", "(Landroid/content/ComponentName;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MyProfileServiceImpl$bindService$2$connection$1 implements ServiceConnection {
    final /* synthetic */ C0873j $continuation;
    final /* synthetic */ MyProfileServiceImpl this$0;

    public MyProfileServiceImpl$bindService$2$connection$1(MyProfileServiceImpl myProfileServiceImpl, C0873j jVar) {
        this.this$0 = myProfileServiceImpl;
        this.$continuation = jVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[onServiceConnected] in");
        this.this$0.basicDomainService = IBasicDomainService.Stub.asInterface(iBinder);
        if (this.$continuation.isActive()) {
            this.$continuation.resumeWith(this);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("BasicDomain-BasicDomainServiceImpl", "[onServiceDisconnected] in");
        this.this$0.basicDomainService = null;
        if (this.$continuation.isActive()) {
            this.$continuation.resumeWith((Object) null);
        }
    }
}
