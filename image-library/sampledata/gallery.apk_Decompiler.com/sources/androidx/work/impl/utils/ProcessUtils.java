package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"TAG", "", "getProcessName", "context", "Landroid/content/Context;", "isDefaultProcess", "", "configuration", "Landroidx/work/Configuration;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProcessUtils {
    private static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("ProcessUtils");
        j.d(tagWithPrefix, "tagWithPrefix(\"ProcessUtils\")");
        TAG = tagWithPrefix;
    }

    private static final String getProcessName(Context context) {
        return Api28Impl.INSTANCE.getProcessName();
    }

    public static final boolean isDefaultProcess(Context context, Configuration configuration) {
        j.e(context, "context");
        j.e(configuration, "configuration");
        String processName = getProcessName(context);
        String defaultProcessName = configuration.getDefaultProcessName();
        if (defaultProcessName == null || defaultProcessName.length() == 0) {
            return j.a(processName, context.getApplicationInfo().processName);
        }
        return j.a(processName, configuration.getDefaultProcessName());
    }
}
