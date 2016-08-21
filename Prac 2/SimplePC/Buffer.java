import org.jcsp.lang.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Buffer implements CSProcess
{
  //create in and out channels
  private AltingChannelInputInt[] in;
  private AltingChannelInputInt[] outreq;
  private ChannelOutputInt[]      out;

  //create buffer queue
  private final ArrayBlockingQueue<Integer> buffer;

  public Buffer (final AltingChannelInputInt[] extin, final ChannelOutputInt[] extout, final AltingChannelInputInt[] extreq)
  {
    in      = extin;
    out     = extout;
    outreq  = extreq;

    buffer = new ArrayBlockingQueue<Integer>(10);
  } // constructor

  public void run()
  {
    int numprod = 2; //number of producers

    final Guard[] guard = { in[0], in[1], outreq[0], outreq[1]};
    final Alternative alt = new Alternative(guard);

    while (numprod > 0 || buffer.size()>0)
    {
      int numalt = alt.select();

      switch (numalt)
      {
        case 0: //Can get input
        case 1:
            try
            {
              if (buffer.remainingCapacity() > 0)
              {
                int i = in[numalt].read();
                if (i == -1)
                {
                  numprod--;
                }
                else
                {
                  buffer.add(i);
                }
              }
            }
            catch (Exception e)
            {
              System.err.println("Buffer Exception: " + e.toString());
              e.printStackTrace();
            }
            break;
        case 2: //Can send output
        case 3:
            try
            {
              if (buffer.size() > 0)
              {
                outreq[numalt-2].read();
                out[numalt-2].write(buffer.remove());
              }
              else
              {
                outreq[numalt-2].read();
                out[numalt-2].write(-2);
              }
            }
            catch (Exception e)
            {
              System.err.println("Buffer Exception: " + e.toString());
              e.printStackTrace();
            }
            break;
      }
    }
    System.out.println("Buffer Done");
  }

}
