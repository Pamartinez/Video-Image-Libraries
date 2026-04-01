package com.samsung.android.gallery.settings.ui;

import A.a;
import A4.A;
import A4.B;
import A4.C0375j;
import A4.C0387w;
import A4.P;
import B4.c;
import Bb.l;
import C3.i;
import D7.g;
import Da.f;
import E5.b;
import Fa.C0562p;
import Fa.C0563q;
import Fa.C0564s;
import Fa.C0565t;
import Fa.C0566u;
import Fa.C0567v;
import Fa.C0568w;
import Fa.C0569x;
import Fa.C0570y;
import Fa.C0571z;
import Fa.r;
import N2.j;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.local.LocalDatabaseHelper;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.module.debugger.BugReporter;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.RuntimeCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.support.utils.ZipCompat;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.framework.core.util.HashUtil;
import i.C0212a;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsDevManageFragment extends LabsBaseFragment {
    static final HashMap<String, Integer> sCacheMap = new HashMap<String, Integer>() {
        {
            put("labs_cache_preview", 8);
            put("labs_cache_large", 2);
            put("labs_cache_medium", 0);
            put("labs_cache_small", 5);
            put("labs_cache_video", 6);
            put("labs_cache_clip", 4);
            put("labs_cache_map", 7);
            put("labs_cache_mtp", 3);
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CacheDumpWorker extends LabsBaseFragment.WorkerTask {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onExecute$0(int i2, int i7) {
            updateProgress(j.b(i2, i7, "", "/"));
        }

        public void onExecute(Object... objArr) {
            new BugReporter(objArr[0]).archiveCache(new y(0, this));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TrashCopyWorker extends LabsBaseFragment.WorkerTask {
        private static final String DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        private int mCount;
        private int mTotalCount;

        private void copy(File file, ITrashProvider iTrashProvider) {
            String str = "";
            try {
                this.mCount++;
                ProgressDialogCompat progressDialogCompat = this.mProgressDialog;
                StringBuilder sb2 = new StringBuilder(str);
                sb2.append(this.mCount);
                if (this.mTotalCount > 0) {
                    str = "/" + this.mTotalCount;
                }
                sb2.append(str);
                progressDialogCompat.updateMessage(sb2.toString());
                String absolutePath = file.getAbsolutePath();
                FileOpUtils.copy(absolutePath, getOriName(iTrashProvider, absolutePath), true);
            } catch (Exception e) {
                a.s(e, new StringBuilder("unable to copy file e="), this.TAG);
            }
        }

        private void copyFile(ArrayList<File> arrayList, ITrashProvider iTrashProvider) {
            File[] fileArr;
            ArrayList arrayList2 = new ArrayList();
            Iterator<File> it = arrayList.iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (next != null) {
                    fileArr = next.listFiles();
                } else {
                    fileArr = null;
                }
                if (fileArr != null) {
                    arrayList2.addAll(Arrays.asList(fileArr));
                }
            }
            this.mTotalCount = arrayList2.size();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                copy((File) it2.next(), iTrashProvider);
            }
        }

        private String getOriName(ITrashProvider iTrashProvider, String str) {
            Cursor trashCursor;
            try {
                trashCursor = iTrashProvider.getTrashCursor("__absPath = ?", new String[]{str});
                if (trashCursor != null) {
                    if (trashCursor.moveToFirst()) {
                        String string = trashCursor.getString(0);
                        String str2 = DOWNLOAD_PATH + "/" + FileUtils.getNameFromPath(string);
                        trashCursor.close();
                        return str2;
                    }
                }
                if (trashCursor != null) {
                    trashCursor.close();
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("unable to get ori name e="), this.TAG);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            return DOWNLOAD_PATH + "/" + FileUtils.getNameFromPath(str);
            throw th;
        }

        private ArrayList<File> getTrashDirs(Context context) {
            return TrashHelperFactory.getExternalHelper(context).getTrashDirs();
        }

        public void onExecute(Object... objArr) {
            String str = DOWNLOAD_PATH;
            FileUtils.makeDirectoryIfAbsent(str);
            Context context = objArr[0];
            copyFile(getTrashDirs(context), TrashProviderFactory.getInstance(context));
            MediaScanner.scanFolder(str, (MediaScannerListener) null);
        }

        public void onPostExecute(Object... objArr) {
            super.lambda$execute$1(objArr);
            Toast.makeText(objArr[0], this.mCount + " files are copied to \"Download\" folder", 0).show();
        }
    }

    private void addCategoryBackupAndRestore() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_cat_backup_and_restore", "Backup and restore");
        addGenericPreference(computePreferenceCategory, "labs_backup_trash", getString(R$string.labs_title_backup_trash), getString(R$string.labs_summary_backup_trash), (Preference.OnPreferenceClickListener) new C0565t(this, 11));
        if (PocFeatures.isEnabled(PocFeatures.GalleryLabsDev)) {
            addGenericPreference(computePreferenceCategory, "labs_backup_disk_cache", getString(R$string.labs_title_backup_disk_cache), getString(R$string.labs_summary_backup_disk_cache), (Preference.OnPreferenceClickListener) new C0565t(this, 12));
        }
    }

    private void addCategoryStatus() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_cat_status", "Information");
        addGenericPreference(computePreferenceCategory, (String) null, "Preference value", "gallery_pref.xml", (Preference.OnPreferenceClickListener) new C0565t(this, 13));
        addGenericPreference(computePreferenceCategory, (String) null, "Preference value for analytics", "gallery_pref_analytics.xml", (Preference.OnPreferenceClickListener) new C0565t(this, 14));
        addGenericPreference(computePreferenceCategory, (String) null, "Preference value for debug", "gallery_pref_debug.xml", (Preference.OnPreferenceClickListener) new C0565t(this, 15));
        addGenericPreference(computePreferenceCategory, (String) null, "Preference value for cache", "gallery_pref_cache.xml", (Preference.OnPreferenceClickListener) new C0565t(this, 16));
        SharedPreferences sharedPreferences = AppResources.getAppContext().getSharedPreferences("SamsungAnalyticsPrefs", 0);
        addGenericPreference(computePreferenceCategory, (String) null, "Samsung analytics status", "last updated: " + TimeUtil.getLocalizedDateTime(sharedPreferences.getLong("status_sent_date", 0)), (Preference.OnPreferenceClickListener) new C0565t(this, 17));
        addGenericPreference(computePreferenceCategory, (String) null, "Signatures", "Show hash-code of signatures", (Preference.OnPreferenceClickListener) new Ad.j(12));
        addGenericPreference(computePreferenceCategory, (String) null, "Backup preferences", (String) null, (Preference.OnPreferenceClickListener) new Ad.j(13));
        addGenericPreference(computePreferenceCategory, (String) null, "Restore preferences", (String) null, (Preference.OnPreferenceClickListener) new Ad.j(14));
    }

    private String buildGalleryPreferenceValues(Map<String, ?> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        map.forEach(new C0562p(arrayList2, arrayList, 0));
        StringBuilder sb2 = new StringBuilder();
        arrayList2.stream().sorted().forEach(new P(sb2, 1));
        arrayList.stream().sorted().forEach(new P(sb2, 2));
        return sb2.toString();
    }

    private String buildSaLogStatus() {
        SharedPreferences sharedPreferences = AppResources.getAppContext().getSharedPreferences("SamsungAnalyticsPrefs", 0);
        ArrayList arrayList = new ArrayList();
        Map<String, ?> all = GalleryPreference.getInstance().getAll();
        for (String next : sharedPreferences.getStringSet("gallery_pref", new ArraySet())) {
            StringBuilder t = C0212a.t(next, "=");
            t.append(all.get(next));
            arrayList.add(t.toString());
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        GalleryPreference instanceAnalytics = GalleryPreference.getInstanceAnalytics();
        Map<String, ?> all2 = instanceAnalytics.getAll();
        for (String next2 : sharedPreferences.getStringSet("gallery_pref_analytics", new ArraySet())) {
            if (next2.matches("\\d{4}") || "initialized".equals(next2)) {
                StringBuilder t3 = C0212a.t(next2, "=");
                t3.append(all2.get(next2));
                arrayList3.add(t3.toString());
            } else {
                StringBuilder t5 = C0212a.t(next2, "=");
                t5.append(all2.get(next2));
                arrayList2.add(t5.toString());
            }
        }
        StringBuilder sb2 = new StringBuilder("last updated: ");
        sb2.append(TimeUtil.getLocalizedDateTime(sharedPreferences.getLong("status_sent_date", 0)));
        sb2.append("\nJob(album): ");
        sb2.append(TimeUtil.getLocalizedDateTime(instanceAnalytics.loadLong("AlbumStatusLogJob#last", 0)));
        sb2.append("\nJob(common): ");
        sb2.append(TimeUtil.getLocalizedDateTime(instanceAnalytics.loadLong("StatusLogJob#last", 0)));
        j.z(sb2, "\n---------------------------\n", (String) arrayList.stream().sorted().collect(Collectors.joining("\n")), "\n---------------------------\ninitialized=");
        sb2.append(instanceAnalytics.loadInt("initialized", 0));
        sb2.append("\n");
        C0086a.z(sb2, (String) arrayList2.stream().sorted().collect(Collectors.joining("\n")), "\n---------------------------\n", (String) arrayList3.stream().sorted().collect(Collectors.joining("\n")), "\n\n---------- xml ---------\n");
        sb2.append(new String(FileUtils.readFile("/data/data/com.sec.android.gallery3d/shared_prefs/gallery_pref_analytics.xml")));
        sb2.append("\n");
        return sb2.toString();
    }

    public static String buildSignatureInfo() {
        StringBuilder sb2 = new StringBuilder();
        printSignature(sb2, "com.sec.android.gallery3d");
        printSignature(sb2, "com.sec.android.easyMover");
        printSignature(sb2, "com.samsung.android.app.sharelive");
        printSignature(sb2, "com.samsung.android.knox.containeragent");
        printSignature(sb2, "com.samsung.knox.securefolder");
        printSignature(sb2, "com.samsung.android.knox.containercore");
        printSignature(sb2, "com.google.android.gms");
        return sb2.toString();
    }

    private String getCacheSizeSummary(int i2) {
        List<File> listFiles = CacheManager.getInstance().listFiles(i2);
        int size = listFiles.size();
        if (size <= 0) {
            return StringCompat.toReadableSize(MapUtil.INVALID_LOCATION);
        }
        long sum = listFiles.stream().mapToLong(new b(14)).sum();
        StringBuilder sb2 = new StringBuilder();
        double d = (double) sum;
        sb2.append(StringCompat.toReadableSize(d));
        sb2.append("/");
        sb2.append(size);
        sb2.append("=");
        sb2.append(StringCompat.toReadableSize(d / ((double) size)));
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryBackupAndRestore$25(Preference preference) {
        showConfirmDialog("Backup files in trash", "Do you want to backup files in trash? It might take a lot of time.", new C0570y(preference, 1));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryBackupAndRestore$27(Preference preference) {
        showConfirmDialog("Backup disk cache", "Do you want to backup disk cache? It might take a lot of time.", new C0570y(preference, 0));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$10() {
        loadCacheInfo("labs_cache_preview", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$11(Preference preference) {
        showConfirmDialog("Clear preview cache?", new r(this, 1));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$12() {
        loadCacheInfo("labs_cache_large", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$13(Preference preference) {
        showConfirmDialog("Clear large cache?", new r(this, 5));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$14() {
        loadCacheInfo("labs_cache_medium", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$15(Preference preference) {
        showConfirmDialog("Clear medium cache?", new r(this, 6));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$16() {
        loadCacheInfo("labs_cache_small", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$17(Preference preference) {
        showConfirmDialog("Clear small cache?", new r(this, 2));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$18() {
        loadCacheInfo("labs_cache_clip", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$19(Preference preference) {
        showConfirmDialog("Clear clip cache?", new r(this, 4));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$20() {
        loadCacheInfo("labs_cache_video", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$21(Preference preference) {
        showConfirmDialog("Clear video cache?", new r(this, 3));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryCacheManager$22() {
        loadCacheInfo("labs_cache_year", true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryCacheManager$23(Preference preference) {
        showConfirmDialog("Clear year cache?", new r(this, 7));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryPreferenceManager$0(Preference preference) {
        GalleryPreference instance = GalleryPreference.getInstance();
        Objects.requireNonNull(instance);
        showConfirmDialog("Clear all preferences?", new g(12, instance));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryPreferenceManager$2(Preference preference) {
        showConfirmDialog("Clear test preferences?", new i(7));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryPreferenceManager$3() {
        Utils.showToast(getContext(), "processing done");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryPreferenceManager$4(Preference preference) {
        AlbumBnRHelper.getInstance().deleteAllMxAlbumTable(preference.getContext(), LocalDatabaseHelper.getInstance(preference.getContext()).getWritableDatabase());
        ThreadUtil.postOnUiThread(new r(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryPreferenceManager$5(Preference preference) {
        SimpleThreadPool.getInstance().execute(new C0566u(this, preference, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryPreferenceManager$6(Preference preference) {
        showConfirmDialog("Clear MxAlbum table?", new C0566u(this, preference, 1));
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addCategoryPreferenceManager$7(String str) {
        if (str.endsWith("ColCntV70") || str.endsWith("ColCnt70")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addCategoryPreferenceManager$8(String str, String str2) {
        Log.d(this.TAG, "GridSpans#removed", str, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryPreferenceManager$9(Preference preference) {
        HashMap<String, String> removeStateIf = GalleryPreference.getInstance().removeStateIf(new C0571z(0));
        removeStateIf.forEach(new C0564s(this, 1));
        Context context = preference.getContext();
        Toast.makeText(context, "V70 Preference #" + removeStateIf.size() + " was cleared", 0).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryStatus$29(Preference preference) {
        GalleryPreference instance = GalleryPreference.getInstance();
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "gallery_pref.xml").setMessage((CharSequence) buildGalleryPreferenceValues(instance.getAll())).setNegativeButton(R$string.delete_all, (DialogInterface.OnClickListener) new C0567v(instance, 1)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryStatus$33(Preference preference) {
        GalleryPreference instanceAnalytics = GalleryPreference.getInstanceAnalytics();
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "gallery_pref_analytics.xml").setMessage((CharSequence) buildGalleryPreferenceValues(instanceAnalytics.getAll())).setNegativeButton(R$string.delete_all, (DialogInterface.OnClickListener) new C0567v(instanceAnalytics, 2)).setNeutralButton(R$string.dlg_reset, (DialogInterface.OnClickListener) new C0567v(instanceAnalytics, 3)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryStatus$35(Preference preference) {
        GalleryPreference instanceDebug = GalleryPreference.getInstanceDebug();
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "gallery_pref_debug.xml").setMessage((CharSequence) buildGalleryPreferenceValues(instanceDebug.getAll())).setNegativeButton(R$string.delete_all, (DialogInterface.OnClickListener) new C0567v(instanceDebug, 0)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryStatus$37(Preference preference) {
        GalleryPreference instanceCache = GalleryPreference.getInstanceCache();
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "gallery_pref_cache.xml").setMessage((CharSequence) buildGalleryPreferenceValues(instanceCache.getAll())).setNegativeButton(R$string.delete_all, (DialogInterface.OnClickListener) new C0567v(instanceCache, 4)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addCategoryStatus$39(Preference preference) {
        new AlertDialog.Builder(preference.getContext()).setTitle((CharSequence) "Analytics: Setting Status").setMessage((CharSequence) buildSaLogStatus()).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addCategoryStatus$41(String str, GalleryPreference galleryPreference) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(galleryPreference.getName());
        s.append(".json");
        galleryPreference.backup(s.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addCategoryStatus$42() {
        String fileTimestamp = TimeUtil.getFileTimestamp();
        String mediaPath = StorageInfo.getDefault().getMediaPath("preferences");
        StringBuilder s = C0212a.s(mediaPath);
        String str = File.separator;
        String p6 = C0212a.p(s, str, fileTimestamp);
        new File(p6).mkdirs();
        GalleryPreference.listOf().forEach(new B(p6, 4));
        ZipCompat.archive(mediaPath + str + fileTimestamp + ".zip", p6);
        FileUtils.deleteDirectory(new File(p6));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$addCategoryStatus$45(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addCategoryStatus$48(File file) {
        String replaceFirst = file.getName().replace(".json", "").replaceFirst("gallery_pref", "");
        if (replaceFirst.startsWith("_")) {
            replaceFirst = replaceFirst.substring(1);
        }
        Optional.ofNullable(GalleryPreference.of(replaceFirst)).ifPresent(new E9.a(8, file));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addCategoryStatus$49(File file) {
        ZipCompat.decompress(file.getPath(), "/data/sec/gallery/backup/.temp");
        Arrays.asList(new File("/data/sec/gallery/backup/.temp").listFiles()).forEach(new l(28));
        FileUtils.deleteDirectory(new File("/data/sec/gallery/backup/.temp"));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addCategoryStatus$51(Preference preference) {
        String mediaPath = StorageInfo.getDefault().getMediaPath("preferences");
        File[] listFiles = new File(mediaPath).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            Utils.showToast(preference.getContext(), "no data available to restore");
            return true;
        }
        String[] strArr = (String[]) Arrays.stream(listFiles).filter(new C0375j(28)).map(new D3.i(24)).filter(new C0375j(29)).sorted(Comparator.reverseOrder()).toArray(new C0387w(5));
        if (strArr.length > 0) {
            AtomicInteger atomicInteger = new AtomicInteger(0);
            new AlertDialog.Builder(preference.getContext()).setTitle(preference.getTitle()).setSingleChoiceItems((CharSequence[]) strArr, atomicInteger.get(), (DialogInterface.OnClickListener) new C0568w(atomicInteger, 0)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new Da.g(mediaPath, strArr, atomicInteger)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
            return true;
        }
        Toast.makeText(preference.getContext(), "No backup files", 0).show();
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$buildGalleryPreferenceValues$53(ArrayList arrayList, ArrayList arrayList2, String str, Object obj) {
        if (str.contains("#last")) {
            arrayList.add(str + ":=" + obj);
            return;
        }
        arrayList2.add(str + ":=" + obj);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$buildGalleryPreferenceValues$54(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append("\n");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$buildGalleryPreferenceValues$55(StringBuilder sb2, String str) {
        sb2.append(str);
        sb2.append("\n");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$57(HashMap hashMap, HashMap hashMap2, String str, Integer num) {
        long sum = CacheManager.getInstance().listOf(num.intValue()).stream().map(new com.samsung.android.gallery.module.abstraction.g(3)).mapToLong(new C0563q(0, hashMap)).sum();
        hashMap2.put(str, getCacheSizeSummary(num.intValue()) + " [" + StringCompat.toReadableSize((double) sum) + "]");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$59(String str, String str2) {
        Optional.ofNullable(findPreference(str)).ifPresent(new B(str2, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$60(HashMap hashMap) {
        hashMap.forEach(new C0564s(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$61(Context context) {
        String path = context.getCacheDir().getPath();
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder s = C0212a.s(path);
        s.append(File.separator);
        s.append("*");
        HashMap<String, Long> du = RuntimeCompat.du(s.toString());
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        String str = this.TAG;
        Log.d(str, "loadCacheInfo#du +" + currentTimeMillis2);
        HashMap hashMap = new HashMap();
        sCacheMap.forEach(new E9.b(this, du, hashMap, 1));
        hashMap.replace("labs_cache_preview", ((String) hashMap.get("labs_cache_preview")) + " +" + currentTimeMillis2);
        ThreadUtil.postOnUiThread(new f(19, this, hashMap));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$63(String str, String str2) {
        Optional.ofNullable(findPreference(str)).ifPresent(new B(str2, 5));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCacheInfo$64(boolean z, int i2, String str) {
        if (z) {
            CacheManager.getInstance().clear(i2);
        }
        ThreadUtil.postOnUiThread(new A9.b(this, str, getCacheSizeSummary(i2), 16));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadYearCacheInfo$66(String str) {
        Optional.ofNullable(findPreference("labs_cache_year")).ifPresent(new B(str, 6));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadYearCacheInfo$67(boolean z) {
        if (z) {
            YearThumbnailLoader.getInstance().deleteAllYearData();
        }
        long cacheSize = YearThumbnailLoader.getInstance().getCacheSize();
        ThreadUtil.postOnUiThread(new f(17, this, "Year thumbnail cache : " + StringCompat.toReadableSize((double) cacheSize)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$printSignature$52(StringBuilder sb2, String str, Signature signature) {
        String str2;
        try {
            MessageDigest instance = MessageDigest.getInstance(HashUtil.SHA256);
            instance.update(signature.toByteArray());
            byte[] digest = instance.digest();
            str2 = "SHA-256=[" + StringCompat.valueOf(digest, 0, digest.length, ':') + "]";
        } catch (Exception unused) {
            str2 = null;
        }
        sb2.append(str);
        sb2.append(" [");
        sb2.append(Integer.toHexString(signature.hashCode()));
        sb2.append("]\n");
        if ("com.sec.android.gallery3d".equals(str)) {
            sb2.append(str2);
            sb2.append("\n");
        }
        StringBuilder t = C0212a.t(str, " [");
        t.append(Integer.toHexString(signature.hashCode()));
        t.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        t.append(signature.hashCode());
        t.append("] ");
        t.append(str2);
        Log.i("Signature", t.toString());
    }

    private void loadCacheInfo(Context context) {
        SimpleThreadPool.getInstance().execute(new f(18, this, context));
    }

    private void loadYearCacheInfo(boolean z) {
        SimpleThreadPool.getInstance().execute(new c((Object) this, z, 8));
    }

    public static void printSignature(StringBuilder sb2, String str) {
        Signature[] packageSignatures = PackageMonitorCompat.getInstance().getPackageSignatures(str);
        if (packageSignatures.length > 0) {
            Stream.of(packageSignatures).forEach(new A(14, (Object) sb2, str));
            return;
        }
        sb2.append(str);
        sb2.append(" [n/a]\n");
        Log.i("Signature", str + " [n/a]");
    }

    public void addCategoryCacheManager() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_cat_cache_manager", "Cache manager");
        addGenericPreference(computePreferenceCategory, "labs_cache_preview", "Preview cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 0));
        addGenericPreference(computePreferenceCategory, "labs_cache_large", "Large cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 1));
        addGenericPreference(computePreferenceCategory, "labs_cache_medium", "Medium cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 2));
        addGenericPreference(computePreferenceCategory, "labs_cache_small", "Small cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 3));
        addGenericPreference(computePreferenceCategory, "labs_cache_clip", "Clip cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 4));
        addGenericPreference(computePreferenceCategory, "labs_cache_video", "Video cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 5));
        addGenericPreference(computePreferenceCategory, "labs_cache_year", "Year cache information", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 6));
        loadCacheInfo(getContext());
        loadCacheInfo("labs_cache_year", false);
    }

    public void addCategoryPreferenceManager() {
        PreferenceGroup computePreferenceCategory = computePreferenceCategory("labs_cat_preference_manager", "Preference manager");
        addGenericPreference(computePreferenceCategory, "labs_pm_clear", "Clear all preferences", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 7));
        addGenericPreference(computePreferenceCategory, "labs_pm_clear_test", "Clear test preferences", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 8));
        addGenericPreference(computePreferenceCategory, "labs_pm_clear_mx_album_table", "Clear MxAlbum table", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 9));
        addGenericPreference(computePreferenceCategory, "labs_pm_clear_pinch_pref_v70", "Clear pinch preferences V70", (String) null, (Preference.OnPreferenceClickListener) new C0565t(this, 10));
    }

    public int getTitleId() {
        return R$string.labs_dev_managing_options;
    }

    public void initPreferences() {
        addCategoryStatus();
        addCategoryPreferenceManager();
        addCategoryCacheManager();
        addCategoryBackupAndRestore();
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    private void loadCacheInfo(String str, boolean z) {
        if (str.equals("labs_cache_year")) {
            loadYearCacheInfo(z);
            return;
        }
        SimpleThreadPool.getInstance().execute(new C0569x(this, z, sCacheMap.getOrDefault(str, 99).intValue(), str));
    }
}
