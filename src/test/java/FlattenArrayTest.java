import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlattenArrayTest {

    @Test
    public void testFlatten1(){
        Object[] testArray = new Object[2];
        testArray[0] = new Object[]{1,2,new Object[]{3}};
        testArray[1] = 4;

        List<Integer> actualResult = FlattenArray.flatten(testArray,new ArrayList<Integer>());
        List<Integer> expectedResult = Arrays.asList(1,2,3,4);
        assertTrue(actualResult.equals(expectedResult));
    }


    @Test
    public void testFlatten2(){
        Object[] testArray = new Object[2];
        Object[] a1 = new Object[]{22};
        Object[] a2 = new Object[]{4,new Object[]{2},new Object[]{8,9,new Object[]{10}}};

        testArray[0] = a1;
        testArray[1] = a2;

        List<Integer> actualResult = FlattenArray.flatten(testArray,new ArrayList<Integer>());
        List<Integer> expectedResult = Arrays.asList(22,4,2,8,9,10);
        assertTrue(actualResult.equals(expectedResult));
    }


}
