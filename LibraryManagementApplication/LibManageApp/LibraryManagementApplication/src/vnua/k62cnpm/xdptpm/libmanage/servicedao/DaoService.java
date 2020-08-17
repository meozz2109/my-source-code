/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.servicedao;

import java.sql.SQLException;
import java.util.List;

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.SignoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Location;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.LogInAccount;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Publisher;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.ReceiptNote;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Supplier;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.User;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.AuthorDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.SignoutSheetsDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.DetaileDocDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.DocumentDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.LocationDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.LogInAccountDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.PublisherDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.ReaderDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.ReceiptNoteDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.CheckoutSheetsDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.SubjectDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.SupplierDao;
import vnua.k62cnpm.xdptpm.libmanage.jdbc.controller.UserDao;
//import qlythuvien.model.Reader;

/**
 *
 * @author Admin
 */
public class DaoService {
	private ReaderDao readerDao;
	private UserDao userDao;
	private AuthorDao authorDao;
	private SupplierDao supplierDao;
	private SignoutSheetsDao signoutSheetsDao;
	private DetaileDocDao detaileDocDao;
	private DocumentDao documentDao;
	private LocationDao locationDao;
	private LogInAccountDao logInAccountDao;
	private PublisherDao publisherDao;
	private ReceiptNoteDao receiptNoteDao;
	private CheckoutSheetsDao checkoutSheetsDao;
	private SubjectDao subjectDao;

	public DaoService() {
		readerDao = new ReaderDao();
		userDao = new UserDao();
		authorDao = new AuthorDao();
		supplierDao = new SupplierDao();
		signoutSheetsDao = new SignoutSheetsDao();
		detaileDocDao = new DetaileDocDao();
		documentDao = new DocumentDao();
		locationDao = new LocationDao();
		logInAccountDao = new LogInAccountDao();
		publisherDao = new PublisherDao();
		receiptNoteDao = new ReceiptNoteDao();
		checkoutSheetsDao = new CheckoutSheetsDao();
		subjectDao = new SubjectDao();
	}

	public List<Reader> getAllReader() {

		return readerDao.getAllReader();
	}

	public List<User> getAllUser() {

		return userDao.getAllUser();

	}

	public List<Author> getAllAuthor() {

		return authorDao.getAllAuthor();

	}

	public List<Supplier> getAllSupplier() {

		return supplierDao.getAllSupplier();

	}

	public List<Subject> getAllSubject() {

		return subjectDao.getAllSubject();

	}

	public List<SignoutSheets> getAllSignoutSheets() {

		return signoutSheetsDao.getAllSignoutSheets();

	}

	public List<DetaileDoc> getAllDetaileDoc() {

		return detaileDocDao.getAllDetaileDoc();

	}

	public List<Document> getAllDocument() {

		return documentDao.getAllDocument();

	}

	public List<Location> getAllLocation() {

		return locationDao.getAllLocation();

	}

	public List<LogInAccount> getAllLogInAccount() {

		return logInAccountDao.getAllLogInAccount();

	}

	public List<Publisher> getAllPublisher() {

		return publisherDao.getAllPublisher();

	}

	public List<ReceiptNote> getAllReceiptNote() {

		return receiptNoteDao.getAllReceiptNote();

	}

	public List<CheckoutSheets> getAllCheckoutSheets() {

		return checkoutSheetsDao.getAllCheckoutSheets();

	}

	public void deleteReader(String ID) {
		readerDao.deleteReader(ID);

	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void addLocation(Location location) {
		locationDao.addLocation(location);
	}

	public void addSupplier(Supplier supplier) {
		supplierDao.addSupplier(supplier);
	}

	public void addAuthor(Author author) {
		authorDao.addAuthor(author);
	}

	public void addSubject(Subject subject) {
		subjectDao.addSubject(subject);
	}

	public void addLogInAccount(LogInAccount account) {
		logInAccountDao.addLogInAccount(account);
	}

	public void addPublisher(Publisher publisher) {
		publisherDao.addPublisher(publisher);
	}

	public void addReader(Reader reader) {
		readerDao.addReader(reader);
	}

	public void addSignoutSheets(SignoutSheets signoutSheets) {
		signoutSheetsDao.addSignoutSheets(signoutSheets);
	}

	public void addCheckoutSheets(CheckoutSheets checkoutSheets) {
		checkoutSheetsDao.addCheckoutSheets(checkoutSheets);
	}

	public void addDocument(Document document) {
		documentDao.addDocument(document);
	}

	public void addDetaileDoc(DetaileDoc detaileDoc) {
		detaileDocDao.addDetaileDoc(detaileDoc);
	}

	public void addReceiptNote(ReceiptNote receiptNote) {
		receiptNoteDao.addReceiptNote(receiptNote);
	}

	public Author getAuthorById(String ID) {
		return authorDao.getAuthorById(ID);
	}

	public Location getLocationById(String ID) {
		return locationDao.getLocationById(ID);
	}

	public Supplier getSupplierById(String ID) {
		return supplierDao.getSupplierById(ID);
	}

	public Publisher getPublisherById(String ID) {
		return publisherDao.getPublisherById(ID);
	}

	public LogInAccount getLogInAccountById(String ID) {
		return logInAccountDao.getLogInAccountById(ID);
	}

	public Reader getReaderById(String ID) {
		return readerDao.getReaderById(ID);
	}

	public CheckoutSheets getCheckoutById(String ID) {
		return checkoutSheetsDao.getCheckoutById(ID);
	}

	public SignoutSheets getSignoutById(String ID) {
		return signoutSheetsDao.getSignoutById(ID);
	}

	public Document getDocumentById(String ID) {
		return documentDao.getDocumentById(ID);
	}

	public DetaileDoc getDetaileDocById(String ID) {
		return detaileDocDao.getDetaileById(ID);
	}

	public User getUserById(String ID) {
		return userDao.getUserById(ID);
	}

	public ReceiptNote getReceiptNoteById(String ID) {
		return receiptNoteDao.getReceiptNoteById(ID);
	}
	public Subject getSubjectById(String ID) {
		return subjectDao.getSubjectById(ID);
	}
	
	
	
	public void updateAuthor(Author author) {
		authorDao.updateAuthor(author);
	}

	public void updateLocation(Location location) {
		locationDao.updateLocation(location);

	}

	public void updateSupplier(Supplier supplier) {
		supplierDao.updateSupplier(supplier);
	}

	public void updatePublisher(Publisher publisher) {
		publisherDao.updatePublisher(publisher);
	}

	public void updateLoginaccount(LogInAccount logInAccount) {
		logInAccountDao.updateLoginaccount(logInAccount);
	}

	public void updateReader(Reader reader) {
		readerDao.updateReader(reader);
	}

	public void updateDocument(Document document) {
		documentDao.updateDocument(document);
	}

	public void updateDetaileDoc(DetaileDoc detaileDoc) {
		detaileDocDao.updateDetaileDoc(detaileDoc);
	}

	public void updateCheckoutSheets(CheckoutSheets checkoutSheets) {
		checkoutSheetsDao.updateCheckoutSheets(checkoutSheets);
	}

	public void updateSignoutSheets(SignoutSheets signoutSheets) {
		signoutSheetsDao.updateSignoutSheets(signoutSheets);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	public void updatReceiptNote(ReceiptNote receiptNote) {
		receiptNoteDao.updateReceiptNote(receiptNote);
	}
	public void updateSubject(Subject subject) {
		subjectDao.updateSubject(subject);
	}

	
	public void deleteDetailed(String ID) {
		detaileDocDao.deleteDetailed(ID);
	}

	public void deleteDocument(String ID) {
		documentDao.deleteDocument(ID);
	}

	public void deleteLocation(String ID) {
		locationDao.deleteLocation(ID);
	}

	public void deleteLogInAccount(String ID) {
		logInAccountDao.deleteLogInAccount(ID);
	}

	public void deletePublisher(String ID) {
		publisherDao.deletePublisher(ID);
	}

	public void deleteReceiptnote(String ID) {
		receiptNoteDao.deleteReceiptnote(ID);
	}

	public void deleteSos(String ID) {
		signoutSheetsDao.deleteSos(ID);
	}

	public void deleteSubject(String ID) {
		subjectDao.deleteSubject(ID);
	}

	public void deleteAuthor(String ID) {
		authorDao.deleteAuThor(ID);
	}

	public void deleteUser(String ID) {
		userDao.deleteUser(ID);
	}

	public void deleteCos(String ID) {
		checkoutSheetsDao.deleteCos(ID);
	}

	public void deleteSupplier(String ID) {
		supplierDao.deleteSupplier(ID);
	}

	// Stat and Rep
	
		public List<Document> getRecIdleDocs() {
			return documentDao.getRecentIdleDocs();
		}

		public List<Document> getRecBorDocs() {
			return documentDao.getRecentBorDocs();
		}

		public List<Document> getRecMostBrDocs() {
			return documentDao.getMostBrDocs();
		}

		public List<Reader> getOveReaders() {
			return readerDao.getOverdueReaders();
		}
}
