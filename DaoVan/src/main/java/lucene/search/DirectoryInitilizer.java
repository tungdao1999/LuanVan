package lucene.search;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class DirectoryInitilizer {
	public static Directory createFSDirectory(String dirPath) {
		try {
			return FSDirectory.open(Paths.get(dirPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Directory createRAMDirectory() {
		return new RAMDirectory();
	}
}
