import java.sql.PreparedStatement;

public class Sort {

    public void selection(Window window) throws InterruptedException {
        Rod[] rods = window.rods;
        for (int x = 0; x < rods.length - 1; x++) {
            int min = x;
            for (int y = x + 1; y < rods.length; y++) {
                if (rods[y].i < rods[min].i) {
                    min = y;
                }
            }
            window.swap(x, min);
        }
        window.repaint();
    }

    public void insertion(Window window) throws InterruptedException {
        Rod[] rods = window.rods;
        for (int j = 1; j < rods.length; j++) {
            Rod val = rods[j];
            int x = j;
            int i = x - 1;
            while (i >= 0 && rods[i].i > val.i) {
                window.swap(x, i);
                x--;
                i--;
            }
        }
        window.repaint();
    }

    public void bubble(Window window) throws InterruptedException {
        Rod[] rods = window.rods;
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < rods.length - 1; i++) {
                if (rods[i].i > rods[i + 1].i) {
                    changed = true;
                    window.swap(i, i + 1);
                }
            }
        } while (changed);
        window.repaint();
    }

    public void mergeINPLACE(Window window) throws InterruptedException {
        Rod[] rods = window.rods;
        mergeHelperINPLACE(window, rods, 0, rods.length - 1);
        window.repaint();
    }

    private void mergeHelperINPLACE(Window window, Rod[] rods, int low, int high) throws InterruptedException {
        if (low < high) {
            int middle = ((high + low) / 2);
            mergeHelperINPLACE(window, rods, low, middle);
            mergeHelperINPLACE(window, rods, middle + 1, high);
            mergeINPLACE(window, rods, low, middle, high);
        }
    }

    private void mergeINPLACE(Window window, Rod[] rods, int low, int middle, int high) throws InterruptedException {
        int i = low, j = middle + 1;
        while (i < j && j <= high) {
            if (rods[i].i > rods[j].i) {
                for (int x = j; x > i; x--) {
                    window.swap(x, x - 1);
                }
                j++;
            }
            i++;
        }
    }

    public void mergeOUTPLACE(Window window) throws InterruptedException {
        Rod[] rods = window.rods;
        mergeHelperOUTPLACE(window, rods, 0, rods.length - 1);
        window.repaint();
    }

    private void mergeHelperOUTPLACE(Window window, Rod[] rods, int low, int high) throws InterruptedException {
        if (low < high) {
            int middle = ((high + low) / 2);
            mergeHelperOUTPLACE(window, rods, low, middle);
            mergeHelperOUTPLACE(window, rods, middle + 1, high);
            mergeOUTPLACE(window, rods, low, middle, high);
        }
    }

    private void mergeOUTPLACE(Window window, Rod[] rods, int low, int middle, int high) throws InterruptedException {
        Rod[] lower = new Rod[middle - low + 1];
        Rod[] higher = new Rod[high - middle];
        for (int i = low; i <= middle; i++) {
            lower[i - low] = rods[i];
        }
        for (int i = middle + 1; i <= high; i++) {
            higher[i - (middle + 1)] = rods[i];
        }
        int l = 0, h = 0, i = low;
        while (l < middle - low + 1 && h < high - middle) {
            if (lower[l].i < higher[h].i) {
                window.put(i++, lower[l++]);
            } else {
                window.put(i++, higher[h++]);
            }
        }
        while (l < middle - low + 1) {
            window.put(i++, lower[l++]);
        }
        while (h < high - middle) {
            window.put(i++, higher[h++]);
        }
    }

    public void quick(Window window) throws InterruptedException {
        quick(window, window.rods, 0, window.rods.length - 1);
        window.repaint();
    }

    private void quick(Window window, Rod[] rods, int low, int high) throws InterruptedException {
        if (low >= high)
            return;
        int p = partition(window, rods, low, high);
        quick(window, rods, low, p - 1);
        quick(window, rods, p + 1, high);
    }

    private int partition(Window window, Rod[] rods, int low, int high) throws InterruptedException {
        int p = high;
        int i = low - 1;
        int j = low;
        while (j < high) {
            if (rods[j].i < rods[high].i) {
                window.swap(i + 1, j);
                i++;
            }
            j++;
        }
        window.swap(i + 1, high);
        return i + 1;
    }


}
