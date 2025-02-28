package in.shriram.dreambiketwowheelerloan.sanction.serviceimpl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import in.shriram.dreambiketwowheelerloan.sanction.model.CustomerDetails;
import in.shriram.dreambiketwowheelerloan.sanction.repository.SanctionRepository;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;
import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionServiceImpl implements SanctionServiceI{

	@Autowired
	SanctionRepository sr;
	
	@Autowired
	JavaMailSender sender;

	@Autowired
	RestTemplate rt;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public CustomerDetails generateSactionId(Integer customerId) {
		// TODO Auto-generated method stub
		
		CustomerDetails customer = sr.findById(customerId).get();
		
		String title = "Shriram Finance Ltd.";

		Document document = new Document(PageSize.A4);
		
		String content1 = "\n\n Dear " + customer.getApplicantname()
		+ ","
		+ "\nABC Shriram Ltd. is Happy to informed you that your loan application has been approved. ";

		String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
				+ "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
				+ "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
				+ "\n\nSincerely,\n\n" + "Sujata Patil (Credit Manager)";

		ByteArrayOutputStream opt = new ByteArrayOutputStream();
		
		PdfWriter.getInstance(document, opt);
		document.open();

		Image img = null;
		try {
			img = Image.getInstance("C:\\Users\\sujata\\Pictures\\logo\\twowheel.png");
			img.scalePercent(50, 50);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);

		} catch (BadElementException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
		Paragraph titlepara = new Paragraph(title, titlefont);
		titlepara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlepara);

		Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Paragraph paracontent1 = new Paragraph(content1, titlefont2);
		document.add(paracontent1);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 2, 2 });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.WHITE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(5, 5, 161);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(5, 5, 161);

		cell.setPhrase(new Phrase("Loan amount Sanctioned", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase(String.valueOf("₹ " + customer.getLoanAmountScantioned()),
				font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("loan tenure", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase(String.valueOf(customer.getLoanTenureMonth()), font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("interest rate", font));
		table.addCell(cell);

		cell.setPhrase(
				new Phrase(String.valueOf(customer.getRateofInterest()) + " %", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Sanction letter generated Date", font));
		table.addCell(cell);

		Date date = new Date();
		//String curdate = date.toString();
		customer.setDate(date);;
		cell.setPhrase(
				new Phrase(String.valueOf(customer.getDate()), font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Total loan Amount with Intrest", font));
		table.addCell(cell);

		document.add(table);

		Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Paragraph paracontent2 = new Paragraph(content2, titlefont3);
		document.add(paracontent2);
		document.close();
		
		ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
		byte[] bytes = byt.readAllBytes();
		customer.setSanctionedletter(bytes);;

		
		
		MimeMessage mimemessage = sender.createMimeMessage();
		
		byte[] sanctionLetter = customer.getSanctionedletter();

		try {
			MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
			mimemessageHelper.setFrom(fromEmail);
			mimemessageHelper.setTo("srjp90@gmail.com");
			mimemessageHelper.setSubject("Shriram Finance Ltd. Sanction Letter");
			String text = "Dear " + customer.getApplicantname()
					+ ",\n" + "\n"
					+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
					+ customer.getLoanAmountScantioned() + " for "
					+ customer.getLoanTenureMonth() + ".\n" + "\n"
					+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
					+ "\n"
					+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
					+ "\n" + "Thank you for your interest in our services.";

			mimemessageHelper.setText(text);

			mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
			sender.send(mimemessage);
		
		
		} catch (Exception e) {
			System.out.println("Email Failed to Send!!!!!!");
			e.printStackTrace();
		}
		
		
		
		return sr.save(customer);
		
		
		
		//return null;
	}

	@Override
	public CustomerDetails addSanction(Integer customerId) {
		// TODO Auto-generated method stub
		
		Customer co = rt.getForObject("http://localhost:7777/apploan/getCustomerVerified/"+customerId, Customer.class);
		CustomerDetails cDetails = new CustomerDetails();
		
		cDetails.setApplicantname(co.getCustomerName());
		cDetails.setContactdetails(co.getCustomerMobileNumber());
		cDetails.setDate(new Date());
		//cDetails.setInteresType("");
		cDetails.setOnRoadPrice(co.getOnRoadPrice());
		cDetails.setLoanTenureMonth(co.getRequiredTenure());
		
		cDetails.setInteresType("Compound Interest");
		
		//LOGIC FOR LOAN TENURE (IN MONTHS)
				if(co.getOnRoadPrice()>=50000) {
					cDetails.setLoanTenureMonth(12);
				}
				else if(co.getOnRoadPrice()>=100000) {
					cDetails.setLoanTenureMonth(24);
				}
				else if(co.getOnRoadPrice()>=150000) {
					cDetails.setLoanTenureMonth(36);
				}
				else {
					cDetails.setLoanTenureMonth(48);
				}
				
		//LOGIC FOR RATE OF INTEREST
				if(co.getCibil().getCibilRemark().equals("Good")) {
					cDetails.setRateofInterest(10.2f);
				}
				else if(co.getCibil().getCibilRemark().equals("Very Good")) {
					cDetails.setRateofInterest(9.1f);
				}
				else if(co.getCibil().getCibilRemark().equals("Excellent")) {
					cDetails.setRateofInterest(7.9f);
				}		
		
		//SANCTIONED LOAN AMOUNT WILL BE 80% OF ON ROAD PRICE
				cDetails.setLoanAmountScantioned(0.8*co.getOnRoadPrice());	//Check input of onRoadPrice
				
		//Logic for Compound Interest Calculation
				int compoundingFrequency=12;
				double totalAmountPayable=cDetails.getLoanAmountScantioned()*Math.pow(1+(cDetails.getRateofInterest()/compoundingFrequency),
						compoundingFrequency*cDetails.getLoanTenureMonth());
				
				
		//Logic for EMI		
				double emi=totalAmountPayable/cDetails.getLoanTenureMonth();
		
		cDetails.setMonthlyEmiAmount(emi);		

		
		return cDetails;
	}

	
	
}
