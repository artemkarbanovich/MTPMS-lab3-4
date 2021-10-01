package karbanovich.fit.bstu.companydata;

import android.content.Context;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileHelper {
    private static final String FILE_NAME = "companies.json";


    public static List<Company> getCompanies(Context context) {

        if(isFilePresent(context)) {
            InputStreamReader isr = null;
            FileInputStream fis = null;
            Gson gson = new Gson();
            try {
                fis = new FileInputStream(context.getFilesDir().getAbsolutePath() + "/" + FILE_NAME);
                isr = new InputStreamReader(fis);

                DataItems companies = gson.fromJson(isr, DataItems.class);
                return companies.getCompanies();
            } catch (Exception e) { }
            finally {
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException e) { }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) { }
                }
            }
        }
        return new ArrayList<Company>();
    }

    public static boolean saveCompanies(Context context, List<Company> companies) {

        FileOutputStream fos = null;
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();

        dataItems.setCompanies(companies);
        String jsonString = gson.toJson(dataItems);

        try {
            fos = new FileOutputStream(context.getFilesDir().getAbsolutePath() + "/" + FILE_NAME, false);
            fos.write(jsonString.getBytes());
            return true;
        }
        catch (Exception e ) { }
        finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) { }
            }
        }
        return false;
    }

    private static boolean isFilePresent(Context context) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + FILE_NAME);
        return file.exists();
    }

    private static class DataItems {
        private List<Company> companies;

        public List<Company> getCompanies() {
            return companies;
        }
        public void setCompanies(List<Company> companies) {
            this.companies = companies;
        }
    }
}
