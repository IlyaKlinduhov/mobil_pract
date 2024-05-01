    package ru.mirea.klinduhovir.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase db = App.getInstance().getDatabase();
        final EmployeeDAO employeeDAO = db.employeeDAO();

        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;

        employeeDAO.insert(employee);
        List<Employee> employees = employeeDAO.getAll();
        employee = employeeDAO.getById(1);
        employee.salary = 20000;
        employeeDAO.update(employee);
        Log.d("Employee", employee.name + " " + employee.salary);
    }
}