package co.hrsquare.bindad.controller.input.patch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Slf4j
public class ObjectPatch<T> {

    private final List<FieldPatch> fieldPatches;

    @SuppressWarnings("rawtypes")
    void applyTo(T t) {
        Map defaultContext = Ognl.createDefaultContext(t, new OgnlMemberAccess());

        if (fieldPatches != null) {
            fieldPatches.forEach(p -> {
                try {
                    if (PatchOp.SET == p.getOp()) {
                        Ognl.setValue(p.getFieldExpression(), defaultContext, t, p.getValue());
                    } else if (PatchOp.DELETE == p.getOp()) {
                        Ognl.setValue(p.getFieldExpression(), defaultContext, t, null);
                    }
                } catch (OgnlException e) {
                    log.error("Error setting {} for type {}", p.getFieldExpression(), t.getClass(), e);
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
