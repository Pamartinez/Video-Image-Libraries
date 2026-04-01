package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationCoordinate {
    private static final double _a = 6378245.0d;
    private static final double _ee = 0.006693421622965943d;

    public static double[] convertGCJtoWGS(double d, double d2) {
        return convertGCJtoWGS(new double[]{d, d2});
    }

    public static double[] convertWGSToGCJ(double d, double d2) {
        return convertWGSToGCJ(new double[]{d, d2});
    }

    private static boolean outOfChina(double[] dArr) {
        double d = dArr[1];
        if (d >= 72.004d && d <= 137.8347d) {
            double d2 = dArr[0];
            if (d2 < 0.8293d || d2 > 55.8271d) {
                return true;
            }
            return false;
        }
        return true;
    }

    private static double tLat(double d, double d2) {
        double d3;
        double d5 = d;
        double d6 = d5 * 2.0d;
        double d7 = d2 * 0.2d * d2;
        double d9 = (0.1d * d5 * d2) + d7 + (d2 * 3.0d) + -100.0d + d6;
        if (d5 > MapUtil.INVALID_LOCATION) {
            d3 = d5;
        } else {
            d3 = -d5;
        }
        double d10 = d2 * 3.141592653589793d;
        return ((((Math.sin(d10 / 30.0d) * 320.0d) + (Math.sin((d2 / 12.0d) * 3.141592653589793d) * 160.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 3.0d) * 3.141592653589793d) * 40.0d) + (Math.sin(d10) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d6 * 3.141592653589793d) * 20.0d) + (Math.sin((d5 * 6.0d) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + (Math.sqrt(d3) * 0.2d) + d9;
    }

    private static double tLon(double d, double d2) {
        double d3;
        double d5 = d;
        double d6 = d5 * 0.1d;
        double d7 = d6 * d5;
        double d9 = d6 * d2;
        double d10 = d9 + d7 + (d2 * 2.0d) + d5 + 300.0d;
        if (d5 > MapUtil.INVALID_LOCATION) {
            d3 = d5;
        } else {
            d3 = -d5;
        }
        double sin = ((((Math.sin((d5 * 2.0d) * 3.141592653589793d) * 20.0d) + (Math.sin((6.0d * d5) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + (Math.sqrt(d3) * 0.1d) + d10;
        double sin2 = Math.sin((d5 / 3.0d) * 3.141592653589793d) * 40.0d;
        return ((((Math.sin((d5 / 30.0d) * 3.141592653589793d) * 300.0d) + (Math.sin((d5 / 12.0d) * 3.141592653589793d) * 150.0d)) * 2.0d) / 3.0d) + (((sin2 + (Math.sin(d5 * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + sin;
    }

    public static double[] convertGCJtoWGS(double[] dArr) {
        if (outOfChina(dArr)) {
            return dArr;
        }
        double[] convertWGSToGCJ = convertWGSToGCJ(dArr);
        return new double[]{(dArr[0] * 2.0d) - convertWGSToGCJ[0], (dArr[1] * 2.0d) - convertWGSToGCJ[1]};
    }

    public static double[] convertWGSToGCJ(double[] dArr) {
        if (outOfChina(dArr)) {
            return dArr;
        }
        double d = (dArr[0] / 180.0d) * 3.141592653589793d;
        double sin = 1.0d - (Math.sin(d) * (Math.sin(d) * _ee));
        double tLat = tLat(dArr[1] - 105.0d, dArr[0] - 35.0d);
        double tLon = tLon(dArr[1] - 105.0d, dArr[0] - 35.0d);
        double sqrt = _a / Math.sqrt(sin);
        return new double[]{dArr[0] + ((tLat * 180.0d) / ((6335552.717000426d / (Math.sqrt(sin) * sin)) * 3.141592653589793d)), dArr[1] + ((tLon * 180.0d) / ((Math.cos(d) * sqrt) * 3.141592653589793d))};
    }
}
