import units.qual.*;

public class RDUMethodReceiver {

    public class MeterClass {
        // :: error: (super.invocation.invalid)
        public @m MeterClass() {}

        @RDU
        Object get(@m MeterClass this) {
            return null;
        }

        Object set(@m MeterClass this, @RDU Object x) {
            return null;
        }
    }

    public class SecondClass {
        // :: error: (super.invocation.invalid)
        public @s SecondClass() {}

        @RDU
        Object get(@s SecondClass this) {
            return null;
        }

        Object set(@s SecondClass this, @RDU Object x) {
            return null;
        }
    }

    void invoke() {
        MeterClass mc = new MeterClass();
        @m Object x = mc.get();
        mc.set(x);

        SecondClass sc = new SecondClass();
        @s Object y = sc.get();
        sc.set(y);

        // :: error: (assignment.type.incompatible)
        @m Object z = sc.get();
        // :: error: (argument.type.incompatible)
        sc.set(z);

        // :: error: (assignment.type.incompatible)
        @s Object w = mc.get();
        // :: error: (argument.type.incompatible)
        mc.set(w);
    }
}
