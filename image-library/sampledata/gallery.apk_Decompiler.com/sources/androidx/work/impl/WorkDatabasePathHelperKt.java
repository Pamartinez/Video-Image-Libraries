package androidx.work.impl;

import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "TAG", "Ljava/lang/String;", "", "DATABASE_EXTRA_FILES", "[Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkDatabasePathHelperKt {
    /* access modifiers changed from: private */
    public static final String[] DATABASE_EXTRA_FILES = {"-journal", "-shm", "-wal"};
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WrkDbPathHelper");
        j.d(tagWithPrefix, "tagWithPrefix(\"WrkDbPathHelper\")");
        TAG = tagWithPrefix;
    }
}
