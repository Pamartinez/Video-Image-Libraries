package com.samsung.android.gallery.module.abstraction;

import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareData {
    public int bucketId;
    public String bucketName;
    public String uri;

    public ShareData(String str, int i2, String str2) {
        this.uri = str;
        this.bucketId = i2;
        this.bucketName = str2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ShareData{");
        sb2.append(this.uri);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0086a.l(sb2, this.bucketId, "}");
    }
}
