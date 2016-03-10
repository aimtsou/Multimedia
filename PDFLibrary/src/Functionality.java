import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.json.JSONException;
import org.json.JSONObject;

public class Functionality {
	
	/**
	 * Outputs heading with proper text
	 * @param fontchoice is font choice given by user
	 * @param input is the string given by user
	 * @return string to be appended
	 */
	public static String Heading1(int fontchoice, String input) {
		String output = null;
		if (input != null) {
			output = "&;Heading1 fontType:" + fontchoice + "\n" + input + "\n";
		}
		return output;
	}
	
	/**
	 * Outputs heading with proper text
	 * @param fontchoice is font choice given by user
	 * @param input is the string given by user
	 * @return string to be appended
	 */
	public static String Heading2(int fontchoice, String input) {
		String output = null;
		if (input != null) {
			output = "&;Heading2 fontType:" + fontchoice + "\n" + input + "\n";
		}
		return output;
	}
	
	/**
	 * Outputs heading with proper text
	 * @param fontchoice is font choice given by user
	 * @param input is the string given by user
	 * @return string to be appended
	 */
	public static String Heading3(int fontchoice, String input) {
		String output = null;
		if (input != null) {
			output = "&;Heading3 fontType:" + fontchoice + "\n" + input + "\n";
		}
		return output;
	}
	
	/**
	 * Outputs heading with proper text
	 * @param fontchoice is font choice given by user
	 * @param input is the string given by user
	 * @return string to be appended
	 */
	public static String Heading4(int fontchoice, String input) {
		String output = null;
		if (input != null) {
			output = "&;Heading4 fontType:" + fontchoice + "\n" + input + "\n";
		}
		return output;
	}
	
	/* Return NewLine string to be appended */
	public static String NewLine() {
		String output = "&;NewLine\n";
		return output;
	}
	
	/**
	 * Return Image string  
	 * @param: factor to scale image
	 * @param: file image file to be scale
	 * @return output string to be appended
	 * */
	public static String Image(String factor, String file) {
		String output = "&;Image scale:" + factor + "\n" + file + "\n";
		return output;
	}
	
	public static String Paragraph(String s1, String s2, int v1, int v2, int v3) {
		String output = null;
		if (s1 != null && v1 != 0 && v2 != 0 && v3 != 0) {
			output = "&;Paragraph fontSize:" + s1 + " fontType:" + v1 + " fontStyle:" + v2 + " fontColor:" + v3 + "\n" + s2 + "\n";
		}
		return output;
	}
	
	/**
	 * Output returns string to be appended in text area
	 * @param s1 font size given
	 * @param s2 text given
	 * @param v1 font type given
	 * @param v2 font style given 
	 * @param v3 font colour chosen
	 * @return
	 */
	public static String Format(String s1, String s2, int v1, int v2, int v3) {
		String output = null;
		if (s1 != null && v1 != 0 && v2 != 0 && v3 != 0) {
			output = "&;Format fontSize:" + s1 + " fontType:" + v1 + " fontStyle:" + v2 + " fontColor:" + v3 + "\n" + s2 + "\n";
		}
		return output;
	}
	
	/**
	 * Appends the text to textarea 
	 * @param s1 fontsize number
	 * @param v1 fontType chosen
	 * @param v2 fontStyle chosen
	 * @param v3 fontColor chosen
	 * @return output String 
	 * */
	public static String OrderedList(String s1, String s2, int v1, int v2, int v3) {
		String output = null;
		if (s1 != null && v1 != 0 && v2 != 0 && v3 != 0) {
			output = "&;OrderedList fontSize:" + s1 + " fontType:" + v1 + " fontStyle:" + v2 + " fontColor:" + v3 + "\n";
		}
		String [] items = s2.split("\\s+");
		for (int i=0; i < items.length; i++){
			output = output + items[i] + "\n";
		}
		return output;
	}
	
	/**
	 * Appends the text to textarea 
	 * @param s1 fontsize number
	 * @param v1 fontType chosen
	 * @param v2 fontStyle chosen
	 * @param v3 fontColor chosen
	 * @return output String 
	 * */
	public static String UnorderedList(String s1, String s2, int v1, int v2, int v3) {
		String output = null;
		if (s1 != null && v1 != 0 && v2 != 0 && v3 != 0) {
			output = "&;UnorderedList fontSize:" + s1 + " fontType:" + v1 + " fontStyle:" + v2 + " fontColor:" + v3 + "\n";
		}
		String [] items = s2.split("\\s+");
		for (int i=0; i < items.length; i++){
			output = output + items[i] + "\n";
		}
		return output;
	}
	
	/**
	 * Create the new file / Used also for Save As functionality
	 * @param filename given by user
	 * @return filename for usage
	 * */
	public static String New(String filename) {
		if (filename != null) {
        	try {
				FileWriter dir = new FileWriter(filename + ".mlab");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return filename;  
	}
	
	/**
	 * Get File Content reading line by line 
	 * @param filename: get the filename 
	 * @param mlabfile: update status of mlab file
	 * @return file content
	 * */
	public static void GetFileContent(String filename, FileStatus mlabFile) {
		BufferedReader in = null;
		String content = "";
		try {
			in = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (line != null) {
			content += line + "\n";
			try {
				line = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mlabFile.setContent(content);
	}
	
	/**
	 * OpenFile from HDD
	 * @param file to be opened
	 * @param textArea to be read
	 * return true if operation is successful
	 */
	public static boolean OpenFile(File file, JTextArea textArea) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String line = null;
		try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while(line != null){
          textArea.append(line + "\n");
          try {
			line = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        }
        return true;
	}
	
	/**
	 * Save Changes to file
	 * @param selected is the file selected on the list to be saved
	 * @param txt      is the text to be saved to file
	 * @return         if operation was successful
	 */
	public static boolean SaveFile(String selected, String txt) throws IOException {
		FileUtils.writeStringToFile(new File(selected), txt);
		return true;
	}
	
	/**
	 * Delete file
	 * @param: get filename
	 * 
	 */
	public static void Delete(String filename) {
		File file = new File(filename);
		file.delete();
	}
	
	/**
	 * Extract all pages from current pdf
	 * @param selected is the pdf chosen
	 * @param dir      is the directory the pdf is 
	 * @return          success if operation is complete
	 */
	public static boolean ExtractAllPages(File selected, File dir) {
        PDDocument pdDoc = null;
		try {
			pdDoc = PDDocument.load(selected);
			//System.out.println("Entered here");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        int numofpages = pdDoc.getNumberOfPages();
        //System.out.println(numofpages);
        //Creates a new pdf document  
        PDDocument document = null;
        int i;
        //Adds specific page "i" where "i" is the page number and then saves the new pdf document
        for (i = 0; i <= numofpages; i++) {
	        try {
	            document = new PDDocument();   
	            document.addPage((PDPage) pdDoc.getDocumentCatalog().getAllPages().get(i));   
	            document.save(dir + "\\page_" + i + ".pdf");  
	            document.close();  
	        }
	        catch(Exception e) {
	        	//return false;
	        }
        }
		return true;
	}
	
	/**
	 * Merge 2 or more pdfs selected
	 * @param files is an array with the selected pdfs
	 * @param the output filename 
	 * @param dir is the directory the pdf is 
	 * @return success if operation is complete
	 */
	public static boolean MergeFiles(File[] files, Object outputFilename, File dir) {
		boolean success = false;
		int maxPdf = 1000;
		int count = files.length;
		
        PDFMergerUtility pdfMerger = new PDFMergerUtility();       
        pdfMerger.setDestinationFileName(dir + "\\" + outputFilename + ".pdf");
        for (int i = 0; i < count; i++) {
        	File file = files[i];
        	pdfMerger.addSource(file);
        }
     
        // merge pdfs
        try {
			pdfMerger.mergeDocuments();
			success = true;
		} catch (COSVisitorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * ExtractPages outputs a pdf with the range of the selected pages given
	 * @param selected is the file chosen
	 * @param dir      the filename of the output file 
	 * @param start    is the page the extraction should start
	 * @param end      is the page the extraction should end(including the page)
	 * @return          success if operation is complete
	 */
	public static boolean ExtractPages(File selected, File dir, int start, int end) throws IOException {
		PDDocument originalDoc = PDDocument.load(selected);
		int numofpages = originalDoc.getNumberOfPages();
		if ((start > 0) && (end <= numofpages)) {
	        PDDocument document = new PDDocument();  
	        for (int i = start-1; i < end; i++) {
	             document.addPage((PDPage) originalDoc.getDocumentCatalog().getAllPages().get(i));   
	        }
			try {
				document.save(dir + "\\extracted.pdf");
				document.close();
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		//JOptionPane.showMessageDialog(null, "Operation Failed, due to bad start or bad end!", "Warning", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	/**
	 * SplitPages outputs 2 pdf files
	 * @param selected is the file chosen
	 * @param dir      the filename of the output file 
	 * @param start    is the page that we start
	 * @param end      is the page that we end(including the page)
	 * @return          success if operation is complete
	 */
	public static boolean SplitPages(File selected, File dir, int start, int end) throws IOException {
		PDDocument originalDoc = PDDocument.load(selected);
		int numofpages = originalDoc.getNumberOfPages();
		if ((start >= 0) && (end <= numofpages)) {
			System.out.println("I entered here");
			System.out.println(dir);
			System.out.println(selected);
	        PDDocument document = new PDDocument();
	        
	        for (int i = start; i < end; i++) {
	            document.addPage((PDPage) originalDoc.getDocumentCatalog().getAllPages().get(i));   
	        }
			try {
				//document.save(dir + "\\" + selected + "part1.pdf");
				document.save(selected + "_part1.pdf");
				document.close();
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PDDocument document2 = new PDDocument();
			for (int i = end; i < numofpages; i++){
				document2.addPage((PDPage) originalDoc.getDocumentCatalog().getAllPages().get(i)); 
			}
			try {
				//document2.save(dir + "\\" + selected + "part2.pdf");
				document2.save(selected + "_part2.pdf");
				document2.close();
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return true;
		}
		//JOptionPane.showMessageDialog(null, "Operation Failed, due to bad start or bad end!", "Warning", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	/**
	 * Get online content from omdbapi
	 * @param movie title given by user
	 * @return Content from internet to be appened
	 */
	public static String OnlineContent(Object movietitle) {
		// TODO Auto-generated method stub
        try {
			movietitle = java.net.URLEncoder.encode((String) movietitle, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		JSONObject json = null;
		try {
			json = new JSONObject(IOUtils.toString(new URL("http://www.omdbapi.com/?t=" + movietitle + "&plot=full&r=json"), Charset.forName("UTF-8")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String title = (String) json.get("Title");
		String year = (String) json.get("Year");
		String genre = (String) json.get("Genre");
		String imdbrating = (String) json.get("imdbRating");
		String plot = (String) json.get("Plot");
		
		String output = "&;Heading1 fontType:1\n" + title + "\n\n" + "&;Heading3 fontType:2\n" + genre + "\n\n" + "&;Heading3 fontType:2\n" + year + "\n\n" + "&;Heading3 fontType:2\n" + imdbrating + "\n\n" + "&;Paragraph fontSize:11 fontType:1 fontStyle:2 fontColor:1\n" + plot + "\n\n";
		System.out.println(output);
		return output;
	}
	
	/**
	 * Creates the pdf with the given parameters, after splitting text and commands
	 * @param: selected file to be read
	 * 
	 */
	public static void CreatePDF(String selected) throws IOException {
		List<String> commands = new ArrayList<>();
		List<String> texts = new ArrayList<>();
		List<StringBuilder> sbs = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(selected))) {
		    
		    int index = -1;
		    
		    for (String line; (line = br.readLine()) != null;) {
			if(line.startsWith("&;")){
			    commands.add(line);
			    sbs.add(new StringBuilder());
			    index++;
			}
			else if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")){
			    continue;
			}
			else{
			    StringBuilder sb = sbs.get(index);
			    sb.append(line);
			    sb.append(' ');
			}
		    }
		}
		catch (Exception e){
		    System.out.println(e.getMessage());
		}
		
		for(StringBuilder sb : sbs){
		    sb.deleteCharAt(sb.length()-1); // delete last space
		    texts.add(sb.toString());
		}
		
		//System.out.println(commands.size());
		//System.out.println(texts.size());
		int val;
		int fontSize = 0;
		int fontType;
		int fontStyle;
		int fontColour;
		float x = 50;
		float y = 720;
		PDFont font = null;
		PDPageContentStream content = null;
		Map<Integer, PDFont> Times = new HashMap<Integer, PDFont>();
        Times.put(1, PDType1Font.TIMES_ROMAN);
        Times.put(2, PDType1Font.TIMES_BOLD);
        Times.put(3, PDType1Font.TIMES_ITALIC);
        Times.put(4, PDType1Font.TIMES_BOLD_ITALIC);
        
		Map<Integer, PDFont> Helvetica = new HashMap<Integer, PDFont>();
        Helvetica.put(1, PDType1Font.HELVETICA);
        Helvetica.put(2, PDType1Font.HELVETICA_BOLD);
        Helvetica.put(3, PDType1Font.HELVETICA_OBLIQUE);
        Helvetica.put(4, PDType1Font.HELVETICA_BOLD_OBLIQUE);
        
        Map<Integer, PDFont> Courier = new HashMap<Integer, PDFont>();
        Courier.put(1, PDType1Font.COURIER);
        Courier.put(2, PDType1Font.COURIER_BOLD);
        Courier.put(3, PDType1Font.COURIER_OBLIQUE);
        Courier.put(4, PDType1Font.COURIER_BOLD_OBLIQUE);
        
        Map<Integer, Color> color = new HashMap<Integer, Color>();
        color.put(1, Color.black);
        color.put(2, Color.blue);
        color.put(3, Color.red);
        color.put(4, Color.yellow);
        
        Color outcolor;
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
        document.addPage(page);
        PDRectangle mediabox = page.getMediaBox();
        float width = mediabox.getWidth() - 2*50;
        float height = mediabox.getWidth() - 2*50;
		content = new PDPageContentStream(document,page);
		
		for (int i = 0; i < commands.size(); i++) {
			if (commands.get(i).contains("Heading")){
				String[] ptrs = commands.get(i).split(" ");
    			val = Integer.parseInt(String.valueOf(ptrs[1].charAt(ptrs[1].length()-1)));
	    		if (val == 1) {
	    			font = Times.get(2);
	    		}
	    		else if (val == 2) {
	    			font = Helvetica.get(2);
	    		}
	    		else {
	    			font = Courier.get(2);
	    		}
	    		//checkString(commands.get(i), texts.get(i), font, width, content);
	    		if (commands.get(i).contains("Heading1"))
	    			fontSize = 20;
	    		else if (commands.get(i).contains("Heading2"))
	    			fontSize = 18;
	    		else if (commands.get(i).contains("Heading3"))
	    			fontSize = 16;
	    		else
	    			fontSize = 14;
	    			
	    		String text = texts.get(i);
	     	    List<String> lines = new ArrayList<String>();
	     	    int lastSpace = -1;
	     	    while (text.length() > 0) {
	     	        int spaceIndex = text.indexOf(' ', lastSpace + 1);
	     	        if (spaceIndex < 0)
	     	            spaceIndex = text.length();
	     	        String subString = text.substring(0, spaceIndex);
	    				float size = fontSize * font.getStringWidth(subString) / 1000;
	     	        System.out.printf("'%s' - %f of %f\n", subString, size, width);
	     	        if (size > width)
	     	        {
	     	            if (lastSpace < 0)
	     	                lastSpace = spaceIndex;
	     	            subString = text.substring(0, lastSpace);
	     	            lines.add(subString);
	     	            text = text.substring(lastSpace).trim();
	     	            System.out.printf("'%s' is line\n", subString);
	     	            lastSpace = -1;
	     	        }
	     	        else if (spaceIndex == text.length()) {
	     	            lines.add(text);
	     	            System.out.printf("'%s' is line\n", text);
	     	            text = "";
	     	        }
	     	        else {
	     	            lastSpace = spaceIndex;
	     	        }
    	 	   }
 	 	       float leading = 1.5f * fontSize;
 	 	       content.beginText();
 			   content.setFont(font, fontSize);
 			   content.setNonStrokingColor(Color.black);
 	 	       content.moveTextPositionByAmount(x, y); 
 	 	       for (String line: lines) {
 	 	          content.drawString(line);
 	 	          content.moveTextPositionByAmount(0, -leading);
 	 	          y = y - leading;
 	 	       }
 	 	       content.endText();
 	 	       y = y-10;
 	 	       System.out.println(y);
			}
			if (commands.get(i).contains("Paragraph") || commands.get(i).contains("Format")){
				String[] ptrs = commands.get(i).split(" ");
    	    	fontSize = Integer.parseInt(ptrs[1].substring(ptrs[1].lastIndexOf(":") + 1));
    	    	fontType = Integer.parseInt(ptrs[2].substring(ptrs[2].lastIndexOf(":") + 1));
    	    	fontStyle = Integer.parseInt(ptrs[3].substring(ptrs[3].lastIndexOf(":") + 1));
    	    	fontColour = Integer.parseInt(ptrs[4].substring(ptrs[4].lastIndexOf(":") + 1));
    	    	
    	    	System.out.println(fontColour);
    	    	
				if (fontColour == 1)
    	    		outcolor = color.get(1);
    	    	else if (fontColour == 2)
    	    		outcolor = color.get(2);
    	    	else if (fontColour == 3)
    	    		outcolor = color.get(3);
    	    	else
    	    		outcolor = color.get(4);
    	    	
    	    	System.out.println(outcolor);

    	    	if (fontType == 1){
    	    		if (fontStyle == 1)
    	    			font = Times.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Times.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Times.get(3);
     	    		else
     	    			font = Times.get(4);
    	    	}
    	    	else if (fontType == 2) {
    	    		if (fontStyle == 1)
    	    			font = Helvetica.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Helvetica.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Helvetica.get(3);
     	    		else
     	    			font = Helvetica.get(4);	
    	    	}
    	    	else {
    	    		if (fontStyle == 1)
    	    			font = Courier.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Courier.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Courier.get(3);
     	    		else
     	    			font = Courier.get(4);	
    	    	}
    	    	
	    		//y = checkString_Paragraph(commands.get(i), texts.get(i), font, fontSize, fontColour, width, content, x, y);
    			String text = texts.get(i);
    			//System.out.println(text);
    	 	    List<String> lines = new ArrayList<String>();
    	 	    int lastSpace = -1;
    	 	    while (text.length() > 0) {
    	 	        int spaceIndex = text.indexOf(' ', lastSpace + 1);
    	 	        if (spaceIndex < 0)
    	 	            spaceIndex = text.length();
    	 	        String subString = text.substring(0, spaceIndex);
    				float size = fontSize * font.getStringWidth(subString) / 1000;
    	 	        System.out.printf("'%s' - %f of %f\n", subString, size, width);
    	 	        if (size > width)
    	 	        {
    	 	            if (lastSpace < 0)
    	 	                lastSpace = spaceIndex;
    	 	            subString = text.substring(0, lastSpace);
    	 	            lines.add(subString);
    	 	            text = text.substring(lastSpace).trim();
    	 	            System.out.printf("'%s' is line\n", subString);
    	 	            lastSpace = -1;
    	 	        }
    	 	        else if (spaceIndex == text.length())
    	 	        {
    	 	            lines.add(text);
    	 	            System.out.printf("'%s' is line\n", text);
    	 	            text = "";
    	 	        }
    	 	        else
    	 	        {
    	 	            lastSpace = spaceIndex;
    	 	        }
    	 	    }
 	 	       	   int count = 0;
    	 	       float leading = 1.2f * fontSize;
    	 	       float fontheight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
    	 	       content.beginText();
    			   content.setFont(font, fontSize);
    			   content.setNonStrokingColor(outcolor);
    	 	       content.moveTextPositionByAmount(x, y);            
    	 	       //for (String line: lines) {
    	 	       /*int line = 0;
    	 	       while (line < lines.size()) {
	    	 	          content.drawString(lines.get(line));
	    	 	          content.moveTextPositionByAmount(0, -leading);
	    	 	          y = y - leading;
	    	 	          //content.drawString("This is the line at position   "+ x +" ,"+ y);
	    	 	          count++;
	    	 	          line++;
                          if (y < 500) {
                        	  content.endText();
                        	  //content.close();
                        	  //content.endText();
                              System.out.println("**********");
                              //leading = leading + 50;
                              PDPage page1 = new PDPage();
                              page.setMediaBox(PDPage.PAGE_SIZE_A4);
                              document.addPage(page);

                              //content = new PDPageContentStream(document, page1);
                              //contentStream1.setFont(PDType1Font.HELVETICA_BOLD, 16);
                              content.beginText();
                              x=50;
                              y=720;
                              content.moveTextPositionByAmount(x, y);
                              //contentStream1.drawString(line);
                              //contentStream1.endText();
                              //contentStream1.close(); 
                           }  
    	 	       }*/
     	 	       for (String line: lines) {
      	 	          content.drawString(line);
      	 	          content.moveTextPositionByAmount(0, -leading);
      	 	          y = y - leading;
      	 	       }
    	 	       content.endText();
    	 	       if (count == 1)
    	 	    	   y = y - 20;
    	 	       else
    	 	    	   //y = y - count * fontSize - (count-1)*;
    	 	    	  y = y - 20;
    	 	       
    	 	       System.out.println(y);
			}
			if (commands.get(i).contains("OrderedList") || commands.get(i).contains("UnorderedList")){
				String[] ptrs = commands.get(i).split(" ");
    	    	fontSize = Integer.parseInt(ptrs[1].substring(ptrs[1].lastIndexOf(":") + 1));
    	    	fontType = Integer.parseInt(ptrs[2].substring(ptrs[2].lastIndexOf(":") + 1));
    	    	fontStyle = Integer.parseInt(ptrs[3].substring(ptrs[3].lastIndexOf(":") + 1));
    	    	fontColour = Integer.parseInt(ptrs[4].substring(ptrs[4].lastIndexOf(":") + 1));
    	    	
    	    	
				if (fontColour == 1)
    	    		outcolor = color.get(1);
    	    	else if (fontColour == 2)
    	    		outcolor = color.get(2);
    	    	else if (fontColour == 3)
    	    		outcolor = color.get(3);
    	    	else
    	    		outcolor = color.get(4);
    	    	
    	    	if (fontType == 1){
    	    		if (fontStyle == 1)
    	    			font = Times.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Times.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Times.get(3);
     	    		else
     	    			font = Times.get(4);
    	    	}
    	    	else if (fontType == 2) {
    	    		if (fontStyle == 1)
    	    			font = Helvetica.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Helvetica.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Helvetica.get(3);
     	    		else
     	    			font = Helvetica.get(4);	
    	    	}
    	    	else {
    	    		if (fontStyle == 1)
    	    			font = Courier.get(1);
    	    		else if (fontStyle == 2)
    	    			font = Courier.get(2);
     	    		else if (fontStyle == 3)
    	    			font = Courier.get(3);
     	    		else
     	    			font = Courier.get(4);	
    	    	}
    	    	
	    		//y = checkString_Paragraph(commands.get(i), texts.get(i), font, fontSize, fontColour, width, content, x, y);
    	    	String mylist[] = texts.get(i).split(" ");
    	    	for (int idx = 0; idx < mylist.length; idx++) {
	    			String text = mylist[idx];
	    			//System.out.println(text);
	    	 	    List<String> lines = new ArrayList<String>();
	    	 	    int lastSpace = -1;
	    	 	    while (text.length() > 0) {
	    	 	        int spaceIndex = text.indexOf(' ', lastSpace + 1);
	    	 	        if (spaceIndex < 0)
	    	 	            spaceIndex = text.length();
	    	 	        String subString = text.substring(0, spaceIndex);
	    				float size = fontSize * font.getStringWidth(subString) / 1000;
	    	 	        System.out.printf("'%s' - %f of %f\n", subString, size, width);
	    	 	        if (size > width)
	    	 	        {
	    	 	            if (lastSpace < 0)
	    	 	                lastSpace = spaceIndex;
	    	 	            subString = text.substring(0, lastSpace);
	    	 	            lines.add(subString);
	    	 	            text = text.substring(lastSpace).trim();
	    	 	            System.out.printf("'%s' is line\n", subString);
	    	 	            lastSpace = -1;
	    	 	        }
	    	 	        else if (spaceIndex == text.length())
	    	 	        {
	    	 	            lines.add(text);
	    	 	            System.out.printf("'%s' is line\n", text);
	    	 	            text = "";
	    	 	        }
	    	 	        else
	    	 	        {
	    	 	            lastSpace = spaceIndex;
	    	 	        }
	    	 	    }
	    	 	       int count = 0;
	    	 	       float leading = 1.5f * fontSize;
	    	 	       //float height = fontSize;
	    	 	       content.beginText();
	    			   content.setFont(font, fontSize);
	    			   content.setNonStrokingColor(outcolor);
	    			   System.out.println(y);
	    	 	       content.moveTextPositionByAmount(x+20, y);            
	    	 	       for (String line: lines) {
	    	 	    	   if (commands.get(i).contains("Unordered"))
	    	 	    	   	  content.drawString("- ");
	    	 	    	   else 
	    	 	    		  content.drawString(idx+1 + ". ");
		    	 	       content.drawString(line);
		    	 	       //content.moveTextPositionByAmount(0, -leading);
		    	 	       count++;
	    	 	       }
	    	 	       content.endText();
	    	 	       y = y-1.5f * fontSize;
				}
    	    	y = y-1.5f * fontSize;
			}
			if (commands.get(i).contains("Image")) {
				String[] ptrs = commands.get(i).split(" ");
    			float scale = Float.parseFloat(ptrs[1].substring(ptrs[1].lastIndexOf(":") + 1));
    			System.out.println(scale);
				BufferedImage bimg = ImageIO.read(new File(texts.get(i)));
				int imgwidth = bimg.getWidth();
				int imgheight = bimg.getHeight();
				
	            PDPixelMap ximage = new PDPixelMap(document, bimg);
	            content.drawXObject(ximage, x, y-imgheight*scale, imgwidth*scale, imgheight*scale);
	            
	            y = y - imgheight*scale - 30;
			}
			if (commands.get(i).contains("Line")) {
				content.beginText();
				content.setFont(PDType1Font.TIMES_ROMAN, 12);
				content.endText();
				y = y - 12;
			}
		}
		try {
			content.close();
			document.save(selected + ".pdf");
			//document.save("letmesee.pdf");
			document.close();
		} catch (COSVisitorException e) {
			e.printStackTrace();
		}
		//System.out.println(commands);
		//System.out.println(texts);
	}
	
	/**
	 * Update status bar with the file information
	 * @param  filename of file
	 * @return String that updates the statusbar
	 */
	public static String updateStatus(String filename) throws IOException {
		File file = new File(filename);
		String output = null;
		
        Path path = Paths.get("");
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        BasicFileAttributes basicattribute = Files.readAttributes(path, BasicFileAttributes.class);
        UserPrincipal owner = ownerAttributeView.getOwner();
        //System.out.println("owner: " + owner.getName());
		
		output = "File: " + file.getName() + " Size: " + file.length() + " Owner: " + owner  + " Last modified: " + new Date(file.lastModified());
		return output;
	}

}
