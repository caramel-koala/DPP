import org.jcsp.lang.*;
import java.util.concurrent.TimeUnit;

/** Producer class: produces one random integer and sends on
  * output channel, then terminates.
  */
public class Producer implements CSProcess
  { private ChannelOutputInt channel;
    private int id;

    public Producer (final ChannelOutputInt out, int idx)
      { channel = out;
        id = idx;
      } // constructor

    public void run ()
      {
        for (int i = 0; i< 100; i++)
        {
          int item = (int)(Math.random()*100)+1 + (100*id);
          channel.write(item);
        }

        System.out.println("Producer " + id + " Done");
        channel.write(-1);
      } // run

  } // class Producer
