package model;

public class Book implements IModel {
    private long IDBook;
    private ECategory eCategory;
    private EPosition ePosition;
    private String nameBook;
    private String description;
    private int numberPage;
    private String author;
    private String language;
    private String publishingCompany;
    private float price;
    private int quantity;
    public Book(){}
    public Book(long IDBook, ECategory eCategory, EPosition ePosition, String nameBook, String description, int numberPage, String author, String language, String publishingCompany, float price, int quantity) {
        this.IDBook = IDBook;
        this.eCategory = eCategory;
        this.ePosition = ePosition;
        this.nameBook = nameBook;
        this.description = description;
        this.numberPage = numberPage;
        this.author = author;
        this.language = language;
        this.publishingCompany = publishingCompany;
        this.price = price;
        this.quantity = quantity;
    }

    public long getIDBook(){
        return IDBook;
    }

    public void setIDBook(long IDBook) {
        this.IDBook = IDBook;
    }

    public ECategory geteCategory() {
        return eCategory;
    }

    public void seteCategory(ECategory eCategory) {
        this.eCategory = eCategory;
    }

    public EPosition getePosition() {
        return ePosition;
    }

    public void setePosition(EPosition ePosition) {
        this.ePosition = ePosition;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return getIDBook() + "," + geteCategory() + "," + getePosition() + "," + getNameBook() + "," + getDescription()
                + "," + getNumberPage() + "," + getAuthor() + "," + getLanguage() + "," + getPublishingCompany() + "," +
                getPrice() + "," + getQuantity();
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        IDBook = Long.parseLong(items[0]);
        eCategory = ECategory.getECategoryByName(items[1]);
        ePosition = EPosition.getEPostionByName(items[2]);
        nameBook=items[3];
        description=items[4];
        numberPage = Integer.parseInt(items[5]);
        author=items[6];
        language=items[7];
        publishingCompany=items[8];
        price = Float.parseFloat(items[9]);
        quantity = Integer.parseInt(items[10]);
    }
}
