package carsharing;

public class Car {

    private int id;
    private int company_id;

    private String name;

    public Car(int company_id, String name) {
        this.company_id = company_id;
        this.name = name;
    }

    public Car(int id, int company_id, String name) {
        this.id = id;
        this.company_id = company_id;
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
