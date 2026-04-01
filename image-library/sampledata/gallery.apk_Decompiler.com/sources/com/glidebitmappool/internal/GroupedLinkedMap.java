package com.glidebitmappool.internal;

import com.glidebitmappool.internal.Poolable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GroupedLinkedMap<K extends Poolable, V> {
    private final LinkedEntry<K, V> head = new LinkedEntry<>();
    private final Map<K, LinkedEntry<K, V>> keyToEntry = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LinkedEntry<K, V> {
        /* access modifiers changed from: private */
        public final K key;
        LinkedEntry<K, V> next;
        LinkedEntry<K, V> prev;
        private List<V> values;

        public LinkedEntry() {
            this((Object) null);
        }

        public void add(V v) {
            if (this.values == null) {
                this.values = new ArrayList();
            }
            this.values.add(v);
        }

        public V removeLast() {
            int size = size();
            if (size > 0) {
                return this.values.remove(size - 1);
            }
            return null;
        }

        public int size() {
            List<V> list = this.values;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public LinkedEntry(K k) {
            this.prev = this;
            this.next = this;
            this.key = k;
        }
    }

    private void makeHead(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.prev = linkedEntry2;
        linkedEntry.next = linkedEntry2.next;
        updateEntry(linkedEntry);
    }

    private void makeTail(LinkedEntry<K, V> linkedEntry) {
        removeEntry(linkedEntry);
        LinkedEntry<K, V> linkedEntry2 = this.head;
        linkedEntry.prev = linkedEntry2.prev;
        linkedEntry.next = linkedEntry2;
        updateEntry(linkedEntry);
    }

    private static <K, V> void removeEntry(LinkedEntry<K, V> linkedEntry) {
        LinkedEntry<K, V> linkedEntry2 = linkedEntry.prev;
        linkedEntry2.next = linkedEntry.next;
        linkedEntry.next.prev = linkedEntry2;
    }

    private static <K, V> void updateEntry(LinkedEntry<K, V> linkedEntry) {
        linkedEntry.next.prev = linkedEntry;
        linkedEntry.prev.next = linkedEntry;
    }

    public V get(K k) {
        LinkedEntry linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry(k);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        makeHead(linkedEntry);
        return linkedEntry.removeLast();
    }

    public void put(K k, V v) {
        LinkedEntry linkedEntry = this.keyToEntry.get(k);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry(k);
            makeTail(linkedEntry);
            this.keyToEntry.put(k, linkedEntry);
        } else {
            k.offer();
        }
        linkedEntry.add(v);
    }

    public V removeLast() {
        for (LinkedEntry<K, V> linkedEntry = this.head.prev; !linkedEntry.equals(this.head); linkedEntry = linkedEntry.prev) {
            V removeLast = linkedEntry.removeLast();
            if (removeLast != null) {
                return removeLast;
            }
            removeEntry(linkedEntry);
            this.keyToEntry.remove(linkedEntry.key);
            ((Poolable) linkedEntry.key).offer();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GroupedLinkedMap( ");
        LinkedEntry<K, V> linkedEntry = this.head.next;
        boolean z = false;
        while (!linkedEntry.equals(this.head)) {
            sb2.append('{');
            sb2.append(linkedEntry.key);
            sb2.append(':');
            sb2.append(linkedEntry.size());
            sb2.append("}, ");
            linkedEntry = linkedEntry.next;
            z = true;
        }
        if (z) {
            sb2.delete(sb2.length() - 2, sb2.length());
        }
        sb2.append(" )");
        return sb2.toString();
    }
}
