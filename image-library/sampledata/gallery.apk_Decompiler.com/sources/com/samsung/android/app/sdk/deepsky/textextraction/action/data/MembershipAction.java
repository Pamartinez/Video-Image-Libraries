package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\fR\u001a\u0010\u000e\u001a\u00020\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\n8\u0016X\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/MembershipAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/WalletAction;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "info", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "isSupportMembership", "()Z", "runAction", "iconUri", "Landroid/net/Uri;", "getIconUri", "()Landroid/net/Uri;", "isSupportAction", "Z", "", "membershipName", "Ljava/lang/String;", "qrData", "qrFormat", "Landroid/graphics/Rect;", "barcodeRect", "Landroid/graphics/Rect;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MembershipAction extends WalletAction {
    public static final Companion Companion = new Companion((e) null);
    private final Rect barcodeRect;
    private final Uri iconUri;
    private final boolean isSupportAction;
    private final String membershipName;
    private final String qrData;
    private final String qrFormat;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/MembershipAction$Companion;", "", "<init>", "()V", "TAG", "", "WALLET_PACKAGE_NAME", "WALLET_URI", "JSON_KEY_MEMBERSHIP_NAME", "JSON_KEY_MEMBERSHIP_IMG", "JSON_KEY_IS_MEMBERSHIP_IMG", "JSON_KEY_QR_CODE_VALUE", "JSON_KEY_QR_CODE_TYPE", "INTENT_KEY_MEMBERSHIP_NAME", "INTENT_KEY_SCREEN_IMG", "INTENT_KEY_MEMBERSHIP_IMG", "INTENT_KEY_IS_MEMBERSHIP_IMG", "INTENT_KEY_QR_DATA", "INTENT_KEY_QR_FORMAT", "INTENT_KEY_ADD_FROM", "NA", "SUPPORTED_FEATURE_MEMBERSHIP", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0041, code lost:
        if (r2 == null) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MembershipAction(android.content.Context r2, android.net.Uri r3, com.google.gson.JsonObject r4) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "imageUri"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "info"
            kotlin.jvm.internal.j.e(r4, r0)
            r1.<init>(r2, r3, r4)
            int r3 = com.samsung.android.app.sdk.deepsky.textextraction.R$drawable.capsule_membership
            android.net.Uri r2 = r1.getResourceUri(r3, r2)
            r1.iconUri = r2
            boolean r2 = r1.isSupportMembership()
            if (r2 != 0) goto L_0x0027
            java.lang.String r3 = "MembershipAction"
            java.lang.String r0 = "Wallet doesn't support Membership."
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r3, r0)
        L_0x0027:
            r1.isSupportAction = r2
            java.lang.String r2 = "membershipName"
            com.google.gson.JsonElement r2 = r4.get(r2)
            if (r2 == 0) goto L_0x0043
            java.lang.String r2 = r2.getAsString()
            if (r2 == 0) goto L_0x0043
            java.lang.String r3 = "N/A"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0040
            goto L_0x0041
        L_0x0040:
            r2 = 0
        L_0x0041:
            if (r2 != 0) goto L_0x0045
        L_0x0043:
            java.lang.String r2 = "Membership Card"
        L_0x0045:
            r1.membershipName = r2
            java.lang.String r2 = "QrCodeValue"
            com.google.gson.JsonElement r2 = r4.get(r2)
            java.lang.String r3 = ""
            if (r2 == 0) goto L_0x0057
            java.lang.String r2 = r2.getAsString()
            if (r2 != 0) goto L_0x0058
        L_0x0057:
            r2 = r3
        L_0x0058:
            r1.qrData = r2
            java.lang.String r2 = "QrCodeType"
            com.google.gson.JsonElement r2 = r4.get(r2)
            if (r2 == 0) goto L_0x006a
            java.lang.String r2 = r2.getAsString()
            if (r2 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r3 = r2
        L_0x006a:
            r1.qrFormat = r3
            java.lang.String r2 = "RECT"
            com.google.gson.JsonElement r2 = r4.get(r2)
            if (r2 == 0) goto L_0x0080
            java.lang.String r2 = r2.getAsString()
            if (r2 == 0) goto L_0x0080
            android.graphics.Rect r2 = android.graphics.Rect.unflattenFromString(r2)
            if (r2 != 0) goto L_0x0085
        L_0x0080:
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
        L_0x0085:
            r1.barcodeRect = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.action.data.MembershipAction.<init>(android.content.Context, android.net.Uri, com.google.gson.JsonObject):void");
    }

    private final boolean isSupportMembership() {
        return isSupportFeature("MEMBERSHIP");
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public boolean isSupportAction() {
        return this.isSupportAction;
    }

    public boolean runAction() {
        LibLogger.i("MembershipAction", "runAction");
        if (isSupportAction()) {
            Bundle bundle = new Bundle();
            bundle.putString("extra_key_name", this.membershipName);
            bundle.putParcelable("extra_key_original_image_uri", getImageUri());
            getContext().grantUriPermission("com.samsung.android.spay", getImageUri(), 1);
            bundle.putString("extra_key_crop_image_uri", "membershipImg");
            bundle.putBoolean("extra_key_need_crop_image_extraction", false);
            bundle.putString("extra_key_barcode_serial_number", this.qrData);
            bundle.putString("extra_key_barcode_format", this.qrFormat);
            bundle.putString("extra_key_add_from", getSourceName(getContext()));
            String str = this.membershipName;
            Uri imageUri = getImageUri();
            String str2 = this.qrData;
            String str3 = this.qrFormat;
            LibLogger.i("MembershipAction", "Insert membershipName : " + str + ", URi: " + imageUri + ", IS_MEMBERSHIP_IMG: false, qrData: " + str2 + ", qrFormat: " + str3);
            Intent intent = new Intent();
            intent.setData(Uri.parse("samsungpay://launch?action=membership_add"));
            intent.setFlags(268435456);
            intent.putExtras(bundle);
            getContext().startActivity(intent);
        }
        return true;
    }
}
