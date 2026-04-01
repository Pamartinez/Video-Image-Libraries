package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionDeprecationMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionDeprecationMetadataDocument implements DocumentClassFactory<AppFunctionDeprecationMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionDeprecationMetadataDocument";
    }

    public AppFunctionDeprecationMetadataDocument fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        String[] propertyStringArray = genericDocument.getPropertyStringArray("message");
        return new AppFunctionDeprecationMetadataDocument(namespace, id, (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0]);
    }
}
