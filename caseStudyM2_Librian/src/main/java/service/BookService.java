package service;

import utils.CheckInput;
import utils.ReadAndWrite;
import model.Book;
import model.ECategory;
import model.EPosition;
import view.BookView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private static final String path3 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\book.csv";
    private static BookView bookView;

    public static List<Book> books;

    static {
        books = ReadAndWrite.readFile(path3, Book.class);
        bookView = new BookView();
    }

    public static Scanner scanner = new Scanner(System.in);

    public static List<Book> addBook() {
        System.out.println("Chọn thể loại?");
        for (ECategory eCategory : ECategory.values()) {
            System.out.printf("Chon %-3s %-10s\n", eCategory.getId(), eCategory.getName());
        }
        int idCategory = Integer.parseInt(scanner.nextLine());
        ECategory eCategory = ECategory.getECategoryById(idCategory);
        System.out.println("Chọn kệ sách muốn chứa?");
        for (EPosition ePosition : EPosition.values()) {
            System.out.printf("Chon %-3s %-10s\n", ePosition.getId(), ePosition.getName());
        }
        int idEposition = Integer.parseInt(scanner.nextLine());
        EPosition ePosition = EPosition.getEPostionById(idEposition);
        System.out.println("Nhập tên sách?");
        String nameBook = scanner.nextLine();
        System.out.println("Nhập mô tả?");
        String description = scanner.nextLine();
        System.out.println("Nhập số trang sách?");
        int numberPage = Integer.parseInt(CheckInput.checkNumber());
        System.out.println("Nhập tên tác giả?");
        String author = scanner.nextLine();
        System.out.println("Nhập ngôn ngữ?");
        String language = scanner.nextLine();
        System.out.println("Nhập nhà xuất bản?");
        String publishingCompany = scanner.nextLine();
        System.out.println("Nhập giá sách?");
        float price = Float.parseFloat(CheckInput.checkNumber());
        System.out.println("Nhập số lượng sách");
        int quantity = Integer.parseInt(CheckInput.checkNumber());
        Book book = new Book(System.currentTimeMillis() / 1000, eCategory, ePosition, nameBook, description, numberPage, author, language, publishingCompany, price, quantity);
        books.add(book);
        ReadAndWrite.writeFile(path3, books);
        return books;
    }

    public static List<Book> fixBook() {
        showBook(findListBook());
        System.out.println("Nhập ID sách cần sửa?");
        long ID = Long.parseLong(CheckInput.checkIDFind());
        Book book = findBookByID(ID);
        boolean actionFix = false;
        while (!actionFix) {
            if (book == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với Id khác!");
                long ID1 = Long.parseLong(scanner.nextLine());
                book = findBookByID(ID1);
                ID = ID1;
            } else {
                System.out.println("Đây là thông tin về sách bạn muốn sửa");
                System.out.println(book.toString() + "\n");
                boolean check1 = false;
                while (!check1) {
                    System.out.println("Bạn muốn sửa gì?");
                    System.out.println("╔══════════════════════════════════╗");
                    System.out.println("║               Menu               ║");
                    System.out.println("╠══════════════════════════════════║");
                    System.out.println("║ [1] Sửa thể loại                 ║");
                    System.out.println("║ [2] Sửa vị trí đặt sách          ║");
                    System.out.println("║ [3] Sửa tên sách                 ║");
                    System.out.println("║ [4] Sửa mô tả                    ║");
                    System.out.println("║ [5] Sửa số trang sách            ║");
                    System.out.println("║ [6] Sửa tên tác giả              ║");
                    System.out.println("║ [7] Sửa ngôn ngữ viết sách       ║");
                    System.out.println("║ [8] Sửa nhà xuất bản             ║");
                    System.out.println("║ [9] Sửa giá sách                 ║");
                    System.out.println("║ [10] Sửa số lượng sách           ║");
                    System.out.println("║ [0] Exit                         ║");
                    System.out.println("╚══════════════════════════════════╝");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Chọn thể loại muốn sửa?");
                            for (ECategory eCategory : ECategory.values()) {
                                System.out.printf("Chọn %-10s %-10s \n", eCategory.getId(), eCategory.getName());
                            }
                            int idCategory = Integer.parseInt(scanner.nextLine());
                            ECategory eCategory = ECategory.getECategoryById(idCategory);
                            book.seteCategory(eCategory);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 2:
                            System.out.println("Chọn kệ sách muốn chứa?");
                            for (EPosition ePosition : EPosition.values()) {
                                System.out.printf("Chon %-10s %-10s \n", ePosition.getId(), ePosition.getName());
                            }
                            int idEposition = Integer.parseInt(scanner.nextLine());
                            EPosition ePosition = EPosition.getEPostionById(idEposition);
                            book.setePosition(ePosition);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 3:
                            System.out.println("Nhập tên sách muốn sửa?");
                            String nameBook = scanner.nextLine();
                            book.setNameBook(nameBook);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 4:
                            System.out.println("Nhập mô tả muốn sửa?");
                            String description = scanner.nextLine();
                            book.setDescription(description);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 5:
                            System.out.println("Nhập số trang sách?");
                            int numberPage = Integer.parseInt(CheckInput.checkNumber());
                            book.setNumberPage(numberPage);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 6:
                            System.out.println("Nhập tên tác giả?");
                            String author = scanner.nextLine();
                            book.setAuthor(author);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 7:
                            System.out.println("Nhập ngôn ngữ?");
                            String language = scanner.nextLine();
                            book.setLanguage(language);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 8:
                            System.out.println("Nhập nhà xuất bản?");
                            String publishingCompany = scanner.nextLine();
                            book.setPublishingCompany(publishingCompany);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 9:
                            System.out.println("Nhập giá sách?");
                            float price = Float.parseFloat(CheckInput.checkNumber());
                            book.setPrice(price);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 10:
                            System.out.println("Nhập số lượng muốn sửa");
                            int quantity = Integer.parseInt(CheckInput.checkNumber());
                            book.setQuantity(quantity);
                            ReadAndWrite.writeFile(path3, books);
                            System.out.println(book.toString() + "\n");
                            break;
                        case 0:
                            check1 = true;
                            bookView.bookview();
                            break;
                        default:
                            System.out.println("Lựa chọn của bạn không có trong danh sách, vui lòng chọn lại!");
                    }
                }
                actionFix = true;
            }
        }
        return books;
    }

    public static List<Book> clearBook() {
        showBook(findListBook());
        System.out.println("Nhập ID sách cần xóa?");
        long ID = Long.parseLong(CheckInput.checkIDFind());
        Book book = findBookByID(ID);
        boolean check = false;
        while (!check) {
            if (book == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
                long ID1 = Long.parseLong(scanner.nextLine());
                book = findBookByID(ID1);
                ID = ID1;
            } else {
                System.out.println("Đây là sách bạn muốn xóa");
                System.out.println(book.toString() + "\n");
                System.out.println("Đây là danh sách sau khi bạn xóa");
                books.remove(book);
                ReadAndWrite.writeFile(path3, books);
                check = true;
            }
        }
        return books;
    }

    public static void findBook() {
        List<Book> books1 = new ArrayList<>();
        boolean check = false;
        while (!check) {
            System.out.println("Bạn muốn tìm kiếm theo thông tin gì?");
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║               Menu               ║");
            System.out.println("╠══════════════════════════════════║");
            System.out.println("║ [1] Tìm kiếm theo tên tác giả    ║");
            System.out.println("║ [2] Tìm kiếm theo tên sách       ║");
            System.out.println("║ [3] Tìm kiếm theo ID sách        ║");
            System.out.println("║ [0] Exit                         ║");
            System.out.println("╚══════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean actionFindAuthor = false;
                    while (!actionFindAuthor) {
                        System.out.println("Nhập tên tác giả bạn muốn tìm?");
                        String author = scanner.nextLine();
                        books1.clear();
                        for (Book book1 : books) {
                            if (book1.getAuthor().equals(author) && book1.getQuantity() != 0) {
                                books1.add(book1);
                                actionFindAuthor = true;
                            }
                        }
                        if (books1.isEmpty()) {
                            System.out.println("Tên tác giả bạn đang tìm không có trong thư viện, hãy thử lại với tên khác! ");
                        } else {
                            System.out.println("Đây là thông tin đọc giả bạn muốn tìm.");
                            showBook(books1);
                        }
                    }
                    break;
                case 2:
                    boolean actionFindNameBook = false;
                    while (!actionFindNameBook) {
                        System.out.println("Nhập tên sách bạn muốn tìm?");
                        String nameBook = scanner.nextLine();
                        books1.clear();
                        for (Book book1 : books) {
                            if (book1.getNameBook().equals(nameBook) && book1.getQuantity() != 0) {
                                books1.add(book1);
                                actionFindNameBook = true;
                            }
                        }
                        if (books1.isEmpty()) {
                            System.out.println("Tên sách bạn đang tìm không có trong thư viện, hãy thử lại với tên khác! ");
                        } else {
                            System.out.println("Đây là thông tin sách bạn muốn tìm.");
                            showBook(books1);
                        }
                    }
                    break;
                case 3:
                    boolean actionFindIDBook = false;
                    while (!actionFindIDBook) {
                        System.out.println("Nhập ID sách bạn muốn tìm?");
                        long IDBook = Long.parseLong(CheckInput.checkIDFind());
                        books1.clear();
                        for (Book book1 : books) {
                            if (book1.getIDBook() == IDBook && book1.getQuantity() != 0) {
                                books1.add(book1);
                                actionFindIDBook = true;
                            }
                        }
                        if (books1.isEmpty()) {
                            System.out.println("Tên sách bạn đang tìm không có trong thư viện, hãy thử lại với tên khác! ");
                        } else {
                            System.out.println("Đây là thông tin sách bạn muốn tìm.");
                            showBook(books1);
                        }
                    }
                    break;
                case 0:
                    check = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public static Book findBookByID(long ID) {
        for (Book book : BookService.books) {
            if (book.getIDBook() == ID && book.getQuantity() != 0) {
                return book;
            }
        }
        return null;
    }
    public static void showBook(List<Book> books) {
        System.out.printf("%-10s %-10s %-10s %-15s %-20s %-10s %-15s %-10s %-15s %-10s %-5s\n",
                "ID", "Thể loại", "Kệ sách", "Tên sách", "Mô tả nội dung", "Số trang", "Tên tác giả",
                "Ngôn ngữ", "Nhà xuất bản", "Giá sách", "Số lượng");
        for (Book book : books) {
            System.out.printf("%-10s %-10s %-10s %-15s %-20s %-10s %-15s %-10s %-15s %-10s %-5s\n",
                    book.getIDBook(), book.geteCategory(), book.getePosition(), book.getNameBook(),
                    book.getDescription(), book.getNumberPage(), book.getAuthor(), book.getLanguage(),
                    book.getPublishingCompany(), book.getPrice(), book.getQuantity());
        }
    }
    public static List<Book> findListBook(){
        books = ReadAndWrite.readFile(path3, Book.class);
        List<Book> books1=new ArrayList<>();
        for(Book book:books){
            if(book.getQuantity()!=0){
                books1.add(book);
            }
        }
        return books1;
    }
}


