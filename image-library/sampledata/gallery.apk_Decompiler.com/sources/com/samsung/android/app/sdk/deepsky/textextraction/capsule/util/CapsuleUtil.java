package com.samsung.android.app.sdk.deepsky.textextraction.capsule.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.samsung.android.app.sdk.deepsky.textextraction.R$drawable;
import com.samsung.android.app.sdk.deepsky.textextraction.R$string;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000f\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010 \u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\u0007\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b!\u0010\"J!\u0010&\u001a\u00020\f2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010#\u001a\u00020\u0006H\u0000¢\u0006\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/util/CapsuleUtil;", "", "<init>", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "type", "", "getEntityDescription", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;)Ljava/lang/Integer;", "Landroid/content/Context;", "context", "capsuleActionType", "Landroid/net/Uri;", "getEntityIcon$deepsky_sdk_textextraction_8_5_30_release", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;)Landroid/net/Uri;", "getEntityIcon", "getCapsulePriority$deepsky_sdk_textextraction_8_5_30_release", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;)I", "getCapsulePriority", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "entityType", "", "actionId", "convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "convertEntityTypeToCapsuleActionType", "convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release", "(Ljava/lang/String;)Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;", "convertCapsuleActionIdToCapsuleActionType", "entityActionId", "convertEntityActionIdToCapsuleActionId$deepsky_sdk_textextraction_8_5_30_release", "(Ljava/lang/String;)Ljava/lang/String;", "convertEntityActionIdToCapsuleActionId", "getEntityDescription$deepsky_sdk_textextraction_8_5_30_release", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/data/CapsuleActionType;)Ljava/lang/String;", "resId", "getResourceUri$deepsky_sdk_textextraction_8_5_30_release", "(Landroid/content/Context;I)Landroid/net/Uri;", "getResourceUri", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleUtil {
    public static final CapsuleUtil INSTANCE = new CapsuleUtil();

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|32|33|34|35|36|37|38|(2:39|40)|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Can't wrap try/catch for region: R(47:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|32|33|34|35|36|37|38|(2:39|40)|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Can't wrap try/catch for region: R(50:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Can't wrap try/catch for region: R(51:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Can't wrap try/catch for region: R(52:0|1|2|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|61) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0093 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x009b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00ab */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00b3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00bb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00c3 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r2 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_CALL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r3 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_SEND_MESSAGE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r4 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_ADD_CONTACT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                r4 = 4
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r5 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_EMAIL     // Catch:{ NoSuchFieldError -> 0x002b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r0[r5] = r4     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                r5 = 5
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r6 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_WEBSITE     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r0[r6] = r5     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                r6 = 6
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r7 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_ADD_EVENT     // Catch:{ NoSuchFieldError -> 0x003d }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r0[r7] = r6     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                r7 = 7
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r8 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_ADD_REMINDER     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r0[r8] = r7     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                r8 = 8
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_MAP     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r0[r9] = r8     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_CONVERT_UNIT     // Catch:{ NoSuchFieldError -> 0x005a }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r10 = 9
                r0[r9] = r10     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.ENTITY_BANK_ACCOUNT_NUMBER     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r10 = 10
                r0[r9] = r10     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.WALLET_BOARDING_PASS     // Catch:{ NoSuchFieldError -> 0x006e }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r10 = 11
                r0[r9] = r10     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.WALLET_COUPON     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r10 = 12
                r0[r9] = r10     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType r9 = com.samsung.android.app.sdk.deepsky.textextraction.capsule.data.CapsuleActionType.WALLET_MEMBERSHIP     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r10 = 13
                r0[r9] = r10     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                $EnumSwitchMapping$0 = r0
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r9 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.PHONE     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r0[r9] = r1     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.EMAIL     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.URL     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.DATE     // Catch:{ NoSuchFieldError -> 0x00ab }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ab }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00ab }
            L_0x00ab:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.DATE_TIME     // Catch:{ NoSuchFieldError -> 0x00b3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b3 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00b3 }
            L_0x00b3:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.ADDRESS     // Catch:{ NoSuchFieldError -> 0x00bb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bb }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00bb }
            L_0x00bb:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.UNIT     // Catch:{ NoSuchFieldError -> 0x00c3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c3 }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x00c3 }
            L_0x00c3:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.BANK_ACCOUNT_NUMBER     // Catch:{ NoSuchFieldError -> 0x00cb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cb }
                r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x00cb }
            L_0x00cb:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.util.CapsuleUtil.WhenMappings.<clinit>():void");
        }
    }

    private CapsuleUtil() {
    }

    private final Integer getEntityDescription(CapsuleActionType capsuleActionType) {
        switch (WhenMappings.$EnumSwitchMapping$0[capsuleActionType.ordinal()]) {
            case 1:
                return Integer.valueOf(R$string.entity_capsule_title_call);
            case 2:
                return Integer.valueOf(R$string.entity_capsule_title_send_message);
            case 3:
                return Integer.valueOf(R$string.entity_capsule_title_add_to_contact);
            case 4:
                return Integer.valueOf(R$string.entity_capsule_title_email);
            case 5:
                return Integer.valueOf(R$string.entity_capsule_title_website);
            case 6:
                return Integer.valueOf(R$string.entity_capsule_title_add_event);
            case 7:
                return Integer.valueOf(R$string.entity_capsule_title_add_reminder);
            case 8:
                return Integer.valueOf(R$string.entity_capsule_title_map);
            case 9:
                return Integer.valueOf(R$string.entity_capsule_title_convert);
            case 10:
                return Integer.valueOf(R$string.entity_capsule_title_transfer_money);
            default:
                return null;
        }
    }

    public final CapsuleActionType convertCapsuleActionIdToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release(String str) {
        j.e(str, "actionId");
        int hashCode = str.hashCode();
        if (hashCode != 653222902) {
            if (hashCode != 691810669) {
                if (hashCode == 2024260678 && str.equals("Coupon")) {
                    return CapsuleActionType.WALLET_COUPON;
                }
            } else if (str.equals("Boardingpass")) {
                return CapsuleActionType.WALLET_BOARDING_PASS;
            }
        } else if (str.equals("Membership")) {
            return CapsuleActionType.WALLET_MEMBERSHIP;
        }
        return CapsuleActionType.OTHER;
    }

    public final String convertEntityActionIdToCapsuleActionId$deepsky_sdk_textextraction_8_5_30_release(String str) {
        j.e(str, "entityActionId");
        switch (str.hashCode()) {
            case -2059753723:
                if (!str.equals("actions.intent.CREATE_MESSAGE")) {
                    return "";
                }
                return "Send message";
            case -1826210545:
                if (!str.equals("actions.intent.CREATE_CONTACT_POINT")) {
                    return "";
                }
                return "Add contact";
            case -1499795887:
                if (!str.equals("sec.actions.intent.CREATE_REMINDER")) {
                    return "";
                }
                return "Add reminder";
            case 181427200:
                if (!str.equals("actions.intent.CREATE_CALL")) {
                    return "";
                }
                return "Call";
            case 931602683:
                if (!str.equals("sec.actions.intent.CREATE_EVENT")) {
                    return "";
                }
                return "Add event";
            default:
                return "";
        }
    }

    public final CapsuleActionType convertEntityTypeToCapsuleActionType$deepsky_sdk_textextraction_8_5_30_release(EntityType entityType, String str) {
        CapsuleActionType capsuleActionType;
        j.e(entityType, "entityType");
        j.e(str, "actionId");
        switch (WhenMappings.$EnumSwitchMapping$1[entityType.ordinal()]) {
            case 1:
                int hashCode = str.hashCode();
                if (hashCode == 2092670) {
                    if (str.equals("Call")) {
                        capsuleActionType = CapsuleActionType.ENTITY_CALL;
                        break;
                    }
                } else if (hashCode == 1412282255) {
                    if (str.equals("Send message")) {
                        capsuleActionType = CapsuleActionType.ENTITY_SEND_MESSAGE;
                        break;
                    }
                } else if (hashCode == 1515827073 && str.equals("Add contact")) {
                    capsuleActionType = CapsuleActionType.ENTITY_ADD_CONTACT;
                    break;
                }
                capsuleActionType = CapsuleActionType.ENTITY_CALL;
                break;
            case 2:
                capsuleActionType = CapsuleActionType.ENTITY_EMAIL;
                break;
            case 3:
                capsuleActionType = CapsuleActionType.ENTITY_WEBSITE;
                break;
            case 4:
            case 5:
                if (!str.equals("Add event")) {
                    if (!str.equals("Add reminder")) {
                        capsuleActionType = CapsuleActionType.ENTITY_ADD_EVENT;
                        break;
                    } else {
                        capsuleActionType = CapsuleActionType.ENTITY_ADD_REMINDER;
                        break;
                    }
                } else {
                    capsuleActionType = CapsuleActionType.ENTITY_ADD_EVENT;
                    break;
                }
            case 6:
                capsuleActionType = CapsuleActionType.ENTITY_MAP;
                break;
            case 7:
                capsuleActionType = CapsuleActionType.ENTITY_CONVERT_UNIT;
                break;
            case 8:
                capsuleActionType = CapsuleActionType.ENTITY_BANK_ACCOUNT_NUMBER;
                break;
            default:
                capsuleActionType = CapsuleActionType.OTHER;
                break;
        }
        LibLogger.i("CapsuleUtil", "convertToCapsuleActionType, type(" + entityType + "), actionId(" + str + ") => actionType(" + capsuleActionType + ")");
        return capsuleActionType;
    }

    public final int getCapsulePriority$deepsky_sdk_textextraction_8_5_30_release(CapsuleActionType capsuleActionType) {
        j.e(capsuleActionType, "capsuleActionType");
        switch (WhenMappings.$EnumSwitchMapping$0[capsuleActionType.ordinal()]) {
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 1;
            case 4:
                return 4;
            case 5:
                return 8;
            case 6:
                return 5;
            case 7:
                return 3;
            case 8:
                return 7;
            case 9:
                return 6;
            case 10:
            case 11:
            case 12:
            case 13:
                return 10;
            default:
                return 0;
        }
    }

    public final String getEntityDescription$deepsky_sdk_textextraction_8_5_30_release(Context context, CapsuleActionType capsuleActionType) {
        String string;
        j.e(context, "context");
        j.e(capsuleActionType, "type");
        Integer entityDescription = getEntityDescription(capsuleActionType);
        if (entityDescription == null || (string = context.getString(entityDescription.intValue())) == null) {
            return "";
        }
        return string;
    }

    public final Uri getEntityIcon$deepsky_sdk_textextraction_8_5_30_release(Context context, CapsuleActionType capsuleActionType) {
        j.e(context, "context");
        j.e(capsuleActionType, "capsuleActionType");
        switch (WhenMappings.$EnumSwitchMapping$0[capsuleActionType.ordinal()]) {
            case 1:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_call_phone);
            case 2:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_send_to_message);
            case 3:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_add_to_contact);
            case 4:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_email);
            case 5:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_internet_web);
            case 6:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_calendar);
            case 7:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_reminder);
            case 8:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_location);
            case 9:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_convert_unit);
            case 10:
                return getResourceUri$deepsky_sdk_textextraction_8_5_30_release(context, R$drawable.capsule_bank_account_transfer);
            default:
                Uri uri = Uri.EMPTY;
                j.d(uri, "EMPTY");
                return uri;
        }
    }

    public final Uri getResourceUri$deepsky_sdk_textextraction_8_5_30_release(Context context, int i2) {
        Resources resources;
        Uri build;
        if (context != null && (resources = context.getResources()) != null && (build = new Uri.Builder().scheme("android.resource").authority(resources.getResourcePackageName(i2)).appendPath(resources.getResourceTypeName(i2)).appendPath(resources.getResourceEntryName(i2)).build()) != null) {
            return build;
        }
        Uri uri = Uri.EMPTY;
        j.d(uri, "EMPTY");
        return uri;
    }
}
