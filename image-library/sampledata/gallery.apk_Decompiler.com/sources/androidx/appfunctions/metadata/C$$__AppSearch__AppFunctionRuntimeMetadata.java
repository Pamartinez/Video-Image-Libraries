package androidx.appfunctions.metadata;

import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.DocumentClassMappingContext;
import androidx.appsearch.app.GenericDocument;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import com.samsung.scsp.media.api.constant.MediaApiContract;

/* renamed from: androidx.appfunctions.metadata.$$__AppSearch__AppFunctionRuntimeMetadata  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$$__AppSearch__AppFunctionRuntimeMetadata implements DocumentClassFactory<AppFunctionRuntimeMetadata> {
    public String getSchemaName() {
        return "AppFunctionRuntimeMetadata";
    }

    public AppFunctionRuntimeMetadata fromGenericDocument(GenericDocument genericDocument, DocumentClassMappingContext documentClassMappingContext) {
        String id = genericDocument.getId();
        String namespace = genericDocument.getNamespace();
        String[] propertyStringArray = genericDocument.getPropertyStringArray("functionId");
        String str = null;
        String str2 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        String str3 = (propertyStringArray2 == null || propertyStringArray2.length == 0) ? null : propertyStringArray2[0];
        long propertyLong = genericDocument.getPropertyLong(MediaApiContract.Parameter.ENABLED);
        String[] propertyStringArray3 = genericDocument.getPropertyStringArray("appFunctionStaticMetadataQualifiedId");
        if (!(propertyStringArray3 == null || propertyStringArray3.length == 0)) {
            str = propertyStringArray3[0];
        }
        return new AppFunctionRuntimeMetadata(id, namespace, str2, str3, propertyLong, str);
    }
}
