package karbanovich.fit.bstu.companydata;

public class Company {

    //first activity
    private String name;    //название компании
    private String dateFoundation;  //дата основания
    private String email;   //эл. почта
    private boolean belCompany;     //белорусская ли компания

    //second activity
    private String businessBranch;  //отрасль бизнеса
    private Integer employeesNum;   //количество сотрудников
    private double capitalizationCost;  //стоимость капитализации
    private String websiteLink;     //ссылка на веб-сайт

    //third activity
    private Person representativeOfCompany;     //представитель компании

    public String getName() {
        return this.name;
    }
    public String getDateFoundation() {
        return this.dateFoundation;
    }
    public String getEmail() {
        return this.email;
    }
    public boolean getBelCompany() {
        return this.belCompany;
    }
    public String getBusinessBranch() {
        return this.businessBranch;
    }
    public Integer getEmployeesNum() {
        return this.employeesNum;
    }
    public double getCapitalizationCost() {
        return this.capitalizationCost;
    }
    public Person getRepresentativeOfCompany() {
        return this.representativeOfCompany;
    }
    public String getWebsiteLink() {
        return this.websiteLink;
    }

    Company(String n, String y, String e, String eb, boolean bc, Integer em, double cc, String wl, Person roc)
    {
        name = n;
        dateFoundation = y;
        email = e;
        businessBranch = eb;
        belCompany = bc;
        employeesNum = em;
        capitalizationCost = cc;
        websiteLink = wl;
        representativeOfCompany = roc;
    }
}


