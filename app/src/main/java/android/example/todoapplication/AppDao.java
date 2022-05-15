package android.example.todoapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getAllTodos();
    @Insert
    void insert(Todo todoItem);
    @Delete
    void delete(Todo todoItem);
    @Update
    void update(Todo todoItem);
}
