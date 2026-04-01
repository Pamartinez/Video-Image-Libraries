package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionNamedDataTypeMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionNamedDataTypeMetadataDocument implements DocumentClassFactory<AppFunctionNamedDataTypeMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionNamedDataTypeMetadataDocument";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.appfunctions.metadata.AppFunctionDataTypeMetadataDocument} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appfunctions.metadata.AppFunctionNamedDataTypeMetadataDocument fromGenericDocument(androidx.appsearch.app.GenericDocument r5, androidx.appsearch.app.DocumentClassMappingContext r6) {
        /*
            r4 = this;
            java.lang.String r4 = r5.getNamespace()
            java.lang.String r0 = r5.getId()
            java.lang.String r1 = "name"
            java.lang.String[] r1 = r5.getPropertyStringArray(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0018
            int r3 = r1.length
            if (r3 == 0) goto L_0x0018
            r3 = 0
            r1 = r1[r3]
            goto L_0x0019
        L_0x0018:
            r1 = r2
        L_0x0019:
            java.lang.String r3 = "dataTypeMetadata"
            androidx.appsearch.app.GenericDocument r5 = r5.getPropertyDocument(r3)
            if (r5 == 0) goto L_0x002a
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionDataTypeMetadataDocument> r2 = androidx.appfunctions.metadata.AppFunctionDataTypeMetadataDocument.class
            java.lang.Object r5 = r5.toDocumentClass(r2, r6)
            r2 = r5
            androidx.appfunctions.metadata.AppFunctionDataTypeMetadataDocument r2 = (androidx.appfunctions.metadata.AppFunctionDataTypeMetadataDocument) r2
        L_0x002a:
            androidx.appfunctions.metadata.AppFunctionNamedDataTypeMetadataDocument r5 = new androidx.appfunctions.metadata.AppFunctionNamedDataTypeMetadataDocument
            r5.<init>(r4, r0, r1, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.metadata.C$$__AppSearch__AppFunctionNamedDataTypeMetadataDocument.fromGenericDocument(androidx.appsearch.app.GenericDocument, androidx.appsearch.app.DocumentClassMappingContext):androidx.appfunctions.metadata.AppFunctionNamedDataTypeMetadataDocument");
    }
}
