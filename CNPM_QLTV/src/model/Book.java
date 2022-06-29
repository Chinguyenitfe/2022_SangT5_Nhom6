package model;

public class Book {
	private String id;
	private String nameBook;
	private String nameAuthor;
	private String nxb;
	private int quantity;

	public Book() {
	}
	

	public Book(String id, String nameBook, String nameAuthor, String nxb, int quantity) {
		super();
		this.id = id;
		this.nameBook = nameBook;
		this.nameAuthor = nameAuthor;
		this.nxb = nxb;
		this.quantity = quantity;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getNxb() {
		return nxb;
	}

	public void setNxb(String nxb) {
		this.nxb = nxb;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", nameBook=" + nameBook + ", nameAuthor=" + nameAuthor + ", nxb=" + nxb
				+ ", quantity=" + quantity + "]";
	}

}
