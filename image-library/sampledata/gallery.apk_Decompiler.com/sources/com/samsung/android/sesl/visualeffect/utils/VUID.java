package com.samsung.android.sesl.visualeffect.utils;

import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R<\u0010\u0015\u001a*\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u0012j\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b`\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/VUID;", "", "", "tagKey", "<init>", "(I)V", "Landroid/view/View;", "v", "Ljava/lang/ref/WeakReference;", "getOrCreateMemoized", "(Landroid/view/View;)Ljava/lang/ref/WeakReference;", "forget", "Lme/x;", "clear", "()V", "I", "getTagKey", "()I", "Ljava/util/HashMap;", "Ljava/util/UUID;", "Lkotlin/collections/HashMap;", "idMap", "Ljava/util/HashMap;", "Companion", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VUID {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VUID";
    private static final int UUID_TAG_KEY = 1029537645;
    private final HashMap<UUID, WeakReference<View>> idMap;
    private final int tagKey;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/VUID$Companion;", "", "<init>", "()V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public VUID(int i2) {
        this.tagKey = i2;
        this.idMap = new HashMap<>();
    }

    public final void clear() {
        this.idMap.clear();
    }

    public final WeakReference<View> forget(View view) {
        j.e(view, "v");
        Object tag = view.getTag(this.tagKey);
        if (tag == null) {
            return null;
        }
        view.setTag(this.tagKey, (Object) null);
        WeakReference<View> weakReference = this.idMap.get(tag);
        y.a(this.idMap).remove(tag);
        return weakReference;
    }

    public final WeakReference<View> getOrCreateMemoized(View view) {
        UUID uuid;
        UUID uuid2;
        j.e(view, "v");
        if (view.getTag(this.tagKey) != null) {
            Object tag = view.getTag(this.tagKey);
            if (tag instanceof UUID) {
                uuid2 = (UUID) tag;
            } else {
                uuid2 = null;
            }
            if (uuid2 != null) {
                WeakReference<View> weakReference = this.idMap.get(tag);
                if (weakReference != null) {
                    return weakReference;
                }
                uuid = (UUID) tag;
            } else {
                String str = TAG;
                int i2 = this.tagKey;
                Log.e(str, "key(=" + i2 + ") of the tag on a view has corrupted by me.");
                uuid = UUID.randomUUID();
            }
        } else {
            uuid = UUID.randomUUID();
        }
        view.setTag(this.tagKey, uuid);
        WeakReference<View> weakReference2 = new WeakReference<>(view);
        this.idMap.put(uuid, weakReference2);
        return weakReference2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VUID(int i2, int i7, e eVar) {
        this((i7 & 1) != 0 ? UUID_TAG_KEY : i2);
    }
}
