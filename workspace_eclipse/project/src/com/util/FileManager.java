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
	 * @param saveFilename       : ������ ����� �����̸�
	 * @param originalFilename   : Ŭ���̾�Ʈ�� ���ε��� ���� �̸�
	 * @param path               : ������ ����� ���
	 * @param resp               : Response ��ü
	 * @return                   : �ٿ�ε� ���� ����
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
			
			if(f.exists())  //������ �����ϴ� ���
			{
				// ������ Ŭ���̾�Ʈ���� �����ϱ� ���ؼ��� �����ϴ� ���� ��Ʈ���̾�� ��
				resp.setContentType("application/octet-stream");
				
				// Ŭ���̾�Ʈ���� �����ϴ� �������(�����̸��� ����� ���� ������.)
				resp.setHeader("Content-disposition", "attachment;filename="+originalFilename);
				
				// ������ ������ �����Ѵ�.
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(pathname));
				
				// Ŭ���̾�Ʈ���� �����ϴ� ��� ��Ʈ��
				OutputStream os = resp.getOutputStream();
				
				byte[] b = new byte[1024];
				int read;
				while((read = bis.read(b, 0, 1024)) != -1)
				{
					os.write(b, 0, read);  // �ִ� 1024 ����Ʈ�� Ŭ���̾�Ʈ���� ���� ������ ����
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
	 * @param filename  : ������ ����� ���� �̸�
	 * @param path      : ������ ������ ����� ���
	 */
	
	public static void doFileDelete(String filename, String path)
	{
		try
		{
			String pathname = path+File.separator+filename;
			
			File f = new File(pathname);
			if(f.exists())			
				f.delete();   // ���� �Ǵ� ���� ����(������ ����ִ� ��츸 ���� �����ϰ� ������ �ϳ����� ���� ����)
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	
}
