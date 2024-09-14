package edu.qc.seclass.grocery_list_team6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
    private List<User> users;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<User> users) {
        super(context, android.R.layout.simple_spinner_item, users);
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        User user = users.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(user.getName()); // assuming getName() returns the user's name

        return convertView;

    }


    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        User user = users.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(user.getName()); // assuming getName() returns the user's name

        return convertView;
    }
}
