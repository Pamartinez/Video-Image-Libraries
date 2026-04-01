package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionDataTypeMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionDataTypeMetadataDocument implements DocumentClassFactory<AppFunctionDataTypeMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionDataTypeMetadataDocument";
    }

    public AppFunctionDataTypeMetadataDocument fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        GenericDocument genericDocument2 = genericDocument;
        DocumentClassMappingContext documentClassMappingContext2 = documentClassMappingContext;
        String namespace = genericDocument2.getNamespace();
        String id = genericDocument2.getId();
        int propertyLong = (int) genericDocument2.getPropertyLong("type");
        GenericDocument propertyDocument = genericDocument2.getPropertyDocument("itemType");
        Class cls = AppFunctionDataTypeMetadataDocument.class;
        AppFunctionDataTypeMetadataDocument appFunctionDataTypeMetadataDocument = propertyDocument != null ? (AppFunctionDataTypeMetadataDocument) propertyDocument.toDocumentClass(cls, documentClassMappingContext2) : null;
        GenericDocument[] propertyDocumentArray = genericDocument2.getPropertyDocumentArray("properties");
        ArrayList arrayList = Collections.EMPTY_LIST;
        if (propertyDocumentArray != null) {
            ArrayList arrayList2 = new ArrayList(propertyDocumentArray.length);
            for (GenericDocument documentClass : propertyDocumentArray) {
                arrayList2.add((AppFunctionNamedDataTypeMetadataDocument) documentClass.toDocumentClass(AppFunctionNamedDataTypeMetadataDocument.class, documentClassMappingContext2));
            }
            arrayList = arrayList2;
        }
        GenericDocument[] propertyDocumentArray2 = genericDocument2.getPropertyDocumentArray("allOf");
        ArrayList arrayList3 = Collections.EMPTY_LIST;
        if (propertyDocumentArray2 != null) {
            ArrayList arrayList4 = new ArrayList(propertyDocumentArray2.length);
            for (GenericDocument documentClass2 : propertyDocumentArray2) {
                arrayList4.add((AppFunctionDataTypeMetadataDocument) documentClass2.toDocumentClass(cls, documentClassMappingContext2));
            }
            arrayList3 = arrayList4;
        }
        GenericDocument[] propertyDocumentArray3 = genericDocument2.getPropertyDocumentArray("oneOf");
        ArrayList arrayList5 = Collections.EMPTY_LIST;
        if (propertyDocumentArray3 != null) {
            ArrayList arrayList6 = new ArrayList(propertyDocumentArray3.length);
            for (GenericDocument documentClass3 : propertyDocumentArray3) {
                arrayList6.add((AppFunctionDataTypeMetadataDocument) documentClass3.toDocumentClass(cls, documentClassMappingContext2));
            }
            arrayList5 = arrayList6;
        }
        String[] propertyStringArray = genericDocument2.getPropertyStringArray("required");
        List list = Collections.EMPTY_LIST;
        List asList = propertyStringArray != null ? Arrays.asList(propertyStringArray) : list;
        String[] propertyStringArray2 = genericDocument2.getPropertyStringArray("dataTypeReference");
        String str = (propertyStringArray2 == null || propertyStringArray2.length == 0) ? null : propertyStringArray2[0];
        boolean propertyBoolean = genericDocument2.getPropertyBoolean("isNullable");
        String[] propertyStringArray3 = genericDocument2.getPropertyStringArray("objectQualifiedName");
        String str2 = (propertyStringArray3 == null || propertyStringArray3.length == 0) ? null : propertyStringArray3[0];
        String[] propertyStringArray4 = genericDocument2.getPropertyStringArray("description");
        String str3 = (propertyStringArray4 == null || propertyStringArray4.length == 0) ? null : propertyStringArray4[0];
        String[] propertyStringArray5 = genericDocument2.getPropertyStringArray("enumValues");
        if (propertyStringArray5 != null) {
            list = Arrays.asList(propertyStringArray5);
        }
        return new AppFunctionDataTypeMetadataDocument(namespace, id, propertyLong, appFunctionDataTypeMetadataDocument, arrayList, arrayList3, arrayList5, asList, str, propertyBoolean, str2, str3, list);
    }
}
