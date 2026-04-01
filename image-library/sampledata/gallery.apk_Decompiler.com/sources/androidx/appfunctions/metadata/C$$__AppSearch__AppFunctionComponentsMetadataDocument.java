package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import java.util.ArrayList;
import java.util.Collections;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionComponentsMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionComponentsMetadataDocument implements DocumentClassFactory<AppFunctionComponentsMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionComponentsMetadataDocument";
    }

    public AppFunctionComponentsMetadataDocument fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String namespace = genericDocument.getNamespace();
        String id = genericDocument.getId();
        GenericDocument[] propertyDocumentArray = genericDocument.getPropertyDocumentArray("dataTypes");
        ArrayList arrayList = Collections.EMPTY_LIST;
        if (propertyDocumentArray != null) {
            ArrayList arrayList2 = new ArrayList(propertyDocumentArray.length);
            for (GenericDocument documentClass : propertyDocumentArray) {
                arrayList2.add((AppFunctionNamedDataTypeMetadataDocument) documentClass.toDocumentClass(AppFunctionNamedDataTypeMetadataDocument.class, documentClassMappingContext));
            }
            arrayList = arrayList2;
        }
        return new AppFunctionComponentsMetadataDocument(namespace, id, arrayList);
    }
}
