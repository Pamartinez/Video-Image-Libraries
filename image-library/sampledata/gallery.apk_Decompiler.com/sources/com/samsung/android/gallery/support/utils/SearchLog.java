package com.samsung.android.gallery.support.utils;

import V8.a;
import i.C0212a;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchLog {
    static final boolean ENABLED = PocFeatures.isEnabled(PocFeatures.SearchLog);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BufferHolder {
        static final LinkedList<String> list = new LinkedList<>();

        public static void append(String str) {
            LinkedList<String> linkedList = list;
            if (linkedList.add(str) && linkedList.size() > 30) {
                linkedList.removeFirst();
            }
        }

        public static String dump() {
            return C0212a.p(new StringBuilder("== SearchLog ==\n"), (String) list.stream().map(new a(29)).collect(Collectors.joining("\n")), "\n");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileHolder {
        static final FileLogger logger;

        static {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(StorageInfo.getDefault().getMediaPath("log"));
            logger = new FileLogger(C0212a.p(sb2, File.separator, "search.log"));
        }
    }

    public static void d(CharSequence charSequence, String str) {
        try {
            String buildLog = FileLogger.buildLog(charSequence, str);
            BufferHolder.append(buildLog);
            if (ENABLED) {
                FileHolder.logger.append(buildLog);
            }
        } catch (Exception unused) {
        }
    }

    public static String dump() {
        return BufferHolder.dump();
    }

    public static long length() {
        return BufferHolder.list.stream().mapToLong(new C0674l(1)).sum();
    }

    public static List<String> listOf() {
        return BufferHolder.list;
    }
}
