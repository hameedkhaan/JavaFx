package application;

public class Books {

	private int id;
	private String title;
	private String author;
	private String year;
	private int pages;
	
	public Books(int id, String title, String author, String year, int pages) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	
}
