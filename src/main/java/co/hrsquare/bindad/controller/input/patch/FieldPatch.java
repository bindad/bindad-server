package co.hrsquare.bindad.controller.input.patch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FieldPatch {

    private final PatchOp op;
    private final String fieldExpression;
    private final Object value;

}
