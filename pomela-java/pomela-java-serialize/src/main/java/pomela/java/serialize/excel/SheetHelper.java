package pomela.java.serialize.excel;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

/**
 * Created by tao.he on 2015/11/5.
 *
 * @see pomela.java.serialize.excel.ExcelHelper
 * @see pomela.java.serialize.excel.ExcelHelperBuilder
 */
public abstract class SheetHelper<T> {
	private SXSSFWorkbook workbook;

	private Sheet sheet;

	private CellStyle defaultTitleCellStyle;

	private CellStyle defaultContentCellStyle;

	/**
	 * 使用ExcelHelper不需要在创建SheetHelper时设置workbook
	 * 
	 * @param workbook
	 * @return
	 */
	public SheetHelper workbook(SXSSFWorkbook workbook) {
		this.workbook = workbook;
		defaultTitleCellStyle = getDefaultTitleCellStyle(workbook);
		defaultContentCellStyle = getDefaultContentFontStyle(workbook);
		return this;
	}

	public Sheet create() {
		sheet = createEmptySheet(workbook, getSheetName());
		createTitleRow(workbook, sheet, getTitles());
		createContentRows(workbook, sheet, getData());
		return sheet;
	}

	protected abstract String getSheetName();

	protected Sheet createEmptySheet(SXSSFWorkbook workbook, String sheetName) {
		return workbook.createSheet(sheetName);
	}

	protected void createTitleRow(SXSSFWorkbook workbook, Sheet sheet, List<String> titles) {
		Row titleRow = sheet.createRow(0);
		for (int i = 0; i < titles.size(); i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellStyle(defaultTitleCellStyle);
			cell.setCellValue(titles.get(i));
		}
	}

	protected abstract List<String> getTitles();

	protected abstract void createContentRows(SXSSFWorkbook workbook, Sheet sheet, T data);

	protected abstract T getData();

	protected CellStyle getDefaultTitleCellStyle(SXSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	protected CellStyle getDefaultContentFontStyle(SXSSFWorkbook workbook) {
		return null;
	}

	protected void setCellValue(Row hssfRow, int column, Object value, CellStyle style) {
		Cell cell = hssfRow.createCell(column);
		cell.setCellStyle(style);
		this.setCellValue(cell, value);
	}

	protected void setCellValue(Row hssfRow, int column, Object value) {
		Cell cell = hssfRow.createCell(column);
		if (null != defaultContentCellStyle) {
			cell.setCellStyle(defaultContentCellStyle);
		}
		this.setCellValue(cell, value);
	}

	private void setCellValue(Cell cell, Object value) {
		if (value == null) {
			return;
		}
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Float) {
			cell.setCellValue((Float) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof BigDecimal) {
			cell.setCellValue(((BigDecimal) value).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} else {
			cell.setCellValue(value.toString());
		}
	}
}
