package karbanovich.fit.bstu.companydata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;


public class ViewCompanies extends AppCompatActivity {

    ArrayList<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_companies);
        getSupportActionBar().hide();

        initializeAdapter();
    }

    private void initializeAdapter() {
        companies = (ArrayList<Company>) FileHelper.getCompanies(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.companyList);
        Intent intent = new Intent(this, CompanyInfo.class);

        CompanyAdapter.OnStateClickListener stateClickListener = new CompanyAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Company company, int position) {
                intent.putExtra("companyName", company.getName());
                intent.putExtra("companyDateFoundation", company.getDateFoundation());
                intent.putExtra("companyEmail", company.getEmail());
                intent.putExtra("belCompany", company.getBelCompany());

                intent.putExtra("businessBranch", company.getBusinessBranch());
                intent.putExtra("employeesNum", company.getEmployeesNum());
                intent.putExtra("capitalizationCost", company.getCapitalizationCost());
                intent.putExtra("websiteLink", company.getWebsiteLink());

                intent.putExtra("represName", company.getRepresentativeOfCompany().getName());
                intent.putExtra("represSurname", company.getRepresentativeOfCompany().getSurname());
                intent.putExtra("represBirthday", company.getRepresentativeOfCompany().getBirthday());
                intent.putExtra("represPositionInCompany", company.getRepresentativeOfCompany().getPositionInCompany());
                intent.putExtra("represPhoneNum", company.getRepresentativeOfCompany().getPhoneNumber());
                intent.putExtra("represPhoto", company.getRepresentativeOfCompany().getPhoto());

                startActivity(intent);
            }
        };
        CompanyAdapter adapter = new CompanyAdapter(this, companies, stateClickListener);
        recyclerView.setAdapter(adapter);
    }
}