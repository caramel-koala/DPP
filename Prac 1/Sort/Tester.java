import java.util.Date;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Tester{

  public static void main (String args[])
    {

      Random rand = new Random();

      Integer[] SeqList = new Integer[500000];
      Integer[] ParList = new Integer[500000];

      for (int i = 0; i < SeqList.length; i++)
      {
          int j = rand.nextInt(1000000000);
          SeqList[i] = j;
          ParList[i] = j;
      }

      //sequential execution
      long startSeq = System.currentTimeMillis();
      Sort.quickSort(SeqList);
      long endSeq = System.currentTimeMillis();
      System.out.println("Time taken for sequential: " + (endSeq-startSeq) + "ms.");

      //parallel execution
      ForkJoinPool fjp = new ForkJoinPool();
      ParallelSort result = new ParallelSort(ParList, 0, ParList.length-1);
      long startPar = System.currentTimeMillis();
      fjp.invoke(result);
      long endPar = System.currentTimeMillis();
      System.out.println("Time taken for parallel: " + (endPar-startPar) + "ms.");


    } // main

}
