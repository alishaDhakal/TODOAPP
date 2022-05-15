package android.example.todoapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private LiveData<List<Todo>> items;
    private AppDao appDao;

    public TodoViewModel(Application application) {
        super(application);
        AppDatabase database = DatabaseClient.getInstance(application).getAppDatabase();
        appDao = database.dao();
        items = appDao.getAllTodos();
    }

    public LiveData<List<Todo>> getAllItems() {
        return items;
    }

    public void insert(Todo item) {
        new InsertNoteAsyncTask(appDao).execute(item);
    }

    public void update(Todo item) {
        new UpdateNoteAsyncTask(appDao).execute(item);
    }

    public void delete(Todo item) {
        new DeleteNoteAsyncTask(appDao).execute(item);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private AppDao todoItemDao;

        private InsertNoteAsyncTask(AppDao todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        @Override
        protected Void doInBackground(Todo... todo) {
            todoItemDao.insert(todo[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private AppDao todoItemDao;

        private UpdateNoteAsyncTask(AppDao todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        @Override
        protected Void doInBackground(Todo... todo) {
            todoItemDao.update(todo[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private AppDao todoItemDao;

        private DeleteNoteAsyncTask(AppDao todoItemDao) {
            this.todoItemDao = todoItemDao;
        }

        @Override
        protected Void doInBackground(Todo... todo) {
            todoItemDao.delete(todo[0]);
            return null;
        }
    }
}
