package com.samsung.android.sum.core;

import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Tag {
    private String primary;
    private String secondary;
    private Tag successor;

    public Tag(String str) {
        this.primary = str;
    }

    public String getPrimary() {
        return (String) Optional.ofNullable(this.successor).map(new a(5)).orElse(this.primary);
    }

    public String getSecondary() {
        return ((String) Optional.ofNullable(this.successor).map(new a(6)).orElse("")) + ((String) Optional.ofNullable(this.secondary).orElse(""));
    }

    public boolean hasSecondary() {
        return this.secondary != null;
    }

    public void setSecondary(String str) {
        this.secondary = C0212a.m("[", str, "]");
    }

    public boolean hasSecondary(String str) {
        return C0212a.m("[", str, "]").equals(this.secondary);
    }

    public Tag(String str, String str2) {
        this.primary = str;
        this.secondary = str2;
    }

    public Tag(Tag tag) {
        this.successor = tag;
    }
}
