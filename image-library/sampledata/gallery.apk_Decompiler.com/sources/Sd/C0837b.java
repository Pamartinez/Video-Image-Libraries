package Sd;

import C3.i;
import O3.o;
import Qb.c;
import S3.d;
import android.content.Context;
import android.os.Build;
import c0.C0086a;
import com.samsung.android.sum.core.buffer.C0923a;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.framework.core.ScspException;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/* renamed from: Sd.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0837b implements C0836a {

    /* renamed from: a  reason: collision with root package name */
    public final Context f3696a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final D f3697c;
    public final String d;
    public final FileDescriptor e;
    public c f = null;
    public boolean g = false;

    /* renamed from: h  reason: collision with root package name */
    public Runnable f3698h = new i(16);

    public C0837b(Context context, String str, String str2, FileDescriptor fileDescriptor) {
        this.f3696a = context;
        this.b = str;
        this.f3697c = new D(context);
        this.d = str2;
        this.e = fileDescriptor;
    }

    public static String a(InputStream inputStream) {
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try {
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb2.append(readLine);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    return sb2.toString();
                }
            }
        } catch (Throwable th) {
            try {
                inputStreamReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
        throw th;
    }

    public final String b() {
        String[] split = "com.samsung.scloud.gallery".split("\\.");
        String str = split[split.length - 1];
        Locale locale = Locale.US;
        String str2 = Build.MODEL;
        String str3 = Build.DISPLAY;
        Context context = this.f3696a;
        String str4 = f.C(context).f3700a;
        String str5 = f.C(context).b;
        int i2 = Build.VERSION.SDK_INT;
        String str6 = Build.VERSION.RELEASE;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append("; ");
        sb2.append(str3);
        sb2.append("; ");
        sb2.append(str4);
        sb2.append("=");
        sb2.append(str5);
        sb2.append("; android sdk=");
        sb2.append(i2);
        C0086a.z(sb2, ", sw=", str6, "; ", str);
        sb2.append("=0.0.0019;");
        return sb2.toString();
    }

    public final void c(InputStream inputStream, FileOutputStream fileOutputStream, HttpURLConnection httpURLConnection) {
        long j2;
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll((Map) httpURLConnection.getHeaderFields().entrySet().stream().filter(new d(4)).collect(Collectors.toMap(new C0923a(17), new o(14))));
        List list = (List) treeMap.get(Header.CONTENT_LENGTH);
        long j3 = 0;
        if (list != null) {
            j2 = Long.parseLong((String) list.get(0));
        } else {
            j2 = 0;
        }
        byte[] bArr = new byte[16384];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1 && !this.g) {
                fileOutputStream.write(bArr, 0, read);
                j3 += (long) read;
                int i7 = (int) ((100 * j3) / j2);
                if (i2 < i7) {
                    this.f.accept(Integer.valueOf(i7));
                    i2 = i7;
                }
            } else {
                return;
            }
        }
    }

    public final w d(boolean z) {
        if (this.g) {
            return new w(ScspException.Code.CANCELED, "request has been canceled.");
        }
        String str = this.d;
        if (str != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            try {
                w e7 = e(z, new J5.c(6, fileOutputStream));
                fileOutputStream.close();
                return e7;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            FileDescriptor fileDescriptor = this.e;
            if (fileDescriptor == null) {
                return new w(ScspException.Code.BAD_IMPLEMENTATION, "filePath or fileDescriptor should not be null.");
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(fileDescriptor);
            try {
                w e8 = e(z, new J5.c(6, fileOutputStream2));
                fileOutputStream2.close();
                return e8;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        throw th;
        throw th;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r0v45, types: [java.net.URLConnection] */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0180, code lost:
        if (r12 != null) goto L_0x012f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0175 A[Catch:{ all -> 0x0183 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0186 A[SYNTHETIC, Splitter:B:86:0x0186] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01a1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Sd.w e(boolean r17, java.util.function.Supplier r18) {
        /*
            r16 = this;
            r1 = r16
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r2 = "cloudServerId"
            java.lang.String r3 = r1.b
            r0.putString(r2, r3)
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            if (r17 == 0) goto L_0x0024
            Sd.D r3 = r1.f3697c
            java.lang.String r4 = "getDownloadRawUrl"
            Qb.c r5 = new Qb.c
            r6 = 10
            r5.<init>(r6, r2)
            r3.a(r0, r4, r5)
            goto L_0x0032
        L_0x0024:
            Sd.D r3 = r1.f3697c
            java.lang.String r4 = "getDownloadDefaultUrl"
            Qb.c r5 = new Qb.c
            r6 = 10
            r5.<init>(r6, r2)
            r3.a(r0, r4, r5)
        L_0x0032:
            Sd.m r0 = new Sd.m
            r0.<init>(r2)
            boolean r2 = r0.a()
            if (r2 != 0) goto L_0x003e
            return r0
        L_0x003e:
            Sd.k r2 = new Sd.k
            android.content.Context r3 = r1.f3696a
            java.lang.Object r4 = r18.get()
            java.io.FileOutputStream r4 = (java.io.FileOutputStream) r4
            r2.<init>(r3, r0, r4)
            boolean r4 = r0.e
            r5 = 0
            if (r4 == 0) goto L_0x00aa
            java.lang.Class<Sd.k> r4 = Sd.k.class
            monitor-enter(r4)
            boolean r6 = Sd.j.f3705a     // Catch:{ all -> 0x00a4 }
            if (r6 != 0) goto L_0x00a6
            r6 = 1
            Sd.j.f3705a = r6     // Catch:{ all -> 0x00a4 }
            java.lang.String r6 = "[SCG-SDK][0.0.0019][SCGalleryCipher]"
            java.lang.String r7 = "EDP is preparing .. "
            android.util.Log.i(r6, r7)     // Catch:{ all -> 0x00a4 }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x00a4 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a4 }
            r7.<init>()     // Catch:{ all -> 0x00a4 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ all -> 0x00a4 }
            r7.append(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = java.io.File.separator     // Catch:{ all -> 0x00a4 }
            r7.append(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = "cloud_file_encrypted"
            r7.append(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = r7.toString()     // Catch:{ all -> 0x00a4 }
            r6.<init>(r3)     // Catch:{ all -> 0x00a4 }
            boolean r3 = r6.exists()     // Catch:{ all -> 0x00a4 }
            if (r3 != 0) goto L_0x008a
            r6.mkdir()     // Catch:{ all -> 0x00a4 }
            goto L_0x009c
        L_0x008a:
            java.io.File[] r3 = r6.listFiles()     // Catch:{ all -> 0x00a4 }
            if (r3 == 0) goto L_0x009c
            int r6 = r3.length     // Catch:{ all -> 0x00a4 }
            r7 = r5
        L_0x0092:
            if (r7 >= r6) goto L_0x009c
            r8 = r3[r7]     // Catch:{ all -> 0x00a4 }
            r8.delete()     // Catch:{ all -> 0x0099 }
        L_0x0099:
            int r7 = r7 + 1
            goto L_0x0092
        L_0x009c:
            java.lang.String r3 = "[SCG-SDK][0.0.0019][SCGalleryCipher]"
            java.lang.String r6 = "EDP is ready now .. "
            android.util.Log.i(r3, r6)     // Catch:{ all -> 0x00a4 }
            goto L_0x00a6
        L_0x00a4:
            r0 = move-exception
            goto L_0x00a8
        L_0x00a6:
            monitor-exit(r4)     // Catch:{ all -> 0x00a4 }
            goto L_0x00aa
        L_0x00a8:
            monitor-exit(r4)     // Catch:{ all -> 0x00a4 }
            throw r0
        L_0x00aa:
            java.lang.String r0 = r0.f3721c
            r3 = 60000004(0x3938704, float:8.6708815E-37)
            java.io.FileOutputStream r4 = r2.f3707c     // Catch:{ all -> 0x02e1 }
            Sd.m r6 = r2.b     // Catch:{ all -> 0x02e1 }
            boolean r6 = r6.e     // Catch:{ all -> 0x02e1 }
            if (r6 == 0) goto L_0x00c0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x02e1 }
            java.lang.String r6 = r2.b()     // Catch:{ all -> 0x02e1 }
            r4.<init>(r6)     // Catch:{ all -> 0x02e1 }
        L_0x00c0:
            java.lang.String r6 = "request has been canceled."
            java.lang.String r7 = "[SCG-SDK][0.0.0019]"
            java.lang.String r8 = "download: error. "
            java.lang.String r9 = "download: "
            r10 = 0
            r11 = 70000004(0x42c1d84, float:2.0232056E-36)
            java.net.URL r12 = new java.net.URL     // Catch:{ all -> 0x016e }
            r12.<init>(r0)     // Catch:{ all -> 0x016e }
            java.net.URLConnection r0 = r12.openConnection()     // Catch:{ all -> 0x016e }
            r12 = r0
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ all -> 0x016e }
            java.lang.String r0 = "User-Agent"
            java.lang.String r13 = r1.b()     // Catch:{ all -> 0x0141 }
            r12.setRequestProperty(r0, r13)     // Catch:{ all -> 0x0141 }
            int r0 = r12.getResponseCode()     // Catch:{ all -> 0x0141 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0141 }
            r13.<init>(r9)     // Catch:{ all -> 0x0141 }
            r13.append(r0)     // Catch:{ all -> 0x0141 }
            java.lang.String r9 = r13.toString()     // Catch:{ all -> 0x0141 }
            android.util.Log.d(r7, r9)     // Catch:{ all -> 0x0141 }
            r9 = 200(0xc8, float:2.8E-43)
            if (r0 != r9) goto L_0x0144
            Qb.e r0 = new Qb.e     // Catch:{ all -> 0x0141 }
            r8 = 14
            r0.<init>(r8, r12)     // Catch:{ all -> 0x0141 }
            r1.f3698h = r0     // Catch:{ all -> 0x0141 }
            java.io.InputStream r8 = r12.getInputStream()     // Catch:{ all -> 0x0141 }
            Qb.c r0 = r1.f     // Catch:{ all -> 0x0134 }
            if (r0 != 0) goto L_0x011d
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0134 }
        L_0x010d:
            int r9 = r8.read(r0)     // Catch:{ all -> 0x0134 }
            r13 = -1
            if (r9 == r13) goto L_0x0120
            boolean r13 = r1.g     // Catch:{ all -> 0x0134 }
            if (r13 == 0) goto L_0x0119
            goto L_0x0120
        L_0x0119:
            r4.write(r0, r5, r9)     // Catch:{ all -> 0x0134 }
            goto L_0x010d
        L_0x011d:
            r1.c(r8, r4, r12)     // Catch:{ all -> 0x0134 }
        L_0x0120:
            boolean r0 = r1.g     // Catch:{ all -> 0x0134 }
            if (r0 == 0) goto L_0x0136
            Sd.w r0 = new Sd.w     // Catch:{ all -> 0x0134 }
            r0.<init>(r11, r6)     // Catch:{ all -> 0x0134 }
        L_0x0129:
            Sd.o.j(r8)
            Sd.o.j(r4)
        L_0x012f:
            r12.disconnect()
            goto L_0x019b
        L_0x0134:
            r0 = move-exception
            goto L_0x0171
        L_0x0136:
            java.lang.String r0 = "download: completed."
            android.util.Log.d(r7, r0)     // Catch:{ all -> 0x0134 }
            Sd.w r0 = new Sd.w     // Catch:{ all -> 0x0134 }
            r0.<init>()     // Catch:{ all -> 0x0134 }
            goto L_0x0129
        L_0x0141:
            r0 = move-exception
            r8 = r10
            goto L_0x0171
        L_0x0144:
            java.io.InputStream r5 = r12.getErrorStream()     // Catch:{ all -> 0x0141 }
            java.lang.String r9 = a(r5)     // Catch:{ all -> 0x016b }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x016b }
            r13.<init>(r8)     // Catch:{ all -> 0x016b }
            r13.append(r9)     // Catch:{ all -> 0x016b }
            java.lang.String r8 = r13.toString()     // Catch:{ all -> 0x016b }
            android.util.Log.e(r7, r8)     // Catch:{ all -> 0x016b }
            Sd.w r7 = new Sd.w     // Catch:{ all -> 0x016b }
            r7.<init>(r0, r9)     // Catch:{ all -> 0x016b }
            Sd.o.j(r5)
            Sd.o.j(r4)
            r12.disconnect()
            r0 = r7
            goto L_0x019b
        L_0x016b:
            r0 = move-exception
            r8 = r5
            goto L_0x0171
        L_0x016e:
            r0 = move-exception
            r8 = r10
            r12 = r8
        L_0x0171:
            boolean r1 = r1.g     // Catch:{ all -> 0x0183 }
            if (r1 == 0) goto L_0x0186
            Sd.w r0 = new Sd.w     // Catch:{ all -> 0x0183 }
            r0.<init>(r11, r6)     // Catch:{ all -> 0x0183 }
            Sd.o.j(r8)
            Sd.o.j(r4)
            if (r12 == 0) goto L_0x019b
            goto L_0x012f
        L_0x0183:
            r0 = move-exception
            goto L_0x02d5
        L_0x0186:
            Sd.w r1 = new Sd.w     // Catch:{ all -> 0x0183 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0183 }
            r1.<init>(r3, r0)     // Catch:{ all -> 0x0183 }
            Sd.o.j(r8)
            Sd.o.j(r4)
            if (r12 == 0) goto L_0x019a
            r12.disconnect()
        L_0x019a:
            r0 = r1
        L_0x019b:
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x01a2
            return r0
        L_0x01a2:
            java.lang.String r0 = "requestDecryptFile"
            android.content.Context r1 = r2.f3706a
            java.lang.String r3 = ", actualSize="
            java.lang.String r4 = "decrypt. finished: elapsedTime="
            java.lang.String r5 = "[SCG-SDK][0.0.0019][SCGalleryCipher]"
            java.io.FileOutputStream r6 = r2.f3707c
            java.lang.String r7 = "[decrypt] other error: "
            java.lang.String r8 = "[decrypt] request params build error: "
            Sd.w r9 = new Sd.w
            r9.<init>()
            Sd.m r11 = r2.b
            boolean r11 = r11.e
            if (r11 == 0) goto L_0x02d4
            long r11 = java.lang.System.currentTimeMillis()
            r13 = 0
            java.io.File r9 = new java.io.File     // Catch:{ all -> 0x026e }
            java.lang.String r15 = r2.b()     // Catch:{ all -> 0x026e }
            r9.<init>(r15)     // Catch:{ all -> 0x026e }
            long r13 = r9.length()     // Catch:{ all -> 0x026e }
            Sd.D r15 = new Sd.D     // Catch:{ all -> 0x026e }
            r15.<init>(r1)     // Catch:{ all -> 0x026e }
            Jd.d r1 = new Jd.d     // Catch:{ all -> 0x026e }
            r1.<init>(r9, r6)     // Catch:{ all -> 0x026e }
            r1.b()     // Catch:{ all -> 0x026e }
            int r9 = r1.d     // Catch:{ all -> 0x022a }
            r10 = 20000000(0x1312d00, float:3.2542052E-38)
            if (r9 != r10) goto L_0x022e
            android.os.Bundle r8 = new android.os.Bundle     // Catch:{ all -> 0x022a }
            r8.<init>()     // Catch:{ all -> 0x022a }
            java.lang.Object r9 = r1.f     // Catch:{ all -> 0x022a }
            android.os.Bundle r9 = (android.os.Bundle) r9     // Catch:{ all -> 0x022a }
            Qb.c r10 = new Qb.c     // Catch:{ all -> 0x022a }
            r17 = r2
            r2 = 10
            r10.<init>(r2, r8)     // Catch:{ all -> 0x0227 }
            r15.a(r9, r0, r10)     // Catch:{ all -> 0x0227 }
            Sd.w r9 = new Sd.w     // Catch:{ all -> 0x0227 }
            r9.<init>(r8)     // Catch:{ all -> 0x0227 }
            long r7 = java.lang.System.currentTimeMillis()
            long r7 = r7 - r11
            java.lang.StringBuilder r0 = N2.j.j(r7, r4, r3)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r5, r0)
            java.lang.Object r0 = r1.f3475h
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            Sd.o.j(r0)
            java.lang.Object r0 = r1.f3476i
        L_0x021a:
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            Sd.o.j(r0)
        L_0x021f:
            Sd.o.j(r6)
            r17.a()
            goto L_0x02d4
        L_0x0227:
            r0 = move-exception
        L_0x0228:
            r10 = r1
            goto L_0x0271
        L_0x022a:
            r0 = move-exception
            r17 = r2
            goto L_0x0228
        L_0x022e:
            r17 = r2
            java.lang.Comparable r0 = r1.g     // Catch:{ all -> 0x0227 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0227 }
            Sd.w r2 = new Sd.w     // Catch:{ all -> 0x0227 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0227 }
            r10.<init>(r8)     // Catch:{ all -> 0x0227 }
            r10.append(r0)     // Catch:{ all -> 0x0227 }
            java.lang.String r0 = r10.toString()     // Catch:{ all -> 0x0227 }
            r2.<init>(r9, r0)     // Catch:{ all -> 0x0227 }
            long r7 = java.lang.System.currentTimeMillis()
            long r7 = r7 - r11
            java.lang.StringBuilder r0 = N2.j.j(r7, r4, r3)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r5, r0)
            java.lang.Object r0 = r1.f3475h
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            Sd.o.j(r0)
            java.lang.Object r0 = r1.f3476i
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            Sd.o.j(r0)
            Sd.o.j(r6)
            r17.a()
            r9 = r2
            goto L_0x02d4
        L_0x026e:
            r0 = move-exception
            r17 = r2
        L_0x0271:
            Sd.w r9 = new Sd.w     // Catch:{ all -> 0x02a9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a9 }
            r1.<init>(r7)     // Catch:{ all -> 0x02a9 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x02a9 }
            r1.append(r0)     // Catch:{ all -> 0x02a9 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x02a9 }
            r1 = 80000000(0x4c4b400, float:4.6244682E-36)
            r9.<init>(r1, r0)     // Catch:{ all -> 0x02a9 }
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 - r11
            java.lang.StringBuilder r0 = N2.j.j(r0, r4, r3)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r5, r0)
            if (r10 == 0) goto L_0x021f
            java.lang.Object r0 = r10.f3475h
            android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            Sd.o.j(r0)
            java.lang.Object r0 = r10.f3476i
            goto L_0x021a
        L_0x02a9:
            r0 = move-exception
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r11
            java.lang.StringBuilder r1 = N2.j.j(r1, r4, r3)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r5, r1)
            if (r10 == 0) goto L_0x02cd
            java.lang.Object r1 = r10.f3475h
            android.os.ParcelFileDescriptor r1 = (android.os.ParcelFileDescriptor) r1
            Sd.o.j(r1)
            java.lang.Object r1 = r10.f3476i
            android.os.ParcelFileDescriptor r1 = (android.os.ParcelFileDescriptor) r1
            Sd.o.j(r1)
        L_0x02cd:
            Sd.o.j(r6)
            r17.a()
            throw r0
        L_0x02d4:
            return r9
        L_0x02d5:
            Sd.o.j(r8)
            Sd.o.j(r4)
            if (r12 == 0) goto L_0x02e0
            r12.disconnect()
        L_0x02e0:
            throw r0
        L_0x02e1:
            r0 = move-exception
            Sd.w r1 = new Sd.w
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Sd.C0837b.e(boolean, java.util.function.Supplier):Sd.w");
    }
}
