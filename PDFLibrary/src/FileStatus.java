public class FileStatus {
	
	public FileStatus(String fileName) {
		super();
		this.fileName = fileName;
	}
	
    private String fileName = "";
    private String content = "";
    private boolean isChanged = false;
    
    /* Get the filename of the file */
    public String getFileName() {
        return fileName;
    }
    
    /* Set the given filename to new file or saved file */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /* Get the content of file */
    public String getContent() {
        return content;
    }
    
    /* Set the content of file */
    public void setContent(String content) {
        this.content = content;
    }
    
    /* Return status of a file */
    public boolean isChanged() {
        return isChanged;
    }
    
    /* Change status of file */
    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }
}
