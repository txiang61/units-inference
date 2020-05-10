import units.qual.*;

// class Test2 {

// 	private double[] _cA;
// 	int _cP;

// 	public void setC(int i, double d) {
// 		_cA[_cP + i] = d;
// 	}

// 	public void scaleC(int i, @rad double d) {
// 		_cA[_cP + i] *= d;
// 		tan(d);
// 	}

// 	double[] angle = new double[3];

// 	public double getC(int _num) {
// 		for ( int i = 0; i < _num; i++ )
// 		{
// 			tan(angle[i]);
// 		}
// 		return angle[0] = -dAtan2(0,0);
// 	}

// 	public static final double dAtan2(double y, double x) {
// 		return Math.atan2(y, x) * Math.atan2(y, x);
// 	}

// 	public static void tan(double angle) {

// 	}
// }

class Test2<E extends Identifiable> {
    private Class<? extends Identifiable> elementType;
    private E[] elements;

    public Test2(E[] elements) {
        this.elementType = elements[0].getClass();
    }
}
