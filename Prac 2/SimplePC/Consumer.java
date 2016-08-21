import org.jcsp.lang.*;

/** Consumer class: reads one int from input channel, displays it, then
  * terminates.
  */
public class Consumer implements CSProcess
  { private AltingChannelInputInt channel;
    private ChannelOutputInt request;
    private int id;

    public Consumer (final AltingChannelInputInt in, final ChannelOutputInt req, int idx)
      { channel = in;
        request = req;
        id = idx;
      } // constructor

    public void run ()
      {
        int item = 0;
        int count = 100;
        while (item != -1 && count > 0)
        {
          request.write(1);
          item = channel.read();
          if (item > -1)
          {
            System.out.println(item);
            count--;
          }
        }

        System.out.println("Consumer " + id + " Done");
      } // run

  } // class Consumer
