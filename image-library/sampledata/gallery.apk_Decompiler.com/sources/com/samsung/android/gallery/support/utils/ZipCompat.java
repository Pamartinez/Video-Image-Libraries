package com.samsung.android.gallery.support.utils;

import N2.j;
import com.samsung.android.gallery.settings.ui.y;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZipCompat {
    private final byte[] BUFFER;
    private int mCount;
    private final String mFolder;
    private OnProgressListener mProgressListener;
    private final int mTotalCount;
    private long mTotalFileSize;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileCounter {
        private int mFileCount;

        public FileCounter(String str) {
            getFileCount(new File(str));
        }

        private void getFileCount(File file) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.isDirectory()) {
                            getFileCount(file2);
                        } else {
                            this.mFileCount++;
                        }
                    }
                    return;
                }
                return;
            }
            this.mFileCount++;
        }

        public int getCount() {
            return this.mFileCount;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnProgressListener {
    }

    public ZipCompat() {
        this((String) null);
    }

    public static void archive(String str, String str2) {
        archive(str, str2, (OnProgressListener) null);
    }

    private void archiveFile(ZipOutputStream zipOutputStream, String str, File file) {
        int i2;
        int i7;
        FileInputStream fileInputStream;
        this.mCount++;
        String replaceFirst = file.getAbsolutePath().replaceFirst(str, "");
        try {
            fileInputStream = new FileInputStream(file);
            zipOutputStream.putNextEntry(new ZipEntry(replaceFirst));
            while (true) {
                int read = fileInputStream.read(this.BUFFER, 0, 2048);
                if (read == -1) {
                    break;
                }
                zipOutputStream.write(this.BUFFER, 0, read);
            }
            zipOutputStream.closeEntry();
            fileInputStream.close();
        } catch (IOException e) {
            j.r(e, j.k("archiveFile failed ", replaceFirst, ", e="), "ZipFile");
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        OnProgressListener onProgressListener = this.mProgressListener;
        if (onProgressListener != null && (i2 = this.mCount) < (i7 = this.mTotalCount)) {
            ((y) onProgressListener).a(i2, i7);
            return;
        }
        return;
        throw th;
    }

    public static void decompress(String str, String str2) {
        if (FileUtils.isInAndroidMediaDir(str)) {
            new ZipCompat().uncompressFile(str, str2);
            return;
        }
        throw new SecurityException(C0212a.l("uncompress not supported ", str));
    }

    public void archiveFolder(String str, OnProgressListener onProgressListener) {
        ZipOutputStream zipOutputStream;
        this.mProgressListener = onProgressListener;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
            File file = new File(this.mFolder);
            archiveFolder(zipOutputStream, file.getPath() + "/", file);
            zipOutputStream.close();
        } catch (IOException | NullPointerException e) {
            StringBuilder k = j.k("archive failed {", str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            k.append(this.mFolder);
            k.append("}, e=");
            k.append(e.getMessage());
            Log.e("ZipFile", k.toString());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        OnProgressListener onProgressListener2 = this.mProgressListener;
        if (onProgressListener2 != null) {
            int i2 = this.mTotalCount;
            ((y) onProgressListener2).a(i2, i2);
            return;
        }
        return;
        throw th;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public void uncompressFile(String str, String str2) {
        ZipInputStream zipInputStream;
        File file = new File(str2);
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(str));
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                decompress(zipInputStream, nextEntry, file);
                if (this.mCount <= 5000) {
                    if (this.mTotalFileSize > 10737418240L) {
                        break;
                    }
                } else {
                    break;
                }
            }
            Log.e("ZipFile", "uncompressFile limited to {" + this.mCount + '/' + 5000 + ',' + this.mTotalFileSize + '/' + 10737418240L + '}');
            zipInputStream.closeEntry();
            Log.d("ZipFile", "uncompress finished {" + this.mTotalCount + ',' + this.mTotalFileSize + '}');
            zipInputStream.close();
            return;
        } catch (IOException | NullPointerException | OutOfMemoryError e) {
            Log.e((CharSequence) "ZipFile", "uncompress failed {" + this.mTotalCount + ',' + this.mTotalFileSize + '}', e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public ZipCompat(String str) {
        int i2;
        this.BUFFER = new byte[2048];
        this.mFolder = str;
        if (str == null) {
            i2 = 0;
        } else {
            i2 = new FileCounter(str).getCount();
        }
        this.mTotalCount = i2;
        if (str != null) {
            Log.i("ZipFile", "ZipFile {" + str + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i2 + "}");
        }
    }

    public static void archive(String str, String str2, OnProgressListener onProgressListener) {
        FileUtils.makeParentIfAbsent(new File(str));
        new ZipCompat(str2).archiveFolder(str, onProgressListener);
    }

    public static byte[] archive(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry("stream"));
            zipOutputStream.write(bArr, 0, bArr.length);
            zipOutputStream.closeEntry();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            zipOutputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
            throw th;
            throw th;
        } catch (IOException e) {
            Log.e((CharSequence) "ZipFile", "archive failed " + bArr.length, (Throwable) e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static byte[] decompress(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bArr));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                if (zipInputStream.getNextEntry() != null) {
                    byte[] bArr2 = new byte[2048];
                    while (true) {
                        int read = zipInputStream.read(bArr2);
                        if (read > 0) {
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } else {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            zipInputStream.close();
                            return byteArray;
                        }
                    }
                } else {
                    byteArrayOutputStream.close();
                    zipInputStream.close();
                    return null;
                }
            } catch (Throwable th) {
                zipInputStream.close();
                throw th;
            }
            throw th;
        } catch (IOException e) {
            Log.e((CharSequence) "ZipFile", "uncompress failed " + bArr.length, (Throwable) e);
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    private void archiveFolder(ZipOutputStream zipOutputStream, String str, File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                Log.d("ZipFile", "archiveFolder {" + this.mCount + "/" + this.mTotalCount + GlobalPostProcInternalPPInterface.SPLIT_REGEX + listFiles.length + GlobalPostProcInternalPPInterface.SPLIT_REGEX + file.getAbsolutePath().replaceFirst(str, "") + "}");
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        archiveFolder(zipOutputStream, str, file2);
                    } else {
                        archiveFile(zipOutputStream, str, file2);
                    }
                }
                return;
            }
            return;
        }
        archiveFile(zipOutputStream, str, file);
    }

    private void decompress(ZipInputStream zipInputStream, ZipEntry zipEntry, File file) {
        FileOutputStream fileOutputStream;
        File file2 = new File(file, zipEntry.getName());
        if (!zipEntry.isDirectory()) {
            File parentFile = file2.getParentFile();
            if (parentFile == null) {
                Log.e("ZipFile", "uncompress failed parent null");
                return;
            } else if (parentFile.exists() || parentFile.mkdirs()) {
                long j2 = 0;
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        int read = zipInputStream.read(this.BUFFER);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(this.BUFFER, 0, read);
                        j2 += (long) read;
                    }
                    fileOutputStream.close();
                } catch (IOException e) {
                    Log.e((CharSequence) "ZipFile", "uncompress failed", (Throwable) e);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
                this.mCount++;
                this.mTotalFileSize += j2;
                return;
            } else {
                Log.e("ZipFile", "uncompress failed mkdirs " + parentFile);
                return;
            }
        } else if (!file2.exists() && !file2.mkdirs()) {
            Log.e("ZipFile", "uncompress failed mkdirs " + file2);
            return;
        } else {
            return;
        }
        throw th;
    }
}
