package com.samsung.android.sdk.moneta.lifestyle.healthandwellness.entity;

import A.a;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import i.C0212a;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001:\u0001-B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\r\u001a\u00060\u000bj\u0002`\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0010J\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0018J\u0010\u0010\u001c\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ8\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b \u0010\u0018J\u001a\u0010$\u001a\u00020#2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010(\u001a\u0004\b)\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b*\u0010\u0018R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010\u001d¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise;", "Landroid/os/Parcelable;", "", "type", "", "name", "category", "Landroid/os/Bundle;", "preferences", "<init>", "(ILjava/lang/String;ILandroid/os/Bundle;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getPreferencesString", "()Ljava/lang/StringBuilder;", "toString", "()Ljava/lang/String;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "component2", "component3", "component4", "()Landroid/os/Bundle;", "copy", "(ILjava/lang/String;ILandroid/os/Bundle;)Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getType", "Ljava/lang/String;", "getName", "getCategory", "Landroid/os/Bundle;", "getPreferences", "PreferencesKey", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Exercise implements Parcelable {
    public static final Parcelable.Creator<Exercise> CREATOR = new Creator();
    private final int category;
    private final String name;
    private final Bundle preferences;
    private final int type;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<Exercise> {
        public final Exercise createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new Exercise(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readBundle(Exercise.class.getClassLoader()));
        }

        public final Exercise[] newArray(int i2) {
            return new Exercise[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/healthandwellness/entity/Exercise$PreferencesKey;", "", "key", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "COUNT", "CONFIDENCE_SCORE", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PreferencesKey {
        COUNT("count"),
        CONFIDENCE_SCORE("confidence_score");
        
        private final String key;

        static {
            PreferencesKey[] $values;
            $ENTRIES = c.t($values);
        }

        private PreferencesKey(String str) {
            this.key = str;
        }

        public static C1295a getEntries() {
            return $ENTRIES;
        }

        public final String getKey() {
            return this.key;
        }
    }

    public Exercise(int i2, String str, int i7, Bundle bundle) {
        j.e(str, "name");
        j.e(bundle, "preferences");
        this.type = i2;
        this.name = str;
        this.category = i7;
        this.preferences = bundle;
    }

    public static /* synthetic */ Exercise copy$default(Exercise exercise, int i2, String str, int i7, Bundle bundle, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i2 = exercise.type;
        }
        if ((i8 & 2) != 0) {
            str = exercise.name;
        }
        if ((i8 & 4) != 0) {
            i7 = exercise.category;
        }
        if ((i8 & 8) != 0) {
            bundle = exercise.preferences;
        }
        return exercise.copy(i2, str, i7, bundle);
    }

    private final StringBuilder getPreferencesString() {
        StringBuilder sb2 = new StringBuilder("preferences: [");
        sb2.append(10);
        Set<String> keySet = this.preferences.keySet();
        j.d(keySet, "keySet(...)");
        for (String str : keySet) {
            if (j.a(str, PreferencesKey.COUNT.getKey())) {
                StringBuilder i2 = a.i("key : ", str, ' ', sb2, "value: ");
                i2.append(this.preferences.getInt(str));
                sb2.append(i2.toString());
                sb2.append(10);
            } else if (j.a(str, PreferencesKey.CONFIDENCE_SCORE.getKey())) {
                StringBuilder i7 = a.i("key : ", str, ' ', sb2, "value: ");
                i7.append(this.preferences.getDouble(str));
                sb2.append(i7.toString());
                sb2.append(10);
            }
        }
        sb2.append("]\n");
        return sb2;
    }

    public final int component1() {
        return this.type;
    }

    public final String component2() {
        return this.name;
    }

    public final int component3() {
        return this.category;
    }

    public final Bundle component4() {
        return this.preferences;
    }

    public final Exercise copy(int i2, String str, int i7, Bundle bundle) {
        j.e(str, "name");
        j.e(bundle, "preferences");
        return new Exercise(i2, str, i7, bundle);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Exercise)) {
            return false;
        }
        Exercise exercise = (Exercise) obj;
        if (this.type == exercise.type && j.a(this.name, exercise.name) && this.category == exercise.category && j.a(this.preferences, exercise.preferences)) {
            return true;
        }
        return false;
    }

    public final int getCategory() {
        return this.category;
    }

    public final String getName() {
        return this.name;
    }

    public final Bundle getPreferences() {
        return this.preferences;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.preferences.hashCode() + C0212a.b(this.category, C0212a.d(Integer.hashCode(this.type) * 31, 31, this.name), 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("type: " + this.type + ", category: " + this.category + ", preferences: " + getPreferencesString());
        sb2.append(10);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeInt(this.type);
        parcel.writeString(this.name);
        parcel.writeInt(this.category);
        parcel.writeBundle(this.preferences);
    }
}
