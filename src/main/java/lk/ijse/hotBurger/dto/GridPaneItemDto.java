package lk.ijse.hotBurger.dto;

public class GridPaneItemDto {

    private String name;
    private double price;
    private String imgSrc;

    public GridPaneItemDto() {
    }

    public GridPaneItemDto(String name, double price, String imgSrc) {
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return "GridPaneItemDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
