package com.samsung.android.gallery.app.activity.external;

import A4.A;
import A4.C0372g;
import D3.b;
import D3.c;
import D3.d;
import D3.e;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.TooltipCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.android.gallery.app.ui.map.LocationPermissionUtil;
import com.samsung.android.gallery.app.ui.map.factory.GalleryMapFactory;
import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.map.abstraction.ISimpleMarker;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.map.manager.AddressHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.function.Consumer;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocationPickerActivity extends AppCompatActivity implements MapPickerContainer.MapClickListener, q {
    /* access modifiers changed from: private */
    public AddressHelper.GetAddressTask mAddressTask;
    private ImageView mCurrentLocationBtn;
    private View mDoneButton;
    private boolean mIsNightMode;
    private MapPickerContainer mMapContainer;
    private FrameLayout mMapLayout;
    private final SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {
        public boolean onQueryTextChange(String str) {
            return false;
        }

        public boolean onQueryTextSubmit(String str) {
            if (LocationPickerActivity.this.mSearchView == null) {
                return false;
            }
            String charSequence = LocationPickerActivity.this.mSearchView.getQuery().toString();
            if (!TextUtils.isEmpty(charSequence)) {
                LocationPickerActivity locationPickerActivity = LocationPickerActivity.this;
                locationPickerActivity.mAddressTask = locationPickerActivity.startAddressTask(charSequence);
            }
            LocationPickerActivity.this.mSearchView.clearFocus();
            return false;
        }
    };
    /* access modifiers changed from: private */
    public SearchView mSearchView;

    private void clearAddressTask() {
        AddressHelper.GetAddressTask getAddressTask = this.mAddressTask;
        if (getAddressTask != null) {
            getAddressTask.setUpdateListener((AddressHelper.OnAddressUpdateListener) null);
            this.mAddressTask.cancel(true);
            this.mAddressTask = null;
        }
    }

    private void handleCancelButtonClick() {
        setResult(0);
        finish();
    }

    private void handleDoneButtonClick() {
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            double[] location = mapPickerContainer.getLocation();
            Bundle bundle = new Bundle();
            bundle.putString("address", this.mMapContainer.getAddressText());
            bundle.putDouble("latitude", location[0]);
            bundle.putDouble("longitude", location[1]);
            setResult(-1, new Intent().putExtras(bundle));
            finish();
            return;
        }
        handleCancelButtonClick();
    }

    private void handleGeocoderFailure(AddressCompat addressCompat) {
        if (addressCompat != null) {
            Log.d("LocationPickerActivity", "invalid geocoder result. move camera only " + Logger.getEncodedString(addressCompat.toString()));
            this.mMapContainer.moveCamera(addressCompat.latitude, addressCompat.longitude, "", "");
            setEnableDoneButton(true);
            return;
        }
        Log.w("LocationPickerActivity", "invalid geocoder result. move failed");
        setEnableDoneButton(false);
        Toast.makeText(this, R.string.no_location, 0).show();
    }

    private void initMapContainer(String str, double d, double d2) {
        if (this.mMapContainer == null) {
            MapPickerContainer createPickerMap = GalleryMapFactory.createPickerMap(this);
            this.mMapContainer = createPickerMap;
            createPickerMap.setLayoutProvider(new MapPickerContainer.LayoutProvider() {
                public View inflateDimLayout(View view) {
                    if (view.findViewById(R.id.map_dim_layer) != null) {
                        return null;
                    }
                    View inflate = View.inflate(view.getContext(), R.layout.location_picker_activity_dim_layer, (ViewGroup) null);
                    ((ViewGroup) view).addView(inflate);
                    return inflate;
                }

                public View inflateMarkerInfo(Context context, String str) {
                    LinearLayout linearLayout = (LinearLayout) View.inflate(context, R.layout.map_marker_info_window, (ViewGroup) null);
                    ((TextView) linearLayout.findViewById(R.id.map_marker_title_view)).setText(str);
                    return linearLayout;
                }
            });
        }
        this.mMapContainer.saveData(d, d2, str);
        this.mMapContainer.setOnMapClickListener(new c(this));
        this.mMapContainer.setOnMarkerDragEndListener(new c(this));
        View view = this.mMapContainer.getView();
        if (view != null) {
            swapView(this.mMapLayout, view);
            if (LocationPermissionUtil.isDeviceLocationStatusAvailable(this) && LocationPermissionUtil.hasLocationPermission(this, false)) {
                requestCurrentLocation();
            }
        }
    }

    private void initSearchView(String str) {
        this.mSearchView.setOnQueryTextListener(this.mQueryTextListener);
        this.mSearchView.seslSetOnUpButtonClickListener(new d(this, 1));
        if (!TextUtils.isEmpty(str)) {
            this.mSearchView.setQuery(str, false);
        } else {
            setEnableDoneButton(false);
        }
    }

    private void initViews() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.location_picker_map_layout);
        this.mMapLayout = frameLayout;
        frameLayout.setClipToOutline(true);
        this.mSearchView = (SearchView) findViewById(R.id.location_search_view);
        ImageView imageView = (ImageView) findViewById(R.id.current_location_button);
        this.mCurrentLocationBtn = imageView;
        imageView.setOnClickListener(new d(this, 0));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.location_picker_bottom_bar);
        bottomNavigationView.setOnItemSelectedListener(this);
        this.mDoneButton = bottomNavigationView.findViewById(R.id.menu_location_edit_done);
        SeApiCompat.getHoverViewCompat().setPopupNoneType(this.mCurrentLocationBtn);
        ImageView imageView2 = this.mCurrentLocationBtn;
        TooltipCompat.setTooltipText(imageView2, imageView2.getContentDescription());
    }

    private boolean isNightMode(Configuration configuration) {
        if ((configuration.uiMode & 48) == 32) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapContainer$0(double d, double d2) {
        this.mAddressTask = startAddressTask(d, d2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapContainer$1(ISimpleMarker iSimpleMarker) {
        this.mAddressTask = startAddressTaskForMarker(iSimpleMarker);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onRequestPermissionsResult$6(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestCurrentLocation$5() {
        if (!tryStartAddressTask()) {
            Log.e("LocationPickerActivity", "fail update current location");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddressTask$2(AddressCompat addressCompat) {
        lambda$startAddressTaskForMarker$4(addressCompat, (ISimpleMarker) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startAddressTask$3(AddressCompat addressCompat) {
        lambda$startAddressTaskForMarker$4(addressCompat, (ISimpleMarker) null);
    }

    /* access modifiers changed from: private */
    public void onCurrentLocationButtonClicked(View view) {
        if (LocationPermissionUtil.isDeviceLocationStatusAvailable(this, R.string.moreinfo_location_editor_gps_popup_body) && LocationPermissionUtil.hasLocationPermission(this, true)) {
            requestCurrentLocation();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (!(inputMethodManager == null || view == null)) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            sendSALogging(AnalyticsEventId.EVENT_MENU_LOCATION_PICKER_CURRENT_LOCATION.toString());
        }
    }

    /* access modifiers changed from: private */
    public void onNavigationUp(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager != null && inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception unused) {
            Log.e("LocationPickerActivity", "onDestroy soft-key hide failed");
        }
        finish();
    }

    private void requestCurrentLocation() {
        if (!tryStartAddressTask()) {
            this.mMapContainer.requestCurrentLocationUpdate(getApplicationContext(), new C0372g(23, this));
        }
    }

    private void sendSALogging(String str) {
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_MAP_VIEW_LOCATION_PICKER.toString(), str);
    }

    private void setEnableDoneButton(boolean z) {
        View view = this.mDoneButton;
        if (view != null) {
            view.setEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public AddressHelper.GetAddressTask startAddressTask(String str) {
        LocationManager.getInstance().loadLocation(str, (Consumer<AddressCompat>) new e(this, 1));
        return null;
    }

    private void swapView(View view, View view2) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            int indexOfChild = viewGroup.indexOfChild(view);
            view2.setLayoutParams(view.getLayoutParams());
            ViewUtils.removeView(viewGroup, view);
            viewGroup.addView(view2, indexOfChild);
        }
    }

    private boolean tryStartAddressTask() {
        double[] storedCurrentLocation = this.mMapContainer.getStoredCurrentLocation();
        if (MapUtil.isValidLocation(storedCurrentLocation[0], storedCurrentLocation[1])) {
            this.mAddressTask = startAddressTask(storedCurrentLocation[0], storedCurrentLocation[1]);
            return true;
        }
        Log.w("LocationPickerActivity", "tryStartAddressTask skip. wrong location");
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: updateAll */
    public void lambda$startAddressTaskForMarker$4(AddressCompat addressCompat, ISimpleMarker iSimpleMarker) {
        SearchView searchView;
        if (addressCompat == null || addressCompat.hasNoAddress()) {
            handleGeocoderFailure(addressCompat);
            return;
        }
        Log.d("LocationPickerActivity", "onUpdateAll");
        SearchView searchView2 = this.mSearchView;
        if (searchView2 != null) {
            searchView2.requestFocus();
        }
        double d = addressCompat.latitude;
        double d2 = addressCompat.longitude;
        String str = addressCompat.addressSimple;
        String str2 = addressCompat.addressText;
        if (AddressCompat.SUPPORT_PLAIN_ADDRESS && !TextUtils.isEmpty(addressCompat.addressPlain)) {
            str2 = addressCompat.addressPlain;
        }
        String str3 = str2;
        if (iSimpleMarker == null) {
            this.mMapContainer.moveCamera(d, d2, str3, str);
        } else {
            this.mMapContainer.saveData(d, d2, str3);
            iSimpleMarker.updateTitle(str);
        }
        setEnableDoneButton(true);
        SearchView searchView3 = this.mSearchView;
        if (searchView3 != null) {
            searchView3.setQuery(str3, false);
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null && (searchView = this.mSearchView) != null && !inputMethodManager.isActive(searchView)) {
            this.mSearchView.clearFocus();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsNightMode != isNightMode(configuration)) {
            Log.e("LocationPickerActivity", "Finish: night mode changed");
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        String str;
        double d;
        double d2;
        super.onCreate(bundle);
        setContentView((int) R.layout.location_picker_activity);
        initViews();
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("address");
            double doubleExtra = intent.getDoubleExtra("latitude", MapUtil.INVALID_LOCATION);
            d2 = intent.getDoubleExtra("longitude", MapUtil.INVALID_LOCATION);
            d = doubleExtra;
        } else {
            str = "";
            d = 0.0d;
            d2 = 0.0d;
        }
        String str2 = str;
        initMapContainer(str2, d, d2);
        this.mMapContainer.onCreate(bundle);
        initSearchView(str2);
        this.mIsNightMode = isNightMode(getResources().getConfiguration());
    }

    public void onDestroy() {
        clearAddressTask();
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            mapPickerContainer.setOnMapClickListener((MapPickerContainer.MapClickListener) null);
            this.mMapContainer.setOnMarkerDragEndListener((MapPickerContainer.MarkerDragEndListener) null);
            this.mMapContainer.setLayoutProvider((MapPickerContainer.LayoutProvider) null);
            this.mMapContainer.onDestroy();
        }
        super.onDestroy();
    }

    public void onMapClicked(double d, double d2) {
        this.mAddressTask = startAddressTask(d, d2);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem == null) {
            return true;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_location_edit_done) {
            sendSALogging(AnalyticsEventId.EVENT_DONE.toString());
            handleDoneButtonClick();
            return true;
        } else if (itemId != R.id.menu_location_edit_cancel) {
            return true;
        } else {
            sendSALogging(AnalyticsEventId.EVENT_MENU_LOCATION_PICKER_CANCEL.toString());
            handleCancelButtonClick();
            return true;
        }
    }

    public void onPause() {
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            mapPickerContainer.onPause();
        }
        super.onPause();
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        boolean z;
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (strArr.length == 0 || iArr.length == 0) {
            Log.e("LocationPickerActivity", "onRequestPermissionsResult length is zero");
            return;
        }
        if (Arrays.stream(iArr).filter(new b(0)).count() > 0) {
            z = true;
        } else {
            z = false;
        }
        Log.d("LocationPickerActivity", "onRequestPermissionsResult", Boolean.valueOf(z));
        if (z) {
            onCurrentLocationButtonClicked(this.mCurrentLocationBtn);
        } else {
            LocationPermissionUtil.showPermissionRationaleDialog(this);
        }
    }

    public void onResume() {
        super.onResume();
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            mapPickerContainer.onResume();
        }
    }

    public void onStart() {
        super.onStart();
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            mapPickerContainer.onStart();
        }
    }

    public void onStop() {
        MapPickerContainer mapPickerContainer = this.mMapContainer;
        if (mapPickerContainer != null) {
            mapPickerContainer.onStop();
        }
        super.onStop();
    }

    public AddressHelper.GetAddressTask startAddressTaskForMarker(ISimpleMarker iSimpleMarker) {
        double[] position = iSimpleMarker.getPosition();
        LocationManager.getInstance().loadLocationFull(position[0], position[1], new A(9, (Object) this, (Object) iSimpleMarker));
        return null;
    }

    private AddressHelper.GetAddressTask startAddressTask(double d, double d2) {
        LocationManager.getInstance().loadLocationFull(d, d2, new e(this, 0));
        return null;
    }
}
