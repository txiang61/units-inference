package units;

import units.notusedquals.g;
import units.notusedquals.kg;
import units.qual.*;

@SuppressWarnings("units")
public class UnitsTools {
    // Static Constants
    public static final @g int g = 1;
    public static final @kg int kg = 1;

    public static final @m int m = 1;
    public static final @km int km = 1;

    public static final @s int s = 1;
    public static final @ms int ms = 1;
    public static final @us int us = 1;
    public static final @ns int ns = 1;

    public static final @rad int rad = 1;
    public static final @deg int deg = 1;

    // Testing use only
    public static final @UnknownUnits int top = 1;
    public static final @UnitsBottom int bottom = 1;
    public static final @Dimensionless int dimensionless = 1;

    // Conversion Functions
    public static final @ms int secondsToMilliSeconds(@s int seconds) {
        return seconds * 1000;
    }
}
