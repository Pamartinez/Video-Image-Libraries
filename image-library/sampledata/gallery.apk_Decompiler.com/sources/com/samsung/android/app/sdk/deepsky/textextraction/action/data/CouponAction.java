package com.samsung.android.app.sdk.deepsky.textextraction.action.data;

import Tf.n;
import Tf.v;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.LocaleList;
import com.google.gson.JsonElement;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.f;
import ne.C1192j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u00048\u0016X\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u00118\u0016X\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u0013R\u0014\u0010\u001e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0017R\u0014\u0010\u001f\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u0017R\u001b\u0010#\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\f¨\u0006%"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/CouponAction;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/WalletAction;", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "imageUri", "Lcom/google/gson/JsonObject;", "info", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/google/gson/JsonObject;)V", "", "initExpirationDate", "()J", "", "couponTypeString", "parseCouponType", "(Ljava/lang/String;)Ljava/lang/String;", "", "isSupportOldCoupon", "()Z", "isSupportCoupon", "runAction", "couponType", "Ljava/lang/String;", "iconUri", "Landroid/net/Uri;", "getIconUri", "()Landroid/net/Uri;", "isSupportAction", "Z", "brandName", "couponName", "expirationDate$delegate", "Lme/f;", "getExpirationDate", "expirationDate", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CouponAction extends WalletAction {
    public static final Companion Companion = new Companion((e) null);
    private final String brandName;
    private final String couponName;
    private String couponType = "CODE_128";
    private final f expirationDate$delegate;
    private final Uri iconUri;
    private final boolean isSupportAction;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001b\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/data/CouponAction$Companion;", "", "<init>", "()V", "TAG", "", "WALLET_PACKAGE_NAME", "WALLET_METADATA_KEY", "WALLET_URI", "JSON_KEY_HOW_TO_USE", "JSON_KEY_COUPON_TYPE", "JSON_KEY_COUPON_CODE_VALUE", "JSON_KEY_COUPON_NAME", "JSON_KEY_VALID_YEAR", "JSON_KEY_VALID_MONTH", "JSON_KEY_VALID_DAY", "JSON_KEY_BRAND", "JSON_KEY_OFFER", "INTENT_KEY_COUPON_NAME", "INTENT_KEY_ORIGINAL_IMAGE_URI", "INTENT_KEY_COUPON_IMAGE_URI", "INTENT_KEY_EXPIRATION_DATE", "INTENT_KEY_BRAND_NAME", "INTENT_KEY_MEMO", "INTENT_KEY_BARCODE_FORMAT", "INTENT_KEY_BARCODE_SERIAL_NUMBER", "INTENT_KEY_NEED_COUPON_IMAGE_EXTRACTION", "INTENT_KEY_ADD_FROM", "NA", "BARCODE_TYPE_CODE_128", "BARCODE_PREFIX", "SUPPORTED_FEATURE_COUPONS", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        if (r4 == null) goto L_0x0056;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CouponAction(android.content.Context r4, android.net.Uri r5, com.google.gson.JsonObject r6) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "imageUri"
            kotlin.jvm.internal.j.e(r5, r0)
            java.lang.String r0 = "info"
            kotlin.jvm.internal.j.e(r6, r0)
            r3.<init>(r4, r5, r6)
            java.lang.String r5 = "CODE_128"
            r3.couponType = r5
            int r5 = com.samsung.android.app.sdk.deepsky.textextraction.R$drawable.capsule_coupon
            android.net.Uri r4 = r3.getResourceUri(r5, r4)
            r3.iconUri = r4
            boolean r4 = r3.isSupportCoupon()
            if (r4 != 0) goto L_0x002d
            boolean r4 = r3.isSupportOldCoupon()
            if (r4 == 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r4 = 0
            goto L_0x002e
        L_0x002d:
            r4 = 1
        L_0x002e:
            if (r4 != 0) goto L_0x0037
            java.lang.String r5 = "CouponAction"
            java.lang.String r0 = "Wallet app doesn't support coupon"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r5, r0)
        L_0x0037:
            r3.isSupportAction = r4
            java.lang.String r4 = "Brand"
            com.google.gson.JsonElement r4 = r6.get(r4)
            java.lang.String r5 = ""
            java.lang.String r0 = "N/A"
            r1 = 0
            if (r4 == 0) goto L_0x0056
            java.lang.String r4 = r4.getAsString()
            if (r4 == 0) goto L_0x0056
            boolean r2 = r4.equals(r0)
            if (r2 != 0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r4 = r1
        L_0x0054:
            if (r4 != 0) goto L_0x0057
        L_0x0056:
            r4 = r5
        L_0x0057:
            r3.brandName = r4
            java.lang.String r4 = "CouponName"
            com.google.gson.JsonElement r4 = r6.get(r4)
            if (r4 == 0) goto L_0x0066
            java.lang.String r4 = r4.getAsString()
            goto L_0x0067
        L_0x0066:
            r4 = r1
        L_0x0067:
            boolean r6 = kotlin.jvm.internal.j.a(r4, r0)
            if (r6 != 0) goto L_0x0070
            if (r4 == 0) goto L_0x0070
            r1 = r4
        L_0x0070:
            if (r1 != 0) goto L_0x0073
            goto L_0x0074
        L_0x0073:
            r5 = r1
        L_0x0074:
            r3.couponName = r5
            Sf.q r4 = new Sf.q
            r5 = 12
            r4.<init>(r5, r3)
            me.m r4 = L1.d.q(r4)
            r3.expirationDate$delegate = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.action.data.CouponAction.<init>(android.content.Context, android.net.Uri, com.google.gson.JsonObject):void");
    }

    /* access modifiers changed from: private */
    public static final long expirationDate_delegate$lambda$3(CouponAction couponAction) {
        return couponAction.initExpirationDate();
    }

    private final long getExpirationDate() {
        return ((Number) this.expirationDate$delegate.getValue()).longValue();
    }

    private final long initExpirationDate() {
        String str;
        String str2;
        String str3;
        Locale locale;
        Date parse;
        Instant instant;
        Configuration configuration;
        LocaleList locales;
        JsonElement jsonElement = getInfo().get("ValidYear");
        if (jsonElement != null) {
            str = jsonElement.getAsString();
        } else {
            str = null;
        }
        JsonElement jsonElement2 = getInfo().get("ValidMonth");
        if (jsonElement2 != null) {
            str2 = jsonElement2.getAsString();
        } else {
            str2 = null;
        }
        JsonElement jsonElement3 = getInfo().get("ValidDay");
        if (jsonElement3 != null) {
            str3 = jsonElement3.getAsString();
        } else {
            str3 = null;
        }
        String str4 = str2 + "/" + str3 + "/" + str;
        Resources resources = getContext().getResources();
        if (resources == null || (configuration = resources.getConfiguration()) == null || (locales = configuration.getLocales()) == null) {
            locale = null;
        } else {
            locale = locales.get(0);
        }
        if (C1192j.z0(new String[]{str, str2, str3}).contains("N/A") || C1192j.z0(new String[]{str, str2, str3}).contains((Object) null) || (parse = new SimpleDateFormat("MM/dd/yyyy", locale).parse(str4)) == null || (instant = parse.toInstant()) == null) {
            return -1;
        }
        return instant.toEpochMilli();
    }

    private final boolean isSupportCoupon() {
        return isSupportFeature("COUPONS");
    }

    private final boolean isSupportOldCoupon() {
        ApplicationInfo applicationInfo = null;
        try {
            PackageManager packageManager = getContext().getPackageManager();
            if (packageManager != null) {
                applicationInfo = packageManager.getApplicationInfo("com.samsung.android.spay", 128);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LibLogger.i("CouponAction", "Can't find wallet app : " + e);
        } catch (IllegalArgumentException e7) {
            LibLogger.i("CouponAction", "Can't find wallet app : " + e7);
        } catch (Exception e8) {
            LibLogger.i("CouponAction", "Exception during check old wallet app : " + e8);
        }
        if (applicationInfo != null) {
            return applicationInfo.metaData.getBoolean("com.samsung.android.spay.vas.coupons.support.addFromExternalApp", false);
        }
        return false;
    }

    private final String parseCouponType(String str) {
        if (n.u0(str, "Barcode_")) {
            return v.s0(str, "Barcode_", "");
        }
        return str;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public boolean isSupportAction() {
        return this.isSupportAction;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005f, code lost:
        if (r4 == null) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean runAction() {
        /*
            r7 = this;
            java.lang.String r0 = "runAction"
            java.lang.String r1 = "CouponAction"
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r1, r0)
            boolean r0 = r7.isSupportAction()
            r2 = 1
            if (r0 == 0) goto L_0x010c
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r3 = "extra_key_coupon_name"
            java.lang.String r4 = r7.couponName
            r0.putString(r3, r4)
            java.lang.String r3 = "extra_key_original_image_uri"
            android.net.Uri r4 = r7.getImageUri()
            r0.putParcelable(r3, r4)
            long r3 = r7.getExpirationDate()
            r5 = -1
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x0036
            java.lang.String r3 = "extra_key_expiration_date"
            long r4 = r7.getExpirationDate()
            r0.putLong(r3, r4)
        L_0x0036:
            java.lang.String r3 = "extra_key_brand_name"
            java.lang.String r4 = r7.brandName
            r0.putString(r3, r4)
            com.google.gson.JsonObject r3 = r7.getInfo()
            java.lang.String r4 = "HowToUse"
            com.google.gson.JsonElement r4 = r3.get(r4)
            if (r4 == 0) goto L_0x0061
            java.lang.String r4 = r4.getAsString()
            if (r4 == 0) goto L_0x0061
            int r5 = r4.length()
            if (r5 <= 0) goto L_0x005e
            java.lang.String r5 = "N/A"
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r4 = 0
        L_0x005f:
            if (r4 != 0) goto L_0x0063
        L_0x0061:
            java.lang.String r4 = ""
        L_0x0063:
            java.lang.String r5 = "extra_key_memo"
            r0.putString(r5, r4)
            java.lang.String r4 = "CouponType"
            com.google.gson.JsonElement r4 = r3.get(r4)
            if (r4 == 0) goto L_0x007f
            java.lang.String r4 = r4.getAsString()
            java.lang.String r5 = "getAsString(...)"
            kotlin.jvm.internal.j.d(r4, r5)
            java.lang.String r4 = r7.parseCouponType(r4)
            r7.couponType = r4
        L_0x007f:
            java.lang.String r4 = r7.couponType
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x0098
            java.lang.String r4 = "CouponCodeValue"
            com.google.gson.JsonElement r3 = r3.get(r4)
            if (r3 == 0) goto L_0x0098
            java.lang.String r4 = "extra_key_barcode_serial_number"
            java.lang.String r3 = r3.getAsString()
            r0.putString(r4, r3)
        L_0x0098:
            java.lang.String r3 = r7.couponType
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x00a7
            java.lang.String r3 = "extra_key_barcode_format"
            java.lang.String r4 = r7.couponType
            r0.putString(r3, r4)
        L_0x00a7:
            android.content.Context r3 = r7.getContext()
            java.lang.String r4 = "com.samsung.android.spay"
            android.net.Uri r5 = r7.getImageUri()
            r3.grantUriPermission(r4, r5, r2)
            java.lang.String r3 = "extra_key_need_coupon_image_extraction"
            r0.putBoolean(r3, r2)
            android.content.Context r3 = r7.getContext()
            java.lang.String r3 = r7.getSourceName(r3)
            java.lang.String r4 = "extra_key_add_from"
            r0.putString(r4, r3)
            java.lang.String r3 = r7.couponName
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x00d0
            r3 = r2
            goto L_0x00d1
        L_0x00d0:
            r3 = 0
        L_0x00d1:
            android.net.Uri r4 = r7.getImageUri()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Send coupon intent : "
            r5.<init>(r6)
            r5.append(r3)
            java.lang.String r3 = ", "
            r5.append(r3)
            r5.append(r4)
            java.lang.String r3 = r5.toString()
            com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger.i(r1, r3)
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            r3 = 268468224(0x10008000, float:2.5342157E-29)
            r1.setFlags(r3)
            java.lang.String r3 = "samsungpay://launch?action=coupons_add"
            android.net.Uri r3 = android.net.Uri.parse(r3)
            r1.setData(r3)
            r1.putExtras(r0)
            android.content.Context r7 = r7.getContext()
            r7.startActivity(r1)
        L_0x010c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.action.data.CouponAction.runAction():boolean");
    }
}
