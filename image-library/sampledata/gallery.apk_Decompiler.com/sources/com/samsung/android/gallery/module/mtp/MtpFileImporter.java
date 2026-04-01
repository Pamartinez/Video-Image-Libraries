package com.samsung.android.gallery.module.mtp;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpFileImporter {
    private static final File DIRECTORY = new File(Environment.getExternalStorageDirectory(), C0212a.p(new StringBuilder("Pictures"), File.separator, "Imported"));
    private final MtpClient mClient;
    private final String mDeviceName;
    private boolean mImported;
    private final MediaScanner mMediaScanner;
    private final ArrayList<String> mScanFiles = new ArrayList<>();

    public MtpFileImporter(Context context, String str) {
        this.mClient = MtpClient.getInstance(context);
        this.mDeviceName = str;
        this.mMediaScanner = new MediaScanner().setBulk(true);
        this.mImported = false;
    }

    public void finishImport() {
        Log.mt("MtpFileImporter", "finishImport [" + this.mImported + "][" + this.mScanFiles.size() + "]");
        if (this.mImported) {
            this.mMediaScanner.scanFiles(this.mScanFiles);
        }
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public String getImportPath(String str) {
        return new File(DIRECTORY, str).getAbsolutePath();
    }

    public boolean initDirectory() {
        return FileUtils.makeDirectoryIfAbsent(DIRECTORY);
    }

    public boolean isImported() {
        return this.mImported;
    }

    public void performImport(int i2, String str) {
        boolean z;
        if (TextUtils.isEmpty(str)) {
            Log.mtw("MtpFileImporter", "unable to perform import. null path");
        } else if (this.mClient.importFile(this.mDeviceName, i2, str)) {
            this.mScanFiles.add(str);
            this.mImported = true;
        } else {
            SecureFile secureFile = new SecureFile(str);
            boolean exists = secureFile.exists();
            if (exists) {
                z = secureFile.delete();
            } else {
                z = false;
            }
            Log.mtw("MtpFileImporter", "broken import image [" + exists + "][" + z + "]");
        }
    }
}
