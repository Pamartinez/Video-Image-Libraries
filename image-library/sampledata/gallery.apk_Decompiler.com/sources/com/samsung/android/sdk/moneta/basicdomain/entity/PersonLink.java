package com.samsung.android.sdk.moneta.basicdomain.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/basicdomain/entity/PersonLink;", "", "galleryPersonUuid", "", "rawContactId", "", "<init>", "(Ljava/lang/String;J)V", "getGalleryPersonUuid", "()Ljava/lang/String;", "getRawContactId", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PersonLink {
    private final String galleryPersonUuid;
    private final long rawContactId;

    public PersonLink(String str, long j2) {
        j.e(str, "galleryPersonUuid");
        this.galleryPersonUuid = str;
        this.rawContactId = j2;
    }

    public static /* synthetic */ PersonLink copy$default(PersonLink personLink, String str, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = personLink.galleryPersonUuid;
        }
        if ((i2 & 2) != 0) {
            j2 = personLink.rawContactId;
        }
        return personLink.copy(str, j2);
    }

    public final String component1() {
        return this.galleryPersonUuid;
    }

    public final long component2() {
        return this.rawContactId;
    }

    public final PersonLink copy(String str, long j2) {
        j.e(str, "galleryPersonUuid");
        return new PersonLink(str, j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonLink)) {
            return false;
        }
        PersonLink personLink = (PersonLink) obj;
        if (j.a(this.galleryPersonUuid, personLink.galleryPersonUuid) && this.rawContactId == personLink.rawContactId) {
            return true;
        }
        return false;
    }

    public final String getGalleryPersonUuid() {
        return this.galleryPersonUuid;
    }

    public final long getRawContactId() {
        return this.rawContactId;
    }

    public int hashCode() {
        return Long.hashCode(this.rawContactId) + (this.galleryPersonUuid.hashCode() * 31);
    }

    public String toString() {
        return "PersonLink(galleryPersonUuid=" + this.galleryPersonUuid + ", rawContactId=" + this.rawContactId + ')';
    }
}
