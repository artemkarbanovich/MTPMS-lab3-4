package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddCompany4 extends AppCompatActivity {

    //first activity data
    private String name;
    private String dateFoundation;
    private String email;
    private boolean belCompany;

    //second activity data
    private String businessBranch;
    private String employeesNum;
    private String capitalizationCost;
    private String websiteLink;

    //last activity data
    private String represName;
    private String represSurname;
    private String represPositionInCompany;
    private String represBirthday;
    private String represPhoneNum;
    private String represPhoto;


    //current activity data view
    private TextView nameView;
    private TextView dateFoundationView;
    private TextView emailView;
    private TextView isBelCompanyView;

    private TextView businessBranchView;
    private TextView employeesNumView;
    private TextView capitalizationCostView;
    private TextView websiteLinkView;

    private TextView represNameView;
    private TextView represSurnameView;
    private TextView represBirthdayView;
    private TextView represBirthdayStrView;
    private TextView represPositionInCompanyView;
    private TextView represPhoneNumView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company4);
        getSupportActionBar().hide();

        dataActivitiesSaver();
        binderViewId();
        outputCompanyData();
    }

    public void prevActivity(View view) {
        Intent intent = new Intent(this, AddCompany3.class);

        intent.putExtra("companyName", name);
        intent.putExtra("companyDateFoundation", dateFoundation);
        intent.putExtra("companyEmail", email);
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

    public void saveCompany(View view) {

        try {
            Person person = new Person(represName, represSurname, represPositionInCompany, represBirthday, represPhoneNum, represPhoto);
            Company company = new Company(name, dateFoundation, email, businessBranch, belCompany, Integer.parseInt(employeesNum), Double.parseDouble(capitalizationCost), websiteLink, person);
            List<Company> companies;

            companies = FileHelper.getCompanies(this);
            companies.add(company);

            Toast toast;
            if(FileHelper.saveCompanies(this, companies) == true)
                toast = Toast.makeText(this, "Компания успешно добавлена", Toast.LENGTH_LONG);
            else
                toast = Toast.makeText(this, "Компания НЕ добавлена", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        catch (Exception e) {
            Toast toast = Toast.makeText(this, "Проверьте введенные данные",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void binderViewId() {
        nameView = (TextView) findViewById(R.id.txtCompanyName);
        dateFoundationView = (TextView) findViewById(R.id.txtDateFoundation);
        emailView = (TextView) findViewById(R.id.txtCompanyEmail);
        isBelCompanyView = (TextView) findViewById(R.id.txtIsBelCompany);

        businessBranchView = (TextView) findViewById(R.id.txtBusinessBranch);
        employeesNumView = (TextView) findViewById(R.id.txtEmployeesNum);
        capitalizationCostView = (TextView) findViewById(R.id.txtCapitalizationCost);
        websiteLinkView = (TextView) findViewById(R.id.txtWebsiteLink);

        represNameView = (TextView) findViewById(R.id.txtRepresNameSurname);
        represBirthdayView = (TextView) findViewById(R.id.txtRepresBirthday);
        represPositionInCompanyView = (TextView) findViewById(R.id.txtRepresPositionInCompany);
        represPhoneNumView = (TextView) findViewById(R.id.txtRepresPhoneNum);
    }

    private void outputCompanyData() {
        nameView.setText("Название компании: " + name);
        dateFoundationView.setText("Дата основания: " + dateFoundation);
        emailView.setText("Email: " + email);
        if(belCompany == true)
            isBelCompanyView.setText("Белорусская компания? да");
        else
            isBelCompanyView.setText("Белорусская компания? нет");

        businessBranchView.setText("Отрасль бизнеса: " + businessBranch);
        employeesNumView.setText("Количество работников: " + employeesNum + " чел.");
        capitalizationCostView.setText("Стоимость капитализации: " + capitalizationCost + " $");
        websiteLinkView.setText("Веб-сайт компании: " + websiteLink);

        represNameView.setText("Имя представителя: " + represName + " " + represSurname);
        represBirthdayView.setText("Дата рождения: " + represBirthday);
        represPositionInCompanyView.setText("Должность: " + represPositionInCompany);
        represPhoneNumView.setText("Номер телефона: " + represPhoneNum);
    }

    private void dataActivitiesSaver() {
        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            name = arguments.getString("companyName");
            dateFoundation = arguments.getString("companyDateFoundation");
            email = arguments.getString("companyEmail");
            belCompany = arguments.getBoolean("belCompany");

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