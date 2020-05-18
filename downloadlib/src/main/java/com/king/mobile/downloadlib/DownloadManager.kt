package com.king.mobile.downloadlib

import android.content.Context
import androidx.lifecycle.LiveData
import com.king.mobile.base.BaseApplication
import com.king.mobile.downloadlib.DownThread.Listener
import com.king.mobile.util.Executor
import com.king.mobile.util.Loker
import okhttp3.Response
import java.io.File
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.collections.ArrayList

class DownloadManager(context: Context) : Listener {

    companion object { // 单例
        val instance: DownloadManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DownloadManager(BaseApplication.getContext())
        }
    }

    private val repository = Repository(context)
    private var tTasksMap: Map<String, List<ThreadTask>>; // 正在下载的任务
    private val waitingList: Queue<Task> = ConcurrentLinkedDeque() // 等待的任务队列

    init {
        val tasks = repository.getAllTask()
        val tTasks = repository.getAllTreadTask()
        tTasksMap = repository.getThreadTasksMap()
    }

    private val THREAD_TASK_MAX_SIZE = 10 * 1024 * 1024L
    var taskCount = 3
    val DOWNLOAD_DIR = "${context.filesDir.absolutePath}/download"


    private fun createTask(url: String): Task? {
        if (isTaskExist(url)) {
            return null
        }
        val lastIndexOf = url.lastIndexOf('.')
        val type = url.substring(lastIndexOf + 1)
        val fileName = "${UUID.randomUUID()}.${type}"
        val path = "$DOWNLOAD_DIR/$fileName"
        val task = Task(
                url = url,
                id = 0,
                name = fileName,
                path = path,
                size = 0,
                state = TaskState.STATE_CREATE,
                progress = 0f,
                completed = 0,
                createdAt = System.currentTimeMillis(),
                type = type,
                priority = 0,
                md5 = ""
        )
        repository.create(task)
        return task
    }

    private fun isTaskExist(url: String): Boolean {
        val task = tTasksMap.get(url)
        if (task != null) return true
        return false
    }

    /**
     * 获取所有任务
     */
    fun getAllTasks(): LiveData<List<Task>>? {
        return null
    }

    /**
     * 获取正在下载任务
     */
    fun getRunningTasks(): LiveData<List<Task>>? {
        return null
    }

    /**
     * 开始某个任务
     */
    fun start(task: Task) {
        //首先判断 任务是否已存在于下载列表
        if (tTasksMap.containsKey(task.url)) { // 如果存在则

        } else if (tTasksMap.size < taskCount) { // 正在下载数还未满
            Executor.getInstance().execute {
                val url = task.url
                val response: Response = OkHttpHelper.doRequest(url)
                var size = response.body?.contentLength() ?: -1
                if (response.code == 200 && size > 0) { // 可以下载
                    val dir = File(DOWNLOAD_DIR)
                    if (!dir.exists()) {
                        dir.mkdir()
                    }
                    task.size = size
                    createThreadTasks(task)
                } else { // bad url
                    task.state = -1
                    // todo update task in db
                }
            }
        } else { // 添加到等待队列
            waitingList.add(task)
        }

    }

    private fun createThreadTasks(task: Task) {
        if (tTasksMap.containsKey(task.url)) { // 如果存在则
            val tTasks = tTasksMap.get(task.url)
        } else {
            // todo update task in db
            val taskCount = Math.ceil((task.size.toDouble() / THREAD_TASK_MAX_SIZE)).toInt()
            val list = ArrayList<ThreadTask>();
            for (i in 0..taskCount - 1) {
                var size: Long
                if (i == taskCount - 1) {
                    size = task.size - THREAD_TASK_MAX_SIZE * i
                } else {
                    size = THREAD_TASK_MAX_SIZE
                }
                val threadTask = ThreadTask(url = task.url,
                        size = size,
                        start = THREAD_TASK_MAX_SIZE * i,
                        finished = 0,
                        createdAt = System.currentTimeMillis() / 1000,
                        id = 0L, isFinished = false)
                list.add(threadTask)
                Executor.getInstance().execute(DownThread(threadTask, task))
            }
        }

    }

    /**
     * 立即开始某个任务
     */
    fun startNow(task: Task) {

    }

    /**
     * 开始下载列表
     */
    fun start() {

    }

    /**
     * 停止所有任务
     */
    fun stop() {

    }

    /**
     * 停止任务
     */
    fun stop(task: Task) {

    }

    /**
     * 删除任务
     */
    fun delete(task: Task) {

    }

    override fun onDownLoad(task: ThreadTask) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted(task: ThreadTask) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun download(url: String) {
        val task = createTask(url)
        if (task != null) start(task) else {
            Loker.d("---------------task == null")
        }
    }
}
