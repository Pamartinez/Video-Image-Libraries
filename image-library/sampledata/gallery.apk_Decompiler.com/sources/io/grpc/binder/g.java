package io.grpc.binder;

import F2.U;
import F2.y0;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import c0.C0087b;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ee.a0;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends c {
    public final /* synthetic */ PackageManager d;
    public final /* synthetic */ U e;

    public g(PackageManager packageManager, y0 y0Var) {
        super(15);
        this.d = packageManager;
        this.e = y0Var;
    }

    public final a0 j(int i2) {
        Signature[] signatureArr;
        PackageManager packageManager = this.d;
        String[] packagesForUid = packageManager.getPackagesForUid(i2);
        if (packagesForUid == null) {
            return a0.f4288j.g("Rejected by (SHA-256 hash signature check) security policy");
        }
        boolean z = false;
        for (String str : packagesForUid) {
            if ("com.google.android.googlequicksearchbox".equals(str)) {
                C0087b bVar = new C0087b(this.e, 1);
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
                    SigningInfo signingInfo = packageInfo.signingInfo;
                    if (signingInfo != null) {
                        if (signingInfo.hasMultipleSigners()) {
                            signatureArr = packageInfo.signingInfo.getApkContentsSigners();
                        } else {
                            signatureArr = packageInfo.signingInfo.getSigningCertificateHistory();
                        }
                        for (Signature apply : signatureArr) {
                            if (bVar.apply(apply)) {
                                return a0.e;
                            }
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                z = true;
            }
        }
        return a0.f4287i.g("Rejected by (SHA-256 hash signature check) security policy. Package name matched: " + z);
    }
}
