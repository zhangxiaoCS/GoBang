package gobang;
/**
 * Created by zhangxiao
 */

public class ChessBoard {
    public final int N = 15;//N=15
    public final int EMPTY = 0;
    public final int BLACK = 1;
    public final int WHITE = 2;

    public int[][] board = new int[N+1][N+1];

    private ChessBoard() {}
    private static final ChessBoard chess = new ChessBoard();

    /* �����൥�� */
    public static ChessBoard getInstance() {
        return chess;
    }

    /*  �жϸ�λ���Ƿ����� */
    public boolean isEmpty(int x, int y) {
        return board[x][y] == EMPTY;
    }

    /* ���� */
    public void makeMove(int x, int y, int color) {
        board[x][y] = color;
    }

    /*�������*/
    
    public void clear() {
    	for(int n=1;n<N;n++)
			for(int m=1;m<N;m++)
				chess.unMove(n,m);
    }
    
    /* ���� */
    public void unMove(int x, int y) {
        board[x][y] = EMPTY;
    }

    public int reckon(int color) {

        int dx[] = {1, 0, 1, 1};
        int dy[] = {0, 1, 1, -1};
        int ans = 0;

        for(int x=1; x<=N; x++) {
            for (int y = 1; y <= N; y++) {
                if (board[x][y] != color)
                    continue;

                int num[][] = new int[2][100];

                for (int i = 0; i < 4; i++) {
                    int sum = 1;
                    int flag1 = 0, flag2 = 0;

                    int tx = x + dx[i];
                    int ty = y + dy[i];
                    while (tx > 0 && tx <= N
                            && ty > 0 && ty <= N
                            && board[tx][ty] == color) {
                        tx += dx[i];
                        ty += dy[i];
                        ++sum;
                    }

                    if(tx > 0 && tx <= N
                            && ty > 0 && ty <= N
                            && board[tx][ty] == EMPTY)
                        flag1 = 1;

                    tx = x - dx[i];
                    ty = y - dy[i];
                    while (tx > 0 && tx <= N
                            && ty > 0 && ty <= N
                            && board[tx][ty] == color) {
                        tx -= dx[i];
                        ty -= dy[i];
                        ++sum;
                    }

                    if(tx > 0 && tx <= N
                            && ty > 0 && ty <= N
                            && board[tx][ty] == EMPTY)
                        flag2 = 1;

                    if(flag1 + flag2 > 0)
                        ++num[flag1 + flag2 - 1][sum];
                }

                //��5
                if(num[0][5] + num[1][5] > 0)
                    ans = Math.max(ans, 100000);
                    //��4 | ˫���� | ��4��3
                else if(num[1][4] > 0
                        || num[0][4] > 1
                        || (num[0][4] > 0 && num[1][3] > 0))
                    ans = Math.max(ans, 10000);
                    //˫��3
                else if(num[1][3] > 1)
                    ans = Math.max(ans, 5000);
                    //��3��3
                else if(num[1][3] > 0 && num[0][3] > 0)
                    ans = Math.max(ans, 1000);
                    //��4
                else if(num[0][4] > 0)
                    ans = Math.max(ans, 500);
                    //����3
                else if(num[1][3] > 0)
                    ans = Math.max(ans, 200);
                    //˫��2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 100);
                    //��3
                else if(num[0][3] > 0)
                    ans = Math.max(ans, 50);
                    //˫��2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 10);
                    //����2
                else if(num[1][2] > 0)
                    ans = Math.max(ans, 5);
                    //��2
                else if(num[0][2] > 0)
                    ans = Math.max(ans, 1);

            }
        }

        return ans;
    }

    /* �жϾ����Ƿ���� 0δ���� 1WHITEӮ 2BLACKӮ */
    public int isEnd(int x, int y, int color) {
        int dx[] = {1, 0, 1, 1};
        int dy[] = {0, 1, 1, -1};

        for (int i = 0; i < 4; i++) {
            int sum = 1;

            int tx = x + dx[i];
            int ty = y + dy[i];
            while (tx > 0 && tx <= N
                    && ty > 0 && ty <= N
                    && board[tx][ty] == color) {
                tx += dx[i];
                ty += dy[i];
                ++sum;
            }

            tx = x - dx[i];
            ty = y - dy[i];
            while (tx > 0 && tx <= N
                    && ty > 0 && ty <= N
                    && board[tx][ty] == color) {

                tx -= dx[i];
                ty -= dy[i];
                ++sum;
            }


            if(sum >= 5)
                return color;
        }
        return 0;
    }
}