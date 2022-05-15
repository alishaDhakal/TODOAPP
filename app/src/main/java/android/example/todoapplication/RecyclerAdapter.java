package android.example.todoapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AppViewHolder> {
    Context context;
    List<Todo> items;
    TodoViewModel viewModel;

    public RecyclerAdapter(Context context, List<Todo> items, TodoViewModel viewModel) {
        this.context = context;
        this.items = items;
        this.viewModel = viewModel;
    }


    @Override
    public RecyclerAdapter.AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_row, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.AppViewHolder holder, int position) {
        String text = (position+1) + ". " + items.get(position).getTitle();
        holder.todoItem.setText(text);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Serializable {
        TextView todoItem;

        public AppViewHolder(View itemView) {
            super(itemView);
            todoItem = (TextView) itemView.findViewById(R.id.textTitle);
            todoItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Todo item = items.get(getAdapterPosition());
            Intent i = new Intent(context, UpdateItem.class);
            i.putExtra("todo", item);
            context.startActivity(i);
        }
    }
}
