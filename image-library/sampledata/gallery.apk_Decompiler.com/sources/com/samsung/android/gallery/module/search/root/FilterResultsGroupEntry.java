package com.samsung.android.gallery.module.search.root;

import A8.C0545b;
import V8.a;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FilterResultsGroupEntry extends FilterResultsEntry {
    private static final Comparator<FilterResultsEntity> SHOT_TYPE_GROUP_COMPARATOR = new Object();
    final HashMap<FilterResultsGroupType, FilterResultsEntity> mGroupEntityMap = new HashMap<>();
    final ArrayList<FilterResultsEntity> mMixedEntry = new ArrayList<>();

    private void addMixedEntry(FilterResultsEntity filterResultsEntity, int i2) {
        ArrayList<FilterResultsEntity> child = filterResultsEntity.getChild();
        if (child.size() >= i2) {
            this.mMixedEntry.add(filterResultsEntity);
        } else {
            this.mMixedEntry.addAll(child);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FilterResultsEntity lambda$addEntity$1(String str, FilterResultsGroupType filterResultsGroupType) {
        return new FilterResultsEntity(filterResultsGroupType, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initEntry$0(FilterResultsGroupType filterResultsGroupType) {
        FilterResultsEntity filterResultsEntity = this.mGroupEntityMap.get(filterResultsGroupType);
        if (filterResultsEntity == null) {
            return;
        }
        if (FilterResultsGroupType.FACES == filterResultsGroupType) {
            addMixedEntry(filterResultsEntity, 3);
        } else if (FilterResultsGroupType.LOCATIONS == filterResultsGroupType) {
            addMixedEntry(filterResultsEntity, 2);
        } else if (FilterResultsGroupType.SHOT_TYPES == filterResultsGroupType) {
            addMixedEntry(filterResultsEntity, 2);
        } else {
            this.mMixedEntry.add(filterResultsEntity);
        }
    }

    public void addEntity(FilterResultsEntity filterResultsEntity) {
        super.addEntity(filterResultsEntity);
        String category = filterResultsEntity.getCategory();
        FilterResultsGroupType of2 = FilterResultsGroupType.of(category);
        FilterResultsEntity computeIfAbsent = this.mGroupEntityMap.computeIfAbsent(of2, new c(category));
        computeIfAbsent.addChild(filterResultsEntity);
        if (of2 == FilterResultsGroupType.SHOT_TYPES) {
            computeIfAbsent.sortChild(SHOT_TYPE_GROUP_COMPARATOR);
        }
    }

    public int getCount() {
        return this.mMixedEntry.size();
    }

    public FilterResultsEntity getItem(int i2) {
        return this.mMixedEntry.get(i2);
    }

    public FilterResultsEntry initEntry(FilterResultsEntry.Builder builder) {
        super.initEntry(builder);
        ArrayList arrayList = new ArrayList(this.mGroupEntityMap.keySet());
        if (arrayList.size() > 1) {
            arrayList.sort(Comparator.comparingInt(new C0545b(19)));
        }
        arrayList.forEach(new a(this));
        return this;
    }

    public boolean isEmpty() {
        return this.mMixedEntry.isEmpty();
    }

    public void removeEntity(FilterResultsEntity filterResultsEntity) {
        this.mMixedEntry.remove(filterResultsEntity);
    }

    public String toString() {
        return this.TAG + "\n" + ((String) this.mMixedEntry.stream().map(new a(24)).collect(Collectors.joining("\n")));
    }
}
