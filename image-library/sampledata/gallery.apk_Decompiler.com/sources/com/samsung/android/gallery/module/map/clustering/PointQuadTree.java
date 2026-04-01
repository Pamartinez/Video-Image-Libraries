package com.samsung.android.gallery.module.map.clustering;

import com.samsung.android.gallery.module.map.clustering.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PointQuadTree<T extends Item> {
    private final Bounds mBounds;
    List<PointQuadTree<T>> mChildren;
    private final int mDepth;
    List<T> mItems;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double d, double d2, double d3, double d5) {
        this(new Bounds(d, d2, d3, d5));
    }

    private void insert(double d, double d2, T t) {
        List<PointQuadTree<T>> list = this.mChildren;
        if (list != null) {
            Bounds bounds = this.mBounds;
            if (d2 < bounds.midY) {
                if (d < bounds.midX) {
                    list.get(0).insert(d, d2, t);
                } else {
                    list.get(1).insert(d, d2, t);
                }
            } else if (d < bounds.midX) {
                list.get(2).insert(d, d2, t);
            } else {
                list.get(3).insert(d, d2, t);
            }
        } else {
            if (this.mItems == null) {
                this.mItems = new ArrayList();
            }
            this.mItems.add(t);
            if (this.mItems.size() > 100000 && this.mDepth < 1000) {
                split();
            }
        }
    }

    private static /* synthetic */ Bounds lambda$search$0(double d, Point point) {
        double d2 = point.f3036x;
        double d3 = point.y;
        return new Bounds(d2 - d, d2 + d, d3 - d, d3 + d);
    }

    private void split() {
        ArrayList arrayList = new ArrayList(4);
        this.mChildren = arrayList;
        Bounds bounds = this.mBounds;
        arrayList.add(new PointQuadTree(bounds.minX, bounds.midX, bounds.minY, bounds.midY, this.mDepth + 1));
        List<PointQuadTree<T>> list = this.mChildren;
        Bounds bounds2 = this.mBounds;
        Bounds bounds3 = bounds2;
        Bounds bounds4 = bounds3;
        list.add(new PointQuadTree(bounds2.midX, bounds3.maxX, bounds4.minY, bounds4.midY, this.mDepth + 1));
        List<PointQuadTree<T>> list2 = this.mChildren;
        Bounds bounds5 = this.mBounds;
        Bounds bounds6 = bounds5;
        Bounds bounds7 = bounds6;
        list2.add(new PointQuadTree(bounds5.minX, bounds6.midX, bounds7.midY, bounds7.maxY, this.mDepth + 1));
        List<PointQuadTree<T>> list3 = this.mChildren;
        Bounds bounds8 = this.mBounds;
        Bounds bounds9 = bounds8;
        Bounds bounds10 = bounds9;
        list3.add(new PointQuadTree(bounds8.midX, bounds9.maxX, bounds10.midY, bounds10.maxY, this.mDepth + 1));
        List<T> list4 = this.mItems;
        this.mItems = null;
        for (T t : list4) {
            insert(t.getPoint().f3036x, t.getPoint().y, t);
        }
    }

    public void add(T t) {
        Point point = t.getPoint();
        if (this.mBounds.contains(point.f3036x, point.y)) {
            insert(point.f3036x, point.y, t);
        }
    }

    public void clear() {
        List<PointQuadTree<T>> list = this.mChildren;
        if (list != null) {
            list.clear();
        }
        this.mChildren = null;
        List<T> list2 = this.mItems;
        if (list2 != null) {
            list2.clear();
        }
        this.mItems = null;
    }

    public Collection<T> search(Point point, double d) {
        Bounds lambda$search$0 = lambda$search$0(d, point);
        ArrayList arrayList = new ArrayList();
        search(lambda$search$0, arrayList);
        return arrayList;
    }

    private PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double d, double d2, double d3, double d5, int i2) {
        this(new Bounds(d, d2, d3, d5), i2);
    }

    private PointQuadTree(Bounds bounds, int i2) {
        this.mChildren = null;
        this.mBounds = bounds;
        this.mDepth = i2;
    }

    private void search(Bounds bounds, Collection<T> collection) {
        if (this.mBounds.intersects(bounds)) {
            List<PointQuadTree<T>> list = this.mChildren;
            if (list != null) {
                for (PointQuadTree<T> search : list) {
                    search.search(bounds, collection);
                }
            } else if (this.mItems == null) {
            } else {
                if (bounds.contains(this.mBounds)) {
                    collection.addAll(this.mItems);
                    return;
                }
                for (T t : this.mItems) {
                    if (bounds.contains(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }
}
