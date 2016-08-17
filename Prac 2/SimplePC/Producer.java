import org.jcsp.lang.*;

/** Producer class: produces one random integer and sends on
  * output channel, then terminates.
  */
public class Producer implements CSProcess
  { private ChannelOutputInt channel;

    public Producer (final ChannelOutputInt out)
      { channel = out;
      } // constructor

    public void run ()
      { int item = (int)(Math.random()*100)+1;
        channel.write(item);
      } // run

  } // class Producer
