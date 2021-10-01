package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddCompany1 extends AppCompatActivity {

    //current activity data
    private EditText name;
    private EditText dateFoundation;
    private EditText email;
    private CheckBox isBelCompany;

    //next activity data
    private String businessBranch;
    private String employeesNum;
    private String capitalizationCost;
    private String websiteLink;

    //last activity data
    private String represName;
    private String represSurname;
    private String represPositionInCompany;
    private String represBirthday = "2021.09.15";
    private String represPhoneNum;
    private String represPhoto = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company1);
        getSupportActionBar().hide();

        name = (EditText) findViewById(R.id.edtTxtCompanyName);
        dateFoundation = (EditText) findViewById(R.id.edtTxtDateFoundation);
        email = (EditText) findViewById(R.id.edtTxtCompanyEmail);
        isBelCompany = (CheckBox) findViewById(R.id.chckBxIsBelCompany);

        dataActivitiesSaver();
    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, AddCompany2.class);

        boolean belCompany;
        if(isBelCompany.isChecked()) belCompany = true;
        else belCompany = false;

        intent.putExtra("companyName", name.getText().toString());
        intent.putExtra("companyDateFoundation", dateFoundation.getText().toString());
        intent.putExtra("companyEmail", email.getText().toString());
        intent.putExtra("belCompany", belCompany);

        intent.putExtra("businessBranch", businessBranch);
        intent.putExtra("employeesNum", employeesNum);
        intent.putExtra("capitalizationCost", capitalizationCost);
        intent.putExtra("websiteLink", websiteLink);

        intent.putExtra("represName", represName);
        intent.putExtra("represSurname", represSurname);
        intent.putExtra("represBirthday", represBirthday);
        intent.putExtra("represPositionInCompany", represPositionInCompany);
        intent.putExtra("represPhoneNum", represPhoneNum);
        intent.putExtra("represPhoto", represPhoto);

        startActivity(intent);
    }

    private void dataActivitiesSaver() {
        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            name.setText(arguments.getString("companyName"));
            dateFoundation.setText(arguments.getString("companyDateFoundation"));
            email.setText(arguments.getString("companyEmail"));
            if(arguments.getBoolean("belCompany")) isBelCompany.setChecked(true);
            else isBelCompany.setChecked(false);

            businessBranch = arguments.getString("businessBranch");
            employeesNum = arguments.getString("employeesNum");
            capitalizationCost = arguments.getString("capitalizationCost");
            websiteLink = arguments.getString("websiteLink");

            represName = arguments.getString("represName");
            represSurname = arguments.getString("represSurname");
            represBirthday = arguments.getString("represBirthday");
            represPositionInCompany = arguments.getString("represPositionInCompany");
            represPhoneNum = arguments.getString("represPhoneNum");
            represPhoto = arguments.getString("represPhoto");
        }
    }
}