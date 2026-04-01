package androidx.appsearch.platformstorage.converter;

import android.app.appsearch.EmbeddingVector;
import android.app.appsearch.GenericDocument;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.platformstorage.util.AppSearchVersionUtil;
import androidx.core.util.Preconditions;
import i.C0212a;
import i.C0213b;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GenericDocumentToPlatformConverter {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ApiHelperForB {
        public static void setJetpackPropertyEmbedding(GenericDocument.Builder<GenericDocument.Builder<?>> builder, String str, EmbeddingVector[] embeddingVectorArr) {
            androidx.appsearch.app.EmbeddingVector[] embeddingVectorArr2 = new androidx.appsearch.app.EmbeddingVector[embeddingVectorArr.length];
            for (int i2 = 0; i2 < embeddingVectorArr.length; i2++) {
                embeddingVectorArr2[i2] = new androidx.appsearch.app.EmbeddingVector(embeddingVectorArr[i2].getValues(), embeddingVectorArr[i2].getModelSignature());
            }
            builder.setPropertyEmbedding(str, embeddingVectorArr2);
        }

        public static void setPlatformPropertyEmbedding(GenericDocument.Builder<GenericDocument.Builder<?>> builder, String str, androidx.appsearch.app.EmbeddingVector[] embeddingVectorArr) {
            EmbeddingVector[] embeddingVectorArr2 = new EmbeddingVector[embeddingVectorArr.length];
            for (int i2 = 0; i2 < embeddingVectorArr.length; i2++) {
                C0213b.r();
                embeddingVectorArr2[i2] = C0213b.c(embeddingVectorArr[i2].getValues(), embeddingVectorArr[i2].getModelSignature());
            }
            builder.setPropertyEmbedding(str, embeddingVectorArr2);
        }
    }

    public static androidx.appsearch.app.GenericDocument toJetpackGenericDocument(android.app.appsearch.GenericDocument genericDocument) {
        Preconditions.checkNotNull(genericDocument);
        GenericDocument.Builder builder = new GenericDocument.Builder(genericDocument.getNamespace(), genericDocument.getId(), genericDocument.getSchemaType());
        builder.setScore(genericDocument.getScore()).setTtlMillis(genericDocument.getTtlMillis()).setCreationTimestampMillis(genericDocument.getCreationTimestampMillis());
        for (String str : genericDocument.getPropertyNames()) {
            Object property = genericDocument.getProperty(str);
            if (str.equals("$$__AppSearch__parentTypes")) {
                if (property instanceof String[]) {
                    builder.setParentTypes(Arrays.asList((String[]) property));
                } else {
                    throw new IllegalStateException(C0212a.l("Parents list must be of String[] type, but got ", property.getClass().toString()));
                }
            } else if (property instanceof String[]) {
                builder.setPropertyString(str, (String[]) property);
            } else if (property instanceof long[]) {
                builder.setPropertyLong(str, (long[]) property);
            } else if (property instanceof double[]) {
                builder.setPropertyDouble(str, (double[]) property);
            } else if (property instanceof boolean[]) {
                builder.setPropertyBoolean(str, (boolean[]) property);
            } else if (property instanceof byte[][]) {
                builder.setPropertyBytes(str, (byte[][]) property);
            } else if (property instanceof android.app.appsearch.GenericDocument[]) {
                android.app.appsearch.GenericDocument[] genericDocumentArr = (android.app.appsearch.GenericDocument[]) property;
                androidx.appsearch.app.GenericDocument[] genericDocumentArr2 = new androidx.appsearch.app.GenericDocument[genericDocumentArr.length];
                for (int i2 = 0; i2 < genericDocumentArr.length; i2++) {
                    genericDocumentArr2[i2] = toJetpackGenericDocument(genericDocumentArr[i2]);
                }
                builder.setPropertyDocument(str, genericDocumentArr2);
            } else if (!AppSearchVersionUtil.isAtLeastB() || !(property instanceof EmbeddingVector[])) {
                throw new IllegalStateException(C0212a.n("Property \"", str, "\" has unsupported value type ", property.getClass().toString()));
            } else {
                ApiHelperForB.setJetpackPropertyEmbedding(builder, str, (EmbeddingVector[]) property);
            }
        }
        return builder.build();
    }
}
