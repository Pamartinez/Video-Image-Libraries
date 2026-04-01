package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/util/ClipboardUtil;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroid/content/ClipData;", "clip", "", "setPrimaryClip", "(Landroid/content/Context;Landroid/content/ClipData;)Z", "", "text", "Lme/x;", "copyClipboard", "(Landroid/content/Context;Ljava/lang/String;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ClipboardUtil {
    public static final ClipboardUtil INSTANCE = new ClipboardUtil();

    private ClipboardUtil() {
    }

    private final boolean setPrimaryClip(Context context, ClipData clipData) {
        Object systemService = context.getSystemService("clipboard");
        j.c(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        try {
            ((ClipboardManager) systemService).setPrimaryClip(clipData);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void copyClipboard(Context context, String str) {
        j.e(context, "context");
        j.e(str, "text");
        ClipData newPlainText = ClipData.newPlainText((CharSequence) null, str);
        j.b(newPlainText);
        setPrimaryClip(context, newPlainText);
    }
}
