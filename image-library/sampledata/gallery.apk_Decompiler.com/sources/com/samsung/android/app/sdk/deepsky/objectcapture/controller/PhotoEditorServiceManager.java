package com.samsung.android.app.sdk.deepsky.objectcapture.controller;

import Ad.f;
import Ae.b;
import L2.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.StickerCallbackListener;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;

@Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0007*\u0001.\b\u0000\u0018\u0000 22\u00020\u0001:\u000232B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ'\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0013J!\u0010\u0017\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r¢\u0006\u0004\b\u001f\u0010\u0018J\u0015\u0010\"\u001a\u00020\b2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010\u0015\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010+R\u0016\u0010\u0016\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010+R\u0018\u0010,\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020(8\u0002X\u0004¢\u0006\u0006\n\u0004\b1\u0010*¨\u00064"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "message", "Lme/x;", "showToast", "(Ljava/lang/CharSequence;)V", "Ljava/io/File;", "file", "", "authority", "Landroid/net/Uri;", "getUriAndProvidePermissionStickerDB", "(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)Landroid/net/Uri;", "bindService", "()V", "unbindService", "authorityForEditor", "authorityForSticker", "setAuthorities", "(Ljava/lang/String;Ljava/lang/String;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "listener", "setPhotoEditorCallbackListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;)V", "clippedImagePath", "originalFilePath", "editClippedImage", "Landroid/graphics/Bitmap;", "bitmap", "insertClippedImageToDB", "(Landroid/graphics/Bitmap;)V", "Landroid/content/Context;", "", "isBound", "Z", "Landroid/os/Messenger;", "serverMessenger", "Landroid/os/Messenger;", "Ljava/lang/String;", "photoEditorCallbackListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "com/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager$connection$1", "connection", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager$connection$1;", "clientMessenger", "Companion", "ClientIncomingHandler", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PhotoEditorServiceManager {
    public static final Companion Companion = new Companion((e) null);
    public static final int MAX_STICKER_SIZE = 100;
    public static final String TAG = "PhotoServiceManager";
    private String authorityForEditor = "";
    private String authorityForSticker = "";
    private final Messenger clientMessenger = new Messenger(new ClientIncomingHandler(new f(3, (Object) this)));
    private final PhotoEditorServiceManager$connection$1 connection = new PhotoEditorServiceManager$connection$1(this);
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isBound;
    private StickerCallbackListener photoEditorCallbackListener;
    /* access modifiers changed from: private */
    public Messenger serverMessenger;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\t\u0010\nR \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager$ClientIncomingHandler;", "Landroid/os/Handler;", "Lkotlin/Function1;", "Landroid/os/Message;", "Lme/x;", "consumer", "<init>", "(LAe/b;)V", "msg", "handleMessage", "(Landroid/os/Message;)V", "LAe/b;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ClientIncomingHandler extends Handler {
        private final b consumer;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ClientIncomingHandler(b bVar) {
            super(Looper.getMainLooper());
            j.e(bVar, "consumer");
            this.consumer = bVar;
        }

        public void handleMessage(Message message) {
            j.e(message, "msg");
            this.consumer.invoke(message);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager$Companion;", "", "<init>", "()V", "MAX_STICKER_SIZE", "", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public PhotoEditorServiceManager(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: private */
    public static final x clientMessenger$lambda$0(PhotoEditorServiceManager photoEditorServiceManager, Message message) {
        j.e(message, "msg");
        LibLogger.d(TAG, "received from service, " + message);
        if (message.what == 1001) {
            Bundle data = message.getData();
            boolean z = data.getBoolean(ServiceManagerUtil.KEY_RESPONSE_DATA);
            if (data.getBoolean(ServiceManagerUtil.KEY_IS_STICKER_LIMIT_EXCEEDED)) {
                LibLogger.e(TAG, "Stickers maximum limit reached.");
                String quantityString = photoEditorServiceManager.context.getResources().getQuantityString(R.plurals.object_capture_toast_could_not_add_more_than_n_stickers, 100, new Object[]{100});
                j.d(quantityString, "getQuantityString(...)");
                photoEditorServiceManager.showToast(quantityString);
                StickerCallbackListener stickerCallbackListener = photoEditorServiceManager.photoEditorCallbackListener;
                if (stickerCallbackListener != null) {
                    stickerCallbackListener.error();
                }
            } else {
                LibLogger.i(TAG, "Image insertion " + z);
                if (z) {
                    String string = photoEditorServiceManager.context.getString(R.string.object_capture_toast_sticker_saved);
                    j.d(string, "getString(...)");
                    photoEditorServiceManager.showToast(string);
                    StickerCallbackListener stickerCallbackListener2 = photoEditorServiceManager.photoEditorCallbackListener;
                    if (stickerCallbackListener2 != null) {
                        stickerCallbackListener2.success();
                    }
                } else {
                    String string2 = photoEditorServiceManager.context.getString(R.string.object_capture_toast_could_not_save_sticker);
                    j.d(string2, "getString(...)");
                    photoEditorServiceManager.showToast(string2);
                    StickerCallbackListener stickerCallbackListener3 = photoEditorServiceManager.photoEditorCallbackListener;
                    if (stickerCallbackListener3 != null) {
                        stickerCallbackListener3.fail();
                    }
                }
            }
        }
        return x.f4917a;
    }

    private final Uri getUriAndProvidePermissionStickerDB(Context context2, File file, String str) {
        Uri uriForFile = FileProvider.getUriForFile(context2, str, file);
        context2.grantUriPermission(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, uriForFile, 3);
        j.b(uriForFile);
        return uriForFile;
    }

    private final void showToast(CharSequence charSequence) {
        Toast.makeText(this.context, charSequence, 0).show();
    }

    public final void bindService() {
        Object obj;
        if (!this.isBound) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, ServiceManagerUtil.PHOTO_EDIT_SERVICE_CLASS_NAME));
                intent.setFlags(3);
                this.context.bindService(intent, this.connection, 1);
                this.isBound = true;
                obj = x.f4917a;
            } catch (Throwable th) {
                obj = a.l(th);
            }
            Throwable a7 = k.a(obj);
            if (a7 != null) {
                LibLogger.e(TAG, "bindService", a7);
            }
        }
    }

    public final void editClippedImage(String str, String str2) {
        j.e(str, "clippedImagePath");
        j.e(str2, ServiceManagerUtil.PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY);
        Uri uriAndProvidePermissionStickerDB = getUriAndProvidePermissionStickerDB(this.context, new File(str), this.authorityForEditor);
        String imageClipperDirectoryPath = ServiceManagerUtil.INSTANCE.getImageClipperDirectoryPath();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, ServiceManagerUtil.PHOTO_EDIT_ACTIVITY_CLASS_NAME));
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_SERVICE_KEY, ServiceManagerUtil.PHOTO_EDIT_EXTRA_SERVICE_VALUE);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_DEEP_SKY_KEY, true);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FILE_PATH_KEY, uriAndProvidePermissionStickerDB);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY, str2);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_SAVE_DIR_KEY, imageClipperDirectoryPath);
        intent.addFlags(3);
        this.context.startActivity(intent);
    }

    public final void insertClippedImageToDB(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        String imageClipperFilePath = ServiceManagerUtil.INSTANCE.getImageClipperFilePath(bitmap, this.context);
        Object obj = null;
        Message obtain = Message.obtain((Handler) null, 1002);
        Uri uriAndProvidePermissionStickerDB = getUriAndProvidePermissionStickerDB(this.context, new File(imageClipperFilePath), this.authorityForSticker);
        Bundle bundle = new Bundle();
        bundle.putString(ServiceManagerUtil.KEY_CLIPPED_IMAGE_FILE_PATH, uriAndProvidePermissionStickerDB.toString());
        obtain.replyTo = this.clientMessenger;
        obtain.setData(bundle);
        try {
            Messenger messenger = this.serverMessenger;
            if (messenger != null) {
                messenger.send(obtain);
                obj = x.f4917a;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null && (a7 instanceof RemoteException)) {
            LibLogger.e(TAG, "insertClippedImageToDB, RemoteException");
        }
    }

    public final void setAuthorities(String str, String str2) {
        if (str != null) {
            this.authorityForEditor = str;
        }
        if (str2 != null) {
            this.authorityForSticker = str2;
        }
    }

    public final void setPhotoEditorCallbackListener(StickerCallbackListener stickerCallbackListener) {
        this.photoEditorCallbackListener = stickerCallbackListener;
    }

    public final void unbindService() {
        Object obj;
        if (this.isBound) {
            try {
                LibLogger.i(TAG, "PhotoEditor service binding is unbound.");
                this.context.unbindService(this.connection);
                obj = x.f4917a;
            } catch (Throwable th) {
                obj = a.l(th);
            }
            Throwable a7 = k.a(obj);
            if (a7 != null) {
                LibLogger.e(TAG, "unbindService", a7);
            }
            this.isBound = false;
        }
    }
}
