package com.samsung.scsp.framework.core.util;

import N.b;
import com.samsung.scsp.error.FaultBarrier;
import java.net.URLEncoder;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UrlUtil {
    public static StringBuilder addUrlParameter(StringBuilder sb2, String str, String str2, boolean z) {
        return (StringBuilder) FaultBarrier.get(new b(z, sb2, str, str2), sb2).obj;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ StringBuilder lambda$addUrlParameter$0(boolean z, StringBuilder sb2, String str, String str2) {
        if (z) {
            sb2.append(URLEncoder.encode(str, "UTF-8"));
            sb2.append('=');
            sb2.append(URLEncoder.encode(str2, "UTF-8"));
            return sb2;
        }
        sb2.append('&');
        sb2.append(URLEncoder.encode(str, "UTF-8"));
        sb2.append('=');
        sb2.append(URLEncoder.encode(str2, "UTF-8"));
        return sb2;
    }

    public static StringBuilder addUrlParameter(StringBuilder sb2, Map<String, String> map) {
        if (map.size() != 0) {
            sb2.append('?');
        }
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            addUrlParameter(sb2, (String) next.getKey(), (String) next.getValue(), z);
            z = false;
        }
        return sb2;
    }
}
