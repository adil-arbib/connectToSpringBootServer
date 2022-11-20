package com.arbib.connecttoserverapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arbib.connecttoserverapp.model.Employee;
import com.arbib.connecttoserverapp.retrofit.EmployeeApi;
import com.arbib.connecttoserverapp.retrofit.RetrofitService;

import java.security.acl.Group;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadEmployees();




    }

    private void loadEmployees(){
        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
        employeeApi.getAllEmployees()
                .enqueue(new Callback<List<Employee>>() {
                    @Override
                    public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                        if(response.isSuccessful()){
                            for(Employee e : response.body()){
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Employee>> call, Throwable t) {

                    }
                });
    }

    private void saveEmployee(){
        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
                Employee employee = new Employee();
        employee.setName("dajdad");
        employee.setLocation("location0");
        employee.setLocation("branch0");

        employeeApi.save(employee)
                .enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Toast.makeText(getApplicationContext(), "saved successfully!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "an error was occurred!!", Toast.LENGTH_SHORT).show();

                    }
                });
    }


}