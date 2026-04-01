package com.samsung.android.gallery.module.bgm;

import D7.g;
import android.text.TextUtils;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmInfo;
import com.samsung.android.gallery.module.bgm.bgmlist.dynamicview.BgmCompat;
import com.samsung.android.gallery.module.bgm.bgmlist.dynamicview.BgmFactory;
import com.samsung.android.gallery.module.bgm.provider.BgmProviderFactory;
import com.samsung.android.gallery.module.bgm.provider.IBgmProvider;
import com.samsung.android.gallery.module.bgm.provider.IProviderCallback;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BgmUriProviderManager {
    private static volatile BgmUriProviderManager sInstance;
    protected BgmCompat mBgmCompat = BgmFactory.getInstance();
    protected final HashMap<String, BgmInfo> mBgmInfoMap = new HashMap<>();
    protected final ArrayList<Consumer<Boolean>> mBgmListeners = new ArrayList<>();
    IBgmProvider mBgmProvider;
    IProviderCallback mCallBack = new IProviderCallback() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onConnected$0() {
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : BgmUriProviderManager.this.getBgmList()) {
                try {
                    List<String> bgmUri = BgmUriProviderManager.this.mBgmProvider.getBgmUri(str.trim().toLowerCase().replaceAll(" ", "_"));
                    if (bgmUri.size() >= 2) {
                        int size = bgmUri.size();
                        int i2 = size - 1;
                        BgmInfo bgmInfo = new BgmInfo();
                        bgmInfo.add(bgmUri.get(0));
                        for (int i7 = 1; i7 < size - 2; i7++) {
                            bgmInfo.add(bgmUri.get(i7));
                        }
                        bgmInfo.add(bgmUri.get(i2));
                        BgmUriProviderManager.this.mBgmInfoMap.put(str, bgmInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Log.d("BgmUriProviderService", "bgm loaded {" + BgmUriProviderManager.this.mBgmInfoMap.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            ThreadUtil.postOnUiThread(new g(9, BgmUriProviderManager.this));
            BgmUriProviderManager.this.unbind();
        }

        public void onConnected() {
            SimpleThreadPool.getInstance().execute(new a(0, this));
        }

        public void onDisconnected() {
        }
    };
    protected final AtomicBoolean mLoaded = new AtomicBoolean(false);
    protected final AtomicBoolean mServiceRequested = new AtomicBoolean(false);

    private void bind() {
        if (this.mBgmProvider != null) {
            unbind();
            Log.w("BgmUriProviderService", "unbind previous connection");
        }
        IBgmProvider create = BgmProviderFactory.create();
        this.mBgmProvider = create;
        create.setCallback(this.mCallBack);
        this.mBgmProvider.bind();
    }

    /* access modifiers changed from: private */
    public List<String> getBgmList() {
        return this.mBgmCompat.getBgmList();
    }

    private String getBgmName(int i2, int i7) {
        return this.mBgmCompat.getBgmName(i2, i7);
    }

    public static BgmUriProviderManager getInstance() {
        if (sInstance == null) {
            synchronized (BgmUriProviderManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new BgmUriProviderManager();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public void onLoadedBgm() {
        this.mLoaded.set(true);
        Iterator<Consumer<Boolean>> it = this.mBgmListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Boolean.TRUE);
        }
        this.mBgmListeners.clear();
    }

    /* access modifiers changed from: private */
    public void unbind() {
        IBgmProvider iBgmProvider = this.mBgmProvider;
        if (iBgmProvider != null) {
            iBgmProvider.setCallback((IProviderCallback) null);
            this.mBgmProvider.unbind();
            this.mBgmProvider = null;
        }
        Log.d("BgmUriProviderService", "unbindService");
    }

    public BgmOptions createBgmOptions(int i2, int i7, int i8) {
        return createBgmOptions("", i2, i7, i8);
    }

    public boolean isLoaded() {
        return this.mLoaded.get();
    }

    public void registerListener(Consumer<Boolean> consumer) {
        if (!this.mBgmListeners.contains(consumer)) {
            this.mBgmListeners.add(consumer);
        }
    }

    public void request() {
        if (this.mLoaded.get()) {
            onLoadedBgm();
        } else if (!this.mServiceRequested.getAndSet(true)) {
            bind();
        }
    }

    public void unregisterListener(Consumer<Boolean> consumer) {
        this.mBgmListeners.remove(consumer);
    }

    private BgmOptions createBgmOptions(String str, int i2, int i7, int i8) {
        if (TextUtils.isEmpty(str)) {
            str = getBgmName(i7, i8);
        }
        BgmInfo bgmInfo = this.mBgmInfoMap.get(str);
        if (bgmInfo != null) {
            return new BgmOptionBuilder().setBgmName(str).setDuration(i2).setBgmInfo(bgmInfo).build();
        }
        Log.w((CharSequence) "BgmUriProviderService", "bgm is not exist", str, Integer.valueOf(i7), Integer.valueOf(i8));
        return null;
    }
}
