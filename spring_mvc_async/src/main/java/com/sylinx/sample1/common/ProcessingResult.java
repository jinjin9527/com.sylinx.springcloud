package com.sylinx.sample1.common;

import java.time.LocalDateTime;

public class ProcessingResult {
    private LocalDateTime acceptedTime;
    private LocalDateTime completedTime;

    public ProcessingResult(LocalDateTime acceptedTime, LocalDateTime completedTime) {
        this.acceptedTime = acceptedTime;
        this.completedTime = completedTime;
    }

    public LocalDateTime getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(LocalDateTime acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public ProcessingResult() {
    }
}
