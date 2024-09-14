package edu.qc.seclass.grocery_list_team6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private UserDBHelper dbHelper;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText groceryItem, groceryQuantity, groceryType;
    private Button saveButton;
    private User currUser;
    MyRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    private ArrayList<String> items;

    ArrayList<Grocery> groceryList;


    private User User1;
    private User User2;
    private User User3;
    private User User4;
    private User User5;
    private User User6;
    private User User7;
    private User User8;
    private User User9;
    private User User10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //FAB
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopUpDialog();
            }
        });


        //FAB 2
        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {clearList();}

        });

        Spinner user_spinner = findViewById(R.id.spinner2);
        ArrayList<User> userList = new ArrayList<>();
        User1 = new User("List_1");
        currUser = User1;

        userList.add(User1);
        loadContent(User1);
        refreshView();

        User2 = new User("List_2");
        userList.add(User2);
        loadContent(User2);

        User3 = new User("List_3");
        userList.add(User3);
        loadContent(User3);

        User4 = new User("List_4");
        userList.add(User4);
        loadContent(User4);

        User5 = new User("List_5");
        userList.add(User5);
        loadContent(User5);

        User6 = new User("List_6");
        userList.add(User6);
        loadContent(User6);


        User7 = new User("List_7");
        userList.add(User7);
        loadContent(User7);


        User8 = new User("List_8");
        userList.add(User8);
        loadContent(User8);

        User9 = new User("List_9");
        userList.add(User9);
        loadContent(User9);

        User10 = new User("List_10");
        userList.add(User10);
        loadContent(User10);

        UserAdapter adapter = new UserAdapter(this, userList);
        user_spinner.setAdapter(adapter);


        user_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected user
                User selectedUser = (User) parent.getItemAtPosition(position);

                // Do something with the selected user
                currUser = selectedUser;
                refreshView();
                // For example, display a toast with the selected user's name
                Toast.makeText(MainActivity.this, "Current user: " + currUser.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when no item is selected
            }
        });

// Long-click listener for the Spinner
        user_spinner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Open a dialog or context menu for editing the name
                // For example, you can use AlertDialog or create a custom context menu
                // Here's a basic example using AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Edit User Name");
                final EditText input = new EditText(MainActivity.this);
                input.setText(currUser.getName());
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = input.getText().toString();
                        // Update the user's name
                        currUser.setName(newName);
                        // Refresh the view to reflect the changes
                        refreshView();
                        adapter.notifyDataSetChanged();
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true; // Indicates that the long click event is consumed
            }
        });

        items = new ArrayList<>(groceryListToStringList(currUser.getGroceryList()));


    }


    public void refreshView(){
        User user = currUser;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        groceryList = user.getGroceryList();
        recyclerViewAdapter = new MyRecyclerViewAdapter(this, groceryList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void createPopUpDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.pop_up, null);
        groceryType = view.findViewById(R.id.grocery_type);
        groceryItem = view.findViewById(R.id.grocery_item);
        groceryQuantity = view.findViewById(R.id.quantity);
        saveButton = view.findViewById(R.id.saveButton);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        groceryType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    groceryItem.setEnabled(true);
                    groceryItem.setFocusableInTouchMode(true);
                    groceryQuantity.setVisibility(View.VISIBLE);
                    groceryQuantity.setEnabled(true);
                    groceryQuantity.setFocusableInTouchMode(true);
                    saveButton.setEnabled(true);
                } else {
                    groceryItem.setEnabled(false);
                    groceryItem.setFocusable(false);
                    groceryQuantity.setVisibility(View.GONE);
                    groceryQuantity.setEnabled(false);
                    groceryQuantity.setFocusable(false);
                    saveButton.setEnabled(false);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!groceryItem.getText().toString().isEmpty() && !groceryQuantity.getText().toString().isEmpty()) {
                    Grocery item = new Grocery();
                    item.setName(groceryItem.getText().toString());
                    item.setQty(groceryQuantity.getText().toString());
                    currUser.addToList(item);
                    refreshView();
                    dialog.hide();
                }
            }
        });
    }

    public void loadContent(User listName) {
        Log.d("LoadContent", "Loading content...");
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, listName.getName() + ".json");

        byte[] content = new byte[(int) readFrom.length()];
        FileInputStream stream = null;

        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String jsonString = new String(content);

            // Convert JSON string to JSONArray
            JSONArray jsonArray = new JSONArray(jsonString);

            // Convert JSONArray to List<Grocery>
            ArrayList<Grocery> groceryList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Grocery grocery = new Grocery();
                grocery.setName(jsonObject.getString("name"));
                grocery.setQty(jsonObject.getString("quantity"));
                Log.d("LoadContent", grocery.getName() + " " + grocery.getQty());
                groceryList.add(grocery);
                listName.addToList(grocery);
            }

            // Initialize RecyclerView
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Set adapter for RecyclerView
            recyclerViewAdapter = new MyRecyclerViewAdapter(this, groceryList);
            recyclerView.setAdapter(recyclerViewAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "saving...");
        File path = getApplicationContext().getFilesDir();
        try {
            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray1 = new JSONArray();
            for (Grocery grocery : User1.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray1.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer = new FileOutputStream(new File(path, User1.getName() + ".json"));
            writer.write(jsonArray1.toString().getBytes());
            writer.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray2 = new JSONArray();
            for (Grocery grocery : User2.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray2.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer2 = new FileOutputStream(new File(path, User2.getName() + ".json"));
            writer2.write(jsonArray2.toString().getBytes());
            writer2.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray3 = new JSONArray();
            for (Grocery grocery : User3.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray3.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer3 = new FileOutputStream(new File(path, User3.getName() + ".json"));
            writer3.write(jsonArray3.toString().getBytes());
            writer3.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray4 = new JSONArray();
            for (Grocery grocery : User4.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray4.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer4 = new FileOutputStream(new File(path, User4.getName() + ".json"));
            writer4.write(jsonArray4.toString().getBytes());
            writer4.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray5 = new JSONArray();
            for (Grocery grocery : User5.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray5.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer5 = new FileOutputStream(new File(path, User5.getName() + ".json"));
            writer5.write(jsonArray5.toString().getBytes());
            writer5.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray6 = new JSONArray();
            for (Grocery grocery : User6.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray6.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer6 = new FileOutputStream(new File(path, User6.getName() + ".json"));
            writer6.write(jsonArray6.toString().getBytes());
            writer6.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray7 = new JSONArray();
            for (Grocery grocery : User7.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray7.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer7 = new FileOutputStream(new File(path, User7.getName() + ".json"));
            writer7.write(jsonArray7.toString().getBytes());
            writer7.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray8 = new JSONArray();
            for (Grocery grocery : User8.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray8.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer8 = new FileOutputStream(new File(path, User8.getName() + ".json"));
            writer8.write(jsonArray8.toString().getBytes());
            writer8.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray9 = new JSONArray();
            for (Grocery grocery : User9.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray9.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer9 = new FileOutputStream(new File(path, User9.getName() + ".json"));
            writer9.write(jsonArray9.toString().getBytes());
            writer9.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray10 = new JSONArray();
            for (Grocery grocery : User10.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());

                Log.d("onDestroy", grocery.getName() + " " + grocery.getQty());
                jsonArray10.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer10 = new FileOutputStream(new File(path, User10.getName() + ".json"));
            writer10.write(jsonArray10.toString().getBytes());
            writer10.close();





        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d("onStop", "saving...");
        File path = getApplicationContext().getFilesDir();
        try {
            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray1 = new JSONArray();
            for (Grocery grocery : User1.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray1.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer = new FileOutputStream(new File(path, User1.getName() + ".json"));
            writer.write(jsonArray1.toString().getBytes());
            writer.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray2 = new JSONArray();
            for (Grocery grocery : User2.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray2.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer2 = new FileOutputStream(new File(path, User2.getName() + ".json"));
            writer2.write(jsonArray2.toString().getBytes());
            writer2.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray3 = new JSONArray();
            for (Grocery grocery : User3.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray3.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer3 = new FileOutputStream(new File(path, User3.getName() + ".json"));
            writer3.write(jsonArray3.toString().getBytes());
            writer3.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray4 = new JSONArray();
            for (Grocery grocery : User4.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onstop", grocery.getName() + " " + grocery.getQty());
                jsonArray4.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer4 = new FileOutputStream(new File(path, User4.getName() + ".json"));
            writer4.write(jsonArray4.toString().getBytes());
            writer4.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray5 = new JSONArray();
            for (Grocery grocery : User5.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onstop", grocery.getName() + " " + grocery.getQty());
                jsonArray5.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer5 = new FileOutputStream(new File(path, User5.getName() + ".json"));
            writer5.write(jsonArray5.toString().getBytes());
            writer5.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray6 = new JSONArray();
            for (Grocery grocery : User6.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray6.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer6 = new FileOutputStream(new File(path, User6.getName() + ".json"));
            writer6.write(jsonArray6.toString().getBytes());
            writer6.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray7 = new JSONArray();
            for (Grocery grocery : User7.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onstop", grocery.getName() + " " + grocery.getQty());
                jsonArray7.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer7 = new FileOutputStream(new File(path, User7.getName() + ".json"));
            writer7.write(jsonArray7.toString().getBytes());
            writer7.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray8 = new JSONArray();
            for (Grocery grocery : User8.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray8.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer8 = new FileOutputStream(new File(path, User8.getName() + ".json"));
            writer8.write(jsonArray8.toString().getBytes());
            writer8.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray9 = new JSONArray();
            for (Grocery grocery : User9.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());
                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray9.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer9 = new FileOutputStream(new File(path, User9.getName() + ".json"));
            writer9.write(jsonArray9.toString().getBytes());
            writer9.close();

            // Convert List<Grocery> to JSONArray
            JSONArray jsonArray10 = new JSONArray();
            for (Grocery grocery : User10.getGroceryList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", grocery.getName());
                jsonObject.put("quantity", grocery.getQty());

                Log.d("onStop", grocery.getName() + " " + grocery.getQty());
                jsonArray10.put(jsonObject);
            }

            // Write JSONArray to file
            FileOutputStream writer10 = new FileOutputStream(new File(path, User10.getName() + ".json"));
            writer10.write(jsonArray10.toString().getBytes());
            writer10.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    private void clearList(){
        currUser.emptyList();
        refreshView();
    }

    private void saveGroceryList(User user) {

        // Get the grocery list from the user
        ArrayList<Grocery> groceryList = user.getGroceryList();

        // Add each grocery item to the database
        for (Grocery item : groceryList) {
            dbHelper.addGrocery(item);
        }
    }

    public List<String> groceryListToStringList(List<Grocery> groceryList) {
        List<String> stringList = new ArrayList<>();
        for (Grocery grocery : groceryList) {
            // Convert each Grocery object to a string representation
            String groceryString = grocery.getName() + ", " + grocery.getQty();
            stringList.add(groceryString);
        }
        return stringList;
    }


}