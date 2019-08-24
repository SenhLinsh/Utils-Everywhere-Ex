package com.linsh.lshutils.tools;

import android.os.AsyncTask;

import java.util.concurrent.Executor;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2018/04/11
 *    desc   : 简便构建 AsyncTask 步骤
 *
 *            注意: AsyncTask 默认是串行执行的, 所以如果大量使用 AsyncTask, 可能会导致阻塞, 任务无法立即执行. 所以建议用来执行短时间
 *                 且无速度要求的子线程任务.
 *
 *                 如果需要并行执行, 可以通过 {@link AsyncTask#executeOnExecutor(Executor, Object[])} 来分配一个线程池给它.
 * </pre>
 */
public class AsyncTaskBuilderEx {

    public static LshAsyncTask build(Runnable asyncTask) {
        return build(null, asyncTask, null);
    }

    public static LshAsyncTask build(Runnable asyncTask, Runnable postTack) {
        return build(null, asyncTask, postTack);
    }

    public static LshAsyncTask build(Runnable preTask, Runnable asyncTask, Runnable postTack) {
        return new LshAsyncTask(preTask, asyncTask, postTack);
    }

    public static LshAsyncTask execute(Runnable asyncTask) {
        return execute(null, asyncTask, null);
    }

    public static LshAsyncTask execute(Runnable asyncTask, Runnable postTack) {
        return execute(null, asyncTask, postTack);
    }

    public static LshAsyncTask execute(Runnable preTask, Runnable asyncTask, Runnable postTack) {
        LshAsyncTask task = new LshAsyncTask(preTask, asyncTask, postTack);
        task.execute();
        return task;
    }

    public static class LshAsyncTask extends AsyncTask<Void, Void, Void> {

        private Runnable preTask;
        private Runnable asyncTask;
        private Runnable postTack;

        public LshAsyncTask(Runnable preTask, Runnable asyncTask, Runnable postTack) {
            this.preTask = preTask;
            this.asyncTask = asyncTask;
            this.postTack = postTack;
        }

        @Override
        protected Void doInBackground(Void... strings) {
            if (asyncTask != null) {
                asyncTask.run();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (preTask != null) {
                preTask.run();
            }
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            if (postTack != null) {
                postTack.run();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
