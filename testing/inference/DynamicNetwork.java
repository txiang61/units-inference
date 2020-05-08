import java.util.function.BiFunction;

public class DynamicNetwork<N extends Object, E extends AbstractEdge<N>> {
    public DynamicNetwork(BiFunction<N, N, E> edgeFactory) {
    }
}
