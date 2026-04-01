package androidx.appfunctions.internal.serializableproxies;

import i.C0212a;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u0000  2\u00020\u0001:\u0001 B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001a\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001c\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001d\u0010\u0013R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u001e\u0010\u0013R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u001f\u0010\u0013¨\u0006!"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionLocalDateTime;", "", "", "year", "month", "dayOfMonth", "hour", "minute", "second", "nanoOfSecond", "<init>", "(IIIIIII)V", "Ljava/time/LocalDateTime;", "toLocalDateTime", "()Ljava/time/LocalDateTime;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getYear", "getMonth", "getDayOfMonth", "getHour", "getMinute", "getSecond", "getNanoOfSecond", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionLocalDateTime {
    public static final Companion Companion = new Companion((e) null);
    private final int dayOfMonth;
    private final int hour;
    private final int minute;
    private final int month;
    private final int nanoOfSecond;
    private final int second;
    private final int year;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/appfunctions/internal/serializableproxies/AppFunctionLocalDateTime$Companion;", "", "<init>", "()V", "fromLocalDateTime", "Landroidx/appfunctions/internal/serializableproxies/AppFunctionLocalDateTime;", "localDateTime", "Ljava/time/LocalDateTime;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final AppFunctionLocalDateTime fromLocalDateTime(LocalDateTime localDateTime) {
            j.e(localDateTime, "localDateTime");
            return new AppFunctionLocalDateTime(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
        }

        private Companion() {
        }
    }

    public AppFunctionLocalDateTime(int i2, int i7, int i8, int i10, int i11, int i12, int i13) {
        this.year = i2;
        this.month = i7;
        this.dayOfMonth = i8;
        this.hour = i10;
        this.minute = i11;
        this.second = i12;
        this.nanoOfSecond = i13;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppFunctionLocalDateTime)) {
            return false;
        }
        AppFunctionLocalDateTime appFunctionLocalDateTime = (AppFunctionLocalDateTime) obj;
        if (this.year == appFunctionLocalDateTime.year && this.month == appFunctionLocalDateTime.month && this.dayOfMonth == appFunctionLocalDateTime.dayOfMonth && this.hour == appFunctionLocalDateTime.hour && this.minute == appFunctionLocalDateTime.minute && this.second == appFunctionLocalDateTime.second && this.nanoOfSecond == appFunctionLocalDateTime.nanoOfSecond) {
            return true;
        }
        return false;
    }

    public final int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getNanoOfSecond() {
        return this.nanoOfSecond;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getYear() {
        return this.year;
    }

    public int hashCode() {
        return Integer.hashCode(this.nanoOfSecond) + C0212a.b(this.second, C0212a.b(this.minute, C0212a.b(this.hour, C0212a.b(this.dayOfMonth, C0212a.b(this.month, Integer.hashCode(this.year) * 31, 31), 31), 31), 31), 31);
    }

    public final LocalDateTime toLocalDateTime() {
        LocalDateTime of2 = LocalDateTime.of(this.year, this.month, this.dayOfMonth, this.hour, this.minute, this.second, this.nanoOfSecond);
        j.d(of2, "of(...)");
        return of2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppFunctionLocalDateTime(year=");
        sb2.append(this.year);
        sb2.append(", month=");
        sb2.append(this.month);
        sb2.append(", dayOfMonth=");
        sb2.append(this.dayOfMonth);
        sb2.append(", hour=");
        sb2.append(this.hour);
        sb2.append(", minute=");
        sb2.append(this.minute);
        sb2.append(", second=");
        sb2.append(this.second);
        sb2.append(", nanoOfSecond=");
        return N2.j.e(sb2, this.nanoOfSecond, ')');
    }
}
