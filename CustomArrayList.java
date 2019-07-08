import java.util.Arrays;

public class CustomArrayList<E> 
{
	    private int size = 0;
	    private static final int DEFAULT_CAPACITY = 10;
	    private Object elements[];
	    protected transient int modCount = 0;
	
	    /**
	     * Constructor
	     */
	    public CustomArrayList() {
	        elements = new Object[DEFAULT_CAPACITY];
	    }
	
	    /**
	     * add element to list
	     * @param e
	     */
	    public void add(E e) {
	        if (size == elements.length) {
	        	increaseSize();
	        }
	        elements[size++] = e;
	    }
	
	    /**
	     * increase the size of the array as double
	     */
	    private void increaseSize() {
	        int newSize = elements.length * 2;
	        elements = Arrays.copyOf(elements, newSize);
	    }
	    
	    /**
	     * get element from list 
	     * @param index
	     * @return
	     */
	    @SuppressWarnings("unchecked")
	    public E get(int index) {
	        if (index>= size || index <0) {
	            return null;
	        }
	        return (E) elements[index];
	    }
	    
	    /**
	     * remove element from list
	     * @param e
	     */
	    /*public void remove(E e) {
	    	if (e != null) {
	    		boolean newArrayFlag = false;
		    	Object[] temp = new String[this.elements.length - 1];
	    	    for(int i = 0, j = 0; i < this.elements.length; i++){
	    	        if (! e.equals(this.elements[i]) && j < this.elements.length) {
	    	        	E oldValue = (E)this.elements[i];
	    	            temp[j++] = oldValue;
	    	        } else {
	    	        	newArrayFlag = true;
	    	        }
	    	    }
	    	    if (newArrayFlag)
	    	    	this.elements = temp; 
	    	}
	    }*/
	    
	    public boolean remove(Object o) {
	        if (o == null) {
	            for (int index = 0; index < size; index++)
	                if (elements[index] == null) {
	                    fastRemove(index);
	                    return true;
	                }
	        } else {
	            for (int index = 0; index < size; index++)
	                if (o.equals(elements[index])) {
	                    fastRemove(index);
	                    return true;
	                }
	        }
	        return false;
	    }
	    
	    private void fastRemove(int index) {
	        modCount++;
	        int numMoved = size - index - 1;
	        if (numMoved > 0)
	            System.arraycopy(elements, index+1, elements, index,
	                             numMoved);
	        elements[--size] = null; 
	    }
	    
	    /**
	     * return the size of list.
	     * @return
	     */
	    public int size() {
	    	return size;
	    }

	    /**
	     * toString
	     */
		@Override
		public String toString() {
			return "CustomArrayList [size=" + size + ", elements=" + Arrays.toString(elements) + "]";
		}
	    
}
