package com.king.mobile.downloadlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

class DownloadThread implements Runnable {
    private long frequency = 500; // 更新进度频率 ms
    private ThreadTask tTask;
    private Task task;
    private Listener listener;

    public DownloadThread(ThreadTask tTask, Task task) {
        this.tTask = tTask;
        this.task = task;
    }

    @Override
    public void run() {
        try {
            long realStart = tTask.start + tTask.finished;
            long end = tTask.start + tTask.finished;
            Response response = OkHttpHelper.doRangeRequest(task.url, realStart, end);
            if (response.code() == 206) {
                File file = new File(task.path);
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.setLength(task.size);
                raf.seek(realStart);
                InputStream inputStream = response.body().byteStream();
                byte[] buf = new byte[1024 * 2]; // 缓冲区1M
                int len = -1;
                long time = System.currentTimeMillis();
                while (inputStream.read(buf) != -1) {
                    raf.write(buf, 0, len);
                    tTask.finished += len;
                    long ll = System.currentTimeMillis() - time;
                    if (ll > frequency) {
                        time = System.currentTimeMillis();
                        if (listener != null) {
                            listener.onDownLoad(tTask);
                        }
                    }
                }
                inputStream.close();
                if (listener != null) {
                    listener.onCompleted(tTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    interface Listener {
        void onDownLoad(ThreadTask task);

        void onCompleted(ThreadTask task);
    }
}
