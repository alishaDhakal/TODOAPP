package android.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateItem extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Todo todo;
    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todo = (Todo) getIntent().getSerializableExtra("todo");
        editText = findViewById(R.id.updateText);
        editText.setText(todo.getTitle());

        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(this);

        Button deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.delete(todo);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        final String title = editText.getText().toString().trim();
        if (title.isEmpty()) {
            editText.setError("Item is required!");
            editText.requestFocus();
            return;
        }

        todo.setTitle(title);
        viewModel.update(todo);
        finish();
    }
}