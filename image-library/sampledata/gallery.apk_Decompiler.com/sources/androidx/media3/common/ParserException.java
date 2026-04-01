package androidx.media3.common;

import c0.C0086a;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParserException extends IOException {
    public final boolean contentIsMalformed;
    public final int dataType;

    public ParserException(String str, Throwable th, boolean z, int i2) {
        super(str, th);
        this.contentIsMalformed = z;
        this.dataType = i2;
    }

    public static ParserException createForMalformedContainer(String str, Throwable th) {
        return new ParserException(str, th, true, 1);
    }

    public static ParserException createForMalformedDataOfUnknownType(String str, Throwable th) {
        return new ParserException(str, th, true, 0);
    }

    public static ParserException createForUnsupportedContainerFeature(String str) {
        return new ParserException(str, (Throwable) null, false, 1);
    }

    public String getMessage() {
        String str;
        String message = super.getMessage();
        StringBuilder sb2 = new StringBuilder();
        if (message != null) {
            str = message.concat(" ");
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append("{contentIsMalformed=");
        sb2.append(this.contentIsMalformed);
        sb2.append(", dataType=");
        return C0086a.l(sb2, this.dataType, "}");
    }
}
