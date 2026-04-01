package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionUriGrant;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/gallery/crossapp/function/MediaItem;", "", "", "description", "Landroidx/appfunctions/AppFunctionUriGrant;", "uri", "<init>", "(Ljava/lang/String;Landroidx/appfunctions/AppFunctionUriGrant;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getDescription", "Landroidx/appfunctions/AppFunctionUriGrant;", "getUri", "()Landroidx/appfunctions/AppFunctionUriGrant;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MediaItem {
    private final String description;
    private final AppFunctionUriGrant uri;

    public MediaItem(String str, AppFunctionUriGrant appFunctionUriGrant) {
        j.e(str, "description");
        j.e(appFunctionUriGrant, OCRServiceConstant.KEY_PARAM_URI);
        this.description = str;
        this.uri = appFunctionUriGrant;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (j.a(this.description, mediaItem.description) && j.a(this.uri, mediaItem.uri)) {
            return true;
        }
        return false;
    }

    public final String getDescription() {
        return this.description;
    }

    public final AppFunctionUriGrant getUri() {
        return this.uri;
    }

    public int hashCode() {
        return this.uri.hashCode() + (this.description.hashCode() * 31);
    }

    public String toString() {
        String str = this.description;
        AppFunctionUriGrant appFunctionUriGrant = this.uri;
        return "MediaItem(description=" + str + ", uri=" + appFunctionUriGrant + ")";
    }
}
