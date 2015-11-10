package pomela.java.serialize.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Created by tao.he on 2015/11/5.
 *
 * @see pomela.java.serialize.excel.ExcelHelper
 * @see pomela.java.serialize.excel.SheetHelper
 */
public class ExcelHelperBuilder {
	private int rowAccessWindowSize = 100;

	private String windowsDir = "D:\\temp";

	private String linuxDir = "/home/appops/orders_excel";

	private String filename = "temp_" + System.currentTimeMillis();

	// private String filePrefix = "_prefix";
	private String fileSuffix = ".xlsx";

	private List<SheetHelper> sheetHelpers = new ArrayList<>();

	private SXSSFWorkbook workbook;

	public ExcelHelper build() {
		if (null == workbook) {
			workbook = new SXSSFWorkbook(rowAccessWindowSize);
		}

		return new ExcelHelper(workbook, exportDir(), filename, fileSuffix, sheetHelpers);
	}

	public ExcelHelperBuilder workbook(SXSSFWorkbook workbook) {
		this.workbook = workbook;
		return this;
	}

	public ExcelHelperBuilder rowAccessWindowSize(int rowAccessWindowSize) {
		this.rowAccessWindowSize = rowAccessWindowSize;
		return this;
	}

	public ExcelHelperBuilder addSheet(SheetHelper helper) {
		sheetHelpers.add(helper);
		return this;
	}

	public ExcelHelperBuilder filename(String filename) {
		this.filename = filename;
		return this;
	}

	/**
	 * default is .xlsx
	 * 
	 * @param fileSuffix
	 * @return
	 */
	public ExcelHelperBuilder fileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
		return this;
	}

	public ExcelHelperBuilder windowsDir(String windowsDir) {
		this.windowsDir = windowsDir;
		return this;
	}

	public ExcelHelperBuilder linuxDir(String linuxDir) {
		this.linuxDir = linuxDir;
		return this;
	}

	private String exportDir() {
		String path;
		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			path = windowsDir;
		} else {
			path = linuxDir;
		}

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return path;
	}
}
