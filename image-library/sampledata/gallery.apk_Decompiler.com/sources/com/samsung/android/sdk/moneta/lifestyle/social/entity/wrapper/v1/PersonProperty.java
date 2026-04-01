package com.samsung.android.sdk.moneta.lifestyle.social.entity.wrapper.v1;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/social/entity/wrapper/v1/PersonProperty;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "PHONE_NUMBER", "NAME", "CONTACT_ID", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PersonProperty {
    PHONE_NUMBER("phone_number"),
    NAME("name"),
    CONTACT_ID("contact_id");
    
    private final String key;

    static {
        PersonProperty[] $values;
        $ENTRIES = c.t($values);
    }

    private PersonProperty(String str) {
        this.key = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getKey() {
        return this.key;
    }
}
