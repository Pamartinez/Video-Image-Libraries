package com.samsung.android.sdk.bixby2.labs;

import He.F;
import L2.a;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.WindowInspector;
import com.samsung.android.sdk.bixby2.LogUtil;
import com.samsung.android.sdk.bixby2.labs.data.ErrorInfo;
import com.samsung.android.sdk.bixby2.labs.data.RootViewNode;
import com.samsung.android.sdk.bixby2.labs.data.ViewNodeInfo;
import com.samsung.android.sdk.bixby2.labs.data.ViewNodeInfoKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/bixby2/labs/ScreenLayoutHandler;", "", "()V", "ACTION_GET_SCREEN_LAYOUT", "", "KEY_SCREEN_LAYOUT_INFO", "TAG", "getAccessibilityNodeInfo", "", "Lcom/samsung/android/sdk/bixby2/labs/data/RootViewNode;", "getScreenLayout", "extras", "Landroid/os/Bundle;", "bixbyappsdk_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScreenLayoutHandler {
    public static final String ACTION_GET_SCREEN_LAYOUT = "bixby_getScreenLayout";
    public static final ScreenLayoutHandler INSTANCE = new ScreenLayoutHandler();
    public static final String KEY_SCREEN_LAYOUT_INFO = "screenLayoutInfo";
    private static final String TAG = "ScreenLayoutHandler";

    private ScreenLayoutHandler() {
    }

    private final List<RootViewNode> getAccessibilityNodeInfo() {
        Object obj;
        LogUtil.i(TAG, String.valueOf(Thread.currentThread()));
        ArrayList arrayList = new ArrayList();
        List<View> globalWindowViews = WindowInspector.getGlobalWindowViews();
        LogUtil.c(TAG, "rootView size = " + globalWindowViews.size());
        for (View view : globalWindowViews) {
            ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
            j.c(layoutParams, "null cannot be cast to non-null type android.view.WindowManager.LayoutParams");
            String obj2 = ((WindowManager.LayoutParams) layoutParams).getTitle().toString();
            ViewGroup.LayoutParams layoutParams2 = view.getRootView().getLayoutParams();
            j.c(layoutParams2, "null cannot be cast to non-null type android.view.WindowManager.LayoutParams");
            int i2 = ((WindowManager.LayoutParams) layoutParams2).type;
            LogUtil.c(TAG, "rootView = " + obj2 + ", windowType = " + i2);
            try {
                AccessibilityNodeInfo createAccessibilityNodeInfo = view.getRootView().createAccessibilityNodeInfo();
                createAccessibilityNodeInfo.setQueryFromAppProcessEnabled(view, true);
                obj = Boolean.valueOf(arrayList.add(new RootViewNode(obj2, ViewNodeInfoKt.toViewNodeInfo(createAccessibilityNodeInfo), Integer.valueOf(i2), (ErrorInfo) null, 8, (e) null)));
            } catch (Throwable th) {
                obj = a.l(th);
            }
            Throwable a7 = k.a(obj);
            if (a7 != null) {
                arrayList.add(new RootViewNode(obj2, (ViewNodeInfo) null, Integer.valueOf(i2), new ErrorInfo(a7.getClass().getSimpleName(), F.P(a7)), 2, (e) null));
            }
        }
        LogUtil.c(TAG, "ViewNodeInfo size : " + arrayList.size());
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: me.j} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: me.j} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getScreenLayout(android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r0 = "Screen Layout : "
            java.lang.String r1 = "extras"
            kotlin.jvm.internal.j.e(r4, r1)
            java.lang.String r4 = "getScreenLayout"
            java.lang.String r1 = "ScreenLayoutHandler"
            com.samsung.android.sdk.bixby2.LogUtil.c(r1, r4)
            com.google.gson.Gson r4 = new com.google.gson.Gson     // Catch:{ all -> 0x002b }
            r4.<init>()     // Catch:{ all -> 0x002b }
            java.util.List r3 = r3.getAccessibilityNodeInfo()     // Catch:{ all -> 0x002b }
            java.lang.String r3 = r4.toJson((java.lang.Object) r3)     // Catch:{ all -> 0x002b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x002b }
            r4.<init>(r0)     // Catch:{ all -> 0x002b }
            r4.append(r3)     // Catch:{ all -> 0x002b }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x002b }
            com.samsung.android.sdk.bixby2.LogUtil.c(r1, r4)     // Catch:{ all -> 0x002b }
            goto L_0x0030
        L_0x002b:
            r3 = move-exception
            me.j r3 = L2.a.l(r3)
        L_0x0030:
            java.lang.Throwable r4 = me.k.a(r3)
            if (r4 == 0) goto L_0x0047
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "exception - "
            r0.<init>(r2)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            com.samsung.android.sdk.bixby2.LogUtil.e(r1, r4)
        L_0x0047:
            boolean r4 = r3 instanceof me.j
            if (r4 == 0) goto L_0x004d
            java.lang.String r3 = ""
        L_0x004d:
            java.lang.String r3 = (java.lang.String) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.bixby2.labs.ScreenLayoutHandler.getScreenLayout(android.os.Bundle):java.lang.String");
    }
}
