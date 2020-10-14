package com.example;

import com.example.blockingqueue.BlockingQueueModel;
import com.example.base.Model;
import com.example.doublelockconditon.DoubleLockConditionModel;
import com.example.lockcondition.LockConditionModel;
import com.example.waitnotify.WaitNotifyModel;

public class Solution {

    public static void main(String[] args) {
//        Model model = new BlockingQueueModel(3);
//        for (int i = 0; i < 2; i++) {
//            new Thread(model.newRunnableConsumer()).start();
//        }
//        for (int i = 0; i < 5; i++) {
//            new Thread(model.newRunnableProducer()).start();
//        }

//        Model model = new WaitNotifyModel(3);
//        for (int i = 0; i < 2; i++) {
//            new Thread(model.newRunnableConsumer()).start();
//        }
//        for (int i = 0; i < 5; i++) {
//            new Thread(model.newRunnableProducer()).start();
//        }

//        Model model = new LockConditionModel(3);
//        for (int i = 0; i < 2; i++) {
//            new Thread(model.newRunnableConsumer()).start();
//        }
//        for (int i = 0; i < 5; i++) {
//            new Thread(model.newRunnableProducer()).start();
//        }

        Model model = new DoubleLockConditionModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}
