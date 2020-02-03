package com.vduty.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

@Component
public class zip {

	public boolean createZip(String sourcePath, String targetPath,String targetFileName ) {

		boolean result = false;
		ZipOutputStream zos = null;
		OutputStream outs = null;
		try {
			File file = new File(targetPath);
			if (file.exists()) {
			   outs = new FileOutputStream(new File(targetPath+File.separator + targetFileName));
			}else {			  
			  return false;			  
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			zos = new ZipOutputStream(outs);

			File sourceFile = new File(sourcePath);

			doCompress(sourceFile, zos, sourceFile.getName());
			result = true;

		} catch (Exception e) {
			result = false;
			throw new RuntimeException("zip	error	from	ZipUtils", e);

		} finally {

			if (zos != null) {

				try {

					zos.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}
		return result;
	}

	@SuppressWarnings("unused")
	private void doCompress(File sourceFile, ZipOutputStream zos, String fileName) throws Exception {

		byte[] bufer = new byte[2048];

		if (sourceFile.isFile()) {

			

			zos.putNextEntry(new ZipEntry(fileName));

			

			int length;

			FileInputStream in = new FileInputStream(sourceFile);

			while ((length = in.read(bufer)) != -1) {

				zos.write(bufer, 0, length);

			}

			zos.closeEntry();

			in.close();

		} else {

			File[] files = sourceFile.listFiles();

			if (files == null || files.length == 0) {

				zos.putNextEntry(new ZipEntry(fileName + "/"));

				zos.closeEntry();

			} else {

				for (File file : files) {

					doCompress(file, zos, fileName + "/" + file.getName());

				}

			}

		}

	}

}
