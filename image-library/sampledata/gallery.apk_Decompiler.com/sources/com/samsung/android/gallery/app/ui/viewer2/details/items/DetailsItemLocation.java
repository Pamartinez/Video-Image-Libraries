package com.samsung.android.gallery.app.ui.viewer2.details.items;

import A.a;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.app.controller.viewer.MoveToMapViewCmd;
import com.samsung.android.gallery.app.ui.map.staticmarker.MarkerViewHolder;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataRefiner;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.dialog.AppChooserBuilder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemLocation extends DetailsItem {
    private final AccessibilityDelegateCompat mAccessibilityDelegate = new AccessibilityDelegateCompat() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setRoleDescription(AppResources.getString(R.string.speak_button));
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        }
    };
    private String mMapBitmapKey;
    private MarkerViewHolder mMarkerViewHolder;

    public DetailsItemLocation(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewInflate$0(View view) {
        MoveToMapViewCmd moveToMapViewCmd = new MoveToMapViewCmd();
        EventContext eventContext = this.mEventContext;
        moveToMapViewCmd.execute(eventContext, eventContext.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$openNavigation$4(MediaItem mediaItem, Intent intent, ResolveInfo resolveInfo) {
        String str;
        String str2 = resolveInfo.activityInfo.packageName;
        try {
            String addressFull = DetailsData.of(mediaItem).getAddressFull();
            if ("com.nhn.android.nmap".equalsIgnoreCase(str2)) {
                str = "navermaps://?menu=location&pinType=place&lat=" + mediaItem.getLatitude() + "&lng=" + mediaItem.getLongitude() + "&title=" + addressFull;
            } else {
                if (!"com.skt.tmap.ku".equalsIgnoreCase(str2)) {
                    if (!str2.startsWith("com.skt.skaf.")) {
                        if ("net.daum.android.map".equalsIgnoreCase(str2)) {
                            str = "kakaomap://route?ep=" + mediaItem.getLatitude() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getLongitude() + "&by=CAR";
                        } else {
                            intent.setPackage(resolveInfo.activityInfo.packageName);
                            str = "geo:" + mediaItem.getLatitude() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getLongitude() + "?q=" + addressFull;
                        }
                    }
                }
                str = "tmap://route?goalx=" + mediaItem.getLongitude() + "&goaly=" + mediaItem.getLatitude() + "&goalname=" + addressFull;
            }
            intent.setData(Uri.parse(str));
            intent.addFlags(268435456);
            this.mEventContext.getActivity().startActivity(intent);
        } catch (Exception e) {
            a.r(e, new StringBuilder("openNavigation failed. e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public void moveToSettings(View view) {
        new StartSettingsCmd().execute(this.mEventContext, SettingPreference.LocationAuth.key, PopoverUtils.Anchor.TOP_END);
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAILS_GALLERY_SETTINGS);
    }

    /* access modifiers changed from: private */
    public void openNavigation(View view) {
        openNavigation(view.getContext(), this.mEventContext.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public void preUpdateView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        ViewUtils.setVisibility(getView(R.id.moreinfo_map_container), 0);
        ViewUtils.setVisibility(getView(R.id.moreinfo_location_text_container), 0);
    }

    /* access modifiers changed from: private */
    public void updateAddressView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        setTextAndVisibility((int) R.id.moreinfo_location_text, (CharSequence) DetailsData.of(mediaItem).getAddress());
        ViewUtils.setVisibleOrGone(getView(R.id.moreinfo_location_text_container), !TextUtils.isEmpty(DetailsData.of(mediaItem).getAddress()));
        ViewUtils.setVisibility(getView((int) R.id.no_auth_location_and_go_setting, false), 8);
    }

    /* access modifiers changed from: private */
    public void updateMapMarker(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        Object[] objArr = detailsLoadResult.extras;
        if (objArr != null && objArr.length >= 1) {
            MarkerViewHolder markerViewHolder = new MarkerViewHolder(getView(R.id.map_view_marker_holder));
            this.mMarkerViewHolder = markerViewHolder;
            markerViewHolder.setVisibility(0);
            this.mMarkerViewHolder.bind(mediaItem);
            this.mMarkerViewHolder.bindImage((Bitmap) objArr[0]);
            ViewUtils.setVisibility(getView(R.id.moreinfo_map_container), 0);
        }
    }

    /* access modifiers changed from: private */
    public void updateMapSnap(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        Object[] objArr = detailsLoadResult.extras;
        if (objArr != null && objArr.length >= 2) {
            Bitmap bitmap = (Bitmap) objArr[0];
            String str = (String) objArr[1];
            if (!TextUtils.equals(str, this.mMapBitmapKey)) {
                this.mMapBitmapKey = str;
                ((ImageView) getView(R.id.mapImage)).setImageBitmap(bitmap);
            }
            ViewUtils.setVisibility(getView(R.id.moreinfo_map_container), 0);
            SimpleAnimator.setAlphaVisible(getView(R.id.mapImage));
        }
    }

    /* access modifiers changed from: private */
    public void updateNoAuthView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        ViewUtils.setVisibility(getView(R.id.moreinfo_location_text_container), 8);
        ViewUtils.setVisibility(getView(R.id.moreinfo_map_container), 8);
        setTextAndVisibility((int) R.id.no_auth_location_and_go_setting, (CharSequence) DetailsDataRefiner.getNoAuthLocationText(mediaItem));
    }

    /* access modifiers changed from: private */
    public void updatePoiView(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        if (TextUtils.isEmpty(DetailsData.of(mediaItem).getPoi())) {
            ViewUtils.setVisibility(getView((int) R.id.moreinfo_location_poi_layout, false), 8);
        } else {
            ViewUtils.setVisibility(getView((int) R.id.moreinfo_location_text_container, true), 0);
            ViewUtils.setVisibility(getView((int) R.id.moreinfo_location_poi_layout, true), 0);
            setTextAndVisibility((int) R.id.moreinfo_location_poi_text, (CharSequence) DetailsData.of(mediaItem).getPoi());
        }
        ViewUtils.setVisibility(getView((int) R.id.no_auth_location_and_go_setting, false), 8);
    }

    public int getLayoutId() {
        return R.id.moreinfo_location;
    }

    public void onRecycled() {
        super.onRecycled();
        Optional.ofNullable(getView((int) R.id.mapImage, false)).ifPresent(new e(6));
        Optional.ofNullable(this.mMarkerViewHolder).ifPresent(new e(7));
        ViewUtils.setVisibility(getView((int) R.id.moreinfo_location_text, false), 8);
        ViewUtils.setVisibility(getView((int) R.id.moreinfo_location_poi_layout, false), 8);
        this.mMapBitmapKey = null;
    }

    public void onViewInflate(ViewStub viewStub, View view) {
        View view2;
        if (this.mDetailsView.getLocationDim()) {
            view.setAlpha(0.6f);
        } else {
            view.setOnClickListener(new v(this, 0));
        }
        if (Features.isEnabled(Features.IS_CHINESE_DEVICE)) {
            getView(view, (int) R.id.no_auth_location_and_go_setting).setOnClickListener(new v(this, 1));
        }
        if (PocFeatures.isEnabled(PocFeatures.OpenNavigation) && (view2 = getView(view, (int) R.id.moreinfo_location_text_container)) != null) {
            view2.setOnClickListener(new v(this, 2));
        }
        ViewCompat.setAccessibilityDelegate(getView(view, (int) R.id.moreinfo_map_container), this.mAccessibilityDelegate);
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.HAS_LOCATION, new w(this, 0));
        this.mViewUpdaterMap.put(DetailsUpdateKey.NO_LOCATION_AUTH, new w(this, 1));
        this.mViewUpdaterMap.put(DetailsUpdateKey.ADDRESS, new w(this, 2));
        this.mViewUpdaterMap.put(DetailsUpdateKey.POI, new w(this, 3));
        this.mViewUpdaterMap.put(DetailsUpdateKey.MAP_SNAP_BITMAP, new w(this, 4));
        this.mViewUpdaterMap.put(DetailsUpdateKey.MAP_MARKER_BITMAP, new w(this, 5));
        this.mViewUpdaterMap.put(DetailsUpdateKey.NO_LOCATION, new x(0));
    }

    public boolean supportItem(MediaItem mediaItem) {
        return MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude());
    }

    private void openNavigation(Context context, MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.e(this.TAG, "openNavigation skip. null item");
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0"));
        new AppChooserBuilder(intent).setTitle(R.string.show_location_in).setCallback(new u(this, mediaItem, intent)).build(context).show();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$registerViewUpdater$3(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
    }
}
