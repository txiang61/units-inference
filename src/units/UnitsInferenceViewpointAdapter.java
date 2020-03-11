package units;

import checkers.inference.util.InferenceViewpointAdapter;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;
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
        if (!(type.getKind() == TypeKind.DECLARED
                || type.getKind() == TypeKind.ARRAY
                || type.getKind().isPrimitive())) {
            return false;
        }
        return super.shouldAdaptMember(type, element);
    }

    //    @Override
    //    protected AnnotationMirror combineAnnotationWithAnnotation(
    //            AnnotationMirror receiver, AnnotationMirror declared) {
    //        if (AnnotationUtils.areSame(declared, unitsRepUtils.RECEIVER_DEPENDANT_UNIT)) {
    //            return super.combineAnnotationWithAnnotation(receiver, declared);
    //        }
    //        return declared;
    //    }
}