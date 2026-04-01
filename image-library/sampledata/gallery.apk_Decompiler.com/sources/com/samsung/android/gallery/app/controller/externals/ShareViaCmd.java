package com.samsung.android.gallery.app.controller.externals;

import A.a;
import H3.j;
import J6.c;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DelegateHelper;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.abstraction.ShareData;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareViaCmd extends BaseCommand {
    private MediaItem mBaseItem;
    private int mCloudItemCount;
    private boolean mFromRemasterViewer = false;
    private boolean mFromTimeline = false;
    private boolean mFromViewer = false;
    private int mImageCount;
    private boolean mIsImage;
    private int mItemIndex;
    private String mLocationKey;
    private Point mPopoverAnchorPos;
    private int mVideoCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShareViaData {
        private final boolean CONSIDER_REMASTER;
        int brokenCount;
        int cloudCount;
        int drmCount;
        int imageCount;
        final ArrayList<Uri> list = new ArrayList<>();
        int pppCount;
        long totalSize;
        int unknownCount;
        int unsupportedCount;
        int videoCount;

        public ShareViaData(String str) {
            boolean z;
            if (!SdkConfig.atLeast(SdkConfig.SEM.R_MR1) || !LocationKey.isRevitalizationView(str)) {
                z = false;
            } else {
                z = true;
            }
            this.CONSIDER_REMASTER = z;
        }

        public ShareViaData process(Context context, MediaItem[] mediaItemArr) {
            Uri uri;
            for (MediaItem mediaItem : mediaItemArr) {
                if (!MediaItemUtil.isSharableItem(mediaItem, false)) {
                    if (mediaItem.isDrm()) {
                        this.drmCount++;
                    } else if (mediaItem.isCommonPostProcessing()) {
                        this.pppCount++;
                    } else if (!MediaItemUtil.hasValidSize(mediaItem)) {
                        this.brokenCount++;
                    } else {
                        this.unknownCount++;
                    }
                    this.unsupportedCount++;
                } else {
                    String path = mediaItem.getPath();
                    if (this.CONSIDER_REMASTER) {
                        if (path == null) {
                            this.unsupportedCount++;
                        } else {
                            uri = FileProviderUtil.getUri(context, path);
                        }
                    } else if (mediaItem.isTemporary() || mediaItem.isPrivateItem()) {
                        uri = FileProviderUtil.getUri(context, path);
                    } else {
                        uri = null;
                    }
                    PocFeatures pocFeatures = PocFeatures.LockedAlbum;
                    if (mediaItem.isStream()) {
                        uri = ContentUri.getStreamUri(context, mediaItem);
                    }
                    if (uri == null) {
                        uri = ContentUri.getUri(mediaItem);
                    }
                    if (uri != null && MediaUri.isFileUri(uri.toString())) {
                        Log.d("ShareViaData", "process > change FileUri to ContentUri" + Logger.getEncodedString(uri.toString()));
                        uri = FileProviderUtil.getFileProviderUri(context, mediaItem.getPath());
                    }
                    if (uri == null && mediaItem.isUriItem()) {
                        uri = FileProviderUtil.getFileProviderUri(context, mediaItem.getPath());
                    }
                    if (uri == null && FileUtils.isInExternalCacheDir(path)) {
                        uri = FileProviderUtil.getUri(context, path);
                    }
                    if (uri == null) {
                        this.unsupportedCount++;
                    } else {
                        this.list.add(uri);
                        if (mediaItem.isCloudOnly()) {
                            this.cloudCount++;
                            this.totalSize = mediaItem.getCloudOriginalSize() + this.totalSize;
                        } else {
                            this.totalSize = mediaItem.getFileSize() + this.totalSize;
                        }
                        if (mediaItem.isVideo()) {
                            this.videoCount++;
                        } else {
                            this.imageCount++;
                        }
                    }
                }
            }
            return this;
        }
    }

    private boolean addShareSheetData(Intent intent, MediaItem[] mediaItemArr) {
        Context context;
        if (!PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET) {
            return false;
        }
        if (getHandler() != null) {
            context = getHandler().getContext();
        } else {
            context = null;
        }
        if (context != null) {
            Uri providerUri = ShareProvider.getProviderUri(context);
            if (!ShareProvider.hasExtendedList(providerUri)) {
                return false;
            }
            intent.putExtra("sem_extra_chooser_slice_provider_uri", providerUri.toString());
            return true;
        }
        Log.w((CharSequence) this.TAG, "ShareSheet skip ", context);
        return false;
    }

    private Intent createChooserIntent(Context context, Intent intent, ArrayList<Uri> arrayList, long j2) {
        Intent intent2;
        if (supportChooserReceiver()) {
            registerChosenComponentReceiver(context);
            intent2 = Intent.createChooser(intent, context.getString(R.string.share_short), createPendingIntent(context).getIntentSender());
        } else {
            intent2 = Intent.createChooser(intent, context.getString(R.string.share_short));
        }
        if (arrayList.size() > 1) {
            intent2.putExtra("sem_extra_chooser_content_count", arrayList.size());
        }
        intent2.putExtra("sem_extra_chooser_content_size", StringCompat.toReadableSize((double) j2));
        return intent2;
    }

    private PendingIntent createPendingIntent(Context context) {
        return AndroidCompat.createBroadcastPendingIntent(context, 0, new Intent("CHOSEN_COMPONENT_RECEIVE_ACTION"), 0);
    }

    private ShareData createShareData(MediaItem mediaItem) {
        return new ShareData(ContentUri.getUriString(mediaItem), mediaItem.getBucketID(), FileUtils.getBucketNameFromPath(mediaItem.getReferencePath()));
    }

    private Intent createShareIntent(ArrayList<Uri> arrayList, ShareComponent shareComponent) {
        Intent buildShareIntent = DelegateHelper.buildShareIntent(arrayList, this.mImageCount, this.mVideoCount);
        int i2 = 1;
        buildShareIntent.addFlags(1);
        buildShareIntent.putExtra("exit_on_sent", false);
        buildShareIntent.putExtra("more_actions_print", isKnox() ^ true ? 1 : 0);
        buildShareIntent.putExtra("more_actions_quick_connect", 1);
        buildShareIntent.putExtra("more_actions_package_name", "com.sec.android.gallery3d");
        buildShareIntent.putExtra("more_actions_screen_mirroring", 1);
        if (Features.isEnabled(Features.USE_SCREEN_SHARING)) {
            if (!this.mFromViewer || !this.mIsImage || isKnox()) {
                i2 = 0;
            }
            buildShareIntent.putExtra("more_actions_screen_sharing", i2);
        }
        buildShareIntent.putExtra("more_actions_dlna", this.mFromViewer ? 1 : 0);
        buildShareIntent.putExtra("location_key", this.mLocationKey);
        buildShareIntent.putExtra("item_index", this.mItemIndex);
        if (shareComponent != null && shareComponent.isFromBixby()) {
            buildShareIntent.putExtra("action_send_addition", "com.sec.android.gallery3d");
        }
        if (SuperHdrConfig.SUPPORT) {
            buildShareIntent.putExtra("photo_hdr", SuperHdrConfig.isEnabled());
        }
        loadAdditionalExtraData(buildShareIntent);
        return buildShareIntent;
    }

    private void filterComponentList(Context context, Intent intent, Intent intent2) {
        ArrayList<String> shareFilterList = getShareFilterList();
        if (shareFilterList != null && !shareFilterList.isEmpty()) {
            try {
                ArrayList arrayList = new ArrayList();
                for (ResolveInfo next : context.getPackageManager().queryIntentActivities(intent2, 128)) {
                    if (shareFilterList.contains(next.activityInfo.name)) {
                        ActivityInfo activityInfo = next.activityInfo;
                        arrayList.add(new ComponentName(activityInfo.packageName, activityInfo.name));
                    }
                }
                intent.putParcelableArrayListExtra("extra_chooser_droplist", arrayList);
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "filterComponentList failed", (Throwable) e);
            }
        }
    }

    private ArrayList<String> getShareFilterList() {
        if (!hasCloudItem()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.sec.knox.switcher.SwitchSfActivity");
        arrayList.add("com.sec.knox.switcher.B2CStoreFilesActivity");
        return arrayList;
    }

    private boolean hasCloudItem() {
        if (this.mCloudItemCount != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, ShareComponent shareComponent) {
        ArrayList<MediaItem> arrayList;
        try {
            if (LocationKey.isPrivateAlbum(this.mLocationKey)) {
                arrayList = PrivateDatabase.getInstance().loadSharePrivateGroupItems(mediaItemArr);
            } else if (this.mFromTimeline) {
                arrayList = GroupItemLoader.loadShareGroupItemsForTimeline(mediaItemArr);
            } else {
                arrayList = GroupItemLoader.loadShareGroupItems(mediaItemArr);
            }
            perform((MediaItem[]) arrayList.toArray(new MediaItem[0]), shareComponent);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "fail share", (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(Object[] objArr, EventContext eventContext, MediaItem[] mediaItemArr, long j2, Integer num) {
        MediaItem[] mediaItemArr2;
        if (num.intValue() > 0) {
            mediaItemArr2 = replacePppItem(eventContext, mediaItemArr);
        } else {
            mediaItemArr2 = mediaItemArr;
        }
        objArr[0] = mediaItemArr2;
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)}, new StringBuilder("onPreExecute#PppChecker"), str);
        super.onPreExecute(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$perform$2(MediaItem[] mediaItemArr) {
        performUnlimitedShareVia(getContext(), mediaItemArr);
    }

    private void perform(MediaItem[] mediaItemArr, ShareComponent shareComponent) {
        if (mediaItemArr.length > 0) {
            this.mBaseItem = mediaItemArr[0];
        }
        if (mediaItemArr.length > 500) {
            SimpleThreadPool.getInstance().execute(new M5.a(7, this, mediaItemArr));
        } else {
            performShareVia(getContext(), mediaItemArr, shareComponent);
        }
        addOperationHistory(HistoryTable.ActionKeyword.SHARE, mediaItemArr);
        if (!supportChooserReceiver()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v12, resolved type: android.os.Bundle} */
    /* JADX WARNING: type inference failed for: r15v8 */
    /* JADX WARNING: type inference failed for: r15v9, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r15v39 */
    /* JADX WARNING: type inference failed for: r15v40 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void performShareVia(android.content.Context r13, com.samsung.android.gallery.module.data.MediaItem[] r14, com.samsung.android.gallery.module.share.ShareComponent r15) {
        /*
            r12 = this;
            java.lang.String r1 = "popover anchor pos="
            com.samsung.android.gallery.app.controller.externals.ShareViaCmd$ShareViaData r0 = new com.samsung.android.gallery.app.controller.externals.ShareViaCmd$ShareViaData
            java.lang.String r2 = r12.mLocationKey
            r0.<init>(r2)
            com.samsung.android.gallery.app.controller.externals.ShareViaCmd$ShareViaData r0 = r0.process(r13, r14)
            int r2 = r12.mCloudItemCount
            int r3 = r0.cloudCount
            int r2 = r2 + r3
            r12.mCloudItemCount = r2
            int r2 = r12.mVideoCount
            int r3 = r0.videoCount
            int r2 = r2 + r3
            r12.mVideoCount = r2
            int r2 = r12.mImageCount
            int r3 = r0.imageCount
            int r2 = r2 + r3
            r12.mImageCount = r2
            long r7 = r0.totalSize
            java.util.ArrayList<android.net.Uri> r6 = r0.list
            int r2 = r0.unsupportedCount
            if (r2 <= 0) goto L_0x003a
            boolean r2 = r6.isEmpty()
            if (r2 != 0) goto L_0x0034
            r2 = 2131888941(0x7f120b2d, float:1.9412532E38)
            goto L_0x0037
        L_0x0034:
            r2 = 2131888940(0x7f120b2c, float:1.941253E38)
        L_0x0037:
            r12.showToast((int) r2)
        L_0x003a:
            java.lang.String r2 = r12.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r9 = "performShareVia {"
            r3.<init>(r9)
            boolean r4 = r12.mFromRemasterViewer
            if (r4 == 0) goto L_0x004a
            java.lang.String r4 = "remaster"
            goto L_0x0053
        L_0x004a:
            boolean r4 = r12.mFromViewer
            if (r4 == 0) goto L_0x0051
            java.lang.String r4 = "viewer"
            goto L_0x0053
        L_0x0051:
            java.lang.String r4 = "list"
        L_0x0053:
            r3.append(r4)
            java.lang.String r4 = ", s="
            r3.append(r4)
            int r4 = r6.size()
            r3.append(r4)
            java.lang.String r4 = "[c="
            r3.append(r4)
            int r4 = r0.cloudCount
            r3.append(r4)
            java.lang.String r4 = ",i="
            r3.append(r4)
            int r4 = r0.imageCount
            r3.append(r4)
            java.lang.String r4 = ",v="
            r3.append(r4)
            int r4 = r0.videoCount
            r3.append(r4)
            java.lang.String r4 = "], u="
            r3.append(r4)
            int r4 = r0.unsupportedCount
            r3.append(r4)
            int r4 = r0.unsupportedCount
            if (r4 <= 0) goto L_0x00bc
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "[drm="
            r4.<init>(r5)
            int r5 = r0.drmCount
            r4.append(r5)
            java.lang.String r5 = ",ppp="
            r4.append(r5)
            int r5 = r0.pppCount
            r4.append(r5)
            java.lang.String r5 = ",broken="
            r4.append(r5)
            int r5 = r0.brokenCount
            r4.append(r5)
            java.lang.String r5 = ",inv="
            r4.append(r5)
            int r0 = r0.unknownCount
            java.lang.String r5 = "]"
            java.lang.String r0 = c0.C0086a.l(r4, r0, r5)
            goto L_0x00be
        L_0x00bc:
            java.lang.String r0 = ""
        L_0x00be:
            r3.append(r0)
            java.lang.String r0 = ", "
            r3.append(r0)
            java.lang.String r0 = r12.mLocationKey
            r3.append(r0)
            java.lang.String r0 = "}"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.samsung.android.gallery.support.utils.Log.d(r2, r0)
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L_0x02a7
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r12.getBlackboard()
            java.lang.String r2 = "data://share_data_list_for_android_beam"
            r0.publish(r2, r6)
            java.lang.String r2 = "performShareVia failed e="
            if (r15 == 0) goto L_0x011c
            boolean r0 = r15.hasComponents()
            if (r0 == 0) goto L_0x011c
            android.content.Intent r14 = r12.createShareIntent(r6, r15)
            r0 = 185073664(0xb080000, float:2.6192647E-32)
            r14.addFlags(r0)
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.String r1 = r15.getPackageName()
            java.lang.String r15 = r15.getClassName()
            r0.<init>(r1, r15)
            r14.setComponent(r0)
            r13.startActivity(r14)     // Catch:{ Exception -> 0x010e }
            goto L_0x02a7
        L_0x010e:
            r0 = move-exception
            r13 = r0
            java.lang.String r12 = r12.TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r2)
            A.a.s(r13, r14, r12)
            goto L_0x02a7
        L_0x011c:
            android.content.Intent r5 = r12.createShareIntent(r6, r15)
            boolean r15 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO
            r10 = 0
            r11 = 1
            if (r15 == 0) goto L_0x0131
            int r15 = r6.size()
            if (r15 != r11) goto L_0x0131
            r15 = r14[r10]
            com.samsung.android.gallery.module.media.InstantSlowMoUtils.setIntentInstantSlowMoExtra(r5, r15)
        L_0x0131:
            boolean r15 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.IS_ONE_UI_80     // Catch:{ Exception -> 0x0159 }
            if (r15 == 0) goto L_0x015c
            int r15 = r6.size()     // Catch:{ Exception -> 0x0159 }
            if (r15 != r11) goto L_0x015c
            r15 = r14[r10]     // Catch:{ Exception -> 0x0159 }
            boolean r15 = r12.unsupportableShareSheetItem(r15)     // Catch:{ Exception -> 0x0159 }
            if (r15 == 0) goto L_0x015c
            android.content.ContentResolver r15 = r13.getContentResolver()     // Catch:{ Exception -> 0x0159 }
            java.lang.String r0 = r5.getType()     // Catch:{ Exception -> 0x0159 }
            java.lang.Object r3 = r6.get(r10)     // Catch:{ Exception -> 0x0159 }
            android.net.Uri r3 = (android.net.Uri) r3     // Catch:{ Exception -> 0x0159 }
            android.content.ClipData r15 = android.content.ClipData.newUri(r15, r0, r3)     // Catch:{ Exception -> 0x0159 }
            r5.setClipData(r15)     // Catch:{ Exception -> 0x0159 }
            goto L_0x015c
        L_0x0159:
            r0 = move-exception
            r15 = r0
            goto L_0x015f
        L_0x015c:
            r3 = r12
            r4 = r13
            goto L_0x016c
        L_0x015f:
            java.lang.String r0 = r12.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "setClipData failed e="
            r3.<init>(r4)
            A.a.s(r15, r3, r0)
            goto L_0x015c
        L_0x016c:
            android.content.Intent r12 = r3.createChooserIntent(r4, r5, r6, r7)
            java.lang.String r13 = r3.mLocationKey
            boolean r13 = com.samsung.android.gallery.support.blackboard.key.LocationKey.isPrivateAlbum(r13)
            if (r13 == 0) goto L_0x017d
            r13 = 268435456(0x10000000, float:2.5243549E-29)
            r12.addFlags(r13)
        L_0x017d:
            java.util.stream.Stream r13 = r6.stream()
            M4.j r15 = new M4.j
            r0 = 9
            r15.<init>(r0)
            java.util.stream.Stream r13 = r13.filter(r15)
            java.util.stream.Collector r15 = java.util.stream.Collectors.toList()
            java.lang.Object r13 = r13.collect(r15)
            java.util.List r13 = (java.util.List) r13
            boolean r15 = r13.isEmpty()
            if (r15 == 0) goto L_0x01a4
            boolean r14 = r3.updateChooserIntent(r14, r12)
            if (r14 == 0) goto L_0x01a4
            r14 = r11
            goto L_0x01a5
        L_0x01a4:
            r14 = r10
        L_0x01a5:
            if (r14 == 0) goto L_0x01c4
            com.samsung.android.gallery.module.data.MediaItem r15 = r3.mBaseItem
            com.samsung.android.gallery.database.dbtype.ExtrasID r0 = com.samsung.android.gallery.database.dbtype.ExtrasID.ORIGINAL_URI
            java.lang.Object r15 = r15.getExtra(r0)
            android.net.Uri r15 = (android.net.Uri) r15
            if (r15 == 0) goto L_0x01ef
            java.lang.String r0 = r3.TAG
            java.lang.String r5 = "putExtra sem_extra_chooser_slow_motion_original_url"
            com.samsung.android.gallery.support.utils.Log.d(r0, r5)
            java.lang.String r0 = "sem_extra_chooser_slow_motion_original_uri"
            java.lang.String r15 = r15.toString()
            r12.putExtra(r0, r15)
            goto L_0x01ef
        L_0x01c4:
            int r15 = r6.size()
            if (r15 != r11) goto L_0x01ef
            java.lang.Object r15 = r6.get(r10)
            android.net.Uri r15 = (android.net.Uri) r15
            java.lang.String r0 = "com.sec.android.gallery3d.fileprovider"
            java.lang.String r5 = r15.getAuthority()
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x01ef
            java.lang.String r15 = r15.getPath()
            if (r15 == 0) goto L_0x01ef
            java.lang.String r0 = "/captured/"
            boolean r15 = r15.contains(r0)
            if (r15 == 0) goto L_0x01ef
            java.lang.String r15 = "sem_extra_chooser_enable_large_preview"
            r12.putExtra(r15, r11)
        L_0x01ef:
            Fa.I r15 = new Fa.I
            r0 = 1
            r15.<init>(r4, r0)
            r13.forEach(r15)
            java.lang.String r13 = "android.intent.extra.INTENT"
            android.os.Parcelable r13 = r12.getParcelableExtra(r13)
            android.content.Intent r13 = (android.content.Intent) r13
            if (r13 == 0) goto L_0x0207
            r15 = 134742016(0x8080000, float:4.092601E-34)
            r13.addFlags(r15)
        L_0x0207:
            r3.filterComponentList(r4, r12, r13)
            int r15 = r6.size()
            if (r15 != r11) goto L_0x0219
            java.lang.Object r15 = r6.get(r10)
            android.net.Uri r15 = (android.net.Uri) r15
            com.samsung.android.gallery.module.utils.ShareList.setShareSingleUri(r15)
        L_0x0219:
            java.lang.String r15 = r3.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r9)
            java.lang.String r5 = com.samsung.android.gallery.support.utils.Logger.getSimpleName((java.lang.Object) r4)
            r0.append(r5)
            java.lang.String r5 = "}\ntarget="
            r0.append(r5)
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.toString((android.content.Intent) r13)
            r0.append(r13)
            java.lang.String r13 = "\nchooser="
            r0.append(r13)
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.toString((android.content.Intent) r12)
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.d(r15, r13)
            android.content.Context r13 = r3.getContext()     // Catch:{ Exception -> 0x0262 }
            boolean r13 = com.samsung.android.gallery.widget.utils.SystemUi.supportPopoverUi(r13, r11)     // Catch:{ Exception -> 0x0262 }
            r15 = 0
            if (r13 == 0) goto L_0x0284
            android.graphics.RectF r13 = r3.getActivityBounds()     // Catch:{ Exception -> 0x0262 }
            android.graphics.Point r14 = r3.mPopoverAnchorPos     // Catch:{ Exception -> 0x0262 }
            android.app.ActivityOptions r14 = com.samsung.android.gallery.support.library.SeApiCompat.getPopoverActivityOptions(r13, r14)     // Catch:{ Exception -> 0x0262 }
            if (r14 == 0) goto L_0x0265
            android.os.Bundle r15 = r14.toBundle()     // Catch:{ Exception -> 0x0262 }
            goto L_0x0265
        L_0x0262:
            r0 = move-exception
            r12 = r0
            goto L_0x029d
        L_0x0265:
            r4.startActivity(r12, r15)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r12 = r3.TAG     // Catch:{ Exception -> 0x0262 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0262 }
            r14.<init>(r1)     // Catch:{ Exception -> 0x0262 }
            android.graphics.Point r15 = r3.mPopoverAnchorPos     // Catch:{ Exception -> 0x0262 }
            r14.append(r15)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r15 = ", activityBounds="
            r14.append(r15)     // Catch:{ Exception -> 0x0262 }
            r14.append(r13)     // Catch:{ Exception -> 0x0262 }
            java.lang.String r13 = r14.toString()     // Catch:{ Exception -> 0x0262 }
            com.samsung.android.gallery.support.utils.Log.d(r12, r13)     // Catch:{ Exception -> 0x0262 }
            goto L_0x02a7
        L_0x0284:
            if (r14 == 0) goto L_0x0293
            boolean r13 = r3.mFromViewer     // Catch:{ Exception -> 0x0262 }
            if (r13 == 0) goto L_0x0293
            java.lang.String r13 = "app_transition_view"
            java.lang.Object r13 = r3.getParameter(r13)     // Catch:{ Exception -> 0x0262 }
            r15 = r13
            android.view.View r15 = (android.view.View) r15     // Catch:{ Exception -> 0x0262 }
        L_0x0293:
            if (r15 == 0) goto L_0x0299
            r3.startActivityWithShareElement(r4, r12, r15)     // Catch:{ Exception -> 0x0262 }
            goto L_0x02a7
        L_0x0299:
            r4.startActivity(r12)     // Catch:{ Exception -> 0x0262 }
            goto L_0x02a7
        L_0x029d:
            java.lang.String r13 = r3.TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r2)
            A.a.s(r12, r14, r13)
        L_0x02a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.externals.ShareViaCmd.performShareVia(android.content.Context, com.samsung.android.gallery.module.data.MediaItem[], com.samsung.android.gallery.module.share.ShareComponent):void");
    }

    private void performUnlimitedShareVia(Context context, MediaItem[] mediaItemArr) {
        Intent intent;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (MediaItemUtil.isSharableItem(mediaItem, true)) {
                arrayList.add(createShareData(mediaItem));
            }
        }
        Log.d(this.TAG, "performUnlimitedShareVia" + Logger.vt(Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)));
        ShareList.setShareList(arrayList);
        try {
            Intent intent2 = new Intent("com.samsung.android.gallery.action.UNLIMITED_SHARE");
            intent2.putExtra("share-version", 1);
            intent2.putExtra("share-from", "gallery");
            intent2.putExtra("share-count", arrayList.size());
            intent2.putExtra("share-projection", new String[]{"share_uri", "share_bucket_id", "share_bucket_name"});
            if (supportChooserReceiver()) {
                registerChosenComponentReceiver(context);
                intent = Intent.createChooser(intent2, context.getString(R.string.share_more_than_500_items), createPendingIntent(context).getIntentSender());
            } else {
                intent = Intent.createChooser(intent2, context.getString(R.string.share_more_than_500_items));
            }
            getContext().startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("performUnlimitedShareVia failed e="), this.TAG);
        }
    }

    private void registerChosenComponentReceiver(Context context) {
        if (getBlackboard().read("data://user/ChosenComponentReceiver") == null) {
            AnonymousClass1 r0 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && "CHOSEN_COMPONENT_RECEIVE_ACTION".equalsIgnoreCase(intent.getAction())) {
                        Log.d(ShareViaCmd.this.TAG, "ChosenComponentReceiver::onReceived");
                        ShareViaCmd.this.getBlackboard().erase("data://user/selection/ShareVia");
                        ShareViaCmd.this.getBlackboard().postEvent(EventMessage.obtain(1003));
                        ShareViaCmd.this.getBlackboard().pop("data://user/ChosenComponentReceiver");
                        AndroidCompat.unregisterReceiver(context, this);
                    }
                }
            };
            AndroidCompat.registerReceiver(context, r0, new IntentFilter("CHOSEN_COMPONENT_RECEIVE_ACTION"));
            getBlackboard().publish("data://user/ChosenComponentReceiver", r0);
        }
    }

    private boolean supportChooserReceiver() {
        return Features.isEnabled(Features.USE_CHOSEN_COMPONENT_RECEIVER);
    }

    private boolean unsupportableShareSheetItem(MediaItem mediaItem) {
        if (mediaItem.getFileId() > 0 || mediaItem.getExtra(ExtrasID.ORIGINAL_URI) != null) {
            return false;
        }
        return true;
    }

    private boolean updateChooserIntent(MediaItem[] mediaItemArr, Intent intent) {
        boolean z = PreferenceFeatures.OneUi40.SUPPORT_SHARE_SHEET;
        boolean z3 = false;
        for (MediaItem mediaItem : mediaItemArr) {
            if (!z3 && ((mediaItem.isVideo() && mediaItem.isFHD()) || MediaItemUtil.isSamsungHdr10plusVideo(mediaItem))) {
                z3 = true;
            }
            if (z && unsupportableShareSheetItem(mediaItem)) {
                Log.i(this.TAG, "shareSheet unsupported " + MediaItemUtil.getDebugLog(mediaItem));
                z = false;
            }
        }
        if (z) {
            z = addShareSheetData(intent, mediaItemArr);
        }
        if (z3) {
            intent.putExtra("sem_extra_chooser_convert_video_option", true);
        }
        return z;
    }

    public String getAnalyticsDetail() {
        if (this.mFromRemasterViewer) {
            return RemasterType.getRemasterTypeEventDetail(MediaItemSuggest.getRevitalizedResultType(this.mBaseItem), this.mLocationKey, false);
        }
        return super.getAnalyticsDetail();
    }

    public Long getAnalyticsValue() {
        long j2 = (long) (this.mImageCount + this.mVideoCount);
        if (j2 > 0) {
            return Long.valueOf(j2);
        }
        return null;
    }

    public String getEventId() {
        if (getAnalyticsValue() == null) {
            return AnalyticsEventId.EVENT_MENU_SHARE.toString();
        }
        if (this.mFromRemasterViewer) {
            if (LocationKey.isRemasterSingle(this.mLocationKey)) {
                return AnalyticsEventId.EVENT_REMASTER_VIEWER_SHARE_CLICK.toString();
            }
            return AnalyticsEventId.EVENT_SUGGEST_SHARE.toString();
        } else if (LocationKey.isAllDayTimeLapse(this.mLocationKey) || LocationKey.isLongExposure(this.mLocationKey)) {
            return AnalyticsEventId.EVENT_SUGGEST_SHARE.toString();
        } else {
            if (this.mFromViewer) {
                return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_SHARE.toString();
            }
            return AnalyticsEventId.EVENT_BOTTOM_MENU_SHARE.toString();
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem;
        MediaItem[] mediaItemArr = objArr[0];
        ShareComponent shareComponent = objArr[1];
        String locationKey = eventContext.getLocationKey();
        this.mLocationKey = locationKey;
        if (!TextUtils.isEmpty(locationKey)) {
            if (LocationKey.isRevitalizationView(this.mLocationKey)) {
                this.mFromRemasterViewer = true;
            } else if (LocationKey.isTimeline(this.mLocationKey)) {
                this.mFromTimeline = true;
            } else {
                this.mFromViewer = LocationKey.isContentViewer(this.mLocationKey);
            }
        }
        this.mPopoverAnchorPos = (Point) getBlackboard().pop("data://user/shareViaAnchorPos");
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            getBlackboard().publish(CommandKey.DATA_REQUEST("data://user/selection/ShareVia"), (Object) null);
            return;
        }
        if (mediaItemArr.length == 1 && (mediaItem = mediaItemArr[0]) != null) {
            this.mIsImage = mediaItem.isImage();
            if (!this.mFromViewer) {
                int firstItemDataPositionFromSelected = eventContext.getFirstItemDataPositionFromSelected();
                this.mItemIndex = firstItemDataPositionFromSelected;
                if (firstItemDataPositionFromSelected == -1) {
                    this.mItemIndex = ((Integer) mediaItem.getExtra(ExtrasID.HOVER_DATA_POSITION, -1)).intValue();
                }
            }
        }
        if (!SdkConfig.atLeast(SdkConfig.GED.Q) || isConfigured(1) || !GroupItemLoader.hasShareGroupMedia(mediaItemArr)) {
            perform(mediaItemArr, shareComponent);
        } else {
            SimpleThreadPool.getInstance().execute(new c(this, mediaItemArr, shareComponent, 15));
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            String locationKey = eventContext.getLocationKey();
            if (eventContext.isSelectionMode() && (LocationKey.isAlbumPictures(locationKey) || LocationKey.isTimeline(locationKey))) {
                mediaItemArr = eventContext.getSelectedItems();
            } else if (LocationKey.isContentViewer(locationKey)) {
                mediaItemArr = objArr[0];
            } else {
                mediaItemArr = null;
            }
            MediaItem[] mediaItemArr2 = mediaItemArr;
            if (mediaItemArr2 != null) {
                EventContext eventContext2 = eventContext;
                executePppChecker(eventContext2, mediaItemArr2, new j(this, objArr, eventContext2, mediaItemArr2, System.currentTimeMillis()));
                return;
            }
        }
        super.onPreExecute(eventContext, objArr);
    }

    public void postAnalyticsLog() {
        if (this.mBaseItem == null || !AnalyticsEventId.EVENT_DETAIL_VIEW_FS_SHARE.toString().equals(getEventId())) {
            super.postAnalyticsLog();
        } else {
            AnalyticsLogger.getInstance().postCustomDimension(getScreenId(), getEventId(), getScreenLabel(), (Pair<String, String>[]) VuAnalytics.getViewerCustomDimensions(this.mBaseItem, getAnalyticsDetail()));
        }
    }

    public void startActivityWithShareElement(Context context, Intent intent, View view) {
        if (view == null) {
            context.startActivity(intent);
            return;
        }
        if (!SdkConfig.atLeast(SdkConfig.SEM.U) && (view instanceof PhotoView)) {
            ((PhotoView) view).ignoreSetAlphaOnce();
        }
        context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), androidx.core.util.Pair.create(view, "sem_shared_element_chooser_preview_image")).toBundle());
        view.setTransitionName((String) null);
        consumeExecuteListener((Object) null);
    }

    public void loadAdditionalExtraData(Intent intent) {
    }
}
