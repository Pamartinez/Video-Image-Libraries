package androidx.appsearch.platformstorage.converter;

import android.app.appsearch.GetByDocumentIdRequest;
import androidx.core.util.Preconditions;
import java.util.Collection;
import java.util.Map;
import o.C0245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RequestToPlatformConverter {
    public static GetByDocumentIdRequest toPlatformGetByDocumentIdRequest(androidx.appsearch.app.GetByDocumentIdRequest getByDocumentIdRequest) {
        Preconditions.checkNotNull(getByDocumentIdRequest);
        C0245a.u();
        GetByDocumentIdRequest.Builder g = C0245a.h(getByDocumentIdRequest.getNamespace()).addIds(getByDocumentIdRequest.getIds());
        for (Map.Entry next : getByDocumentIdRequest.getProjections().entrySet()) {
            g.addProjection((String) next.getKey(), (Collection) next.getValue());
        }
        return g.build();
    }
}
