package view;

import utils.ReadAndWrite;
import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.BookService.findBookByID;
import static service.BookService.findListBook;
import static service.BorrowingAndBillBookService.*;

public class UserView {
    public static final String path4 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\billandborrowing.csv";
    public static final String path3 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\book.csv";
    private static BookView bookView;
    private static ReaderView readerView;
    private static BorrowingAndBillBookView borrowingView;
    private static BorrowingAndBillBookService borrowingAndBillBookService;
    private static BookService bookService;
    private static List<BorrowingAndBillBook> borrowingAndBillBooks;
    private static List<Book> books;
    private static UserService userService;
    private static BorrowingAndBillBookView borrowingAndBillBookView;

    static {
        bookService = new BookService();
        books = ReadAndWrite.readFile(path3, Book.class);
        bookView = new BookView();
        readerView = new ReaderView();
        borrowingView = new BorrowingAndBillBookView();
        borrowingAndBillBookService = new BorrowingAndBillBookService();
        borrowingAndBillBooks = ReadAndWrite.readFile(path4, BorrowingAndBillBook.class);
        userService = new UserService();
        borrowingAndBillBookView = new BorrowingAndBillBookView();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void adminView() {
        System.out.println("Đăng nhập thành công!");
        boolean actionAdmin = false;
        while (!actionAdmin) {
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║                         Menu                        ║");
            System.out.println("╠═════════════════════════════════════════════════════║");
            System.out.println("║ [1] Quản lý sách trong thư viện                     ║");
            System.out.println("║ [2] Quản lý đọc giả                                 ║");
            System.out.println("║ [3] Quản lý oder                                    ║");
            System.out.println("║ [4] Quản lý hóa đơn                                 ║");
            System.out.println("║ [0] Exit                                            ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bookView.bookview();
                    break;
                case 2:
                    readerView.readerView();
                    break;
                case 3:
                    borrowingView.BorrowingBookView();
                    break;
                case 4:
                    borrowingAndBillBookView.BillBooKView();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    actionAdmin = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public void userView(long ID) {
        System.out.println("Đăng nhập thành công!");
        boolean actionUser = false;
        while (!actionUser) {
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║                         Menu                        ║");
            System.out.println("╠═════════════════════════════════════════════════════║");
            System.out.println("║ [1] Hiển thị toàn bộ sách trong thư viện            ║");
            System.out.println("║ [2] Tìm kiếm sách trong thư viện                    ║");
            System.out.println("║ [3] Mượn sách                                       ║");
            System.out.println("║ [4] Quản lý oder                                    ║");
            System.out.println("║ [5] Xem bill của bạn                                ║");
            System.out.println("║ [0] Exit                                            ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bookService.showBook(findListBook());
                    break;
                case 2:
                    BookService.findBook();
                    break;
                case 3:
                    long IDBorrowingBook = BorrowingAndBillBookService.borrowingBook(ID);
                    System.out.println("╔═══════════════════════════════╗");
                    System.out.println("║ Bạn có muốn thanh toán không? ║");
                    System.out.println("╠═══════════════════════════════║");
                    System.out.println("║ [Y] Đồng ý                    ║");
                    System.out.println("║ [N] Không đồng ý              ║");
                    System.out.println("╚═══════════════════════════════╝");
                    String choice1 = scanner.nextLine();
                    switch (choice1.toUpperCase()) {
                        case "Y":
                            borrowingAndBillBooks = ReadAndWrite.readFile(path4, BorrowingAndBillBook.class);
                            for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                                if (borrowingAndBillBook.getIDBrrowing() == IDBorrowingBook) {
                                    borrowingAndBillBook.setePay(EPay.PAID);
                                    ReadAndWrite.writeFile(path4, borrowingAndBillBooks);
                                }
                            }
                            List<BorrowingAndBillBook> borrowingAndBillBookList = findBillOrBorrowByID(IDBorrowingBook);
                            for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBookList) {
                                findBookByID(borrowingAndBillBook.getIDBook()).setQuantity(findBookByID(borrowingAndBillBook.getIDBook()).getQuantity() - findBillByIDBookAndIDBorrow(borrowingAndBillBook.getIDBook(), IDBorrowingBook).getQuantity());
                                ReadAndWrite.writeFile(path3,BookService.books);
                            }
                            System.out.println("Đây là thông tin hóa đơn của bạn:");
                            borrowingAndBillBookService.showBorrowingBook(borrowingAndBillBookService.findBillOrBorrowByID(IDBorrowingBook));
                            break;
                        case "N":
                            actionUser = false;
                            break;
                        default:
                            System.out.println("Lựa chọn của bạn không có, vui lòng nhập Y/N!");
                    }
                    break;
                case 4:
                    borrowingView.BorrowingBookView();
                    break;
                case 5:
                    System.out.println("Đây là danh sách hóa đơn của bạn:");
                    List<BorrowingAndBillBook> borrowingAndBillBookList = new ArrayList<>();
                    borrowingAndBillBookList.clear();
                    for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                        if (borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                            borrowingAndBillBookList.add(borrowingAndBillBook);
                        }
                    }
                    borrowingAndBillBookService.showBorrowingBook(borrowingAndBillBookList);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    actionUser = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
}

