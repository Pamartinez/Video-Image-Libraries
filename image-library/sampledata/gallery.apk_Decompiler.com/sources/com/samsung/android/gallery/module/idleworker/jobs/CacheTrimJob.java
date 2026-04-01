package com.samsung.android.gallery.module.idleworker.jobs;

import A4.C0383s;
import A8.C0545b;
import Ba.f;
import D3.b;
import O9.a;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RuntimeCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CacheTrimJob extends IdleWorkerJob {
    int mRetryCount;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RemainedTask {
        public static boolean exists() {
            return !TextUtils.isEmpty(load());
        }

        public static String load() {
            return GalleryPreference.getInstanceCache().loadString("DiskCacheTrimIndex", "");
        }

        public static void save(List<Integer> list) {
            GalleryPreference.getInstanceCache().saveState("DiskCacheTrimIndex", StringCompat.joinText((CharSequence) GlobalPostProcInternalPPInterface.SPLIT_REGEX, list));
        }
    }

    public CacheTrimJob(int i2, IdleWorkerJob.Type type) {
        super(i2, type);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$execute2$0(int i2) {
        return !CacheManager.hasChild(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute2$1(List list, List list2, List list3, ArrayList arrayList, String str, Long l) {
        int i2 = UnsafeCast.toInt(str, 0);
        if (i2 > 0) {
            long max = CacheManager.getInstance().max(i2);
            if (l.longValue() < Math.round(((double) max) * 0.9d)) {
                int i7 = (i2 / 100) - 1;
                if (i7 == 0) {
                    list.remove(list2);
                } else if (i7 == 5) {
                    list.remove(list3);
                }
                arrayList.add(i2 + "=" + Logger.toMegaBytes(l.longValue()) + "(" + Logger.toMegaBytes(max) + ")");
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$execute2$2(long j2, ArrayList arrayList, ArrayList arrayList2, List list) {
        if (System.currentTimeMillis() < j2) {
            ArrayList<Integer> trim = CacheManager.getInstance().trim(j2, list);
            Objects.requireNonNull(trim);
            arrayList.removeIf(new a(trim, 2));
            RemainedTask.save(arrayList);
            arrayList2.addAll(trim);
        }
    }

    public static void log(String str, String str2) {
        Log.d(str, str2);
    }

    public long duration() {
        if (RemainedTask.exists()) {
            return 1000;
        }
        return 43200000;
    }

    public void execute(Context context) {
        execute2(context);
    }

    public void execute2(Context context) {
        List list;
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = 60000 + currentTimeMillis;
        String load = RemainedTask.load();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (TextUtils.isEmpty(load)) {
            this.mRetryCount = 0;
            List<Integer> listOf = CacheManager.getInstance().listOf(0);
            List<Integer> listOf2 = CacheManager.getInstance().listOf(5);
            list = (List) Stream.of(new List[]{listOf, listOf2, (List) IntStream.range(2, 9).filter(new b(3)).boxed().collect(Collectors.toList())}).collect(Collectors.toList());
            ArrayList arrayList3 = new ArrayList();
            File cacheDir = context.getCacheDir();
            if (cacheDir != null) {
                RuntimeCompat.du(cacheDir.getPath() + File.separator + "?0" + (listOf.get(listOf.size() - 1).intValue() % 10)).forEach(new p9.a(list, listOf, listOf2, arrayList3));
            }
            log(this.TAG, "execute2#skip=[" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList3) + "] +" + (System.currentTimeMillis() - currentTimeMillis));
            list.forEach(new f4.a(arrayList, 11));
        } else {
            this.mRetryCount++;
            Arrays.stream(load.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).mapToInt(new C0545b(3)).forEach(new C0383s(3, arrayList));
            list = List.of(new ArrayList(arrayList));
        }
        log(this.TAG, "execute2#start" + Logger.vt(StringCompat.toString(arrayList), Long.valueOf(currentTimeMillis)));
        list.forEach(new f(j2, arrayList, arrayList2));
        log(this.TAG, "execute2#finish" + Logger.vt(StringCompat.toString(arrayList), StringCompat.toString(arrayList2), Long.valueOf(currentTimeMillis)));
    }

    public boolean wantsReschedule() {
        if (this.mRetryCount >= 3 || !RemainedTask.exists()) {
            return super.wantsReschedule();
        }
        String str = this.TAG;
        Log.d(str, "reschedule#" + this.mRetryCount + ",[" + RemainedTask.load() + "]");
        return true;
    }
}
