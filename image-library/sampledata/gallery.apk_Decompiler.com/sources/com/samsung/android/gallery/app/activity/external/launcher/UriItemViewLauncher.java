package com.samsung.android.gallery.app.activity.external.launcher;

import C3.C0391a;
import D3.i;
import D6.a;
import N2.j;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.download.DownloadHelper;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.remote.SConnectUtil;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UriItemViewLauncher extends QuickViewLauncher {
    Runnable mFailCallbackNoMessage;
    private final SubscriberListener mOnQuickViewDataChanged;

    public UriItemViewLauncher(Blackboard blackboard, LaunchIntent launchIntent, Runnable runnable, Runnable runnable2) {
        super(blackboard, launchIntent, runnable);
        C0391a aVar = new C0391a(4, this);
        this.mOnQuickViewDataChanged = aVar;
        this.mFailCallbackNoMessage = runnable2;
        this.mBlackboard.subscribe("command://event/QuickViewDataChanged", aVar);
    }

    private void addUnlockScreen(ArrayList<MediaItem> arrayList) {
        if (isFromLockScreen() && !isFromFlipCoverCamera()) {
            arrayList.add(MediaItemBuilder.createUnlockScreen());
        }
    }

    private ArrayList<Uri> findRemovedUri(ArrayList<Uri> arrayList, List<MediaItem> list) {
        ArrayList<Uri> arrayList2 = new ArrayList<>();
        Iterator<Uri> it = arrayList.iterator();
        while (it.hasNext()) {
            Uri next = it.next();
            if (next != null) {
                long j2 = UnsafeCast.toLong(next.getLastPathSegment(), -1);
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        arrayList2.add(next);
                        break;
                    } else if (j2 == list.get(i2).getFileId()) {
                        list.remove(i2);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        return arrayList2;
    }

    private int getPositionFromItemList(ArrayList<MediaItem> arrayList) {
        int i2;
        i iVar;
        if (isFromLockScreen()) {
            Uri uriData = this.mLaunchIntent.getUriData();
            long parseId = ContentUris.parseId(uriData);
            if (MediaUri.getInstance().isSecMediaUri(uriData.toString())) {
                iVar = new i(12);
            } else {
                iVar = new i(13);
            }
            i2 = 0;
            while (i2 < arrayList.size()) {
                MediaItem mediaItem = arrayList.get(i2);
                if (mediaItem == null || ((Long) iVar.apply(mediaItem)).longValue() != parseId) {
                    i2++;
                } else {
                    String str = this.TAG;
                    StringBuilder j2 = j.j(parseId, "getPositionFromItemList {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    j2.append(arrayList.size());
                    j2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    j2.append(i2);
                    j2.append("} ");
                    j2.append(MediaItemUtil.getSimpleLog(mediaItem));
                    Log.d(str, j2.toString());
                    return i2;
                }
            }
            String str2 = this.TAG;
            StringBuilder j3 = j.j(parseId, "getPositionFromItemList : fail {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            j3.append(arrayList.size());
            j3.append(",-1}");
            Log.e(str2, j3.toString());
        } else if (useUriList()) {
            i2 = this.mLaunchIntent.getItemPosition();
            Log.d(this.TAG, "getPositionFromItemList : {0," + arrayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        } else {
            i2 = 0;
        }
        if (i2 < arrayList.size()) {
            return i2;
        }
        ArrayList<Uri> uriList = getUriList();
        Log.e(this.TAG, "getPositionFromItemList : wrong position {0," + arrayList.size() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "} URI{" + uriList.size() + NumericEnum.SEP + StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, uriList.stream().limit(5).map(new i(14)).iterator()));
        return 0;
    }

    private ArrayList<Uri> getSConnectUri() {
        ArrayList<Uri> uris = SConnectUtil.getUris(this.mLaunchIntent.getIntent());
        if (uris == null || uris.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Uri> arrayList = new ArrayList<>();
        Iterator<Uri> it = uris.iterator();
        while (it.hasNext()) {
            Uri next = it.next();
            String authority = next.getAuthority();
            if (authority != null) {
                String substring = authority.substring(authority.indexOf(64) + 1);
                Uri.Builder buildUpon = next.buildUpon();
                arrayList.add(buildUpon.encodedAuthority("" + substring).build());
            }
        }
        return arrayList;
    }

    private ArrayList<Uri> getUriList() {
        Uri uri;
        ArrayList<Uri> arrayList = new ArrayList<>();
        if (isFromLockScreen() || isFromFlipCoverCamera() || useUriList()) {
            return this.mLaunchIntent.getUriList();
        }
        if (SConnectUtil.isSConnectIntent(this.mLaunchIntent.getIntent())) {
            return getSConnectUri();
        }
        Uri uriData = this.mLaunchIntent.getUriData();
        if (uriData != null) {
            if (DownloadHelper.isDownloadUri(uriData)) {
                uri = DownloadHelper.convertToMediaProviderUri(AppResources.getAppContext(), uriData);
            } else if (DownloadHelper.isIncludePendingStateUri(uriData)) {
                uri = DownloadHelper.removePendingArguments(uriData);
            } else {
                uri = null;
            }
            if (uri != null) {
                uriData = uri;
            }
        }
        arrayList.add(uriData);
        return arrayList;
    }

    private boolean isFromCamera() {
        return this.mLaunchIntent.isFromCamera();
    }

    private boolean isFromFlipCoverCamera() {
        if (!isFromCamera() || !FoldUtils.isFlipCoverScreen(getActivity())) {
            return false;
        }
        return true;
    }

    private boolean isFromLockScreen() {
        if (!this.mLaunchIntent.isFromLockScreen() || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$1(ArrayList arrayList, AtomicReference atomicReference, boolean z, Uri uri, int[] iArr, MediaItem[] mediaItemArr, CountDownLatch countDownLatch) {
        Object obj;
        int i2;
        try {
            if (PocFeatures.TBD.OPEN_IN_OTHER_WINDOW && this.mLaunchIntent.containsKey("QueryParams") && this.mItemLoader.loadQuickViewItemsByQueryParams(arrayList)) {
                String str = (String) this.mLaunchIntent.getExtra("QueryParams", "");
                QueryParams queryParams = (QueryParams) Blackboard.getApplicationInstance().pop(str);
                this.mBlackboard.publish(str, queryParams);
                Log.d(this.TAG, "loadQuickViewCommon#QueryParams", Integer.valueOf(arrayList.size()), queryParams);
                atomicReference.set(ArgumentsUtil.appendArgs((String) atomicReference.get(), "QueryParams", str));
            } else if (!this.mItemLoader.loadQuickViewUriItems(z, arrayList, getUriList())) {
                String str2 = this.TAG;
                Log.e(str2, "loadQuickViewCommon failed. no uri. referrer={" + Optional.ofNullable(getActivity()).map(new i(11)).orElse((Object) null) + "}");
                if (!"content://media/external/images/media".equals(uri.toString()) || !"image/*".equals(this.mLaunchIntent.getType())) {
                    Runnable runnable = this.mFailCallback;
                    if (runnable != null) {
                        runnable.run();
                    }
                } else {
                    Runnable runnable2 = this.mFailCallbackNoMessage;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
                countDownLatch.countDown();
                return;
            }
            sortQuickViewUriItemList(arrayList);
            addUnlockScreen(arrayList);
            if (isRestartedFromFlipCover()) {
                i2 = getPositionFromFlipCoverSavedData();
            } else {
                i2 = getPositionFromItemList(arrayList);
            }
            iArr[0] = i2;
            updateBestShot(arrayList);
            mediaItemArr[0] = (MediaItem) arrayList.get(iArr[0]);
            MediaItem[] mediaItemArr2 = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            String str3 = this.TAG;
            Log.d(str3, "loadQuickViewCommon {" + iArr[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItemArr2.length + "} " + MediaItemUtil.getDebugLog(mediaItemArr[0]));
            this.mBlackboard.publish("data://viewer_first_data", mediaItemArr[0]);
            loadThumbnailSync(mediaItemArr[0]);
            this.mBlackboard.publish(DataKey.DATA("location://quickView"), mediaItemArr2);
            countDownLatch.countDown();
        } catch (Exception e) {
            Activity activity = getActivity();
            String str4 = this.TAG;
            StringBuilder sb2 = new StringBuilder("loadQuickViewCommon failed {");
            if (activity != null) {
                obj = activity.getReferrer();
            } else {
                obj = "null";
            }
            sb2.append(obj);
            sb2.append("}");
            Log.e((CharSequence) str4, sb2.toString(), (Throwable) e);
            Utils.showToast((Context) activity, (int) R.string.access_denied);
            Runnable runnable3 = this.mFailCallbackNoMessage;
            if (runnable3 != null) {
                runnable3.run();
            }
            countDownLatch.countDown();
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        Bundle extras = this.mLaunchIntent.getIntent().getExtras();
        if (extras != null) {
            ArrayList arrayList = (ArrayList) extras.get("uriListData");
            if ((obj instanceof List) && arrayList != null && arrayList.size() > 0) {
                synchronized (arrayList) {
                    try {
                        Iterator<Uri> it = findRemovedUri(arrayList, new ArrayList((List) obj)).iterator();
                        while (it.hasNext()) {
                            arrayList.remove(it.next());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$sortQuickViewUriItemList$2(MediaItem mediaItem, MediaItem mediaItem2) {
        int i2 = ((mediaItem2.getDateTaken() - mediaItem.getDateTaken()) > 0 ? 1 : ((mediaItem2.getDateTaken() - mediaItem.getDateTaken()) == 0 ? 0 : -1));
        if (i2 > 0) {
            return 1;
        }
        if (i2 < 0) {
            return -1;
        }
        return 0;
    }

    private void sortQuickViewUriItemList(ArrayList<MediaItem> arrayList) {
        if (isFromLockScreen() || isFromFlipCoverCamera()) {
            arrayList.sort(new a(1));
        }
    }

    private void updateBestShot(ArrayList<MediaItem> arrayList) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            MediaItem mediaItem = arrayList.get(i2);
            if (mediaItem.getGroupMediaId() > 0) {
                arrayList.set(i2, this.mItemLoader.getBestItem(mediaItem));
            }
        }
    }

    private boolean useUriList() {
        return this.mLaunchIntent.useUriList();
    }

    public void destroy() {
        this.mBlackboard.unsubscribe("command://event/QuickViewDataChanged", this.mOnQuickViewDataChanged);
        super.destroy();
    }

    public void execute() {
        Uri uriData;
        boolean z;
        if (isRestartedFromFlipCover()) {
            uriData = getUriFromFlipCoverSavedData();
        } else {
            uriData = this.mLaunchIntent.getUriData();
        }
        Uri uri = uriData;
        long currentTimeMillis = System.currentTimeMillis();
        Log.d(this.TAG, "loadQuickViewCommon {" + Logger.getEncodedString((Object) uri) + "}");
        boolean z3 = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MediaItem[] mediaItemArr = new MediaItem[1];
        int[] iArr = new int[1];
        ArrayList arrayList = new ArrayList();
        if (isFromLockScreen() || isFromFlipCoverCamera()) {
            z = true;
        } else {
            z = false;
        }
        AtomicReference atomicReference = new AtomicReference("location://quickView");
        SimpleThreadPool.getInstance().execute(new E3.i(this, arrayList, atomicReference, z, uri, iArr, mediaItemArr, countDownLatch));
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Log.e(this.TAG, "loadQuickViewCommon time-out");
            if (mediaItemArr[0] == null) {
                MediaItem loadQuickViewUriItem = this.mItemLoader.loadQuickViewUriItem(this.mLaunchIntent.getUriData(), new ArrayList());
                mediaItemArr[0] = loadQuickViewUriItem;
                if (loadQuickViewUriItem == null && arrayList.size() > 0) {
                    mediaItemArr[0] = (MediaItem) arrayList.get(0);
                }
                MediaItem mediaItem = mediaItemArr[0];
                if (mediaItem != null) {
                    this.mBlackboard.publish("data://viewer_first_data", mediaItem);
                    this.mBlackboard.publish(DataKey.DATA("location://quickView"), arrayList.toArray(new MediaItem[0]));
                }
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("loadQuickViewCommon failed {");
                C0086a.A(sb2, arrayList, "} ");
                sb2.append(MediaItemUtil.getSimpleLog(mediaItemArr[0]));
                Log.e(str, sb2.toString());
            }
        }
        String str2 = this.TAG;
        StringBuilder sb3 = new StringBuilder("loadQuickViewCommon ViewerData{");
        if (mediaItemArr[0] == null) {
            z3 = false;
        }
        sb3.append(z3);
        sb3.append("} +");
        sb3.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d(str2, sb3.toString());
        if (mediaItemArr[0] != null) {
            new VuLauncher(this.mBlackboard).requestBitmapUrgent().addTrueArgument(isFromLockScreen(), "with_group").launch((String) atomicReference.get(), iArr[0], mediaItemArr);
        }
    }

    public boolean isHttpUri() {
        Uri uri;
        if (isRestartedFromFlipCover()) {
            uri = getUriFromFlipCoverSavedData();
        } else {
            uri = this.mLaunchIntent.getUriData();
        }
        return UriItemLoader.isHttpUri(uri);
    }
}
