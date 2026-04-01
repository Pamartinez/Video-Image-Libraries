package com.samsung.android.gallery.module.lottie.recorder;

import A.a;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RandomNumber;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BgmProvider {
    private static final ArrayList<EffectTheme> sTheme = new ArrayList<EffectTheme>() {
        {
            add(EffectTheme.Happy);
            add(EffectTheme.Lounge);
            add(EffectTheme.Relaxing);
            add(EffectTheme.Upbeat);
            add(EffectTheme.Sentimental);
            add(EffectTheme.Lovely);
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BgmInfo {
        long duration;
        List<Uri> uris = new ArrayList();
    }

    private BgmInfo createBgmInfo(BgmOptions bgmOptions, long j2) {
        BgmInfo bgmInfo = new BgmInfo();
        bgmInfo.uris.add(bgmOptions.getBgmOption(0).uri);
        bgmInfo.uris.add(bgmOptions.getBgmOption(bgmOptions.size() - 1).uri);
        long j3 = (long) (bgmOptions.getBgmOption(0).duration + bgmOptions.getBgmOption(bgmOptions.size() - 1).duration);
        int i2 = 1;
        while (true) {
            if (i2 >= bgmOptions.size() - 1) {
                i2 = 1;
            }
            BgmOptions.BgmOption bgmOption = bgmOptions.getBgmOption(i2);
            StringBuilder o2 = C0086a.o(i2, "prepared music uri [", "] ");
            o2.append(bgmOption.uri);
            o2.append("/");
            a.w(o2, bgmOption.duration, "BgmProvider");
            if (((long) bgmOption.duration) + j3 < j2) {
                List<Uri> list = bgmInfo.uris;
                list.add(list.size() - 1, bgmOption.uri);
                j3 += (long) bgmOption.duration;
                StringBuilder o3 = C0086a.o(i2, "added music uri [", "] ");
                o3.append(bgmOption.uri);
                o3.append("/");
                o3.append(bgmOption.duration);
                Log.d("BgmProvider", o3.toString());
                i2++;
            } else {
                bgmInfo.duration = j3;
                return bgmInfo;
            }
        }
    }

    private BgmOptions getBgmOptions(String str) {
        BgmUriService bgmUriService = new BgmUriService();
        try {
            return bgmUriService.createBgmOptionsSync(str, 10000);
        } finally {
            bgmUriService.destroy();
        }
    }

    private String randomBgm() {
        ArrayList<EffectTheme> arrayList = sTheme;
        String[] preloadBgmList = arrayList.get(RandomNumber.nextInt(arrayList.size())).getPreloadBgmList();
        return preloadBgmList[RandomNumber.nextInt(preloadBgmList.length)];
    }

    public BgmInfo getBgmInfo(long j2) {
        BgmOptions bgmOptions;
        String randomBgm = randomBgm();
        try {
            bgmOptions = getBgmOptions(randomBgm);
            if (bgmOptions == null) {
                Log.e((CharSequence) "BgmProvider", "fail to load bgm", randomBgm);
                if (bgmOptions == null) {
                    return null;
                }
                bgmOptions.close();
                return null;
            }
            BgmInfo createBgmInfo = createBgmInfo(bgmOptions, j2);
            bgmOptions.close();
            return createBgmInfo;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to getBgmInfo e="), "BgmProvider");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
