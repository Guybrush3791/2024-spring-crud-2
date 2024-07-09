package org.java.spring_crud2.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prodotto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    private int price;
    private int rebate;

    private int quantity;

    @Column(length = 32, nullable = false)
    private String status;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    public Product() {
    }

    public Product(
            String name, String description, int price,
            int rebate, int quantity, String status)
            throws Exception {

        setName(name);
        setDescription(description);
        setPrice(price);
        setRebate(rebate);
        setQuantity(quantity);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {

        if (name == null || name.isEmpty())
            throw new Exception("Name cannot be null or empty!");

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws Exception {

        if (price <= 0)
            throw new Exception("Price cannot be less than or equal to 0!");

        this.price = price;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) throws Exception {

        if (rebate < 0)
            throw new Exception("Rebate cannot be less than 0!");

        this.rebate = rebate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws Exception {

        if (quantity < 0)
            throw new Exception("Quantity cannot be less than 0!");

        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFinalPrice() {

        return (int) (getPrice() * (1 - (getRebate() / 100f)));
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {

        return "Product{" +
                "\n\tid=" + id +
                ",\n\tname='" + name + '\'' +
                ",\n\tdescription='" + description + '\'' +
                ",\n\tprice=" + price +
                ",\n\trebate=" + rebate +
                ",\n\tfinalPrice=" + getFinalPrice() +
                ",\n\tquantity=" + quantity +
                ",\n\tstatus='" + status + '\'' +
                "\n}";
    }
}
