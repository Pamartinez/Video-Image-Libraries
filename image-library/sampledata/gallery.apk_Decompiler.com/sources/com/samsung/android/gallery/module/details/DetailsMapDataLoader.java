package com.samsung.android.gallery.module.details;

import B8.e;
import O3.b;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import androidx.window.embedding.c;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsMapDataLoader extends DetailsDataLoader {
    private static boolean hasAuthority() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth) || Features.isEnabled(Features.SUPPORT_PLACE_GDPR)) {
            return true;
        }
        return false;
    }

    private static boolean hasValidLocation(MediaItem mediaItem) {
        if (mediaItem == null || !MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude())) {
            return false;
        }
        return true;
    }

    private static boolean isLoadable(MediaItem mediaItem) {
        if (!DetailsUtil.supportDbLoad(mediaItem) || Features.isEnabled(Features.IS_UPSM) || !Features.isEnabled(Features.SUPPORT_LOCATION)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadAddress$0(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback, AddressCompat addressCompat) {
        DetailsData.of(mediaItem).setAddress(addressCompat.getAddress(), addressCompat.addressText);
        DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.ADDRESS);
    }

    public static void loadAddress(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        if (mediaItem != null && support(mediaItem)) {
            if (DetailsData.of(mediaItem).getAddress() != null) {
                DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.ADDRESS);
            } else {
                LocationManager.getInstance().loadLocation((FileItemInterface) mediaItem, (Consumer<AddressCompat>) new a(mediaItem, detailsDataLoadCallback));
            }
        }
    }

    public static void loadMapInfos(MediaItem mediaItem, Blackboard blackboard, Size size, Size size2, DetailsDataLoadCallback detailsDataLoadCallback) {
        loadAddress(mediaItem, detailsDataLoadCallback);
        loadPoi(mediaItem, detailsDataLoadCallback);
        loadMapSnap(mediaItem, blackboard, size, size2, detailsDataLoadCallback);
        loadMapMarkerBitmap(mediaItem, detailsDataLoadCallback);
    }

    public static void loadMapMarkerBitmap(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        if (support(mediaItem)) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Objects.requireNonNull(mediaItem);
            instance.getOrLoad(mediaItem, thumbKind, new e(mediaItem, 1), new b(20, detailsDataLoadCallback, mediaItem));
        }
    }

    public static void loadMapSnap(MediaItem mediaItem, Blackboard blackboard, Size size, Size size2, DetailsDataLoadCallback detailsDataLoadCallback) {
        ThreadUtil.assertBgThread("loadMapSnap");
        if (support(mediaItem)) {
            if (size != null) {
                if (size.getHeight() * size.getWidth() > 0) {
                    double latitude = mediaItem.getLatitude();
                    double longitude = mediaItem.getLongitude();
                    int width = size.getWidth();
                    int height = size.getHeight();
                    String diskCacheKey = MapUtil.getDiskCacheKey(latitude, longitude, width, height);
                    Bitmap diskCache = BitmapCache.getDiskCache(7, diskCacheKey.getBytes());
                    if (diskCache == null) {
                        Log.d("DetailsMapDataLoader", "loadMapSnap", Long.valueOf(mediaItem.getFileId()), Integer.valueOf(width), Integer.valueOf(height));
                        String build = new UriBuilder(CommandKey.DATA_REQUEST("data://MapSnapShot")).appendArg("latitude", latitude).appendArg("longitude", longitude).appendArg("width", width).appendArg("height", height).build();
                        final String[] strArr = {"data://MapSnapShot", "command://MoveCMD", "data://MapSnapShot_failure"};
                        final String str = diskCacheKey;
                        final MediaItem mediaItem2 = mediaItem;
                        final Blackboard blackboard2 = blackboard;
                        final DetailsDataLoadCallback detailsDataLoadCallback2 = detailsDataLoadCallback;
                        AnonymousClass1 r0 = new SubscriberListener() {
                            public void onNotify(Object obj, Bundle bundle) {
                                Bitmap bitmap;
                                for (String unsubscribe : strArr) {
                                    blackboard2.unsubscribe(unsubscribe, this);
                                }
                                String string = bundle.getString("_SUBSCRIBE_KEY");
                                if ("data://MapSnapShot".equals(string)) {
                                    bitmap = (Bitmap) obj;
                                } else {
                                    bitmap = null;
                                }
                                if (bitmap == null) {
                                    Log.d("DetailsMapDataLoader", "failed to load map snapshot", Long.valueOf(mediaItem2.getFileId()), string);
                                    return;
                                }
                                Log.d("DetailsMapDataLoader", "onMapSnapNotified", Long.valueOf(mediaItem2.getFileId()), Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), Double.valueOf(BundleWrapper.getDouble(bundle, "latitude")), Double.valueOf(BundleWrapper.getDouble(bundle, "longitude")));
                                DetailsDataLoader.notify(detailsDataLoadCallback2, mediaItem2, new DetailsLoadResult(DetailsUpdateKey.MAP_SNAP_BITMAP, bitmap, str));
                                BitmapCache.putDiskCache(7, str.getBytes(), bitmap);
                            }
                        };
                        for (int i2 = 0; i2 < 3; i2++) {
                            blackboard.subscribeOnUi(strArr[i2], r0);
                        }
                        ThreadUtil.postOnUiThread(new c(21, blackboard, build));
                        return;
                    }
                    Log.d("DetailsMapDataLoader", "loadMapSnap#cache", Long.valueOf(mediaItem.getFileId()), Integer.valueOf(width), Integer.valueOf(height));
                    DetailsDataLoadCallback detailsDataLoadCallback3 = detailsDataLoadCallback;
                    DetailsDataLoader.notify(detailsDataLoadCallback3, mediaItem, new DetailsLoadResult(DetailsUpdateKey.MAP_SNAP_BITMAP, diskCache, diskCacheKey));
                    return;
                }
            }
            Log.w("DetailsMapDataLoader", "loadMapSnap failed because of frameSize" + Logger.v(mediaItem, size));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[SYNTHETIC, Splitter:B:26:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadPoi(com.samsung.android.gallery.module.data.MediaItem r5, com.samsung.android.gallery.module.details.DetailsDataLoadCallback r6) {
        /*
            java.lang.String r0 = "loadPoi"
            com.samsung.android.gallery.support.utils.ThreadUtil.assertBgThread(r0)
            boolean r0 = support(r5)
            if (r0 == 0) goto L_0x009e
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_POI
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 != 0) goto L_0x0015
            goto L_0x009e
        L_0x0015:
            com.samsung.android.gallery.support.utils.PreferenceFeatures r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.LocationAuth
            boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r0)
            if (r0 != 0) goto L_0x0035
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            java.lang.String r0 = r0.getPoi()
            if (r0 == 0) goto L_0x009e
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            r1 = 0
            r0.setPoi(r1)
            com.samsung.android.gallery.module.details.DetailsUpdateKey r0 = com.samsung.android.gallery.module.details.DetailsUpdateKey.POI
            com.samsung.android.gallery.module.details.DetailsDataLoader.notify((com.samsung.android.gallery.module.details.DetailsDataLoadCallback) r6, (com.samsung.android.gallery.module.data.MediaItem) r5, (com.samsung.android.gallery.module.details.DetailsUpdateKey) r0)
            return
        L_0x0035:
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            java.lang.String r0 = r0.getPoi()
            if (r0 == 0) goto L_0x0045
            com.samsung.android.gallery.module.details.DetailsUpdateKey r0 = com.samsung.android.gallery.module.details.DetailsUpdateKey.POI
            com.samsung.android.gallery.module.details.DetailsDataLoader.notify((com.samsung.android.gallery.module.details.DetailsDataLoadCallback) r6, (com.samsung.android.gallery.module.data.MediaItem) r5, (com.samsung.android.gallery.module.details.DetailsUpdateKey) r0)
            return
        L_0x0045:
            com.samsung.android.gallery.database.dal.mp.helper.LocationApi r0 = new com.samsung.android.gallery.database.dal.mp.helper.LocationApi     // Catch:{ Exception -> 0x007a }
            r0.<init>()     // Catch:{ Exception -> 0x007a }
            double r1 = r5.getLatitude()     // Catch:{ Exception -> 0x007a }
            double r3 = r5.getLongitude()     // Catch:{ Exception -> 0x007a }
            android.database.Cursor r0 = r0.getPoiTableCursor(r1, r3)     // Catch:{ Exception -> 0x007a }
            if (r0 == 0) goto L_0x0066
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0066
            r1 = 0
            java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r6 = move-exception
            goto L_0x007c
        L_0x0066:
            java.lang.String r1 = ""
        L_0x0068:
            com.samsung.android.gallery.module.data.DetailsData r2 = com.samsung.android.gallery.module.data.DetailsData.of(r5)     // Catch:{ all -> 0x0064 }
            r2.setPoi(r1)     // Catch:{ all -> 0x0064 }
            com.samsung.android.gallery.module.details.DetailsUpdateKey r1 = com.samsung.android.gallery.module.details.DetailsUpdateKey.POI     // Catch:{ all -> 0x0064 }
            com.samsung.android.gallery.module.details.DetailsDataLoader.notify((com.samsung.android.gallery.module.details.DetailsDataLoadCallback) r6, (com.samsung.android.gallery.module.data.MediaItem) r5, (com.samsung.android.gallery.module.details.DetailsUpdateKey) r1)     // Catch:{ all -> 0x0064 }
            if (r0 == 0) goto L_0x009e
            r0.close()     // Catch:{ Exception -> 0x007a }
            return
        L_0x007a:
            r6 = move-exception
            goto L_0x0087
        L_0x007c:
            if (r0 == 0) goto L_0x0086
            r0.close()     // Catch:{ all -> 0x0082 }
            goto L_0x0086
        L_0x0082:
            r0 = move-exception
            r6.addSuppressed(r0)     // Catch:{ Exception -> 0x007a }
        L_0x0086:
            throw r6     // Catch:{ Exception -> 0x007a }
        L_0x0087:
            long r0 = r5.getFileId()
            java.lang.Long r5 = java.lang.Long.valueOf(r0)
            java.lang.String r6 = r6.getMessage()
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
            java.lang.String r6 = "DetailsMapDataLoader"
            java.lang.String r0 = "error"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r6, (java.lang.String) r0, (java.lang.Object[]) r5)
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.details.DetailsMapDataLoader.loadPoi(com.samsung.android.gallery.module.data.MediaItem, com.samsung.android.gallery.module.details.DetailsDataLoadCallback):void");
    }

    public static void preLoadMapInfos(MediaItem mediaItem, DetailsDataLoadCallback detailsDataLoadCallback) {
        if (!isLoadable(mediaItem) || !hasValidLocation(mediaItem)) {
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.NO_LOCATION);
        } else if (!hasAuthority()) {
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.NO_LOCATION_AUTH);
        } else {
            DetailsDataLoader.notify(detailsDataLoadCallback, mediaItem, DetailsUpdateKey.HAS_LOCATION);
        }
    }

    private static boolean support(MediaItem mediaItem) {
        if (!isLoadable(mediaItem) || !hasValidLocation(mediaItem) || !hasAuthority()) {
            return false;
        }
        return true;
    }
}
