package com.samsung.android.gallery.module.map.model;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MapLatLngBounds {
    public final MapLatLng northeast;
    public final MapLatLng southwest;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private double amQ = Double.POSITIVE_INFINITY;
        private double amR = Double.NEGATIVE_INFINITY;
        private double amS = Double.NaN;
        private double amT = Double.NaN;

        public static void zza(boolean z, Object obj) {
            if (!z) {
                throw new IllegalStateException(String.valueOf(obj));
            }
        }

        private boolean zzi(double d) {
            double d2 = this.amS;
            double d3 = this.amT;
            if (d2 <= d3) {
                if (d2 > d || d > d3) {
                    return false;
                }
                return true;
            } else if (d2 <= d || d <= d3) {
                return true;
            } else {
                return false;
            }
        }

        public MapLatLngBounds build() {
            zza(!Double.isNaN(this.amS), "no included points");
            return new MapLatLngBounds(new MapLatLng(this.amQ, this.amS), new MapLatLng(this.amR, this.amT));
        }

        public Builder include(MapLatLng mapLatLng) {
            this.amQ = Math.min(this.amQ, mapLatLng.latitude);
            this.amR = Math.max(this.amR, mapLatLng.latitude);
            double d = mapLatLng.longitude;
            if (Double.isNaN(this.amS)) {
                this.amS = d;
                this.amT = d;
                return this;
            }
            if (!zzi(d)) {
                if (MapLatLngBounds.zzb(this.amS, d) < MapLatLngBounds.zzc(this.amT, d)) {
                    this.amS = d;
                    return this;
                }
                this.amT = d;
            }
            return this;
        }
    }

    public MapLatLngBounds(MapLatLng mapLatLng, MapLatLng mapLatLng2) {
        this.southwest = mapLatLng;
        this.northeast = mapLatLng2;
    }

    private boolean containsLat(double d) {
        if (this.southwest.latitude > d || d > this.northeast.latitude) {
            return false;
        }
        return true;
    }

    private boolean containsLng(double d) {
        double d2 = this.southwest.longitude;
        double d3 = this.northeast.longitude;
        if (d2 <= d3) {
            if (d2 > d || d > d3) {
                return false;
            }
            return true;
        } else if (d2 <= d || d <= d3) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* access modifiers changed from: private */
    public static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    public boolean contains(MapLatLng mapLatLng) {
        if (!containsLat(mapLatLng.latitude) || !containsLng(mapLatLng.longitude)) {
            return false;
        }
        return true;
    }
}
