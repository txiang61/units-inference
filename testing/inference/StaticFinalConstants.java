import static java.lang.Math.*;

import units.UnitsTools;
import units.qual.*;

class StaticFinalConstants {

    static class MyConstants {
        public static final double X = 10;
        public static final Integer Y = Integer.MAX_VALUE;

        public static final @rad int HASUNIT = 20 * UnitsTools.rad;

        public MyConstants() {};
    }

    void inferRadians2() {
        // :: fixable-error: (argument.type.incompatible)
        sin(MyConstants.X);

        // :: fixable-error: (argument.type.incompatible)
        tan(MyConstants.Y);

        cos(MyConstants.HASUNIT);
    }
}
