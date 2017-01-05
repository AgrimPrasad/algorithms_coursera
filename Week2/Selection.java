// Selection sort implementation
// First, find the smallest entry and exchange it with the first entry. Then, find the second smallest item and exchange it with the second entry
// and so on until the array is sorted

public class Selection<Item>{
	private Item[] array;
	public Selection(Item[] unsortedArray) {
		array = unsortedArray;
	}

	public void sort() {
		for (int i = 0; i < array.length; i++) {
			int currentMin = i;
			for (int j = i + 1; j < array.length; j++) {
				if (less(array[j], array[currentMin])) currentMin = j;
			}
			array = exch(array, i, currentMin);
		}
	}

	private boolean less(Item item1, Item item2) {
		if (item1.compareto(item2) < 1) return true;
	}

	private Item[] exch(Item[] array, int idx1, int idx2) {
		Item temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;

		return array;
	}

}