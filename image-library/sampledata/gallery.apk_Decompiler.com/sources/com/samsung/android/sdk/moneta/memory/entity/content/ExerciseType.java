package com.samsung.android.sdk.moneta.memory.entity.content;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.z;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\bt\b\u0002\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\bu¨\u0006w"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "OTHER", "WALKING", "RUNNING", "FLOOR_CLIMB", "TRACK_RUN", "BASEBALL", "SOFTBALL", "CRICKET", "GOLF", "BOWLING", "HOCKEY", "RUGBY", "BASKETBALL", "SOCCER", "HANDBALL", "FOOTBALL", "VOLLEYBALL", "BEACH_VOLLEYBALL", "SQUASH", "TENNIS", "BADMINTON", "TABLE_TENNIS", "RACQUETBALL", "BOXING", "MARTIAL_ARTS", "BALLET", "DANCING", "BALLROOM_DANCING", "PILATES", "YOGA", "STRETCHING", "JUMP_ROPE", "HULA_HOOPING", "PUSH_UPS", "PULL_UPS", "SIT_UPS", "CIRCUIT_TRAINING", "MOUNTAIN_CLIMBERS", "JUMPING_JACKS", "BURPEE", "BENCH_PRESS", "SQUATS", "LUNGES", "LEG_PRESSES", "LEG_EXTENSIONS", "LEG_CURLS", "BACK_EXTENSIONS", "LAT_PULL_DOWNS", "DEADLIFTS", "SHOULDER_PRESSES", "FRONT_RAISES", "LATERAL_RAISES", "CRUNCHES", "LEG_RAISES", "PLANK", "ARM_CURLS", "ARM_EXTENSIONS", "SKATERS", "HANG_KNEES", "INLINE_SKATING", "HANG_GLIDING", "ARCHERY", "HORSEBACK_RIDING", "BIKE", "FLYING_DISC", "ROLLER_SKATING", "AEROBICS", "HIKING", "ROCK_CLIMBING", "BACKPACKING", "MOUNTAIN_BIKING", "ORIENTEERING", "SWIMMING", "AQUAROBICS", "CANOEING", "SAILING", "SCUBA_DIVING", "SNORKELING", "KAYAKING", "KITESURFING", "RAFTING", "ROWING", "WINDSURFING", "YACHTING", "WATER_SKIING", "STEP_MACHINE", "WEIGHT_MACHINE", "BIKE_INDOOR", "ROWING_MACHINE", "TREADMILL", "ELLIPTICAL_TRAINER", "STAIR_CLIMBER", "CROSS_COUNTRY_SKIING", "SKIING", "ICE_DANCING", "ICE_SKATING", "ICE_HOCKEY", "SNOWBOARDING", "ALPINE_SKIING", "SNOWSHOEING", "TRIATHLON", "DUATHLON", "AQUATHLON", "AQUABIKE", "CROSS_TRIATHLON", "CROSS_DUATHLON", "BREAK", "COOL_DOWN", "WARM_UP", "TRANSITION", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ExerciseType {
    OTHER("Other"),
    WALKING("Walking"),
    RUNNING("Running"),
    FLOOR_CLIMB("Floor climb"),
    TRACK_RUN("Track run"),
    BASEBALL("Baseball"),
    SOFTBALL("Softball"),
    CRICKET("Cricket"),
    GOLF("Golf"),
    BOWLING("Bowling"),
    HOCKEY("Hockey"),
    RUGBY("Rugby"),
    BASKETBALL("Basketball"),
    SOCCER("Soccer"),
    HANDBALL("Handball"),
    FOOTBALL("Football"),
    VOLLEYBALL("Volleyball"),
    BEACH_VOLLEYBALL("Beach volleyball"),
    SQUASH("Squash"),
    TENNIS("Tennis"),
    BADMINTON("Badminton"),
    TABLE_TENNIS("Table tennis"),
    RACQUETBALL("Racquetball"),
    BOXING("Boxing"),
    MARTIAL_ARTS("Martial arts"),
    BALLET("Ballet"),
    DANCING("Dancing"),
    BALLROOM_DANCING("Ballroom dancing"),
    PILATES("Pilates"),
    YOGA("Yoga"),
    STRETCHING("Stretching"),
    JUMP_ROPE("Jump rope"),
    HULA_HOOPING("Hula-hooping"),
    PUSH_UPS("Push-ups"),
    PULL_UPS("Pull-ups"),
    SIT_UPS("Sit-ups"),
    CIRCUIT_TRAINING("Circuit training"),
    MOUNTAIN_CLIMBERS("Mountain climbers"),
    JUMPING_JACKS("Jumping Jacks"),
    BURPEE("Burpee"),
    BENCH_PRESS("Bench press"),
    SQUATS("Squats"),
    LUNGES("Lunges"),
    LEG_PRESSES("Leg presses"),
    LEG_EXTENSIONS("Leg extensions"),
    LEG_CURLS("Leg curls"),
    BACK_EXTENSIONS("Back extensions"),
    LAT_PULL_DOWNS("Lat Pull-downs"),
    DEADLIFTS("Deadlifts"),
    SHOULDER_PRESSES("Shoulder presses"),
    FRONT_RAISES("Front raises"),
    LATERAL_RAISES("Lateral raises"),
    CRUNCHES("Crunches"),
    LEG_RAISES("Leg raises"),
    PLANK("Plank"),
    ARM_CURLS("Arm curls"),
    ARM_EXTENSIONS("Arm extensions"),
    SKATERS("Skaters"),
    HANG_KNEES("High knees"),
    INLINE_SKATING("Inline skating"),
    HANG_GLIDING("Hang gliding"),
    ARCHERY("Archery"),
    HORSEBACK_RIDING("Horseback riding"),
    BIKE("Bike"),
    FLYING_DISC("Flying disc"),
    ROLLER_SKATING("Roller skating"),
    AEROBICS("Aerobics"),
    HIKING("Hiking"),
    ROCK_CLIMBING("Rock climbing"),
    BACKPACKING("Backpacking"),
    MOUNTAIN_BIKING("Mountain biking"),
    ORIENTEERING("Orienteering"),
    SWIMMING("Swimming"),
    AQUAROBICS("Aquarobics"),
    CANOEING("Canoeing"),
    SAILING("Sailing"),
    SCUBA_DIVING("Scuba diving"),
    SNORKELING("Snorkeling"),
    KAYAKING("Kayaking"),
    KITESURFING("Kitesurfing"),
    RAFTING("Rafting"),
    ROWING("Rowing"),
    WINDSURFING("Windsurfing"),
    YACHTING("Yachting"),
    WATER_SKIING("Water skiing"),
    STEP_MACHINE("Step machine"),
    WEIGHT_MACHINE("Weight machine"),
    BIKE_INDOOR("Bike indoor"),
    ROWING_MACHINE("Rowing machine"),
    TREADMILL("Treadmill"),
    ELLIPTICAL_TRAINER("Elliptical trainer"),
    STAIR_CLIMBER("Stair climber"),
    CROSS_COUNTRY_SKIING("Cross-country skiing"),
    SKIING("Skiing"),
    ICE_DANCING("Ice dancing"),
    ICE_SKATING("Ice skating"),
    ICE_HOCKEY("Ice hockey"),
    SNOWBOARDING("Snowboarding"),
    ALPINE_SKIING("Alpine skiing"),
    SNOWSHOEING("Snowshoeing"),
    TRIATHLON("Triathlon"),
    DUATHLON("Duathlon"),
    AQUATHLON("Aquathlon"),
    AQUABIKE("Aquabike"),
    CROSS_TRIATHLON("Cross triathlon"),
    CROSS_DUATHLON("Cross Duathlon"),
    BREAK("Break"),
    COOL_DOWN("Cool Down"),
    WARM_UP("Warm Up"),
    TRANSITION("Transition");
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final Map<String, ExerciseType> map = null;
    private final String value;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType$Companion;", "", "<init>", "()V", "map", "", "", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ExerciseType;", "fromString", "type", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final ExerciseType fromString(String str) {
            j.e(str, "type");
            ExerciseType exerciseType = (ExerciseType) ExerciseType.map.get(str);
            if (exerciseType == null) {
                return ExerciseType.OTHER;
            }
            return exerciseType;
        }

        private Companion() {
        }
    }

    static {
        int i2;
        ExerciseType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
        C1295a entries = getEntries();
        int Z = z.Z(C1196n.w0(entries, 10));
        if (Z >= 16) {
            i2 = Z;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(i2);
        for (Object next : entries) {
            linkedHashMap.put(((ExerciseType) next).value, next);
        }
        map = linkedHashMap;
    }

    private ExerciseType(String str) {
        this.value = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }
}
