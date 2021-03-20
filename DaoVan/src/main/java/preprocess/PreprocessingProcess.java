package preprocess;

import java.io.File;
import java.io.IOException;
import java.util.List;

import DirectoryHelper.DirectoryActor;
import resource.ResourcesUtils;

public class PreprocessingProcess {
	private static String ITVIEC_IN = ResourcesUtils.resourcePath + "/crawled/ITViec/";
	private static String VIBLO_IN = ResourcesUtils.resourcePath + "/crawled/Viblo/";
	private static String WIKI_IN = ResourcesUtils.resourcePath + "/crawled/Wiki/";
	private static String UPLOAD_FILE_IN = ResourcesUtils.resourcePath + "/crawled/uploadFile/";

	private static String ITVIEC_OUT = ResourcesUtils.resourcePath + "/lucene/crawled/ITViec/";
	private static String VIBLO_OUT = ResourcesUtils.resourcePath + "/lucene/crawled/Viblo/";
	private static String WIKI_OUT = ResourcesUtils.resourcePath + "/lucene/crawled/Wiki/";
	private static String UPLOAD_FILE_OUT = ResourcesUtils.resourcePath + "/lucene/crawled/uploadFile/";

	public void preprocessingITViec() {
		List<File> listFile = DirectoryActor.getPathOfTextCrawled(ITVIEC_IN);

		for (int i = 0; i < listFile.size(); i++) {

			String content = PreprocessService.PreprocessRawTextFile(ITVIEC_IN + listFile.get(i).getName());

			listFile.get(i).delete();

			try {
				DirectoryActor.writeFile(content, ITVIEC_OUT + listFile.get(i).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void preprocessingViblo() {
		List<File> listFile = DirectoryActor.getPathOfTextCrawled(VIBLO_IN);

		for (int i = 0; i < listFile.size(); i++) {

			String content = PreprocessService.PreprocessRawTextFile(VIBLO_IN + listFile.get(i).getName());

			listFile.get(i).delete();

			try {
				DirectoryActor.writeFile(content, VIBLO_OUT + listFile.get(i).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void preprocessingUploadFile() {
		List<File> listFile = DirectoryActor.getPathOfTextCrawled(UPLOAD_FILE_IN);

		for (int i = 0; i < listFile.size(); i++) {

			String content = PreprocessService.PreprocessRawTextFile(UPLOAD_FILE_IN + listFile.get(i).getName());

			listFile.get(i).delete();

			try {
				DirectoryActor.writeFile(content, UPLOAD_FILE_OUT + listFile.get(i).getName());
			} catch (IOException e) {

				e.printStackTrace();
				return;
			}
		}
	}

}
