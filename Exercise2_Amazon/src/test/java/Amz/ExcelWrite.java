package Amz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	 

	public class ExcelWrite {
		private static XSSFWorkbook ItemsReport = new XSSFWorkbook();
	    private static XSSFSheet FlashDeals = ItemsReport.createSheet("Flash Deals");
		
		public static void createExcel() 
		{
				FlashDeals.createRow(0);
				FlashDeals.getRow(0).createCell(0).setCellValue("No.");
				FlashDeals.getRow(0).createCell(1).setCellValue("Product Name");
		}
		
		public void addItems (int n, String cellValue) 
		{
				FlashDeals.createRow(n);
				FlashDeals.getRow(n).createCell(0).setCellValue(n);
				FlashDeals.getRow(n).createCell(1).setCellValue(cellValue);
		}	
		
		public void saveExcel() throws IOException
		{
				File file = new File("C:\\Report\\ItemsReport.xlsx");
				FileOutputStream fos =new FileOutputStream(file);
				ItemsReport.write(fos);
				ItemsReport.close();
		}
		
	}
