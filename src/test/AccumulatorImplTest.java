package test;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;
import metapack.Accumulator;
import metapack.AccumulatorImpl;

/**
 * Class test AccumulatorImpl
 * @author Harmonie
 *
 */
public class AccumulatorImplTest extends TestCase {
	/** Accumulator */
    private Accumulator accumulator;
    /** Exception handler */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /**
     * setUp accumulator for all the test cases
     */
    @Override
    protected void setUp () throws Exception {
        accumulator = new AccumulatorImpl();
    }
    
    /**
     * test reset()
     * @throws Exception
     */
    public final void  testReset () throws Exception {
        accumulator.accumulate(5);
        accumulator.reset();
        assertEquals(0, accumulator.getTotal());
    }
    /**
     * test getTotal()
     * @throws Exception
     */
    public final void testGetTotal () throws Exception {
        accumulator.accumulate(2, 4);
        assertEquals(6, accumulator.getTotal());
        accumulator.accumulate(4);
        assertEquals(10, accumulator.getTotal());
    }
    
    /**
     * test total init value equals 0
     */
    public final void testInitTotalValue () throws Exception {
        assertEquals(0, accumulator.getTotal());
    }
    
    /**
     * test empty param in accumulate()
     * @throws Exception
     */
    public final void testEmptyParamAccumulate () throws Exception {
        assertEquals(0, accumulator.accumulate());
    }
    
    /**
     * test accumulate() multiple positives values
     * @throws Exception
     */
    public final void testAccumulateMultiplePositivesValues () throws Exception {
        assertEquals(10, accumulator.accumulate(2, 4, 4));
    }
    
    /**
     * test accumulate() multiple values with negative
     * @throws Exception
     */
    public final void testAccumulateMultiplePosAndNegValues () throws Exception {
        assertEquals(2, accumulator.accumulate(2, -4, 4));
    }
    
    /**
     * test accumulate() multiple negatives values
     * @throws Exception
     */
    public final void testAccumulateMultipleNegativesValues () throws Exception {
        assertEquals(-10, accumulator.accumulate(-2, -4, -4));
    }
    
    /**
     * test multiple accumulation
     * @throws Exception
     */
    public final void testMultipleAccumulate () throws Exception {
        assertEquals(15, accumulator.accumulate(10, 5));
        assertEquals(-5, accumulator.accumulate(-5, 0));
    }
    
    /**
     * test the overflow under limit
     * @throws Exception
     */
    public final void testOverflowUnderLimit () throws Exception {
        assertEquals(Integer.MAX_VALUE, accumulator.accumulate(Integer.MAX_VALUE));
    }
    /**
     * test the underflow over limit
     * @throws Exception
     */
    public final void testUnderflowOverLimit () throws Exception {
        assertEquals(Integer.MIN_VALUE, accumulator.accumulate(Integer.MIN_VALUE));
    }
    
    /**
     * test overflow 
     * @throws Exception
     */
    public final void testAccumulateOverflow () throws ArithmeticException {
    	thrown.expect(ArithmeticException.class);
    	thrown.expectMessage("ArithmeticException OverFlow");
    	accumulator.accumulate(Integer.MAX_VALUE);
    }
     
    /**
     * test underflow 
     * @throws Exception
     */
    public final void testAccumulateUnderflow () throws ArithmeticException {
    	thrown.expect(ArithmeticException.class);
    	thrown.expectMessage("ArithmeticException UnderFlow");
    	accumulator.accumulate(Integer.MIN_VALUE);
    }
    
    /**
     * test when there is an illegal argument exception
     * @throws IllegalArgumentException
     */
    public  void testIllegalArgumentException() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("IllegalArgumentException ");
        accumulator.accumulate();
    }
}
