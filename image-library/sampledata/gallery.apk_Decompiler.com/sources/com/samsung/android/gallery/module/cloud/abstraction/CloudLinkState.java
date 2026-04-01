package com.samsung.android.gallery.module.cloud.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum CloudLinkState {
    None,
    Migrating,
    Migrated,
    Unlinked,
    Unknown,
    Error,
    Invalid;

    public boolean equals(String str) {
        return name().equals(str);
    }
}
