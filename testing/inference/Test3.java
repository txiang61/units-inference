import java.nio.file.Files;
import java.nio.file.Path;

class Test3<E extends Identifiable> {
	private Class<? extends Identifiable> elementType;
	private E[] elements;

	public Test3( E[] elements ) {
		this.elementType = elements[0].getClass();
	}
}
