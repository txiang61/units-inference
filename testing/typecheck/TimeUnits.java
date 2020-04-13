import units.qual.*;

@UnknownUnits
enum TimeUnit {
    @ns NANOSECONDS,
    @us MICROSECONDS,
    @ms MILLISECONDS,
    @s SECONDS;

    // For example, to convert 10 minutes to milliseconds, use:
    // TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)
    // TODO: enforce source value and unit are equal
    @RDU long convert(@UnknownUnits TimeUnit this, @UnknownUnits long sourceDuration,
            @UnknownUnits TimeUnit sourceUnit) {
        return 0;
    }

    // TODO: add dimensional bound on duration to be a time value
    @ns long toNanos(@UnknownUnits TimeUnit this, @RDU long duration) {
        return (@ns long) 0;
    }

    @us long toMicros(@UnknownUnits TimeUnit this, @RDU long duration) {
        return (@us long) 0;
    }

    @ms long toMillis(@UnknownUnits TimeUnit this, @RDU long duration) {
        return (@ms long) 0;
    }

    @s long toSeconds(@UnknownUnits TimeUnit this, @RDU long duration) {
        return (@s long) 0;
    }


    void test() {
        @s
        TimeUnit s = TimeUnit.SECONDS;

        @ms
        TimeUnit ms = TimeUnit.MILLISECONDS;

        @us
        TimeUnit us = TimeUnit.MICROSECONDS;

        @ns
        TimeUnit ns = TimeUnit.NANOSECONDS;

        @s long seconds = s.convert(10, NANOSECONDS);

        // convert 10 minutes to milliseconds
        @ms long milliseconds = ms.convert(10, TimeUnit.SECONDS);

        @us long microsec = us.convert(10, TimeUnit.SECONDS);

        @ns long nanosec = ns.convert(10, TimeUnit.SECONDS);
    }
}