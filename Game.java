import java.util.Scanner;

public class Game {


    static int W1, W2, W3, W4,W5,W6;
    static int[] additionalMove=new int[2], stonesCaptured=new int[2];
    static int player0_Heuristic, player1_Heuristic;
    static int depthMax;
    static   int DEEP_LIMIT=14,LOW_LIMIT=3;

    public static void main(String args[]){

        MancalaBoard mancalaBoard=new MancalaBoard(6,4);
        Scanner scanner=new Scanner(System.in);


        System.out.println("2. Experiment");
        System.out.println("1. User Vs Computer");
        System.out.println("0. Computer Vs Computer");
        System.out.print("Select : ");
        int choice=scanner.nextInt();

        if(choice<2){
            System.out.print("\nEnter heuristic (1 to 6) : ");
            player0_Heuristic=scanner.nextInt();
        }


        W1 = 11;
        W2 = 13;
        W3 = 17;
        W4 = 19;
        W5 = 23;
        W6 = 29;

        int player = (int)(Math.random() * 10)%2;

        int selectedPosition;
        int currentState;


        if(choice==0){
            System.out.print("Enter Max Depth : ");
            DEEP_LIMIT=scanner.nextInt();

            System.out.println("\n1.Normal Mode ");
            System.out.println("0.Iterative Deepening Mode ");
            System.out.print("Select : ");
            int option=scanner.nextInt();


            System.out.println("\n0.Ascending 1. Descending ");
            System.out.print("Move Order : ");
            int moveOrder=scanner.nextInt();

            mancalaBoard.printBoard();
            if(option==1){
                depthMax=DEEP_LIMIT;
                while(true)
                {
                    selectedPosition = MinMaxAlgoWithPruning(mancalaBoard, player,moveOrder);
                    System.out.println("\nNow Playing Player : "+(player+1));

                    if(selectedPosition!=-1)
                    {   System.out.println("Position Selected  : "+(selectedPosition+1)+" th\n");
                        player = mancalaBoard.updateBoard(player, selectedPosition);
                        mancalaBoard.printBoard();
                    }
                    currentState = mancalaBoard.gameState();

                    if(currentState == 0)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nWinner : Player 1\n");
                        break;
                    }
                    else if(currentState == 1)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nWinner : Player 2\n");
                        break;
                    }
                    else if(currentState == 2)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nMatch Drawn\n");
                        break;
                    }
                }
            }
            else {

                depthMax=3;

                while(true)
                {
                    selectedPosition = MinMaxAlgoWithPruning(mancalaBoard, player,moveOrder);
                    System.out.println("\nNow Playing Player : "+(player+1));

                    if(selectedPosition!=-1)
                    {   System.out.println("Position Selected  : "+(selectedPosition+1)+" th\n");
                        player = mancalaBoard.updateBoard(player, selectedPosition);
                        mancalaBoard.printBoard();
                    }
                    currentState = mancalaBoard.gameState();

                    if(currentState == 0)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nWinner : Player 1\n");
                        break;
                    }
                    else if(currentState == 1)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nWinner : Player 2\n");
                        break;
                    }
                    else if(currentState == 2)
                    {
                        System.out.println("\n                   <<Final Board>> ");
                        mancalaBoard.printBoard();
                        System.out.println("\nMatch Drawn\n");
                        break;
                    }

                    if(depthMax+1<DEEP_LIMIT)
                        depthMax++;
                }
            }


        }else if(choice==1) {

            depthMax=DEEP_LIMIT;
            System.out.println("You will play as Player 2");
            mancalaBoard.printBoard();
            while(true)
            {
                System.out.println("\nNow Playing Player : "+(player+1));

                if(player==1){
                    do{
                        System.out.print("Enter Bin Position : ");
                        selectedPosition=scanner.nextInt();
                    }while(selectedPosition<=0&&selectedPosition>6);
                    selectedPosition--;
                }
               else  selectedPosition = MinMaxAlgoWithPruning(mancalaBoard, player,0);


                if(selectedPosition!=-1)
                {   System.out.println("Position Selected  : "+(selectedPosition+1)+" th\n");
                    player = mancalaBoard.updateBoard(player, selectedPosition);
                    mancalaBoard.printBoard();
                }
                currentState = mancalaBoard.gameState();

                if(currentState == 0)
                {
                    System.out.println("\n                   <<Final Board>> ");
                    mancalaBoard.printBoard();
                    System.out.println("\nWinner : Player 1\n");
                    break;
                }
                else if(currentState == 1)
                {
                    System.out.println("\n                   <<Final Board>> ");
                    mancalaBoard.printBoard();
                    System.out.println("\nWinner : Player 2\n");
                    break;
                }
                else if(currentState == 2)
                {
                    System.out.println("\n                   <<Final Board>> ");
                    mancalaBoard.printBoard();
                    System.out.println("\nMatch Drawn\n");
                    break;
                }
            }


        }else {

            DEEP_LIMIT=16;

            for(int h=1;h<=6;h++) {

                int pl1=0,pl2=0,mdraw=0;

                 for(int d=LOW_LIMIT;d<=DEEP_LIMIT;d++)
                 {

                     mancalaBoard=new MancalaBoard(6,4);
                     player0_Heuristic=h;
                     depthMax=d;
                     player = (int)(Math.random() * 10)%2;

                 while (true) {
                     selectedPosition = MinMaxAlgoWithPruning(mancalaBoard, player, 1);
                    // System.out.println("\nNow Playing Player : " + (player + 1));

                     if (selectedPosition != -1) {
                        // System.out.println("Position Selected  : " + (selectedPosition + 1) + " th\n");
                         player = mancalaBoard.updateBoard(player, selectedPosition);
                        // mancalaBoard.printBoard();
                     }
                     currentState = mancalaBoard.gameState();

                     if (currentState == 0) {
                         //System.out.println("\n                   <<Final Board>> ");
                        // mancalaBoard.printBoard();
                        // System.out.println("\nWinner : Player 1\n");
                         System.out.println("Heuristic : "+h+"  Depth : "+d+"   Won : Player "+(player+1));
                         pl1++;
                         break;
                     } else if (currentState == 1) {
                        // System.out.println("\n                   <<Final Board>> ");
                        // mancalaBoard.printBoard();
                        // System.out.println("\nWinner : Player 2\n");
                         System.out.println("Heuristic : "+h+"  Depth : "+d+"   Won : Player "+(player+1));
                         pl2++;
                         break;
                     } else if (currentState == 2) {
                        // System.out.println("\n                   <<Final Board>> ");
                       //  mancalaBoard.printBoard();
                       //  System.out.println("\nMatch Drawn\n");
                         System.out.println("Heuristic : "+h+"  Depth : "+d+"   Math Drawn");
                        mdraw++;
                         break;
                     }
                 }

             }
                System.out.println("--------------------------------------------------");
                System.out.println("Heuristic : "+h);
                System.out.print("Player 1 : ");
                System.out.printf("%.2f", (pl1*100.0)/(DEEP_LIMIT-LOW_LIMIT+1));
                System.out.print(" %");
                System.out.print(" ||  Player 2 : ");
                System.out.printf("%.2f", (pl2*100.0)/(DEEP_LIMIT-LOW_LIMIT+1));
                System.out.print(" %");
                System.out.print("  ||  Match Drawn : ");
                System.out.printf("%.2f", (mdraw*100.0)/(DEEP_LIMIT-LOW_LIMIT+1));
                System.out.println(" %");
                System.out.println("--------------------------------------------------");
            }


        }




    }
    public static int H1(MancalaBoard board, int player)
    {
        return board.storage[player] - board.storage[1 - player];
    }

    static int H2(MancalaBoard board, int player)
    {
        int currPlayer = 0, otherPlayer = 0;
        for(int i = 0; i < board.noOfBuckets; i++)
        {
            currPlayer += board.bucket[player][i];
            otherPlayer += board.bucket[1 - player][i];
        }
        return W1*H1(board,player) + W2*(currPlayer - otherPlayer);
    }

    static int H3(MancalaBoard board, int player)
    {

        return H2(board,player)+ W3*additionalMove[player];
    }

   static int H4(MancalaBoard board, int player)
    {

        return H3(board,player) + W4*stonesCaptured[player];
    }

    static int H5(MancalaBoard board, int player)
    {

        double goalDiff=(board.noOfBuckets* board.noOfStoneEachBucket)-board.storage[player];

        if(goalDiff==0.0) goalDiff=1.0;
        return H4(board,player) + (int)(W5*1234.5/goalDiff);
    }

    static int H6(MancalaBoard board, int player)
    {

        return H5(board,player) + W6*(board.bucket[player][board.noOfBuckets-1]+board.bucket[player][1]);
    }

    static int selectHeuristic(MancalaBoard board, int player, int choice)
    {

        switch (choice){
            case 1 :
                return H1(board,player);
            case 2 :
                return H2(board,player);
            case 3 :
                return H3(board,player);
            case 4 :
                return H4(board,player);
            case 5 :
                return H5(board,player);
            case 6 :
                return H6(board,player);
            default:
                return  -1;
        }
    }

    static boolean reachedLeaf(MancalaBoard board, int depth, int player)
    {
        if(depth == 1)  return true;

        int currPlayer = 0;
        int otherPlayer = 0;

        for(int i = 0; i < board.noOfBuckets; i++)
        {
            currPlayer+=board.bucket[player][i];
            otherPlayer+=board.bucket[1 - player][i];
        }

        if(currPlayer == 0 || otherPlayer == 0) return true;
        else return false;
    }

     static int MinMaxAlgoWithPruning(MancalaBoard board, int player,int moveOrder) //  returns the position to be played
    {
           // move order 0 => ascending 1=> descending
        int alpha = Integer.MIN_VALUE;
        int beta  = Integer.MAX_VALUE;

        additionalMove[player] =additionalMove[1-player]= 0;
        stonesCaptured[player] =stonesCaptured[1-player]= 0;

        if(player==0)
        return  Maximizer(board, alpha, beta, depthMax, player,moveOrder).second;
        else return  Minimizer(board, alpha, beta, depthMax, player,moveOrder).second;
        //second is the index which will be played, -1 if reached a result/depth
    }

    static Pair Maximizer(MancalaBoard board, int alpha, int beta, int depth, int player, int moveOrder)
    {
        if(reachedLeaf(board, depth, player))
        {

           // if(player == 0)
                return new Pair(selectHeuristic(board, player, player0_Heuristic), -1);
            //else
               // return new Pair(selectHeuristic(board, player, player1_Heuristic), -1);
        }

        Pair option = new Pair(Integer.MIN_VALUE, -1);

        if(moveOrder==0){
            for(int i = 0; i < board.noOfBuckets; i++)
            {
                MancalaBoard tempBoard=new MancalaBoard(6,4);
                board.mirrorBoardTo(tempBoard);

                if(tempBoard.bucket[player][i] == 0)
                    continue;

                int nextTurn = tempBoard.updateBoard(player, i);
                int tempValue;

                if(nextTurn == player) //bonus move
                {
                    additionalMove[player]++;
                    tempValue = Maximizer(tempBoard, alpha, beta, depth-1, player,moveOrder).first;
                }
                else
                {
                    tempValue = Minimizer(tempBoard, alpha, beta, depth-1, (1 - player),moveOrder).first;
                }


                if(option.first < tempValue) //maximizing
                {
                    option.first = tempValue;
                    option.second = i;
                }

                if(option.first >= beta) //for pruning
                {
                    return option;
                }

                alpha = Math.max(alpha, option.first);

            }
        }
        else {

            for(int i = board.noOfBuckets-1; i >=0; i--)
            {
                MancalaBoard tempBoard=new MancalaBoard(6,4);
                board.mirrorBoardTo(tempBoard);

                if(tempBoard.bucket[player][i] == 0)
                    continue;

                int nextTurn = tempBoard.updateBoard(player, i);
                int tempValue;

                if(nextTurn == player) //bonus move
                {
                    additionalMove[player]++;
                    tempValue = Maximizer(tempBoard, alpha, beta, depth-1, player,moveOrder).first;
                }
                else
                {
                    tempValue = Minimizer(tempBoard, alpha, beta, depth-1, (1 - player),moveOrder).first;
                }


                if(option.first < tempValue) //maximizing
                {
                    option.first = tempValue;
                    option.second = i;
                }

                if(option.first >= beta) //for pruning
                {
                    return option;
                }

                alpha = Math.max(alpha, option.first);

            }
        }


        return option;
    }

    static Pair Minimizer(MancalaBoard board, int alpha, int beta, int depth, int player, int moveOrder) // not cracked yet
    {
        if(reachedLeaf(board,depth,player))
        {
            if(player == 0)
                return new Pair(selectHeuristic(board, player, player0_Heuristic), -1);
            else
                return new Pair(selectHeuristic(board, player, player0_Heuristic), -1);
        }

            Pair option = new Pair(Integer.MAX_VALUE, -1);

       if(moveOrder==0){
           for(int i = 0; i < board.noOfBuckets; i++)
           {
               MancalaBoard tempBoard=new MancalaBoard(6,4);
               board.mirrorBoardTo(tempBoard);
               if(tempBoard.bucket[player][i] == 0)
                   continue;

               int nextTurn = tempBoard.updateBoard(player, i);
               int tempValue;

               if(nextTurn == player)
               {
                   additionalMove[player]++;
                   tempValue = Minimizer(tempBoard, alpha, beta, depth-1, player,moveOrder).first;
               }
               else
               {
                   tempValue = Maximizer(tempBoard, alpha, beta, depth-1, (1 - player),moveOrder).first;
               }

               if(option.first > tempValue) //minimizing
               {
                   option.first = tempValue;
                   option.second = i;
               }
               if(option.first <= alpha) //for pruning
               {
                   return option;
               }

               beta = Math.min(beta, option.first);
           }
       }else {

           for(int i = board.noOfBuckets-1; i >= 0; i--)
           {
               MancalaBoard tempBoard=new MancalaBoard(6,4);
               board.mirrorBoardTo(tempBoard);
               if(tempBoard.bucket[player][i] == 0)
                   continue;

               int nextTurn = tempBoard.updateBoard(player, i);
               int tempValue;

               if(nextTurn == player)
               {
                   additionalMove[player]++;
                   tempValue = Minimizer(tempBoard, alpha, beta, depth-1, player,moveOrder).first;
               }
               else
               {
                   tempValue = Maximizer(tempBoard, alpha, beta, depth-1, (1 - player),moveOrder).first;
               }

               if(option.first > tempValue) //minimizing
               {
                   option.first = tempValue;
                   option.second = i;
               }
               if(option.first <= alpha) //for pruning
               {
                   return option;
               }

               beta = Math.min(beta, option.first);
           }

       }

        return option;
    }

}
