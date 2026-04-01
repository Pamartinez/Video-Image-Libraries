package com.samsung.android.vexfwk.sdk.docscan;

import Ae.b;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkDocumentDetectionType;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkDocumentDetectionType e;

    public /* synthetic */ a(VexFwkDocumentDetectionType vexFwkDocumentDetectionType, int i2) {
        this.d = i2;
        this.e = vexFwkDocumentDetectionType;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        VexFwkDocumentDetectionType vexFwkDocumentDetectionType = this.e;
        switch (i2) {
            case 0:
                return VexFwkDocDetector.configure$lambda$3$lambda$2$lambda$1(vexFwkDocumentDetectionType, (VexFwkMetadataNative) obj);
            case 1:
                return VexFwkDocDetector.configure$lambda$3$lambda$2(vexFwkDocumentDetectionType, (VexFwkSessionConfigRequest.Builder) obj);
            default:
                return VexFwkDocDetector.configure$lambda$3(vexFwkDocumentDetectionType, (VexFwkDocDetector) obj);
        }
    }
}
