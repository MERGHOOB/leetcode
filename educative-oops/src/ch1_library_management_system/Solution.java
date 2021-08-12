package ch1_library_management_system;

import java.time.LocalDate;
import java.util.Date;

// checkout a book, renew a book, return a book
enum AccountStatus {
    ACTIVE,
    IN_ACTIVE,
    CLOSED,
    CANCELLED,
    BLACKLISTED,
    NONE
}

enum BookFormat {
    ebook,
    HARDCOVER,
    AUDIO_BOOK,
    NEWSPAPER,
    MAGAZINE
}


//
enum ReservationStatus {
    RESERVED, COMPLETED

}

enum BookStatus {
    RESERVED,
    AVAILABLE
}

class Address {
    String houseNumber;
    String streetName;
    String city;
    String zipcode;
    String country;
}

class Person {
    String name;
    String emailId;
    String phoneNumber;
    Address address;
}

class Account {
    String id;
    String password;
    AccountStatus accountStatus;
    Person person;

    protected String getId() {
        return null;
    }
}

class Librarian extends Member {
    boolean addBookItem(BookItem bookItem) {
        return true;
    }

    boolean blockMember(BookItem bookItem) {
        return true;
    }

    boolean unBlockMember(BookItem bookItem) {
        return true;
    }


}

class Member extends Account {
    private Date dateOfMembership;

    private int checkedOutCount;

    // checkout a book, renew a book, return a book
    public boolean checkoutBookItem(BookItem bookItem) {
        if (getCheckedOutCount() > Constants.MAX_BOOK_ISSUED_TO_A_MEMBER) {
            throw new RuntimeException("The user already check out maximum number of books"); // this exception can be made CustomExceptions
        }
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation == null || bookReservation.getMemberId() != this.getId()) {
            throw new RuntimeException("This book item is reserved by another user");
        }

        bookReservation.updateStatus(ReservationStatus.COMPLETED);

        boolean checkout = bookItem.checkout(this.getId());
        if (!checkout) {
            return false;
        }

        incrementCheckedOutCount();

        return true;
    }

    public void returnBookItem(BookItem bookItem) {
        checkForFine(bookItem);
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null) {
            bookItem.updateStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
        } else {
            bookItem.updateStatus(BookStatus.AVAILABLE);
        }
        decrementCheckedOutCount();
    }

    public boolean renewBookItem(BookItem bookItem) {
        checkForFine(bookItem);
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null && bookReservation.getMemberId() == this.getId()) {
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
            bookItem.updateDueDate(LocalDate.now().plusDays(10));
            return true;
        }
        if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
            bookItem.updateStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
        } else {
            bookItem.updateStatus(BookStatus.AVAILABLE);
        }

        decrementCheckedOutCount();

        return false;
    }

    private void checkForFine(BookItem bookItem) {

    }

    public int getCheckedOutCount() {
        return checkedOutCount;
    }

//    public void setCheckedOutCount(int checkedOutCount) {
//        this.checkedOutCount = checkedOutCount;
//    }

    public void incrementCheckedOutCount() {
        checkedOutCount++;
    }

    public void decrementCheckedOutCount() {
        checkedOutCount--;
    }
}

class BookReservation {

    public static BookReservation fetchReservationDetails(String barcode) {
        return null;
    }

    public String getMemberId() {
        return null;
    }

    public void updateStatus(ReservationStatus completed) {

    }

    public void sendBookAvailableNotification() {

    }
}

class Constants {
    static int MAX_BOOK_ISSUED_TO_A_MEMBER = 5;
}

// Relate to book
class BookItem {

    private LocalDate dueDate;

    public String getBarcode() {
        return null;
    }

    public boolean checkout(String id) {
        return false;
    }

    public void updateStatus(BookStatus reserved) {

    }

    public void updateDueDate(LocalDate date) {
        this.dueDate = date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

