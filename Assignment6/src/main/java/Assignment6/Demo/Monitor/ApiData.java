package Assignment6.Demo.Monitor;

import lombok.Data;

@Data
public class ApiData {
    int num_call = 0;
    Long min_span = Long.MAX_VALUE;
    Long max_span = 0L;
    float avg_span = 0L;
    int num_exceptions = 0;

    Long total_time = 0L;

    public void addExceptions() {
        this.num_exceptions++;
    }
    public void addData(Long timeSpan) {
        min_span = Math.min(timeSpan, min_span);
        max_span = Math.max(timeSpan, max_span);
        num_call++;
        total_time += timeSpan;
        avg_span = (float) total_time / num_call;
    }

}
