package karbanovich.fit.bstu.companydata;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCompany3 extends AppCompatActivity {

    //first activity data
    private String name;
    private String dateFoundation;
    private String email;
    private boolean belCompany;

    //prev activity data
    private String businessBranch;
    private String employeesNum;
    private String capitalizationCost;
    private String websiteLink;

    //current activity data
    private EditText represName;
    private EditText represSurname;
    private DatePicker represBirthday;
    private String represBirthdayStr;
    private EditText represPositionInCompany;
    private EditText represPhoneNum;
    private ImageView represPhoto;
    private Uri represPhotoUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company3);
        getSupportActionBar().hide();

        represName = (EditText) findViewById(R.id.edtTxtRepresName);
        represSurname = (EditText) findViewById(R.id.edtTxtRepresSurname);
        represBirthday = (DatePicker) findViewById(R.id.dtPcrRepresBirthday);
        represPositionInCompany = (EditText) findViewById(R.id.edtTxtRepresPositionInCompany);
        represPhoneNum = (EditText) findViewById(R.id.edtTxtRepresPhoneNum);
        represPhoto = (ImageView) findViewById(R.id.imgRepresPhoto);

        dataActivitiesSaver();
    }

    public void addPhoto(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPickerIntent.setType("image/*");
        resultRepresPhoto.launch(photoPickerIntent);
    }

    ActivityResultLauncher<Intent> resultRepresPhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                try {
                    Uri imageUri = result.getData().getData();
                    represPhotoUri = imageUri;
                    represPhoto.setImageURI(represPhotoUri);
                } catch (Exception e) { }
            }
        }
    });

    public void prevActivity(View view) {
        Intent intent = new Intent(this, AddCompany2.class);
        captureData(intent);
        startActivity(intent);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, AddCompany4.class);
        captureData(intent);
        startActivity(intent);
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

            represName.setText(arguments.getString("represName"));
            represSurname.setText(arguments.getString("represSurname"));

            Integer year = Integer.parseInt(arguments.getString("represBirthday").substring(0,4));
            Integer month = Integer.parseInt(arguments.getString("represBirthday").substring(5,7));
            Integer day = Integer.parseInt(arguments.getString("represBirthday").substring(8,10));

            represBirthday.updateDate(year, month, day);
            represPositionInCompany.setText(arguments.getString("represPositionInCompany"));
            represPhoneNum.setText(arguments.getString("represPhoneNum"));
            represPhotoUri = Uri.parse(arguments.getString("represPhoto"));

            if(represPhotoUri.toString() != "") {
                try {
                    final InputStream imageStream = getContentResolver().openInputStream(represPhotoUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    represPhoto.setImageBitmap(selectedImage);
                } catch (Exception e) { }
            }
        }
    }

    private void captureData(Intent intent) {
        String year = String.valueOf(represBirthday.getYear());
        String month = String.valueOf(represBirthday.getMonth());
        String day = String.valueOf(represBirthday.getDayOfMonth());
        if(month.length() == 1)
            month = "0" + month;
        if(day.length() == 1)
            day = "0" + day;
        represBirthdayStr = year + "." + month + "." + day;

        intent.putExtra("companyName", name);
        intent.putExtra("companyDateFoundation", dateFoundation);
        intent.putExtra("companyEmail", email);
        intent.putExtra("belCompany", belCompany);

        intent.putExtra("businessBranch", businessBranch);
        intent.putExtra("employeesNum", employeesNum);
        intent.putExtra("capitalizationCost", capitalizationCost);
        intent.putExtra("websiteLink", websiteLink);

        intent.putExtra("represName", represName.getText().toString());
        intent.putExtra("represSurname", represSurname.getText().toString());
        intent.putExtra("represBirthday", represBirthdayStr);
        intent.putExtra("represPositionInCompany", represPositionInCompany.getText().toString());
        intent.putExtra("represPhoneNum", represPhoneNum.getText().toString());
        intent.putExtra("represPhoto", represPhotoUri.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("represPhotoStrKey", represPhotoUri.toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        represPhotoUri = Uri.parse(savedInstanceState.getString("represPhotoStrKey"));
        if(represPhotoUri.toString() != "") {
            try {

                final InputStream imageStream = getContentResolver().openInputStream(represPhotoUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                represPhoto.setImageBitmap(selectedImage);
            } catch (Exception e) { }
        }
    }
}