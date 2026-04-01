package com.samsung.scsp.framework.core.network.base;

import B8.b;
import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.DefaultErrorListener;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NetworkImpl implements Network {
    private static final String DELETE = "DELETE";
    private static final String GET = "GET";
    private static final String HEAD = "HEAD";
    private static final String LINE_FEED = "\r\n";
    private static final String PATCH = "PATCH";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static volatile SSLContext sslContext = null;
    private static final PayloadWriterVisitor<OutputStream> visitor = new PayloadWriterVisitorImpl();
    private final Object CLOSING_LOCK = new Object();
    private final Queue<HttpURLConnection> connectionQueue = new ConcurrentLinkedQueue();
    private final Network.ErrorListener errorListener = new DefaultErrorListener();
    private boolean isClosing = false;
    private final Logger logger = Logger.get("NetworkImpl");
    private final Predicate<String> networkPolicyPredicate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ConnectionSetter {
        void setup(HttpURLConnection httpURLConnection);
    }

    public NetworkImpl(Predicate<String> predicate) {
        if (predicate != null) {
            this.networkPolicyPredicate = predicate;
        } else {
            this.networkPolicyPredicate = new c(1);
        }
    }

    private void checkNetworkClosing() {
        synchronized (this.CLOSING_LOCK) {
            try {
                if (this.isClosing) {
                    throw new ScspException(60000004, "Network is closing, try again later.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void closeSilently(Closeable closeable) {
        Objects.requireNonNull(closeable);
        FaultBarrier.run(new g(1, closeable), true);
    }

    private void configureConnection(HttpURLConnection httpURLConnection, HttpRequest httpRequest, Map<String, String> map) {
        httpURLConnection.setReadTimeout(httpRequest.getTimeOut());
        httpURLConnection.setConnectTimeout(httpRequest.getTimeOut());
        for (Map.Entry next : map.entrySet()) {
            httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
        }
    }

    private static void disconnect(HttpURLConnection httpURLConnection) {
        FaultBarrier.run(new f(0, httpURLConnection), true);
        FaultBarrier.run(new f(1, httpURLConnection), true);
        FaultBarrier.run(new f(2, httpURLConnection), true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.net.HttpURLConnection, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0175 A[SYNTHETIC, Splitter:B:51:0x0175] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017a  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void execute(com.samsung.scsp.framework.core.network.HttpRequest r11, com.samsung.scsp.framework.core.network.base.NetworkImpl.ConnectionSetter r12, com.samsung.scsp.framework.core.network.Network.StreamListener r13) {
        /*
            r10 = this;
            java.lang.String r0 = "Connection is removed."
            java.lang.String r1 = "["
            java.util.function.Predicate<java.lang.String> r2 = r10.networkPolicyPredicate
            java.lang.String r3 = r11.getUrl()
            boolean r2 = r2.test(r3)
            if (r2 == 0) goto L_0x0195
            com.samsung.scsp.framework.core.network.base.b r2 = new com.samsung.scsp.framework.core.network.base.b
            r3 = 0
            r2.<init>(r11, r3)
            com.samsung.scsp.error.FaultBarrier.run(r2)
            r2 = 1
            r3 = 0
            android.net.TrafficStats.setThreadStatsTag(r2)     // Catch:{ IOException -> 0x0152, ScspException -> 0x014f, all -> 0x014c }
            java.net.HttpURLConnection r2 = r10.getConnection(r11)     // Catch:{ IOException -> 0x0152, ScspException -> 0x014f, all -> 0x014c }
            r12.setup(r2)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            int r4 = r2.getResponseCode()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.TreeMap r5 = new java.util.TreeMap     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.Comparator r6 = java.lang.String.CASE_INSENSITIVE_ORDER     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.stream.Stream r6 = r6.stream()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.framework.core.network.base.c r7 = new com.samsung.scsp.framework.core.network.base.c     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r8 = 0
            r7.<init>(r8)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.stream.Stream r6 = r6.filter(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.android.sum.core.buffer.a r7 = new com.samsung.android.sum.core.buffer.a     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r8 = 17
            r7.<init>(r8)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            O3.o r8 = new O3.o     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r9 = 14
            r8.<init>(r9)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.stream.Collector r7 = java.util.stream.Collectors.toMap(r7, r8)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.Object r6 = r6.collect(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r5.putAll(r6)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r6 = "HTTP_STATUS"
            java.lang.String r7 = java.lang.Integer.toString(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String[] r7 = new java.lang.String[]{r7}     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.util.List r7 = java.util.Arrays.asList(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r5.put(r6, r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.framework.core.listeners.NetworkStatusListener r6 = r11.getNetworkStatusListener()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            boolean r7 = r6 instanceof com.samsung.scsp.framework.core.listeners.NetworkStatusAndProtocolListener     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r7 == 0) goto L_0x008b
            com.samsung.scsp.framework.core.listeners.NetworkStatusAndProtocolListener r6 = (com.samsung.scsp.framework.core.listeners.NetworkStatusAndProtocolListener) r6     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r7 = "http/1.1"
            r6.onNegotiatedProtocol(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            goto L_0x008b
        L_0x0082:
            r12 = move-exception
            goto L_0x0173
        L_0x0085:
            r12 = move-exception
            goto L_0x0155
        L_0x0088:
            r12 = move-exception
            goto L_0x015f
        L_0x008b:
            java.util.function.Predicate r6 = com.samsung.scsp.framework.core.network.ResponseUtil.responsible()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            boolean r6 = r6.test(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r6 == 0) goto L_0x00cf
            java.lang.String r12 = "Content-Encoding"
            java.lang.String r12 = r2.getHeaderField(r12)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r13 == 0) goto L_0x0123
            boolean r1 = android.text.TextUtils.isEmpty(r12)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r1 != 0) goto L_0x00c6
            java.lang.String r1 = "gzip"
            java.util.Locale r4 = java.util.Locale.getDefault()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r12 = r12.toLowerCase(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r12 = r12.trim()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            boolean r12 = r1.equals(r12)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r12 == 0) goto L_0x00c6
            java.util.zip.GZIPInputStream r12 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.io.InputStream r1 = r2.getInputStream()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r12.<init>(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
        L_0x00c4:
            r3 = r12
            goto L_0x00cb
        L_0x00c6:
            java.io.InputStream r12 = r2.getInputStream()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            goto L_0x00c4
        L_0x00cb:
            r13.onStream(r11, r5, r3)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            goto L_0x0123
        L_0x00cf:
            java.util.function.Predicate r6 = com.samsung.scsp.framework.core.network.ResponseUtil.redirected()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            boolean r6 = r6.test(r7)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r6 == 0) goto L_0x011a
            java.lang.String r4 = "Location"
            java.lang.String r4 = r2.getHeaderField(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.error.Logger r5 = r10.logger     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r6.<init>(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r1 = r11.getName()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r6.append(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r1 = "]["
            r6.append(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            int r1 = r11.hashCode()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r6.append(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r1 = "][redirected]"
            r6.append(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.lang.String r1 = r6.toString()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r5.i(r1)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.error.Logger r1 = r10.logger     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.framework.core.network.base.d r5 = new com.samsung.scsp.framework.core.network.base.d     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r5.<init>((com.samsung.scsp.framework.core.network.HttpRequest) r11, (java.lang.String) r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r1.d(r5)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r11.setUrl(r4)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r10.execute(r11, r12, r13)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            goto L_0x0123
        L_0x011a:
            com.samsung.scsp.framework.core.network.Network$ErrorListener r12 = r10.errorListener     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            java.io.InputStream r13 = r2.getErrorStream()     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r12.onError(r11, r5, r4, r13)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
        L_0x0123:
            com.samsung.scsp.framework.core.network.base.b r12 = new com.samsung.scsp.framework.core.network.base.b     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            r13 = 1
            r12.<init>(r11, r13)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            com.samsung.scsp.error.FaultBarrier.run(r12)     // Catch:{ IOException -> 0x0088, ScspException -> 0x0085 }
            if (r3 == 0) goto L_0x0131
            r3.close()     // Catch:{ IOException -> 0x0131 }
        L_0x0131:
            disconnect(r2)
            java.util.Queue<java.net.HttpURLConnection> r12 = r10.connectionQueue
            r12.remove(r2)
            com.samsung.scsp.error.Logger r10 = r10.logger
            r10.i(r0)
            com.samsung.scsp.framework.core.listeners.NetworkStatusListener r10 = r11.getNetworkStatusListener()
            if (r10 == 0) goto L_0x014b
            int r11 = r2.hashCode()
            r10.onClosed(r11)
        L_0x014b:
            return
        L_0x014c:
            r12 = move-exception
            r2 = r3
            goto L_0x0173
        L_0x014f:
            r12 = move-exception
            r2 = r3
            goto L_0x0155
        L_0x0152:
            r12 = move-exception
            r2 = r3
            goto L_0x015f
        L_0x0155:
            com.samsung.scsp.framework.core.network.base.a r13 = new com.samsung.scsp.framework.core.network.base.a     // Catch:{ all -> 0x0082 }
            r1 = 3
            r13.<init>(r11, r12, r1)     // Catch:{ all -> 0x0082 }
            com.samsung.scsp.error.FaultBarrier.run(r13)     // Catch:{ all -> 0x0082 }
            throw r12     // Catch:{ all -> 0x0082 }
        L_0x015f:
            com.samsung.scsp.framework.core.network.base.b r13 = new com.samsung.scsp.framework.core.network.base.b     // Catch:{ all -> 0x0082 }
            r1 = 2
            r13.<init>(r11, r1)     // Catch:{ all -> 0x0082 }
            com.samsung.scsp.error.FaultBarrier.run(r13)     // Catch:{ all -> 0x0082 }
            com.samsung.scsp.framework.core.ScspException r13 = new com.samsung.scsp.framework.core.ScspException     // Catch:{ all -> 0x0082 }
            java.lang.String r1 = "IOException occurred during network use."
            r4 = 60000004(0x3938704, float:8.6708815E-37)
            r13.<init>(r4, r1, r12)     // Catch:{ all -> 0x0082 }
            throw r13     // Catch:{ all -> 0x0082 }
        L_0x0173:
            if (r3 == 0) goto L_0x0178
            r3.close()     // Catch:{ IOException -> 0x0178 }
        L_0x0178:
            if (r2 == 0) goto L_0x0194
            disconnect(r2)
            java.util.Queue<java.net.HttpURLConnection> r13 = r10.connectionQueue
            r13.remove(r2)
            com.samsung.scsp.error.Logger r10 = r10.logger
            r10.i(r0)
            com.samsung.scsp.framework.core.listeners.NetworkStatusListener r10 = r11.getNetworkStatusListener()
            if (r10 == 0) goto L_0x0194
            int r11 = r2.hashCode()
            r10.onClosed(r11)
        L_0x0194:
            throw r12
        L_0x0195:
            com.samsung.scsp.framework.core.ScspException r10 = new com.samsung.scsp.framework.core.ScspException
            r11 = 80300002(0x4c947e2, float:4.7320887E-36)
            java.lang.String r12 = "Network is not allowed."
            r10.<init>(r11, r12)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.scsp.framework.core.network.base.NetworkImpl.execute(com.samsung.scsp.framework.core.network.HttpRequest, com.samsung.scsp.framework.core.network.base.NetworkImpl$ConnectionSetter, com.samsung.scsp.framework.core.network.Network$StreamListener):void");
    }

    private HttpURLConnection getConnection(HttpRequest httpRequest) {
        HashMap hashMap = new HashMap();
        int headerCount = httpRequest.getHeaderCount();
        for (int i2 = 0; i2 < headerCount; i2++) {
            hashMap.put(httpRequest.getHeaderKey(i2), httpRequest.getHeaderValue(i2));
        }
        this.logger.d(new d(hashMap, httpRequest));
        this.logger.i(String.format("[%s][%s][%s][%s]", new Object[]{httpRequest.getName(), Integer.valueOf(httpRequest.hashCode()), httpRequest.getUrl(), ScspCorePreferences.get().registrationId.get()}));
        try {
            URL url = new URL(httpRequest.getUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (url.getProtocol().equals("https")) {
                this.logger.i("Create SSLContext.");
                SSLContext sSLContext = SSLContextFactory.get();
                if (sSLContext != null) {
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLContext.getSocketFactory());
                }
            }
            this.connectionQueue.add(httpURLConnection);
            this.logger.i("Connection is added.");
            httpURLConnection.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            configureConnection(httpURLConnection, httpRequest, hashMap);
            NetworkStatusListener networkStatusListener = httpRequest.getNetworkStatusListener();
            if (networkStatusListener != null) {
                networkStatusListener.onStarted(httpURLConnection.hashCode());
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new ScspException(60000004, "IOException occurred during network use.", e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$close$4(HttpURLConnection httpURLConnection) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$close$5(int i2, HttpURLConnection httpURLConnection) {
        if (httpURLConnection.hashCode() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$close$6(Predicate predicate) {
        synchronized (this.CLOSING_LOCK) {
            this.isClosing = true;
            this.logger.i("close request.");
            for (HttpURLConnection next : this.connectionQueue) {
                Logger logger2 = this.logger;
                logger2.i("finding connection to close." + next.hashCode());
                if (predicate.test(next)) {
                    try {
                        if (next.getDoOutput()) {
                            this.logger.i("conn has output, will be close...");
                            OutputStream outputStream = next.getOutputStream();
                            if (outputStream != null) {
                                outputStream.close();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    disconnect(next);
                    this.logger.i("Connection is closed.");
                }
            }
            this.isClosing = false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$execute$8(Map.Entry entry) {
        if (entry.getKey() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$execute$9(HttpRequest httpRequest, String str) {
        return "[" + httpRequest.getName() + "][" + httpRequest.hashCode() + "][" + str + "][redirected]";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getConnection$7(Map map, HttpRequest httpRequest) {
        StringBuilder sb2 = new StringBuilder();
        for (String str : map.keySet()) {
            sb2.append(str);
            sb2.append(':');
            sb2.append((String) map.get(str));
            sb2.append(',');
        }
        if (sb2.length() > 0) {
            return "[" + httpRequest.getName() + "][" + httpRequest.hashCode() + "][ header - " + sb2.substring(0, sb2.length() - 1) + " ]";
        }
        return "[" + httpRequest.getName() + "][" + httpRequest.hashCode() + "][ header - NONE]";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$patch$3(HttpRequest httpRequest, Network.StreamListener streamListener, HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("X-HTTP-Method-Override", PATCH);
        httpURLConnection.setRequestMethod(PATCH);
        httpURLConnection.setDoOutput(true);
        if (httpRequest.getSupportChunkedStreaming()) {
            httpURLConnection.setChunkedStreamingMode(0);
        }
        httpURLConnection.setRequestProperty(ContentType.KEY, httpRequest.getContentType(0));
        Object content = httpRequest.getContent(0);
        if (content != null) {
            PayloadWriterVisitor.Payload payload = new PayloadWriterVisitor.Payload();
            payload.httpRequest = httpRequest;
            payload.streamListener = streamListener;
            payload.output = httpURLConnection.getOutputStream();
            payload.content = content;
            httpRequest.getPayloadWriter(0).accept(payload, visitor);
        }
        httpURLConnection.getOutputStream().close();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$post$1(HttpRequest httpRequest, Network.StreamListener streamListener, HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestMethod(POST);
        httpURLConnection.setDoOutput(true);
        if (httpRequest.getSupportChunkedStreaming()) {
            httpURLConnection.setChunkedStreamingMode(0);
        }
        int partCount = httpRequest.getPartCount();
        if (partCount > 1) {
            String boundary = httpRequest.getBoundary();
            String charset = httpRequest.getCharset();
            httpURLConnection.setRequestProperty(ContentType.KEY, "multipart/form-data; boundary=" + boundary);
            T outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, charset);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            printWriter.append(LINE_FEED);
            printWriter.append("--").append(boundary);
            try {
                PayloadWriterVisitor.Payload payload = new PayloadWriterVisitor.Payload();
                payload.httpRequest = httpRequest;
                payload.streamListener = streamListener;
                payload.output = outputStream;
                for (int i2 = 0; i2 < partCount; i2++) {
                    Map<String, String> partHeaders = httpRequest.getPartHeaders(i2);
                    if (partHeaders != null) {
                        for (String next : partHeaders.keySet()) {
                            printWriter.append(LINE_FEED);
                            printWriter.append(next).append(": ").append(partHeaders.get(next));
                        }
                        printWriter.append(LINE_FEED);
                        printWriter.append(ContentType.KEY).append(": ").append(httpRequest.getContentType(i2));
                        printWriter.append(LINE_FEED).append(LINE_FEED);
                        printWriter.flush();
                    }
                    payload.content = httpRequest.getContent(i2);
                    httpRequest.getPayloadWriter(i2).accept(payload, visitor);
                    printWriter.append(LINE_FEED).append("--").append(boundary);
                    printWriter.flush();
                }
                printWriter.append("--");
                printWriter.append(LINE_FEED);
                printWriter.flush();
                printWriter.close();
                outputStreamWriter.close();
            } catch (Throwable th) {
                printWriter.close();
                outputStreamWriter.close();
                throw th;
            }
        } else {
            httpURLConnection.setRequestProperty(ContentType.KEY, httpRequest.getContentType(0));
            PayloadWriterVisitor.Payload payload2 = new PayloadWriterVisitor.Payload();
            payload2.httpRequest = httpRequest;
            payload2.streamListener = streamListener;
            payload2.output = httpURLConnection.getOutputStream();
            payload2.transferred = httpRequest.getRange();
            Object content = httpRequest.getContent(0);
            if (content != null) {
                payload2.content = content;
                payload2.length = httpRequest.getLength();
                httpRequest.getPayloadWriter(0).accept(payload2, visitor);
            }
        }
        httpURLConnection.getOutputStream().close();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$put$2(HttpRequest httpRequest, Network.StreamListener streamListener, HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestMethod(PUT);
        httpURLConnection.setDoOutput(true);
        if (httpRequest.getSupportChunkedStreaming()) {
            httpURLConnection.setChunkedStreamingMode(0);
        }
        httpURLConnection.setRequestProperty(ContentType.KEY, httpRequest.getContentType(0));
        Object content = httpRequest.getContent(0);
        if (content != null) {
            PayloadWriterVisitor.Payload payload = new PayloadWriterVisitor.Payload();
            payload.httpRequest = httpRequest;
            payload.streamListener = streamListener;
            payload.transferred = httpRequest.getRange();
            payload.output = httpURLConnection.getOutputStream();
            payload.content = content;
            payload.length = httpRequest.getLength();
            httpRequest.getPayloadWriter(0).accept(payload, visitor);
        }
        httpURLConnection.getOutputStream().close();
    }

    private void simpleMethod(String str, HttpRequest httpRequest, Network.StreamListener streamListener) {
        this.logger.i(str);
        checkNetworkClosing();
        execute(httpRequest, new g(0, str), streamListener);
    }

    public void close() {
        close((Predicate<HttpURLConnection>) new c(2));
    }

    public void delete(HttpRequest httpRequest, Network.StreamListener streamListener) {
        simpleMethod(DELETE, httpRequest, streamListener);
    }

    public void get(HttpRequest httpRequest, Network.StreamListener streamListener) {
        simpleMethod(GET, httpRequest, streamListener);
    }

    public void head(HttpRequest httpRequest, Network.StreamListener streamListener) {
        simpleMethod(HEAD, httpRequest, streamListener);
    }

    public void patch(HttpRequest httpRequest, Network.StreamListener streamListener) {
        this.logger.i("patch");
        checkNetworkClosing();
        execute(httpRequest, new a(httpRequest, streamListener, 1), streamListener);
    }

    public void post(HttpRequest httpRequest, Network.StreamListener streamListener) {
        this.logger.i("post");
        checkNetworkClosing();
        execute(httpRequest, new a(httpRequest, streamListener, 0), streamListener);
    }

    public void put(HttpRequest httpRequest, Network.StreamListener streamListener) {
        this.logger.i("put");
        checkNetworkClosing();
        execute(httpRequest, new a(httpRequest, streamListener, 2), streamListener);
    }

    public void close(int i2) {
        close((Predicate<HttpURLConnection>) new b(i2, 23));
    }

    private void close(Predicate<HttpURLConnection> predicate) {
        new Thread(new e(this, predicate), "CLOSE_NETWORK").start();
    }
}
