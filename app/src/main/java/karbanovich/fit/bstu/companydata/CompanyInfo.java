package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class CompanyInfo extends AppCompatActivity {

    //first activity data
    private String name;
    private String dateFoundation;
    private String email;
    private boolean belCompany;

    //second activity data
    private String businessBranch;
    private int employeesNum;
    private Double capitalizationCost;
    private String websiteLink;

    //last activity data
    private String represName;
    private String represSurname;
    private String represPositionInCompany;
    private String represBirthday;
    private String represPhoneNum;
    private String represPhoto = "";


    //current activity data
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
    private ImageView represPhotoView;
    private Uri represPhotoUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        getSupportActionBar().hide();

        dataActivitiesSaver();
        binderViewId();
        outputCompanyData();
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

        if(represPhoto.length() > 2) {
            try {
                represPhotoView.setImageURI(null);
                represPhotoView.setImageURI(represPhotoUri);
            } catch (Exception e) { }
        }
        else {
            Resources resources = getResources();
            represPhotoView.setImageDrawable(resources.getDrawable(R.drawable.empty_repres_photo));
        }
    }

    private void dataActivitiesSaver() {
        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            name = arguments.getString("companyName");
            dateFoundation = arguments.getString("companyDateFoundation");
            email = arguments.getString("companyEmail");
            belCompany = arguments.getBoolean("belCompany");

            businessBranch = arguments.getString("businessBranch");
            employeesNum = arguments.getInt("employeesNum");
            capitalizationCost = arguments.getDouble("capitalizationCost");
            websiteLink = arguments.getString("websiteLink");

            represName = arguments.getString("represName");
            represSurname = arguments.getString("represSurname");
            represBirthday = arguments.getString("represBirthday");
            represPositionInCompany = arguments.getString("represPositionInCompany");
            represPhoneNum = arguments.getString("represPhoneNum");
            represPhoto = arguments.getString("represPhoto");
            represPhotoUri = Uri.parse(represPhoto);
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
        represPhotoView = (ImageView) findViewById(R.id.imgRepresPhoto);
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + represPhoneNum));
        startActivity(intent);
    }

    public void openLink(View view) {
        Uri uri;
        if(websiteLink.contains("http"))
            uri = Uri.parse(websiteLink);
        else
            uri = Uri.parse("http:\\" + websiteLink);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",email, null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }

    public void openImage(View view) {
        if(represPhoto.length() > 2) {
            Intent intent = new Intent(Intent.ACTION_VIEW, represPhotoUri);
            startActivity(intent);
        }
    }
}