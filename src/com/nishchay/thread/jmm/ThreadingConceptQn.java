package com.nishchay.thread.jmm;

public class ThreadingConceptQn {

    public static void main(String[] args) {

    }

    public static class MetricContainer {

        long failVal;
        long successVal;
        long totalVal;

        public synchronized void updateMetrics(long failVal, long successVal) {
            System.out.println("updating the metrics");
            long totalVal = failVal + successVal;
            if (totalVal > 0) {
                this.failVal += failVal;
                this.successVal += successVal;
                this.totalVal += totalVal;
            }
        }



        public String getMetrics() {
            return String.format("Failure %d, Success %d Total %d", failVal, successVal, totalVal);
        }
    }

}
