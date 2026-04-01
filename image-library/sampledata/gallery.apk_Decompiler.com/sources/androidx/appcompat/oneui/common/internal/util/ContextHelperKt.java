package androidx.appcompat.oneui.common.internal.util;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroid/content/Context;", "", "isDefaultTheme", "(Landroid/content/Context;)Z", "appcompat_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ContextHelperKt {
    public static final boolean isDefaultTheme(Context context) {
        j.e(context, "<this>");
        return TextUtils.isEmpty(Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage"));
    }
}
