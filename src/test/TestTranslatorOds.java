package test;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;

import openoffice.OpenDocumentSpreadsheet;
import openoffice.TemporaryOpenDocumentFile;
import openoffice.html.HtmlDocument;
import openoffice.html.ods.TranslatorOds;


public class TestTranslatorOds {
	
	public static void main(String[] args) throws Throwable {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.CANCEL_OPTION) return;
		
		File file = fileChooser.getSelectedFile();
		FileInputStream inputStream = new FileInputStream(file);
		
		TemporaryOpenDocumentFile documentFile = new TemporaryOpenDocumentFile(
				inputStream);
		OpenDocumentSpreadsheet spreadsheet = new OpenDocumentSpreadsheet(
				documentFile);
		System.out.println(spreadsheet.getTableNames());
		TranslatorOds translatorOds = new TranslatorOds(spreadsheet);
		
		long startTime = System.nanoTime();
		HtmlDocument pageOds = translatorOds.translate();
		long endTime = System.nanoTime();
		
		System.out.println(((endTime - startTime) / 1000000000d) + "s");
		
		String htmlFileName = file.getName();
		int lastDot = htmlFileName.lastIndexOf(".");
		if (lastDot != -1) htmlFileName = htmlFileName.substring(0, lastDot);
		htmlFileName += ".html";
		pageOds.getHtmlDocument();
		pageOds.save(new File(file.getParentFile(), htmlFileName));
	}
	
}