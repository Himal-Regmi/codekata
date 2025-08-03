package karatechop;

public class KarateChop {
    public static int karateChop(int target, int[] numbers) {
        return binarySearchKarateChop(target, numbers);
    }

    //O(log n) & O(1)
    public static int binarySearchKarateChop(int target, int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (numbers[mid] > target) {
                high = mid - 1;
            } else if (numbers[mid] < target) {
                low = mid + 1;
            } else if (numbers[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    //O(n) & O(1)
    public static int iterativeKarateChop(int target, int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //O(log n) & O(log n)
    public static int recursiveKarateChop(int target, int[] numbers) {
        return recursiveKarateChopHelper(target, numbers, 0, numbers.length - 1);
    }

    public static int recursiveKarateChopHelper(int target, int[] numbers, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return -1;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;

        if (numbers[mid] == target) {
            return mid;
        } else if (numbers[mid] < target) {
            return recursiveKarateChopHelper(target, numbers, mid + 1, endIndex);
        } else {
            return recursiveKarateChopHelper(target, numbers, startIndex, mid - 1);
        }

    }
}
