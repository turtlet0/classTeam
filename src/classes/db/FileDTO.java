package classes.db;

public class FileDTO {
	private String uploadFileName;

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public String toString() {
		return "FileDTO [uploadFileName=" + uploadFileName + "]";
	}
	
	 
}
