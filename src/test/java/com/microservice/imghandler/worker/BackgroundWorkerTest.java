package com.microservice.imghandler.worker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BackgroundWorkerTest{

    @Autowired
    private BackgroundWorker backgroundWorker;

    @MockBean
    private Runnable task;

    @Test
    public void testSubmitTask() {
        backgroundWorker.submitTask(task);
        verify(task, times(1)).run();
    }
}
