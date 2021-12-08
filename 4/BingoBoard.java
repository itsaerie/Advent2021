public class BingoBoard {
    int[][] board = new int[5][5];

    public BingoBoard(String line1, String line2, String line3, String line4, String line5) {
        String[] l1 = line1.trim().split("\\s+");
        String[] l2 = line2.trim().split("\\s+");
        String[] l3 = line3.trim().split("\\s+");
        String[] l4 = line4.trim().split("\\s+");
        String[] l5 = line5.trim().split("\\s+");
        for (int i = 0; i < 5; i++) {
            board[0][i] = Integer.parseInt(l1[i]);
            board[1][i] = Integer.parseInt(l2[i]);
            board[2][i] = Integer.parseInt(l3[i]);
            board[3][i] = Integer.parseInt(l4[i]);
            board[4][i] = Integer.parseInt(l5[i]);
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                str += this.board[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }

    public boolean callNum(int calledNum) {
        // 'call' or cross out a num
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (calledNum == this.board[i][j]) {
                    this.board[i][j] = -1;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasBingo() {
        for (int i = 0; i < 5; i++) {
            // check horiz
            for (int j = 0; j < 5; j++) {
                if (this.board[i][j] != -1) {
                    break;
                } else if (j == 4) {
                    return true;
                }
            }
            // check vert
            for (int j = 0; j < 5; j++) {
                if (this.board[j][i] != -1) {
                    break;
                } else if (j == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public int nonNegSum() {
        int sum = 0;
        for (int[] line : board) {
            for (int i : line) {
                if (i != -1)
                    sum += i;
            }
        }
        return sum;
    }
}