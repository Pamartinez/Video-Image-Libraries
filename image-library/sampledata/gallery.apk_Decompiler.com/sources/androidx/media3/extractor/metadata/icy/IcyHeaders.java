package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class IcyHeaders implements Metadata.Entry {
    public final int bitrate;
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    public final String name;
    public final String url;

    public IcyHeaders(int i2, String str, String str2, String str3, boolean z, int i7) {
        boolean z3;
        if (i7 == -1 || i7 > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3);
        this.bitrate = i2;
        this.genre = str;
        this.name = str2;
        this.url = str3;
        this.isPublic = z;
        this.metadataInterval = i7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.metadata.icy.IcyHeaders parse(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r14) {
        /*
            java.lang.String r0 = "Invalid metadata interval: "
            java.lang.String r1 = "Invalid bitrate: "
            java.lang.String r2 = "icy-br"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r3 = "IcyHeaders"
            r4 = 1
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            int r7 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            int r7 = r7 * 1000
            if (r7 <= 0) goto L_0x0023
            r1 = r4
            goto L_0x0034
        L_0x0023:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0037 }
            r8.<init>(r1)     // Catch:{ NumberFormatException -> 0x0037 }
            r8.append(r2)     // Catch:{ NumberFormatException -> 0x0037 }
            java.lang.String r1 = r8.toString()     // Catch:{ NumberFormatException -> 0x0037 }
            androidx.media3.common.util.Log.w(r3, r1)     // Catch:{ NumberFormatException -> 0x0037 }
            r1 = r5
            r7 = r6
        L_0x0034:
            r8 = r7
            goto L_0x0040
        L_0x0036:
            r7 = r6
        L_0x0037:
            java.lang.String r1 = "Invalid bitrate header: "
            A.a.C(r1, r2, r3)
            r1 = r5
            goto L_0x0034
        L_0x003e:
            r1 = r5
            r8 = r6
        L_0x0040:
            java.lang.String r2 = "icy-genre"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            r7 = 0
            if (r2 == 0) goto L_0x0054
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r9 = r1
            r1 = r4
            goto L_0x0055
        L_0x0054:
            r9 = r7
        L_0x0055:
            java.lang.String r2 = "icy-name"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0068
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r10 = r1
            r1 = r4
            goto L_0x0069
        L_0x0068:
            r10 = r7
        L_0x0069:
            java.lang.String r2 = "icy-url"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x007c
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r11 = r1
            r1 = r4
            goto L_0x007d
        L_0x007c:
            r11 = r7
        L_0x007d:
            java.lang.String r2 = "icy-pub"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0096
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            r12 = r1
            r1 = r4
            goto L_0x0097
        L_0x0096:
            r12 = r5
        L_0x0097:
            java.lang.String r2 = "icy-metaint"
            java.lang.Object r14 = r14.get(r2)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x00c0
            java.lang.Object r14 = r14.get(r5)
            java.lang.String r14 = (java.lang.String) r14
            int r2 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00c3 }
            if (r2 <= 0) goto L_0x00af
            r6 = r2
            goto L_0x00bf
        L_0x00af:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00c2 }
            r4.<init>(r0)     // Catch:{ NumberFormatException -> 0x00c2 }
            r4.append(r14)     // Catch:{ NumberFormatException -> 0x00c2 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x00c2 }
            androidx.media3.common.util.Log.w(r3, r4)     // Catch:{ NumberFormatException -> 0x00c2 }
            r4 = r1
        L_0x00bf:
            r1 = r4
        L_0x00c0:
            r13 = r6
            goto L_0x00c7
        L_0x00c2:
            r6 = r2
        L_0x00c3:
            A.a.C(r0, r14, r3)
            goto L_0x00c0
        L_0x00c7:
            if (r1 == 0) goto L_0x00ce
            androidx.media3.extractor.metadata.icy.IcyHeaders r7 = new androidx.media3.extractor.metadata.icy.IcyHeaders
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x00ce:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.icy.IcyHeaders.parse(java.util.Map):androidx.media3.extractor.metadata.icy.IcyHeaders");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && IcyHeaders.class == obj.getClass()) {
            IcyHeaders icyHeaders = (IcyHeaders) obj;
            if (this.bitrate != icyHeaders.bitrate || !Objects.equals(this.genre, icyHeaders.genre) || !Objects.equals(this.name, icyHeaders.name) || !Objects.equals(this.url, icyHeaders.url) || this.isPublic != icyHeaders.isPublic || this.metadataInterval != icyHeaders.metadataInterval) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        int i7;
        int i8 = (527 + this.bitrate) * 31;
        String str = this.genre;
        int i10 = 0;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i8 + i2) * 31;
        String str2 = this.name;
        if (str2 != null) {
            i7 = str2.hashCode();
        } else {
            i7 = 0;
        }
        int i12 = (i11 + i7) * 31;
        String str3 = this.url;
        if (str3 != null) {
            i10 = str3.hashCode();
        }
        return ((((i12 + i10) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.name;
        if (str != null) {
            builder.setStation(str);
        }
        String str2 = this.genre;
        if (str2 != null) {
            builder.setGenre(str2);
        }
    }

    public String toString() {
        return "IcyHeaders: name=\"" + this.name + "\", genre=\"" + this.genre + "\", bitrate=" + this.bitrate + ", metadataInterval=" + this.metadataInterval;
    }
}
