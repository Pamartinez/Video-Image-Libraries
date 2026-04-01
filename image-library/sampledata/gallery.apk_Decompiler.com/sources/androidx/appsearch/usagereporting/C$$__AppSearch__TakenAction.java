package androidx.appsearch.usagereporting;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.usagereporting.TakenAction;
import com.samsung.android.sdk.bixby2.action.ActionHandler;

/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__TakenAction  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__TakenAction implements DocumentClassFactory<TakenAction> {
    public String getSchemaName() {
        return "builtin:TakenAction";
    }

    public TakenAction fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        long ttlMillis = genericDocument.getTtlMillis();
        TakenAction.Builder builder = new TakenAction.Builder(namespace, id, genericDocument.getCreationTimestampMillis(), (int) genericDocument.getPropertyLong(ActionHandler.ACTION_TYPE));
        builder.setDocumentTtlMillis(ttlMillis);
        return builder.build();
    }
}
