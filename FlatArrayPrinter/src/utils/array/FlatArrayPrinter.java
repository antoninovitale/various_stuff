package utils.array;

import java.util.Arrays;


/**
 * Write some code, that will flatten an array of arbitrarily nested arrays of
 * integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4].
 * 
 * @author a.vitale
 * 
 */
public class FlatArrayPrinter {

	public static void main(String[] args) {
		Object[][][] input = {{{}}, null, {{1,4}, null, {2,3,5}, {1,3}}, {{3,1,2}, {1}}, {{8,7,4}}, null};
//		Object[][] input = {{10}, null, {}, null, {1,4}, {2,3,5}, {1,3}, {3,1,2}, {1}, {8,7,4}, null, null};
//		Object[] input = {10, null, 0, 1, 2, 3, 5, 7, 9, null};
//		Object[] input = null;
		Object[] array = toFlatArray(input);
		if(array != null) {
			for(Object o : array) {
				System.out.print(o+" ");
			}
		}
	}
	
	/**
	 * Method to convert an array of nested arrays into a flat array
	 * 
	 * @param input
	 * @return a flat array
	 */
	public static Object[] toFlatArray(Object[] input) {
        if (input == null) return null;
        Object[] flatArray = null;
        int index = 0;
        for (Object element : input) {
        	if(element == null)
        		continue;
        	if(element instanceof Object[]) {
        		Object[] newArray = toFlatArray((Object[]) element);
        		if(newArray==null)
        			continue;
        		if(flatArray==null) {
        			flatArray = newArray;
        		}
        		else {
        			int start = flatArray.length;
        			flatArray = Arrays.copyOf(flatArray, flatArray.length+newArray.length);
        			System.arraycopy(newArray, 0, flatArray, start, newArray.length);
        		}
            	index = flatArray.length;
        	}
        	else if (element instanceof Object) {
        		if(flatArray==null) {
        			flatArray = new Object[]{element};
        		}
        		else {
	        		if(index==flatArray.length) {
	        			flatArray = Arrays.copyOf(flatArray, flatArray.length+1);
	        		}
	        		flatArray[index] = element;
        		}
        		index++;
            }
        }
        return flatArray;
    }

}
