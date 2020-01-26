package com.king.mobile.downloadlib

import java.io.File
import java.io.RandomAccessFile

class DownThread(tTask: ThreadTask, task: Task) : Runnable {
    private var frequency = 500 // 更新进度频率
    val task = task
    val tTask = tTask
    private lateinit var listener: Listener
    override fun run() {
        val realStart = tTask.start + tTask.finished
        val end = tTask.start + tTask.finished
        val response = OkhttpHelper.doRequestRange(task.url, realStart, end)
        if (response.code == 206) {
            val file = File(task.path)
            var raf = RandomAccessFile(file, "rwd")
            raf.setLength(task.size)
            raf.seek(realStart)
            val inputStream = response.body?.byteStream()
            val buf = ByteArray(1024 shl 2) // 缓冲区1M
            var len = -1
            var time = System.currentTimeMillis()
            while (inputStream!!.read(buf).also { len = it } != -1) {
                raf.write(buf, 0, len)
                tTask.finished += len.toLong()
                val ll: Long = System.currentTimeMillis() - time
                if (ll > frequency) {
                    time = System.currentTimeMillis()
                    listener?.onDownLoad(tTask)
                }
            }
            inputStream.close()
            listener?.onCompleted(tTask)
        }
    }


    interface Listener {
        fun onDownLoad(task: ThreadTask)
        fun onCompleted(task: ThreadTask)
    }

}