package com.samsung.android.gallery.support.library.v0.media;

import A8.C0545b;
import N2.j;
import android.os.Build;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.MediaResourceHelperCompat;
import com.samsung.android.media.SemMediaResourceHelper;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemMediaResourceHelperCompat extends MediaResourceHelperCompat {
    /* access modifiers changed from: private */
    public final Object LOCK = new Object();
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, ArrayList<Object>> mMap = new ConcurrentHashMap<>();
    private final SemMediaResourceHelper.ResourceInfoChangedListener mOnResourceListener = new SemMediaResourceHelper.ResourceInfoChangedListener() {
        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$notifyListeners$0(int i2, int i7, String str, ArrayList arrayList) {
            if (C0212a.i(arrayList, 1) != null) {
                throw new ClassCastException();
            }
        }

        private void notifyListeners(int i2, int i7, int i8) {
            String str;
            StringBuilder sb2 = new StringBuilder("ResourceInfoChangedListener {");
            if (i2 == 1) {
                str = "add";
            } else {
                str = "remove";
            }
            sb2.append(str);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(i7);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(i8);
            sb2.append("}");
            Log.i("SemMediaResourceHelperCompat", sb2.toString());
            synchronized (SemMediaResourceHelperCompat.this.LOCK) {
                SemMediaResourceHelperCompat.this.mMap.forEach(new a(i2, i8));
            }
        }

        public void onAdd(ArrayList<SemMediaResourceHelper.MediaResourceInfo> arrayList) {
            int i2;
            if (arrayList != null) {
                i2 = arrayList.size();
            } else {
                i2 = -1;
            }
            notifyListeners(1, i2, SemMediaResourceHelperCompat.this.getUsedSize());
        }

        public void onError(SemMediaResourceHelper semMediaResourceHelper) {
            Log.e("SemMediaResourceHelperCompat", "ResourceInfoChangedListener onError ");
        }

        public void onRemove(ArrayList<SemMediaResourceHelper.MediaResourceInfo> arrayList) {
            int i2;
            if (arrayList != null) {
                i2 = arrayList.size();
            } else {
                i2 = -1;
            }
            notifyListeners(2, i2, SemMediaResourceHelperCompat.this.getUsedSize());
        }
    };
    private SemMediaResourceHelper mSemMediaResourceHelper;

    private SemMediaResourceHelper createMediaResourceHelper() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            SemMediaResourceHelper createInstance = SemMediaResourceHelper.createInstance(2, true);
            if (Build.VERSION.SEM_INT >= 2617) {
                createInstance.setResourcePriority(0);
            }
            Log.i("SemMediaResourceHelperCompat", "createMediaResourceHelper +" + (System.currentTimeMillis() - currentTimeMillis));
            return createInstance;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("createMediaResourceHelper failed e="), "SemMediaResourceHelperCompat");
            return null;
        }
    }

    private int getListenerCount() {
        int sum;
        synchronized (this.LOCK) {
            sum = this.mMap.values().stream().mapToInt(new C0545b(14)).sum();
        }
        return sum;
    }

    private synchronized SemMediaResourceHelper getMediaResourceHelper() {
        try {
            if (this.mSemMediaResourceHelper == null) {
                this.mSemMediaResourceHelper = createMediaResourceHelper();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mSemMediaResourceHelper;
    }

    private void onDestroyMediaResourceHelper() {
        Log.i("SemMediaResourceHelperCompat", "onDestroyMediaResourceHelper");
        SemMediaResourceHelper semMediaResourceHelper = this.mSemMediaResourceHelper;
        this.mSemMediaResourceHelper = null;
        if (semMediaResourceHelper != null) {
            try {
                semMediaResourceHelper.setResourceInfoChangedListener((SemMediaResourceHelper.ResourceInfoChangedListener) null);
            } catch (IllegalStateException e) {
                j.t(e, new StringBuilder("onDestroyMediaResourceHelper#listener failed e="), "SemMediaResourceHelperCompat");
            }
            semMediaResourceHelper.release();
        }
    }

    public int getUsedSize() {
        SemMediaResourceHelper mediaResourceHelper = getMediaResourceHelper();
        if (mediaResourceHelper != null) {
            try {
                ArrayList mediaResourceInfo = mediaResourceHelper.getMediaResourceInfo(2);
                if (mediaResourceInfo != null) {
                    return mediaResourceInfo.size();
                }
                return 0;
            } catch (Exception e) {
                j.D(e, new StringBuilder("getUsedSize failed e="), "SemMediaResourceHelperCompat");
            }
        }
        return 0;
    }

    public void release(String str) {
        synchronized (this.LOCK) {
            try {
                if (this.mMap.remove(str) != null) {
                    int listenerCount = getListenerCount();
                    Log.e("SemMediaResourceHelperCompat", "release executed {" + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + listenerCount + "}");
                    if (listenerCount == 0) {
                        onDestroyMediaResourceHelper();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
