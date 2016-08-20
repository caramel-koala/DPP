import org.jcsp.lang.*;
import java.util.*;

public class Buffer implements CSProcess
{
  //create in and out channels
  private AltingChannelInputInt in;
  private ChannelOutputInt      out;

  //create buffer queue
  Queue<Integer> buffer;

  public Buffer (final AltingChannelInputInt extin, final ChannelOutputInt extout)
  {
    in  = extin;
    out = extout;
  } // constructor

  public void run()
  {
    new Parallel
    (
      buffer.add(in.read()),
      out.write(buffer.poll())
    ).run();
  }

}
