package karbanovich.fit.bstu.companydata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    interface OnStateClickListener{
        void onStateClick(Company company, int position);
    }

    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<Company> companies;

    CompanyAdapter(Context context, List<Company> companies, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.companies = companies;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.company_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompanyAdapter.ViewHolder holder, int position) {
        Company company = companies.get(position);
        holder.companyNameView.setText(company.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onStateClick(company, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView companyNameView;
        ViewHolder(View view){
            super(view);
            companyNameView = (TextView) view.findViewById(R.id.txtCompanyName);
        }
    }
}
