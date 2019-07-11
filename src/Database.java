public class Database {
    private String name, surname, company, position, mobilePhone, workingPhone;

    Database(String name, String surname, String company, String position, String mobilePhone, String workingPhone)
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

    String[] toStr() {
        return new String[]{name, surname, company, position, mobilePhone, workingPhone};
    }

    public void setWorkingPhone(String workingPhone) {
        this.workingPhone = workingPhone;
    }

    public String getDataFromInt(int id){
        return getString(id);
    }

    static String getString(int id) {
        switch(id){
            case 0: return "Name";
            case 1: return "Surname";
            case 2: return "Company";
            case 3: return "Position";
            case 4: return "Mobile phone";
            case 5: return "Working phone";
        }
        return "";
    }

}
