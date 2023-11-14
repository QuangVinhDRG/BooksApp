package com.example.booksapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    List<Book> list;

    public CustomListAdapter(List<Book> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_layout, parent, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvAuthor = view.findViewById(R.id.tvAuthor);
        TextView tvPublisher = view.findViewById(R.id.tvPublisher);
        TextView tvDescription = view.findViewById(R.id.tvDescription);
        ImageView ivBookImage = view.findViewById(R.id.ivBookImage);
        Book book = list.get(position);
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvPublisher.setText(book.getPublisher());
        tvDescription.setText(book.getDescription());
        Picasso.get().load(book.getBookImageLink()).into(ivBookImage);
        return view;
    }
}
