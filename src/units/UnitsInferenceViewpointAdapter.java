package units;

import checkers.inference.util.InferenceViewpointAdapter;
import javax.lang.model.element.Element;
import org.checkerframework.framework.type.AnnotatedTypeFactory;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import units.representation.UnitsRepresentationUtils;

public class UnitsInferenceViewpointAdapter extends InferenceViewpointAdapter {

    // static reference to the singleton instance
    protected static UnitsRepresentationUtils unitsRepUtils;

    public UnitsInferenceViewpointAdapter(AnnotatedTypeFactory atypeFactory) {
        super(atypeFactory);
        unitsRepUtils = UnitsRepresentationUtils.getInstance();
    }

    @Override
    protected boolean shouldAdaptMember(AnnotatedTypeMirror type, Element element) {
        return false;
    }
}
