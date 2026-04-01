package com.samsung.scsp.framework.core;

import O3.b;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.scs.ai.language.a;
import com.samsung.scsp.common.Header;
import com.samsung.scsp.common.Holder;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.api.Response;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;
import com.samsung.scsp.framework.core.identity.ScspIdentity;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import com.samsung.scsp.framework.core.network.HttpRequest;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.util.StringUtil;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultErrorListener implements Network.ErrorListener {
    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$onError$0(Map map) {
        return (String) ((List) map.get(Header.CONTENT_ENCODING)).get(0);
    }

    public void handleRegistrationRequired(ScspException scspException) {
        if (scspException.rcode == 40001001) {
            ScspIdentity.onRegistrationRequired();
        }
    }

    public void handleUnauthenticatedForSC(ScspException scspException, HttpRequest httpRequest) {
        String str;
        int i2 = scspException.rcode;
        if (i2 == 40100002 || i2 == 101000) {
            int headerCount = httpRequest.getHeaderCount();
            int i7 = 0;
            while (true) {
                if (i7 >= headerCount) {
                    str = "";
                    break;
                } else if (StringUtil.equals(HeaderSetup.Key.AUTHORIZATION, httpRequest.getHeaderKey(i7))) {
                    str = httpRequest.getHeaderValue(i7);
                    break;
                } else {
                    i7++;
                }
            }
            ScspIdentity.onUnauthenticatedForSC(str);
        }
    }

    public void onError(HttpRequest httpRequest, Map<String, List<String>> map, int i2, InputStream inputStream) {
        ScspException scspException;
        Holder holder = new Holder();
        if (inputStream != null) {
            String str = (String) FaultBarrier.get(new a(11, map), "", true).obj;
            if (TextUtils.isEmpty(str) || !Header.GZIP.equals(str.toLowerCase(Locale.getDefault()).trim())) {
                holder.hold(new Response(inputStream).toString());
            } else {
                FaultBarrier.run(new b(22, holder, inputStream));
            }
        }
        if (StringUtil.isEmpty((CharSequence) holder.get())) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(Contract.HEADERS, map.toString());
            holder.hold(jsonObject.toString());
        }
        Logger.get(httpRequest.getName()).e(String.format("[%s][%s][%s] %s", new Object[]{Integer.valueOf(httpRequest.hashCode()), httpRequest.getUrl(), ScspCorePreferences.get().registrationId.get(), holder.get()}));
        if (!httpRequest.isSilent()) {
            try {
                scspException = (ScspException) new Gson().fromJson((String) holder.get(), ScspException.class);
            } catch (Throwable unused) {
                scspException = new ScspException(ScspException.Code.BAD_IMPLEMENTATION, (String) holder.get());
            }
            scspException.headers = map;
            scspException.status = i2;
            scspException.errorString = (String) holder.get();
            handleRegistrationRequired(scspException);
            handleUnauthenticatedForSC(scspException, httpRequest);
            throw scspException;
        }
    }
}
