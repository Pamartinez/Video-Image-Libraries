package androidx.appsearch.app;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import androidx.appsearch.safeparcel.stub.StubCreators$GetByDocumentIdRequestCreator;
import androidx.appsearch.util.BundleUtil;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GetByDocumentIdRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetByDocumentIdRequest> CREATOR = new StubCreators$GetByDocumentIdRequestCreator();
    final List<String> mIds;
    private Set<String> mIdsCached;
    private final String mNamespace;
    final Bundle mTypePropertyPaths;

    public GetByDocumentIdRequest(String str, List<String> list, Bundle bundle) {
        Objects.requireNonNull(str);
        this.mNamespace = str;
        Objects.requireNonNull(list);
        this.mIds = list;
        Objects.requireNonNull(bundle);
        this.mTypePropertyPaths = bundle;
    }

    public Set<String> getIds() {
        if (this.mIdsCached == null) {
            this.mIdsCached = Collections.unmodifiableSet(new ArraySet(this.mIds));
        }
        return this.mIdsCached;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public Map<String, List<String>> getProjections() {
        Set<String> keySet = this.mTypePropertyPaths.keySet();
        ArrayMap arrayMap = new ArrayMap(keySet.size());
        for (String next : keySet) {
            ArrayList<String> stringArrayList = this.mTypePropertyPaths.getStringArrayList(next);
            if (stringArrayList != null) {
                arrayMap.put(next, Collections.unmodifiableList(stringArrayList));
            }
        }
        return arrayMap;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean mBuilt = false;
        private List<String> mIds = new ArrayList();
        private final String mNamespace;
        private Bundle mProjectionTypePropertyPaths = new Bundle();

        public Builder(String str) {
            this.mNamespace = (String) Preconditions.checkNotNull(str);
        }

        private void resetIfBuilt() {
            if (this.mBuilt) {
                this.mIds = new ArrayList(this.mIds);
                this.mProjectionTypePropertyPaths = BundleUtil.deepCopy(this.mProjectionTypePropertyPaths);
                this.mBuilt = false;
            }
        }

        public Builder addIds(String... strArr) {
            Preconditions.checkNotNull(strArr);
            resetIfBuilt();
            return addIds((Collection<String>) Arrays.asList(strArr));
        }

        public GetByDocumentIdRequest build() {
            this.mBuilt = true;
            return new GetByDocumentIdRequest(this.mNamespace, this.mIds, this.mProjectionTypePropertyPaths);
        }

        public Builder addIds(Collection<String> collection) {
            Preconditions.checkNotNull(collection);
            resetIfBuilt();
            this.mIds.addAll(collection);
            return this;
        }
    }
}
