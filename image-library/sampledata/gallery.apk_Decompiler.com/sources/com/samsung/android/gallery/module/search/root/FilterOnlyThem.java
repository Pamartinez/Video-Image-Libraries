package com.samsung.android.gallery.module.search.root;

import B8.d;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterOnlyThem implements Parcelable {
    public static final Parcelable.Creator<FilterOnlyThem> CREATOR = new Parcelable.Creator<FilterOnlyThem>() {
        public FilterOnlyThem createFromParcel(Parcel parcel) {
            return new FilterOnlyThem(parcel);
        }

        public FilterOnlyThem[] newArray(int i2) {
            return new FilterOnlyThem[i2];
        }
    };
    private boolean mIsAvailable;
    private final ArrayList<String> mPersonUnifiedKeyList;

    public FilterOnlyThem(Collection<String> collection) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.mPersonUnifiedKeyList = arrayList;
        arrayList.addAll(collection);
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<String> getKeys() {
        return this.mPersonUnifiedKeyList;
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    public boolean isEmpty() {
        return this.mPersonUnifiedKeyList.isEmpty();
    }

    public void setAvailable(boolean z) {
        this.mIsAvailable = z;
    }

    public int size() {
        return this.mPersonUnifiedKeyList.size();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeStringList(this.mPersonUnifiedKeyList);
        parcel.writeBoolean(this.mIsAvailable);
    }

    public FilterOnlyThem(Parcel parcel) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.mPersonUnifiedKeyList = arrayList;
        Optional.ofNullable(parcel.createStringArrayList()).ifPresent(new d(arrayList, 10));
        this.mIsAvailable = parcel.readBoolean();
    }
}
