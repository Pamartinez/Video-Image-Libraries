package com.samsung.android.gallery.module.viewer;

import A.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.mimage.photoretouching.service.IFinishService;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoEditorManager {
    private final Context mAppContext;
    private final Handler mHandler = new PhotoEditorServiceHandler(this);
    private final AtomicBoolean mLaunched = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public IFinishService mPhotoEditorService;
    private final ServiceConnection mPhotoEditorServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("PhotoEditorManager", "PhotoEditor#IFinishService connected");
            PhotoEditorManager.this.mPhotoEditorService = IFinishService.Stub.asInterface(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("PhotoEditorManager", "PhotoEditor#IFinishService disconnected");
            PhotoEditorManager.this.mPhotoEditorService = null;
        }
    };
    private boolean mServiceBound;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhotoEditorServiceHandler extends Handler {
        private final WeakReference<PhotoEditorManager> mManager;

        public PhotoEditorServiceHandler(PhotoEditorManager photoEditorManager) {
            this.mManager = new WeakReference<>(photoEditorManager);
        }

        public void handleMessage(Message message) {
            PhotoEditorManager photoEditorManager = this.mManager.get();
            if (photoEditorManager == null) {
                Log.e("PhotoEditorManager", "handleMessage failed. null manager {what=" + message.what + "}");
                return;
            }
            int i2 = message.what;
            if (i2 == 0) {
                Log.d("PhotoEditorManager", "BIND_PHOTO_EDITOR_SERVICE");
                photoEditorManager.bindService();
            } else if (i2 == 1) {
                Log.d("PhotoEditorManager", "UNBIND_PHOTO_EDITOR_SERVICE");
                hideImage(photoEditorManager, 0);
                photoEditorManager.unbindService();
            } else if (i2 == 2) {
                Log.d("PhotoEditorManager", "HIDE_PHOTO_EDITOR_IMAGE");
                hideImage(photoEditorManager, 500);
            }
        }

        public void hideImage(PhotoEditorManager photoEditorManager, int i2) {
            if (photoEditorManager.mPhotoEditorService != null) {
                try {
                    Log.d("PhotoEditorManager", "hideImage {" + i2 + "}");
                    photoEditorManager.mPhotoEditorService.hideImage(i2);
                } catch (Error | Exception e) {
                    a.z(e, new StringBuilder("hideImage failed. e="), "PhotoEditorManager");
                }
            }
        }
    }

    public PhotoEditorManager(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    /* access modifiers changed from: private */
    public void bindService() {
        if (this.mPhotoEditorService == null || !this.mServiceBound) {
            try {
                Log.d("PhotoEditorManager", "bindService");
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, "com.sec.android.mimage.photoretouching.service.FinishService"));
                if (this.mAppContext.bindService(intent, this.mPhotoEditorServiceConnection, 1)) {
                    this.mServiceBound = true;
                    return;
                }
                throw new UnsupportedOperationException("not supported service");
            } catch (Exception e) {
                a.s(e, new StringBuilder("bindService failed. e="), "PhotoEditorManager");
            }
        }
    }

    /* access modifiers changed from: private */
    public void unbindService() {
        if (this.mServiceBound) {
            try {
                Log.d("PhotoEditorManager", "unbindService");
                this.mAppContext.unbindService(this.mPhotoEditorServiceConnection);
            } catch (Exception e) {
                a.s(e, new StringBuilder("unbindService failed. e="), "PhotoEditorManager");
            }
            this.mServiceBound = false;
        }
    }

    public void create() {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(0));
        this.mLaunched.set(true);
    }

    public void destroy() {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
        this.mLaunched.set(false);
    }

    public void hideImage() {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(2));
        this.mLaunched.set(false);
    }

    public boolean isLaunched() {
        return this.mLaunched.get();
    }

    public boolean isServiceBound() {
        return this.mServiceBound;
    }
}
