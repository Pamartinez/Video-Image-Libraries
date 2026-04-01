package o;

import android.app.appsearch.GenericDocument;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {
    public final Object apply(Object obj) {
        return GenericDocumentToPlatformConverter.toJetpackGenericDocument((GenericDocument) obj);
    }
}
