package com.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

public class FileManager
{
	/**
	 * 
	 * @param saveFilename       : 서버에 저장된 파일이름
	 * @param originalFilename   : 클라이언트가 업로드한 파일 이름
	 * @param path               : 서버에 저장된 경로
	 * @param resp               : Response 객체
	 * @return                   : 다운로드 성공 여부
	 */
	public static boolean doFileDownload(String saveFilename, String originalFilename, String path, HttpServletResponse resp)
	{
		boolean result = false;
		
		try
		{
			String pathname = path + File.separator + saveFilename;
			
			if(originalFilename == null || originalFilename.length() == 0)
			{
				originalFilename = saveFilename;
			}
			
			originalFilename = new String(originalFilename.getBytes("euc-kr"), "8859_1");
			
			File f = new File(pathname);
			
			if(f.exists())  //파일이 존재하는 경우
			{
				// 파일을 클라이언트에게 전송하기 위해서는 전송하는 것이 스트림이어야 함
				resp.setContentType("application/octet-stream");
				
				// 클라이언트에게 전송하는 헤더정보(파일이름을 헤더에 먼저 보낸다.)
				resp.setHeader("Content-disposition", "attachment;filename="+originalFilename);
				
				// 파일의 내용을 전송한다.
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathname));
				
				// 클라이언트에게 전송하는 출력 스트림
				OutputStream os = resp.getOutputStream();
				
				byte[] b = new byte[1024];
				int read;
				while((read = bis.read(b, 0, 1024)) != -1)
				{
					os.write(b, 0, read);  // 최대 1024 바이트씩 클라이언트에게 파일 내용을 전송
				}
				os.flush();
				os.close();
				
				result = true;
			}
			
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}
	
	/**
	 * 
	 * @param filename  : 서버에 저장된 파일 이름
	 * @param path      : 서버에 파일이 저장된 경로
	 */
	
	public static void doFileDelete(String filename, String path)
	{
		try
		{
			String pathname = path+File.separator+filename;
			
			File f = new File(pathname);
			if(f.exists())			
				f.delete();   // 파일 또는 폴더 삭제(폴더는 비어있는 경우만 삭제 가능하고 파일은 하나씪만 삭제 가능)
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	
}
