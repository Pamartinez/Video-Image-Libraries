package androidx.appcompat.app;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TwilightCalculator {
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    public static TwilightCalculator getInstance() {
        if (sInstance == null) {
            sInstance = new TwilightCalculator();
        }
        return sInstance;
    }

    public void calculateTwilight(long j2, double d, double d2) {
        float f = ((float) (j2 - 946728000000L)) / 8.64E7f;
        float f5 = (0.01720197f * f) + 6.24006f;
        double d3 = (double) f5;
        double sin = (Math.sin((double) (f5 * 3.0f)) * 5.236000106378924E-6d) + (Math.sin((double) (2.0f * f5)) * 3.4906598739326E-4d) + (Math.sin(d3) * 0.03341960161924362d) + d3 + 1.796593063d + 3.141592653589793d;
        double d5 = (-d2) / 360.0d;
        double sin2 = (Math.sin(2.0d * sin) * -0.0069d) + (Math.sin(d3) * 0.0053d) + ((double) (((float) Math.round(((double) (f - 9.0E-4f)) - d5)) + 9.0E-4f)) + d5;
        double asin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(sin));
        double d6 = 0.01745329238474369d * d;
        double sin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(asin) * Math.sin(d6))) / (Math.cos(asin) * Math.cos(d6));
        if (sin3 >= 1.0d) {
            this.state = 1;
            this.sunset = -1;
            this.sunrise = -1;
        } else if (sin3 <= -1.0d) {
            this.state = 0;
            this.sunset = -1;
            this.sunrise = -1;
        } else {
            double acos = (double) ((float) (Math.acos(sin3) / 6.283185307179586d));
            this.sunset = Math.round((sin2 + acos) * 8.64E7d) + 946728000000L;
            long round = Math.round((sin2 - acos) * 8.64E7d) + 946728000000L;
            this.sunrise = round;
            if (round >= j2 || this.sunset <= j2) {
                this.state = 1;
            } else {
                this.state = 0;
            }
        }
    }
}
