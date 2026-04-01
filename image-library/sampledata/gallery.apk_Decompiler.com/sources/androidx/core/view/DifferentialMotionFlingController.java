package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DifferentialMotionFlingController {
    private final Context mContext;
    private final int[] mFlingVelocityThresholds;
    private float mLastFlingVelocity;
    private int mLastProcessedAxis;
    private int mLastProcessedDeviceId;
    private int mLastProcessedSource;
    private final DifferentialMotionFlingTarget mTarget;
    private final DifferentialVelocityProvider mVelocityProvider;
    private final FlingVelocityThresholdCalculator mVelocityThresholdCalculator;
    private VelocityTracker mVelocityTracker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DifferentialVelocityProvider {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FlingVelocityThresholdCalculator {
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [androidx.core.view.DifferentialMotionFlingController$FlingVelocityThresholdCalculator, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.core.view.DifferentialMotionFlingController$DifferentialVelocityProvider] */
    public DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget) {
        this(context, differentialMotionFlingTarget, new Object(), new Object());
    }

    private boolean calculateFlingVelocityThresholds(MotionEvent motionEvent, int i2) {
        int source = motionEvent.getSource();
        int deviceId = motionEvent.getDeviceId();
        if (this.mLastProcessedSource == source && this.mLastProcessedDeviceId == deviceId && this.mLastProcessedAxis == i2) {
            return false;
        }
        FlingVelocityThresholdCalculator flingVelocityThresholdCalculator = this.mVelocityThresholdCalculator;
        Context context = this.mContext;
        int[] iArr = this.mFlingVelocityThresholds;
        ((a) flingVelocityThresholdCalculator).getClass();
        calculateFlingVelocityThresholds(context, iArr, motionEvent, i2);
        this.mLastProcessedSource = source;
        this.mLastProcessedDeviceId = deviceId;
        this.mLastProcessedAxis = i2;
        return true;
    }

    private float getCurrentVelocity(MotionEvent motionEvent, int i2) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        DifferentialVelocityProvider differentialVelocityProvider = this.mVelocityProvider;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        ((a) differentialVelocityProvider).getClass();
        return getCurrentVelocity(velocityTracker, motionEvent, i2);
    }

    public void onMotionEvent(MotionEvent motionEvent, int i2) {
        boolean calculateFlingVelocityThresholds = calculateFlingVelocityThresholds(motionEvent, i2);
        if (this.mFlingVelocityThresholds[0] == Integer.MAX_VALUE) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
                return;
            }
            return;
        }
        float scaledScrollFactor = this.mTarget.getScaledScrollFactor() * getCurrentVelocity(motionEvent, i2);
        float signum = Math.signum(scaledScrollFactor);
        float f = 0.0f;
        if (calculateFlingVelocityThresholds || !(signum == Math.signum(this.mLastFlingVelocity) || signum == 0.0f)) {
            this.mTarget.stopDifferentialMotionFling();
        }
        float abs = Math.abs(scaledScrollFactor);
        int[] iArr = this.mFlingVelocityThresholds;
        if (abs >= ((float) iArr[0])) {
            int i7 = iArr[1];
            float max = Math.max((float) (-i7), Math.min(scaledScrollFactor, (float) i7));
            if (this.mTarget.startDifferentialMotionFling(max)) {
                f = max;
            }
            this.mLastFlingVelocity = f;
        }
    }

    public DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget, FlingVelocityThresholdCalculator flingVelocityThresholdCalculator, DifferentialVelocityProvider differentialVelocityProvider) {
        this.mLastProcessedAxis = -1;
        this.mLastProcessedSource = -1;
        this.mLastProcessedDeviceId = -1;
        this.mFlingVelocityThresholds = new int[]{Integer.MAX_VALUE, 0};
        this.mContext = context;
        this.mTarget = differentialMotionFlingTarget;
        this.mVelocityThresholdCalculator = flingVelocityThresholdCalculator;
        this.mVelocityProvider = differentialVelocityProvider;
    }

    private static float getCurrentVelocity(VelocityTracker velocityTracker, MotionEvent motionEvent, int i2) {
        VelocityTrackerCompat.addMovement(velocityTracker, motionEvent);
        VelocityTrackerCompat.computeCurrentVelocity(velocityTracker, 1000);
        return VelocityTrackerCompat.getAxisVelocity(velocityTracker, i2);
    }

    private static void calculateFlingVelocityThresholds(Context context, int[] iArr, MotionEvent motionEvent, int i2) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        iArr[0] = ViewConfigurationCompat.getScaledMinimumFlingVelocity(context, viewConfiguration, motionEvent.getDeviceId(), i2, motionEvent.getSource());
        iArr[1] = ViewConfigurationCompat.getScaledMaximumFlingVelocity(context, viewConfiguration, motionEvent.getDeviceId(), i2, motionEvent.getSource());
    }
}
