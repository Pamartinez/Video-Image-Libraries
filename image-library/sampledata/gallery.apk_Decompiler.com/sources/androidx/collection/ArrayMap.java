package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    ArrayMap<K, V>.EntrySet mEntrySet;
    ArrayMap<K, V>.KeySet mKeySet;
    ArrayMap<K, V>.ValueCollection mValues;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        public EntrySet() {
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new MapIterator();
        }

        public int size() {
            return ArrayMap.this.size();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class KeyIterator extends IndexBasedArrayIterator<K> {
        public KeyIterator() {
            super(ArrayMap.this.size());
        }

        public K elementAt(int i2) {
            return ArrayMap.this.keyAt(i2);
        }

        public void removeAt(int i2) {
            ArrayMap.this.removeAt(i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int mEnd;
        boolean mEntryValid;
        int mIndex = -1;

        public MapIterator() {
            this.mEnd = ArrayMap.this.size() - 1;
        }

        public boolean equals(Object obj) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry entry = (Map.Entry) obj;
                if (!ContainerHelpersKt.equal(entry.getKey(), ArrayMap.this.keyAt(this.mIndex)) || !ContainerHelpersKt.equal(entry.getValue(), ArrayMap.this.valueAt(this.mIndex))) {
                    return false;
                }
                return true;
            }
        }

        public K getKey() {
            if (this.mEntryValid) {
                return ArrayMap.this.keyAt(this.mIndex);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.mEntryValid) {
                return ArrayMap.this.valueAt(this.mIndex);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            if (this.mIndex < this.mEnd) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i2;
            if (this.mEntryValid) {
                Object keyAt = ArrayMap.this.keyAt(this.mIndex);
                Object valueAt = ArrayMap.this.valueAt(this.mIndex);
                int i7 = 0;
                if (keyAt == null) {
                    i2 = 0;
                } else {
                    i2 = keyAt.hashCode();
                }
                if (valueAt != null) {
                    i7 = valueAt.hashCode();
                }
                return i2 ^ i7;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public void remove() {
            if (this.mEntryValid) {
                ArrayMap.this.removeAt(this.mIndex);
                this.mIndex--;
                this.mEnd--;
                this.mEntryValid = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.mEntryValid) {
                return ArrayMap.this.setValueAt(this.mIndex, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }

        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.mIndex++;
                this.mEntryValid = true;
                return this;
            }
            throw new NoSuchElementException();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ValueIterator extends IndexBasedArrayIterator<V> {
        public ValueIterator() {
            super(ArrayMap.this.size());
        }

        public V elementAt(int i2) {
            return ArrayMap.this.valueAt(i2);
        }

        public void removeAt(int i2) {
            ArrayMap.this.removeAt(i2);
        }
    }

    public ArrayMap() {
    }

    public static <T> boolean equalsSetHelper(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() != set2.size() || !set.containsAll(set2)) {
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        ArrayMap<K, V>.EntrySet entrySet = this.mEntrySet;
        if (entrySet != null) {
            return entrySet;
        }
        ArrayMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.mEntrySet = entrySet2;
        return entrySet2;
    }

    public V get(Object obj) {
        return super.get(obj);
    }

    public Set<K> keySet() {
        ArrayMap<K, V>.KeySet keySet = this.mKeySet;
        if (keySet != null) {
            return keySet;
        }
        ArrayMap<K, V>.KeySet keySet2 = new KeySet();
        this.mKeySet = keySet2;
        return keySet2;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(map.size() + size());
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public V remove(Object obj) {
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        int size = size();
        for (Object remove : collection) {
            remove(remove);
        }
        if (size != size()) {
            return true;
        }
        return false;
    }

    public boolean retainAll(Collection<?> collection) {
        int size = size();
        for (int size2 = size() - 1; size2 >= 0; size2--) {
            if (!collection.contains(keyAt(size2))) {
                removeAt(size2);
            }
        }
        if (size != size()) {
            return true;
        }
        return false;
    }

    public Collection<V> values() {
        ArrayMap<K, V>.ValueCollection valueCollection = this.mValues;
        if (valueCollection != null) {
            return valueCollection;
        }
        ArrayMap<K, V>.ValueCollection valueCollection2 = new ValueCollection();
        this.mValues = valueCollection2;
        return valueCollection2;
    }

    public ArrayMap(int i2) {
        super(i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class KeySet implements Set<K> {
        public KeySet() {
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            ArrayMap.this.clear();
        }

        public boolean contains(Object obj) {
            return ArrayMap.this.containsKey(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return ArrayMap.this.containsAll(collection);
        }

        public boolean equals(Object obj) {
            return ArrayMap.equalsSetHelper(this, obj);
        }

        public int hashCode() {
            int i2;
            int i7 = 0;
            for (int size = ArrayMap.this.size() - 1; size >= 0; size--) {
                Object keyAt = ArrayMap.this.keyAt(size);
                if (keyAt == null) {
                    i2 = 0;
                } else {
                    i2 = keyAt.hashCode();
                }
                i7 += i2;
            }
            return i7;
        }

        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean remove(Object obj) {
            int indexOfKey = ArrayMap.this.indexOfKey(obj);
            if (indexOfKey < 0) {
                return false;
            }
            ArrayMap.this.removeAt(indexOfKey);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return ArrayMap.this.removeAll(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return ArrayMap.this.retainAll(collection);
        }

        public int size() {
            return ArrayMap.this.size();
        }

        public Object[] toArray() {
            int size = ArrayMap.this.size();
            Object[] objArr = new Object[size];
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.keyAt(i2);
            }
            return objArr;
        }

        public <T> T[] toArray(T[] tArr) {
            int size = size();
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            for (int i2 = 0; i2 < size; i2++) {
                tArr[i2] = ArrayMap.this.keyAt(i2);
            }
            if (tArr.length > size) {
                tArr[size] = null;
            }
            return tArr;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ValueCollection implements Collection<V> {
        public ValueCollection() {
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            ArrayMap.this.clear();
        }

        public boolean contains(Object obj) {
            if (ArrayMap.this.__restricted$indexOfValue(obj) >= 0) {
                return true;
            }
            return false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public boolean remove(Object obj) {
            int __restricted$indexOfValue = ArrayMap.this.__restricted$indexOfValue(obj);
            if (__restricted$indexOfValue < 0) {
                return false;
            }
            ArrayMap.this.removeAt(__restricted$indexOfValue);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int size = ArrayMap.this.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                if (collection.contains(ArrayMap.this.valueAt(i2))) {
                    ArrayMap.this.removeAt(i2);
                    i2--;
                    size--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int size = ArrayMap.this.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                if (!collection.contains(ArrayMap.this.valueAt(i2))) {
                    ArrayMap.this.removeAt(i2);
                    i2--;
                    size--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        public int size() {
            return ArrayMap.this.size();
        }

        public Object[] toArray() {
            int size = ArrayMap.this.size();
            Object[] objArr = new Object[size];
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.valueAt(i2);
            }
            return objArr;
        }

        public <T> T[] toArray(T[] tArr) {
            int size = size();
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            for (int i2 = 0; i2 < size; i2++) {
                tArr[i2] = ArrayMap.this.valueAt(i2);
            }
            if (tArr.length > size) {
                tArr[size] = null;
            }
            return tArr;
        }
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }
}
