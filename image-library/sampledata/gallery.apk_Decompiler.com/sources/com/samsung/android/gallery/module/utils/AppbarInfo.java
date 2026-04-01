package com.samsung.android.gallery.module.utils;

import A.a;
import android.content.Context;
import android.util.DisplayMetrics;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppbarInfo {
    private static final Object LOCK_APPBAR_INFO = new Object();
    private static final AppbarData[] sAppbarInfoArray = {new AppbarData(0), new AppbarDataLandscape(0)};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppbarData {
        protected final String TAG;
        private Integer deviceDensityDpi;
        private Integer deviceHeightPixels;
        private Integer height;
        private Integer heightAvailable;
        private Integer indexOfHeight;
        private Boolean spaceEnough;

        public /* synthetic */ AppbarData(int i2) {
            this();
        }

        private void ensureResolution(Context context) {
            DisplayMetrics realDisplayMetrics = DeviceInfo.getRealDisplayMetrics(context);
            Integer num = this.deviceDensityDpi;
            if (num == null || this.deviceHeightPixels == null || num.intValue() != realDisplayMetrics.densityDpi || this.deviceHeightPixels.intValue() != realDisplayMetrics.heightPixels) {
                if (this.deviceDensityDpi != null) {
                    String str = this.TAG;
                    Log.d(str, "appbar recycled {" + realDisplayMetrics.densityDpi + GlobalPostProcInternalPPInterface.SPLIT_REGEX + realDisplayMetrics.heightPixels + "} changed from {" + this.deviceDensityDpi + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.deviceHeightPixels + "}");
                }
                this.deviceDensityDpi = Integer.valueOf(realDisplayMetrics.densityDpi);
                this.deviceHeightPixels = Integer.valueOf(realDisplayMetrics.heightPixels);
                this.height = null;
                this.spaceEnough = null;
                this.heightAvailable = null;
                this.indexOfHeight = null;
            }
        }

        private int getAvailableHeight() {
            if (this.heightAvailable == null) {
                this.heightAvailable = Integer.valueOf(this.deviceHeightPixels.intValue() - getSystemBarHeight());
            }
            return this.heightAvailable.intValue();
        }

        private int[] getHeightPixels(DisplayMetrics displayMetrics) {
            int[] heightRangeArray = getHeightRangeArray();
            int length = heightRangeArray.length;
            int[] iArr = new int[length];
            float f = displayMetrics.density;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = Math.round(((float) heightRangeArray[i2]) * f);
            }
            return iArr;
        }

        private int indexOf(Context context) {
            if (this.indexOfHeight == null) {
                this.indexOfHeight = Integer.valueOf(indexOfHeightPixels(context));
            }
            return this.indexOfHeight.intValue();
        }

        private int indexOfHeightPixels(Context context) {
            DisplayMetrics displayMetrics = ResourceCompat.getDisplayMetrics(context);
            int[] heightPixels = getHeightPixels(displayMetrics);
            int i2 = displayMetrics.heightPixels;
            for (int i7 = 0; i7 < heightPixels.length; i7++) {
                if (i2 <= heightPixels[i7]) {
                    return i7;
                }
            }
            return 0;
        }

        public int[] getExpandedToolbarVisibilityArray() {
            return new int[]{1, 1, 0, 0};
        }

        public final int getHeight(Context context) {
            ensureResolution(context);
            if (this.height == null) {
                this.height = Integer.valueOf((int) (((float) getAvailableHeight()) * getHeightRatioArray()[indexOf(context)]));
                Log.d(this.TAG, toString());
            }
            return this.height.intValue();
        }

        public int[] getHeightRangeArray() {
            return new int[]{580, 959, 1919, 30000};
        }

        public float[] getHeightRatioArray() {
            return new float[]{0.39f, 0.39f, 0.25f, 0.25f};
        }

        public int getSystemBarHeight() {
            try {
                return DeviceInfo.getNavigationBarHeight() + DeviceInfo.getStatusBarHeight();
            } catch (Exception e) {
                a.s(e, new StringBuilder("getSystemBarHeight failed e="), this.TAG);
                return 0;
            }
        }

        public final boolean hasEnoughSpace(Context context) {
            ensureResolution(context);
            if (this.spaceEnough == null) {
                boolean z = true;
                if (getExpandedToolbarVisibilityArray()[indexOf(context)] != 1) {
                    z = false;
                }
                this.spaceEnough = Boolean.valueOf(z);
                String str = this.TAG;
                Log.d(str, "EnoughSpace{" + this.spaceEnough + "}");
            }
            return this.spaceEnough.booleanValue();
        }

        public String tag() {
            return "AppbarData";
        }

        public String toString() {
            return this.TAG + "{" + this.indexOfHeight + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.height + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.spaceEnough + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.heightAvailable + ", RealMetrics(" + this.deviceDensityDpi + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.deviceHeightPixels + ")}";
        }

        private AppbarData() {
            this.TAG = tag();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AppbarDataLandscape extends AppbarData {
        public /* synthetic */ AppbarDataLandscape(int i2) {
            this();
        }

        public int[] getExpandedToolbarVisibilityArray() {
            return new int[]{1, 0, 0, 0};
        }

        public int[] getHeightRangeArray() {
            return new int[]{479, 579, 959, 30000};
        }

        public float[] getHeightRatioArray() {
            return new float[]{0.67f, 0.35f, 0.35f, 0.25f};
        }

        public int getSystemBarHeight() {
            return 0;
        }

        public String tag() {
            return "AppbarDataLandscape";
        }

        private AppbarDataLandscape() {
            super(0);
        }
    }

    public static int getAppbarDefaultBottomPadding(Context context) {
        return ResourceCompat.dpToPixel(context, 12.0f);
    }

    public static int getAppbarHeight(Context context) {
        int height;
        synchronized (LOCK_APPBAR_INFO) {
            height = getAppbarInfo(ResourceCompat.isLandscape(context)).getHeight(context);
        }
        return height;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [boolean] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.samsung.android.gallery.module.utils.AppbarInfo.AppbarData getAppbarInfo(boolean r1) {
        /*
            com.samsung.android.gallery.module.utils.AppbarInfo$AppbarData[] r0 = sAppbarInfoArray
            r1 = r0[r1]
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.utils.AppbarInfo.getAppbarInfo(boolean):com.samsung.android.gallery.module.utils.AppbarInfo$AppbarData");
    }

    public static boolean isAppbarSpaceEnough(Context context) {
        boolean hasEnoughSpace;
        synchronized (LOCK_APPBAR_INFO) {
            hasEnoughSpace = getAppbarInfo(ResourceCompat.isLandscape(context)).hasEnoughSpace(context);
        }
        return hasEnoughSpace;
    }
}
