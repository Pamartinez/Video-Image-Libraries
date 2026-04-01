package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new Object();
    private static final LruCache<ProviderCacheKey, ProviderInfo> sProviderCache = new LruCache<>(2);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ContentQueryWrapper {
        static ContentQueryWrapper make(Context context, Uri uri) {
            return new ContentQueryWrapperApi24Impl(context, uri);
        }

        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        public ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProviderCacheKey {
        String mAuthority;
        List<List<byte[]>> mCertificates;
        String mPackageName;

        public ProviderCacheKey(String str, String str2, List<List<byte[]>> list) {
            this.mAuthority = str;
            this.mPackageName = str2;
            this.mCertificates = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProviderCacheKey)) {
                return false;
            }
            ProviderCacheKey providerCacheKey = (ProviderCacheKey) obj;
            if (!Objects.equals(this.mAuthority, providerCacheKey.mAuthority) || !Objects.equals(this.mPackageName, providerCacheKey.mPackageName) || !Objects.equals(this.mCertificates, providerCacheKey.mCertificates)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mAuthority, this.mPackageName, this.mCertificates});
        }
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    public static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, List<FontRequest> list, CancellationSignal cancellationSignal) {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                FontRequest fontRequest = list.get(i2);
                ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
                if (provider == null) {
                    return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
                }
                arrayList.add(query(context, fontRequest, provider.authority, cancellationSignal));
            }
            FontsContractCompat.FontFamilyResult create = FontsContractCompat.FontFamilyResult.create(0, (List<FontsContractCompat.FontInfo[]>) arrayList);
            Trace.endSection();
            return create;
        } finally {
            Trace.endSection();
        }
    }

    public static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) {
        Trace.beginSection("FontProvider.getProvider");
        try {
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            ProviderCacheKey providerCacheKey = new ProviderCacheKey(fontRequest.getProviderAuthority(), fontRequest.getProviderPackage(), certificates);
            ProviderInfo providerInfo = sProviderCache.get(providerCacheKey);
            if (providerInfo != null) {
                return providerInfo;
            }
            String providerAuthority = fontRequest.getProviderAuthority();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
            if (resolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
            } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
                List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
                Collections.sort(convertToByteArrayList, sByteArrayComparator);
                for (int i2 = 0; i2 < certificates.size(); i2++) {
                    ArrayList arrayList = new ArrayList(certificates.get(i2));
                    Collections.sort(arrayList, sByteArrayComparator);
                    if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                        sProviderCache.put(providerCacheKey, resolveContentProvider);
                        Trace.endSection();
                        return resolveContentProvider;
                    }
                }
                Trace.endSection();
                return null;
            } else {
                throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
            }
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b = bArr[i2];
            byte b5 = bArr2[i2];
            if (b != b5) {
                return b - b5;
            }
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0110 A[SYNTHETIC, Splitter:B:57:0x0110] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r16, androidx.core.provider.FontRequest r17, java.lang.String r18, android.os.CancellationSignal r19) {
        /*
            r0 = r18
            java.lang.String r1 = "content"
            java.lang.String r2 = "FontProvider.query"
            androidx.tracing.Trace.beginSection(r2)
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0117 }
            r2.<init>()     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r3 = new android.net.Uri$Builder     // Catch:{ all -> 0x0117 }
            r3.<init>()     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r3 = r3.scheme(r1)     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r3 = r3.authority(r0)     // Catch:{ all -> 0x0117 }
            android.net.Uri r5 = r3.build()     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r3 = new android.net.Uri$Builder     // Catch:{ all -> 0x0117 }
            r3.<init>()     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r1 = r3.scheme(r1)     // Catch:{ all -> 0x0117 }
            android.net.Uri$Builder r0 = r1.authority(r0)     // Catch:{ all -> 0x0117 }
            java.lang.String r1 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r1)     // Catch:{ all -> 0x0117 }
            android.net.Uri r0 = r0.build()     // Catch:{ all -> 0x0117 }
            r1 = r16
            androidx.core.provider.FontProvider$ContentQueryWrapper r4 = androidx.core.provider.FontProvider.ContentQueryWrapper.make(r1, r5)     // Catch:{ all -> 0x0117 }
            r1 = 0
            java.lang.String r6 = "_id"
            java.lang.String r7 = "file_id"
            java.lang.String r8 = "font_ttc_index"
            java.lang.String r9 = "font_variation_settings"
            java.lang.String r10 = "font_weight"
            java.lang.String r11 = "font_italic"
            java.lang.String r12 = "result_code"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12}     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = "ContentQueryWrapper.query"
            androidx.tracing.Trace.beginSection(r3)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = "query = ?"
            java.lang.String r3 = r17.getQuery()     // Catch:{ all -> 0x0107 }
            java.lang.String[] r8 = new java.lang.String[]{r3}     // Catch:{ all -> 0x0107 }
            r9 = 0
            r10 = r19
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0107 }
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x00a7 }
            if (r1 == 0) goto L_0x00f0
            int r6 = r1.getCount()     // Catch:{ all -> 0x00a7 }
            if (r6 <= 0) goto L_0x00f0
            java.lang.String r2 = "result_code"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ all -> 0x00a7 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x00a7 }
            r6.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = "_id"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r8 = "file_id"
            int r8 = r1.getColumnIndex(r8)     // Catch:{ all -> 0x00a7 }
            java.lang.String r9 = "font_ttc_index"
            int r9 = r1.getColumnIndex(r9)     // Catch:{ all -> 0x00a7 }
            java.lang.String r10 = "font_weight"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ all -> 0x00a7 }
            java.lang.String r11 = "font_italic"
            int r11 = r1.getColumnIndex(r11)     // Catch:{ all -> 0x00a7 }
        L_0x0099:
            boolean r12 = r1.moveToNext()     // Catch:{ all -> 0x00a7 }
            if (r12 == 0) goto L_0x00ec
            r12 = -1
            if (r2 == r12) goto L_0x00ac
            int r13 = r1.getInt(r2)     // Catch:{ all -> 0x00a7 }
            goto L_0x00ad
        L_0x00a7:
            r0 = move-exception
            r16 = r4
            goto L_0x010e
        L_0x00ac:
            r13 = 0
        L_0x00ad:
            if (r9 == r12) goto L_0x00b4
            int r14 = r1.getInt(r9)     // Catch:{ all -> 0x00a7 }
            goto L_0x00b5
        L_0x00b4:
            r14 = 0
        L_0x00b5:
            if (r8 != r12) goto L_0x00c4
            r16 = r4
            long r3 = r1.getLong(r7)     // Catch:{ all -> 0x00c2 }
            android.net.Uri r3 = android.content.ContentUris.withAppendedId(r5, r3)     // Catch:{ all -> 0x00c2 }
            goto L_0x00ce
        L_0x00c2:
            r0 = move-exception
            goto L_0x010e
        L_0x00c4:
            r16 = r4
            long r3 = r1.getLong(r8)     // Catch:{ all -> 0x00c2 }
            android.net.Uri r3 = android.content.ContentUris.withAppendedId(r0, r3)     // Catch:{ all -> 0x00c2 }
        L_0x00ce:
            if (r10 == r12) goto L_0x00d5
            int r4 = r1.getInt(r10)     // Catch:{ all -> 0x00c2 }
            goto L_0x00d7
        L_0x00d5:
            r4 = 400(0x190, float:5.6E-43)
        L_0x00d7:
            if (r11 == r12) goto L_0x00e1
            int r12 = r1.getInt(r11)     // Catch:{ all -> 0x00c2 }
            r15 = 1
            if (r12 != r15) goto L_0x00e1
            goto L_0x00e2
        L_0x00e1:
            r15 = 0
        L_0x00e2:
            androidx.core.provider.FontsContractCompat$FontInfo r3 = androidx.core.provider.FontsContractCompat.FontInfo.create(r3, r14, r4, r15, r13)     // Catch:{ all -> 0x00c2 }
            r6.add(r3)     // Catch:{ all -> 0x00c2 }
            r4 = r16
            goto L_0x0099
        L_0x00ec:
            r16 = r4
            r2 = r6
            goto L_0x00f2
        L_0x00f0:
            r16 = r4
        L_0x00f2:
            if (r1 == 0) goto L_0x00f7
            r1.close()     // Catch:{ all -> 0x0117 }
        L_0x00f7:
            r16.close()     // Catch:{ all -> 0x0117 }
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]     // Catch:{ all -> 0x0117 }
            java.lang.Object[] r0 = r2.toArray(r0)     // Catch:{ all -> 0x0117 }
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0     // Catch:{ all -> 0x0117 }
            androidx.tracing.Trace.endSection()
            return r0
        L_0x0107:
            r0 = move-exception
            r16 = r4
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x010e:
            if (r1 == 0) goto L_0x0113
            r1.close()     // Catch:{ all -> 0x0117 }
        L_0x0113:
            r16.close()     // Catch:{ all -> 0x0117 }
            throw r0     // Catch:{ all -> 0x0117 }
        L_0x0117:
            r0 = move-exception
            androidx.tracing.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }
}
