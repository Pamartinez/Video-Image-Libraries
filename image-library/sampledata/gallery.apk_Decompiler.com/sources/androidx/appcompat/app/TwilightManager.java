package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TwilightManager {
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TwilightState {
        boolean isNight;
        long nextUpdate;
    }

    public TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    public static TwilightManager getInstance(Context context) {
        if (sInstance == null) {
            Context applicationContext = context.getApplicationContext();
            sInstance = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return sInstance;
    }

    private Location getLastKnownLocation() {
        Location location;
        Location location2 = null;
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            location = getLastKnownLocationForProvider("network");
        } else {
            location = null;
        }
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location2 = getLastKnownLocationForProvider("gps");
        }
        if (location2 == null || location == null) {
            if (location2 != null) {
                return location2;
            }
            return location;
        } else if (location2.getTime() > location.getTime()) {
            return location2;
        } else {
            return location;
        }
    }

    private Location getLastKnownLocationForProvider(String str) {
        LocationManager locationManager = this.mLocationManager;
        if (locationManager == null) {
            return null;
        }
        try {
            if (locationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
        }
        return null;
    }

    private boolean isStateValid() {
        TwilightState twilightState = this.mTwilightState;
        if (twilightState == null || twilightState.nextUpdate <= System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    private void updateState(Location location) {
        long j2;
        TwilightState twilightState = this.mTwilightState;
        long currentTimeMillis = System.currentTimeMillis();
        TwilightCalculator instance = TwilightCalculator.getInstance();
        instance.calculateTwilight(currentTimeMillis - MediaApiContract.DAY_IN_MILLI, location.getLatitude(), location.getLongitude());
        TwilightCalculator twilightCalculator = instance;
        twilightCalculator.calculateTwilight(currentTimeMillis, location.getLatitude(), location.getLongitude());
        TwilightCalculator twilightCalculator2 = twilightCalculator;
        boolean z = true;
        if (twilightCalculator2.state != 1) {
            z = false;
        }
        boolean z3 = z;
        long j3 = twilightCalculator2.sunrise;
        long j8 = twilightCalculator2.sunset;
        long j10 = j8;
        twilightCalculator2.calculateTwilight(currentTimeMillis + MediaApiContract.DAY_IN_MILLI, location.getLatitude(), location.getLongitude());
        long j11 = twilightCalculator2.sunrise;
        if (j3 == -1 || j10 == -1) {
            j2 = currentTimeMillis + 43200000;
        } else {
            if (currentTimeMillis > j10) {
                j3 = j11;
            } else if (currentTimeMillis > j3) {
                j3 = j10;
            }
            j2 = j3 + 60000;
        }
        twilightState.isNight = z3;
        twilightState.nextUpdate = j2;
    }

    public boolean isNight() {
        TwilightState twilightState = this.mTwilightState;
        if (isStateValid()) {
            return twilightState.isNight;
        }
        Location lastKnownLocation = getLastKnownLocation();
        if (lastKnownLocation != null) {
            updateState(lastKnownLocation);
            return twilightState.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        if (i2 < 6 || i2 >= 22) {
            return true;
        }
        return false;
    }
}
