package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.robin.RobinKitService;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RobinServiceCmd extends BaseCommand {
    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e(r3.TAG, "failed to encode signing sha256 hash key");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x003f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<byte[]> encodeSigningSha256(android.content.Context r4) {
        /*
            r3 = this;
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            java.lang.String r0 = "com.google.android.googlequicksearchbox"
            r1 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r0, r1)     // Catch:{ NameNotFoundException -> 0x0048 }
            android.content.pm.SigningInfo r4 = r4.signingInfo     // Catch:{ NameNotFoundException -> 0x0048 }
            if (r4 == 0) goto L_0x0046
            android.content.pm.Signature[] r4 = r4.getApkContentsSigners()     // Catch:{ NameNotFoundException -> 0x0048 }
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ Exception -> 0x003f }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x003f }
            r2 = 0
            r4 = r4[r2]     // Catch:{ Exception -> 0x003f }
            byte[] r4 = r4.toByteArray()     // Catch:{ Exception -> 0x003f }
            r1.<init>(r4)     // Catch:{ Exception -> 0x003f }
            java.security.cert.Certificate r4 = r0.generateCertificate(r1)     // Catch:{ Exception -> 0x003f }
            java.security.cert.X509Certificate r4 = (java.security.cert.X509Certificate) r4     // Catch:{ Exception -> 0x003f }
            java.lang.String r0 = "SHA-256"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch:{ Exception -> 0x003f }
            byte[] r4 = r4.getEncoded()     // Catch:{ Exception -> 0x003f }
            byte[] r4 = r0.digest(r4)     // Catch:{ Exception -> 0x003f }
            java.util.List r3 = java.util.Collections.singletonList(r4)     // Catch:{ Exception -> 0x003f }
            return r3
        L_0x003f:
            java.lang.String r3 = r3.TAG     // Catch:{ NameNotFoundException -> 0x0048 }
            java.lang.String r4 = "failed to encode signing sha256 hash key"
            com.samsung.android.gallery.support.utils.Log.e(r3, r4)     // Catch:{ NameNotFoundException -> 0x0048 }
        L_0x0046:
            r3 = 0
            return r3
        L_0x0048:
            r3 = move-exception
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.internals.RobinServiceCmd.encodeSigningSha256(android.content.Context):java.util.List");
    }

    private void execute(MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new a(19, this, mediaItem));
    }

    private String getUriString(MediaItem mediaItem) {
        Uri uri = ContentUri.getUri(mediaItem);
        getContext().grantUriPermission("com.google.android.googlequicksearchbox", uri, 1);
        return uri.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(MediaItem mediaItem) {
        new RobinKitService(getContext(), encodeSigningSha256(getContext())).launch(getUriString(mediaItem), mediaItem.getDisplayName(), mediaItem.getMimeType());
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr != null && objArr.length >= 1) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem instanceof MediaItem) {
                execute(mediaItem);
                return;
            }
        }
        String str = this.TAG;
        if (objArr == null) {
            objArr = new Object[]{"null"};
        }
        Log.e((CharSequence) str, "onExecute failed. wrong argument", objArr);
    }
}
