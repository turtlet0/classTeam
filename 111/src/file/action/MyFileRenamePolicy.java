package file.action;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;
// UUID 이용 참고 페이지 : https://github.com/yj-park/chyoni/blob/c2b42e723099f0afdf6c8334741a82395d4bfa20/00_board_prj/src/kr/co/mlec/file/MlecFileRenamePolicy.java#L8
public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File f) {
		// ex) C:/java90/.../upload/2017/01/19/aaa.jpg
		String name = f.getName(); // aaa.jpg
		String parent = f.getParent(); // 전체 폴더 주소(파일명 위)
		
		String ext = ""; // 확장자(extension)
		int idx = name.lastIndexOf(".");
		if (idx != -1) {
			ext = name.substring(idx); // .jpg
			name = name.substring(0, idx);
		}
		
		// UUID 이용 고유 파일명 생성
		String uName = UUID.randomUUID().toString() + "_"+name;
		
		return new File(parent, uName + ext);
	}

}
