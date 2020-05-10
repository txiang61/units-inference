public class GasEdge extends AbstractEdge<Number> {

    public GasEdge(Number start, Number end) {
        super(start, end);
    }

    public static GasEdge createEdge(Number start, Number end) {
        return new GasEdge(start, end);
    }
}
