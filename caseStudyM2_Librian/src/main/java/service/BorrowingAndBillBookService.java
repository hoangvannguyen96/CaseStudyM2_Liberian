package service;

import utils.CheckInput;
import utils.DateUtils;
import utils.ReadAndWrite;
import model.*;
import model.Reader;
import view.BookView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static service.BookService.*;
import static service.StaffService.readStaff;

public class BorrowingAndBillBookService {
    private static final String path4 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\billandborrowing.csv";
    private static final String path3 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\book.csv";
    private static BookView bookView;
    private static List<Staff> staffs;
    private static List<BorrowingAndBillBook> borrowingAndBillBooks;
    private static List<Book> books;
    private static Reader reader;
    private static BorrowingAndBillBookService borrowingAndBillBookService;

    static {
        staffs = readStaff();
        borrowingAndBillBooks = ReadAndWrite.readFile(path4, BorrowingAndBillBook.class);
        bookView = new BookView();
        books = ReadAndWrite.readFile(path3, Book.class);
        reader = new Reader();
        borrowingAndBillBookService=new BorrowingAndBillBookService();
    }

    public static Scanner scanner = new Scanner(System.in);

    public static List<BorrowingAndBillBook> fixBorrowingBook() {
        System.out.println("Dưới đây là danh sách oder");
        showBorrowingBook(findListOder());
        boolean actionFix = false;
        while (!actionFix) {
            System.out.println("Nhập ID oder cần sửa?");
            long ID = Long.parseLong(CheckInput.checkIDFind());
            List<BorrowingAndBillBook> borrowingAndBillBook = findBorrowingBookByID(ID);
            if (borrowingAndBillBook.isEmpty()) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
                ID = Long.parseLong(scanner.nextLine());
                borrowingAndBillBook = findBorrowingBookByID(ID);
                actionFix = false;
            } else {
                System.out.println("Đây là thông tin về oder bạn muốn sửa");
                showBorrowingBook(borrowingAndBillBook);
                boolean actionFixBorrowing = false;
                while (!actionFixBorrowing) {
                    System.out.println("Bạn muốn sửa thông tin gì?");
                    System.out.println("╔══════════════════════════════════╗");
                    System.out.println("║               Menu               ║");
                    System.out.println("╠══════════════════════════════════║");
                    System.out.println("║ [1] Sửa số lượng sách muốn mượn  ║");
                    System.out.println("║ [2] Sửa ngày trả sách            ║");
                    System.out.println("║ [0] Exit                         ║");
                    System.out.println("╚══════════════════════════════════╝");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            boolean acionFixQuantityBorrowingBook = false;
                            while (!acionFixQuantityBorrowingBook) {
                                System.out.println("Nhập ID sách muốn sửa?");
                                long IDBook = Long.parseLong(CheckInput.checkIDFind());
                                findBookByID(IDBook).setQuantity(findBorrowingByIDBookAndIDBorrow(IDBook, ID).getQuantity() + findBookByID(IDBook).getQuantity());
                                ReadAndWrite.writeFile(path3, BookService.books);
                                System.out.println("Nhập lại số lượng sách muốn mượn?");
                                int quantity = Integer.parseInt(CheckInput.checkNumber());
                                if (findBookByID(IDBook).getQuantity() >= quantity) {
                                    findBorrowingByIDBookAndIDBorrow(IDBook, ID).setQuantity(quantity);
                                    ReadAndWrite.writeFile(path4, borrowingAndBillBooks);
                                    System.out.println("Đây là thông tin oder của bạn sau khi sửa số lượng:");
                                    System.out.println(findBorrowingByIDBookAndIDBorrow(IDBook, ID).toString() + "\n");
                                    acionFixQuantityBorrowingBook = true;
                                } else {
                                    System.out.println("Số lượng bạn nhập vượt quá số lượng sách đang có, mời bạn nhập lại!");
                                    acionFixQuantityBorrowingBook = false;
                                }
                            }
                            break;
                        case 2:
                            boolean actionFixPayDate = false;
                            while (!actionFixPayDate) {
                                System.out.println("Nhập ID sách muốn thay đổi ngày trả?");
                                long IDBook = Long.parseLong(CheckInput.checkIDFind());
                                System.out.println("Nhập ngày muốn trả?");
                                String payDate = CheckInput.checkDate();
                                if (DateUtils.parse(payDate).toInstant().isAfter(findBorrowingByIDBookAndIDBorrow(IDBook, ID).getBorrowDate().toInstant()) || DateUtils.parse(payDate).equals(findBorrowingByIDBookAndIDBorrow(IDBook, ID).getBorrowDate())) {
                                    findBorrowingByIDBookAndIDBorrow(IDBook, ID).setPayDate(DateUtils.parse(payDate));
                                    ReadAndWrite.writeFile(path4, borrowingAndBillBooks);
                                    System.out.println("Đây là thông tin oder của bạn sau khi sửa ngày trả ");
                                    System.out.println(findBorrowingByIDBookAndIDBorrow(IDBook, ID).toString() + "\n");
                                    actionFixPayDate = true;
                                } else {
                                    System.out.println("Ngày nhập không hợp lệ, ngày nhập vào phải lớn hơn hoặc tổi thiểu bằng ngày hôm nay, vui lòng nhập lại!");
                                    actionFixPayDate = false;
                                }
                            }
                            break;
                        case 0:
                            actionFixBorrowing = true;
                            actionFix = true;
                            break;
                        default:
                            System.out.println("Lựa chọn của bạn không có trong danh mục, vui lòng nhập lại!");
                    }
                }
            }
        }
        return borrowingAndBillBooks;
    }

    public static List<BorrowingAndBillBook> findListOder() {
        borrowingAndBillBooks = ReadAndWrite.readFile(path4, BorrowingAndBillBook.class);
        List<BorrowingAndBillBook> borrowingAndBillBookList = new ArrayList<>();
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getePay().equals(EPay.UNPAID)) {
                borrowingAndBillBookList.add(borrowingAndBillBook);
            }
        }
        return borrowingAndBillBookList;
    }

    public static void  clearBorrowingBook() {
        showBorrowingBook(findListOder());
        System.out.println("Nhập ID oder cần xóa?");
        long ID = Long.parseLong(CheckInput.checkIDFind());
        List<BorrowingAndBillBook> borrowingAndBillBook = findBorrowingBookByID(ID);
        boolean check = false;
        while (!check) {
            if (borrowingAndBillBook.isEmpty()) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
                ID = Long.parseLong(scanner.nextLine());
                borrowingAndBillBook = findBorrowingBookByID(ID);
                check = false;
            } else {
                System.out.println("Đây là oder bạn muốn xóa");
                showBorrowingBook(borrowingAndBillBook);
                System.out.println("Đây là danh sách oder sau khi bạn xóa");
                borrowingAndBillBooks.removeAll(borrowingAndBillBook);
                ReadAndWrite.writeFile(path4, borrowingAndBillBooks);
                check = true;
            }
        }
    }

    public static List<BorrowingAndBillBook> findBorrowingBookByID(long ID) {
        List<BorrowingAndBillBook> borrowingAndBillBookListID = new ArrayList<>();
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getIDBrrowing() == ID && borrowingAndBillBook.getePay().equals(EPay.UNPAID)) {
                borrowingAndBillBookListID.add(borrowingAndBillBook);
            }
        }
        return borrowingAndBillBookListID;
    }

    public static BorrowingAndBillBook findBorrowingByIDBookAndIDBorrow(long IDBook, long IDBorrow) {
        borrowingAndBillBooks=ReadAndWrite.readFile(path4,BorrowingAndBillBook.class);
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getIDBook() == IDBook && borrowingAndBillBook.getIDBrrowing() == IDBorrow && borrowingAndBillBook.getePay().equals(EPay.UNPAID)) {
                return borrowingAndBillBook;
            }
        }
        return null;
    }

    public static BorrowingAndBillBook findBillByIDBookAndIDBorrow(long IDBook, long IDBorrow) {
        borrowingAndBillBooks=ReadAndWrite.readFile(path4,BorrowingAndBillBook.class);
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getIDBook() == IDBook && borrowingAndBillBook.getIDBrrowing() == IDBorrow && borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                return borrowingAndBillBook;
            }
        }
        return null;
    }

    public static List<BorrowingAndBillBook> findBillOrBorrowByID(long ID) {
        borrowingAndBillBooks=ReadAndWrite.readFile(path4,BorrowingAndBillBook.class);
        List<BorrowingAndBillBook> borrowingAndBillBooksList = new ArrayList<>();
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getIDBrrowing() == ID) {
                borrowingAndBillBooksList.add(borrowingAndBillBook);
            }
        }
        return borrowingAndBillBooksList;
    }

    public static void findBorrowingBook() {
        List<BorrowingAndBillBook> borrowingAndBillBook1s = new ArrayList<>();
        boolean check = false;
        while (!check) {
            System.out.println("Bạn muốn tìm kiếm oder theo tiêu chí gì?");
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║               Menu               ║");
            System.out.println("╠══════════════════════════════════║");
            System.out.println("║ [1] Tìm kiếm theo ID oder        ║");
            System.out.println("║ [2] Tìm kiếm theo ngày trả sách  ║");
            System.out.println("║ [0] Exit                         ║");
            System.out.println("╚══════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean actionFindID = false;
                    while (!actionFindID) {
                        System.out.println("Nhập ID oder bạn muốn tìm?");
                        long ID = Long.parseLong(CheckInput.checkIDFind());
                        borrowingAndBillBook1s.clear();
                        borrowingAndBillBook1s.addAll(findBorrowingBookByID(ID));
                        if (borrowingAndBillBook1s.isEmpty()) {
                            System.out.println("ID oder bạn đang tìm không có trong thư viện, hãy thử lại với ID khác! ");
                        } else {
                            showBorrowingBook(borrowingAndBillBook1s);
                        }
                    }
                    break;
                case 2:
                    boolean actionFindPayDate = false;
                    while (!actionFindPayDate) {
                        System.out.println("Nhập ngày trả sách bạn muốn tìm? dd/MM/yyyy");
                        String payDate = CheckInput.checkDate();
                        borrowingAndBillBook1s.clear();
                        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                            if (borrowingAndBillBook.getPayDate().equals(DateUtils.parse(payDate)) && borrowingAndBillBook.getePay().equals(EPay.UNPAID)) {
                                borrowingAndBillBook1s.add(borrowingAndBillBook);
                                actionFindPayDate = true;
                            }
                        }
                        if (borrowingAndBillBook1s.isEmpty()) {
                            System.out.println("Ngày trả sách bạn đang tìm không có trong thư viện, hãy thử lại với ngày khác! ");
                        } else {
                            showBorrowingBook(borrowingAndBillBook1s);
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

    public static void showBorrowingBook(List<BorrowingAndBillBook> borrowingAndBillBooks) {
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-10s %-15s %-15s %-10s %-10s\n", "ID", "ID đọc giả", "ID NV",
                "ID sách", "Tên sách", "Số lượng", "Ngày mượn", "Ngày trả", "Tiền", "Trạng thái");
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            System.out.printf("%-10s %-15s %-15s %-15s %-15s %-10s %-15s %-15s %-10s %-10s\n", borrowingAndBillBook.getIDBrrowing(),
                    borrowingAndBillBook.getIDReader(), borrowingAndBillBook.getIDSatff(), borrowingAndBillBook.getIDBook(), borrowingAndBillBook.getNameBook(),
                    borrowingAndBillBook.getQuantity(), DateUtils.format(borrowingAndBillBook.getBorrowDate()), DateUtils.format(borrowingAndBillBook.getPayDate()),
                    borrowingAndBillBook.getDeposit(), borrowingAndBillBook.getePay());
        }
    }

    public static long borrowingBook(long IDReader) {
        showBook(findListBook());
        BookService.findBook();
        List<BorrowingAndBillBook> tempBorrowingAndBillBooks = new ArrayList<>();
        long IDBorrowingBook = System.currentTimeMillis() / 1000;
        boolean actionborrowingBook = false;
        while (!actionborrowingBook) {
            System.out.println("Bạn muốn chọn sách gì, mời bạn nhập ID sách?");
            long ID = Long.parseLong(CheckInput.checkIDFind());
            Book book = findBookByID(ID);
            if (book == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
                ID = Long.parseLong(scanner.nextLine());
                actionborrowingBook = false;
            } else {
                boolean actionQuantity = false;
                while (!actionQuantity) {
                    System.out.println("Nhập số lượng sách muốn mượn?");
                    int quantity = Integer.parseInt(CheckInput.checkNumber());
                    if (book.getQuantity() >= quantity) {
                        boolean actionDate = false;
                        while (!actionDate) {
                            System.out.println("Nhập ngày trả sách? dd/MM/yyyy");
                            String payDate = CheckInput.checkDate();
                            if (DateUtils.parse(payDate).toInstant().isAfter(new Date().toInstant()) || DateUtils.parse(payDate).equals(new Date())) {
                                BorrowingAndBillBook borrowingAndBillBook = new BorrowingAndBillBook(IDBorrowingBook, IDReader, staffs.get(0).getIDStaff(), ID, book.getNameBook(), quantity, new Date(), DateUtils.parse(payDate), book.getPrice() * quantity, EPay.UNPAID);
                                tempBorrowingAndBillBooks.add(borrowingAndBillBook);
                                actionDate = true;
                                actionQuantity = true;
                            } else {
                                System.out.println("Ngày nhập không hợp lệ, ngày trả sách phải lớn hơn hoặc tối thiểu bằng ngày hôm nay, vui lòng nhập lại!");
                                actionDate = false;
                            }
                        }
                    } else {
                        System.out.println("Số lượng bạn nhập vượt quá số lượng sách đang có, mời bạn nhập lại!");
                        actionQuantity = false;
                        continue;
                    }
                    System.out.println("Bạn có muốn tiếp tục không?");
                    System.out.println("Nhập Y: Đồng ý");
                    System.out.println("Nhập N: Exit");
                    String choice = scanner.nextLine();
                    switch (choice.toUpperCase()) {
                        case "Y":
                            showBook(findListBook());
                            BookService.findBook();
                            ID = borrowingAndBillBookService.notificationWhenDuplicateID(ID);
                            book = findBookByID(ID);
                            actionQuantity=false;
                            break;
                        case "N":
                            actionQuantity = true;
                            actionborrowingBook=true;
                            break;
                        default:
                            System.out.println("Lựa chọn của bạn không có, vui lòng nhập Y/N!");
                    }
                }
            }
        }
        borrowingAndBillBooks.addAll(tempBorrowingAndBillBooks);
        ReadAndWrite.writeFile(path4, borrowingAndBillBooks);
        return IDBorrowingBook;
    }

    public  long notificationWhenDuplicateID(long ID)  {
        boolean actionBorrowing= false;
        while (!actionBorrowing) {
            System.out.println("Bạn muốn chọn sách gì, mời bạn nhập ID sách?");
            long IDBook = Long.parseLong(CheckInput.checkIDFind());
            Book book = findBookByID(IDBook);
            if (book == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
            }else if(IDBook==ID){
                System.out.println("Bạn đã chọn loại sách này trước đó rồi, mời bạn lựa chọn sách khác!");
            }else {
                return IDBook;
            }

        }
        return 0;
    }

    public void findBill() {
        List<BorrowingAndBillBook> billBookList = new ArrayList<>();
        boolean actionFindBill = false;
        while (!actionFindBill) {
            System.out.println("Bạn muốn tìm thông tin hóa đơn theo tiêu chí nào?");
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║                     Menu                   ║");
            System.out.println("╠════════════════════════════════════════════║");
            System.out.println("║ [1] Tìm kiếm theo ID hóa đơn nhập vào      ║");
            System.out.println("║ [2] Tìm kiếm theo ngày bắt đầu mượn sách   ║");
            System.out.println("║ [0] Exit                                   ║");
            System.out.println("╚════════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean actionFindBillID = false;
                    while (!actionFindBillID) {
                        System.out.println("Nhập ID hóa đơn bạn muốn tìm?");
                        long IDBill = Long.parseLong(CheckInput.checkIDFind());
                        billBookList.clear();
                        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                            if (borrowingAndBillBook.getIDBrrowing() == IDBill && borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                                billBookList.add(borrowingAndBillBook);
                            }
                        }
                        if (billBookList.isEmpty()) {
                            System.out.println("ID hóa đơn bạn đang tìm không có trong danh sách hóa đơn, hãy thử lại với ID khác! ");
                            actionFindBillID = false;
                        } else {
                            showBorrowingBook(billBookList);
                            actionFindBillID = true;
                        }
                    }
                    break;
                case 2:
                    boolean actionFindBillDate = false;
                    while (!actionFindBillDate) {
                        System.out.println("Nhập ngày bắt đầu mượn sách của bạn?");
                        String borrowingDate = CheckInput.checkDate();
                        billBookList.clear();
                        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                            if (borrowingAndBillBook.getBorrowDate().equals(DateUtils.parse(borrowingDate)) && borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                                billBookList.add(borrowingAndBillBook);
                            }
                        }
                        if (billBookList.isEmpty()) {
                            System.out.println("Ngày bạn đang tìm không có, hãy thử lại với ngày khác! ");
                            actionFindBillDate = false;
                        } else {
                            showBorrowingBook(billBookList);
                            actionFindBillDate = true;
                        }
                    }
                    break;
                case 0:
                    actionFindBill = true;
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public void seeRevenue() {
        boolean actionSeeRevenue = false;
        while (!actionSeeRevenue) {
            System.out.println("Bạn muốn xem doanh thu theo tiêu chí nào?");
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║               Menu               ║");
            System.out.println("╠══════════════════════════════════║");
            System.out.println("║ [1] Xem doanh thu trong ngày     ║");
            System.out.println("║ [2] Xem doanh thu theo tháng     ║");
            System.out.println("║ [3] Xem tổng doanh thu           ║");
            System.out.println("║ [0] Exit                         ║");
            System.out.println("╚══════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    float total = 0;
                    for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                        if (DateUtils.format(borrowingAndBillBook.getBorrowDate()).equals(DateUtils.format(new Date())) && borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                            total += borrowingAndBillBook.getDeposit();
                        }
                    }
                    System.out.printf("Doanh thu hôm nay của thư viện là: %.1f VND\n", total);
                    break;
                case 2:
                    System.out.println("Nhập tháng/năm muốn xem doanh thu?");
                    String monthYear1 = CheckInput.checkDateMM_yyyy();
                    Date date = DateUtils.parseMM_yyyy(monthYear1);
                    String monthYear = DateUtils.formatMM_yyyy(date);
                    float total1 = 0;
                    for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
                        if (DateUtils.formatMM_yyyy(borrowingAndBillBook.getBorrowDate()).equals(monthYear) && borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                            total1 += borrowingAndBillBook.getDeposit();
                        }
                    }
                    System.out.printf("Doanh thu " + monthYear + " của thư viện là: %.1f VND\n", total1);
                    break;
                case 3:
                    System.out.printf("Tổng doanh thu của thư viện là: %.1f VND\n", totalRevenue());
                    break;
                case 0:
                    actionSeeRevenue = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public static float totalRevenue() {
        float totalRevenue = 0;
        for (BorrowingAndBillBook borrowingAndBillBook : borrowingAndBillBooks) {
            if (borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                totalRevenue += borrowingAndBillBook.getDeposit();
            }
        }
        return totalRevenue;
    }
    public List<BorrowingAndBillBook> InforByPayDate(){
        List<BorrowingAndBillBook> borrowingAndBillBookList=new ArrayList<>();
        for(BorrowingAndBillBook borrowingAndBillBook:borrowingAndBillBooks){
            if(DateUtils.format(borrowingAndBillBook.getPayDate()).equals(DateUtils.format(new Date()))&&borrowingAndBillBook.getePay().equals(EPay.PAID)){
                borrowingAndBillBookList.add(borrowingAndBillBook);
            }
        }
        return borrowingAndBillBookList;
    }
}



