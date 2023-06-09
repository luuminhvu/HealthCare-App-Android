package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages ={
            {"Package1 : Kiểm tra tổng quát","","","","999"},
            {"Package2 : Kiểm tra đường huyết","","","","299"},
            {"Package3 : Kiểm tra Covid","","","","300"},
            {"Package4 : Kiểm tra tuyến giáp","","","","283"},
            {"Package5 : Kiểm tra hệ miễn dịch","","","","532"}
    };
    private String [] package_details = {
            "Kiểm tra bên trong\n" + "Kiểm tra bên ngoài\n" + "Chụp X-quang\n", "Kiểm tra lượng đường trong máu", "Kiểm tra kháng thể Covid"+ "Tiêm Vacxin",
            "Kiểm tra tuyến giáp\n" + "CRP\n" + "ESR\n" , "Kiểm tra hệ miễn dịch\n"+ "RBS\n"
    };
    HashMap<String, String>item;
    ArrayList list;
    SimpleAdapter adapter;
    Button btnGoToCart, btnBack;
    ListView lvPackages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart = findViewById(R.id.buttonGoToCart);
        btnBack = findViewById(R.id.buttonLTBack);
        lvPackages = findViewById(R.id.idlstLabTest);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String,String>();
          item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Giá Tiền: "+ packages[i][4]+"$");
            list.add(item);
        }
        adapter = new SimpleAdapter(LabTestActivity.this, list, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lvPackages.setAdapter(adapter);
        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                intent.putExtra("text1", packages[i][0]);
                intent.putExtra("text2", package_details[i]);
                intent.putExtra("text3", packages[i][4]);
                startActivity(intent);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}