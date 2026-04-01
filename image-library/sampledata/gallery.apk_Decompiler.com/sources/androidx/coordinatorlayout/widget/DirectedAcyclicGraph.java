package androidx.coordinatorlayout.widget;

import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DirectedAcyclicGraph<T> {
    private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap<>();
    private final Pools$Pool<ArrayList<T>> mListPool = new Pools$SimplePool(10);
    private final ArrayList<T> mSortResult = new ArrayList<>();
    private final HashSet<T> mSortTmpMarked = new HashSet<>();

    private void dfs(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (!arrayList.contains(t)) {
            if (!hashSet.contains(t)) {
                hashSet.add(t);
                ArrayList arrayList2 = this.mGraph.get(t);
                if (arrayList2 != null) {
                    int size = arrayList2.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        dfs(arrayList2.get(i2), arrayList, hashSet);
                    }
                }
                hashSet.remove(t);
                arrayList.add(t);
                return;
            }
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
    }

    private ArrayList<T> getEmptyList() {
        ArrayList<T> acquire = this.mListPool.acquire();
        if (acquire == null) {
            return new ArrayList<>();
        }
        return acquire;
    }

    private void poolList(ArrayList<T> arrayList) {
        arrayList.clear();
        this.mListPool.release(arrayList);
    }

    public void addEdge(T t, T t3) {
        if (!this.mGraph.containsKey(t) || !this.mGraph.containsKey(t3)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayList = this.mGraph.get(t);
        if (arrayList == null) {
            arrayList = getEmptyList();
            this.mGraph.put(t, arrayList);
        }
        arrayList.add(t3);
    }

    public void addNode(T t) {
        if (!this.mGraph.containsKey(t)) {
            this.mGraph.put(t, null);
        }
    }

    public void clear() {
        int size = this.mGraph.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList valueAt = this.mGraph.valueAt(i2);
            if (valueAt != null) {
                poolList(valueAt);
            }
        }
        this.mGraph.clear();
    }

    public boolean contains(T t) {
        return this.mGraph.containsKey(t);
    }

    public List<T> getIncomingEdges(T t) {
        ArrayList incomingEdgesInternal = getIncomingEdgesInternal(t);
        if (incomingEdgesInternal == null) {
            return null;
        }
        return new ArrayList(incomingEdgesInternal);
    }

    public ArrayList<T> getIncomingEdgesInternal(T t) {
        return this.mGraph.get(t);
    }

    public List<T> getOutgoingEdges(T t) {
        int size = this.mGraph.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList valueAt = this.mGraph.valueAt(i2);
            if (valueAt != null && valueAt.contains(t)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.mGraph.keyAt(i2));
            }
        }
        return arrayList;
    }

    public ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int size = this.mGraph.size();
        for (int i2 = 0; i2 < size; i2++) {
            dfs(this.mGraph.keyAt(i2), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    public boolean hasOutgoingEdges(T t) {
        int size = this.mGraph.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList valueAt = this.mGraph.valueAt(i2);
            if (valueAt != null && valueAt.contains(t)) {
                return true;
            }
        }
        return false;
    }
}
