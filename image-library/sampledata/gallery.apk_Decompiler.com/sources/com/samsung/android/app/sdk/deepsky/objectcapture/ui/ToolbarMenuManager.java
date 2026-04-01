package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import A.a;
import Vf.C;
import Vf.C0867e0;
import Vf.C0886x;
import Vf.D;
import Vf.M;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.PhotoEditorServiceManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.controller.StickerCenterServiceManager;
import com.samsung.android.app.sdk.deepsky.objectcapture.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.objectcapture.popover.DeviceType;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.app.sdk.deepsky.objectcapture.video.VideoClipper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 L2\u00020\u0001:\u0001LB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\bJ\u0019\u0010\f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\bJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010#\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u001e¢\u0006\u0004\b#\u0010$J\r\u0010&\u001a\u00020%¢\u0006\u0004\b&\u0010'J\u0019\u0010(\u001a\u00020\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b(\u0010\rJ\r\u0010)\u001a\u00020\u0006¢\u0006\u0004\b)\u0010\bJ\u001d\u0010.\u001a\u00020\u00062\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b.\u0010/J\u0015\u00100\u001a\u00020\u00062\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0006¢\u0006\u0004\b2\u0010\bJ\r\u00104\u001a\u000203¢\u0006\u0004\b4\u00105J\u001b\u00108\u001a\u00020\u00062\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000606¢\u0006\u0004\b8\u00109R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0018\u0010C\u001a\u0004\u0018\u00010B8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010E\u001a\u0002038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u001a8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bJ\u0010K¨\u0006M"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lme/x;", "initPhotoEditor", "()V", "initStickerCenter", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "vc", "initVideoClipper", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;)V", "bindPhotoEditor", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "getToolbarMenuList", "()Ljava/util/List;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "menu", "setToolbarMenu", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;)V", "", "getTitleColor", "()Ljava/lang/Integer;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "type", "setDeviceType", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;)V", "", "hasToolbarMenu", "()Z", "id", "isEnabled", "updateToolbarMenu", "(IZ)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "createToolbarMenuBuilder", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "init", "unbindPhotoEditor", "Landroid/graphics/Bitmap;", "bitmap", "Landroid/graphics/Rect;", "rect", "insertClippedImage", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;)V", "editClipperFilePath", "(Landroid/graphics/Bitmap;)V", "clearStickerId", "", "getStickerID", "()Ljava/lang/String;", "Lkotlin/Function0;", "select", "selectAll", "(LAe/a;)V", "Landroid/content/Context;", "toolbarMenu", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "videoClipper", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoClipper;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager;", "photoEditorServiceManager", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/PhotoEditorServiceManager;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterServiceManager;", "stickerCenterServiceManager", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/controller/StickerCenterServiceManager;", "stickerID", "Ljava/lang/String;", "deviceType", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "LVf/e0;", "insertJob", "LVf/e0;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenuManager {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "ToolbarMenuManager";
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public DeviceType deviceType = DeviceType.NORMAL_TYPE;
    /* access modifiers changed from: private */
    public C0867e0 insertJob;
    /* access modifiers changed from: private */
    public PhotoEditorServiceManager photoEditorServiceManager;
    /* access modifiers changed from: private */
    public StickerCenterServiceManager stickerCenterServiceManager;
    /* access modifiers changed from: private */
    public String stickerID = "";
    private ToolbarMenu toolbarMenu;
    /* access modifiers changed from: private */
    public VideoClipper videoClipper;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ToolbarMenuManager(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final void bindPhotoEditor() {
        StickerCallbackListener stickerCallbackListener;
        PhotoEditorServiceManager photoEditorServiceManager2 = this.photoEditorServiceManager;
        if (photoEditorServiceManager2 != null) {
            LibLogger.i(TAG, "bind photoEditorServiceManager.");
            photoEditorServiceManager2.bindService();
            ToolbarMenu toolbarMenu2 = this.toolbarMenu;
            if (toolbarMenu2 != null) {
                stickerCallbackListener = toolbarMenu2.getStickerCallBackListener();
            } else {
                stickerCallbackListener = null;
            }
            photoEditorServiceManager2.setPhotoEditorCallbackListener(stickerCallbackListener);
        }
    }

    public static /* synthetic */ void init$default(ToolbarMenuManager toolbarMenuManager, VideoClipper videoClipper2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            videoClipper2 = null;
        }
        toolbarMenuManager.init(videoClipper2);
    }

    private final void initPhotoEditor() {
        String str;
        PhotoEditorServiceManager photoEditorServiceManager2 = new PhotoEditorServiceManager(this.context);
        this.photoEditorServiceManager = photoEditorServiceManager2;
        ToolbarMenu toolbarMenu2 = this.toolbarMenu;
        String str2 = null;
        if (toolbarMenu2 != null) {
            str = toolbarMenu2.getAuthForEdit();
        } else {
            str = null;
        }
        ToolbarMenu toolbarMenu3 = this.toolbarMenu;
        if (toolbarMenu3 != null) {
            str2 = toolbarMenu3.getAuthForSticker();
        }
        photoEditorServiceManager2.setAuthorities(str, str2);
    }

    private final void initStickerCenter() {
        String str;
        Context context2 = this.context;
        ToolbarMenu toolbarMenu2 = this.toolbarMenu;
        if (toolbarMenu2 == null || (str = toolbarMenu2.getAuthForSticker()) == null) {
            str = "";
        }
        StickerCenterServiceManager stickerCenterServiceManager2 = new StickerCenterServiceManager(context2, str);
        stickerCenterServiceManager2.init();
        if (!stickerCenterServiceManager2.isClippedStickerEnabled()) {
            bindPhotoEditor();
        }
        this.stickerCenterServiceManager = stickerCenterServiceManager2;
    }

    private final void initVideoClipper(VideoClipper videoClipper2) {
        if (this.videoClipper == null && videoClipper2 != null) {
            this.videoClipper = videoClipper2;
        }
    }

    public final void clearStickerId() {
        String str = this.stickerID;
        LibLogger.d(TAG, "clearStickerId : stickerId(before) = " + str);
        D.n(D.a(M.f3843a), (C0886x) null, (C) null, new ToolbarMenuManager$clearStickerId$1(this, (C1227c) null), 3);
    }

    public final ToolbarMenu.Builder createToolbarMenuBuilder() {
        return new ToolbarMenu.Builder(this.context);
    }

    public final void editClipperFilePath(Bitmap bitmap) {
        String str;
        j.e(bitmap, "bitmap");
        PhotoEditorServiceManager photoEditorServiceManager2 = this.photoEditorServiceManager;
        if (photoEditorServiceManager2 != null) {
            String imageClipperFilePath = ServiceManagerUtil.INSTANCE.getImageClipperFilePath(bitmap, this.context);
            ToolbarMenu toolbarMenu2 = this.toolbarMenu;
            if (toolbarMenu2 == null || (str = toolbarMenu2.getOriginalFilePath()) == null) {
                str = "";
            }
            photoEditorServiceManager2.editClippedImage(imageClipperFilePath, str);
        }
    }

    public final String getStickerID() {
        return this.stickerID;
    }

    public final Integer getTitleColor() {
        ToolbarMenu toolbarMenu2 = this.toolbarMenu;
        if (toolbarMenu2 != null) {
            return toolbarMenu2.getTitleColor();
        }
        return null;
    }

    public final List<ToolbarMenuItem> getToolbarMenuList() {
        List<ToolbarMenuItem> toolbarMenuList;
        ToolbarMenu toolbarMenu2 = this.toolbarMenu;
        if (toolbarMenu2 == null || (toolbarMenuList = toolbarMenu2.getToolbarMenuList()) == null) {
            return new ArrayList();
        }
        return toolbarMenuList;
    }

    public final boolean hasToolbarMenu() {
        if (this.toolbarMenu != null) {
            return true;
        }
        return false;
    }

    public final void init(VideoClipper videoClipper2) {
        initVideoClipper(videoClipper2);
        initPhotoEditor();
        initStickerCenter();
    }

    public final void insertClippedImage(Bitmap bitmap, Rect rect) {
        j.e(bitmap, "bitmap");
        j.e(rect, "rect");
        String f = a.f("MySticker_", System.currentTimeMillis());
        this.stickerID = f;
        LibLogger.d(TAG, "insertClippedImage : stickerId(after) = " + f);
        this.insertJob = D.n(D.a(M.f3843a), (C0886x) null, (C) null, new ToolbarMenuManager$insertClippedImage$1(this, bitmap, rect, (C1227c) null), 3);
    }

    public final void selectAll(Ae.a aVar) {
        j.e(aVar, "select");
        aVar.invoke();
    }

    public final void setDeviceType(DeviceType deviceType2) {
        j.e(deviceType2, "type");
        this.deviceType = deviceType2;
    }

    public final void setToolbarMenu(ToolbarMenu toolbarMenu2) {
        j.e(toolbarMenu2, "menu");
        this.toolbarMenu = toolbarMenu2;
    }

    public final void unbindPhotoEditor() {
        PhotoEditorServiceManager photoEditorServiceManager2 = this.photoEditorServiceManager;
        if (photoEditorServiceManager2 != null) {
            LibLogger.i(TAG, "unbind photoEditorServiceManager.");
            photoEditorServiceManager2.unbindService();
        }
        this.photoEditorServiceManager = null;
    }

    public final void updateToolbarMenu(int i2, boolean z) {
        Object obj;
        ToolbarMenu toolbarMenu2 = this.toolbarMenu;
        if (toolbarMenu2 != null) {
            Iterator it = toolbarMenu2.getToolbarMenuList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((ToolbarMenuItem) obj).getId() == i2) {
                    break;
                }
            }
            ToolbarMenuItem toolbarMenuItem = (ToolbarMenuItem) obj;
            if (toolbarMenuItem != null) {
                toolbarMenuItem.setEnabled(z);
            }
        }
    }
}
