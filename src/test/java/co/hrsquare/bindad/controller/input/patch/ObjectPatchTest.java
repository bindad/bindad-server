package co.hrsquare.bindad.controller.input.patch;

import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectPatchTest {

    @Test
    public void testApplyWithPrimitives() {

        @Data
        class Test {
            private long x;
            private Long y;
        }

        FieldPatch x = FieldPatch.builder().fieldExpression("x").op(PatchOp.SET).value(10).build();
        FieldPatch y = FieldPatch.builder().fieldExpression("y").op(PatchOp.SET).value(20).build();
        ObjectPatch<Test> objectPatch = new ObjectPatch<>(Arrays.asList(x, y));
        Test candidate = new Test();

        objectPatch.applyTo(candidate);

        assertEquals(10, candidate.x);
        assertEquals(20, candidate.y);

    }

    @Disabled
    @Test
    public void testApplyWithNestedObject() {
        @Data
        class NestedClass {
            private int z;
        }

        @Data
        class Test {
            private long x;
            private Long y;
            private NestedClass ns;
        }

        FieldPatch x = FieldPatch.builder().fieldExpression("x").op(PatchOp.DELETE).build();
        FieldPatch y = FieldPatch.builder().fieldExpression("y").op(PatchOp.SET).value(20).build();
        FieldPatch ns = FieldPatch.builder().fieldExpression("ns.z").op(PatchOp.SET).value(30).build();
        ObjectPatch<Test> objectPatch = new ObjectPatch<>(Arrays.asList(x, y, ns));
        Test candidate = new Test();
        candidate.x = -100;
        candidate.y = -200L;

        objectPatch.applyTo(candidate);

        assertEquals(0, candidate.x);
        assertEquals(20, candidate.y);
        assertEquals(30, candidate.ns.z);

    }

    @Test
    public void testApplyWithPrimitivesForDelete() {

        @Data
        class Test {
            private long x;
            private Long y;
        }

        FieldPatch x = FieldPatch.builder().fieldExpression("x").op(PatchOp.DELETE).build();
        FieldPatch y = FieldPatch.builder().fieldExpression("y").op(PatchOp.SET).value(20).build();
        ObjectPatch<Test> objectPatch = new ObjectPatch<>(Arrays.asList(x, y));
        Test candidate = new Test();
        candidate.x = -100;
        candidate.y = -200L;

        objectPatch.applyTo(candidate);

        assertEquals(0, candidate.x);
        assertEquals(20, candidate.y);

    }




}