import java.util.ArrayList;
import java.util.List;

/**
 * Write some code, that will flatten an array of arbitrarily nested arrays of integers
 * into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4].
 */

public class FlattenArray {

    public static void main(String[] args) {
        Object[] testArray = new Object[2];
        testArray[0] = new Object[]{1,2,new Object[]{3}};
        testArray[1] = 4;

        System.out.println(flatten(testArray, new ArrayList<Integer>()));
    }

    /**
     *
     * @param arrayOfObjects
     * @param result
     * @return
     */
     static List<Integer> flatten(Object[] arrayOfObjects,List<Integer> result){
        if (arrayOfObjects.length < 1){
            return result;
        }
        for (Object object: arrayOfObjects){
            if (object instanceof Integer){
                result.add((Integer) object);
            }else {
                flatten((Object[]) object,result);
            }
        }return result;

    }
}
