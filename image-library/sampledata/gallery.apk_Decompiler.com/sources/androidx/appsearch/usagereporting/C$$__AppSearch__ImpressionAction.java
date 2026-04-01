package androidx.appsearch.usagereporting;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.usagereporting.ImpressionAction;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.bixby2.action.ActionHandler;

/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__ImpressionAction  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__ImpressionAction implements DocumentClassFactory<ImpressionAction> {
    public String getSchemaName() {
        return "builtin:ImpressionAction";
    }

    public ImpressionAction fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        long ttlMillis = genericDocument.getTtlMillis();
        long creationTimestampMillis = genericDocument.getCreationTimestampMillis();
        int propertyLong = (int) genericDocument.getPropertyLong(ActionHandler.ACTION_TYPE);
        String[] propertyStringArray = genericDocument.getPropertyStringArray(Contract.QUERY);
        String str = null;
        String str2 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray("referencedQualifiedId");
        if (!(propertyStringArray2 == null || propertyStringArray2.length == 0)) {
            str = propertyStringArray2[0];
        }
        String str3 = str;
        ImpressionAction.Builder builder = new ImpressionAction.Builder(namespace, id, creationTimestampMillis, propertyLong);
        builder.setDocumentTtlMillis(ttlMillis);
        builder.setQuery(str2);
        builder.setReferencedQualifiedId(str3);
        builder.setResultRankInBlock((int) genericDocument.getPropertyLong("resultRankInBlock"));
        builder.setResultRankGlobal((int) genericDocument.getPropertyLong("resultRankGlobal"));
        return builder.build();
    }
}
