import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Simulation.java
 * <p>
 * Built for pa4. More details see README
 * <p>
 * Went formal with JavaDoc this time
 * to help managing this project
 *
 * @author Yanwen_Xu
 */

public class Simulation {

    static PrintWriter rptOut = null;
    static PrintWriter trcOut = null;

    /**
     * create processors
     *
     * @param n
     * @param jobList
     * @throws Exception
     */
    public static void processors(int n, List<Job> jobList) throws Exception {
        trcOut.println("*****************************");
        trcOut.println(n + (n > 1 ? " processors" : " processor"));
        trcOut.println("*****************************");

        Queue[] queues = new Queue[n + 1];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new Queue();
        }
        for (Job job : jobList) {
            queues[0].enqueue(job);
        }

        int time = 0;
        printTrace(time, queues);


        while ((queues[0].length() != jobList.size() || ((Job) queues[0].peek()).getFinish() < 0)) {
            time = nextTime(queues);
            // first finish, then arrive

            // finish
            for (int i = 1; i < queues.length; ++i) {
                if (!queues[i].isEmpty() && ((Job) queues[i].peek()).getFinish() == time) {
                    queues[0].enqueue(queues[i].dequeue());
                    if (!queues[i].isEmpty()) {
                        ((Job) queues[i].peek()).computeFinishTime(time);
                    }
                }
            }


            // arrive
            while (!queues[0].isEmpty()) {
                Job job = (Job) queues[0].peek();
                if (job.getFinish() < 0 && job.getArrival() == time) {
                    //search shortest line
                    int shortestLineIndex = shortestLine(queues);
                    job = (Job) queues[0].dequeue();
                    queues[shortestLineIndex].enqueue(job);
                    if (job == (Job) queues[shortestLineIndex].peek()) {
                        job.computeFinishTime(time);
                    }
                } else {
                    break;
                }
            }
            printTrace(time, queues);
        }
    }

    private static int nextTime(Queue[] qArr) throws QueueEmptyException {
        List<Integer> temp = new ArrayList<Integer>();
        if (!qArr[0].isEmpty() && ((Job) qArr[0].peek()).getFinish() < 0) {
            temp.add(((Job) qArr[0].peek()).getArrival());
        }

        for (int i = 1; i < qArr.length; ++i) {
            if (!qArr[i].isEmpty()) {
                temp.add(((Job) qArr[i].peek()).getFinish());
            }
        }
        return Collections.min(temp);
    }


    private static int shortestLine(Queue[] qArr) {

        int tempMin = Integer.MAX_VALUE;
        for (int i = 1; i < qArr.length; ++i) {
            tempMin = tempMin < qArr[i].length() ? tempMin : qArr[i].length();
            if (tempMin == 0) {
                return i;
            }
        }

        for (int i = 1; i < qArr.length; ++i) {
            if (tempMin == qArr[i].length()) {
                return i;
            }
        }
        return 0;
    }

    /**
     * print
     *
     * @param time
     * @param queues
     */
    private static void printTrace(int time, Queue[] queues) {
        trcOut.println("time=" + time);
        for (int i = 0; i < queues.length; i++) {
            //System.out.println(i+":"+queues[i].toString());
            trcOut.println(i + ": " + queues[i].toString());
        }
        trcOut.println("");
    }

    public static void main(String[] args) throws Exception {
        Scanner in = null;

        String line = null;
        int m = 0, n = 0, lineNumber = 0;

        if (args.length < 1) {
            System.exit(1);
        }
        String fileName = args[0];
        // String fileName = "infile9.txt";
        String rpt = fileName + ".rpt";
        String trc = fileName + ".trc";
        // open files
        in = new Scanner(new File(fileName));

        rptOut = new PrintWriter(rpt);
        trcOut = new PrintWriter(trc);

        rptOut.println("Report file: " + rpt);
        trcOut.println("Trace file: " + trc);

        List<Job> jobList = new ArrayList<Job>();

        while (in.hasNextLine()) {
            line = in.nextLine().trim();
            if (line == null || "".equals(line)) {
                break;
            }
            if (lineNumber == 0) {
                m = Integer.parseInt(line);
            } else {
                jobList.add(new Job(Integer.parseInt(line.split("\\s+")[0]), Integer.parseInt(line.split("\\s+")[1])));
            }
            lineNumber++;
        }

        rptOut.println(m + " Jobs: ");
        String jobInfo = "";
        for (int i = 0; i < m; i++) {
            jobInfo += jobList.get(i) + " ";
        }

        rptOut.println(jobInfo.trim());
        rptOut.println();
        rptOut.println("***********************************************************");

        n = m - 1;
        for (int i = 1; i <= n; i++) {
            processors(i, jobList);
            rptOut.print(i + (i > 1 ? " processors:" : " processor:"));

            int[] waitTime = new int[m];
            for (int j = 0; j < m; j++) {
                waitTime[j] = jobList.get(j).getWaitTime();
                jobList.get(j).resetFinishTime();
            }


            int totalWait = 0;
            int maxWait = 0;
            double averageWait = 0.00;

            for (int time : waitTime) {
                totalWait += time;
                if (time > maxWait) {
                    maxWait = time;
                }
            }
            averageWait = totalWait * 1.00 / m;
            rptOut.println(" totalWait=" + totalWait
                    + ", maxWait=" + maxWait
                    + ", averageWait=" + String.format("%.2f", averageWait));
        }
        // close files
        in.close();
        rptOut.close();
        trcOut.close();
    }
}
