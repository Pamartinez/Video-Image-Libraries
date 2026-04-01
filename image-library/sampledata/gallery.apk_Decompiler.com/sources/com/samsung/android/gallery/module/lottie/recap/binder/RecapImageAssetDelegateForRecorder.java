package com.samsung.android.gallery.module.lottie.recap.binder;

import K3.C0404a;
import U5.b;
import android.graphics.Bitmap;
import com.samsung.android.gallery.module.graphics.BitmapPool;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import x0.C0325c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapImageAssetDelegateForRecorder implements C0325c {
    HashMap<Integer, ArrayList<Runnable>> mBitmapReleaser = new HashMap<>();
    HashMap<String, UsedImage> mBitmaps = new HashMap<>();
    AtomicInteger mFetchCounter = new AtomicInteger(0);
    HashMap<String, RecapImage> mImages;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UsedImage {
        Bitmap mBitmap;
        public int sceneIndex;
        public String sceneName;
    }

    public RecapImageAssetDelegateForRecorder(HashMap<String, RecapImage> hashMap) {
        this.mImages = hashMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: freeUnusedBitmap */
    public void lambda$fetchBitmap$1(String str, long j2) {
        Set<Map.Entry<String, UsedImage>> entrySet = this.mBitmaps.entrySet();
        ArrayList arrayList = new ArrayList();
        entrySet.forEach(new C0404a(j2, arrayList));
        arrayList.forEach(new b(28, this, str));
        if (!arrayList.isEmpty()) {
            BitmapPool.getInstance().clear();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$fetchBitmap$0(Integer num) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ long lambda$fetchBitmap$2(UsedImage usedImage) {
        return (long) usedImage.mBitmap.getAllocationByteCount();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$freeUnusedBitmap$3(long j2, ArrayList arrayList, Map.Entry entry) {
        if (((long) ((UsedImage) entry.getValue()).sceneIndex) < j2) {
            arrayList.add((String) entry.getKey());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$freeUnusedBitmap$4(String str, String str2) {
        UsedImage remove = this.mBitmaps.remove(str2);
        Log.i("RecapImageAssetDelegate", "freeUnusedBitmap", str2, str, remove.sceneName);
        BitmapUtils.putBitmap(remove.mBitmap);
    }

    private void putCache(RecapImage recapImage) {
        UsedImage usedImage = new UsedImage();
        usedImage.mBitmap = recapImage.mTargetBitmap;
        usedImage.sceneIndex = recapImage.sceneIndex;
        usedImage.sceneName = recapImage.sceneName;
        this.mBitmaps.put(recapImage.dataBindingKey, usedImage);
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.Object, java.util.function.ToLongFunction] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap fetchBitmap(x0.y r12) {
        /*
            r11 = this;
            java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage> r0 = r11.mImages
            java.lang.String r12 = r12.d
            java.lang.String r1 = "png"
            java.lang.String r2 = "jpg"
            java.lang.String r1 = r12.replace(r1, r2)
            java.lang.Object r0 = r0.get(r1)
            com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage r0 = (com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage) r0
            if (r0 == 0) goto L_0x00cb
            java.util.concurrent.atomic.AtomicInteger r1 = r11.mFetchCounter
            int r1 = r1.incrementAndGet()
            android.graphics.Bitmap r2 = r0.mTargetBitmap
            java.lang.String r3 = "fetchBitmap"
            java.lang.String r4 = "RecapImageAssetDelegate"
            if (r2 == 0) goto L_0x002e
            java.lang.String r11 = r0.sceneName
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r12}
            com.samsung.android.gallery.support.utils.Log.i(r4, r3, r11)
            android.graphics.Bitmap r11 = r0.mTargetBitmap
            return r11
        L_0x002e:
            java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder$UsedImage> r2 = r11.mBitmaps
            java.lang.String r5 = r0.dataBindingKey
            java.lang.Object r2 = r2.get(r5)
            com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder$UsedImage r2 = (com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder.UsedImage) r2
            if (r2 != 0) goto L_0x004c
            r0.loadBitmap()
            r11.putCache(r0)
            int r2 = r0.sceneIndex
            long r8 = (long) r2
            java.lang.String r7 = r0.sceneName
            int r2 = r0.delayForLastSceneImageFree
            if (r2 != 0) goto L_0x004e
            r11.lambda$fetchBitmap$1(r7, r8)
        L_0x004c:
            r6 = r11
            goto L_0x0071
        L_0x004e:
            if (r2 <= 0) goto L_0x004c
            int r2 = r2 / 30
            int r2 = r2 + r1
            int r2 = r2 + 1
            java.util.HashMap<java.lang.Integer, java.util.ArrayList<java.lang.Runnable>> r5 = r11.mBitmapReleaser
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.samsung.android.gallery.module.lottie.recap.binder.f r6 = new com.samsung.android.gallery.module.lottie.recap.binder.f
            r10 = 2
            r6.<init>(r10)
            java.lang.Object r2 = r5.computeIfAbsent(r2, r6)
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            H.b r5 = new H.b
            r10 = 5
            r6 = r11
            r5.<init>(r6, r7, r8, r10)
            r2.add(r5)
        L_0x0071:
            java.util.HashMap<java.lang.Integer, java.util.ArrayList<java.lang.Runnable>> r11 = r6.mBitmapReleaser
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r11 = r11.remove(r1)
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            if (r11 == 0) goto L_0x0088
            O3.l r1 = new O3.l
            r2 = 0
            r1.<init>(r2)
            r11.forEach(r1)
        L_0x0088:
            java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder$UsedImage> r11 = r6.mBitmaps
            java.util.Collection r11 = r11.values()
            java.util.stream.Stream r11 = r11.stream()
            com.samsung.android.gallery.module.lottie.recap.binder.g r1 = new com.samsung.android.gallery.module.lottie.recap.binder.g
            r1.<init>()
            java.util.stream.LongStream r11 = r11.mapToLong(r1)
            long r1 = r11.sum()
            r7 = 1024(0x400, double:5.06E-321)
            long r1 = r1 / r7
            long r1 = r1 / r7
            java.lang.String r11 = r0.sceneName
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "c:"
            r5.<init>(r7)
            java.util.HashMap<java.lang.String, com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder$UsedImage> r6 = r6.mBitmaps
            int r6 = r6.size()
            r5.append(r6)
            java.lang.String r6 = ",size:"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r12, r1}
            com.samsung.android.gallery.support.utils.Log.i(r4, r3, r11)
            android.graphics.Bitmap r11 = r0.mTargetBitmap
            return r11
        L_0x00cb:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "fetchBitmap fail : "
            java.lang.String r12 = r0.concat(r12)
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder.fetchBitmap(x0.y):android.graphics.Bitmap");
    }
}
