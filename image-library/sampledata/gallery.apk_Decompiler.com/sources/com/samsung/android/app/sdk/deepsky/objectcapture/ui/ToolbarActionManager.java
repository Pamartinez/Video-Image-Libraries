package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import Ad.C0721b;
import Ae.a;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionManager;", "", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;", "toolbarMenuManager", "Lkotlin/Function0;", "Lme/x;", "selectAll", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;LAe/a;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "result", "setObjectResult", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;)V", "Landroid/graphics/Rect;", "rect", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "getToolbarActionListener", "(Landroid/graphics/Rect;)Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarMenuManager;", "LAe/a;", "objectResult", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/base/ObjectResult;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarActionManager {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "ToolbarActionManager";
    /* access modifiers changed from: private */
    public ObjectResult objectResult;
    /* access modifiers changed from: private */
    public final a selectAll;
    /* access modifiers changed from: private */
    public final ToolbarMenuManager toolbarMenuManager;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ToolbarActionManager$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public ToolbarActionManager(ToolbarMenuManager toolbarMenuManager2, a aVar) {
        j.e(toolbarMenuManager2, "toolbarMenuManager");
        j.e(aVar, "selectAll");
        this.toolbarMenuManager = toolbarMenuManager2;
        this.selectAll = aVar;
    }

    /* access modifiers changed from: private */
    public static final x _init_$lambda$0() {
        return x.f4917a;
    }

    public final ToolbarActionListener getToolbarActionListener(Rect rect) {
        j.e(rect, "rect");
        return new ToolbarActionManager$getToolbarActionListener$1(this, rect);
    }

    public final void setObjectResult(ObjectResult objectResult2) {
        j.e(objectResult2, "result");
        this.objectResult = objectResult2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ToolbarActionManager(ToolbarMenuManager toolbarMenuManager2, a aVar, int i2, e eVar) {
        this(toolbarMenuManager2, (i2 & 2) != 0 ? new C0721b(5) : aVar);
    }
}
