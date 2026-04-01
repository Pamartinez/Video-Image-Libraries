package androidx.slice.compat;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SliceProviderCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProviderHolder implements AutoCloseable {
        final ContentProviderClient mProvider;

        public ProviderHolder(ContentProviderClient contentProviderClient) {
            this.mProvider = contentProviderClient;
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mProvider;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }
    }

    private static ProviderHolder acquireClient(ContentResolver contentResolver, Uri uri) {
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient != null) {
            return new ProviderHolder(acquireUnstableContentProviderClient);
        }
        throw new IllegalArgumentException("No provider found for " + uri);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r2 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void grantSlicePermission(android.content.Context r2, java.lang.String r3, java.lang.String r4, android.net.Uri r5) {
        /*
            android.content.ContentResolver r2 = r2.getContentResolver()
            androidx.slice.compat.SliceProviderCompat$ProviderHolder r2 = acquireClient(r2, r5)     // Catch:{ RemoteException -> 0x0039 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x002b }
            r0.<init>()     // Catch:{ all -> 0x002b }
            java.lang.String r1 = "slice_uri"
            r0.putParcelable(r1, r5)     // Catch:{ all -> 0x002b }
            java.lang.String r5 = "provider_pkg"
            r0.putString(r5, r3)     // Catch:{ all -> 0x002b }
            java.lang.String r3 = "pkg"
            r0.putString(r3, r4)     // Catch:{ all -> 0x002b }
            android.content.ContentProviderClient r3 = r2.mProvider     // Catch:{ all -> 0x002b }
            java.lang.String r4 = "grant_perms"
            java.lang.String r5 = "supports_versioned_parcelable"
            r3.call(r4, r5, r0)     // Catch:{ all -> 0x002b }
            r2.close()     // Catch:{ RemoteException -> 0x0039 }
            return
        L_0x002b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002d }
        L_0x002d:
            r4 = move-exception
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ RemoteException -> 0x0039 }
        L_0x0038:
            throw r4     // Catch:{ RemoteException -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            java.lang.String r3 = "SliceProviderCompat"
            java.lang.String r4 = "Unable to get slice descendants"
            android.util.Log.e(r3, r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.compat.SliceProviderCompat.grantSlicePermission(android.content.Context, java.lang.String, java.lang.String, android.net.Uri):void");
    }
}
