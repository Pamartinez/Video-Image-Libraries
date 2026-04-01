package com.samsung.android.gallery.app.provider;

import A4.Q;
import S3.d;
import U9.b;
import X3.C0413a;
import android.app.slice.Slice;
import android.app.slice.SliceManager;
import android.app.slice.SliceProvider;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareProvider extends SliceProvider {
    /* access modifiers changed from: private */
    public static final String[] INSTANT_SUBSCRIBE_KEYS = {"data://on_location_moving", "command://CancelDialog"};
    private static final SliceSpec LIST_SLICE_SPEC = new SliceSpec("androidx.slice.LIST", 1);
    public static final String SHARE_SHEET_PACKAGE;
    private static final boolean SUPPORT_MOTION_PHOTO_VIEW_MODE = PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE;
    public static final boolean SUPPORT_SHARE_SHEET = SdkConfig.atLeast(SdkConfig.SEM.S);
    public static final ConcurrentHashMap<Uri, ArrayList<MediaItem>> sFileItemsMap = new ConcurrentHashMap<>();
    ArrayList<MediaItem> mItemList = new ArrayList<>();
    final ConcurrentHashMap<Long, MediaItem> mItemMap = new ConcurrentHashMap<>();

    static {
        String str;
        if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
            str = "com.android.intentresolver";
        } else {
            str = "android";
        }
        SHARE_SHEET_PACKAGE = str;
    }

    public ShareProvider() {
        super(new String[]{SHARE_SHEET_PACKAGE});
    }

    public static void clearExtendedShareList(Context context) {
        if (context != null) {
            sFileItemsMap.remove(getProviderUri(context));
            Log.d("ShareProvider", "clearExtendedShareList");
        }
    }

    private Bitmap createBitmap(Bitmap bitmap, MediaItem mediaItem) {
        int i2;
        if (bitmap == null) {
            return null;
        }
        BitmapOperator bitmapOperator = new BitmapOperator(bitmap);
        if (mediaItem.isVideo()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        return bitmapOperator.rotate(i2, mediaItem.getOrientationTag()).apply();
    }

    private Slice createSubSlice(Slice.Builder builder, Bitmap bitmap, MediaItem mediaItem, boolean z) {
        int i2;
        Slice.Builder builder2 = new Slice.Builder(builder);
        builder2.addHints(Arrays.asList(new String[]{"list_item", "activity"}));
        if (z) {
            Log.i("ShareProvider", "provider_add:", mediaItem.toString(), Logger.toString(bitmap));
        }
        builder2.addText(ContentUri.getUriString(mediaItem), (String) null, Collections.singletonList("uri_list"));
        builder2.addText("content://com.sec.android.gallery3d.provider.ShareProvider/thumb/" + mediaItem.getFileId(), (String) null, Collections.singletonList(BundleKey.THUMBNAIL_URI));
        if (bitmap != null) {
            builder2.addIcon(Icon.createWithBitmap(bitmap), (String) null, Collections.singletonList("image_list"));
        }
        if (mediaItem.getGroupType() > 0 && mediaItem.getCount() > 1) {
            builder2.addInt(mediaItem.getGroupType(), (String) null, Collections.singletonList(com.samsung.android.sdk.mobileservice.social.group.BundleKey.GROUP_TYPE));
            builder2.addLong(mediaItem.getGroupMediaId(), (String) null, Collections.singletonList(BundleKey.GROUP_ID));
            builder2.addInt(mediaItem.getBucketID(), (String) null, Collections.singletonList("bucket_id"));
        }
        builder2.addInt(mediaItem.isFavourite() ? 1 : 0, (String) null, Collections.singletonList("isFavourite"));
        builder2.addInt(mediaItem.getSefFileType(), (String) null, Collections.singletonList("sefFileType"));
        if (mediaItem.isVideo()) {
            i2 = 3;
        } else if (mediaItem.isImage()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        builder2.addInt(i2, (String) null, Collections.singletonList(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE));
        if (mediaItem.isVideo()) {
            String shotMode = mediaItem.getShotMode();
            if (shotMode != null) {
                builder2.addInt("super_slow_mo".equals(shotMode) ? 1 : 0, (String) null, Collections.singletonList("isSSM"));
                builder2.addInt("slow_motion".equals(shotMode) ? 1 : 0, (String) null, Collections.singletonList("isSM"));
            }
            builder2.addInt(mediaItem.isHdr10Video() ? 1 : 0, (String) null, Collections.singletonList("isHdr10"));
            builder2.addInt(mediaItem.isHlgVideo() ? 1 : 0, (String) null, Collections.singletonList("isHlg"));
            builder2.addInt(mediaItem.getRecordingMode(), (String) null, Collections.singletonList("recordingMode"));
            builder2.addInt(mediaItem.getRecordingType(), (String) null, Collections.singletonList("recordingType"));
            builder2.addInt(mediaItem.getFileDuration(), (String) null, Collections.singletonList("duration"));
            builder2.addInt(mediaItem.isFHD() ? 1 : 0, (String) null, Collections.singletonList("isFHD"));
            builder2.addInt(mediaItem.is4K() ? 1 : 0, (String) null, Collections.singletonList("needVideoConverting"));
        }
        builder2.addInt(mediaItem.isHeif() ? 1 : 0, (String) null, Collections.singletonList("isHeif"));
        String location = mediaItem.getLocation();
        if (!TextUtils.isEmpty(location)) {
            builder2.addText(location, (String) null, Collections.singletonList("location"));
        } else if (!(mediaItem.getLatitude() == MapUtil.INVALID_LOCATION && mediaItem.getLongitude() == MapUtil.INVALID_LOCATION)) {
            builder2.addText("unknown", (String) null, Collections.singletonList("location"));
        }
        builder2.addLong(mediaItem.getFileSize(), (String) null, Collections.singletonList("size"));
        builder2.addLong(mediaItem.getDateTaken(), (String) null, Collections.singletonList("dateTime"));
        builder2.addInt(mediaItem.isCloudOnly() ? 1 : 0, (String) null, Collections.singletonList("cloudOnly"));
        builder2.addInt(mediaItem.getOrientation(), (String) null, Collections.singletonList("orientation"));
        builder2.addInt(mediaItem.getOrientationTag(), (String) null, Collections.singletonList("orientationTag"));
        builder2.addInt(mediaItem.getWidth(), (String) null, Collections.singletonList("width"));
        builder2.addInt(mediaItem.getHeight(), (String) null, Collections.singletonList("height"));
        builder2.addText(mediaItem.getMimeType(), (String) null, Collections.singletonList(FileApiContract.Parameter.MIME_TYPE));
        builder2.addText(mediaItem.getSefFileTypes(), (String) null, Collections.singletonList("sefFileTypes"));
        builder2.addInt(mediaItem.getSefFileSubType(), (String) null, Collections.singletonList("sefFileSubType"));
        if (mediaItem.hasSefFileTypes(SefInfo.COLOR_DISPLAY_P3.keyCode)) {
            builder2.addInt(1, (String) null, Collections.singletonList("isP3"));
        }
        if (SUPPORT_MOTION_PHOTO_VIEW_MODE && mediaItem.isMotionPhoto()) {
            builder2.addText(((MotionPhotoViewMode) Optional.ofNullable(DetailsData.of(mediaItem).motionPhotoViewMode).orElse(MotionPhotoViewMode.ON)).name().toLowerCase(), (String) null, Collections.singletonList("motion_photo_view_mode"));
        }
        return builder2.build();
    }

    public static Uri getProviderUri(Context context) {
        return Uri.parse("content://com.sec.android.gallery3d.provider.ShareProvider/data/" + context.toString().hashCode());
    }

    public static boolean hasExtendedList(Uri uri) {
        return sFileItemsMap.containsKey(uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$2(Uri uri, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Log.d("ShareProvider", "onBitmapLoaded : ", uri, bitmap);
        if (bitmap == null) {
            mediaItem.setBroken(true);
        }
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadMediaItem$3(MediaItem mediaItem) {
        this.mItemMap.put(Long.valueOf(mediaItem.getFileId()), mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindSlice$1(Slice.Builder builder, boolean z, MediaItem mediaItem) {
        builder.addSubSlice(createSubSlice(builder, ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND), mediaItem, z), (String) null);
    }

    private void loadBitmap(Uri uri, MediaItem mediaItem) {
        ThumbnailLoader.getInstance().loadThumbnail(mediaItem, ThumbKind.MEDIUM_KIND, new Q((Object) this, (Object) uri, (Object) mediaItem, 12));
    }

    private void loadMediaItem(Uri uri) {
        this.mItemMap.clear();
        ArrayList<MediaItem> arrayList = sFileItemsMap.get(uri);
        this.mItemList = arrayList;
        if (arrayList != null) {
            arrayList.forEach(new b(11, this));
        }
        Log.d("ShareProvider", "loadMediaItem", Integer.valueOf(this.mItemMap.size()));
    }

    public static void prepareExtendedShareList(Context context, Blackboard blackboard, MediaItem[] mediaItemArr, Runnable runnable) {
        if (SUPPORT_SHARE_SHEET && context != null && blackboard != null) {
            Log.d("ShareProvider", "prepareExtendedShareList ", mediaItemArr != null ? Integer.valueOf(mediaItemArr.length) : "null");
            if (mediaItemArr != null) {
                long currentTimeMillis = System.currentTimeMillis();
                Uri providerUri = getProviderUri(context);
                ArrayList arrayList = new ArrayList();
                Stream.of(mediaItemArr).filter(new d(16)).forEach(new B8.d(arrayList, 23));
                sFileItemsMap.put(providerUri, arrayList);
                Integer valueOf = Integer.valueOf(arrayList.size());
                Log.d("ShareProvider", "prepareExtendedShareList done", valueOf, (System.currentTimeMillis() - currentTimeMillis) + "msec");
                subscribeInstantForClear(context, blackboard, runnable);
            }
        }
    }

    public static void subscribeInstantForClear(final Context context, final Blackboard blackboard, final Runnable runnable) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AnonymousClass1 r1 = new SubscriberListener() {
            public void onNotify(Object obj, Bundle bundle) {
                if (atomicBoolean.getAndSet(false)) {
                    ShareProvider.clearExtendedShareList(context);
                    for (String unsubscribe : ShareProvider.INSTANT_SUBSCRIBE_KEYS) {
                        blackboard.unsubscribe(unsubscribe, this);
                    }
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }
        };
        for (String subscribeOnUi : INSTANT_SUBSCRIBE_KEYS) {
            blackboard.subscribeOnUi(subscribeOnUi, r1);
        }
    }

    public Slice onBindSlice(Uri uri, Set<SliceSpec> set) {
        MediaItem mediaItem;
        Bitmap bitmap;
        if (SHARE_SHEET_PACKAGE.equals(getCallingPackage())) {
            if (SUPPORT_SHARE_SHEET) {
                String uri2 = uri.toString();
                boolean booleanQueryParameter = uri.getBooleanQueryParameter("verbose", false);
                if (uri2.startsWith("content://com.sec.android.gallery3d.provider.ShareProvider/data/")) {
                    Log.d("ShareProvider", "onBindSlice", uri);
                    loadMediaItem(uri);
                    Slice.Builder builder = new Slice.Builder(uri, LIST_SLICE_SPEC);
                    ArrayList<MediaItem> arrayList = this.mItemList;
                    if (arrayList != null) {
                        arrayList.forEach(new C0413a(this, builder, booleanQueryParameter, 0));
                    }
                    return builder.build();
                } else if (uri2.startsWith("content://com.sec.android.gallery3d.provider.ShareProvider/thumb/")) {
                    long j2 = UnsafeCast.toLong(uri.getLastPathSegment());
                    if (j2 != -1) {
                        mediaItem = this.mItemMap.get(Long.valueOf(j2));
                        if (mediaItem == null) {
                            bitmap = null;
                        } else if (mediaItem.isBroken()) {
                            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
                        } else {
                            bitmap = ThumbnailLoader.getInstance().getMemCache(mediaItem, ThumbKind.MEDIUM_KIND);
                            if (bitmap == null) {
                                Log.d("ShareProvider", "bmp not ready", uri);
                                loadBitmap(uri, mediaItem);
                            }
                        }
                    } else {
                        mediaItem = null;
                        bitmap = null;
                    }
                    if (mediaItem != null) {
                        Slice.Builder builder2 = new Slice.Builder(uri, LIST_SLICE_SPEC);
                        builder2.addSubSlice(createSubSlice(builder2, createBitmap(bitmap, mediaItem), mediaItem, false), (String) null);
                        return builder2.build();
                    }
                }
            }
            return null;
        }
        throw new SecurityException("not allowed for " + getCallingPackage());
    }

    public boolean onCreate() {
        try {
            ((SliceManager) getContext().getSystemService(SliceManager.class)).grantSlicePermission(SHARE_SHEET_PACKAGE, Uri.parse("content://com.sec.android.gallery3d.provider.ShareProvider"));
            return false;
        } catch (NullPointerException | SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void prepareExtendedShareList(Context context, Blackboard blackboard, Function<Integer, MediaItem> function, int i2, int i7, int i8, Runnable runnable) {
        int[] range;
        if (SUPPORT_SHARE_SHEET && context != null && blackboard != null && (range = Utils.getRange(i7, i8, i2, 500)) != null) {
            ArrayList arrayList = new ArrayList();
            long currentTimeMillis = System.currentTimeMillis();
            for (int i10 = range[0]; i10 <= range[1]; i10++) {
                MediaItem apply = function.apply(Integer.valueOf(i10));
                if (apply == null) {
                    Log.e((CharSequence) "ShareProvider", "mediaItem load fail : ", Integer.valueOf(i10));
                } else if (MediaItemUtil.isSharableItem(apply, false)) {
                    arrayList.add(apply);
                } else {
                    Log.d("ShareProvider", "skip item for extended list", apply, Boolean.valueOf(apply.isDrm()), Boolean.valueOf(apply.isBroken()), Boolean.valueOf(apply.isPostProcessing()));
                }
            }
            sFileItemsMap.put(getProviderUri(context), arrayList);
            Integer valueOf = Integer.valueOf(range[0]);
            Integer valueOf2 = Integer.valueOf(range[1]);
            Log.d("ShareProvider", "prepareExtendedShareList done", valueOf, valueOf2, (System.currentTimeMillis() - currentTimeMillis) + "msec");
            subscribeInstantForClear(context, blackboard, runnable);
        }
    }
}
