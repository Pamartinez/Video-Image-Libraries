package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.b;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001f\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J*\u0010\u0013\u001a\u00020\u00002\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0010J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR'\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u0012¨\u0006 "}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result;", "Landroid/os/Parcelable;", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "tags", "<init>", "(Ljava/util/HashSet;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/util/HashSet;", "copy", "(Ljava/util/HashSet;)Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result;", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/HashSet;", "getTags", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTaggerV2Result implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageTaggerV2Result> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final HashSet<String> tags;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkImageTaggerV2Result;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkImageTaggerV2Result> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkImageTaggerV2Result.CREATOR;
        }

        private Companion() {
            super(new b(0));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageTaggerV2Result> {
        public final VexFwkImageTaggerV2Result createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            int readInt = parcel.readInt();
            HashSet hashSet = new HashSet(readInt);
            for (int i2 = 0; i2 != readInt; i2++) {
                hashSet.add(parcel.readString());
            }
            return new VexFwkImageTaggerV2Result(hashSet);
        }

        public final VexFwkImageTaggerV2Result[] newArray(int i2) {
            return new VexFwkImageTaggerV2Result[i2];
        }
    }

    public VexFwkImageTaggerV2Result(HashSet<String> hashSet) {
        j.e(hashSet, "tags");
        this.tags = hashSet;
    }

    public static /* synthetic */ VexFwkImageTaggerV2Result copy$default(VexFwkImageTaggerV2Result vexFwkImageTaggerV2Result, HashSet<String> hashSet, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            hashSet = vexFwkImageTaggerV2Result.tags;
        }
        return vexFwkImageTaggerV2Result.copy(hashSet);
    }

    public final HashSet<String> component1() {
        return this.tags;
    }

    public final VexFwkImageTaggerV2Result copy(HashSet<String> hashSet) {
        j.e(hashSet, "tags");
        return new VexFwkImageTaggerV2Result(hashSet);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VexFwkImageTaggerV2Result) && j.a(this.tags, ((VexFwkImageTaggerV2Result) obj).tags)) {
            return true;
        }
        return false;
    }

    public final HashSet<String> getTags() {
        return this.tags;
    }

    public int hashCode() {
        return this.tags.hashCode();
    }

    public String toString() {
        HashSet<String> hashSet = this.tags;
        return "VexFwkImageTaggerV2Result(tags=" + hashSet + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        HashSet<String> hashSet = this.tags;
        parcel.writeInt(hashSet.size());
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
    }
}
