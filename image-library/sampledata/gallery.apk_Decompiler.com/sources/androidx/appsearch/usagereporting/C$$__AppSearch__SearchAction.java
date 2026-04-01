package androidx.appsearch.usagereporting;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.usagereporting.SearchAction;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.bixby2.action.ActionHandler;

/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__SearchAction  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__SearchAction implements DocumentClassFactory<SearchAction> {
    public String getSchemaName() {
        return "builtin:SearchAction";
    }

    public SearchAction fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        long ttlMillis = genericDocument.getTtlMillis();
        long creationTimestampMillis = genericDocument.getCreationTimestampMillis();
        int propertyLong = (int) genericDocument.getPropertyLong(ActionHandler.ACTION_TYPE);
        String[] propertyStringArray = genericDocument.getPropertyStringArray(Contract.QUERY);
        String str = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        SearchAction.Builder builder = new SearchAction.Builder(namespace, id, creationTimestampMillis, propertyLong);
        builder.setDocumentTtlMillis(ttlMillis);
        builder.setQuery(str);
        builder.setFetchedResultCount((int) genericDocument.getPropertyLong("fetchedResultCount"));
        return builder.build();
    }
}
