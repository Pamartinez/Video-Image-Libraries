package com.samsung.android.app.sdk.deepsky.objectcapture.controller;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.objectcapture.common.Rune;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.PopOverUtil;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ7\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b \u0010\u001f¨\u0006\""}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterServiceManager;", "", "Landroid/content/Context;", "context", "", "authForSticker", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "Lme/x;", "init", "()V", "", "isClippedStickerEnabled", "()Z", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "deviceType", "Landroid/graphics/Rect;", "rect", "stickerID", "isAnimatedImage", "sendImageToStickerCenter", "(Landroid/graphics/Bitmap;Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;Landroid/graphics/Rect;Ljava/lang/String;Z)V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Ljava/lang/String;", "getAuthForSticker", "()Ljava/lang/String;", "isStickerCenterEnabled", "Z", "isStickerCenterPopupSupported", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StickerCenterServiceManager {
    public static final Companion Companion = new Companion((e) null);
    private static final int MINIMUM_API_VERSION_POP_OVER_SUPPORT = 15;
    private static final int STICKER_CENTER_VERSION_ENABLE_CLIP_STICKER = 13;
    private static final String TAG = "StickerCenterServiceManager";
    private final String authForSticker;
    private final Context context;
    private boolean isStickerCenterEnabled;
    private boolean isStickerCenterPopupSupported;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterServiceManager$Companion;", "", "<init>", "()V", "TAG", "", "STICKER_CENTER_VERSION_ENABLE_CLIP_STICKER", "", "MINIMUM_API_VERSION_POP_OVER_SUPPORT", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public StickerCenterServiceManager(Context context2, String str) {
        j.e(context2, "context");
        j.e(str, "authForSticker");
        this.context = context2;
        this.authForSticker = str;
    }

    public static /* synthetic */ void sendImageToStickerCenter$default(StickerCenterServiceManager stickerCenterServiceManager, Bitmap bitmap, DeviceType deviceType, Rect rect, String str, boolean z, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z = false;
        }
        stickerCenterServiceManager.sendImageToStickerCenter(bitmap, deviceType, rect, str, z);
    }

    public final String getAuthForSticker() {
        return this.authForSticker;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void init() {
        int i2;
        boolean z;
        Bundle bundle;
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(ServiceManagerUtil.STICKER_CENTER_PACKAGE_NAME, 128);
            if (packageInfo == null) {
                LibLogger.e(TAG, "Package is not found");
                this.isStickerCenterEnabled = false;
                return;
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                i2 = -1;
            } else {
                i2 = bundle.getInt("com.samsung.android.stickercenter.api.version");
            }
            boolean z3 = true;
            if (i2 >= 13) {
                z = true;
            } else {
                z = false;
            }
            this.isStickerCenterEnabled = z;
            if (i2 < 15) {
                z3 = false;
            }
            this.isStickerCenterPopupSupported = z3;
            if (this.authForSticker.length() == 0) {
                LibLogger.e(TAG, "auth is empty.");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LibLogger.e(TAG, "sticker center is not installed.");
            this.isStickerCenterEnabled = false;
        }
    }

    public final boolean isClippedStickerEnabled() {
        if (!this.isStickerCenterEnabled || !Rune.INSTANCE.getSUPPORT_SAVE_AS_STICKER_FROM_STICKER_CENTER()) {
            return false;
        }
        return true;
    }

    public final void sendImageToStickerCenter(Bitmap bitmap, DeviceType deviceType, Rect rect, String str, boolean z) {
        j.e(bitmap, "bitmap");
        j.e(deviceType, "deviceType");
        j.e(rect, "rect");
        j.e(str, "stickerID");
        LibLogger.d(TAG, "call sendImageToStickerCenter");
        ServiceManagerUtil serviceManagerUtil = ServiceManagerUtil.INSTANCE;
        Uri uriAndProvidePermissionStickerDB = serviceManagerUtil.getUriAndProvidePermissionStickerDB(this.context, new File(serviceManagerUtil.getImageClipperFilePath(bitmap, this.context)), this.authForSticker);
        try {
            Intent intent = new Intent();
            intent.setAction(ServiceManagerUtil.STICKER_CENTER_SAVE_IMAGE_ACTION);
            intent.setPackage(ServiceManagerUtil.STICKER_CENTER_PACKAGE_NAME);
            intent.putExtra(ServiceManagerUtil.STICKER_CENTER_IMAGE_PATH, uriAndProvidePermissionStickerDB);
            intent.putExtra("ACCESS_TOKEN", str);
            intent.putExtra(ServiceManagerUtil.STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_IMAGE_RECT, rect);
            intent.putExtra(ServiceManagerUtil.STICKER_CENTER_INTENT_FILTER_STRING_EXTRA_USE_ANIMATED, z);
            PopOverUtil popOverUtil = PopOverUtil.INSTANCE;
            if (!popOverUtil.isPickerPopOverEnabled(this.context, deviceType) || !this.isStickerCenterPopupSupported) {
                this.context.startActivity(intent);
                return;
            }
            Context context2 = this.context;
            context2.startActivity(intent, popOverUtil.getPopOverDetails(context2));
        } catch (Exception e) {
            String message = e.getMessage();
            LibLogger.e(TAG, "sendImageToStickerCenter error : " + message);
        }
    }
}
