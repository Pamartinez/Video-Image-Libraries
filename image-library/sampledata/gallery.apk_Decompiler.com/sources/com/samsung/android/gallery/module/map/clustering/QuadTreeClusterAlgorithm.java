package com.samsung.android.gallery.module.map.clustering;

import A4.C0367b;
import com.samsung.android.gallery.module.map.clustering.IClusterItem;
import com.samsung.android.gallery.module.map.clustering.PointQuadTree;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuadTreeClusterAlgorithm<T extends IClusterItem> implements IAlgorithm<T> {
    private boolean mInterrupted = false;
    final Collection<QuadItem<T>> mItems = new ArrayList();
    private final IProjection mProjection;
    final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<>(MapUtil.INVALID_LOCATION, 1.0d, MapUtil.INVALID_LOCATION, 1.0d);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuadItem<T extends IClusterItem> implements PointQuadTree.Item, ICluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final double[] mPosition;
        private final Set<T> mSingleTonSet;

        public /* synthetic */ QuadItem(IClusterItem iClusterItem, IProjection iProjection, int i2) {
            this(iClusterItem, iProjection);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof QuadItem) || !((QuadItem) obj).getTopItem().equals(this.mClusterItem)) {
                return false;
            }
            return true;
        }

        public List<T> getItems() {
            return new ArrayList(this.mSingleTonSet);
        }

        public Point getPoint() {
            return this.mPoint;
        }

        public double[] getPosition() {
            return this.mPosition;
        }

        public int getSize() {
            return 1;
        }

        public T getTopItem() {
            return this.mClusterItem;
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }

        public boolean isEntryItem() {
            return this.mClusterItem.isEntryItem();
        }

        public boolean isItemIncluded(long j2) {
            return this.mClusterItem.isItemIncluded(j2);
        }

        public void setEntryItem(boolean z) {
            this.mClusterItem.setEntryItem(z);
        }

        private QuadItem(T t, IProjection iProjection) {
            this.mClusterItem = t;
            double[] position = t.getPosition();
            this.mPosition = position;
            this.mPoint = iProjection.toPoint(position);
            this.mSingleTonSet = Collections.singleton(t);
        }
    }

    public QuadTreeClusterAlgorithm(IProjection iProjection) {
        this.mProjection = iProjection;
    }

    public static double distanceSquared(Point point, Point point2) {
        double d = point.f3036x - point2.f3036x;
        double d2 = point.y - point2.y;
        return (d2 * d2) + (d * d);
    }

    private boolean isInterrupted() {
        return this.mInterrupted;
    }

    public void addItem(T t) {
        QuadItem quadItem = new QuadItem(t, this.mProjection, 0);
        synchronized (this.mQuadTree) {
            this.mItems.add(quadItem);
            this.mQuadTree.add(quadItem);
        }
    }

    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    public Set<ICluster<T>> getClusters(double d) {
        double d2;
        QuadTreeClusterAlgorithm quadTreeClusterAlgorithm = this;
        double d3 = 2.0d;
        double pow = (100.0d / Math.pow(2.0d, d)) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        quadTreeClusterAlgorithm.mInterrupted = false;
        synchronized (quadTreeClusterAlgorithm.mQuadTree) {
            try {
                for (QuadItem next : quadTreeClusterAlgorithm.mItems) {
                    if (quadTreeClusterAlgorithm.isInterrupted()) {
                        throw new IllegalStateException("");
                    } else if (!hashSet.contains(next)) {
                        Collection<QuadItem<T>> search = quadTreeClusterAlgorithm.mQuadTree.search(next.getPoint(), pow / d3);
                        if (search.size() == 1) {
                            hashSet2.add(next);
                            hashSet.add(next);
                            hashMap.put(next, Double.valueOf(MapUtil.INVALID_LOCATION));
                        } else {
                            StaticCluster staticCluster = new StaticCluster(next.getTopItem().getPosition());
                            hashSet2.add(staticCluster);
                            for (QuadItem next2 : search) {
                                if (!quadTreeClusterAlgorithm.isInterrupted()) {
                                    Double d5 = (Double) hashMap.get(next2);
                                    double distanceSquared = distanceSquared(next2.getPoint(), next.getPoint());
                                    if (d5 == null) {
                                        d2 = distanceSquared;
                                    } else if (d5.doubleValue() >= distanceSquared) {
                                        d2 = distanceSquared;
                                        Optional.ofNullable((StaticCluster) hashMap2.get(next2)).ifPresent(new C0367b(10, next2));
                                    }
                                    hashMap.put(next2, Double.valueOf(d2));
                                    if (next2.isEntryItem()) {
                                        staticCluster.addFirst(next2.getTopItem());
                                    } else {
                                        staticCluster.add(next2.getTopItem());
                                    }
                                    hashMap2.put(next2, staticCluster);
                                    quadTreeClusterAlgorithm = this;
                                } else {
                                    throw new IllegalStateException("");
                                }
                            }
                            hashSet.addAll(search);
                            d3 = 2.0d;
                            quadTreeClusterAlgorithm = this;
                        }
                    }
                }
            } finally {
            }
        }
        return hashSet2;
    }

    public void interrupt() {
        this.mInterrupted = true;
    }
}
