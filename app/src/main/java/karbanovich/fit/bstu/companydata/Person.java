package karbanovich.fit.bstu.companydata;


import android.net.Uri;

public class Person {

    private String name;    //имя
    private String surname; //фамилия
    private String positionInCompany;   //должность в компании
    private String birthday;    //дата рождения
    private String phoneNumber;    //мобильный телефон
    private String photo;

    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public String getPositionInCompany() {
        return this.positionInCompany;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getPhoto() {
        return this.photo;
    }


    Person(String n, String s, String pic, String b, String pn, String p)
    {
        name = n;
        surname = s;
        positionInCompany = pic;
        birthday = b;
        phoneNumber = pn;
        photo = p;
    }
}
