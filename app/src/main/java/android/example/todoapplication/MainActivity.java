package android.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.todo_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TodoViewModel viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        LiveData<List<Todo>> items = viewModel.getAllItems();
        items.observe(this, todoItems -> {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, todoItems, viewModel);
            recyclerView.setAdapter(recyclerAdapter);
        });

        FloatingActionButton addTodoItemBtn = findViewById(R.id.add_btn);
        addTodoItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddItem.class);
                startActivity(i);
            }
        });
    }
}