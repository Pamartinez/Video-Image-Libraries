package com.samsung.android.gallery.support.utils;

import A.a;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BnRDocStorageHelper {
    public static int copyFileToDirUri(Context context, File file, Uri uri) {
        if (file.isDirectory()) {
            Uri createDirectory = createDirectory(context, uri, file.getName());
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int i2 = 0;
                for (File copyFileToDirUri : listFiles) {
                    i2 += copyFileToDirUri(context, copyFileToDirUri, createDirectory);
                }
                return i2;
            }
        } else {
            Uri createFile = createFile(context, uri, file.getName(), (String) null);
            if (createFile != null) {
                return copyFileToFileUri(context, file, createFile);
            }
        }
        return 0;
    }

    private static int copyFileToFileUri(Context context, File file, Uri uri) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedOutputStream = new BufferedOutputStream(context.getContentResolver().openOutputStream(uri));
            if (copyStream(bufferedInputStream, bufferedOutputStream)) {
                bufferedOutputStream.close();
                bufferedInputStream.close();
                return 1;
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
            return 0;
            throw th;
            throw th;
        } catch (Exception e) {
            Log.e((CharSequence) "BnRDocStorageHelper", "copyFileToFileUri failed", (Throwable) e);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    private static boolean copyStream(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[32768];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return true;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    private static boolean copyUriToFile(Context context, Uri uri, File file) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            boolean copyStream = copyStream(bufferedInputStream, bufferedOutputStream);
            bufferedOutputStream.close();
            bufferedInputStream.close();
            return copyStream;
            throw th;
            throw th;
        } catch (Exception e) {
            Log.e((CharSequence) "BnRDocStorageHelper", "copyUriToFile failed {" + uri + '}', (Throwable) e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    private static Uri createDirectory(Context context, Uri uri, String str) {
        return createFile(context, uri, str, "vnd.android.document/directory");
    }

    private static Uri createFile(Context context, Uri uri, String str, String str2) {
        Log.d("BnRDocStorageHelper", "createFile");
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str2, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getDataFromUri(Context context, Uri uri) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
            String streamData = getStreamData(bufferedInputStream);
            bufferedInputStream.close();
            return streamData;
        } catch (Exception e) {
            Log.e((CharSequence) "BnRDocStorageHelper", "getDataFromUri failed {" + uri + '}', (Throwable) e);
            return "";
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static List<String> getPathFromJson(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONObject(getDataFromUri(context, Uri.parse(str))).getJSONArray("dataList");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        arrayList.add(jSONArray.getJSONObject(i2).getString("docUri"));
                    } catch (Exception e) {
                        Log.e((CharSequence) "BnRDocStorageHelper", "getPathFromJson failed {" + i2 + '/' + jSONArray.length() + '}', (Throwable) e);
                    }
                }
            } catch (Exception e7) {
                Log.e((CharSequence) "BnRDocStorageHelper", "getPathFromJson failed", (Throwable) e7);
            }
        }
        return arrayList;
    }

    public static List<Uri> getPathUris(Context context, Intent intent) {
        List stringArrayListExtra = intent.getStringArrayListExtra("SAVE_PATH_URIS");
        if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
            stringArrayListExtra = getPathFromJson(context, intent.getStringExtra("SAVE_URIS_FILE"));
        }
        List<Uri> list = (List) stringArrayListExtra.stream().map(new C0670h(9)).collect(Collectors.toList());
        Log.i("BnRDocStorageHelper", "getPathUris", Integer.valueOf(list.size()));
        return list;
    }

    private static String getStreamData(InputStream inputStream) {
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder(2048);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] cArr = new char[2048];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read <= 0) {
                    break;
                }
                sb2.append(cArr, 0, read);
            }
            bufferedReader.close();
        } catch (IOException e) {
            Log.e((CharSequence) "BnRDocStorageHelper", "getStreamData failed {" + sb2.length() + '}', (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    public static int moveUrisToDir(Context context, Uri uri, Collection<Uri> collection, File file) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            str = DocumentsContract.getDocumentId(uri);
        } else {
            str = DocumentsContract.getTreeDocumentId(uri);
        }
        String absolutePath = file.getAbsolutePath();
        Log.i("BnRDocStorageHelper", "moveUrisToDir");
        int i2 = 0;
        for (Uri next : collection) {
            if (DocumentsContract.isDocumentUri(context, next)) {
                String documentId = DocumentsContract.getDocumentId(next);
                File file2 = new File(documentId.replaceFirst(str, absolutePath));
                FileUtils.makeParentIfAbsent(file2);
                boolean copyUriToFile = copyUriToFile(context, next, file2);
                try {
                    if (!DocumentsContract.deleteDocument(context.getContentResolver(), next) || !copyUriToFile) {
                        Log.w((CharSequence) "BnRDocStorageHelper", "moveUrisToDir failed to delete", documentId, Boolean.valueOf(copyUriToFile));
                    } else {
                        i2++;
                    }
                } catch (Exception e) {
                    Log.e((CharSequence) "BnRDocStorageHelper", "moveUrisToDir failed {" + next + '}', (Throwable) e);
                }
            }
        }
        a.A(new Object[]{Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("moveUrisToDir"), "BnRDocStorageHelper");
        return i2;
    }
}
