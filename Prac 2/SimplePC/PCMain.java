import org.jcsp.lang.*;

/** Main program class for Producer/Consumer example.
  * Sets up channel, creates one of each process then
  * executes them in parallel, using JCSP.
  */
public final class PCMain
  {
    public static void main (String[] args)
      { new PCMain();
      } // main

    public PCMain ()
      { // Create channel object
        final One2OneChannelInt[] tobuff    = {Channel.one2oneInt(), Channel.one2oneInt()};
        final One2OneChannelInt[] frombuff  = {Channel.one2oneInt(), Channel.one2oneInt()};
        final One2OneChannelInt[] consreq   = {Channel.one2oneInt(), Channel.one2oneInt()};

        // Create and run parallel construct with a list of processes
        CSProcess[] procList = {  new Producer(tobuff[0].out(),0),
                                  new Producer(tobuff[1].out(),1),
                                  new Consumer(frombuff[0].in(),consreq[0].out(),0),
                                  new Consumer(frombuff[1].in(),consreq[1].out(),1),
                                  new Buffer( new AltingChannelInputInt[]{tobuff[0].in(),tobuff[1].in()},
                                              new ChannelOutputInt[]{frombuff[0].out(),frombuff[1].out()},
                                              new AltingChannelInputInt[]{consreq[0].in(),consreq[1].in()}) }; // Processes
        Parallel par = new Parallel(procList); // PAR construct
        par.run(); // Execute processes in parallel
      } // PCMain constructor

  } // class PCMain
