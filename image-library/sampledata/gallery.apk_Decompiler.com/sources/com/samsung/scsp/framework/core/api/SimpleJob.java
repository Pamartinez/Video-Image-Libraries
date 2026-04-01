package com.samsung.scsp.framework.core.api;

import android.util.Pair;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.framework.core.SContext;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.decorator.SdkConfig;
import com.samsung.scsp.framework.core.ers.DomainVo;
import com.samsung.scsp.framework.core.ers.ScspErs;
import com.samsung.scsp.framework.core.listeners.Listeners;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.HttpRequestImpl;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.StringUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleJob implements Job {
    private static final Map<Property, Consumer<HttpRequestImpl.HttpRequestBuilder>> PROPERTY_MAP = new HashMap<Property, Consumer<HttpRequestImpl.HttpRequestBuilder>>() {
        {
            put(Property.Localed, new f(0));
            put(Property.GzipEncoded, new f(1));
            put(Property.None, new f(2));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$3(HttpRequestImpl.HttpRequestBuilder httpRequestBuilder) {
        }
    };
    private static final Pattern RESOURCES = Pattern.compile("\\{(.*?)\\}");
    private final String apiPath;
    private final List<Function<ApiContext, Pair<String, String>[]>> headerFunctions = new ArrayList();
    private Supplier<Pair<String, String>> journalSupplier;
    private final HttpRequest.Method method;
    protected final String name;
    private final List<Property> properties = new ArrayList();
    private final List<String> resourceNames = new ArrayList();
    private BiFunction<ApiContext, String, String> urlFunction;

    /* renamed from: com.samsung.scsp.framework.core.api.SimpleJob$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.scsp.framework.core.network.HttpRequest$Method[] r0 = com.samsung.scsp.framework.core.network.HttpRequest.Method.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method = r0
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.POST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.PUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.GET     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.DELETE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.PATCH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.samsung.scsp.framework.core.network.HttpRequest$Method r1 = com.samsung.scsp.framework.core.network.HttpRequest.Method.HEAD     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.scsp.framework.core.api.SimpleJob.AnonymousClass2.<clinit>():void");
        }
    }

    public SimpleJob(HttpRequest.Method method2, String str, String str2) {
        this.name = str;
        this.apiPath = str2;
        this.method = method2;
        Matcher matcher = RESOURCES.matcher(str2);
        while (matcher.find()) {
            this.resourceNames.add(matcher.group(1));
        }
    }

    public void attachHeaderFunction(Function<ApiContext, Pair<String, String>[]> function) {
        this.headerFunctions.add(function);
    }

    public void attachJournalSupplier(Supplier<Pair<String, String>> supplier) {
        this.journalSupplier = supplier;
    }

    public void attachProperties(Property[] propertyArr) {
        if (propertyArr != null) {
            this.properties.addAll(Arrays.asList(propertyArr));
        }
    }

    public void attachUrlFunction(BiFunction<ApiContext, String, String> biFunction) {
        this.urlFunction = biFunction;
    }

    public HttpRequest createRequest(ApiContext apiContext, Listeners listeners) {
        Pair pair;
        String apiUrl = getApiUrl(apiContext);
        BiFunction<ApiContext, String, String> biFunction = this.urlFunction;
        if (biFunction != null) {
            apiUrl = biFunction.apply(apiContext, apiUrl);
        }
        HttpRequestImpl.HttpRequestBuilder networkStatusListener = getHttpRequestBuilder(apiContext, apiUrl).responseListener(listeners.responseListener).progressListener(listeners.progressListener).networkStatusListener(listeners.networkStatusListener);
        if (!StringUtil.isEmpty(apiContext.payload)) {
            networkStatusListener.payload("application/json;charset=UTF-8", apiContext.payload);
        }
        if (!StringUtil.isEmpty(apiContext.etag)) {
            networkStatusListener.addHeader(Header.IF_NONE_MATCH, apiContext.etag);
        }
        if (!StringUtil.isEmpty(apiContext.invoker)) {
            networkStatusListener.addHeader(Header.INVOKER, apiContext.invoker);
        }
        for (Function<ApiContext, Pair<String, String>[]> apply : this.headerFunctions) {
            Pair[] pairArr = (Pair[]) apply.apply(apiContext);
            if (pairArr != null) {
                for (Pair pair2 : pairArr) {
                    networkStatusListener.addHeader((String) pair2.first, (String) pair2.second);
                }
            }
        }
        for (Property property : this.properties) {
            PROPERTY_MAP.get(property).accept(networkStatusListener);
        }
        Supplier<Pair<String, String>> supplier = this.journalSupplier;
        if (supplier == null || (pair = supplier.get()) == null) {
            return networkStatusListener.build();
        }
        return networkStatusListener.build().journal((String) pair.first, (String) pair.second);
    }

    public final void execute(ApiContext apiContext, Listeners listeners) {
        Network network = apiContext.scontextHolder.network;
        HttpRequest createRequest = createRequest(apiContext, listeners);
        switch (AnonymousClass2.$SwitchMap$com$samsung$scsp$framework$core$network$HttpRequest$Method[createRequest.getMethod().ordinal()]) {
            case 1:
                network.post(createRequest, getStreamListener());
                return;
            case 2:
                network.put(createRequest, getStreamListener());
                return;
            case 3:
                network.get(createRequest, getStreamListener());
                return;
            case 4:
                network.delete(createRequest, getStreamListener());
                return;
            case 5:
                network.patch(createRequest, getStreamListener());
                return;
            case 6:
                network.head(createRequest, getStreamListener());
                return;
            default:
                return;
        }
    }

    public String getApiUrl(ApiContext apiContext) {
        SContext sContext = apiContext.scontext;
        String str = this.apiPath;
        for (String next : this.resourceNames) {
            String asString = apiContext.parameters.getAsString(next);
            if (!StringUtil.isEmpty(asString)) {
                str = str.replace("{" + next + "}", asString);
            } else {
                throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, C0212a.l("There is no required parameter. ", next));
            }
        }
        DomainVo domain = ScspErs.getDomain(apiContext.scontextHolder.network);
        if (apiContext.scontextHolder.domain == SdkConfig.Domain.play) {
            return C0212a.p(new StringBuilder(), domain.playUrl, str);
        }
        return C0212a.p(new StringBuilder(), domain.defaultUrl, str);
    }

    public final HttpRequestImpl.HttpRequestBuilder getHttpRequestBuilder(ApiContext apiContext, String str) {
        return new HttpRequestImpl.HttpRequestBuilder(apiContext.scontextHolder, this.method, str).name(this.name);
    }

    public String getName() {
        return this.name;
    }

    public Network.StreamListener getStreamListener() {
        return null;
    }
}
