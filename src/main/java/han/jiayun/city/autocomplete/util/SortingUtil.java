package han.jiayun.city.autocomplete.util;

import java.util.List;

/**
 * This class provides custom tool for sorting a list.
 * 
 * @author Jiayun Han
 *
 */
public final class SortingUtil {

	private SortingUtil() {
	}

	/**
	 * Insert a new item into the correct position so that the list still keeps its
	 * descending-sorted order
	 * 
	 * @param <T>The item must implement the Camparable interface
	 * @param list   The list of items that are sorted in descending order
	 * @param item   The new item to be inserted
	 */
	public static <T extends Comparable<? super T>> void orderedInsert(List<T> list, T item) {

		list.add(null);

		int size = list.size();

		while (--size > 0) {
			T shift = list.get(size - 1);

			if (shift.compareTo(item) < 0) {
				break;
			}

			list.set(size, shift);
		}

		list.set(size, item);
	}
}
