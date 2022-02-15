package classes.action;

// 페이지 이동 시 필요한 정보 저장하는 객체
// 1. 페이지 이동 정보(주소)
// 2. 페이지 이동 방식(response | forward)
public class ActionForward {
	private String path;
	private boolean isRedirect;
		// true : sendRedirection
		// false : forward 
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
