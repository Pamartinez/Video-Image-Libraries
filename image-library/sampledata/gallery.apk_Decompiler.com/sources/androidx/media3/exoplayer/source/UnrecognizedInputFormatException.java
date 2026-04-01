package androidx.media3.exoplayer.source;

import F2.U;
import android.net.Uri;
import androidx.media3.common.ParserException;
import androidx.media3.extractor.SniffFailure;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UnrecognizedInputFormatException extends ParserException {
    public final U sniffFailures;
    public final Uri uri;

    public UnrecognizedInputFormatException(String str, Uri uri2, List<? extends SniffFailure> list) {
        super(str, (Throwable) null, false, 1);
        this.uri = uri2;
        this.sniffFailures = U.y(list);
    }
}
