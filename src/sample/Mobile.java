package sample;

public class Mobile {
    public String brand;
    public String model;
    public String size;
    public String battery;
    public String weight;
    public String price;

    public Mobile(String brand, String model, String size, String battery, String weight, String price) {
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.battery = battery;
        this.weight = weight;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
