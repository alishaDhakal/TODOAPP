package android.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddItem extends AppCompatActivity implements View.OnClickListener {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editText = findViewById(R.id.editText);
        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String title = editText.getText().toString().trim();
        if (title.isEmpty()) {
            editText.setError("Item is required!");
            editText.requestFocus();
            return;
        }

        Todo todo = new Todo();
        todo.setTitle(title);
        TodoViewModel todoItemViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoItemViewModel.insert(todo);
        finish();
    }
}