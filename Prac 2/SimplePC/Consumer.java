import org.jcsp.lang.*;

/** Consumer class: reads one int from input channel, displays it, then
  * terminates.
  */
public class Consumer implements CSProcess
  { private AltingChannelInputInt channel;

    public Consumer (final AltingChannelInputInt in)
      { channel = in;
      } // constructor

    public void run ()
      {  int item = channel.read();
         System.out.println(item);
      } // run

  } // class Consumer
