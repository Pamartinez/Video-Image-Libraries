package com.samsung.android.gallery.module.bgm;

import A4.L;
import B8.d;
import Da.f;
import F8.a;
import F8.b;
import F8.c;
import android.net.Uri;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmCache;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmInfo;
import com.samsung.android.gallery.module.bgm.provider.BgmProviderFactory;
import com.samsung.android.gallery.module.bgm.provider.IBgmProvider;
import com.samsung.android.gallery.module.bgm.provider.IProviderCallback;
import com.samsung.android.gallery.module.bgm.updater.AbsUpdater;
import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.bgm.updater.ProviderUpdater;
import com.samsung.android.gallery.module.bgm.updater.ServiceUpdater;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmUriService implements BgmUpdateListener {
    private final boolean USE_CONTENT_PROVIDER;
    protected final BgmCache mBgmCache;
    IBgmProvider mBgmProvider;
    protected BgmUpdateListener mBgmUpdateListener;
    private final PendingThreadHandler<Runnable> mDownloadHandler;
    protected final AtomicBoolean mIsDestroyed;
    IProviderCallback mProviderCallback;
    protected final AtomicBoolean mProviderConnected;
    protected AbsUpdater mUpdater;
    /* access modifiers changed from: private */
    public final PendingThreadHandler<Runnable> mUriServiceHandler;

    public BgmUriService() {
        boolean z;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider) || !Features.isEnabled(Features.SUPPORT_BGM_PICKER_WITH_CONTENT_PROVIDER)) {
            z = false;
        } else {
            z = true;
        }
        this.USE_CONTENT_PROVIDER = z;
        this.mBgmCache = BgmCache.getInstance();
        this.mProviderConnected = new AtomicBoolean();
        this.mIsDestroyed = new AtomicBoolean();
        this.mUriServiceHandler = new PendingThreadHandler<>("BgmUriService");
        this.mDownloadHandler = new PendingThreadHandler<>("BgmDownload");
        this.mProviderCallback = new IProviderCallback() {
            public void onConnected() {
                BgmUriService.this.mUriServiceHandler.post(new c(BgmUriService.this, 0));
            }

            public void onDisconnected() {
                Log.d("BgmUriService", "onDisconnected");
            }
        };
        initUpdater();
        bindProvider();
        Log.d("BgmUriService", "create Updater", this.mUpdater);
    }

    private void bindProvider() {
        IBgmProvider create = BgmProviderFactory.create();
        this.mBgmProvider = create;
        create.setCallback(this.mProviderCallback);
        this.mBgmProvider.bind();
    }

    private BgmOptions createBgmOption(String str, int i2, BgmInfo bgmInfo) {
        return new BgmOptionBuilder().setBgmName(str).setDuration(i2).setBgmInfo(bgmInfo).build();
    }

    /* access modifiers changed from: private */
    /* renamed from: download */
    public boolean lambda$requestDownload$1(String str) {
        try {
            this.mBgmProvider.downloadBgm(str);
            return true;
        } catch (Exception e) {
            Log.e("BgmUriService", "fail to downloadInternal");
            e.printStackTrace();
            onDownloaded(false, str, (ArrayList<Uri>) null);
            return false;
        }
    }

    private void initUpdater() {
        AbsUpdater absUpdater;
        if (this.USE_CONTENT_PROVIDER) {
            absUpdater = new ProviderUpdater(this);
        } else {
            absUpdater = new ServiceUpdater(this);
        }
        this.mUpdater = absUpdater;
        absUpdater.open();
    }

    public static boolean isSupportingFormat(String str) {
        if (str == null) {
            return false;
        }
        Locale locale = Locale.US;
        if (str.toLowerCase(locale).endsWith(".mp3") || str.toLowerCase(locale).endsWith(".3gp") || str.toLowerCase(locale).endsWith(".aac") || str.toLowerCase(locale).endsWith(".ogg") || str.toLowerCase(locale).endsWith(".3ga") || str.toLowerCase(locale).endsWith(".3gpp") || str.toLowerCase(locale).endsWith(".m4a") || str.toLowerCase(locale).endsWith(".amr")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDownloaded$3(boolean z, String str, ArrayList arrayList) {
        Optional.ofNullable(this.mBgmUpdateListener).ifPresent(new b(z, str, arrayList, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDownloadedAll$5(boolean z) {
        Optional.ofNullable(this.mBgmUpdateListener).ifPresent(new L(z, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUpdated$7(ArrayList arrayList) {
        Optional.ofNullable(this.mBgmUpdateListener).ifPresent(new d(arrayList, 3));
    }

    /* access modifiers changed from: private */
    public void onConnected() {
        if (this.mBgmProvider != null && !this.mIsDestroyed.get()) {
            this.mUpdater.bgmProviderConnected(this.mBgmProvider);
            this.mProviderConnected.set(true);
            this.mUriServiceHandler.setPrepared();
            this.mDownloadHandler.setPrepared();
        }
    }

    private BgmInfo queryBgmInfo(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            List<String> bgmUri = this.mBgmProvider.getBgmUri(str.trim().toLowerCase().replaceAll(" ", "_"));
            if (!bgmUri.isEmpty()) {
                BgmInfo bgmInfo = new BgmInfo();
                bgmInfo.addFileInfo(bgmUri);
                this.mBgmCache.addBgmInfo(str, bgmInfo);
                Integer valueOf = Integer.valueOf(bgmUri.size());
                Log.d("BgmUriService", "queryBgmInfo", str, valueOf, "+" + (System.currentTimeMillis() - currentTimeMillis));
                return bgmInfo;
            }
            Log.e("BgmUriService", "queryBgmInfo failed for ".concat(str));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void unbindProvider() {
        this.mBgmProvider.unbind();
        this.mBgmProvider.setCallback((IProviderCallback) null);
        this.mBgmProvider = null;
        Log.d("BgmUriService", "unbindService");
    }

    private void waitProviderConnect() {
        if (!this.mProviderConnected.get()) {
            int i2 = 0;
            while (i2 < 10) {
                if (!this.mProviderConnected.get()) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i2++;
                } else {
                    return;
                }
            }
            Log.e("BgmUriService", "waitServiceConnect failed");
        }
    }

    public void addBgmInfo(String str, ArrayList<Uri> arrayList, String str2) {
        this.mBgmCache.addBgmInfo(str, arrayList, str2);
    }

    public void cancelDownload() {
        this.mDownloadHandler.cancel();
    }

    public BgmOptions createBgmOptionsSync(String str, int i2) {
        ThreadUtil.assertBgThread("createBgmOptionsSync should run on background thread");
        long currentTimeMillis = System.currentTimeMillis();
        if (!isPrepared(str)) {
            waitProviderConnect();
            if (this.mProviderConnected.get() && !isPrepared(str)) {
                queryBgmInfo(str);
            }
        }
        Boolean valueOf = Boolean.valueOf(isPrepared(str));
        Boolean valueOf2 = Boolean.valueOf(isDownloaded(str));
        Log.d("BgmUriService", "createBgmOptionsSync", str, valueOf, valueOf2, "+" + (System.currentTimeMillis() - currentTimeMillis));
        if (isPrepared(str)) {
            return createBgmOption(str, i2, this.mBgmCache.getBgmInfo(str));
        }
        return null;
    }

    public void destroy() {
        unbindProvider();
        this.mProviderConnected.set(false);
        this.mIsDestroyed.set(true);
        this.mUriServiceHandler.release();
        this.mDownloadHandler.release();
        this.mUpdater.close();
        this.mBgmUpdateListener = null;
    }

    public void downloadAllBgm() {
        try {
            if (this.USE_CONTENT_PROVIDER) {
                this.mBgmProvider.downloadAllBgm();
            }
            Log.d("BgmUriService", "downloadAll requested");
        } catch (Exception e) {
            Log.e((CharSequence) "BgmUriService", "fail to downloadAll", e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Uri> getUris(String str) {
        return this.mBgmCache.getUris(str);
    }

    public ArrayList<Uri> getUrisSync(String str) {
        ArrayList<Uri> arrayList;
        BgmInfo bgmInfo = this.mBgmCache.getBgmInfo(str);
        if (bgmInfo != null) {
            arrayList = bgmInfo.getUris();
        } else {
            arrayList = new ArrayList<>();
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        waitProviderConnect();
        if (this.mProviderConnected.get()) {
            queryBgmInfo(str);
        }
        BgmInfo bgmInfo2 = this.mBgmCache.getBgmInfo(str);
        if (bgmInfo2 != null) {
            return bgmInfo2.getUris();
        }
        return new ArrayList<>();
    }

    public boolean isAllDownloaded() {
        return this.mUpdater.isAllDownloaded();
    }

    public boolean isDownloaded(String str) {
        return this.mUpdater.isDownloaded(str);
    }

    public boolean isDownloading(String str) {
        return this.mUpdater.isDownloading(str);
    }

    public boolean isPrepared(String str) {
        return this.mBgmCache.isPrepared(str);
    }

    public void onDownloaded(boolean z, String str, ArrayList<Uri> arrayList) {
        ThreadUtil.runOnUiThread(new a((Object) this, z, (Object) str, (Object) arrayList, 0));
    }

    public void onDownloadedAll(boolean z) {
        ThreadUtil.runOnUiThread(new B4.c((Object) this, z, 6));
    }

    public void onUpdated(ArrayList<String> arrayList) {
        ThreadUtil.runOnUiThread(new f(11, this, arrayList));
    }

    public void requestDownload(String str) {
        if (this.mBgmCache.getBgmInfo(str) != null) {
            onDownloaded(true, str, (ArrayList<Uri>) null);
        } else {
            this.mDownloadHandler.enqueue(new f(12, this, str));
        }
    }

    public void setBgmUpdateListener(BgmUpdateListener bgmUpdateListener) {
        this.mBgmUpdateListener = bgmUpdateListener;
    }
}
