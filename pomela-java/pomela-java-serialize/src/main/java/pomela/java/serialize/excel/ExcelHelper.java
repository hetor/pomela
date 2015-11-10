package pomela.java.serialize.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Created by tao.he on 2015/11/5.
 *
 * use ExcelHelperBuilder
 *
 * toZip(), toFile(), toByteArray(), toTmpFile()
 *
 * @see pomela.java.serialize.excel.ExcelHelperBuilder
 * @see pomela.java.serialize.excel.SheetHelper
 */
public class ExcelHelper {
	private SXSSFWorkbook workbook;

	private String exportDir;

	private String filename;

	private String fileSuffix;

	private List<SheetHelper> sheetHelpers;

	private AtomicBoolean created = new AtomicBoolean(false);

	public ExcelHelper(SXSSFWorkbook workbook, String exportDir, String filename, String fileSuffix,
			List<SheetHelper> sheetHelpers) {
		this.workbook = workbook;
		this.exportDir = exportDir;
		this.filename = filename;
		this.fileSuffix = fileSuffix;
		this.sheetHelpers = sheetHelpers;
	}


	public File toZip() {
		createSheets();

		ZipOutputStream zipOut = null;
		try {
			File zipFile = new File(this.exportDir + File.separator + this.filename + ".zip");
			zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
			zipOut.putNextEntry(new ZipEntry(filename + fileSuffix));
			workbook.write(zipOut);
			return zipFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != zipOut) {
					zipOut.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				clear();
			}
		}
	}

	public File toFile() {
		createSheets();

		FileOutputStream fos = null;
		try {
			File file = new File(this.exportDir + File.separator + this.filename + this.fileSuffix);
			fos = new FileOutputStream(file);
			workbook.write(fos);
			return file;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public byte[] toByteArray() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				clear();
			}
		}
	}

	/**
	 * 使用该方法，生成的文件名随机，filePath及getFilePath()无效
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public File toTmpFile() {
		createSheets();

		FileOutputStream fos = null;
		try {
			File tmpFile = File.createTempFile(filename, fileSuffix);
			fos = new FileOutputStream(tmpFile);
			workbook.write(fos);
			return tmpFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				clear();
			}
		}
	}

	private void createSheets() {
		if(created.compareAndSet(false, true)) {
			for (SheetHelper sheetHelper : sheetHelpers) {
				sheetHelper.workbook(workbook).create();
			}
		}
	}

	public void clear() {
		workbook.dispose();
	}
}
