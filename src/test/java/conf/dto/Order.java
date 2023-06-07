package conf.dto;

public class Order {

    private Integer bookId;

    private String bookIdAsString;
    private String customerName;

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void  setBookIdAsString(String bookIdAsString) {
        this.bookIdAsString = bookIdAsString;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}