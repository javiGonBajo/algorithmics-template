package algstudent.s3;

import java.time.Duration;
import java.time.Instant;


public class CalendarTimes { 
    public static void main (String arg [] ) {
      for (int n=2; n<1000000; n*=2) {
          Calendar calendar = new Calendar(n);
          Instant start = Instant.now();
          for (int nTimes=1 ; nTimes <= 1000; nTimes++)
              calendar.makeTable();
          Instant end = Instant.now();
          System.out.println("n="+n+"**"+"TIME="+(Duration.between(start, end).toMillis())+" MILLISECONDS");
      }
    }
}