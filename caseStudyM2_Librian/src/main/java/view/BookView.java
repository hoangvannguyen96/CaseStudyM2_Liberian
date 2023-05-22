package view;

import service.BookService;
import utils.CheckInput;
import utils.ReadAndWrite;
import model.Book;

import java.util.List;
import java.util.Scanner;

import static service.BookService.*;

public class BookView {
    public static final String path3 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\book.csv";

    public BookView() {

    }

    public static List<Book> books;

    static {
        books = ReadAndWrite.readFile(path3, Book.class);
    }

    public static Scanner scanner = new Scanner(System.in);

    public void bookview() {
        boolean check = false;
        while (!check) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                   Menu                  ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ [0] Thống kê sách trong thư viện        ║");
            System.out.println("║ [1] Thêm sách vào thư viện              ║");
            System.out.println("║ [2] Sửa sách trong thư viện             ║");
            System.out.println("║ [3] Xóa sách trong thư viện             ║");
            System.out.println("║ [4] Tìm kiếm sách theo ID               ║");
            System.out.println("║ [5] Tìm kiếm sách theo tùy chọn         ║");
            System.out.println("║ [6] Exit                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    showBook(ReadAndWrite.readFile(path3, Book.class));
                    break;
                case 1:
                    showBook(addBook());
                    break;
                case 2:
                    showBook(fixBook());
                    break;
                case 3:
                    showBook(clearBook());
                    break;
                case 4:
                    boolean actionFindByID = false;
                    while (!actionFindByID) {
                        System.out.println("Nhập ID sách cần tìm kiếm?");
                        long ID = Long.parseLong(CheckInput.checkIDFind());
                        Book book = findBookByID(ID);
                        if (book != null) {
                            System.out.println("Đây là sách bạn đang tìm");
                            System.out.println(book.toString() + "\n");
                            actionFindByID = true;
                            break;
                        } else {
                            System.out.println("ID bạn nhập không có trong thư viện, vui lòng thử lại với ID khác!");
                        }
                    }
                    break;
                case 5:
                    findBook();
                    break;
                case 6:
                    check = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
}
