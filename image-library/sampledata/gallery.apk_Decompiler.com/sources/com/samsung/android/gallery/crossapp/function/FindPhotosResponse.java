package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionTextResource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/FindPhotosResponse;", "", "", "Lcom/samsung/android/gallery/crossapp/function/MediaItem;", "mediaItems", "Landroidx/appfunctions/AppFunctionTextResource;", "resources", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getMediaItems", "()Ljava/util/List;", "getResources", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FindPhotosResponse {
    private final List<MediaItem> mediaItems;
    private final List<AppFunctionTextResource> resources;

    public FindPhotosResponse(List<MediaItem> list, List<AppFunctionTextResource> list2) {
        j.e(list, "mediaItems");
        j.e(list2, "resources");
        this.mediaItems = list;
        this.resources = list2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FindPhotosResponse)) {
            return false;
        }
        FindPhotosResponse findPhotosResponse = (FindPhotosResponse) obj;
        if (j.a(this.mediaItems, findPhotosResponse.mediaItems) && j.a(this.resources, findPhotosResponse.resources)) {
            return true;
        }
        return false;
    }

    public final List<MediaItem> getMediaItems() {
        return this.mediaItems;
    }

    public List<AppFunctionTextResource> getResources() {
        return this.resources;
    }

    public int hashCode() {
        return this.resources.hashCode() + (this.mediaItems.hashCode() * 31);
    }

    public String toString() {
        List<MediaItem> list = this.mediaItems;
        List<AppFunctionTextResource> list2 = this.resources;
        return "FindPhotosResponse(mediaItems=" + list + ", resources=" + list2 + ")";
    }
}
