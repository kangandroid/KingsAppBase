package com.king.mobile.downloadlib;

import com.king.mobile.downloadlib.model.Task;
import com.king.mobile.downloadlib.model.ThreadTask;
import com.king.mobile.util.Loker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

import okhttp3.Response;

class DownloadWorker implements Runnable {
    private long frequency = 500; // 更新进度频率 ms
    private ThreadTask tTask;
    private Task task;
    private Listener listener;

    public DownloadWorker(ThreadTask tTask, Task task) {
        this.tTask = tTask;
        this.task = task;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Loker.d("run======"+tTask.toString());
            long realStart = tTask.start + tTask.finished;
            long end = tTask.start + tTask.size;
            Response response = OkHttpHelper.doRangeRequest(task.url, realStart, end);
            if (response.code() == 206) {
                File file = new File(task.path);
                if(!file.exists()){
                    file.createNewFile();
                }
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                raf.setLength(task.size);
                raf.seek(realStart);
                InputStream inputStream = Objects.requireNonNull(response.body()).byteStream();
                byte[] buf = new byte[1024 * 512]; // 缓冲区0.5M
                int len = 0;
                long time = System.currentTimeMillis();
                while ((len = inputStream.read(buf)) != -1) {
                    Loker.e("len------------------"+ len);
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
                    tTask.isFinished = true;
                    listener.onCompleted(tTask);
                }
            }
        } catch (IOException e) {
            if (listener != null) {
                listener.onDownLoad(tTask);
            }
            e.printStackTrace();
        }

    }

    interface Listener {
        void onDownLoad(ThreadTask task);

        void onCompleted(ThreadTask task);
    }
}
