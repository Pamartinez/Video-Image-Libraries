package com.samsung.android.app.sdk.deepsky.textextraction.entity;

import com.samsung.android.sdk.globalpostprocmgr.IGPPDBInterface;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import ne.C1195m;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "", "typeId", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getTypeId", "()Ljava/lang/String;", "UNKNOWN", "EMAIL", "PHONE", "URL", "DATE_TIME", "ADDRESS", "DATE", "FLIGHT_NUMBER", "UNIT", "BANK_ACCOUNT_NUMBER", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum EntityType {
    UNKNOWN(""),
    EMAIL("email"),
    PHONE("phone"),
    URL("url"),
    DATE_TIME(IGPPDBInterface.IRequestQueue.FIELD_REQUEST_DATETIME),
    ADDRESS("address"),
    DATE(BuddyContract.Event.DATE),
    FLIGHT_NUMBER("flight"),
    UNIT("unit"),
    BANK_ACCOUNT_NUMBER("bank_account_number");
    
    public static final Companion Companion = null;
    private final String typeId;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType$Companion;", "", "<init>", "()V", "toEntityType", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "typeId", "", "listOfEntityType", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final List<EntityType> listOfEntityType() {
            return C1195m.q0(EntityType.ADDRESS, EntityType.EMAIL, EntityType.FLIGHT_NUMBER, EntityType.PHONE, EntityType.URL, EntityType.DATE_TIME, EntityType.DATE, EntityType.BANK_ACCOUNT_NUMBER, EntityType.UNIT);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0081, code lost:
            if (r1.equals("phone_e164") == false) goto L_0x0083;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0085, code lost:
            return com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.UNKNOWN;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0088, code lost:
            return com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.PHONE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0022, code lost:
            if (r1.equals("phone") == false) goto L_0x0083;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType toEntityType(java.lang.String r1) {
            /*
                r0 = this;
                java.lang.String r0 = "typeId"
                kotlin.jvm.internal.j.e(r1, r0)
                int r0 = r1.hashCode()
                switch(r0) {
                    case -1824434085: goto L_0x007b;
                    case -1422091778: goto L_0x006f;
                    case -1271823248: goto L_0x0063;
                    case -1147692044: goto L_0x0057;
                    case 116079: goto L_0x004a;
                    case 3076014: goto L_0x003e;
                    case 3594628: goto L_0x0031;
                    case 96619420: goto L_0x0025;
                    case 106642798: goto L_0x001c;
                    case 1793702779: goto L_0x000f;
                    default: goto L_0x000d;
                }
            L_0x000d:
                goto L_0x0083
            L_0x000f:
                java.lang.String r0 = "datetime"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0019
                goto L_0x0083
            L_0x0019:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.DATE_TIME
                return r0
            L_0x001c:
                java.lang.String r0 = "phone"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0086
                goto L_0x0083
            L_0x0025:
                java.lang.String r0 = "email"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x002e
                goto L_0x0083
            L_0x002e:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.EMAIL
                return r0
            L_0x0031:
                java.lang.String r0 = "unit"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x003b
                goto L_0x0083
            L_0x003b:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.UNIT
                return r0
            L_0x003e:
                java.lang.String r0 = "date"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0047
                goto L_0x0083
            L_0x0047:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.DATE
                return r0
            L_0x004a:
                java.lang.String r0 = "url"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0054
                goto L_0x0083
            L_0x0054:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.URL
                return r0
            L_0x0057:
                java.lang.String r0 = "address"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0060
                goto L_0x0083
            L_0x0060:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.ADDRESS
                return r0
            L_0x0063:
                java.lang.String r0 = "flight"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x006c
                goto L_0x0083
            L_0x006c:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.FLIGHT_NUMBER
                return r0
            L_0x006f:
                java.lang.String r0 = "bank_account_number"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0078
                goto L_0x0083
            L_0x0078:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.BANK_ACCOUNT_NUMBER
                return r0
            L_0x007b:
                java.lang.String r0 = "phone_e164"
                boolean r0 = r1.equals(r0)
                if (r0 != 0) goto L_0x0086
            L_0x0083:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.UNKNOWN
                return r0
            L_0x0086:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.PHONE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.Companion.toEntityType(java.lang.String):com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType");
        }

        private Companion() {
        }
    }

    static {
        EntityType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private EntityType(String str) {
        this.typeId = str;
    }

    public final String getTypeId() {
        return this.typeId;
    }
}
