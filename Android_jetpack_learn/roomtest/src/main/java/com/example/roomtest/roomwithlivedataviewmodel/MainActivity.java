package com.example.roomtest.roomwithlivedataviewmodel;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.roomtest.R;
import com.example.roomtest.roomdemo.StudentAdapter;
import com.example.roomtest.roomdemo.database.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnInsertStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudentDialog();
            }
        });

        ListView lvStudent = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(MainActivity.this, studentList);
        lvStudent.setAdapter(studentAdapter);
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDeleteDialog(studentList.get(position));
                return false;
            }
        });

        myDatabase = MyDatabase.getInstance(this);

        StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentList.clear();
                studentList.addAll(students);
                studentAdapter.notifyDataSetChanged();
            }
        });
    }

    private void updateOrDeleteDialog(final Student student) {
        final String[] options = new String[]{"更新", "删除"};
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            openUpdateStudentDialog(student);
                        } else if (which == 1) {
                            new DeleteStudentTask(student).execute();
                        }
                    }
                }).show();
    }

    private void openAddStudentDialog() {
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Add Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new InsertStudentTask(etName.getText().toString(), etAge.getText().toString()).execute();
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }

    private void openUpdateStudentDialog(final Student student) {
        if (student == null) {
            return;
        }
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);
        etName.setText(student.name);
        etAge.setText(student.age);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Update Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    new UpdateStudentTask(student.id, etName.getText().toString(), etAge.getText().toString()).execute();
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }

    private class InsertStudentTask extends AsyncTask<Void, Void, Void> {
        String name;
        String age;

        public InsertStudentTask(final String name, final String age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            myDatabase.studentDao().insertStudent(new Student(name, age));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private class UpdateStudentTask extends AsyncTask<Void, Void, Void> {
        int id;
        String name;
        String age;

        public UpdateStudentTask(final int id, final String name, final String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            myDatabase.studentDao().updateStudent(new Student(id, name, age));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private class DeleteStudentTask extends AsyncTask<Void, Void, Void> {
        Student student;

        public DeleteStudentTask(Student student) {
            this.student = student;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            myDatabase.studentDao().deleteStudent(student);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
