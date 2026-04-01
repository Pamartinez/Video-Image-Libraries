package com.samsung.android.gallery.settings.ui;

import A4.C0387w;
import A5.a;
import A9.b;
import Ad.C0720a;
import C3.i;
import Da.g;
import Fa.C0557k;
import Fa.C0558l;
import Fa.C0559m;
import Fa.C0560n;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.LabsBaseFragment;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import i.C0212a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsAlbumBnRFragment extends LabsBaseFragment {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AlbumBnRWorker extends LabsBaseFragment.WorkerTask {
        private Runnable mPreExecutor;
        private Boolean mResult = Boolean.FALSE;

        private String getBackupFolder(Context context) {
            String str;
            if (FileUtils.isSdcardMounted(context)) {
                str = FileUtils.getSdcardDir();
            } else {
                str = FileUtils.EXTERNAL_STORAGE_DIR;
            }
            StringBuilder s = C0212a.s(str);
            String str2 = File.separator;
            return C0212a.q(s, str2, "Android", str2, ".gallery");
        }

        public AlbumBnRWorker appendPreExecutor(Runnable runnable) {
            this.mPreExecutor = runnable;
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onExecute(java.lang.Object... r7) {
            /*
                r6 = this;
                java.lang.Runnable r0 = r6.mPreExecutor
                if (r0 == 0) goto L_0x000a
                r0.run()
                r0 = 0
                r6.mPreExecutor = r0
            L_0x000a:
                r0 = 0
                r1 = r7[r0]
                android.content.Context r1 = (android.content.Context) r1
                if (r1 != 0) goto L_0x0012
                goto L_0x0066
            L_0x0012:
                r2 = 1
                r3 = r7[r2]
                java.lang.Boolean r3 = (java.lang.Boolean) r3
                boolean r3 = r3.booleanValue()
                int r4 = r7.length
                r5 = 2
                if (r4 <= r5) goto L_0x0030
                r7 = r7[r5]
                boolean r4 = r7 instanceof java.lang.String
                if (r4 == 0) goto L_0x0030
                java.lang.String r7 = (java.lang.String) r7
                java.lang.String r4 = com.samsung.android.gallery.support.utils.FileUtils.getDirectoryFromPath(r7, r0)
                java.lang.String r7 = com.samsung.android.gallery.support.utils.FileUtils.getNameFromPath(r7)
                goto L_0x0036
            L_0x0030:
                java.lang.String r4 = r6.getBackupFolder(r1)
                java.lang.String r7 = "BACKUP_ALBUM_DB.txt"
            L_0x0036:
                if (r3 == 0) goto L_0x0047
                com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper r0 = com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper.getInstance()
                boolean r7 = r0.backupAlbumDatabase(r1, r4, r7)
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
                r6.mResult = r7
                return
            L_0x0047:
                com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper r3 = com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper.getInstance()
                boolean r7 = r3.restoreAlbumDatabase(r1, r4, r7)
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
                r6.mResult = r7
                boolean r7 = r7.booleanValue()
                if (r7 == 0) goto L_0x0066
                r7 = 104(0x68, float:1.46E-43)
                java.lang.String r6 = r6.TAG
                com.samsung.android.gallery.support.blackboard.key.EventMessage r6 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r7, r2, r0, r6)
                com.samsung.android.gallery.support.blackboard.Blackboard.postBroadcastEventGlobal(r6)
            L_0x0066:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment.AlbumBnRWorker.onExecute(java.lang.Object[]):void");
        }

        public void onPostExecute(Object... objArr) {
            String str;
            String str2;
            super.lambda$execute$1(new Object[0]);
            Context context = objArr[0];
            StringBuilder sb2 = new StringBuilder("[Labs] ");
            if (objArr[1].booleanValue()) {
                str = "Backup ";
            } else {
                str = "Restore ";
            }
            sb2.append(str);
            if (this.mResult.booleanValue()) {
                str2 = "successful";
            } else {
                str2 = "failed";
            }
            sb2.append(str2);
            Utils.showToast(context, sb2.toString(), 0);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DateTimeFormatter {
        static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");

        public static String getLocalizedDateTime(String str) {
            try {
                SimpleDateFormat simpleDateFormat = FORMATTER;
                Date parse = simpleDateFormat.parse(str + "000");
                Objects.requireNonNull(parse);
                return TimeUtil.getLocalizedDateTime(parse.getTime());
            } catch (Exception unused) {
                return str;
            }
        }
    }

    private ArrayList<String> getAlbumBackupList(String str) {
        String str2;
        ArrayList<String> arrayList = new ArrayList<>();
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                if (file == null || !file.isFile()) {
                    str2 = null;
                } else {
                    str2 = file.getName();
                }
                if (str2 != null && (str2.startsWith("album_") || str2.startsWith("Album.backup."))) {
                    arrayList.add(file.getPath());
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onBackupAlbumDbClicked$1(Preference preference, DialogInterface dialogInterface, int i2) {
        String backupFileName = AlbumBnRHelper.getInstance().getBackupFileName();
        FileUtils.makeParentIfAbsent(backupFileName);
        new AlbumBnRWorker().execute(preference.getContext(), Boolean.TRUE, backupFileName);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$onRestoreAlbumDbClicked$2(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRestoreAlbumDbClicked$3(AtomicInteger atomicInteger, ArrayList arrayList, DialogInterface dialogInterface, int i2) {
        atomicInteger.set(i2);
        String str = this.TAG;
        Log.d(str, "select {" + ((String) arrayList.get(i2)) + '}');
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRestoreAlbumDbClicked$4(ArrayList arrayList, Context context) {
        if (arrayList.size() == 0) {
            File file = new File(AlbumBnRHelper.getInstance().getBackupFileName());
            AlbumBnRHelper.getInstance().backupAlbumDatabase(context, file.getParent(), file.getName());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onRestoreAlbumDbClicked$5(ArrayList arrayList, Context context, ArrayList arrayList2, AtomicInteger atomicInteger, DialogInterface dialogInterface, int i2) {
        new AlbumBnRWorker().appendPreExecutor(new b(this, arrayList, context, 15)).execute(context, Boolean.FALSE, arrayList2.get(atomicInteger.get()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onRestoreAlbumDbClicked$6(ArrayList arrayList, AtomicInteger atomicInteger, Context context, DialogInterface dialogInterface, int i2) {
        String str = (String) arrayList.get(atomicInteger.get());
        if (str.startsWith("/data/sec/gallery/backup")) {
            FileUtils.delete(str);
        } else {
            Toast.makeText(context, "Cannot delete system backup file", 0).show();
        }
    }

    private boolean makeDirsIfAbsent(String str) {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onBackupAlbumDbClicked(Preference preference) {
        ArrayList<String> albumBackupList = getAlbumBackupList("/data/sec/gallery/backup");
        if (albumBackupList.size() >= 8) {
            Context context = preference.getContext();
            Toast.makeText(context, "Too many backup files (" + albumBackupList.size() + "). Please delete old ones from restore menu", 0).show();
            return false;
        }
        new AlertDialog.Builder(preference.getContext()).setTitle(R$string.backup_album_database).setMessage(R$string.can_restore_album_from_backup_later).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0558l(0, preference)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onRestoreAlbumDbClicked(Preference preference) {
        Context context = preference.getContext();
        ArrayList arrayList = (ArrayList) getAlbumBackupList("/data/sec/gallery/backup").stream().sorted(Comparator.reverseOrder()).limit(8).collect(Collectors.toCollection(new C0720a(1)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(arrayList);
        arrayList2.addAll((ArrayList) getAlbumBackupList("/data/sec/gallery/smartswitch").stream().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toCollection(new C0720a(1))));
        if (arrayList2.isEmpty()) {
            Toast.makeText(context, "[Labs] Album restore failed. no data", 0).show();
            return true;
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new AlertDialog.Builder(context).setTitle(R$string.restore_album_database).setSingleChoiceItems((CharSequence[]) (String[]) arrayList2.stream().map(new a(6, this)).toArray(new C0387w(4)), atomicInteger.get(), (DialogInterface.OnClickListener) new g(this, atomicInteger, arrayList2, 1)).setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new C0559m(this, arrayList, context, arrayList2, atomicInteger)).setNeutralButton(R$string.delete, (DialogInterface.OnClickListener) new C0560n(arrayList2, atomicInteger, context, 0)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) new C0560n(arrayList2, atomicInteger, context, 1)).show();
        return true;
    }

    /* access modifiers changed from: private */
    public String toReadableBackupName(String str) {
        String str2 = "";
        String replaceAll = new File(str).getName().replaceFirst("(Album.backup.|album_)", str2).replaceAll(".txt.*$", str2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(DateTimeFormatter.getLocalizedDateTime(replaceAll));
        if (!str.startsWith("/data/sec/gallery/backup")) {
            str2 = " (S)";
        }
        sb2.append(str2);
        return sb2.toString();
    }

    public int getPreferenceResourceId() {
        return R$xml.setting_preference_labs_album_bnr;
    }

    public int getTitleId() {
        return R$string.album_backup_and_restore;
    }

    public void initPreferences() {
        PreferenceGroup preferenceGroup = (PreferenceGroup) getPreferenceScreen().findPreference("category_album_bnr");
        addGenericPreference(preferenceGroup, "labs_backup_album_db", getString(R$string.backup_album_database), (String) null, (Preference.OnPreferenceClickListener) new C0557k(this, 0));
        addGenericPreference(preferenceGroup, "labs_restore_album_db", getString(R$string.restore_album_database), (String) null, (Preference.OnPreferenceClickListener) new C0557k(this, 1));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        makeDirsIfAbsent("/data/sec/gallery/backup");
        makeDirsIfAbsent("/data/sec/gallery/smartswitch");
        if (AlbumBnRHelper.getInstance().hasRestoreFile()) {
            SimpleThreadPool.getInstance().execute(new i(5));
        }
    }

    public /* bridge */ /* synthetic */ RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onRestoreAlbumDbClicked$7(ArrayList arrayList, AtomicInteger atomicInteger, Context context, DialogInterface dialogInterface, int i2) {
    }
}
