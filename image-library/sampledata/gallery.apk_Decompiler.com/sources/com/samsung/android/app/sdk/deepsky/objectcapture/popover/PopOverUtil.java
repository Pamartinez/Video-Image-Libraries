package com.samsung.android.app.sdk.deepsky.objectcapture.popover;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.objectcapture.R;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/PopOverUtil;", "", "<init>", "()V", "TAG", "", "POSITION_LANDSCAPE", "", "POSITION_PORTRAIT", "MINIMUM_WIDTH", "getPopOverDetails", "Landroid/os/Bundle;", "context", "Landroid/content/Context;", "isPickerPopOverEnabled", "", "deviceType", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "getScreenSizeInDP", "pixelToDp", "px", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PopOverUtil {
    public static final PopOverUtil INSTANCE = new PopOverUtil();
    private static final int MINIMUM_WIDTH = 600;
    private static final int POSITION_LANDSCAPE = 0;
    private static final int POSITION_PORTRAIT = 1;
    private static final String TAG = "PopOverUtil";

    private PopOverUtil() {
    }

    private final int getScreenSizeInDP(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }

    private final int pixelToDp(Context context, float f) {
        return (int) (f / context.getResources().getDisplayMetrics().density);
    }

    public final Bundle getPopOverDetails(Context context) {
        j.e(context, "context");
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        Point[] pointArr = new Point[2];
        int pixelToDp = pixelToDp(context, context.getResources().getDimension(R.dimen.popover_default_width));
        int pixelToDp2 = pixelToDp(context, context.getResources().getDimension(R.dimen.popover_default_height));
        Resources resources = context.getResources();
        int i2 = R.dimen.popover_start_end_margin;
        int pixelToDp3 = pixelToDp(context, resources.getDimension(i2));
        int pixelToDp4 = pixelToDp(context, context.getResources().getDimension(i2));
        iArr[1] = pixelToDp;
        iArr[0] = pixelToDp;
        iArr2[1] = pixelToDp2;
        iArr2[0] = pixelToDp2;
        pointArr[1] = new Point(pixelToDp3, pixelToDp4);
        pointArr[0] = new Point(pixelToDp3, pixelToDp4);
        Log.i(TAG, "Target width : " + pixelToDp + ", Target height : " + pixelToDp2);
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.semSetPopOverOptions(iArr, iArr2, pointArr, new int[]{36, 36});
        return makeBasic.toBundle();
    }

    public final boolean isPickerPopOverEnabled(Context context, DeviceType deviceType) {
        j.e(context, "context");
        j.e(deviceType, "deviceType");
        if (deviceType == DeviceType.NORMAL_TYPE || getScreenSizeInDP(context) < MINIMUM_WIDTH) {
            return false;
        }
        return true;
    }
}
