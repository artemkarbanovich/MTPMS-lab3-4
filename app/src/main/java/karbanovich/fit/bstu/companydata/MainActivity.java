package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void addCompany(View view) {
        Intent intent = new Intent(this, AddCompany1.class);
        startActivity(intent);
    }

    public void showCompanies(View view) {
        Intent intent = new Intent(this, ViewCompanies.class);
        startActivity(intent);
    }
}