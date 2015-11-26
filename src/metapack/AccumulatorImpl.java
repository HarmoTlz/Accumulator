package metapack;

import java.util.logging.Logger;

/**
 * 
 * @author Harmonie
 *         <p>
 *         Class implements Accumulator Interface
 *         </p>
 *
 */
public class AccumulatorImpl implements Accumulator {
	/** total value */
	private int total = 0;
	/** Logger */
	private static Logger log = Logger.getLogger(Accumulator.class.getName());

	/**
	 * <p>
	 * Adds one or more values to the running sum.
	 * </p>
	 *
	 * <p>
	 * This method calculates the sum of the given arguments first, then updates
	 * the total value with this sum.
	 * </p>
	 *
	 * @param values
	 *            the item or items to add to the running total
	 * @return the sum of the arguments passed to the method
	 */
	public int accumulate(int... values) {
		int totalValue = 0;
		try {
			for (int valueToAdd : values) {
				// throws an exception if the result overflows an int
				totalValue = Math.addExact(totalValue, valueToAdd);
			}
		} catch (IllegalArgumentException e) {
			log.severe("IllegalArgumentException " + e.getMessage());
		}
		total = Math.addExact(total, totalValue);
		return totalValue;
	}
 
	/**
	 * <p>
	 * The current value of the total sum.
	 * </p>
	 *
	 * @return the total sum value
	 */
	public int getTotal() {

		return total;
	}

	/**
	 * <p>
	 * Resets the running value to 0.
	 * </p>
	 */
	public void reset() {
		total = 0;

	}

}
