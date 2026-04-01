package com.samsung.android.gallery.module.editor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.sec.android.mimage.photoretouching.service.IPresetService;
import com.sec.android.mimage.photoretouching.service.IPresetServiceCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PresetServiceBinder implements ServiceConnection {
    private static final PresetServiceBinder sInstance = new PresetServiceBinder();
    private WeakReference<Context> mContext;
    private final AtomicBoolean mReference = new AtomicBoolean(false);
    private IPresetService mService;
    private final ArrayList<Uri> mUriList = new ArrayList<>();

    private PresetServiceBinder() {
    }

    private void applyPreset(ArrayList<Uri> arrayList) {
        try {
            Log.d("PresetServiceBinder", "apply preset started", Integer.valueOf(arrayList.size()));
            final long currentTimeMillis = System.currentTimeMillis();
            this.mService.applyPreset(arrayList, new IPresetServiceCallback.Stub() {
                public void onComplete() {
                    Log.d("PresetServiceBinder", "apply preset completed" + Logger.vt(currentTimeMillis));
                    BlackboardUtils.forceRefreshPicturesDataGlobal();
                    PresetServiceBinder.this.unbindService();
                }
            });
        } catch (Exception e) {
            Log.e((CharSequence) "PresetServiceBinder", "apply preset failed", (Throwable) e);
        }
    }

    private void bindService() {
        Context context;
        try {
            WeakReference<Context> weakReference = this.mContext;
            if (weakReference != null) {
                context = weakReference.get();
            } else {
                context = null;
            }
            if (context != null) {
                Log.d("PresetServiceBinder", "bindService");
                Intent intent = new Intent();
                intent.setClassName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.service.PresetService");
                if (context.bindService(intent, this, 1)) {
                    this.mReference.set(true);
                    return;
                }
                Log.e("PresetServiceBinder", "bind service returns false");
                unbindService();
            }
        } catch (Exception e) {
            Log.e((CharSequence) "PresetServiceBinder", "bind service failed", (Throwable) e);
        }
    }

    public static PresetServiceBinder getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public void unbindService() {
        Context context;
        try {
            WeakReference<Context> weakReference = this.mContext;
            if (weakReference != null) {
                context = weakReference.get();
            } else {
                context = null;
            }
            if (context == null) {
                return;
            }
            if (this.mReference.get()) {
                Log.d("PresetServiceBinder", "unbindService");
                this.mReference.set(false);
                context.unbindService(this);
                this.mService = null;
                this.mUriList.clear();
            }
        } catch (Exception e) {
            Log.e((CharSequence) "PresetServiceBinder", "unbind service failed", (Throwable) e);
        }
    }

    public void onResume() {
        if (this.mReference.get()) {
            Log.d("PresetServiceBinder", "onResume");
            try {
                this.mService.resumePreset();
            } catch (Exception e) {
                Log.e((CharSequence) "PresetServiceBinder", "resume preset failed", (Throwable) e);
            }
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d("PresetServiceBinder", "onServiceConnected");
        this.mService = IPresetService.Stub.asInterface(iBinder);
        applyPreset(this.mUriList);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.d("PresetServiceBinder", "onServiceDisconnected");
        this.mReference.set(false);
    }

    public void onStart(Context context, ArrayList<Uri> arrayList) {
        if (this.mReference.get()) {
            Log.d("PresetServiceBinder", "already running");
            applyPreset(arrayList);
            return;
        }
        Log.d("PresetServiceBinder", "onStart");
        this.mContext = new WeakReference<>(context);
        this.mUriList.addAll(arrayList);
        bindService();
    }
}
