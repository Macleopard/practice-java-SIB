import javax.xml.crypto.Data;

public class Database {
    private String name;
    private String surname;
    private String company;
    private String position;
    private String mobilePhone;
    private String workingPhone;

    public Database(String name, String surname, String company, String position, String mobilePhone, String workingPhone)
    {
        this.name = name;
        this.surname = surname;
        this.company = company;
        this.position = position;
        this.mobilePhone = mobilePhone;
        this.workingPhone = workingPhone;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkingPhone() {
        return workingPhone;
    }

    public String[] toStr() {
        String[] res = {name, surname, company, position, mobilePhone, workingPhone};
        return res;
    }

    public void setWorkingPhone(String workingPhone) {
        this.workingPhone = workingPhone;
    }
}
