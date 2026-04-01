package com.samsung.scsp.framework.core.identity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushInfoList {
    private static final String ID = "id";
    private static final String TOKEN = "token";
    private static final String TYPE = "type";
    private List<PushInfo> pushInfoList;

    public PushInfoList(PushInfo[] pushInfoArr) {
        ArrayList arrayList = new ArrayList();
        this.pushInfoList = arrayList;
        if (pushInfoArr != null) {
            arrayList.addAll(Arrays.asList(pushInfoArr));
        }
    }

    private List<PushInfo> jsonArrayToArrayList(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList();
        jsonArray.iterator().forEachRemaining(new h(arrayList, new Gson()));
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toJsonArray$0(JsonArray jsonArray, PushInfo pushInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", pushInfo.getId());
        jsonObject.addProperty("type", pushInfo.getType());
        jsonObject.addProperty(TOKEN, pushInfo.getToken());
        jsonArray.add((JsonElement) jsonObject);
    }

    public void add(PushInfo pushInfo) {
        this.pushInfoList.add(pushInfo);
    }

    public List<PushInfo> getPushInfoList() {
        return this.pushInfoList;
    }

    public JsonArray toJsonArray() {
        JsonArray jsonArray = new JsonArray();
        this.pushInfoList.forEach(new g(jsonArray));
        return jsonArray;
    }

    public PushInfoList(String str) {
        this.pushInfoList = new ArrayList();
        this.pushInfoList = jsonArrayToArrayList((JsonArray) new Gson().fromJson(str, JsonArray.class));
    }

    public PushInfoList(List<PushInfo> list) {
        new ArrayList();
        this.pushInfoList = list;
    }
}
