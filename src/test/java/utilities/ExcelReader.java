package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReader {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public static void setExcelFile(String Path,String SheetName) throws Exception {
			try {
					// Open the Excel file
					FileInputStream ExcelFile = new FileInputStream(Path);
					// Access the required test data sheet
					ExcelWBook = new XSSFWorkbook(ExcelFile);
					ExcelWSheet = ExcelWBook.getSheet(SheetName);
					
			} catch (Exception e){
					throw (e);
			}
	
	}
	@SuppressWarnings("deprecation")
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		String CellData = "";
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			/*Consultar el tipo de Celda: (0 = num�rico, 1 = String)
			Cell.getCellType();  */
			if (Cell.getCellType()==1){ CellData = Cell.getStringCellValue(); }
			if (Cell.getCellType()==0){ CellData = Cell.getRawValue(); }
			return CellData;
			}catch (Exception e){
			return "";
			}
	}
	public static int ContarFilas() throws Exception 
	{
		int NumFilas = ExcelWSheet.getLastRowNum();
		return NumFilas;
	}
	public static void setCellData(int RowNum, int ColNum, String TextObtenido) {
		try {
			ExcelWSheet.getRow(RowNum).createCell(ColNum).setCellValue(TextObtenido);
			ExcelWSheet.getRow(RowNum).getCell(ColNum).setCellValue(TextObtenido);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}	
	public static void SaveData(String Path) throws IOException {
		try {
			FileOutputStream ExcelFileOut = new FileOutputStream(Path);
			ExcelWBook.write(ExcelFileOut);
		}
		catch (Exception e)
		{ System.out.println(e.getMessage()); }
	}
	public static void CerrarBook() throws IOException {
		ExcelWBook.close();
	}
}
