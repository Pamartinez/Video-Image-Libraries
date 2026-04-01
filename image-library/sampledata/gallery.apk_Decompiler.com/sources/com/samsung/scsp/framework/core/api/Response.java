package com.samsung.scsp.framework.core.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.ScspException;
import com.samsung.scsp.framework.core.util.JsonUtil;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Response {
    public static final String RCODE = "rcode";
    public static final String RMSG = "rmsg";
    public InputStream inputStream;
    private JsonObject json = null;
    private int rcode = -1;
    private String string = null;

    public Response(InputStream inputStream2) {
        this.inputStream = inputStream2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toString$0() {
        JsonObject jsonObject = this.json;
        if (jsonObject != null) {
            this.string = jsonObject.toString();
        } else {
            this.string = StreamParser.parseString(this.inputStream);
        }
    }

    public <T> T create(Class<T> cls) {
        try {
            Gson gson = new Gson();
            String str = this.string;
            if (str != null) {
                return gson.fromJson(str, cls);
            }
            JsonObject jsonObject = this.json;
            if (jsonObject != null) {
                return gson.fromJson((JsonElement) jsonObject, cls);
            }
            return gson.fromJson(new JsonReader(new InputStreamReader(this.inputStream)), (Type) cls);
        } catch (JsonIOException | JsonSyntaxException e) {
            throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "An error occurred in the process of Json parsing.", e);
        }
    }

    public int getRcode() {
        if (this.rcode == -1) {
            if (this.json == null) {
                toJson();
            }
            this.rcode = this.json.get("rcode").getAsInt();
        }
        return this.rcode;
    }

    public JsonObject toJson() {
        if (this.json == null) {
            String str = this.string;
            if (str != null) {
                this.json = JsonUtil.toJson(str);
            } else {
                this.json = JsonUtil.toJson(this.inputStream);
            }
        }
        return this.json;
    }

    public String toString() {
        if (this.string == null) {
            FaultBarrier.run(new a(3, this));
        }
        return this.string;
    }
}
