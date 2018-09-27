package sv.gob.bandesal.connect_folder;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class ConnectFolder {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectFolder.class);
	
	@Test
	//@Ignore
	public void copyFiles() {
		try {
	        String yourPeerIP = "192.168.36.153";
	        String path = "smb://" + yourPeerIP + "//FTP-EFI-ENER//somename.log";
	        
	            NtlmPasswordAuthentication auth1 = new NtlmPasswordAuthentication(
	                    null, "ftpener", "AKmt$81.");
	            SmbFile remoteFile = new SmbFile(path, auth1);
	       
	        
	        File localFile = new File("c:/SUService.log");
	        
	       
	        SmbFileOutputStream out = new SmbFileOutputStream(remoteFile);
	        FileInputStream fis = new FileInputStream(localFile);
	        out.write(IOUtils.toByteArray(fis));
	        out.close();
	        
	        	        
	    } catch (Exception e) {
	        logger.error(e.getMessage());
	    }
	}
	
	@Test
	//@Ignore
	public void connectAndListFiles() {
		try {
	        String yourPeerIP = "192.168.36.153";
	        String path = "smb://" + yourPeerIP + "//FTP-EFI-ENER//";
	        
	            NtlmPasswordAuthentication auth1 = new NtlmPasswordAuthentication(
	                    null, "ftpener", "AKmt$81.");
	            SmbFile remoteFile = new SmbFile(path, auth1);
	        
	        Assert.assertTrue(remoteFile.exists());
	        logger.debug(remoteFile.getName());
	        
	        SmbFile[] listFiles = remoteFile.listFiles();
	        for (SmbFile file : listFiles) {
	        	logger.debug("x: " + file.getName() + " last modified: " + new Date(file.getLastModified()) + " length: " + file.getContentLength());
			}
	        
	    } catch (Exception ex) {
	        logger.error(ex.getMessage());
	    }
	}
	
}
