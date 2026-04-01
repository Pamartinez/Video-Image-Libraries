package com.samsung.android.gallery.app.provider;

import A.a;
import N2.j;
import U5.b;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.AlbumGroupView;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.camera.PostProcessingHelper;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.lottie.service.RecapWorker;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.share.StoryQuickShare;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SignatureChecker;
import com.samsung.android.gallery.support.utils.SystemEnvironment;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.VoldApi;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalProvider2 extends LocalProvider {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestType {
        DELETE_ID,
        DELETE_IDS,
        FILE_ID,
        CLOUD_SERVER_ID,
        ABS_FILE_PATH,
        MEDIA_TYPE,
        MIME_TYPE,
        DATE_TAKEN
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ResponseType {
        TRASH_INTERNAL_CAPACITY,
        TRASH_SDCARD_CAPACITY,
        TRASH_INTENT_ACTION,
        TRASH_ON,
        DELETE_SUCCESS_COUNT,
        DELETE_FAIL_COUNT,
        DOWNLOAD_URI,
        RESULT_OF_EMPTY_TRASH,
        TRASH_ITEM_COUNT,
        TRASH_MIGRATION_COMPLETED
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TransferThread extends Thread {
        private final byte[] buffer;
        private final ParcelFileDescriptor[] pipe;

        public TransferThread(byte[] bArr) {
            this.buffer = bArr;
            if (bArr == null || bArr.length == 0) {
                throw new IllegalArgumentException("buffer is not available");
            }
            this.pipe = ParcelFileDescriptor.createPipe();
        }

        public ParcelFileDescriptor prepare() {
            super.start();
            return this.pipe[0];
        }

        public void run() {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
            byte[] bArr = new byte[16384];
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.buffer);
                try {
                    autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(this.pipe[1]);
                    while (true) {
                        int read = byteArrayInputStream.read(bArr);
                        if (read < 0) {
                            break;
                        }
                        autoCloseOutputStream.write(bArr, 0, read);
                    }
                    autoCloseOutputStream.flush();
                    autoCloseOutputStream.close();
                    byteArrayInputStream.close();
                    Log.d("TransferThread", "finished");
                    return;
                } catch (Throwable th) {
                    byteArrayInputStream.close();
                    throw th;
                }
                throw th;
            } catch (IOException e) {
                Log.e("TransferThread", "transferring failed. e=" + e);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UriMatcherHolder2 {
        static final UriMatcher URI_MATCHER = new UriMatcher(-1) {
            {
                addURI("com.sec.android.gallery3d.provider2", "album_display_info_table", 7);
                addURI("com.sec.android.gallery3d.provider2", "album", 6);
                addURI("com.sec.android.gallery3d.provider2", "mxalbum", 15);
                addURI("com.sec.android.gallery3d.provider2", "album_cover_history", 19);
                addURI("com.sec.android.gallery3d.provider2", "search_history", 1);
                addURI("com.sec.android.gallery3d.provider2", "trash", 3);
                addURI("com.sec.android.gallery3d.provider2", "share", 2);
                addURI("com.sec.android.gallery3d.provider2", "unlimited_move_list", 4);
                addURI("com.sec.android.gallery3d.provider2", "unlimited_share_list", 5);
                addURI("com.sec.android.gallery3d.provider2", "log", 8);
                addURI("com.sec.android.gallery3d.provider2", "suggested", 9);
                addURI("com.sec.android.gallery3d.provider2", "recover", 21);
                addURI("com.sec.android.gallery3d.provider2", "my_query", 22);
                addURI("com.sec.android.gallery3d.provider2", "ashmem/#", 10);
                addURI("com.sec.android.gallery3d.provider2", "local/*", 13);
                addURI("com.sec.android.gallery3d.provider2", "operation_history", 11);
                addURI("com.sec.android.gallery3d.provider2", "cache", 14);
                addURI("com.sec.android.gallery3d.provider2", "recent_album_item_list", 16);
                addURI("com.sec.android.gallery3d.provider2", "favorite_album_item_list", 17);
                addURI("com.sec.android.gallery3d.provider2", "share/album", 18);
                addURI("com.sec.android.gallery3d.provider2", "album_group", 20);
                addURI("com.sec.android.gallery3d.provider2", "private_album", 23);
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VoldApiPackageHolder {
        static final HashSet<String> set = new HashSet<>(List.of("com.sec.android.gallery3d", "com.sec.android.easyMover"));

        public static boolean support(Context context, String str) {
            if (str == null || !set.contains(str) || !SignatureChecker.support(context, str)) {
                return false;
            }
            return true;
        }
    }

    private Bundle changeOrientation(Context context, Bundle bundle) {
        String str;
        if (!ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME.equals(getCallingPackage())) {
            throw new SecurityException("changeOrientation not permitted. " + getCallingPackage());
        } else if (bundle != null) {
            String string = bundle.getString("data", (String) null);
            int i2 = bundle.getInt("orientation", -1);
            if (TextUtils.isEmpty(string) || i2 < 0) {
                throw new IllegalArgumentException("changeOrientation failed. invalid argument");
            }
            Bundle bundle2 = new Bundle();
            if (FileUtils.exists(string)) {
                boolean changeOrientation = ExifUtils.changeOrientation(string, i2);
                bundle2.putBoolean("result", changeOrientation);
                if (changeOrientation) {
                    str = "success";
                } else {
                    str = "unknown";
                }
                bundle2.putString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, str);
                return bundle2;
            }
            bundle2.putBoolean("result", false);
            bundle2.putString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, "invalid file");
            return bundle2;
        } else {
            throw new IllegalArgumentException("changeOrientation failed. invalid extras={null}");
        }
    }

    private Bundle createAlbumStory(Context context, String str, Bundle bundle) {
        int i2;
        Bundle bundle2 = bundle;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList parcelableArrayList = bundle2.getParcelableArrayList("items");
        String str2 = "failure";
        if (parcelableArrayList == null || parcelableArrayList.size() == 0) {
            Log.e(this.TAG, "create story failed. empty items");
            Bundle bundle3 = new Bundle();
            bundle3.putString("result", str2);
            return bundle3;
        }
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("extras");
        String string = bundle2.getString("title");
        String string2 = bundle2.getString("theme");
        StoryApi storyApi = DbCompat.storyApi();
        int createStory = createStory(parcelableArrayList, string, StoryType.MANUAL);
        boolean z = false;
        if (createStory >= 0 && string2 != null) {
            z = storyApi.updateStoryThemeInfo(createStory, StoryQuickShare.unmarshallTheme(string2), 0);
        }
        String str3 = this.TAG;
        StringBuilder sb2 = new StringBuilder("create story album");
        int i7 = createStory;
        Integer valueOf = Integer.valueOf(i7);
        Integer valueOf2 = Integer.valueOf(parcelableArrayList.size());
        if (stringArrayList != null) {
            i2 = stringArrayList.size();
        } else {
            i2 = -1;
        }
        Integer valueOf3 = Integer.valueOf(i2);
        Boolean valueOf4 = Boolean.valueOf(z);
        Long valueOf5 = Long.valueOf(currentTimeMillis);
        Integer num = valueOf2;
        Boolean bool = valueOf4;
        Integer num2 = num;
        int i8 = i7;
        sb2.append(Logger.vt(valueOf, string, num2, valueOf3, bool, string2, valueOf5));
        Log.d(str3, sb2.toString());
        Bundle bundle4 = new Bundle();
        if (i8 >= 0) {
            str2 = "success";
        }
        bundle4.putString("result", str2);
        bundle4.putInt("id", i8);
        bundle4.putString("title", string);
        bundle4.putInt("count", parcelableArrayList.size());
        return bundle4;
    }

    private Bundle deleteItem(Context context, Bundle bundle, boolean z) {
        if (DeviceConfig.DEBUG_BINARY) {
            throw new SecurityException("multiple binding process is not allowed. " + getCallingPackage());
        } else if (bundle != null) {
            long j2 = bundle.getLong(RequestType.DELETE_ID.name(), 0);
            if (j2 != 0) {
                return deleteItemInternal(context, new long[]{j2}, z);
            }
            throw new IllegalArgumentException("delete item failed, invalid id={0}");
        } else {
            throw new IllegalArgumentException("delete item failed, invalid extras={null}");
        }
    }

    private Bundle deleteItemInternal(Context context, long[] jArr, boolean z) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(context);
        deleteHelper.deleteItems(jArr, z);
        deleteHelper.done();
        deleteHelper.dump(getTrashLogType(), getTrashLogData(jArr));
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("delete item");
        String callingPackage = getCallingPackage();
        if (jArr != null) {
            i2 = jArr.length;
        } else {
            i2 = -1;
        }
        sb2.append(Logger.vt(callingPackage, Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(deleteHelper.getSucceedCount()), Integer.valueOf(deleteHelper.getImageFailedCount()), Long.valueOf(currentTimeMillis)));
        Log.d(str, sb2.toString());
        Bundle bundle = new Bundle();
        bundle.putInt(ResponseType.DELETE_SUCCESS_COUNT.name(), deleteHelper.getSucceedCount());
        bundle.putInt(ResponseType.DELETE_FAIL_COUNT.name(), deleteHelper.getVideoFailedCount() + deleteHelper.getImageFailedCount());
        return bundle;
    }

    private Bundle deleteItems(Context context, Bundle bundle, boolean z) {
        boolean z3;
        if (DeviceConfig.DEBUG_BINARY) {
            throw new SecurityException("multiple binding process is not allowed. " + getCallingPackage());
        } else if (bundle != null) {
            long[] longArray = bundle.getLongArray(RequestType.DELETE_IDS.name());
            if (longArray != null && longArray.length != 0) {
                return deleteItemInternal(context, longArray, z);
            }
            StringBuilder sb2 = new StringBuilder("delete items failed, invalid ids={null:");
            if (longArray == null) {
                z3 = true;
            } else {
                z3 = false;
            }
            throw new IllegalArgumentException(j.h(sb2, z3, "}"));
        } else {
            throw new IllegalArgumentException("delete items failed, invalid extras={null}");
        }
    }

    private Bundle downloadCloudFile(Context context, Bundle bundle) {
        Uri uri;
        if (bundle != null) {
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = bundle.getLong(RequestType.FILE_ID.name(), 0);
            String string = bundle.getString(RequestType.CLOUD_SERVER_ID.name(), "");
            String string2 = bundle.getString(RequestType.ABS_FILE_PATH.name(), "");
            int i2 = bundle.getInt(RequestType.MEDIA_TYPE.name(), 1);
            String string3 = bundle.getString(RequestType.MIME_TYPE.name(), "");
            ArrayList<Uri> download = SamsungCloudCompat.download(context, DownloadParams.builder().setFileId(j2).setCloudServerId(string).setTargetPath(string2).setMediaType(i2).setMimeType(string3).setDateTaken(bundle.getLong(RequestType.DATE_TAKEN.name(), 0)).build());
            if (download == null || download.isEmpty()) {
                uri = null;
            } else {
                uri = download.get(0);
            }
            Bundle bundle2 = new Bundle();
            if (uri != null) {
                Optional.ofNullable(getCaller()).ifPresent(new b(7, context, uri));
                bundle2.putParcelable(ResponseType.DOWNLOAD_URI.name(), uri);
            }
            String str = this.TAG;
            a.A(new Object[]{Long.valueOf(j2), Integer.valueOf(i2), string3, uri, Long.valueOf(currentTimeMillis)}, new StringBuilder("downloadCloudFile"), str);
            return bundle2;
        }
        throw new IllegalArgumentException("downloadCloudFile failed, invalid extras={null}");
    }

    private Bundle emptyTrashItems(Context context) {
        String callingPackage = getCallingPackage();
        if ("com.sec.android.app.myfiles".equals(callingPackage)) {
            long currentTimeMillis = System.currentTimeMillis();
            TrashHelperFactory.getExternalHelper(context).clearTrash();
            String str = this.TAG;
            Log.majorEvent(str, "empty trash" + Logger.vt(callingPackage, Long.valueOf(currentTimeMillis)));
            Bundle bundle = new Bundle();
            bundle.putBoolean(ResponseType.RESULT_OF_EMPTY_TRASH.name(), true);
            return bundle;
        }
        throw new SecurityException(C0212a.l("empty trash not permitted. ", callingPackage));
    }

    private Bundle getGppmDonationEnabled(Context context) {
        boolean isDonationEnabled = GppmHelper.isDonationEnabled();
        Log.d(this.TAG, "get gppm donation", Boolean.valueOf(isDonationEnabled));
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaApiContract.Parameter.ENABLED, isDonationEnabled);
        return bundle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0099 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b1 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00e4 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e7 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0100 A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0129 A[SYNTHETIC, Splitter:B:44:0x0129] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x012c A[Catch:{ Exception -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0135 A[Catch:{ Exception -> 0x0019 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Bundle getTrashInfo(android.content.Context r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r0 = ","
            java.lang.String r2 = "TrashInfo{"
            com.samsung.android.gallery.support.utils.PreferenceFeatures r3 = com.samsung.android.gallery.support.utils.PreferenceFeatures.UseTrash     // Catch:{ Exception -> 0x0019 }
            boolean r3 = com.samsung.android.gallery.support.utils.PreferenceFeatures.isEnabled(r3)     // Catch:{ Exception -> 0x0019 }
            boolean r4 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH     // Catch:{ Exception -> 0x0019 }
            if (r4 == 0) goto L_0x001c
            com.samsung.android.gallery.module.trash.abstraction.ITrashProvider r5 = com.samsung.android.gallery.module.trash.factory.TrashProviderFactory.getInstance(r20)     // Catch:{ Exception -> 0x0019 }
            java.lang.Object[] r5 = r5.getTrashInfo()     // Catch:{ Exception -> 0x0019 }
            goto L_0x0024
        L_0x0019:
            r0 = move-exception
            goto L_0x0147
        L_0x001c:
            com.samsung.android.gallery.module.trash.helper.TrashExternalHelper r5 = com.samsung.android.gallery.module.trash.helper.TrashHelperFactory.getExternalHelper(r20)     // Catch:{ Exception -> 0x0019 }
            java.lang.Object[] r5 = r5.getTrashDirectoryInfo()     // Catch:{ Exception -> 0x0019 }
        L_0x0024:
            r6 = 0
            r7 = r5[r6]     // Catch:{ Exception -> 0x0019 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0019 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x0019 }
            r8 = 1
            r9 = r5[r8]     // Catch:{ Exception -> 0x0019 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0019 }
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ Exception -> 0x0019 }
            int r7 = r7 + r9
            r9 = 0
            if (r4 == 0) goto L_0x0066
            com.samsung.android.gallery.support.utils.PreferenceCache r10 = com.samsung.android.gallery.support.utils.PreferenceCache.TrashMigrationComplete     // Catch:{ Exception -> 0x0019 }
            boolean r10 = r10.getBoolean()     // Catch:{ Exception -> 0x0019 }
            if (r10 != 0) goto L_0x0063
            com.samsung.android.gallery.module.trash.helper.TrashExternalHelper r9 = com.samsung.android.gallery.module.trash.helper.TrashHelperFactory.getExternalHelper(r20)     // Catch:{ Exception -> 0x0019 }
            java.lang.Object[] r9 = r9.getTrashDirectoryInfo()     // Catch:{ Exception -> 0x0019 }
            r6 = r9[r6]     // Catch:{ Exception -> 0x0019 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0019 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x0019 }
            r8 = r9[r8]     // Catch:{ Exception -> 0x0019 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x0019 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x0019 }
            int r6 = r6 + r8
        L_0x0063:
            r8 = r6
            r6 = r10
            goto L_0x0067
        L_0x0066:
            r8 = r6
        L_0x0067:
            android.os.Bundle r10 = new android.os.Bundle     // Catch:{ Exception -> 0x0019 }
            r10.<init>()     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_ITEM_COUNT     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            if (r6 != 0) goto L_0x0079
            if (r7 <= 0) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r12 = r8
            goto L_0x007a
        L_0x0079:
            r12 = r7
        L_0x007a:
            r10.putInt(r11, r12)     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_INTERNAL_CAPACITY     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            r12 = 2
            r13 = r5[r12]     // Catch:{ Exception -> 0x0019 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ Exception -> 0x0019 }
            long r13 = r13.longValue()     // Catch:{ Exception -> 0x0019 }
            r15 = 0
            if (r9 == 0) goto L_0x0099
            r17 = r9[r12]     // Catch:{ Exception -> 0x0019 }
            java.lang.Long r17 = (java.lang.Long) r17     // Catch:{ Exception -> 0x0019 }
            long r17 = r17.longValue()     // Catch:{ Exception -> 0x0019 }
            goto L_0x009b
        L_0x0099:
            r17 = r15
        L_0x009b:
            long r13 = r13 + r17
            r10.putLong(r11, r13)     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_SDCARD_CAPACITY     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            r13 = 3
            r14 = r5[r13]     // Catch:{ Exception -> 0x0019 }
            java.lang.Long r14 = (java.lang.Long) r14     // Catch:{ Exception -> 0x0019 }
            long r17 = r14.longValue()     // Catch:{ Exception -> 0x0019 }
            if (r9 == 0) goto L_0x00b9
            r14 = r9[r13]     // Catch:{ Exception -> 0x0019 }
            java.lang.Long r14 = (java.lang.Long) r14     // Catch:{ Exception -> 0x0019 }
            long r15 = r14.longValue()     // Catch:{ Exception -> 0x0019 }
        L_0x00b9:
            long r14 = r17 + r15
            r10.putLong(r11, r14)     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_INTENT_ACTION     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            java.lang.String r14 = "com.android.gallery.action.TRASH_VIEW"
            r10.putString(r11, r14)     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_ON     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            r10.putBoolean(r11, r3)     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.app.provider.LocalProvider2$ResponseType r11 = com.samsung.android.gallery.app.provider.LocalProvider2.ResponseType.TRASH_MIGRATION_COMPLETED     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r11.name()     // Catch:{ Exception -> 0x0019 }
            r10.putBoolean(r11, r6)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r11 = r1.TAG     // Catch:{ Exception -> 0x0019 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0019 }
            r14.<init>(r2)     // Catch:{ Exception -> 0x0019 }
            if (r3 == 0) goto L_0x00e7
            java.lang.String r2 = "on"
            goto L_0x00e9
        L_0x00e7:
            java.lang.String r2 = "off"
        L_0x00e9:
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r2 = "F"
            java.lang.String r3 = "T"
            if (r4 == 0) goto L_0x00f7
            r4 = r3
            goto L_0x00f8
        L_0x00f7:
            r4 = r2
        L_0x00f8:
            r14.append(r4)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            if (r6 == 0) goto L_0x0101
            r2 = r3
        L_0x0101:
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            r14.append(r7)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            r2 = r5[r12]     // Catch:{ Exception -> 0x0019 }
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            r2 = r5[r13]     // Catch:{ Exception -> 0x0019 }
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r2 = " legacy: "
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            r14.append(r8)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r2 = "0"
            if (r9 == 0) goto L_0x012c
            r3 = r9[r12]     // Catch:{ Exception -> 0x0019 }
            goto L_0x012d
        L_0x012c:
            r3 = r2
        L_0x012d:
            r14.append(r3)     // Catch:{ Exception -> 0x0019 }
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            if (r9 == 0) goto L_0x0137
            r2 = r9[r13]     // Catch:{ Exception -> 0x0019 }
        L_0x0137:
            r14.append(r2)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r0 = "}"
            r14.append(r0)     // Catch:{ Exception -> 0x0019 }
            java.lang.String r0 = r14.toString()     // Catch:{ Exception -> 0x0019 }
            com.samsung.android.gallery.support.utils.Log.d(r11, r0)     // Catch:{ Exception -> 0x0019 }
            return r10
        L_0x0147:
            java.lang.String r1 = r1.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getTrashInfo:"
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.provider.LocalProvider2.getTrashInfo(android.content.Context):android.os.Bundle");
    }

    private String getTrashLogData(long[] jArr) {
        return getCallingPackage() + "{" + Arrays.toString(jArr) + "}";
    }

    private TrashLogType getTrashLogType() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            return TrashLogType.MOVE_TO_BY_PROVIDER;
        }
        return TrashLogType.DELETE_BY_PROVIDER;
    }

    private Bundle isInTrash(Context context, String str, Bundle bundle) {
        if (DeviceConfig.DEBUG_BINARY) {
            throw new SecurityException("multiple binding process is not allowed. " + getCallingPackage());
        } else if (!Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            return null;
        } else {
            PostProcessingHelper postProcessingHelper = new PostProcessingHelper();
            String str2 = this.TAG;
            Log.d(str2, "isInTrash called : " + Logger.getEncodedString(str), Logger.toString(bundle));
            boolean isInTrash = postProcessingHelper.isInTrash(context, bundle.getLong("secMpId", -1));
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("result", isInTrash);
            return bundle2;
        }
    }

    private void mkdirsIfAbsent(String str) {
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            String str2 = this.TAG;
            Log.d(str2, "mkdirsIfAbsent failed " + file.getName());
        }
    }

    private ParcelFileDescriptor openAshMem(Uri uri) {
        int i2 = UnsafeCast.toInt(uri.getLastPathSegment(), 0);
        if (i2 == 0 || i2 != ShareList.getSharedMemoryHash()) {
            throw new IllegalArgumentException("openAshMem failed. invalid Uri{" + uri + "}");
        }
        try {
            byte[] sharedMemory = ShareList.getSharedMemory();
            String str = this.TAG;
            Log.d(str, "openAshMem {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sharedMemory.length + "}");
            return new TransferThread(sharedMemory).prepare();
        } catch (Exception e) {
            String str2 = this.TAG;
            Log.e(str2, "openAshMem failed e=" + e.getMessage());
            throw new IllegalArgumentException("openAshMem failed. " + e.getMessage());
        }
    }

    private ParcelFileDescriptor openLocalFile(Uri uri) {
        try {
            return ParcelFileDescriptor.open(new File(FileProviderUtil.toReadablePath(uri.getLastPathSegment())), 268435456);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "openLocalFile failed e=" + e.getMessage());
            throw new IllegalArgumentException("openLocalFile failed. " + e.getMessage());
        }
    }

    private Bundle pppCompleted(Context context, String str, Bundle bundle) {
        if (!Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Blackboard.getApplicationInstance().publish("debug://PppCompletedStart", Long.valueOf(currentTimeMillis));
        Log.initPLogForce(currentTimeMillis);
        long j2 = bundle.getLong("secMpId", -1);
        String str2 = this.TAG;
        StringBuilder j3 = j.j(j2, "pppCompleted called : ", ArcCommonLog.TAG_COMMA);
        j3.append(Logger.getEncodedString(str));
        j3.append(ArcCommonLog.TAG_COMMA);
        j3.append(Logger.toString(bundle));
        Log.p(str2, j3.toString());
        PostProcessingHelper postProcessingHelper = new PostProcessingHelper();
        postProcessingHelper.validate(str, bundle);
        if (PocFeatures.isEnabled(PocFeatures.SavePppTempImage)) {
            postProcessingHelper.saveTemp(str, bundle);
        }
        postProcessingHelper.pppCompleted(context, j2);
        return null;
    }

    private Bundle procKeepSession(Context context, String str, String str2) {
        String str3;
        String caller = getCaller();
        if (caller == null || !SignatureChecker.support(context, caller)) {
            String str4 = this.TAG;
            StringBuilder k = j.k("keep session failed. not system package. caller=", caller, " ");
            k.append(Logger.getEncodedString((String) Stream.of(PackageMonitorCompat.getInstance().getPackageSignatures(caller)).map(new V8.a(11)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]"))));
            Log.e(str4, k.toString());
            throw new SecurityException(C0212a.l("only supported in system package. caller=", caller));
        }
        System.currentTimeMillis();
        Bundle bundle = new Bundle();
        KeepStorage passStorage = SeApiCompat.getPassStorage();
        boolean z = true;
        boolean z3 = false;
        if ("open".equals(str2)) {
            String create = passStorage.create();
            if (create != null) {
                mkdirsIfAbsent(create);
            }
            if (create == null || !passStorage.unlock() || !passStorage.prepare()) {
                z = false;
            }
            if (!z) {
                if (create == null) {
                    str3 = "create failed";
                } else if (!passStorage.isUnlocked()) {
                    str3 = "unlock failed";
                } else {
                    str3 = "prepare failed";
                }
                bundle.putString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, str3);
            }
        } else if (!"close".equals(str2)) {
            if ("isUnlocked".equals(str2)) {
                z3 = passStorage.isUnlocked();
            } else {
                bundle.putString(KeywordInfo.EXTRA_BUNDLE_KEY_REASON, "not supported api " + str);
            }
            bundle.putString("keep", new File(passStorage.getRoot()).getName());
            bundle.putBoolean("result", z3);
            return bundle;
        } else if (!passStorage.prepare() || !passStorage.lock()) {
            z = false;
        }
        z3 = z;
        bundle.putString("keep", new File(passStorage.getRoot()).getName());
        bundle.putBoolean("result", z3);
        return bundle;
    }

    private Cursor queryAlbumGroupInfo(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Object obj;
        try {
            Cursor query = new AlbumGroupView().query(getContext(), str, strArr2);
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("queryAlbumGroupInfo:");
            if (query != null) {
                obj = Integer.valueOf(query.getCount());
            } else {
                obj = "no result";
            }
            sb2.append(obj);
            Log.d(str3, sb2.toString());
            return query;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cursor queryForShareAlbum(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String caller = getCaller();
        if (SignatureChecker.support(getContext(), caller)) {
            String queryParameter = uri.getQueryParameter("cat");
            String queryParameter2 = uri.getQueryParameter("id");
            if (TextUtils.isEmpty(queryParameter) || TextUtils.isEmpty(queryParameter2)) {
                throw new IllegalArgumentException(C0212a.n("wrong parameters for cat=", queryParameter, " and id=", queryParameter2));
            } else if (!"story".equals(queryParameter)) {
                return null;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                Cursor query = DbCompat.query(new QueryParams(DbKey.STORY_SHARE_FILES).addAlbumId(Integer.parseInt(queryParameter2)));
                String str4 = this.TAG;
                StringBuilder sb2 = new StringBuilder(Contract.QUERY);
                if (query != null) {
                    str3 = "T";
                } else {
                    str3 = "F";
                }
                a.A(new Object[]{uri, str3, Long.valueOf(currentTimeMillis)}, sb2, str4);
                return query;
            }
        } else {
            throw new SecurityException(C0212a.l("only supported in system package. caller=", caller));
        }
    }

    private Bundle readPreference(Context context, String str, String str2, Bundle bundle) {
        String str3;
        String caller = getCaller();
        if (SignatureChecker.support(context, caller)) {
            String replace = str.replace("preference://read/", "");
            if (str2 != null) {
                GalleryPreference galleryPreference = null;
                if (bundle != null) {
                    str3 = bundle.getString("space", (String) null);
                } else {
                    str3 = null;
                }
                if (str3 != null) {
                    galleryPreference = GalleryPreference.of(str3);
                }
                if (galleryPreference == null) {
                    galleryPreference = GalleryPreference.getInstance();
                }
                return read(str, galleryPreference, str2, replace);
            }
            throw new IllegalArgumentException(C0212a.l("wrong argument=", str2));
        }
        throw new SecurityException(C0212a.l("only supported in system package. caller=", caller));
    }

    private Bundle sendMessage(Bundle bundle) {
        Intent intent;
        Integer num;
        String str = this.TAG;
        Log.at(str, "sendMessage called : " + Logger.toString(bundle));
        if (bundle == null || (intent = (Intent) bundle.getParcelable("intent")) == null || (num = IntentActionToEventKey.get(intent.getAction())) == null) {
            return null;
        }
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(num.intValue(), intent));
        return null;
    }

    private Bundle setGppmDonationEnabled(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Log.d(this.TAG, "set gppm donation", str);
            GppmHelper.setDonationEnabled(UnsafeCast.toBoolean(str));
            return new Bundle();
        }
        throw new IllegalArgumentException("argument is null or empty");
    }

    private Bundle touchOnVold(Context context, String str, Bundle bundle) {
        String str2;
        ArrayList<String> arrayList;
        String caller = getCaller();
        if (VoldApiPackageHolder.support(context, caller)) {
            if (bundle != null) {
                str2 = bundle.getString("caller-package");
            } else {
                str2 = null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (bundle != null) {
                arrayList = bundle.getStringArrayList("file-list");
            } else {
                arrayList = new ArrayList<>();
            }
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
            int i2 = 0;
            if (arrayList.size() > 0) {
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (!VoldApi.touchOnVold(context, it.next())) {
                        i2++;
                    }
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            String str3 = this.TAG;
            Log.d(str3, "touchOnVold" + Logger.v(caller, str2, Integer.valueOf(arrayList.size()), Integer.valueOf(i2)) + " +" + currentTimeMillis2);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("totalCount", arrayList.size());
            bundle2.putInt("failCount", i2);
            bundle2.putLong("executionTime", currentTimeMillis2);
            return bundle2;
        }
        throw new SecurityException(C0212a.l("not supported in this package ", caller));
    }

    private Bundle updateGalleryAssistant(Context context, String str, String str2, Bundle bundle) {
        if (!"com.samsung.android.gallery.assistant.app".equals(getCaller()) || !"settings".equals(str2)) {
            return null;
        }
        Blackboard.postGlobal("global://assistant/update/settings", bundle);
        return null;
    }

    private Bundle uploadEditedItem(Context context, Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            return new SharedItemUploader().run(context, bundle);
        }
        Log.w(this.TAG, "not support updating shared item.");
        return null;
    }

    private Bundle writePreference(Context context, String str, String str2, Bundle bundle) {
        GalleryPreference galleryPreference;
        String caller = getCaller();
        if (SignatureChecker.support(context, caller)) {
            if ("cache".equals(str2) || "debug".equals(str2)) {
                galleryPreference = GalleryPreference.of(str2);
            } else {
                galleryPreference = null;
            }
            if (galleryPreference != null) {
                String string = bundle.getString("key");
                if (!TextUtils.isEmpty(string)) {
                    String string2 = bundle.getString("value");
                    if (string2 == null) {
                        galleryPreference.removeState(string);
                    } else {
                        String replace = str.replace("preference://write/", "");
                        if ("boolean".equalsIgnoreCase(replace)) {
                            galleryPreference.saveState(string, Boolean.parseBoolean(string2));
                        } else if ("string".equalsIgnoreCase(replace)) {
                            galleryPreference.saveState(string, string2);
                        } else if ("integer".equalsIgnoreCase(replace)) {
                            galleryPreference.saveState(string, Integer.parseInt(string2));
                        } else if ("long".equalsIgnoreCase(replace)) {
                            galleryPreference.saveState(string, Long.parseLong(string2));
                        } else {
                            throw new IllegalArgumentException(C0212a.l("wrong parameter ", replace));
                        }
                    }
                    bundle.putBoolean("result", true);
                    return bundle;
                }
                throw new IllegalArgumentException("wrong arguments. null or empty value in mandatory fields");
            }
            throw new IllegalArgumentException(C0212a.l("wrong argument. wrong space=", str2));
        }
        throw new SecurityException(C0212a.l("only supported in system package. caller=", caller));
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        Context context = getContext();
        if (context == null || TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "unable to handle call [" + context + "][" + str + "]");
            return null;
        }
        AppResources.setAppContext(context);
        if ("setGppmDonationEnabled".equals(str)) {
            return setGppmDonationEnabled(context, str2);
        }
        if ("getGppmDonationEnabled".equals(str)) {
            return getGppmDonationEnabled(context);
        }
        if (str.startsWith("preference://read/")) {
            return readPreference(context, str, str2, bundle);
        }
        if (str.startsWith("preference://write/")) {
            return writePreference(context, str, str2, bundle);
        }
        if ("assistant://update".equals(str)) {
            return updateGalleryAssistant(context, str, str2, bundle);
        }
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM && "keep://session".equals(str)) {
            return procKeepSession(context, str, str2);
        }
        SystemEnvironment.updateIfChanged(context);
        char c5 = 65535;
        switch (str.hashCode()) {
            case -2051333701:
                if (str.equals("pppCompleted")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1785559027:
                if (str.equals("clipboard://update")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1428782890:
                if (str.equals("vold://touch")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1407272543:
                if (str.equals("album://story/create")) {
                    c5 = 3;
                    break;
                }
                break;
            case -1142143787:
                if (str.equals("deleteItems")) {
                    c5 = 4;
                    break;
                }
                break;
            case -874556612:
                if (str.equals("deleteItemsSec")) {
                    c5 = 5;
                    break;
                }
                break;
            case -506032861:
                if (str.equals("createRecap")) {
                    c5 = 6;
                    break;
                }
                break;
            case -70427648:
                if (str.equals("changeOrientation")) {
                    c5 = 7;
                    break;
                }
                break;
            case 467146026:
                if (str.equals("clipboard://load")) {
                    c5 = 8;
                    break;
                }
                break;
            case 691453791:
                if (str.equals("sendMessage")) {
                    c5 = 9;
                    break;
                }
                break;
            case 937553853:
                if (str.equals("uploadEditedItem")) {
                    c5 = 10;
                    break;
                }
                break;
            case 1175183696:
                if (str.equals("getTrashInfo")) {
                    c5 = 11;
                    break;
                }
                break;
            case 1301255593:
                if (str.equals("downloadCloudFile")) {
                    c5 = 12;
                    break;
                }
                break;
            case 1349102729:
                if (str.equals("isInTrash")) {
                    c5 = 13;
                    break;
                }
                break;
            case 1535462407:
                if (str.equals("empty_trash_items")) {
                    c5 = 14;
                    break;
                }
                break;
            case 1588227721:
                if (str.equals("clipboard://clear")) {
                    c5 = 15;
                    break;
                }
                break;
            case 1764271966:
                if (str.equals("deleteItem")) {
                    c5 = 16;
                    break;
                }
                break;
            case 1911420947:
                if (str.equals("deleteItemSec")) {
                    c5 = 17;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return pppCompleted(context, str2, bundle);
            case 1:
                return LocalClipboard.update(this, str2, bundle);
            case 2:
                return touchOnVold(context, str2, bundle);
            case 3:
                return createAlbumStory(context, str2, bundle);
            case 4:
                return deleteItems(context, bundle, false);
            case 5:
                return deleteItems(context, bundle, true);
            case 6:
                return RecapWorker.init(context, str2, bundle, false);
            case 7:
                return changeOrientation(context, bundle);
            case 8:
                return LocalClipboard.load(this, str2, bundle);
            case 9:
                return sendMessage(bundle);
            case 10:
                return uploadEditedItem(context, bundle);
            case 11:
                return getTrashInfo(context);
            case 12:
                return downloadCloudFile(context, bundle);
            case 13:
                return isInTrash(context, str2, bundle);
            case 14:
                return emptyTrashItems(context);
            case 15:
                return LocalClipboard.clear(this, str2, bundle);
            case 16:
                return deleteItem(context, bundle, false);
            case 17:
                return deleteItem(context, bundle, true);
            default:
                Log.e(this.TAG, "not matched method [" + str + "]");
                return null;
        }
    }

    public int createStory(ArrayList<Uri> arrayList, String str, StoryType storyType) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList();
        UriItemLoader.loadUri(arrayList, arrayList2);
        ArrayList arrayList3 = new ArrayList(arrayList2);
        int createStory = DbCompat.storyApi().createStory(arrayList3, str, 0, StoryType.MANUAL, (String) null, arrayList3.size());
        String str3 = this.TAG;
        StringBuilder sb2 = new StringBuilder("createStory");
        if (createStory < 0) {
            str2 = " failed";
        } else {
            str2 = "";
        }
        sb2.append(str2);
        a.A(new Object[]{Integer.valueOf(createStory), Integer.valueOf(arrayList.size()), storyType, Long.valueOf(currentTimeMillis)}, sb2, str3);
        return createStory;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (SdkConfig.atLeast(SdkConfig.SEM.V)) {
            super.dump(fileDescriptor, printWriter, strArr);
        }
        Context context = getContext();
        if (context == null) {
            printWriter.println("dump failed. context is null");
            return;
        }
        try {
            new LocalProviderDebugHelper(context).dumpDb(context, printWriter);
        } catch (Error | Exception e) {
            printWriter.println("dump failed e=" + e.getMessage());
        }
    }

    public int matchUri(Uri uri) {
        return UriMatcherHolder2.URI_MATCHER.match(uri);
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        int matchUri = matchUri(uri);
        if (matchUri == 10) {
            return openAshMem(uri);
        }
        if (matchUri == 13) {
            return openLocalFile(uri);
        }
        Log.e((CharSequence) this.TAG, "openFile failed. invalid Uri", uri, str, Integer.valueOf(matchUri));
        throw new FileNotFoundException("openFile failed. invalid Uri{" + uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str + "}");
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int matchUri = matchUri(uri);
        if (matchUri == 18) {
            return queryForShareAlbum(uri, strArr, str, strArr2, str2);
        }
        if (matchUri == 20) {
            return queryAlbumGroupInfo(uri, strArr, str, strArr2, str2);
        }
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || matchUri != 23) {
            return super.query(uri, strArr, str, strArr2, str2);
        }
        return PrivateDatabase.getInstance().queryBackupFiles(str, strArr2);
    }

    public Bundle read(String str, GalleryPreference galleryPreference, String str2, String str3) {
        String str4 = str;
        GalleryPreference galleryPreference2 = galleryPreference;
        String str5 = str2;
        String str6 = str3;
        PreferenceName valueOfKey = PreferenceName.valueOfKey(str5);
        Bundle bundle = new Bundle();
        char c5 = 65535;
        if (valueOfKey == null || valueOfKey.getDefault() == null) {
            str6.getClass();
            switch (str6.hashCode()) {
                case -891985903:
                    if (str6.equals("string")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 3327612:
                    if (str6.equals("long")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 64711720:
                    if (str6.equals("boolean")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 1958052158:
                    if (str6.equals("integer")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    bundle.putString("value", galleryPreference2.loadString(str5, ""));
                    return bundle;
                case 1:
                    bundle.putLong("value", galleryPreference2.loadLong(str5, Long.MIN_VALUE));
                    return bundle;
                case 2:
                    bundle.putBoolean("value", galleryPreference2.loadBoolean(str5, false));
                    return bundle;
                case 3:
                    bundle.putInt("value", galleryPreference2.loadInt(str5, Integer.MIN_VALUE));
                    return bundle;
                default:
                    throw new IllegalArgumentException(C0212a.l("wrong method=", str4));
            }
        } else {
            str6.getClass();
            switch (str6.hashCode()) {
                case -891985903:
                    if (str6.equals("string")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 3327612:
                    if (str6.equals("long")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 64711720:
                    if (str6.equals("boolean")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 1958052158:
                    if (str6.equals("integer")) {
                        c5 = 3;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    bundle.putString("value", galleryPreference2.loadString(valueOfKey));
                    return bundle;
                case 1:
                    bundle.putLong("value", galleryPreference2.loadLong(valueOfKey));
                    return bundle;
                case 2:
                    bundle.putBoolean("value", galleryPreference2.loadBoolean(valueOfKey));
                    return bundle;
                case 3:
                    bundle.putInt("value", galleryPreference2.loadInt(valueOfKey));
                    return bundle;
                default:
                    throw new IllegalArgumentException(C0212a.l("wrong method=", str4));
            }
        }
    }

    public void assertSystemPackage() {
    }
}
