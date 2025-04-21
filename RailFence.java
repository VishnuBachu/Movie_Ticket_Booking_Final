public class RailFence {
    public static String encrypt(String text) {
        int l = text.length();
        char[][] a = new char[3][l];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < l; j++) {
                a[i][j] = '-';
            }
        }

        int row = 0;
        boolean down = true;

        for (int i = 0; i < l; i++) {
            a[row][i] = text.charAt(i);

            if (down) {
                row++;
                if (row == 3) {
                    down = false;
                    row = 1;
                }
            } else {
                row--;
                if (row == 0) {
                    down = true;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < l; j++) {
                if (a[i][j] != '-') {
                    ans.append(a[i][j]);
                }
            }
        }
        return ans.toString();
    }
    public static String decrypt(String text) {
        int l = text.length();
        char[][] a = new char[l][3];
    
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = '-';
            }
        }

        int k = 0;
        int p=4;
        for (int j = 0; j < 3; j++) {
            for (int i = j; i < l; i += p) {
                a[i][j] = text.charAt(k++);
            }
            p=p-2;
            if(p==0) p=4;
        }
    
        StringBuilder ans = new StringBuilder();
        int j = 0;
        boolean down = true;
        for (int i = 0; i < l; i++) {
            ans.append(a[i][j]);
            if (down) {
                j++;
                if (j == 3) {
                    j = 1;
                    down = false;
                }
            } else {
                j--;
                if (j == 0) {
                    down = true;
                }
            }
        }
        return ans.toString();
    }
}

