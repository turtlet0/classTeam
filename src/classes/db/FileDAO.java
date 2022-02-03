package classes.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

public class FileDAO {
	public String getFilename(Part part) {
		String filePath = null;
		
		String fileName = null;
		
		// filePath 구하기 위한 코드 part.getHeader
		String contentDispositionHeader = part.getHeader("content-disposition");
		System.out.println("FDAO. contentDispositionHeader: " + contentDispositionHeader);
		String[] elements = contentDispositionHeader.split(";");

		
		// 일자 값 생성
		Date from = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String time = sdf.format(from);
		
		// filePath
		for(String element: elements) {
			System.out.println("서브스트링 전: " + element);
			filePath = element.substring(element.indexOf('=') + 1);
			System.out.println("트림 전: "+filePath);
			filePath = filePath.trim().replace("\"",""); // 쌍따옴표 " 제거
				// trim() : 양 끝 공백 제거
			System.out.println("트림 후:" + filePath);
		}
		
		
		// fileName = "C:\\downloads\\file.exe"
		// array = {"C:", "downlaods", "file.exe"}
		String[] array = filePath.split("\\\\");
		
		// fileName = "file.exe"
		fileName = array[array.length - 1];
		
		// fileName = "file.exe"
		// array2 = {"file", "exe"}

		// fileName2 = "a.b.c.txt"
		// array2_long = {"a", "b", "c", "txt"}
		String[] array2 = fileName.split("\\.");
		
		// 파일 확장자 필터링 후 파일 이름 정재작업 진행
		if (array2[array2.length - 1].equals("exe") || array2[array2.length - 1].equals("bat")) {
			return "7";
		} else {
			// 파일명에 일자 연결하기
			// 파일명에 다수의 Dot('.')이 있으면 IF 문 진행
			if (array2.length > 2) {

				// fileName = "a"
				fileName = array2[0];
				for (int i = 1; i < array2.length - 1; i++) {

					// fileName = "a.b"
					// fileName = "a.b.c"
					fileName = fileName.concat(".").concat(array2[i]);
				}
				// fileName = "a.b.c" + "_(" + "2106250000" + ")" + "." + "txt"
				// fileName = "a.b.c_(2106250000).txt"
				fileName = fileName + "_(" + time + ")" + "." + array2[array2.length - 1];

			} else {
				// fileName = "file" + "_(" + "2106250000" + ")" + "." + "exe"
				// fileName = "file_(2106250000).exe"
				fileName = array2[0] + "_(" + time + ")" + "." + array2[1];
			}

			// fileName = "file_(2106250000).exe"
			// fileName = "a.b.c_(2106250000).txt"
			return fileName;
		}
		
		
		
		
		
	}
}
