package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.Tag;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaFilterBase implements MediaFilter {
    private Tag tag = new Tag(SLog.tagOf(getClass()));
    protected Map<Enum<?>, Tag> tags = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public /* synthetic */ Tag lambda$addTag$0(Enum enumR) {
        return this.tag;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Tag lambda$getTag$1(Enum enumR) {
        return this.tag;
    }

    public void addTag(String str) {
        if (!this.tag.hasSecondary(str)) {
            Tag tag2 = new Tag(this.tag);
            this.tag = tag2;
            tag2.setSecondary(str);
        }
    }

    public Tag getTag() {
        return this.tag;
    }

    public Tag getTag(Enum<?> enumR) {
        return this.tags.computeIfAbsent(enumR, new g(this, 1));
    }

    public void addTag(Enum<?> enumR, String str) {
        Tag computeIfAbsent = this.tags.computeIfAbsent(enumR, new g(this, 0));
        if (!computeIfAbsent.hasSecondary(str)) {
            Tag tag2 = new Tag(computeIfAbsent);
            tag2.setSecondary(str);
            this.tags.put(enumR, tag2);
        }
    }
}
