package androidx.appsearch.app;

import android.os.Parcelable;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.stub.StubCreators$EmbeddingVectorCreator;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EmbeddingVector extends AbstractSafeParcelable {
    public static final Parcelable.Creator<EmbeddingVector> CREATOR = new StubCreators$EmbeddingVectorCreator();
    private Integer mHashCode;
    private final String mModelSignature;
    private final float[] mValues;

    public EmbeddingVector(float[] fArr, String str) {
        float[] fArr2 = (float[]) Preconditions.checkNotNull(fArr);
        this.mValues = fArr2;
        if (fArr2.length != 0) {
            this.mModelSignature = (String) Preconditions.checkNotNull(str);
            return;
        }
        throw new IllegalArgumentException("Embedding values cannot be empty.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EmbeddingVector)) {
            return false;
        }
        EmbeddingVector embeddingVector = (EmbeddingVector) obj;
        if (!Arrays.equals(this.mValues, embeddingVector.mValues) || !this.mModelSignature.equals(embeddingVector.mModelSignature)) {
            return false;
        }
        return true;
    }

    public String getModelSignature() {
        return this.mModelSignature;
    }

    public float[] getValues() {
        return this.mValues;
    }

    public int hashCode() {
        if (this.mHashCode == null) {
            this.mHashCode = Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(Arrays.hashCode(this.mValues)), this.mModelSignature}));
        }
        return this.mHashCode.intValue();
    }
}
