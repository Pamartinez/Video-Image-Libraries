package com.samsung.android.gallery.module.bgm.provider;

import I4.b;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.app.ve.vebgm.IBgmUriProviderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmAidlService implements IBgmProvider {
    protected IProviderCallback mCallback;
    final ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BgmAidlService.this.mService = IBgmUriProviderService.Stub.asInterface(iBinder);
            Optional.ofNullable(BgmAidlService.this.mCallback).ifPresent(new b(11));
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Optional.ofNullable(BgmAidlService.this.mCallback).ifPresent(new b(10));
        }
    };
    /* access modifiers changed from: private */
    public IBgmUriProviderService mService;

    public void bind() {
        Context appContext = AppResources.getAppContext();
        if (appContext != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.sec.android.app.ve.vebgm", "com.sec.android.app.ve.vebgm.service.BgmUriProviderService"));
            appContext.bindService(intent, this.mConnection, 1);
            Log.d("BgmAidlService", "bindService");
            return;
        }
        Log.e("BgmAidlService", "failed");
        Optional.ofNullable(this.mCallback).ifPresent(new b(9));
        release();
    }

    public boolean downloadAllBgm() {
        try {
            IBgmUriProviderService iBgmUriProviderService = this.mService;
            if (iBgmUriProviderService == null) {
                return false;
            }
            iBgmUriProviderService.downloadAllBgms();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean downloadBgm(String str) {
        try {
            IBgmUriProviderService iBgmUriProviderService = this.mService;
            if (iBgmUriProviderService == null) {
                return false;
            }
            iBgmUriProviderService.downloadSingleBgm("com.sec.android.gallery3d", str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getBgmUri(String str) {
        try {
            IBgmUriProviderService iBgmUriProviderService = this.mService;
            if (iBgmUriProviderService != null) {
                return iBgmUriProviderService.arrangeBgmUri("com.sec.android.gallery3d", str);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getDownloadedBgm() {
        try {
            IBgmUriProviderService iBgmUriProviderService = this.mService;
            if (iBgmUriProviderService != null) {
                return iBgmUriProviderService.getBgmDownloadedNames();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public void release() {
        unbind();
        this.mCallback = null;
    }

    public void setCallback(IProviderCallback iProviderCallback) {
        this.mCallback = iProviderCallback;
    }

    public void unbind() {
        if (this.mService != null) {
            this.mService = null;
            Context appContext = AppResources.getAppContext();
            if (appContext != null) {
                appContext.unbindService(this.mConnection);
            }
        }
        Log.d("BgmAidlService", "unbindService");
    }
}
