import units.qual.*;
import java.lang.reflect.Field;

class Test3 {

    int test() {
        int i = val();
        Integer i2 = new Integer(i);
        return i2;
    }

    int val() {
    	return 0;
    }

    // long test2(Object obj) {
    // 	Object fieldObj = obj;
    //     return ((Long)fieldObj).longValue();
    // }
}
