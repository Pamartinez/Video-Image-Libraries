package com.samsung.android.gallery.module.search.filter;

import com.samsung.android.gallery.module.search.root.FilterResultsEntity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterRemoveParam {
    private final FilterResultsEntity mNewEntity;
    private final int mPosition;
    private final FilterResultsEntity mRemovedEntity;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FilterRemoveParamBuilder {
        /* access modifiers changed from: private */
        public FilterResultsEntity newEntity;
        /* access modifiers changed from: private */
        public int position;
        /* access modifiers changed from: private */
        public FilterResultsEntity removedEntity;

        public FilterRemoveParam build() {
            return new FilterRemoveParam(this);
        }

        public FilterRemoveParamBuilder setNewEntity(FilterResultsEntity filterResultsEntity) {
            this.newEntity = filterResultsEntity;
            return this;
        }

        public FilterRemoveParamBuilder setPosition(int i2) {
            this.position = i2;
            return this;
        }

        public FilterRemoveParamBuilder setRemovedEntity(FilterResultsEntity filterResultsEntity) {
            this.removedEntity = filterResultsEntity;
            return this;
        }
    }

    public FilterRemoveParam(FilterRemoveParamBuilder filterRemoveParamBuilder) {
        this.mNewEntity = filterRemoveParamBuilder.newEntity;
        this.mRemovedEntity = filterRemoveParamBuilder.removedEntity;
        this.mPosition = filterRemoveParamBuilder.position;
    }

    public FilterResultsEntity getNewEntity() {
        return this.mNewEntity;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public FilterResultsEntity getRemovedEntity() {
        return this.mRemovedEntity;
    }
}
