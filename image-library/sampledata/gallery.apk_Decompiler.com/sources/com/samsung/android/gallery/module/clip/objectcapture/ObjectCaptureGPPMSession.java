package com.samsung.android.gallery.module.clip.objectcapture;

import K5.a;
import M4.d;
import M9.o;
import N8.c;
import O3.l;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectCaptureDrawHelper;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.GPPMListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemRetryLoader;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureGPPMSession implements GPPMListener {
    private Consumer<Uri> mCompleteCallback;
    private WeakReference<ObjectCaptureDrawHelper> mDrawHelperRef;
    private Runnable mServiceBoundCallback;

    private boolean isValidMediaId(String str) {
        if (UnsafeCast.toInt(str, 0) > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTaskCompleted$0(Uri uri, MediaItem mediaItem) {
        sendCompleteCallback(uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTaskCompleted$1(MediaItem mediaItem) {
        sendCompleteCallback((Uri) null);
    }

    private void sendCompleteCallback(Uri uri) {
        Optional.ofNullable(this.mCompleteCallback).ifPresent(new c(uri, 0));
        this.mCompleteCallback = null;
    }

    public void connect(ObjectCaptureDrawHelper objectCaptureDrawHelper) {
        boolean z;
        if (objectCaptureDrawHelper != null) {
            z = true;
        } else {
            z = false;
        }
        try {
            Log.d("ObjectCaptureGPPMSession", "connect", Boolean.valueOf(z));
            if (objectCaptureDrawHelper != null) {
                this.mDrawHelperRef = new WeakReference<>(objectCaptureDrawHelper);
                objectCaptureDrawHelper.connectGPPMSession(this);
            }
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ObjectCaptureGPPMSession", "connect failed", e.getMessage());
        }
    }

    public void disconnect() {
        try {
            Log.d("ObjectCaptureGPPMSession", "disconnect", this.mDrawHelperRef);
            WeakReference<ObjectCaptureDrawHelper> weakReference = this.mDrawHelperRef;
            if (weakReference != null) {
                this.mDrawHelperRef.clear();
                Optional.ofNullable(weakReference.get()).ifPresent(new d(27));
            }
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ObjectCaptureGPPMSession", "disconnect failed", e.getMessage());
        }
    }

    public void onServiceBound() {
        Log.d("ObjectCaptureGPPMSession", "bounded");
        Optional.ofNullable(this.mServiceBoundCallback).ifPresent(new l(0));
        this.mServiceBoundCallback = null;
    }

    public void onTaskCompleted(Message message) {
        o oVar;
        try {
            Bundle data = message.getData();
            if (data != null) {
                if (!data.isEmpty()) {
                    String string = data.getString(IParameterKey.DST_PATH);
                    String string2 = data.getString("media_id");
                    Log.d("ObjectCaptureGPPMSession", "completed", string2, Logger.getEncodedString(string));
                    if (FileUtils.exists(string) && isValidMediaId(string2)) {
                        Uri withAppendedPath = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, string2);
                        new MediaItemRetryLoader(10, 30).setSuccessCallback(new N3.c(2, this, withAppendedPath)).setFailCallback(new a(16, this)).getMediaItemFromUri(withAppendedPath);
                    }
                    oVar = new o(6, this);
                    DeepSkyHelper.post(oVar);
                }
            }
            Log.e((CharSequence) "ObjectCaptureGPPMSession", "invalid bundle", Logger.toString(data));
            oVar = new o(6, this);
        } catch (Error | Exception e) {
            Log.e((CharSequence) "ObjectCaptureGPPMSession", "complete failed", e.getMessage());
            oVar = new o(6, this);
        } catch (Throwable th) {
            DeepSkyHelper.post(new o(6, this));
            throw th;
        }
        DeepSkyHelper.post(oVar);
    }

    public ObjectCaptureGPPMSession setCompleteCallback(Consumer<Uri> consumer) {
        this.mCompleteCallback = consumer;
        return this;
    }

    public ObjectCaptureGPPMSession setServiceBoundCallback(Runnable runnable) {
        this.mServiceBoundCallback = runnable;
        return this;
    }
}
