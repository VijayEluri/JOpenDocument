package openoffice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import at.andiwand.library.io.StreamUtil;


public class TemporaryOpenDocumentFile extends LocatedOpenDocumentFile {
	
	private static final String PREFIX = "ooreadertmp";
	
	public TemporaryOpenDocumentFile(InputStream inputStream)
			throws IOException {
		super(File.createTempFile(PREFIX, ""));
		FileOutputStream outputStream = new FileOutputStream(getFile());
		StreamUtil.writeStream(inputStream, outputStream);
		outputStream.close();
	}
	
}