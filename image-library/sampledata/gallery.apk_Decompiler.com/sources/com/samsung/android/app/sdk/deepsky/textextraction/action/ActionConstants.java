package com.samsung.android.app.sdk.deepsky.textextraction.action;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import me.i;
import ne.C1192j;
import ne.z;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/action/ActionConstants;", "", "<init>", "()V", "", "", "", "BARCODE_LOOKUP_TABLE", "Ljava/util/Map;", "getBARCODE_LOOKUP_TABLE", "()Ljava/util/Map;", "", "SUPPORTED_BARCODE_FORMATS_IN_CN", "Ljava/util/Set;", "getSUPPORTED_BARCODE_FORMATS_IN_CN", "()Ljava/util/Set;", "", "ACTION_CATEGORY_LIST", "[Ljava/lang/String;", "getACTION_CATEGORY_LIST", "()[Ljava/lang/String;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActionConstants {
    private static final String[] ACTION_CATEGORY_LIST = {"Coupon", "Location", "Schedule", "Book", "Food", "Boardingpass", "Membership", "Shopping"};
    private static final Map<String, Integer> BARCODE_LOOKUP_TABLE = z.b0(new i("UNKNOWN", -1), new i("ALL_FORMATS", 0), new i("CODE_128", 1), new i("CODE_39", 2), new i("CODE_93", 4), new i("CODABAR", 8), new i("DATA_MATRIX", 16), new i("EAN_13", 32), new i("EAN_8", 64), new i("ITF", 128), new i("QR_CODE", 256), new i("UPC_A", 512), new i("UPC_E", 1024), new i("PDF_417", 2048), new i("AZTEC", 4096));
    public static final ActionConstants INSTANCE = new ActionConstants();
    private static final Set<String> SUPPORTED_BARCODE_FORMATS_IN_CN = C1192j.z0(new String[]{"DATA_MATRIX", "QR_CODE", "PDF_417", "AZTEC"});

    private ActionConstants() {
    }

    public final String[] getACTION_CATEGORY_LIST() {
        return ACTION_CATEGORY_LIST;
    }

    public final Map<String, Integer> getBARCODE_LOOKUP_TABLE() {
        return BARCODE_LOOKUP_TABLE;
    }

    public final Set<String> getSUPPORTED_BARCODE_FORMATS_IN_CN() {
        return SUPPORTED_BARCODE_FORMATS_IN_CN;
    }
}
