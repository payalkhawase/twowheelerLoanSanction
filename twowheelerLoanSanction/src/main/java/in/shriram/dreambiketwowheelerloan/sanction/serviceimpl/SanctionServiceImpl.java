package in.shriram.dreambiketwowheelerloan.sanction.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import in.shriram.dreambiketwowheelerloan.sanction.model.Customer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import in.shriram.dreambiketwowheelerloan.sanction.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.sanction.repository.CustomerRepository;
import in.shriram.dreambiketwowheelerloan.sanction.repository.SanctionRepository;
import in.shriram.dreambiketwowheelerloan.sanction.servicei.SanctionServiceI;
import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionServiceImpl implements SanctionServiceI{

	@Autowired
	SanctionRepository sr;
	
	@Autowired
	CustomerRepository cr;

	@Autowired
	RestTemplate rt;
	
	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public SanctionLetter generateSactionId(int customerId) {
		
		
		Customer co = rt.getForObject("http://localhost:7777/apploan/getCustomerVerified/"+customerId, Customer.class);
		
		SanctionLetter sanction = sr.findById(co.getSanctionletter().getSanctionId()).get();
		
		String title = "Shriram Finance Ltd.";

		Document document = new Document(PageSize.A4);
		
		String content1 = "\n\n Dear " + sanction.getApplicantName()
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
			img = Image.getInstance("G:/bike.png");
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

		cell.setPhrase(new Phrase(String.valueOf("₹ " + sanction.getLoanAmtSanctioned()),
				font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("loan tenure", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase(String.valueOf(sanction.getLoanTenureInMonth()), font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("interest rate", font));
		table.addCell(cell);

		cell.setPhrase(
				new Phrase(String.valueOf(sanction.getRateOfInterest()) + " %", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Sanction letter generated Date", font));
		table.addCell(cell);

		Date date = new Date();

		sanction.setSanctionDate(date);;
		cell.setPhrase(
				new Phrase(String.valueOf(sanction.getSanctionDate()), font1));
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
		sanction.setSanctionletterpdf(bytes);;

		sanction.setStatus("Offered");
		
		MimeMessage mimemessage = sender.createMimeMessage();
		
		byte[] sanctionLetter = sanction.getSanctionletterpdf();

		try {
			MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
			mimemessageHelper.setFrom(fromEmail);
			mimemessageHelper.setTo(co.getCustomerEmail());
			mimemessageHelper.setSubject("Shriram Finance Ltd. Sanction Letter");
			String text = "Dear " + sanction.getApplicantName()
					+ ",\n" + "\n"
					+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
					+ sanction.getLoanAmtSanctioned() + " for "
					+ sanction.getLoanTenureInMonth() + ".\n" + "\n"
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
		
		return sr.save(sanction);
		
	}

	@Override
	public SanctionLetter addSanction(int customerId) {
		// TODO Auto-generated method stub
		
		Customer co = rt.getForObject("http://localhost:7777/apploan/getCustomerVerified/"+customerId, Customer.class);
		SanctionLetter cDetails = new SanctionLetter();
		
		cDetails.setApplicantName(co.getCustomerName());
		cDetails.setContactDetails(co.getCustomerMobileNumber());
		cDetails.setSanctionDate(new Date());
		cDetails.setOnRoadPrice(co.getOnRoadPrice());
		cDetails.setLoanTenureInMonth(co.getRequiredTenure());
		cDetails.setModeOfPayment("Online");
		
		cDetails.setInterestType("Compound Interest");
		
		//LOGIC FOR LOAN TENURE (IN MONTHS)
		if (co.getOnRoadPrice() >= 150000) {
		    cDetails.setLoanTenureInMonth(48);
		} else if (co.getOnRoadPrice() >= 100000) {
		    cDetails.setLoanTenureInMonth(36);
		} else if (co.getOnRoadPrice() >= 50000) {
		    cDetails.setLoanTenureInMonth(24);
		} else {
		    cDetails.setLoanTenureInMonth(12);
		}

				
		//LOGIC FOR RATE OF INTEREST
		if(co.getCibil().getCibilRemark().equals("Good")) {
			cDetails.setRateOfInterest(10.2f);
		}
		else if(co.getCibil().getCibilRemark().equals("Very Good")) {
			cDetails.setRateOfInterest(9.1f);
		}
		else if(co.getCibil().getCibilRemark().equals("Excellent")) {
			cDetails.setRateOfInterest(7.9f);
		}		
		
		//SANCTIONED LOAN AMOUNT WILL BE 80% OF ON ROAD PRICE
		double loanSanctioned = 0.8 * co.getOnRoadPrice();
		cDetails.setLoanAmtSanctioned(loanSanctioned);

				    // Convert annual interest rate to monthly rate
		double rate = cDetails.getRateOfInterest() / 100; // Annual rate to decimal
		double monthlyRate = rate / 12; // Monthly rate
		int tenureMonths = cDetails.getLoanTenureInMonth();
				    
		double loanBalance = loanSanctioned;
		double totalInterestPaid = 0.0;

				    // Using LinkedHashSet to store EMI values while maintaining order
		Set<Double> monthlyEMISet = new LinkedHashSet<>();

				    // Dynamic EMI Calculation (Reducing Balance Method)
		for (int month = 1; month <= tenureMonths; month++) {
			double interestForMonth = loanBalance * monthlyRate; // Monthly interest
			totalInterestPaid += interestForMonth;

				        // Calculate new EMI dynamically
			int remainingMonths = tenureMonths - month + 1;
			double emi;
			if (remainingMonths > 0) {
				emi = (loanBalance * monthlyRate * Math.pow(1 + monthlyRate, remainingMonths)) /
				                  (Math.pow(1 + monthlyRate, remainingMonths) - 1);
			} else {
	            emi = loanBalance; // Last installment to clear balance
			}

			double principalPaid = emi - interestForMonth;
	        loanBalance -= principalPaid; // Reduce loan balance
	        if (loanBalance < 0) loanBalance = 0; // Prevent negative balance

		        monthlyEMISet.add(emi); // Store EMI in Set
		    }

				    // Set last month's EMI as the final EMI value
			 cDetails.setMonthlyEmiAmount(loanBalance == 0 ? 0 : ((LinkedHashSet<Double>) monthlyEMISet).toArray(new Double[0])[monthlyEMISet.size() - 1]);
//				    cDetails.setTotalPayableAmount(loanSanctioned + totalInterestPaid);
			 cDetails.setStatus("Created");

				    // Store the entire EMI set (optional, modify class to support it)
				    
				    //THIS GIVES RECURRING MONTHLY EMI   (need to create field)
			 
//				    cDetails.setMonthlyEmiSet(monthlyEMISet); 

				
				//NO SUCH FIELD PRESENT
//				cDetails.setTotalPayableAmount(cDetails.getLoanAmtSanctioned() + totalInterest);

		
			 cDetails.setStatus("Created"); 
		
		
			 SanctionLetter so = sr.save(cDetails);
		
			 co.setSanctionletter(so);
		
			 rt.put("http://localhost:7777/apploan/upadtedata",co);
		
			 return so;
	}

	@Override
	public Customer updateSanctionStatus(int customerId, String status) {
		
		
		Customer cust=rt.getForObject("http://localhost:7777/apploan/getaCustomer/"+customerId, Customer.class);
		
		SanctionLetter sl=sr.findById(cust.getSanctionletter().getSanctionId()).get();
		sl.setStatus(status);
		
		if(status.equals("Accepted")) {
			
			cust.setLoanStatus("Sanctioned");
		}else {
			cust.setLoanStatus("Not Sanctioned");
		}
		rt.put("http://localhost:7777/apploan/upadtedata",cust);
		sr.save(sl);
		return cr.save(cust);
	}

	 
	@Override
	public List getSanctionList() {
		
		return sr.findAllByStatus("Offered");
	}

	


	

//	@Override
//	public Customer updateSanctionStatus(int customerId, String status) {
//		
//		Customer cust=rt.getForObject("http://localhost:7777/apploan/getaCustomer/"+customerId, Customer.class);
//		cust.setSanctionStatus(status);
//		
//		return sr.save(cust);
//	}

	

	 
	
	
	
	

}
