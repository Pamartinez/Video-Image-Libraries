package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionMetadataDocument  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionMetadataDocument implements DocumentClassFactory<AppFunctionMetadataDocument> {
    public String getSchemaName() {
        return "AppFunctionStaticMetadata";
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: androidx.appfunctions.metadata.AppFunctionDeprecationMetadataDocument} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.appfunctions.metadata.AppFunctionMetadataDocument fromGenericDocument(androidx.appsearch.app.GenericDocument r13, androidx.appsearch.app.DocumentClassMappingContext r14) {
        /*
            r12 = this;
            java.lang.String r1 = r13.getNamespace()
            java.lang.String r2 = r13.getId()
            java.lang.String r12 = "enabledByDefault"
            boolean r3 = r13.getPropertyBoolean(r12)
            java.lang.String r12 = "schemaCategory"
            java.lang.String[] r12 = r13.getPropertyStringArray(r12)
            r0 = 0
            r4 = 0
            if (r12 == 0) goto L_0x001e
            int r5 = r12.length
            if (r5 == 0) goto L_0x001e
            r12 = r12[r0]
            goto L_0x001f
        L_0x001e:
            r12 = r4
        L_0x001f:
            java.lang.String r5 = "schemaName"
            java.lang.String[] r5 = r13.getPropertyStringArray(r5)
            if (r5 == 0) goto L_0x002d
            int r6 = r5.length
            if (r6 == 0) goto L_0x002d
            r5 = r5[r0]
            goto L_0x002e
        L_0x002d:
            r5 = r4
        L_0x002e:
            java.lang.String r6 = "schemaVersion"
            long[] r6 = r13.getPropertyLongArray(r6)
            if (r6 == 0) goto L_0x0040
            int r7 = r6.length
            if (r7 == 0) goto L_0x0040
            r6 = r6[r0]
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            goto L_0x0041
        L_0x0040:
            r6 = r4
        L_0x0041:
            java.lang.String r7 = "parameters"
            androidx.appsearch.app.GenericDocument[] r7 = r13.getPropertyDocumentArray(r7)
            if (r7 == 0) goto L_0x0065
            java.util.ArrayList r8 = new java.util.ArrayList
            int r9 = r7.length
            r8.<init>(r9)
            r9 = r0
        L_0x0050:
            int r10 = r7.length
            if (r9 >= r10) goto L_0x0063
            r10 = r7[r9]
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionParameterMetadataDocument> r11 = androidx.appfunctions.metadata.AppFunctionParameterMetadataDocument.class
            java.lang.Object r10 = r10.toDocumentClass(r11, r14)
            androidx.appfunctions.metadata.AppFunctionParameterMetadataDocument r10 = (androidx.appfunctions.metadata.AppFunctionParameterMetadataDocument) r10
            r8.add(r10)
            int r9 = r9 + 1
            goto L_0x0050
        L_0x0063:
            r7 = r8
            goto L_0x0066
        L_0x0065:
            r7 = r4
        L_0x0066:
            java.lang.String r8 = "response"
            androidx.appsearch.app.GenericDocument r8 = r13.getPropertyDocument(r8)
            if (r8 == 0) goto L_0x0077
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionResponseMetadataDocument> r9 = androidx.appfunctions.metadata.AppFunctionResponseMetadataDocument.class
            java.lang.Object r8 = r8.toDocumentClass(r9, r14)
            androidx.appfunctions.metadata.AppFunctionResponseMetadataDocument r8 = (androidx.appfunctions.metadata.AppFunctionResponseMetadataDocument) r8
            goto L_0x0078
        L_0x0077:
            r8 = r4
        L_0x0078:
            java.lang.String r9 = "description"
            java.lang.String[] r9 = r13.getPropertyStringArray(r9)
            if (r9 == 0) goto L_0x0087
            int r10 = r9.length
            if (r10 == 0) goto L_0x0087
            r0 = r9[r0]
            r9 = r0
            goto L_0x0088
        L_0x0087:
            r9 = r4
        L_0x0088:
            java.lang.String r0 = "deprecation"
            androidx.appsearch.app.GenericDocument r13 = r13.getPropertyDocument(r0)
            if (r13 == 0) goto L_0x0099
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionDeprecationMetadataDocument> r0 = androidx.appfunctions.metadata.AppFunctionDeprecationMetadataDocument.class
            java.lang.Object r13 = r13.toDocumentClass(r0, r14)
            r4 = r13
            androidx.appfunctions.metadata.AppFunctionDeprecationMetadataDocument r4 = (androidx.appfunctions.metadata.AppFunctionDeprecationMetadataDocument) r4
        L_0x0099:
            r10 = r4
            androidx.appfunctions.metadata.AppFunctionMetadataDocument r0 = new androidx.appfunctions.metadata.AppFunctionMetadataDocument
            r4 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.metadata.C$$__AppSearch__AppFunctionMetadataDocument.fromGenericDocument(androidx.appsearch.app.GenericDocument, androidx.appsearch.app.DocumentClassMappingContext):androidx.appfunctions.metadata.AppFunctionMetadataDocument");
    }
}
