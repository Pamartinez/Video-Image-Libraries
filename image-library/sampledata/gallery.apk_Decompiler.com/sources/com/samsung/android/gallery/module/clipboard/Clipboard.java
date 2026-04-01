package com.samsung.android.gallery.module.clipboard;

import O3.o;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Clipboard {
    private static final ConcurrentHashMap<String, Clipboard> mClipBoardMap = new ConcurrentHashMap<>();
    private final String mBlackBoardName;
    private final LinkedHashSet<Long> mLongSet = new LinkedHashSet<>();
    private final LinkedHashSet<Long> mRemovedClipboardItemIdSet = new LinkedHashSet<>();

    private Clipboard(String str) {
        this.mBlackBoardName = str;
    }

    public static /* synthetic */ Clipboard a(String str) {
        return new Clipboard(str);
    }

    public static Clipboard getInstance(Blackboard blackboard) {
        return mClipBoardMap.computeIfAbsent(blackboard.getName(), new o(9));
    }

    public boolean add(Long l) {
        boolean add;
        synchronized (this) {
            add = this.mLongSet.add(l);
        }
        return add;
    }

    public boolean addAll(List<Long> list) {
        boolean addAll;
        synchronized (this) {
            addAll = this.mLongSet.addAll(list);
        }
        return addAll;
    }

    public void clear() {
        synchronized (this) {
            this.mLongSet.clear();
        }
    }

    public LinkedHashSet<Long> cloneSet() {
        LinkedHashSet<Long> linkedHashSet;
        synchronized (this) {
            linkedHashSet = (LinkedHashSet) this.mLongSet.clone();
        }
        return linkedHashSet;
    }

    public boolean contains(Long l) {
        boolean contains;
        synchronized (this) {
            contains = this.mLongSet.contains(l);
        }
        return contains;
    }

    public boolean containsRemovedClipboardItemId(Long l) {
        return this.mRemovedClipboardItemIdSet.contains(l);
    }

    public void deleteRemovedClipboardItemId(Long l) {
        this.mRemovedClipboardItemIdSet.remove(l);
    }

    public int getTotalCount() {
        int size;
        synchronized (this) {
            size = this.mLongSet.size();
        }
        return size;
    }

    public void release() {
        mClipBoardMap.remove(this.mBlackBoardName);
    }

    public boolean remove(Long l) {
        boolean remove;
        synchronized (this) {
            remove = this.mLongSet.remove(l);
        }
        return remove;
    }

    public void saveRemovedClipboardItemId(Long l) {
        this.mRemovedClipboardItemIdSet.add(l);
    }
}
