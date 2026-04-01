package com.samsung.android.sum.core.types;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OptionBase implements Parcelable, Serializable {
    private static final String TAG = Def.tagOf((Class<?>) OptionBase.class);
    private static final long serialVersionUID = 1;
    private final Map<Integer, Object> data;

    public OptionBase() {
        this.data = new HashMap();
    }

    public void clear() {
        this.data.clear();
    }

    public boolean contains(int i2) {
        return this.data.containsKey(Integer.valueOf(i2));
    }

    public void copyTo(OptionBase optionBase) {
        optionBase.data.putAll(this.data);
    }

    public int describeContents() {
        return 0;
    }

    public <V> V get(int i2) {
        return this.data.get(Integer.valueOf(i2));
    }

    public Map<Integer, Object> getAll() {
        return this.data;
    }

    public <V> V remove(int i2) {
        return this.data.remove(Integer.valueOf(i2));
    }

    public OptionBase set(int i2) {
        this.data.put(Integer.valueOf(i2), (Object) null);
        return this;
    }

    public OptionBase setIfExists(int i2, Object obj) {
        if (obj != null) {
            return set(i2, obj);
        }
        String str = TAG;
        SLog.d(str, "skip this(" + i2 + ")... data is null");
        return this;
    }

    public Stream<Map.Entry<Integer, Object>> stream() {
        return this.data.entrySet().stream();
    }

    public String toString() {
        return Def.tagOf((Object) this) + "opt=" + this.data;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeMap(this.data);
    }

    public <V> V get(int i2, V v) {
        return this.data.getOrDefault(Integer.valueOf(i2), v);
    }

    public OptionBase set(int i2, Object obj) {
        if (this.data.containsKey(Integer.valueOf(i2))) {
            String str = TAG;
            StringBuilder o2 = C0086a.o(i2, "exist option(", ") value will be overwritten: ");
            o2.append(this.data.get(Integer.valueOf(i2)));
            o2.append(" -> ");
            o2.append(obj);
            SLog.w(str, o2.toString());
        }
        this.data.put(Integer.valueOf(i2), obj);
        return this;
    }

    public OptionBase(Parcel parcel) {
        HashMap hashMap = new HashMap();
        this.data = hashMap;
        parcel.readMap(hashMap, (ClassLoader) null);
    }
}
