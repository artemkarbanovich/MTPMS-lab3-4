package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class AddCompany2 extends AppCompatActivity {

    //prev activity data
    private String name;
    private String dateFoundation;
    private String email;
    private boolean belCompany;

    //current activity data
    private String[] businessBranches = {"Выберите отрасль...","Торговля", "Общепит", "ИТ", "Производство", "Услуги", "Полезные ископаемые", "Прочее"};
    private Spinner businessBranch;
    private String selectedBusinessBranch;
    private EditText employeesNum;
    private EditText capitalizationCost;
    private EditText websiteLink;

    //next activity data
    private String represName;
    private String represSurname;
    private String represPositionInCompany;
    private String represBirthday = "2021.09.15";
    private String represPhoneNum;
    private String represPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company2);
        getSupportActionBar().hide();

        businessBranch = (Spinner) findViewById(R.id.spnrBusinessBranch);
        employeesNum = (EditText) findViewById(R.id.edtTxtEmployeesNum);
        capitalizationCost = (EditText) findViewById(R.id.edtTxtCapitalizationCost);
        websiteLink = (EditText) findViewById(R.id.edtTxtWebsiteLink);

        dataActivitiesSaver();
        spinnerCreator();
    }

    public void prevActivity(View view) {
        Intent intent = new Intent(this, AddCompany1.class);
        captureData(intent);
        startActivity(intent);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, AddCompany3.class);
        captureData(intent);
        startActivity(intent);
    }

    private void spinnerCreator() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, businessBranches);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessBranch.setAdapter(adapter);

        if(selectedBusinessBranch != businessBranches[0]) {
            int spinnerPosition = adapter.getPosition(selectedBusinessBranch);
            businessBranch.setSelection(spinnerPosition);
        }
    }

    private void dataActivitiesSaver() {
        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            name = arguments.getString("companyName");
            dateFoundation = arguments.getString("companyDateFoundation");
            email = arguments.getString("companyEmail");
            belCompany = arguments.getBoolean("belCompany");

            selectedBusinessBranch = arguments.getString("businessBranch");
            employeesNum.setText(arguments.getString("employeesNum"));
            capitalizationCost.setText(arguments.getString("capitalizationCost"));
            websiteLink.setText(arguments.getString("websiteLink"));

            represName = arguments.getString("represName");
            represSurname = arguments.getString("represSurname");
            represBirthday = arguments.getString("represBirthday");
            represPositionInCompany = arguments.getString("represPositionInCompany");
            represPhoneNum = arguments.getString("represPhoneNum");
            represPhoto = arguments.getString("represPhoto");
        }
    }

    private void captureData(Intent intent) {
        intent.putExtra("companyName", name);
        intent.putExtra("companyDateFoundation", dateFoundation);
        intent.putExtra("companyEmail", email);
        intent.putExtra("belCompany", belCompany);

        intent.putExtra("businessBranch", businessBranch.getSelectedItem().toString());
        intent.putExtra("employeesNum", employeesNum.getText().toString());
        intent.putExtra("capitalizationCost", capitalizationCost.getText().toString());
        intent.putExtra("websiteLink", websiteLink.getText().toString());

        intent.putExtra("represName", represName);
        intent.putExtra("represSurname", represSurname);
        intent.putExtra("represBirthday", represBirthday);
        intent.putExtra("represPositionInCompany", represPositionInCompany);
        intent.putExtra("represPhoneNum", represPhoneNum);
        intent.putExtra("represPhoto", represPhoto);
    }
}