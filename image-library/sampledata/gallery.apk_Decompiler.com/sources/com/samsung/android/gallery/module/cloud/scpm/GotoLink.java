package com.samsung.android.gallery.module.cloud.scpm;

import J5.c;
import M4.j;
import O3.o;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ScpmMessage;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GotoLink {
    final Object LOCK = new Object();
    final String ROOT = "/data/sec/gallery/odm/gotolink";
    Set<String> allowedSet = new HashSet(List.of(new String[]{"com.sec.android.app.sbrowser", "com.android.chrome", "com.google.android.apps.maps", "com.google.android.googlequicksearchbox", "com.google.android.youtube", "com.navercorp.game.android.community", "com.naver.labs.translator", "com.naver.papago.plus", "com.nhn.android.search", "com.naver.whale", "com.naver.clova.minute", "com.navercorp.navershopping", "com.fstudio.kream", "com.instagram.android", "com.facebook.wakizashi", "com.facebook.katana"}));
    long version;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final GotoLink instance = new GotoLink();
    }

    public GotoLink() {
        GotoLinkData gotoLinkData = new GotoLinkData(GalleryPreference.getInstanceCache().computeStringIfAbsent("GotoLink", new c(5, this)));
        if (gotoLinkData.version > 0) {
            update(gotoLinkData);
        }
        Log.d("GotoLink", "GotoLink{" + this.version + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.allowedSet.size() + "} ");
    }

    public static GotoLink getInstance() {
        return LazyHolder.instance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$update$0(File file) {
        return !file.delete();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$update$1(ScpmMessage scpmMessage, String str) {
        File file = new File("/data/sec/gallery/odm/gotolink");
        if (file.exists() || file.mkdirs()) {
            List<String> loadJsonFiles = loadJsonFiles();
            FileUtils.writeFile(new File("/data/sec/gallery/odm/gotolink", String.valueOf(scpmMessage.version)), str.getBytes());
            if (!loadJsonFiles.isEmpty()) {
                long count = loadJsonFiles.stream().limit((long) (loadJsonFiles.size() - 1)).map(new o(12)).filter(new j(26)).count();
                if (count > 0) {
                    Log.e("GotoLink", "update > delete failed=" + count);
                }
            }
        }
    }

    public boolean contains(String str) {
        boolean contains;
        synchronized (this.LOCK) {
            contains = this.allowedSet.contains(str);
        }
        return contains;
    }

    public String loadJsonFile() {
        ScpmMessage fromJson;
        List<String> loadJsonFiles = loadJsonFiles();
        if (loadJsonFiles.isEmpty() || (fromJson = ScpmMessage.fromJson(new File((String) C0086a.f(1, loadJsonFiles)))) == null) {
            return "";
        }
        return new GotoLinkData(fromJson).toPreferenceValue();
    }

    public List<String> loadJsonFiles() {
        File[] fileArr;
        File file = new File("/data/sec/gallery/odm/gotolink");
        if (file.exists()) {
            fileArr = file.listFiles();
        } else {
            fileArr = null;
        }
        if (fileArr == null || fileArr.length <= 0) {
            return new ArrayList();
        }
        return (List) Stream.of(fileArr).map(new o(13)).sorted().collect(Collectors.toList());
    }

    public String toString() {
        return "GotoLink{" + this.version + ",a=" + this.allowedSet.size();
    }

    public boolean update(String str) {
        GotoLinkData gotoLinkData;
        ScpmMessage fromJson = ScpmMessage.fromJson(str);
        if (fromJson == null) {
            gotoLinkData = null;
        } else {
            gotoLinkData = new GotoLinkData(fromJson);
        }
        if (gotoLinkData == null || !"GotoLink".equals(gotoLinkData.tag) || gotoLinkData.version <= 0) {
            Log.w("GotoLink", "update failed " + gotoLinkData);
            return false;
        }
        boolean update = update(gotoLinkData);
        Log.d("GotoLink", "update" + Logger.v(Boolean.valueOf(update), Long.valueOf(this.version), Long.valueOf(fromJson.version)) + "");
        if (!update) {
            return false;
        }
        GalleryPreference.getInstanceCache().saveState("GotoLink", gotoLinkData.toPreferenceValue());
        ThreadUtil.runOnBgThread(new R6.c(this, fromJson, str, 1));
        return true;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GotoLinkData {
        ArrayList<String> allowedList;
        ArrayList<String> forbiddenList;
        String tag;
        long version;

        public GotoLinkData(ScpmMessage scpmMessage) {
            this.allowedList = new ArrayList<>();
            this.forbiddenList = new ArrayList<>();
            this.tag = "GotoLink".equals(scpmMessage.tag) ? scpmMessage.tag : null;
            this.version = scpmMessage.version;
            load(scpmMessage.data);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$load$1(String[] strArr) {
            if (strArr.length <= 1 || TextUtils.isEmpty(strArr[1])) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$load$3(String str) {
            this.version = Long.parseLong(str);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$load$4(String str) {
            this.allowedList.addAll(Arrays.asList(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$load$5(String str) {
            this.forbiddenList.addAll(Arrays.asList(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        }

        /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, java.util.function.Function] */
        /* JADX WARNING: type inference failed for: r1v2, types: [java.util.function.Predicate, java.lang.Object] */
        private void load(String str) {
            if (!TextUtils.isEmpty(str)) {
                HashMap hashMap = new HashMap();
                Stream.of(str.split(";")).map(new Object()).filter(new Object()).forEach(new c(3, hashMap));
                Optional.ofNullable((String) hashMap.get("version")).ifPresent(new c(0, this));
                Optional.ofNullable((String) hashMap.get("allowed")).ifPresent(new c(1, this));
                Optional.ofNullable((String) hashMap.get("forbidden")).ifPresent(new c(2, this));
            }
        }

        public String toPreferenceValue() {
            if (this.tag == null || this.version <= 0) {
                return "";
            }
            return "version=" + this.version + ";allowed=" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.allowedList) + ";forbidden=" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.forbiddenList);
        }

        public String toString() {
            return "GotoLinkData{" + this.tag + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.version + GlobalPostProcInternalPPInterface.SPLIT_REGEX + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.allowedList) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.forbiddenList) + "}";
        }

        public GotoLinkData(String str) {
            this.allowedList = new ArrayList<>();
            this.forbiddenList = new ArrayList<>();
            this.tag = "GotoLink";
            load(str);
        }
    }

    public boolean update(GotoLinkData gotoLinkData) {
        synchronized (this.LOCK) {
            try {
                long j2 = gotoLinkData.version;
                if (j2 <= this.version) {
                    return false;
                }
                this.version = j2;
                this.allowedSet.addAll(gotoLinkData.allowedList);
                if (!gotoLinkData.forbiddenList.isEmpty()) {
                    ArrayList<String> arrayList = gotoLinkData.forbiddenList;
                    Set<String> set = this.allowedSet;
                    Objects.requireNonNull(set);
                    arrayList.forEach(new Qb.c(4, set));
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
