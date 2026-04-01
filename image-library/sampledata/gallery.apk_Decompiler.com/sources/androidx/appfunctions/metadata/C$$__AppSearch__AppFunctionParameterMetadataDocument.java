package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionParameterMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionParameterMetadataDocument implements DocumentClassFactory<AppFunctionParameterMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionParameterMetadataDocument";
    }

    public AppFunctionParameterMetadataDocument fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        String[] propertyStringArray = genericDocument.getPropertyStringArray("name");
        String str = null;
        String str2 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        boolean propertyBoolean = genericDocument.getPropertyBoolean("isRequired");
        GenericDocument propertyDocument = genericDocument.getPropertyDocument("dataTypeMetadata");
        AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = propertyDocument != null ? (AppFunctionDataTypeMetadataDocument) propertyDocument.toDocumentClass(AppFunctionDataTypeMetadataDocument.class, documentClassMappingContext) : null;
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray("description");
        if (!(propertyStringArray2 == null || propertyStringArray2.length == 0)) {
            str = propertyStringArray2[0];
        }
        return new AppFunctionParameterMetadataDocument(namespace, id, str2, propertyBoolean, appFunctionDataTypeMetadataDocument, str);
    }
}
