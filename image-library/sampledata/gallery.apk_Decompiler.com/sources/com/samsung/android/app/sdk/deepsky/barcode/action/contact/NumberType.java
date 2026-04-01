package com.samsung.android.app.sdk.deepsky.barcode.action.contact;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/contact/NumberType;", "", "contactPhoneType", "", "<init>", "(Ljava/lang/String;II)V", "getContactPhoneType", "()I", "HOME_FAX", "WORK_FAX", "HOME", "WORK", "CELL", "TEL", "VOICE", "OTHER", "PAGER", "CALLBACK", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum NumberType {
    HOME_FAX(5),
    WORK_FAX(4),
    HOME(1),
    WORK(3),
    CELL(2),
    TEL(2),
    VOICE(7),
    OTHER(7),
    PAGER(6),
    CALLBACK(8);
    
    private final int contactPhoneType;

    static {
        NumberType[] $values;
        $ENTRIES = c.t($values);
    }

    private NumberType(int i2) {
        this.contactPhoneType = i2;
    }

    public final int getContactPhoneType() {
        return this.contactPhoneType;
    }
}
