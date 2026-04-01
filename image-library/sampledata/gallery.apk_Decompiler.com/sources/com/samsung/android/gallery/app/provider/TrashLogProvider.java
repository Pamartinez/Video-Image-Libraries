package com.samsung.android.gallery.app.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashLogProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        boolean z;
        BufferedReader bufferedReader;
        Context context = getContext();
        if (context != null) {
            boolean isEnabled = PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);
            boolean isSyncOn = SamsungCloudCompat.isSyncOn(context);
            boolean isEnabled2 = Features.isEnabled(Features.IS_UPSM);
            if (!isSyncOn || isEnabled2) {
                z = true;
            } else {
                z = false;
            }
            printWriter.println("== cloud mode check == ");
            printWriter.println(" trash on : " + isEnabled);
            printWriter.println(" cloud hidden mode : " + z);
            printWriter.println(" cloud sync on : " + isSyncOn);
            printWriter.println(" upsm mode : " + isEnabled2);
            CloudStateCompat.dump(printWriter);
            try {
                ITrashProvider instance = TrashProviderFactory.getInstance(context);
                printWriter.println("== trash count cloud hidden mode ==");
                int[] trashCount = instance.getTrashCount(true);
                if (trashCount != null) {
                    printWriter.println(" trash image : " + trashCount[0]);
                    printWriter.println(" trash video : " + trashCount[1]);
                }
                printWriter.println("== trash count normal mode ==");
                int[] trashCount2 = instance.getTrashCount(false);
                if (trashCount2 != null) {
                    printWriter.println(" trash image : " + trashCount2[0]);
                    printWriter.println(" trash video : " + trashCount2[1]);
                }
                printWriter.println("== trash info : ");
            } catch (Exception unused) {
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.B) && PocFeatures.SUPPORT_QUICK_DELETE) {
                printWriter.println("== trash job info ==");
                printWriter.print("idle-job executed[" + GalleryPreference.getInstanceDebug().loadBoolean("TrashRollbackTask_job_finished", false) + "]QD Abn count[" + PreferenceCache.QuickDeleteService.getInt() + "]");
            }
            try {
                FileReader fileReader = new FileReader(new File(context.getFilesDir(), "dump.log"));
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            fileReader.close();
                            return;
                        } else if (!readLine.isEmpty()) {
                            printWriter.println(readLine);
                        }
                    }
                } catch (Throwable th) {
                    fileReader.close();
                    throw th;
                }
            } catch (Exception unused2) {
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            return;
        }
        throw th;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
