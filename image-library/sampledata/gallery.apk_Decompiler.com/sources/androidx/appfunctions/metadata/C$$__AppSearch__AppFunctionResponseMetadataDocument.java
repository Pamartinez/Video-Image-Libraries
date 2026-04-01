package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionResponseMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionResponseMetadataDocument implements DocumentClassFactory<AppFunctionResponseMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionResponseMetadataDocument";
    }

    public AppFunctionResponseMetadataDocument fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        GenericDocument propertyDocument = genericDocument.getPropertyDocument("valueType");
        String str = null;
        AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = propertyDocument != null ? (AppFunctionDataTypeMetadataDocument) propertyDocument.toDocumentClass(AppFunctionDataTypeMetadataDocument.class, documentClassMappingContext) : null;
        String[] propertyStringArray = genericDocument.getPropertyStringArray("description");
        if (!(propertyStringArray == null || propertyStringArray.length == 0)) {
            str = propertyStringArray[0];
        }
        return new AppFunctionResponseMetadataDocument(namespace, id, appFunctionDataTypeMetadataDocument, str);
    }
}
