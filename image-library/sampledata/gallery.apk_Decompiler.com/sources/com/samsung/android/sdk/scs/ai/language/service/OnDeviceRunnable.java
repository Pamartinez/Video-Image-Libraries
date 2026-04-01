package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sivs.ai.sdkcommon.language.F;
import java.util.Map;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDeviceRunnable extends TaskRunnable<Result> {
    private static final String TAG = "OnDeviceRunnable";
    Map<String, String> authHeader;
    OnDeviceServiceExecutor mServiceExecutor;

    public OnDeviceRunnable(OnDeviceServiceExecutor onDeviceServiceExecutor) {
        this.mServiceExecutor = onDeviceServiceExecutor;
    }

    public void execute() {
        Parcel obtain;
        try {
            F f = (F) this.mServiceExecutor.getService();
            f.getClass();
            obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.samsung.android.sivs.ai.sdkcommon.language.IOnDeviceService");
            f.f1681a.transact(1, obtain, (Parcel) null, 1);
            obtain.recycle();
            this.mSource.setResult(new Result(new Bundle()));
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public Map<String, String> getAuthHeader() {
        return this.authHeader;
    }

    public String getFeatureName() {
        return Feature.FEATURE_SIVS_CONFIGURATION;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.authHeader = new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context);
    }
}
