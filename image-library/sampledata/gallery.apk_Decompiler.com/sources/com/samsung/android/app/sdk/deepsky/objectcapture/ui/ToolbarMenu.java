package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1199q;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\u0018\u00002\u00020\u0001:\u0001\u001eBI\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "", "authForEdit", "", "authForSticker", "toolbarMenuList", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "stickerCallBackListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "originalFilePath", "titleColor", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;Ljava/lang/String;Ljava/lang/Integer;)V", "getAuthForEdit", "()Ljava/lang/String;", "getAuthForSticker", "getToolbarMenuList", "()Ljava/util/List;", "getStickerCallBackListener", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "setStickerCallBackListener", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;)V", "getOriginalFilePath", "getTitleColor", "()Ljava/lang/Integer;", "setTitleColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "Builder", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenu {
    private final String authForEdit;
    private final String authForSticker;
    private final String originalFilePath;
    private StickerCallbackListener stickerCallBackListener;
    private Integer titleColor;
    private final List<ToolbarMenuItem> toolbarMenuList;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0013J%\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000b¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u0013J1\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000b2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001e\u0010\u0013J-\u0010!\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020#¢\u0006\u0004\b$\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010&R\u0016\u0010'\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010(R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*8\u0002X\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b0\u0010(R\u0018\u00101\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102¨\u00063"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "color", "Lme/x;", "setTitleColor", "(I)V", "", "filePath", "setOriginalFilePath", "(Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "order", "", "useDefaultOption", "addCopy", "(IZ)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "addShare", "addSaveAsImage", "authorities", "addEdit", "(IZLjava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "addSelectAll", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "listener", "addSaveAsSticker", "(IZLjava/lang/String;Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "addSaveAsGif", "id", "title", "addMenu", "(IILjava/lang/String;Z)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu$Builder;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "build", "()Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenu;", "Landroid/content/Context;", "authForEdit", "Ljava/lang/String;", "authForSticker", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuItem;", "toolbarMenuList", "Ljava/util/List;", "stickerCallBackListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/StickerCallbackListener;", "originalFilePath", "titleColor", "Ljava/lang/Integer;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String authForEdit = "";
        private String authForSticker = "";
        private final Context context;
        private String originalFilePath = "";
        private StickerCallbackListener stickerCallBackListener;
        private Integer titleColor;
        private final List<ToolbarMenuItem> toolbarMenuList = new ArrayList();

        public Builder(Context context2) {
            j.e(context2, "context");
            this.context = context2;
        }

        public static /* synthetic */ Builder addSaveAsSticker$default(Builder builder, int i2, boolean z, String str, StickerCallbackListener stickerCallbackListener, int i7, Object obj) {
            if ((i7 & 8) != 0) {
                stickerCallbackListener = null;
            }
            return builder.addSaveAsSticker(i2, z, str, stickerCallbackListener);
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addCopy(int r12, boolean r13) {
            /*
                r11 = this;
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0012
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x0036
            L_0x0012:
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0036
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                if (r2 == 0) goto L_0x002e
                int r1 = r1.getOrder()
                if (r1 != r12) goto L_0x0016
            L_0x002e:
                java.lang.String r12 = "ToolbarMenuManager"
                java.lang.String r13 = "Please check addCopy."
                android.util.Log.i(r12, r13)
                return r11
            L_0x0036:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                android.content.Context r1 = r11.context
                r2 = 17039361(0x1040001, float:2.4244574E-38)
                java.lang.String r6 = r1.getString(r2)
                java.lang.String r1 = "getString(...)"
                kotlin.jvm.internal.j.d(r6, r1)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r3 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r9 = 16
                r10 = 0
                r4 = 0
                r8 = 0
                r5 = r12
                r7 = r13
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                r0.add(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addCopy(int, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addEdit(int r11, boolean r12, java.lang.String r13) {
            /*
                r10 = this;
                java.lang.String r0 = "authorities"
                kotlin.jvm.internal.j.e(r13, r0)
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r10.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0017
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0017
                goto L_0x003c
            L_0x0017:
                java.util.Iterator r0 = r0.iterator()
            L_0x001b:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x003c
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 4
                if (r2 == r3) goto L_0x0034
                int r1 = r1.getOrder()
                if (r1 != r11) goto L_0x001b
            L_0x0034:
                java.lang.String r11 = "ToolbarMenuManager"
                java.lang.String r12 = "Please check addShare."
                android.util.Log.i(r11, r12)
                return r10
            L_0x003c:
                r10.authForEdit = r13
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r13 = r10.toolbarMenuList
                android.content.Context r0 = r10.context
                int r1 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_edit
                java.lang.String r5 = r0.getString(r1)
                java.lang.String r0 = "getString(...)"
                kotlin.jvm.internal.j.d(r5, r0)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r2 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r8 = 16
                r9 = 0
                r3 = 4
                r7 = 0
                r4 = r11
                r6 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
                r13.add(r2)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addEdit(int, boolean, java.lang.String):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x0022  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addMenu(int r10, int r11, java.lang.String r12, boolean r13) {
            /*
                r9 = this;
                java.lang.String r0 = "title"
                kotlin.jvm.internal.j.e(r12, r0)
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r9.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0018
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0018
                goto L_0x003c
            L_0x0018:
                java.util.Iterator r0 = r0.iterator()
            L_0x001c:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x003c
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                if (r2 == r10) goto L_0x0034
                int r1 = r1.getOrder()
                if (r1 != r11) goto L_0x001c
            L_0x0034:
                java.lang.String r10 = "ToolbarMenuManager"
                java.lang.String r11 = "Please check addMenu."
                android.util.Log.i(r10, r11)
                return r9
            L_0x003c:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r9.toolbarMenuList
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r7 = 16
                r8 = 0
                r6 = 0
                r2 = r10
                r3 = r11
                r4 = r12
                r5 = r13
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                r0.add(r1)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addMenu(int, int, java.lang.String, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addSaveAsGif(int r12, boolean r13) {
            /*
                r11 = this;
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0012
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x0037
            L_0x0012:
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0037
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 6
                if (r2 == r3) goto L_0x002f
                int r1 = r1.getOrder()
                if (r1 != r12) goto L_0x0016
            L_0x002f:
                java.lang.String r12 = "ToolbarMenuManager"
                java.lang.String r13 = "Please check addSaveAsSticker."
                android.util.Log.i(r12, r13)
                return r11
            L_0x0037:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                android.content.Context r1 = r11.context
                int r2 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_save_as_gif
                java.lang.String r6 = r1.getString(r2)
                java.lang.String r1 = "getString(...)"
                kotlin.jvm.internal.j.d(r6, r1)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r3 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r9 = 16
                r10 = 0
                r4 = 6
                r8 = 0
                r5 = r12
                r7 = r13
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                r0.add(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addSaveAsGif(int, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addSaveAsImage(int r12, boolean r13) {
            /*
                r11 = this;
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0012
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x0037
            L_0x0012:
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0037
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 2
                if (r2 == r3) goto L_0x002f
                int r1 = r1.getOrder()
                if (r1 != r12) goto L_0x0016
            L_0x002f:
                java.lang.String r12 = "ToolbarMenuManager"
                java.lang.String r13 = "Please check addSaveAsImage."
                android.util.Log.i(r12, r13)
                return r11
            L_0x0037:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                android.content.Context r1 = r11.context
                int r2 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_save_as_image
                java.lang.String r6 = r1.getString(r2)
                java.lang.String r1 = "getString(...)"
                kotlin.jvm.internal.j.d(r6, r1)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r3 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r9 = 16
                r10 = 0
                r4 = 2
                r8 = 0
                r5 = r12
                r7 = r13
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                r0.add(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addSaveAsImage(int, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addSaveAsSticker(int r10, boolean r11, java.lang.String r12, com.samsung.android.app.sdk.deepsky.objectcapture.ui.StickerCallbackListener r13) {
            /*
                r9 = this;
                java.lang.String r0 = "authorities"
                kotlin.jvm.internal.j.e(r12, r0)
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r9.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0017
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0017
                goto L_0x003c
            L_0x0017:
                java.util.Iterator r0 = r0.iterator()
            L_0x001b:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x003c
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 3
                if (r2 == r3) goto L_0x0034
                int r1 = r1.getOrder()
                if (r1 != r10) goto L_0x001b
            L_0x0034:
                java.lang.String r10 = "ToolbarMenuManager"
                java.lang.String r11 = "Please check addSaveAsSticker."
                android.util.Log.i(r10, r11)
                return r9
            L_0x003c:
                r9.authForSticker = r12
                r9.stickerCallBackListener = r13
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r12 = r9.toolbarMenuList
                android.content.Context r13 = r9.context
                int r0 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_Save_as_sticker
                java.lang.String r4 = r13.getString(r0)
                java.lang.String r13 = "getString(...)"
                kotlin.jvm.internal.j.d(r4, r13)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r7 = 16
                r8 = 0
                r2 = 3
                r6 = 0
                r3 = r10
                r5 = r11
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                r12.add(r1)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addSaveAsSticker(int, boolean, java.lang.String, com.samsung.android.app.sdk.deepsky.objectcapture.ui.StickerCallbackListener):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addSelectAll(int r12, boolean r13) {
            /*
                r11 = this;
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0012
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x0037
            L_0x0012:
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0037
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 5
                if (r2 == r3) goto L_0x002f
                int r1 = r1.getOrder()
                if (r1 != r12) goto L_0x0016
            L_0x002f:
                java.lang.String r12 = "ToolbarMenuManager"
                java.lang.String r13 = "Please check addSelectAll."
                android.util.Log.i(r12, r13)
                return r11
            L_0x0037:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                android.content.Context r1 = r11.context
                int r2 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_select_all
                java.lang.String r6 = r1.getString(r2)
                java.lang.String r1 = "getString(...)"
                kotlin.jvm.internal.j.d(r6, r1)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r3 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r9 = 16
                r10 = 0
                r4 = 5
                r8 = 0
                r5 = r12
                r7 = r13
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                r0.add(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addSelectAll(int, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder addShare(int r12, boolean r13) {
            /*
                r11 = this;
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r1 = r0 instanceof java.util.Collection
                if (r1 == 0) goto L_0x0012
                r1 = r0
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                if (r1 == 0) goto L_0x0012
                goto L_0x0037
            L_0x0012:
                java.util.Iterator r0 = r0.iterator()
            L_0x0016:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0037
                java.lang.Object r1 = r0.next()
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r1 = (com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem) r1
                int r2 = r1.getId()
                r3 = 1
                if (r2 == r3) goto L_0x002f
                int r1 = r1.getOrder()
                if (r1 != r12) goto L_0x0016
            L_0x002f:
                java.lang.String r12 = "ToolbarMenuManager"
                java.lang.String r13 = "Please check addShare."
                android.util.Log.i(r12, r13)
                return r11
            L_0x0037:
                java.util.List<com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem> r0 = r11.toolbarMenuList
                android.content.Context r1 = r11.context
                int r2 = com.samsung.android.app.sdk.deepsky.objectcapture.R.string.object_capture_popup_main_item_share
                java.lang.String r6 = r1.getString(r2)
                java.lang.String r1 = "getString(...)"
                kotlin.jvm.internal.j.d(r6, r1)
                com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem r3 = new com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem
                r9 = 16
                r10 = 0
                r4 = 1
                r8 = 0
                r5 = r12
                r7 = r13
                r3.<init>(r4, r5, r6, r7, r8, r9, r10)
                r0.add(r3)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu.Builder.addShare(int, boolean):com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenu$Builder");
        }

        public final ToolbarMenu build() {
            List<ToolbarMenuItem> list = this.toolbarMenuList;
            if (list.size() > 1) {
                C1199q.z0(list, new ToolbarMenu$Builder$build$$inlined$sortBy$1());
            }
            return new ToolbarMenu(this.authForEdit, this.authForSticker, this.toolbarMenuList, this.stickerCallBackListener, this.originalFilePath, this.titleColor, (e) null);
        }

        public final Builder setOriginalFilePath(String str) {
            j.e(str, "filePath");
            this.originalFilePath = str;
            return this;
        }

        public final void setTitleColor(int i2) {
            this.titleColor = Integer.valueOf(i2);
        }
    }

    public /* synthetic */ ToolbarMenu(String str, String str2, List list, StickerCallbackListener stickerCallbackListener, String str3, Integer num, e eVar) {
        this(str, str2, list, stickerCallbackListener, str3, num);
    }

    public final String getAuthForEdit() {
        return this.authForEdit;
    }

    public final String getAuthForSticker() {
        return this.authForSticker;
    }

    public final String getOriginalFilePath() {
        return this.originalFilePath;
    }

    public final StickerCallbackListener getStickerCallBackListener() {
        return this.stickerCallBackListener;
    }

    public final Integer getTitleColor() {
        return this.titleColor;
    }

    public final List<ToolbarMenuItem> getToolbarMenuList() {
        return this.toolbarMenuList;
    }

    public final void setStickerCallBackListener(StickerCallbackListener stickerCallbackListener) {
        this.stickerCallBackListener = stickerCallbackListener;
    }

    public final void setTitleColor(Integer num) {
        this.titleColor = num;
    }

    private ToolbarMenu(String str, String str2, List<ToolbarMenuItem> list, StickerCallbackListener stickerCallbackListener, String str3, Integer num) {
        this.authForEdit = str;
        this.authForSticker = str2;
        this.toolbarMenuList = list;
        this.stickerCallBackListener = stickerCallbackListener;
        this.originalFilePath = str3;
        this.titleColor = num;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ToolbarMenu(String str, String str2, List list, StickerCallbackListener stickerCallbackListener, String str3, Integer num, int i2, e eVar) {
        this(str, str2, list, (i2 & 8) != 0 ? null : stickerCallbackListener, (i2 & 16) != 0 ? "" : str3, (i2 & 32) != 0 ? null : num);
    }
}
