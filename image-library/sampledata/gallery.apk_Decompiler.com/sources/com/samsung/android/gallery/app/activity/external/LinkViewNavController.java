package com.samsung.android.gallery.app.activity.external;

import A.a;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabFragment;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.remotegallery.RemoteClientUi;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LinkViewNavController extends ViewNavigatorController {
    private String mInitLocation;

    public LinkViewNavController(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    /* JADX WARNING: type inference failed for: r1v8, types: [com.samsung.android.gallery.module.thumbnail.logic.RemoteThumbnailLoader, java.lang.Object] */
    private boolean execRemoteGallery(String str) {
        String str2 = this.TAG;
        Log.i(str2, "execRemoteGallery : " + Logger.getEncodedString(str));
        String argValue = ArgumentsUtil.getArgValue(str, "min_version", (String) null);
        int i2 = UnsafeCast.toInt(argValue, 0);
        String argValue2 = ArgumentsUtil.getArgValue(str, "remoteIp", (String) null);
        String argValue3 = ArgumentsUtil.getArgValue(str, "pass", (String) null);
        if (TextUtils.isEmpty(argValue2) || !PocFeatures.isEnabled(PocFeatures.WifiGallery)) {
            return false;
        }
        if (i2 == 0 || i2 > 2024011800) {
            String str3 = this.TAG;
            Log.e(str3, "execRemoteGallery#min_version failed. min=" + argValue);
            Utils.showToast(getContext(), "Server version is high. need to update client app");
            return false;
        }
        if (!TextUtils.isEmpty(argValue3)) {
            RemoteClient.setServerInfo(argValue2, argValue3);
        }
        PocFeatures.WifiGalleryClient.setEnabled(true);
        ThumbnailLoader.getInstance().setRemoteThumbnailLoader(new Object());
        RemoteClientUi.start(argValue2, this.mBlackboard);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadLocation$4(Consumer consumer, double d, double d2, double d3, String str) {
        MediaItem loadLatestLocationItem = loadLatestLocationItem();
        if (loadLatestLocationItem != null) {
            consumer.accept(LocationManager.getInstance().buildJsonObject(loadLatestLocationItem, (String) null));
            return;
        }
        consumer.accept(LocationManager.getInstance().buildJsonObject(d, d2, Double.valueOf(d3), str, (Long) null, (String) null));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyAndFinish$3(Activity activity, String str) {
        Toast.makeText(activity, str, 0).show();
        activity.finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onNavigatorCreated$0(Bundle bundle, int i2, String str) {
        Cursor loadAlbumFilesCursor;
        int i7;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            loadAlbumFilesCursor = loadAlbumFilesCursor(bundle, i2);
            if (loadAlbumFilesCursor != null) {
                if (loadAlbumFilesCursor.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(loadAlbumFilesCursor, 0);
                    String appendArgsIfEmpty = ArgumentsUtil.appendArgsIfEmpty(str, "shortcut_album", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
                    if (!BucketUtils.isRecent(i2)) {
                        if (!BucketUtils.isFavourite(i2)) {
                            if (PreferenceFeatures.OneUi5x.MX_ALBUMS && i2 == 0 && (i7 = BundleWrapper.getInt(bundle, "mergedAlbumId", 0)) != 0) {
                                load.setAlbumID(i7);
                                load.setAlbumType(AlbumType.Merged);
                                load.setTag("merged-album-ids", BundleWrapper.getString(bundle, "ids", ""));
                                int count = load.getCount();
                                int count2 = loadAlbumFilesCursor.getCount();
                                for (int i8 = 1; i8 < count2; i8++) {
                                    count += MediaItemLoader.load(loadAlbumFilesCursor, i8).getCount();
                                }
                                load.setCount(count);
                            }
                            String str2 = this.TAG;
                            Log.d(str2, "onNavigatorCreated#album" + Logger.vt(appendArgsIfEmpty, Integer.valueOf(load.getAlbumID()), load.getAlbumType(), Integer.valueOf(load.getCount()), Long.valueOf(currentTimeMillis)));
                            this.mBlackboard.publish("data://albums/current", load);
                            this.mBlackboard.post("command://MoveURL", appendArgsIfEmpty);
                            loadAlbumFilesCursor.close();
                            return;
                        }
                    }
                    load.setAlbumID(i2);
                    load.setAlbumType(AlbumType.Virtual);
                    load.setDisplayName(AlbumTitleHelper.getAlbumTitle(i2));
                    String str22 = this.TAG;
                    Log.d(str22, "onNavigatorCreated#album" + Logger.vt(appendArgsIfEmpty, Integer.valueOf(load.getAlbumID()), load.getAlbumType(), Integer.valueOf(load.getCount()), Long.valueOf(currentTimeMillis)));
                    this.mBlackboard.publish("data://albums/current", load);
                    this.mBlackboard.post("command://MoveURL", appendArgsIfEmpty);
                    loadAlbumFilesCursor.close();
                    return;
                }
            }
            if (loadAlbumFilesCursor != null) {
                loadAlbumFilesCursor.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("onNavigatorCreated#album failed. e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        notifyAndFinish(AppResources.getString(R.string.no_such_item));
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onNavigatorCreated$1(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mBlackboard.publish("data://user/map/InitialLocation", jSONObject);
        }
        preloadDataCursor(str);
        this.mBlackboard.post("command://MoveURL", str);
    }

    private Cursor loadAlbumFilesCursor(Bundle bundle, int i2) {
        int i7;
        int loadSortBy;
        QueryParams.DbStorageType dbStorageType;
        QueryParams showHidden = new QueryParams(DbKey.ALBUMS).setShowHidden(true);
        showHidden.setMediaTypeFilter(bundle.getString("filterMediaType", (String) null));
        int i8 = BundleWrapper.getInt(bundle, "filterStorageType", 0);
        if (i8 > 0) {
            QueryParams.DbStorageType dbStorageType2 = QueryParams.DbStorageType.All;
            showHidden.setStorageTypes(new QueryParams.DbStorageType[]{dbStorageType2, QueryParams.DbStorageType.LocalOnly, QueryParams.DbStorageType.CloudOnly, dbStorageType2}[Math.min(3, i8)]);
        } else {
            if (BundleWrapper.getBoolean(bundle, "filterLocalOnly", false)) {
                dbStorageType = QueryParams.DbStorageType.LocalOnly;
            } else {
                dbStorageType = QueryParams.DbStorageType.All;
            }
            showHidden.setStorageTypes(dbStorageType);
        }
        if (BucketUtils.isRecent(i2)) {
            showHidden.setDbKey(DbKey.VIRTUAL_ALBUM_RECENT_ALBUM).setGroupTypes(GroupType.BURST).addGroupType(GroupType.SINGLE_TAKEN);
            if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                showHidden.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
            }
        } else if (BucketUtils.isFavourite(i2)) {
            showHidden.setDbKey(DbKey.VIRTUAL_ALBUM_FAVORITE_ALBUM).setGroupTypes(GroupType.BURST);
            if (PocFeatures.SUPPORT_LOCKED_ALBUM) {
                showHidden.excludeAlbumId(LockedAlbum.getInstance().getBucketList());
            }
            int loadSortBy2 = GalleryPreference.getInstance().loadSortBy(String.valueOf(BucketUtils.VirtualBucketHolder.favorite), 12);
            if (loadSortBy2 != 12) {
                showHidden.setSortBy(loadSortBy2);
            }
        } else {
            showHidden.setGroupTypes(GroupType.BURST).addGroupType(GroupType.SINGLE_TAKEN);
            String string = BundleWrapper.getString(bundle, "ids", (String) null);
            if (string != null) {
                if (!(!PreferenceFeatures.OneUi5x.MX_ALBUMS || (i7 = BundleWrapper.getInt(bundle, "mergedAlbumId", 0)) == 0 || (loadSortBy = GalleryPreference.getInstance().loadSortBy(String.valueOf(i7), 12)) == 12)) {
                    showHidden.setSortBy(loadSortBy);
                }
                showHidden.addAlbumIds(StringCompat.toIntArray(string, GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            } else {
                showHidden.addAlbumId(i2);
            }
        }
        return DbCompat.query(showHidden);
    }

    private MediaItem loadLatestLocationItem() {
        Cursor latestLocationData;
        try {
            latestLocationData = new LocationApi().getLatestLocationData(0);
            if (latestLocationData != null) {
                if (latestLocationData.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(latestLocationData);
                    latestLocationData.close();
                    return load;
                }
            }
            if (latestLocationData == null) {
                return null;
            }
            latestLocationData.close();
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadLatestLocationItem failed "), this.TAG);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void loadLocation(String str, Consumer<JSONObject> consumer) {
        double argValue = ArgumentsUtil.getArgValue(str, "latitude", (double) MapUtil.INVALID_LOCATION);
        double argValue2 = ArgumentsUtil.getArgValue(str, "longitude", (double) MapUtil.INVALID_LOCATION);
        double argValue3 = ArgumentsUtil.getArgValue(str, "mapZoomLevel", 3.0d);
        String argValue4 = ArgumentsUtil.getArgValue(str, "address", (String) null);
        if (argValue == MapUtil.INVALID_LOCATION || argValue2 == MapUtil.INVALID_LOCATION) {
            double d = argValue2;
            double d2 = argValue;
            SimpleThreadPool.getInstance().execute(new d(this, consumer, d2, d, argValue3, argValue4));
            return;
        }
        consumer.accept(LocationManager.getInstance().buildJsonObject(argValue, argValue2, Double.valueOf(argValue3), argValue4, (Long) null, (String) null));
    }

    private void notifyAndFinish(String str) {
        Activity activity = this.mActivityView.getActivity();
        if (activity != null) {
            ThreadUtil.runOnUiThread(new e(0, activity, str));
        }
    }

    public void onMoveUrlFailed(String str) {
        if (str.equals(this.mInitLocation)) {
            this.mInitLocation = null;
            if ("location://settings".equals(str)) {
                new StartSettingsCmd().execute((EventContext) this.mActivityView, PopoverUtils.Anchor.TOP_START);
                this.mBlackboard.post("command://request_suicide", (Object) null);
                return;
            }
            super.onNavigatorCreated();
        }
    }

    public void onNavigatorCreated() {
        LaunchIntent launchIntent = getLaunchIntent();
        Uri uriData = launchIntent.getUriData();
        String parseDeepLink = LocationKey.parseDeepLink(uriData);
        Log.i(this.TAG, "onNavigatorCreated", uriData);
        if (parseDeepLink != null) {
            this.mInitLocation = parseDeepLink;
            if (LocationKey.isAlbumPictures(parseDeepLink)) {
                if (!execRemoteGallery(parseDeepLink)) {
                    preloadDataCursor(parseDeepLink);
                    Bundle args = ArgumentsUtil.getArgs(parseDeepLink);
                    int i2 = BundleWrapper.getInt(args, "id", 0);
                    String string = BundleWrapper.getString(args, "ids", (String) null);
                    if (i2 == 0 && string == null) {
                        Log.e((CharSequence) this.TAG, "onNavigatorCreated#album failed with invalid", Integer.valueOf(i2));
                        notifyAndFinish(AppResources.getString(R.string.no_such_item));
                    } else if (!PocFeatures.SUPPORT_LOCKED_ALBUM || !LockedAlbum.getInstance().contains(i2)) {
                        SimpleThreadPool.getInstance().execute(new b(this, args, i2, parseDeepLink));
                    } else {
                        Log.e((CharSequence) this.TAG, "onNavigatorCreated#album block locked-album", Integer.valueOf(i2));
                        notifyAndFinish("Cannot enter locked-album through link");
                    }
                }
            } else if (LocationKey.isStoryHighlight(parseDeepLink) || LocationKey.isStoryPictures(parseDeepLink)) {
                preloadDataCursor(parseDeepLink);
                new StoriesViewNavigatorDelegate(launchIntent, this.mBlackboard).onHandle(parseDeepLink);
            } else if (LocationKey.isMapMatch(ArgumentsUtil.removeArgs(parseDeepLink))) {
                loadLocation(parseDeepLink, new c(this, parseDeepLink));
            } else {
                if (LocationKey.isFavoritePictures(parseDeepLink)) {
                    parseDeepLink = ArgumentsUtil.appendArgsIfEmpty(ArgumentsUtil.appendArgsIfEmpty(parseDeepLink, "with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE), "id", String.valueOf(BucketUtils.VirtualBucketHolder.favorite));
                }
                preloadDataCursor(parseDeepLink);
                this.mBlackboard.post("command://MoveURL", parseDeepLink);
            }
        } else {
            super.onNavigatorCreated();
        }
    }

    public boolean setMvpFragmentForContainer(String str) {
        if (!isExistFragment("BottomTabFragment")) {
            return setMvpFragment(new BottomTabFragment(), str, "BottomTabFragment");
        }
        return false;
    }
}
